package Controller.Staff;

import Controller.Error.ErrorController;
import Model.*;
import View.Staff.StaffMainPanel.StaffView;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Andre on 15.03.2017.
 * Controlleur pour la fenêtre du alpPersonnel
 */
public class StaffController {
    JFrame mainPanel = null;

    // Controlleur des sous fenêtres de Staff
    private AddStaffController addController = null;
    private ModifyStaffController modifyStaffController = null;
    private AssignStaffTaskController assignController = null;
    private AssignExternalTaskController aetcAssignExternal = null;

    private AddExternalController aecAddExternal = null;
    private ModifyExternalController mecModifyExternal = null;
    private ErrorController ecError = null;
    private DBInteraction querry = null;

    /**
     * Constructeur du controlleur de la fenêtre du alpPersonnel
     */
    public StaffController() {
        // établis la connection
        dbConnection();

        StaffView svPersonnel = new StaffView(this, getPersonnel());
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

        ArrayList<Personne> personnel = null;
    }

    /**
     * Méthode permettant d'obtenir le listing du personnel
     * @return un ArrayList avec le personnel présent dans la base de donnée
     */
    public ArrayList<Personne> getPersonnel(){
        ArrayList<Personne> alpPersonnel = new ArrayList<>();

        try{
            alpPersonnel = querry.selAllEmployes();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            ecError = new ErrorController(exceptionsql.toString());
        }
        /******************* Permet de tester hors ligne **************************/
/*
        alpPersonnel = new ArrayList<>();
        Personne p1 = new Personne("123.1234.1234.12", "Lara", "Gut", 2, "Lara.gut@swisscom.ch",
                "00415678923", new Date(1988, 02, 28),1, "Aide",
                new Date(2011, 9, 01), "CDD");
        Personne p2 = new Personne("321.1234.1234.89", "Hector", "Newman", 4, "hector.newman@mas.ch",
                "00415678923", new Date(1988, 02, 28),1, "Aide",
                new Date(2009, 9, 01), "CDD");
        Personne p3 = new Personne("678.1234.1234.56", "Lara", "Gut", 7, "Lara.gut@msn.ch",
                "00415678923", new Date(1988, 02, 28),1, "Aide",
                new Date(2010, 4, 20), "CDD");
        alpPersonnel.add(p1);
        alpPersonnel.add(p2);
        alpPersonnel.add(p3);
        /*********************************************************************/
        return alpPersonnel;
    }

    /**
     * Méthode permettant d'obtenir le listing du personnel
     * @return un ArrayList avec le personnel présent dans la base de donnée
     */
    public ArrayList<Intervenant> getExternal(){
        ArrayList<Intervenant> aliExternal = new ArrayList<>();

        try{
            aliExternal = querry.selIntervenant();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            ecError = new ErrorController(exceptionsql.toString());
        }

        Intervenant i1 = new Intervenant("Test", "Bob", "Dylan", new Adresse(1), "Bob.dylan@test.ch", "+417845123698");
        Intervenant i2 = new Intervenant("ghfad", "gfasd", "ztwr", new Adresse(2), "zgdf.hfasd@ter.ch", "+4171236547998");
        Intervenant i3 = new Intervenant("poiurz", "ikuuzr", "errew ", new Adresse(5), "pouz.fds@HJZRT.jr", "+417126873698");
        aliExternal.add(i1);
        aliExternal.add(i2);
        aliExternal.add(i3);
        return aliExternal;
    }

    private ArrayList<Pays> getCountries(){
        dbConnection();
        ArrayList<Pays> alp = null;
        try {
            alp = querry.selCountries();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            ecError = new ErrorController(sqlException.toString());
        }
        return alp;
    }

    /**
     * Méthode permettant de récupérer les responsables de l'entreprise
     */
    private ArrayList<Personne> getResponsable(){
        dbConnection();
        ArrayList<Personne> alp = null;
        try {
            alp = querry.selResponsables();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            ecError = new ErrorController(sqlException.toString());
        }
        return alp;
    }


    /**
     * Méthode permettant d'obtenir le listing des différents statuts présent dans la DB
     */
    private ArrayList<String> getStatus(){
        dbConnection();
        ArrayList<String> als = null;
        try{
            als = querry.getAllStatuts();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            ecError = new ErrorController("Erreur récup statut " + exceptionDB.toString());
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            ecError = new ErrorController("Erreur récup statut " + exceptionsql.toString());
        }
        return als;
    }

    /**
     * Méthode permettant d'obtenir le listing des différents contrats présent dans la DB
     */
    private ArrayList<String> getContract(){
        dbConnection();
        ArrayList<String> als = null;
        try{
            als = querry.selAllContractType();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            ecError = new ErrorController("Erreur récup contrat " + exceptionDB.toString());
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            ecError = new ErrorController("Erreur récup contrat " + exceptionsql.toString());
        }
        return als;
    }

    public void getAssignedTask(){
        dbConnection();
        /*
        TODO : non présent dans les méthodes
        try {
            querry.
        }
        */

    }

    /**
     * Méthode permettant d'instancier la fenêtre d'ajout de personne
     */
    public void addStaffView() {
        addController = new AddStaffController(getStatus(), getContract(), getResponsable(), getCountries());
    }

    /**
     * Méthode permettant l'ajout d'intervenant
     */
    public void addExternalView(){
        aecAddExternal = new AddExternalController(getCountries());
    }

    /**
     * Méthode permettant d'instancier la fenêtre d'assignation de tâches pour la Personne
     * @param personne
     */
    public void assignStaffTaskView(Personne personne) {
        assignController = new AssignStaffTaskController(personne);
    }

    /**
     * Méthode permettant d'instancier la fenêtre d'assignation de tâches pour l'Intervenant
     * @param external personne externe à qui attribuer des tâches
     */
    public void assignExternalTaskView(Intervenant external) {
        aetcAssignExternal = new AssignExternalTaskController(external);
    }

    /**
     * Méthode pour instancier la fenêtre de modification d'une personne
     */
    public void modifyStaffView(Personne personne) {
        modifyStaffController = new ModifyStaffController(personne,getContract(), getStatus(), getCountries(), getResponsable());
    }

    public void modifyExternalView(Intervenant external){
        mecModifyExternal = new ModifyExternalController(external);
    }

    /**
     * Méthode permettant de lancer une fenêtre popup
     */
    public void erreurPopup(String error) {
        ErrorController ecError = new ErrorController(error);
    }

    /**
     * Méthode permettant de supprimer un personnel
     * @param personne personne à supprimer
     **************  Renvoyer un booleen?
     */
    public void deleteStaff(Personne personne){
        dbConnection();
        try {
            querry.delPersonne(personne.getIdPersonne());
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            ecError = new ErrorController(sqlException.toString());
        }
    }

    /**
     * Méthode permettant de supprimer un Intervenant
     * @param external intervenant à supprimer
     **************  Renvoyer un booleen?
     */
    public void deleteExternal(Intervenant external){
        dbConnection();

        try{
            querry.delIntervenant(external.getId());
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            ecError = new ErrorController(sqlException.toString());
        }

    }
}
