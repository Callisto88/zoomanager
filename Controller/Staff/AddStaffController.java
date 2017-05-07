package Controller.Staff;

import Controller.Error.ErrorController;
import Controller.Validate.Validate;
import Model.*;
import View.Staff.StaffAddPanel.AddStaff;

import java.lang.reflect.Executable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Andre on 17.03.2017.
 * Class permettant d'instancier et de controller tout ce qui concerne l'ajout de personnel
 */
public class AddStaffController {
    private AddStaff add = null;

    private ErrorController ecError = null;
    private DBInteraction querry = null;

    /**
     * Constructeur du controlleur de la fenêtre d'ajout de personnel
     * @param statuts liste des statuts actuels
     * @param contract liste des contract actuels
     * @param supervisor liste des responsables actuels
     * @param countries liste des pays actuels
     */
    public AddStaffController(ArrayList<String> statuts,ArrayList<String> contract,
                              ArrayList<Personne> supervisor, ArrayList<Pays> countries) {
        add = new AddStaff(this, statuts, contract, supervisor, countries);
        add.disableError();
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
     * @param city Ville de la personne
     * @param country Pays de la personne
     * @param phone Numéro de télephone de la personne
     * @param supervisor Superviseur de la personne
     * @param status Statut de la personne
     * @param contract Contrat de la personne
     */
    public void checkPersonne(String lastName, String firstName, int day, int month, int year, String avs, String email, String address, String npa,
                              String city, String country, String phone, String supervisor, String status, String contract) {

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
        boolean bBirthday = Validate.isDate(year, month, day);
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

        // ! Plus nécessaire vu que le NPA est maintenant un attribut de type entier dans la classe Adresse
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
        int cityID = 0;
        Pays pays = new Pays();
        pays.setPays(country);

        Ville ville = new Ville();
        ville.setVille(city);
        ville.setCp(cp);
        ville.setPays(pays);

        Adresse adresse = new Adresse();
        adresse.setAdresse(address);
        adresse.setVille(ville);

        if (bNPA && bChange && bCity && bCountry) {
            try {
                cityID = querry.insAddress(adresse, ville, pays);
            } catch (ExceptionDataBase exceptionDataBase) {
                exceptionDataBase.printStackTrace();
                bAddAddress = false;
            } catch (SQLException sqlException) {
                bAddAddress = false;
                sqlException.printStackTrace();
                ecError = new ErrorController("Erreur insertion adresse " + sqlException.toString());
            }
        }
        // Permet de checker le numéro de télephone
        boolean bPhone = Validate.isPhoneNumber(phone);
        if(!bPhone){
            add.setPhoneError("Champ télephone non conforme");
        }
        if (bLastName && bFirstName && bBirthday && bAVS && bEmail && bNPA && bChange && bCity && bCountry && bAddAddress && bPhone) {
            dbConnection();
/***************** Problème pour récupérer l'id d'un responsable **********************/

// Expected : Personne(null, noAVS, prenom, nom, adresse, email, telephone, dateNaissance, responsable, statut, dateDebut, typeContrat)

            Personne personne = new Personne(avs, firstName, lastName, adresse, email, phone, new Date(year, month, day),
                                    getPersonne(supervisor).getIdPersonne(), status, new Date(year, month, day), contract);
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

    private Personne getPersonne(String name){
        dbConnection();
        ArrayList<Personne> personnes = null;
        String lastName = name.substring(0, name.indexOf(" "));
        String firstName = name.substring(name.indexOf(" ") + 1, name.length());
        try {
            personnes = querry.selEmployeeParPrenomNom(firstName, lastName);
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            ecError = new ErrorController(sqlException.toString());
        }
        Personne supervisor = null;
        for(Personne p : personnes){
            supervisor = p;
        }
        return supervisor;
    }
}
