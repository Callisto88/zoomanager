package Controller.Staff;

import Controller.Error.ErrorController;
import Controller.Validate.Validate;
import Model.*;
import View.Staff.AddPanel.AddStaff;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Andre on 17.03.2017.
 * Class permettant d'instancier et de controller tout ce qui concerne l'ajout de personnel
 */
public class AddStaffController {
    private AddStaff add = null;
    private StaffController scControlleur = null;

    private DBInteraction querry = null;

    /**
     * Constructeur du controlleur de la fenêtre d'ajout de personnel
     * @param scControlleur controlleur géneral
     * @param statuts liste des statuts actuels
     * @param contract liste des contract actuels
     * @param supervisor liste des responsables actuels
     * @param countries liste des pays actuels
     */
    public AddStaffController(StaffController scControlleur, ArrayList<String> statuts,ArrayList<String> contract,
                              ArrayList<Personne> supervisor, ArrayList<Pays> countries) {
        this.scControlleur = scControlleur;
        add = new AddStaff(this, statuts, contract, supervisor, countries);
        add.disableError();
    }

    /**
     * Méthode permettant de checker qu'une personne est OK avant de l'insérer
     * @param lastName Nom de la personne
     * @param firstName Prénom de la personne
     * @param dateNaissance date de naissance de l'employé
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
    public void checkPersonne(String lastName, String firstName, Date dateNaissance, Date dateDebut, String avs,
                              String email, String address, String npa, String city, String country, String phone,
                              int supervisor, String status, String contract) {

        // Permet de checker le nom
        boolean bLastName = Validate.isAlphabetic(lastName) && !lastName.equals("nom");
        if(!bLastName){
            add.setLastNameError("Champ nom contenant des caractères innaproprié");
        }
        // Permet de checker le prénom
        boolean bFirstName = Validate.isAlphabetic(firstName) && !firstName.equals("prénom");
        if(!bFirstName){
            add.setFirstNameError("Champ prénom contenant des caractères innaproprié");
        }
        // Permet de checker le numéro AVS
        boolean bAVS = Validate.isAVS(avs);
        if(!bAVS){
            add.setAVSError("Champ AVS non conforme");
        }
        // Permet de checker l'email
        boolean bEmail = true;
        if(!email.isEmpty()) {
            bEmail = Validate.isEmail(email);
            if (!bEmail) {
                add.setEmailError("Champ Email non conforme");
            }
        }

        // Permet de vérifier que les champs sont correct et que ils sont bien tous remplis
        boolean bNPA = true;
        boolean bCity = true;
        boolean bChange = true;
        boolean bCountry = true;
        boolean bAddAddress = true;
        Adresse adresse = new Adresse();
        boolean bEmptyAddress = address.isEmpty() || adresse.equals("adresse");
        boolean bEmptyNPA = npa.isEmpty() || npa.equals("npa");
        boolean bEmptyCity = city.isEmpty() || city.equals("ville");
        boolean bEmptyCountry = country.isEmpty();
        if(!bEmptyAddress && !bEmptyNPA && !bEmptyCity && !bEmptyCountry) {
            // Permet de checker le npa
            bNPA = Validate.isNumeric(npa);
            if (!bNPA) {
                add.setNPAError("Le champ NPA ne contient pas que des chiffres");
            }
            // Permet de checker la ville
            bCity = Validate.isAlphabetic(city);
            if (!bCity) {
                add.setCityError("Le champ ville non conforme");
            }
            // Permet de checker le pays
            bCountry = Validate.isAlphabetic(country);
            if (!bCountry) {
                add.setCountryError("Le champ pays non conforme");
            }

            // Permet de convertir en int le npa

            int cp = 0;
            bChange = true;
            if (bNPA) {
                try {
                    cp = Integer.parseInt(npa);
                } catch (Exception exception) {
                    bChange = false;
                    exception.printStackTrace();
                    new ErrorController("Erreur conversion NPA " + exception.toString());
                }
            }

            // Permet d'insérer l'adresse dans la db
            dbConnection();
            bAddAddress = true;
            int cityID = 0;
            Pays pays = new Pays();
            pays.setPays(country);

            Ville ville = new Ville();
            ville.setVille(city);
            ville.setCp(cp);
            ville.setPays(pays);

            adresse.setAdresse(address);
            adresse.setVille(ville);

                try {
                    querry.insAddress(adresse, ville, pays);
                } catch (ExceptionDataBase exceptionDataBase) {
                    exceptionDataBase.printStackTrace();
                    bAddAddress = false;
                } catch (SQLException sqlException) {
                    bAddAddress = false;
                    sqlException.printStackTrace();
                    new ErrorController("Erreur insertion adresse " + sqlException.toString());
                }
        }
        else{
            if(bEmptyAddress){
                add.setAddressError("Champ manquant");
            }
            if(bEmptyNPA){
                add.setNPAError("Champ manquant");
            }
            if(bEmptyCity){
                add.setCityError("Champ manquant");
            }
            if(bEmptyCountry){
                add.setCountryError("Champ manquant");
            }
        }
        // Permet de checker le numéro de télephone
        boolean bPhone = true;
        if(!phone.isEmpty()) {
            bPhone = Validate.isPhoneNumber(phone);
            if (!bPhone) {
                add.setPhoneError("Champ télephone non conforme");
            }
        }
        if (bLastName && bFirstName && bAVS && bEmail && bNPA && bChange && bCity && bCountry && bAddAddress && bPhone) {
            dbConnection();

// Expected : Personne(null, noAVS, prenom, nom, adresse, email, telephone, dateNaissance, responsable, statut, dateDebut, typeContrat)

            Personne personne = new Personne(avs, firstName, lastName, adresse, email, phone, dateNaissance,
                                    supervisor, status, dateDebut, contract);
            insertPersonne(personne);
        }
    }

    /**
     * Méthode permettant d'interragir avec la DB pour insérer une personne
     * @param personne personne à insérer dans la DB
     */
    private void insertPersonne (Personne personne){
        dbConnection();
        System.out.println(personne.getDateNaissance());
        try{
            querry.insertPersonne(personne);
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            new ErrorController("Erreur insertion personne " + exceptionsql.toString());
        }
        scControlleur.refreshStaffTab(personne);
        int n = JOptionPane.showConfirmDialog(new JPanel(), "Voulez-vous ajouter d'autres employées ?",
                "Continuer des ajout?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
        if(n == 1) {
            add.close();
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
            new ErrorController(exceptionDB.toString());
        }
    }
}
