package Controller.Staff;

import View.Staff.StaffMainPanel.StaffTask;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andre on 17.03.2017.
 * Controlleur de la fenêtre d'assignation de tâche du personnel
 */
public class AssignStaffTaskController {
    private StaffTask task = null;

    /**
     * Constructeur du controlleur de la fenêtre d'assignation de tâches
     */
    public AssignStaffTaskController() {
        JFrame panel = new JFrame("Assignation de tâches");
        panel.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        task = new StaffTask();
        panel.getContentPane().add(task);
    }

    /**
     * Méthode permettant de réafficher la fenêtre
     */
    public void revalidateView() {

    }
}
