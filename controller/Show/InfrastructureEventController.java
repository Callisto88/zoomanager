package Controller.Show;

import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Infrastructure;
import Model.Intervenant;

import java.util.ArrayList;

/**
 * Created by doriane kaffo on 10/05/2017.
 */
public class InfrastructureEventController {
    DBInteraction query;
    public InfrastructureEventController(){
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }
    public ArrayList<Infrastructure> selAllByEventId(int id) {
        ArrayList<Infrastructure> lstPer = new ArrayList<Infrastructure>();
        return lstPer;
    }
    public ArrayList<Infrastructure> selAll() {
        ArrayList<Infrastructure> lstPer = new ArrayList<Infrastructure>();
        return lstPer;
    }
    public boolean add(int idP, int idE) {
            /*
            Fonction pas encore implementee
             */
           // query.inspevent(idP, idE);
            return  false;

    }
    public boolean del(int idP, int idE) {
        return false;
    }

    public void saveByEventId(Infrastructure a, int id_event) {
        if(a!=null) {
            add(a.getId(),id_event);
            System.out.println("On AJOUTE UN INFRASTRUCTURE " + a.getNom());
        }else{
            System.out.println("On AJOUTE UNE INFRASTRUCTURE MAIS CETTE PERSONNE EST NULL");
        }
    }

    public void delByEventId(Infrastructure a, int id_event) {
        if(a!=null) {
            del(a.getId(),id_event);
            System.out.println("On SUPPRIME UNE INFRASTRUCTURE " + a.getNom());
        }else{
            System.out.println("On SUPPRIME UNE INFRASTRUCTURE MAIS CETTE PERSONNE EST NULL");
        }
    }
}
