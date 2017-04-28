package Controller.Staff;

import Controller.Error.ErrorController;
import Controller.Validate.Validate;
import Model.*;
import View.Staff.ModifyPanel.ModifyStaffPanel;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Andre on 17.03.2017.
 * Controlleur de la fenêtre de modification d'employée
 */
public class ModifyStaffController {
    private ErrorController ecError = null;
    private ModifyStaffPanel mspModifyStaff = null;
    private Personne personne = null;
    private DBInteraction querry = null;

    /**
     * Constructeur du controlleur
     */
    public ModifyStaffController(Personne personne) {
        mspModifyStaff = new ModifyStaffPanel(this,personne);
        this.personne = personne;
    }

    /**
     * Méthode permettant de modifier la personne
     */
    public void checkModifyStaff(String sFirstName, String sLastName, String sSupervisor, String sEMail, String sAddress,
                                 String sNPA, String sCity, String sCountry, String sPhone) {

        boolean bLastName = Validate.isAlphabetic(sLastName);
        if(!bLastName){
            mspModifyStaff.setLastNameError("Champ nom contenant des caractères innaproprié");
        }
        boolean bFirstName = Validate.isAlphabetic(sFirstName);
        if(!bFirstName){
            mspModifyStaff.setFirstNameError("Champ prénom contenant des caractères innaproprié");
        }
        boolean bEmail = Validate.isEmail(sEMail);
        if(!bEmail){
            mspModifyStaff.setEmailError("Champ Email non conforme");
        }
        //boolean bAddress = Validate.isAlphabetic(address);
        boolean bNPA = Validate.isNumeric(sNPA);
        if(!bNPA){
            mspModifyStaff.setNPAError("Le champ NPA ne contient pas que des chiffres");
        }
        boolean bCity = Validate.isAlphabetic(sCity);
        if(!bCity){
            mspModifyStaff.setCityError("Le champ ville non conforme");
        }
        boolean bCountry = Validate.isAlphabetic(sCountry);
        if(!bCountry){
            mspModifyStaff.setCountryError("Le champ pays non conforme");
        }
        int cp = 0;
        boolean bChange = true;
        try{
            cp = Integer.parseInt(sNPA);
        } catch(Exception exception){
            bChange = false;
            exception.printStackTrace();
            ecError = new ErrorController(exception.toString());
        }
        dbConnection();
        boolean bAddAddress = true;
        try{
            Pays pays = new Pays();
            pays.setPays(sCountry);

            Ville ville = new Ville();
            ville.setCp(cp);
            ville.setVille(sCity);
            ville.setPays(pays);

            Adresse adresse = new Adresse();
            adresse.setAdresse(sAddress);
            adresse.setVille(ville);

            querry.insAddress(adresse, ville, pays);
        } catch(SQLException sqlException){
            bAddAddress = false;
            sqlException.printStackTrace();
            ecError = new ErrorController(sqlException.toString());
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
        boolean bPhone = Validate.isPhoneNumber(sPhone);

        if(bFirstName && bLastName && bEmail && bCity && bNPA && bCountry && bChange && bAddAddress && bPhone){
            personne.setEmail(sEMail);
            personne.setPrenom(sFirstName);
            personne.setNom(sLastName);
/**************** problème, pas possible de récupérer le numéro d'une adresse ainsi que pour le responsable ***************/
            modifyStaff(personne);
        }
    }

    private void modifyStaff(Personne personne){
        dbConnection();
        // Permet d'insérer la personne modifié
        boolean erreur = false;
        try {
            querry.updatePersonne(personne);
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            ecError = new ErrorController(sqlException.toString());
            erreur = true;
        }
        if(!erreur){
            mspModifyStaff.getParent().hide();
        }

    }

    private void dbConnection(){
        // Permet de joindre la BD
        try {
            querry = new DBInteraction();
        } catch (ExceptionDataBase exceptionDB) {
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        }
    }

    private String getCity(int Adresse){
        String city = "";
        dbConnection();

        return city;
    }
}
