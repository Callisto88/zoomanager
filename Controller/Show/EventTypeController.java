package Controller.Show;

import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.TypeEvenement;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by doriane kaffo on 09/05/2017.
 */
public class EventTypeController {
    DBInteraction query;
    public EventTypeController(){
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }
    public  boolean del(String type){
        try {
            query.delEventType(type);
            return true;
        } catch (SQLException e) {
            return false;
        } catch (ExceptionDataBase exceptionDataBase) {
            return false;
        }
    }
    public boolean save(String evtType){
        if(evtType.equalsIgnoreCase("")){
            System.out.println("On me peut ajouter un type d evenement vide");
        }


        try {
            query.insEventType(evtType);
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            //exceptionDataBase.printStackTrace();
        }
//        try {
//            System.out.println("Insertion d un nouveau type d evenement reussit "+evtType);
//            return true;
//        } catch (SQLException e) {
//            System.out.println("Insertion du type d evenement "+evtType+" a echoue");
//            e.printStackTrace();
//        }
        return false;
    }

    public ArrayList<String> selAll() {
        ArrayList<String> listType = null;
        try {
            listType = query.selEventTypes();
        } catch (SQLException e) {
            System.out.println("La liste des type  d evenement n a pas pu etre reccuperee pb sql");
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("La liste des type  d evenement n a pas pu etre reccuperee pb db");
        }finally {
            if(listType==null){
                System.out.println("La liste des type  d evenement est vide");
                listType = new ArrayList<String>();
            }
        }
        return listType;
    }
}
