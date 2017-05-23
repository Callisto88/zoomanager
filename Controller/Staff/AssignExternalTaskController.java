package Controller.Staff;

import Controller.Error.ErrorController;
import Model.DBInteraction;
import Model.Evenement;
import Model.ExceptionDataBase;
import Model.Intervenant;
import View.Staff.AssignTaskPanel.TaskExternalPanel;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by André on 23.04.2017.
 * Class permettant d'instancier et de controller tout ce qui concerne l'assignation de tâche à un intervenant
 */
public class AssignExternalTaskController {

    private DBInteraction querry = null;

    /**
     * Constructeur du controlleur
     * @param external permet d'avoir l'intervenant en référence pour lui ajouter des tâches.
     */
    public AssignExternalTaskController(Intervenant external){
        ArrayList<Evenement> tasks = new ArrayList<>();

        dbConnection();

        try{
            tasks = querry.getAllUnassignedTaskIntervenant();
        }
        catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            new ErrorController(exceptionDB.toString());
        }
        catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            new ErrorController(exceptionsql.toString());
        }

        TaskExternalPanel tepExternal = new TaskExternalPanel(this, external, tasks);
    }

    /**
     * Méthode permettant d'attribuer une tâche à un intervenant
     * @param external intervenant à qui l'on souhaite attribuer une tâche
     * @param event tâche à attribuer
     */
    public void assignTask(Intervenant external, Evenement event){
        dbConnection();
        try{
            querry.assignEvenementIntervenant(event, external);
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            new ErrorController(sqlException.toString());
        }
    }

    /**
     * Méthode permettant d'établir la connection avec la DB
     */
    private void dbConnection(){
        try {
            querry = new DBInteraction();
        } catch (ExceptionDataBase exceptionDB) {
            exceptionDB.printStackTrace();
            new ErrorController("Erreur dbCo " + exceptionDB.toString());
        }
    }

}
