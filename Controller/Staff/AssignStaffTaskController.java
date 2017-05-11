package Controller.Staff;

import Controller.Error.ErrorController;
import Model.DBInteraction;
import Model.Evenement;
import Model.ExceptionDataBase;
import Model.Personne;
import View.Staff.AssignTaskPanel.TaskStaffPanel;

import java.sql.SQLException;
import java.sql.Timestamp;
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
            new ErrorController(exceptionDB.toString());
        }
        catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            new ErrorController(exceptionsql.toString());
        }
/*
        tasks = new ArrayList<>();
        Evenement e1 = new Evenement(1, "Nettoyage cage", new Timestamp(2002, 11, 20, 15, 47, 13, 2), "animation");
        Evenement e2 = new Evenement(1, "Nourrir Lion", new Timestamp(2012, 5, 25, 10, 57, 13, 2), "Spectacle");
        Evenement e3 = new Evenement(1, "Médicaments Singe", new Timestamp(2015, 4, 12, 16, 50, 13, 7), "Représentation");

        tasks.add(e1);
        tasks.add(e2);
        tasks.add(e3);
*/
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
