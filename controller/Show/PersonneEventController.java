package Controller.Show;

import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Personne;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by doriane kaffo  on 10/05/2017.
 */
public class PersonneEventController {
    DBInteraction query;
    public PersonneEventController(){
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }
    public ArrayList<Personne> selAllByEventId(int id) {
        ArrayList<Personne> lstPer = null;
        try {
            lstPer = query.selPeopleByEventID(id);
        } catch (SQLException e) {
            System.out.println("Aucune personne trouvee pour cet evenement");
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("Aucune personne trouvee pour cet evenement");
        } finally {
            if(lstPer == null){
                lstPer = new ArrayList<Personne>();
            }
        }
        return lstPer;
    }
    public ArrayList<Personne> selAll() {
        ArrayList<Personne> lstPer = null;
        try {
            lstPer = query.selAllEmployes();
        } catch (SQLException e) {
            System.out.println("Aucun animal trouve pour cet evenement");
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("Aucun animal trouve pour cet evenement");
        }finally {
            if(lstPer == null){
                lstPer = new ArrayList<Personne>();
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
        try {
            query.delPersonneEvenement(idP, idE);
            return  true;
        } catch (SQLException e) {
            System.out.println("Echec de suppression d une nouvelle personne a l evenement");
        }
        return false;
    }
}
