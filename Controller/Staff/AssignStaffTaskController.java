package Controller.Staff;

import Controller.Error.ErrorController;
import Model.DBInteraction;
import Model.Evenement;
import Model.ExceptionDataBase;
import Model.Personne;
import View.Staff.AssignTaskPanel.TaskStaffPanel;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Andre on 17.03.2017.
 * Class permettant d'instancier et de controller tout ce qui concerne l'assignation de tâche du personnel
 */
public class AssignStaffTaskController {
    private TaskStaffPanel task = null;
    private DBInteraction querry = null;

    /**
     * Constructeur du controlleur de la fenêtre d'assignation de tâches pour le personnel
     * @param personne personne à qui l'on souhaite attribuer des tâches
     */
    public AssignStaffTaskController(Personne personne) {
        ArrayList<Evenement> tasks = null;

        dbConnection();

        try{
            tasks = querry.getAllUnassignedTaskEmployee();
        }
        catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
        }
        catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            new ErrorController(exceptionsql.toString());
        }

        task = new TaskStaffPanel(this, personne, tasks);
    }

    /**
     * Méthode permettant d'attribuer une tâche à un employé
     * @param personne employé à qui l'on souhaite attribuer une tâche
     * @param event tâche que l'on souhaite attribuer
     */
    public void assignTask(Personne personne, Evenement event){
        dbConnection();
        try {
            querry.assignEvenementEmploye(event, personne);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            new ErrorController("Erreur assignation staff tache " + sqlException.toString());
        }
    }

    /**
     * Méthode permettant d'établir la connection avec la DB
     */
    private void dbConnection() {
        try {
            querry = new DBInteraction();
        } catch (ExceptionDataBase exceptionDB) {
            exceptionDB.printStackTrace();
            new ErrorController("Erreur dbCo " + exceptionDB.toString());
        }
    }
}
