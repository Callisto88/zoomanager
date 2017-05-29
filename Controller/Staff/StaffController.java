package Controller.Staff;

import Controller.Error.ErrorController;
import Model.*;
import View.PrintPDF;
import View.Staff.StaffMainPanel.StaffView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Andre on 15.03.2017.
 * Controlleur pour la fenêtre du alpPersonnel
 */
public class StaffController {

    private StaffView svPersonnel = null;
    private DBInteraction querry = null;

    /**
     * Constructeur du controlleur de la fenêtre du alpPersonnel
     */
    public StaffController() {

        svPersonnel = new StaffView(this);
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

    /**
     * Méthode permettant d'obtenir le listing du personnel
     * @return un ArrayList avec le personnel présent dans la base de données
     */
    public ArrayList<Personne> getPersonnel(){
        ArrayList<Personne> alpPersonnel = new ArrayList<>();
        dbConnection();
        try{
            alpPersonnel = querry.selAllEmployes();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            new ErrorController(exceptionsql.toString());
        }
        return alpPersonnel;
    }

    /**
     * Méthode permettant d'obtenir le listing du personnel
     * @return un ArrayList avec les intervenants présents dans la base de donnée
     */
    public ArrayList<Intervenant> getExternal(){
        ArrayList<Intervenant> aliExternal = null;
        dbConnection();
        try{
            aliExternal = querry.selIntervenant();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            new ErrorController(exceptionsql.toString());
        }
        return aliExternal;
    }

    /**
     * Méthode permettant d'obtenir tout les pays dans la BDD
     * @return ArrayList contenant tout les pays
     */
    private ArrayList<Pays> getCountries(){
        dbConnection();
        ArrayList<Pays> alp = null;
        try {
            alp = querry.selCountries();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            new ErrorController(exceptionDB.toString());
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            new ErrorController(sqlException.toString());
        }
        return alp;
    }

    /**
     * Méthode permettant de récupérer les responsables de l'entreprise
     * @return ArrayList contenant tout les responsables
     */
    private ArrayList<Personne> getResponsable(){
        dbConnection();
        ArrayList<Personne> alp = null;
        try {
            alp = querry.selResponsables();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            new ErrorController(sqlException.toString());
        }
        return alp;
    }


    /**
     * Méthode permettant d'obtenir le listing des différents statuts présent dans la DB
     * @return ArrayList contenant les différents statuts
     */
    private ArrayList<String> getStatus(){
        dbConnection();
        ArrayList<String> als = null;
        try{
            als = querry.getAllStatuts();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            new ErrorController("Erreur récup statut " + exceptionDB.toString());
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            new ErrorController("Erreur récup statut " + exceptionsql.toString());
        }
        return als;
    }

    /**
     * Méthode permettant d'obtenir le listing des différents contrats présent dans la DB
     * @return ArrayList contenant les différents contrats
     */
    private ArrayList<String> getContract(){
        /*dbConnection();
        ArrayList<String> als = null;
        try{
            als = querry.selAllContractType();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            new ErrorController("Erreur récup contrat " + exceptionDB.toString());
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            new ErrorController("Erreur récup contrat " + exceptionsql.toString());
        }

        return als;
        */
        ArrayList<String> als = null;
        als.add(TypeContrat.APPRENTISSAGE.toString());
        als.add(TypeContrat.CONTRAT_UNIQUE.toString());
        als.add(TypeContrat.DUREE_DETERMINEE.toString());
        als.add(TypeContrat.DUREE_INDETERMINEE.toString());
        als.add(TypeContrat.PROFESSIONNALISATION.toString());
        als.add(TypeContrat.STAGE.toString());
        als.add(TypeContrat.TEMPORAIRE.toString());
        return als;
    }

    /**
     * Méthode permettant de retourner les tâches attribuer à un employé
     * @param IDStaff ID de la personne ou l'on souhaite obtenir le détails des tâches qui lui sont attribuer
     * @return ArrayList de tâches attribuer à cette personne
     */
    public ArrayList<Evenement> getStaffTask(int IDStaff){
        dbConnection();
        ArrayList<Evenement> events = null;
        try {
            events = querry.getEvenementAssignToPersonByID(IDStaff);
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            return null;
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            new ErrorController(sqlException.toString());
            return null;
        }
            return events;

    }

    /**
     *  Méthode permettant de récupérer le nom de la personne en fonction de son ID
     * @param personneID ID de la personne ou l'on souhaite obtenir son nom
     * @return String contenant le nom et prénom de la personne
     */
    public String getNameStaff(int personneID){
        dbConnection();
        Personne personne = null;
        try {
            personne = querry.selEmployeDetails(personneID);
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            new ErrorController(exceptionDB.toString());
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            new ErrorController(sqlException.toString());
        }
        return (personne.getNom() + " " + personne.getPrenom());
    }

    /**
     * Méthode permettant de retourner les tâches attribuer à un intervenant
     * @param IDExternal ID de l'intervenant à traiter
     * @return ArrayList de tâches de l'intervenant
     */
    public ArrayList<Evenement> getExternalTask(int IDExternal){
        dbConnection();
        ArrayList<Evenement> events = null;
        try {
            events = querry.getEvenementAssignToIntervenantByID(IDExternal);
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            new ErrorController(sqlException.toString());
        }
        return events;
    }

    /**
     * Méthode permettant de supprimer un personnel
     * @param personne personne à supprimer
     */
    public boolean deleteStaff(Personne personne){
        dbConnection();
        boolean insert = true;
        try {
            querry.delPersonne(personne.getIdPersonne());
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            new ErrorController(sqlException.toString());
            insert = false;
        }
        return insert;
    }

    /**
     * Méthode permettant de supprimer un Intervenant
     * @param external intervenant à supprimer
     **************  Renvoyer un booleen?
     */
    public boolean deleteExternal(Intervenant external){
        dbConnection();
        boolean delete = true;
        try{
            querry.delIntervenant(external.getId());
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            new ErrorController(exceptionDB.toString());
            delete = false;
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            new ErrorController(sqlException.toString());
            delete = false;
        }
        return delete;
    }

    /**
     * Methode permettant de crée un PDF
     * @param jtTable Table à crée
     * @param title titre du PDF
     * @param additional Permet d'ajouter le nom d'une personne (en option)
     */
    public void print(JTable jtTable, String title, String additional){
        new PrintPDF(jtTable,title, additional);
    }

    /**
     * Méthode permettant de rajouter une ligne sur le tableau une fois l'ajout de personne OK
     * @param personne personne à rajouter
     */
    public void refreshStaffTab(Personne personne){
        svPersonnel.addStaffTab(personne);
    }

    /**
     * Méthode permettant de rajouter une ligne sur le tableau une fois l'ajout d'intervenant OK
     * @param external intervenant à rajouter
     */
    public void refreshExternalTab(Intervenant external){
        svPersonnel.addExternalTab(external);
    }

    /**
     * Méthode permettant de rafraichir la fenêtre de droite une fois l'employé modifié
     * @param personne personne qui à été modifié
     * @param line ligne de la table permettant de la mettre à jour
     */
    public void refreshStaff(Personne personne, int line){
        svPersonnel.refreshStaffView(personne, line);
    }

    /**
     * Méthode permettant de rafraichir la fenêtre de droite une fois l'intervenant modifié
     * @param external intervenant qui à été modifié
     * @param line ligne de la table permettant de la mettre à jour
     */
    public void refreshExternal(Intervenant external, int line){
        svPersonnel.refreshExternalView(external, line);
    }
    /**
     * Méthode permettant de supprimer une ligne du tableau des intervenanta
     * @param line ligne à supprimer
     */
    public void deleteExternalRow(int line){
        svPersonnel.eraseExternalRow(line);
    }

    /**
     * Méthode permettant de supprimer une ligne du tableau du personnel
     * @param line ligne à supprimer
     */
    public void deleteStaffRow(int line){
        svPersonnel.eraseStaffRow(line);
    }

    /**
     * Méthode permettant d'instancier la fenêtre d'ajout de personne
     */
    public void addStaffView() {
        AddStaffController addController = new AddStaffController(this, getStatus(), getContract(), getResponsable(), getCountries());
    }

    /**
     * Méthode permettant d'instancier la fenêtre d'ajout d'intervenant
     */
    public void addExternalView(){
        AddExternalController aecAddExternal = new AddExternalController(this, getCountries());
    }

    /**
     * Méthode permettant d'instancier la fenêtre d'assignation de tâches pour la Personne
     * @param personne personne à qui l'on souhaite assigner des tâches
     */
    public void assignStaffTaskView(Personne personne) {
        AssignStaffTaskController assignController = new AssignStaffTaskController(personne);
    }

    /**
     * Méthode permettant d'instancier la fenêtre d'assignation de tâches pour l'Intervenant
     * @param external personne externe à qui attribuer des tâches
     */
    public void assignExternalTaskView(Intervenant external) {
        AssignExternalTaskController aetcAssignExternal = new AssignExternalTaskController(external);
    }

    /**
     *  Méthode pour instancier la fenêtre de modification d'une personne
     * @param personne personne que l'on souhaite modifier
     * @param line ligne de la table permettant de la mettre à jour
     */
    public void modifyStaffView(Personne personne, int line) {
        ModifyStaffController modifyStaffController = new ModifyStaffController(this, personne,getContract(), getStatus(), getCountries(), getResponsable(), line);
    }

    /**
     * Méthode permettant de lancer la fenêtre de modification de l'intervenant externe
     * @param external intervenant à modifier
     * @param line permet d'obtenir la ligne du tableau pour la mise à jour
     */
    public void modifyExternalView(Intervenant external, int line){
        ModifyExternalController mecModifyExternal = new ModifyExternalController(this, external, line);
    }

}
