package Controller.Staff;

import Model.Evenement;
import Model.Personne;
import View.Staff.AssignTaskPanel.TaskStaffPanel;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Andre on 17.03.2017.
 * Controlleur de la fenêtre d'assignation de tâche du personnel
 */
public class AssignStaffTaskController {
    private TaskStaffPanel task = null;

    /**
     * Constructeur du controlleur de la fenêtre d'assignation de tâches pour le personnel
     */
    public AssignStaffTaskController(Personne personne) {
        //JFrame panel = new JFrame("Assignation de tâches");
        //panel.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ArrayList<Evenement> tasks = null;
/*
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
*/
/***********************************************************/
        tasks = new ArrayList<>();
        Evenement e1 = new Evenement(1, "Nettoyage cage", new Timestamp(2002, 11, 20, 15, 47, 13, 2),3);
        Evenement e2 = new Evenement(1, "Nourrir Lion", new Timestamp(2012, 5, 25, 10, 57, 13, 2),4);
        Evenement e3 = new Evenement(1, "Médicaments Singe", new Timestamp(2015, 4, 12, 16, 50, 13, 7),1);

        tasks.add(e1);
        tasks.add(e2);
        tasks.add(e3);
/************************************************************/
        task = new TaskStaffPanel(personne, tasks);
        //panel.getContentPane().add(task);
    }

    /**
     * Méthode permettant de réafficher la fenêtre
     */
    public void revalidateView() {

    }
}
