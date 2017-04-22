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
     * Méthode permettant de construire la chaîne de caractère contenant les éventuelles erreurs
     *
     * @param label String contenant le label et une description du problème
     * @param input String contenant ce que l'utilisateur à tapé
     */
    private void addError(String label, String input) {
        error.append(label);
        error.append("\"" + input + "\"" + "\n");
    }

    /**
     * Méthode permettant de savoir si il y a eu une erreur lors du contrôle d'erreur
     *
     * @return true si des erreur sont présentes, false sinon.
     */
    public boolean getErrorPanel() {
        return errorPanel;
    }

    /**
     * Méthode permettant de réinitialisé les état d'erreur
     */
    public void resetError() {
        errorPanel = false;
        errorParsing = false;
    }

    /**
     * Méthode permettant d'obtenir les éventuelles erreurs
     *
     * @return String contenant les différentes erreurs
     */
    public String getError() {
        return error.toString();
    }

    /**
     * Méthode pour "vider" le buffer d'erreur
     */
    public void clearError() {
        error.setLength(0);
    }
}
