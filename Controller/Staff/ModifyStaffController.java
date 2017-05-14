package Controller.Staff;

import Controller.Error.ErrorController;
import Controller.Validate.Validate;
import Model.*;
import View.Staff.ModifyPanel.ModifyStaffPanel;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Andre on 17.03.2017.
 * Controlleur de la fenêtre de modification d'employée
 */
public class ModifyStaffController {
    private ModifyStaffPanel mspModifyStaff = null;
    private Personne personne = null;
    private DBInteraction querry = null;
// TODO : LAISSER UNE CASE VIDE POUR LE PAYS!!!!
    /**
     * Constructeur du controlleur
     * @param personne personne à modifier
     * @param contract liste des contract actuels
     * @param status liste des statut actuels
     * @param countries liste de pays présent dans la DB
     * @param supervisor liste des superviseur présent dans la db
     */
    public ModifyStaffController(Personne personne, ArrayList<String> contract, ArrayList<String> status,
                                 ArrayList<Pays> countries, ArrayList<Personne> supervisor) {
        this.personne = personne;
        mspModifyStaff = new ModifyStaffPanel(this ,personne , contract, status, countries, supervisor);

    }

    /**
     * Méthode permettant de vérifier les champs de la personne
     * @param sFirstName String contenant le prénom
     * @param sLastName String contenant le nom
     * @param iSupervisor ID du responsable
     * @param sEMail String contenant l'E-Mail
     * @param sAddress String contenant l'adresse
     * @param sNPA String contenant le NPA
     * @param sCity String contenant la ville
     * @param sCountry String ontenant le pays
     * @param sPhone String contenant le télephone
     * @param sContract String contenant le contrat
     * @param sStatut String contenant le statut
     */
    public void checkModifyStaff(String sFirstName, String sLastName, int iSupervisor, String sEMail, String sAddress,
                                 String sNPA, String sCity, String sCountry, String sPhone,
                                 String sContract, String sStatut) {

        // Permet de lancer une batterie de test sur les champs
        boolean bLastName = Validate.isAlphabetic(sLastName);
        if(!bLastName){
            mspModifyStaff.setLastNameError("Champ nom contenant des caractères innaproprié");
        }

        boolean bFirstName = Validate.isAlphabetic(sFirstName);
        if(!bFirstName){
            mspModifyStaff.setFirstNameError("Champ prénom contenant des caractères innaproprié");
        }

        boolean bEmail = true;
        if(sEMail.length() != 0) {
            bEmail = Validate.isEmail(sEMail);
            if (!bEmail) {
                mspModifyStaff.setEmailError("Champ Email non conforme");
            }
        }
        // TODO : check si un champ est null!!
        boolean bNPA = true;
        boolean bCity = true;
        boolean bChange = true;
        boolean bAddAddress = true;
        // TODO : new adresse ou null??
        Adresse adresse = new Adresse();
        boolean bEmptyAddress = sAddress.isEmpty();
        boolean bEmptyNPA = sNPA.isEmpty();
        boolean bEmptyCity = sCity.isEmpty();
        boolean bEmptyCountry = sCountry.isEmpty();
        if(!bEmptyAddress && !bEmptyNPA && !bEmptyCity && !bEmptyCountry) {
            bNPA = Validate.isNumeric(sNPA);
            if (!bNPA) {
                mspModifyStaff.setNPAError("Le champ NPA ne contient pas que des chiffres");
            }
            bCity = Validate.isAlphabetic(sCity);
            if (!bCity) {
                mspModifyStaff.setCityError("Le champ ville non conforme");
            }
            int cp = 0;
            try {
                cp = Integer.parseInt(sNPA);
            } catch (Exception exception) {
                bChange = false;
                exception.printStackTrace();
                new ErrorController(exception.toString());
            }
            dbConnection();
            try {
                Pays pays = new Pays();
                pays.setPays(sCountry);

                Ville ville = new Ville();
                ville.setCp(cp);
                ville.setVille(sCity);
                ville.setPays(pays);

                adresse = new Adresse();
                adresse.setAdresse(sAddress);
                adresse.setVille(ville);

                querry.insAddress(adresse, ville, pays);
            } catch (SQLException sqlException) {
                bAddAddress = false;
                sqlException.printStackTrace();
                new ErrorController(sqlException.toString());
            } catch (ExceptionDataBase exceptionDataBase) {
                bAddAddress = false;
                exceptionDataBase.printStackTrace();
                new ErrorController(exceptionDataBase.toString());
            }
        }
        else if(!bEmptyAddress && bEmptyNPA && bEmptyCity && bEmptyCountry){
            // TODO: comment faire si il en manque un??
        }
        else{
            if(bEmptyAddress){
                mspModifyStaff.setAddressError("Champ manquant");
            }
            if(bEmptyNPA){
                mspModifyStaff.setNPAError("Champ manquant");
            }
            if(bEmptyCity){
                mspModifyStaff.setCityError("Champ manquant");
            }
            if(bEmptyCountry){
                mspModifyStaff.setCountryError("Champ manquant");
            }
        }
        boolean bPhone = true;
        if(!sPhone.isEmpty()) {
            bPhone = Validate.isPhoneNumber(sPhone);
            if (!bPhone) {
                mspModifyStaff.setPhoneError("Le champ téléphone est incorrect");
            }
        }
        if(bFirstName && bLastName && bEmail && bCity && bNPA && bChange && bAddAddress && bPhone){
            personne.setEmail(sEMail);
            personne.setPrenom(sFirstName);
            personne.setNom(sLastName);
            personne.setAdresse(adresse);
            personne.setResponsable(iSupervisor);
            personne.setStatut(sStatut);
            personne.setTypeContrat(sContract);
            personne.setTelephone(sPhone);
            modifyStaff(personne);
        }
    }

    /**
     * Méthode permettant de modifier la personne
     * @param personne Personne modifié à réinséré
     */
    private void modifyStaff(Personne personne){
        dbConnection();
        // Permet d'insérer la personne modifié
        boolean erreur = false;
        try {
            Personne.afficherPersonne(personne);

            querry.updatePersonne(personne);
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            new ErrorController(sqlException.toString());
            erreur = true;
        }
        if(!erreur){
            mspModifyStaff.getParent().disable();
        }
    }

    /**
     * Méthode pour permettre la connection à la DB
     */
    private void dbConnection(){
        // Permet de joindre la BD
        try {
            querry = new DBInteraction();
        } catch (ExceptionDataBase exceptionDB) {
            exceptionDB.printStackTrace();
            new ErrorController(exceptionDB.toString());
        }
    }

    public String getSupervisor(int supervisorID){
        dbConnection();
        Personne supervisor = null;
        try {
            supervisor = querry.selEmployeDetails(supervisorID);
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            new ErrorController(exceptionDB.toString());
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            new ErrorController(sqlException.toString());
        }
        return (supervisor.getPrenom() + " " + supervisor.getNom());
    }

}
