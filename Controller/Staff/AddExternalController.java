package Controller.Staff;

import Controller.Error.ErrorController;
import Controller.Validate.Validate;
import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Intervenant;
import View.Staff.StaffAddPanel.AddExternal;

import java.sql.SQLException;

/**
 * Created by Bureau on 23.04.2017.
 */
public class AddExternalController {
    private AddExternal aeExternal = null;
    private DBInteraction querry = null;
    private ErrorController ecError = null;

    public AddExternalController(Intervenant external){
        aeExternal = new AddExternal(this);
    }

    /**
     * Méthode permettant d'établir la connection avec la DB
     */
    private void dbConnection(){
        try {
            querry = new DBInteraction();
        } catch (ExceptionDataBase exceptionDB) {
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        }
    }

    /**
     * Méthode permettant de checker qu'un intervenant est OK avant de l'insérer
     * @param lastName Nom de la personne
     * @param firstName Prénom de la personne
     * @param avs Numéro AVS de la personne
     * @param email EMail de la personne
     * @param address Adresse de la personne
     * @param phone Numéro de télephone de la personne
     */
    public void checkExternal(String lastName, String firstName,String avs, String email, String address,
                              String phone){

        boolean bLastName = Validate.isAlphabetic(lastName);
        boolean bFirstName = Validate.isAlphabetic(firstName);
        boolean bAVS = Validate.isAVS(avs);
        boolean bEmail = Validate.isEmail(email);
        //boolean bAddress = Validate.is(address);
        boolean bPhone = Validate.isPhoneNumber(phone);
        if(bLastName && bFirstName && bAVS && bEmail && bPhone){
            dbConnection();
            Intervenant external = new Intervenant(avs, lastName, firstName, 1,email, phone);
            insertExternal(external);
        }
    }

    /**
     * Méthode permettant d'interragir avec la DB pour insérer une personne
     * @param external personne à insérer dans la DB
     */
    public void insertExternal(Intervenant external){
        /*
        try{
            querry.insertExternal(external);
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            ecError = new ErrorController(exceptionsql.toString());
        }
        */
        System.out.println("Intervenant externe inséré");
    }
}
