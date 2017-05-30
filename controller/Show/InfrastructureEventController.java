package Controller.Show;

import Model.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by doriane kaffo  on 10/05/2017.
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
    public ArrayList<Enclos> selAllByEventId(int id) {
        ArrayList<Enclos> lstPer = new ArrayList<Enclos>();
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
    public ArrayList<Enclos> selAll() {
        ArrayList<Enclos> lstPer = new ArrayList<Enclos>();
        try {
            lstPer = query.selEnclos();
        } catch (ExceptionDataBase exceptionDataBase) {

        } catch (SQLException e) {

        } finally {
            if(lstPer == null){
                lstPer = new ArrayList<Enclos>();
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

    public void saveByEventId(Enclos a, int id_event) {
        if(a!=null) {
            add(a.getId(),id_event);
            System.out.println("On AJOUTE UN INFRASTRUCTURE " + a.getNom());
        }else{
            System.out.println("On AJOUTE UNE INFRASTRUCTURE MAIS CETTE PERSONNE EST NULL");
        }
    }

    public void delByEventId(Enclos a, int id_event) {
        if(a!=null) {
            del(a.getId(),id_event);
            System.out.println("On SUPPRIME UNE INFRASTRUCTURE " + a.getNom());
        }else{
            System.out.println("On SUPPRIME UNE INFRASTRUCTURE MAIS CETTE PERSONNE EST NULL");
        }
    }
}
