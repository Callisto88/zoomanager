package Controller.Staff;

import Controller.Error.ErrorController;
import Controller.Validate.Validate;
import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Intervenant;
import View.Staff.StaffAddPanel.AddExternal;

import java.sql.SQLException;

/**
 * Created by André on 23.04.2017.
 * Class permettant d'instancier et de controller tout ce qui concerne l'ajout d'intervenant
 */
public class AddExternalController {
    private AddExternal aeExternal = null;
    private DBInteraction querry = null;
    private ErrorController ecError = null;

    /**
     * Constructeur du controlleur d'ajout d'intervenant
     */
    public AddExternalController(){
        aeExternal = new AddExternal(this);
        aeExternal.disableError();
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
     * @param lastName Nom de l'intervenant
     * @param firstName Prénom de l'intervenant
     * @param avs Numéro AVS de l'intervenant
     * @param email EMail de l'intervenant
     * @param address Adresse de l'intervenant
     * @param npa NPA de l'intervenant
     * @param city Ville de l'intervenant
     * @param country Pays de l'intervenant
     * @param phone Numéro de télephone de l'intervenant
     */
    public void checkExternal(String lastName, String firstName,String avs, String email, String address, String npa, String city,
                              String country, String phone){

        boolean bLastName = Validate.isAlphabetic(lastName);
        boolean bFirstName = Validate.isAlphabetic(firstName);
        boolean bAVS = Validate.isAVS(avs);
        boolean bEmail = Validate.isEmail(email);
        //boolean bAddress = Validate.isStreet(address);
/************************* A checker les adresses ******************************/
        boolean bNPA = Validate.isNumeric(npa);
        boolean bCity = Validate.isAlphabetic(city);
        boolean bCountry = Validate.isAlphabetic(country);
        boolean bPhone = Validate.isPhoneNumber(phone);
        if(bLastName && bFirstName && bAVS && bEmail && bNPA && bCity && bCountry && bPhone){
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
