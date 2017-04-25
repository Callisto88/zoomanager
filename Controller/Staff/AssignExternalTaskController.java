package Controller.Staff;

import Model.Evenement;
import Model.Intervenant;
import View.Staff.AssignTaskPanel.TaskExternalPanel;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by André on 23.04.2017.
 */
public class AssignExternalTaskController {
    private TaskExternalPanel tepExternal = null;
    public AssignExternalTaskController(Intervenant external){
        ArrayList<Evenement> tasks = null;
/*
        DBInteraction querry = null;
        try {
            querry = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

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
        tepExternal = new TaskExternalPanel(external, tasks);
    }
}
