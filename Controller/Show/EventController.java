package Controller.Show;

import Model.DBInteraction;
import Model.Evenement;
import Model.ExceptionDataBase;

import java.sql.SQLException;
import java.util.ArrayList;


public class EventController {
    DBInteraction query;
    public EventController(){
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }
    public int save(Evenement evt){
        if(evt==null){
            System.out.println("On me peut ajouter   d evenement vide");
        }
        try {
            return query.insertEvenement(evt);
        } catch (SQLException e) {
          //  e.printStackTrace();
        }
//        try {
//            System.out.println("Insertion d un nouvel d evenement reussit "+evt);
//
//        } catch (SQLException e1) {
//            System.out.println("Insertion   d evenement "+evt+" a echoue");
//        }
        return 0;
    }
    public ArrayList<Evenement> selFakeData(){
        ArrayList<Evenement> list = null, retour = new ArrayList<Evenement>();

        try {
            list = query.selAllEvents();
        } catch (SQLException e) {
           // e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
           // exceptionDataBase.printStackTrace();
        }

        finally {
            if (list == null){
                System.out.print("La liste d evenement de ce type est vide");
                list = new ArrayList<Evenement>();
            }
        }
        ArrayList<String> types = (new EventTypeController()).selAll();
        int n= types.size();
        System.out.println("IL  A "+n+" TYPES D EVENEMENTS");
        if(n<1)
            return new ArrayList<Evenement>();
        for (Evenement v : list){
            if(v.getType().length()<2 && !v.getType().trim().equalsIgnoreCase("")){
                System.out.println(v.getType()+" "+v.getType().length());
                int i = Integer.parseInt(v.getType());
                v.setType(types.get(i%n));
                retour.add(v);
            }
        }
        return retour;
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
            System.out.print("destruction de l evenement a reussi sql "+id);
            return true;
        } catch (SQLException e) {
            System.out.print("SQL - Il faut dabord deassigner l'evenement aux intervenants, animaux, personne et infrastructures"+id);
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.print("BD - Il faut dabord deassigner l'evenement aux intervenants, animaux, personne et infrastructures"+id);
        }
        return false;
    }
}
