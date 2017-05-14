package Controller.Show;

import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Intervenant;
import Model.Personne;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by doriane kaffo  on 10/05/2017.
 */
public class IntervenantEventController {
    DBInteraction query;
    public IntervenantEventController(){
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }
    public ArrayList<Intervenant> selAllByEventId(int id) {
        ArrayList<Intervenant> lstPer = new ArrayList<Intervenant>();
//        try {
//            lstPer = query.selIntervenantByEventID(id);
//        } catch (SQLException e) {
//            System.out.println("Aucune Intervenant trouvee pour cet evenement");
//        } catch (ExceptionDataBase exceptionDataBase) {
//            System.out.println("Aucune Intervenant trouvee pour cet evenement");
//        } finally {
//            if(lstPer == null){
//                lstPer = new ArrayList<Intervenant>();
//            }
//        }
        return lstPer;
    }
    public ArrayList<Intervenant> selAll() {
        ArrayList<Intervenant> lstPer = new ArrayList<Intervenant>();
        try {
            lstPer = query.selIntervenant();
        } catch (SQLException e) {
            System.out.println("Aucun animal trouve pour cet evenement");
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("Aucun animal trouve pour cet evenement");
        }finally {
            if(lstPer == null){
                lstPer = new ArrayList<Intervenant>();
            }
        }
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
//        try {
//            query.delIntervenantEvenement(idP, idE);
//            return  true;
//        } catch (SQLException e) {
//            System.out.println("Echec de suppression d une nouvelle Intervenant a l evenement");
//        }
        return false;
    }
}
