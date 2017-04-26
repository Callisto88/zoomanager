package Controller.Staff;

import Model.DBInteraction;
import Model.Evenement;
import Model.ExceptionDataBase;
import Model.Intervenant;
import View.Staff.AssignTaskPanel.TaskExternalPanel;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by André on 23.04.2017.
 * Class permettant d'instancier et de controller tout ce qui concerne l'assignation de tâche à un intervenant
 */
public class AssignExternalTaskController {
    private TaskExternalPanel tepExternal = null;

    /**
     * Constructeur du controlleur
     * @param external
     */
    public AssignExternalTaskController(Intervenant external){
        ArrayList<Evenement> tasks = null;

        // Permet de récupérer les taches non assignées des intervenant
        DBInteraction querry = null;
        try {
            querry = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

/**************** Problème méthodes non présente pour récupérer les taches non assignées des intervenant ***************/
        /*
        try{
            tasks = querry.getExternalUnassignedTaskEmployee();
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
        tepExternal = new TaskExternalPanel(this, external, tasks);
    }

    /**
     * Méthode permettant d'attribuer une tâche à un intervenant
     * @param external intervenant à qui l'on souhaite attribuer une tâche
     * @param event tâche à attribuer
     */
    public void assignTask(Intervenant external, Evenement event){

    }

}
