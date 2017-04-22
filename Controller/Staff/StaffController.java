package Controller.Staff;

import Controller.Error.ErrorController;
import Controller.ManagerDashboardController;
import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Personne;
import View.Staff.StaffMainPanel.StaffView;

import javax.swing.*;
import java.awt.*;
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
    private ModifyStaffController modifyController = null;
    private AssignStaffTaskController assignController = null;
    private ErrorController ecError = null;
    private ArrayList<Personne> alpPersonnel = null;
    private DBInteraction querry = null;

    /**
     * Constructeur du controlleur de la fenêtre du alpPersonnel
     */
    public StaffController() {
        // établis la connection
        dbConnection();

        // Permet de récupérer l'Arraylist du alpPersonnel
        getPersonnel();

        StaffView svPersonnel = new StaffView(this, alpPersonnel);
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
     */
    private void getPersonnel(){
        try{
            alpPersonnel = querry.selAllEmployes();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            ecError = new ErrorController(exceptionsql.toString());
        }
    }

    /**
     * Méthode permettant de réafficher la fenêtre du alpPersonnel
     */
    public void revalidateView() {
        mainPanel.setVisible(true);
    }

    /**
     * Méthode permettant d'instancier la fenêtre d'ajout de personne
     */
    public void addView() {
        addController = new AddStaffController();
        /*
        if (addController == null) {
            addController = new AddStaffController();
        } else {
            addController.revalidateView();
        }
        */
    }

    /**
     * Méthode permettant d'instancier la fenêtre d'assignation de tâches pour le alpPersonnel
     */
    public void assignTaskView(Personne personne) {
        assignController = new AssignStaffTaskController(personne);
    }

    /**
     * Méthode pour instancier la fenêtre de modification d'une personne
     */
    public void modifyView(Personne personne) {
        modifyController = new ModifyStaffController(personne);
    }


    /**
     * Méthode permettant de lancer une fenêtre popup
     */
    public void erreurPopup(String error) {
        ErrorController ecError = new ErrorController(error);
    }
}
