package Controller.Staff;

import Model.Evenement;
import Model.Personne;
import View.Staff.StaffAssignTaskPanel.TaskStaffPanel;
import View.Staff.StaffMainPanel.StaffTask;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Andre on 17.03.2017.
 * Controlleur de la fenêtre d'assignation de tâche du personnel
 */
public class AssignStaffTaskController {
    private TaskStaffPanel task = null;

    /**
     * Constructeur du controlleur de la fenêtre d'assignation de tâches
     */
    public AssignStaffTaskController(Personne personne, ArrayList<Evenement> tasks) {
        JFrame panel = new JFrame("Assignation de tâches");
        panel.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        task = new TaskStaffPanel(personne, tasks);
        panel.getContentPane().add(task);
    }

    /**
     * Méthode permettant de réafficher la fenêtre
     */
    public void revalidateView() {

    }
}
