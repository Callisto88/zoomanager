package Controller.Show;

import Model.DBInteraction;
import Model.ExceptionDataBase;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * Cette classe contient le controlleur qui  gère interaction entre les évènements
 * et les différents types d'événements puis reccupère les données au model pour les vues
 *
 * @author doriane kaffo
 *
 * @version 1.0
 *
 * @date    09/05/2017.(Création)
 * @date    29/05/2017 (Finalisation v1.0)
 *
 */
public class EventTypeController {
    DBInteraction query;
    /**
     * Permet d'instancier le controlleur EventTypeController
     **
     */
    public EventTypeController(){
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }
    /**
     * Permet de supprimer un type d'évènement
     *
     * @param type String
     *
     * @return boolean
     */
    public  boolean del(String type){
        if(query==null)return false;
        try {
            query.delEventType(type);
            return true;
        } catch (SQLException e) {
            System.out.println("Le type d'évènement "+type+" n a pas pu etre supprimé SQL_ERROR");
            return false;
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("Le type d'évènement "+type+" n a pas pu etre supprimé DB_ERROR");
            return false;
        }
    }
    /**
     * Permet d'ajouter un type d'évènement
     *
     * @param evtType String
     *
     * @return boolean
     */
    public boolean save(String evtType){
        if(query==null)return false;
        if(evtType.equalsIgnoreCase("")){
            System.out.println("On me peut ajouter un type d evenement vide");
        }


        try {
            query.insEventType(evtType);
            return true;
        } catch (SQLException e) {
            System.out.println("Le type d'évènement "+evtType+" n a pas pu etre qjouté SQL_ERROR");
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("Le type d'évènement "+evtType+" n a pas pu etre qjouté DB_ERROR");
        }
        return false;
    }
    /**
     * Permet d'obtenir la liste complete des types d'évènements
     *
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> selAll() {
        if(query==null)return new ArrayList<String>();
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
