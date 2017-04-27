package Controller.Staff;

import Controller.Error.ErrorController;
import Controller.Validate.Validate;
import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Personne;
import View.Staff.StaffAddPanel.AddStaff;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Andre on 17.03.2017.
 * Class permettant d'instancier et de controller tout ce qui concerne l'ajout de personnel
 */
public class AddStaffController {
    private AddStaff add = null;

    private ArrayList<String> status = null;
    private ArrayList<String> contract = null;

    private ErrorController ecError = null;
    private DBInteraction querry = null;

    /**
     * Constructeur du controlleur de la fenêtre d'ajout de personnel
     */
    public AddStaffController() {
        dbConnection();
        getStatus();
        getContract();
        add = new AddStaff(this, status, contract);
        add.disableError();
    }

    /**
     * Méthode permettant d'établir la connection avec la DB
     */
    private void dbConnection(){
        try {
            querry = new DBInteraction();
        } catch (ExceptionDataBase exceptionDB) {
            exceptionDB.printStackTrace();
            ecError = new ErrorController("Erreur dbCo " + exceptionDB.toString());
        }
    }

    /**
     * Méthode permettant d'obtenir le listing des différents statuts présent dans la DB
     */
    private void getStatus(){
        try{
            status = querry.getAllStatuts();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            ecError = new ErrorController("Erreur récup statut " + exceptionDB.toString());
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            ecError = new ErrorController("Erreur récup statut " + exceptionsql.toString());
        }
    }

    /**
     * Méthode permettant d'obtenir le listing des différents contrats présent dans la DB
     */
    private void getContract(){
        try{
            contract = querry.selAllContractType();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            ecError = new ErrorController("Erreur récup contrat " + exceptionDB.toString());
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            ecError = new ErrorController("Erreur récup contrat " + exceptionsql.toString());
        }
    }

    private void getSupervisor(){

    }

    /**
     * Méthode permettant de checker qu'une personne est OK avant de l'insérer
     * @param lastName Nom de la personne
     * @param firstName Prénom de la personne
     * @param day Jour de naissance de la personne
     * @param month Mois de naissance de la personne
     * @param year Année de naissance de la personne
     * @param avs Numéro AVS de la personne
     * @param email EMail de la personne
     * @param address Adresse de la personne
     * @param npa NPA de la personne
     * @param city Ville de la personne
     * @param country Pays de la personne
     * @param phone Numéro de télephone de la personne
     * @param supervisor Superviseur de la personne
     * @param status Statut de la personne
     * @param contract Contrat de la personne
     */
    public void checkPersonne(String lastName, String firstName, int day, int month, int year, String avs, String email, String address,
                              String npa, String city, String country, String phone, String supervisor, String status, String contract){

        // Permet de checker le nom
        boolean bLastName = Validate.isAlphabetic(lastName);
        if(!bLastName){
            add.setLastNameError("Champ nom contenant des caractères innaproprié");
        }
        // Permet de checker le prénom
        boolean bFirstName = Validate.isAlphabetic(firstName);
        if(!bFirstName){
            add.setFirstNameError("Champ prénom contenant des caractères innaproprié");
        }
        // Permet de checker la date de naissance
        boolean bBirthday = Validate.isDate(day, month, year);
        if(!bBirthday){
            add.setBirthdayError("Champs date inaproprié");
        }
        // Permet de checker le numéro AVS
        boolean bAVS = Validate.isAVS(avs);
        if(!bAVS){
            add.setAVSError("Champ AVS non conforme");
        }
        // Permet de checker l'email
        boolean bEmail = Validate.isEmail(email);
        if(!bEmail){
            add.setEmailError("Champ Email non conforme");
        }
        //boolean bAddress = Validate.isAlphabetic(address);
        // Permet de checker le npa
        boolean bNPA = Validate.isNumeric(npa);
        if(!bNPA){
            add.setNPAError("Le champ NPA ne contient pas que des chiffres");
        }
        // Permet de checker la ville
        boolean bCity = Validate.isAlphabetic(city);
        if(!bCity){
            add.setCityError("Le champ ville non conforme");
        }
        // Permet de checker le pays
        boolean bCountry = Validate.isAlphabetic(country);
        if(!bCountry){
            add.setCountryError("Le champ pays non conforme");
        }
        // Permet de convertir en int le npa
        int cp = 0;
        boolean bChange = true;
        if(bNPA) {
            try {
                cp = Integer.parseInt(npa);
            } catch (Exception exception) {
                bChange = false;
                exception.printStackTrace();
                ecError = new ErrorController("Erreur conversion NPA " + exception.toString());
            }
        }

        // Permet d'insérer l'adresse dans la db
        dbConnection();
        boolean bAddAddress = true;
        try {
            querry.insAddress(address, cp, city, country);
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        } catch(SQLException sqlException){
            bAddAddress = false;
            sqlException.printStackTrace();
            ecError = new ErrorController("Erreur insertion adresse " + sqlException.toString());
        }
        // Permet de checker le numéro de télephone
        boolean bPhone = Validate.isPhoneNumber(phone);
        if(!bPhone){
            add.setPhoneError("Champ télephone non conforme");
        }
        if(bLastName && bFirstName && bBirthday && bAVS && bEmail && bNPA && bCity && bCountry && bChange && bAddAddress && bPhone){
            dbConnection();
/***************** Problème pour récupérer l'id d'une adresse pour l'insertion ainsi que l'id d'un responsable **********************/
            Personne personne = new Personne(avs, firstName, lastName, 1,email, phone, new Date(year, month, day),2,status,new Date(1,1,1),contract);
            insertPersonne(personne);
        }
    }

    /**
     * Méthode permettant d'interragir avec la DB pour insérer une personne
     * @param personne personne à insérer dans la DB
     */
    public void insertPersonne (Personne personne){
        try{
            querry.insertPersonne(personne);
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            ecError = new ErrorController("Erreur insertion personne " + exceptionsql.toString());
        }
    }

}
