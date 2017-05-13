package Controller.Show;

import Model.DBInteraction;
import Model.Evenement;
import Model.ExceptionDataBase;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by doriane kaffo  on 09/05/2017.
 */
public class EventController {
    DBInteraction query;
    public EventController(){
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }
    public boolean save(Evenement evt){
        if(evt==null){
            System.out.println("On ne peut ajouter   d evenement vide");
        }
        try {
            query.insertEvenement(evt);
            System.out.println("Insertion d un nouvel  evenement reussit "+evt);
            return true;
        } catch (SQLException e1) {
            System.out.println("Insertion d evenement "+evt+" a echoue");
        }
        return false;
    }
    public ArrayList<Evenement> selAllByEventType(String typeLast) {
        ArrayList<Evenement> list = null;
        try {
            list = query.selEventsByEventType(typeLast);
        } catch (SQLException e) {
            System.out.print("Pas d evenements pour ce type");
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.print("Pas d evenements pour ce type");
        } finally {
            if (list == null){
                System.out.print("La liste d evenement de ce type est vide");
                list = new ArrayList<Evenement>();
            }
        }
        return list;
    }

    public boolean delById(int id) {
        try {
            query.delEventByID(id);
            return true;
        } catch (SQLException e) {
            System.out.print("Pas d evenements pour ce type");
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.print("Pas d evenements pour ce type");
        }
        return false;
    }
}
