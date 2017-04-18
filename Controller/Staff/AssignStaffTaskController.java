package Controller.Staff;

import Model.DBInteraction;
import Model.Evenement;
import Model.ExceptionDataBase;
import Model.Personne;
import View.Staff.StaffAssignTaskPanel.TaskStaffPanel;
import View.Staff.StaffMainPanel.StaffTask;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
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
    public AssignStaffTaskController(Personne personne) {
        JFrame panel = new JFrame("Assignation de tâches");
        panel.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ArrayList<Evenement> tasks = null;

        DBInteraction querry = null;
        try {
            querry = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try{
            tasks = querry.getAllUnassignedTaskEmployee();
        }
        catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
        }
        catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
        }

        task = new TaskStaffPanel(personne, tasks);
        panel.getContentPane().add(task);
    }

    /**
     * Méthode permettant de réafficher la fenêtre
     */
    public void revalidateView() {

    }
}
