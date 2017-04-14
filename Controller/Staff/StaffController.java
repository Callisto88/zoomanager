package Controller.Staff;

import Controller.ManagerDashboardController;
import Model.Personne;
import View.Staff.StaffMainPanel.StaffView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andre on 15.03.2017.
 * Controlleur pour la fenêtre du personnel
 */
public class StaffController {
    JFrame mainPanel = null;

    // Controlleur des sous fenêtres de Staff
    AddStaffController addController = null;
    ModifyStaffController modifyController = null;
    AssignStaffTaskController assignController = null;

    /**
     * Constructeur du controlleur de la fenêtre du personnel
     */
    public StaffController() {
        StaffView personnel = new StaffView(this);
    }

    /**
     * Méthode permettant de réafficher la fenêtre du personnel
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
     * Méthode permettant d'instancier la fenêtre d'assignation de tâches pour le personnel
     */
    public void assignTaskView() {

    }

    /**
     * Méthode pour instancier la fenêtre de modification d'une personne
     */
    public void modifyView(Personne personne){
        modifyController = new ModifyStaffController(personne);
    }
}
