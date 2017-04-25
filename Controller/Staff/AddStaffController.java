package Controller.Staff;

import Controller.Error.ErrorController;
import Controller.Validate.Validate;
import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Personne;
import View.Staff.StaffAddPanel.AddStaff;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Andre on 17.03.2017.
 * Controlleur de la fenêtre d'ajout de personnel
 */
public class AddStaffController {
    private JFrame addPanel = null;
    private AddStaff add = null;
    // Booléen réutilise pour les TestDate dans les Strings
    private boolean errorPanel = false;
    private boolean errorParsing = false;

    private ArrayList<String> status = null;
    private ArrayList<String> contract = null;

    private ErrorController ecError = null;
    private DBInteraction querry = null;
    // tableau de String contenant les erreur
    private StringBuffer error = new StringBuffer();

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
            ecError = new ErrorController(exceptionDB.toString());
        }
    }

    /**
     * Méthode permettant d'obtenir le listing des différents statuts
     */
    private void getStatus(){
        try{
            status = querry.getAllStatuts();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            ecError = new ErrorController(exceptionsql.toString());
        }
    }

    /**
     * Méthode permettant d'obtenir le listing des différents contrats
     */
    private void getContract(){
        try{
            contract = querry.selAllContractType();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            ecError = new ErrorController(exceptionsql.toString());
        }
    }

    /**
     * Méthode permettant de réafficher la fenêtre
     */
    public void revalidateView() {
        add.setVisible(true);
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
     * @param phone Numéro de télephone de la personne
     * @param supervisor Superviseur de la personne
     * @param status Statut de la personne
     * @param contract Contrat de la personne
     */
    public void checkPersonne(String lastName, String firstName, int day, int month, int year, String avs, String email, String address,
                              String phone, String supervisor, String status, String contract){

        boolean bLastName = Validate.isAlphabetic(lastName);
        boolean bFirstName = Validate.isAlphabetic(firstName);
        boolean bBirthday = Validate.isDate(day, month, year);
        boolean bAVS = Validate.isAVS(avs);
        boolean bEmail = Validate.isEmail(email);
        //boolean bAddress = Validate.is(address);
        boolean bPhone = Validate.isPhoneNumber(phone);
        if(bLastName && bFirstName && bBirthday && bAVS && bEmail && bPhone){
            dbConnection();
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
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            ecError = new ErrorController(exceptionsql.toString());
        }
    }

    /**
     * Méthode permettant de réinitialisé les état d'erreur
     */
    public void resetError() {
        errorPanel = false;
        errorParsing = false;
    }

}
