package Controller.Show;

import Model.DBInteraction;
import Model.Evenement;
import Model.ExceptionDataBase;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * Cette classe contient le controlleur qui  gère interaction entre les évènements
 * et les différents infrastructures puis reccupère les données au model pour les vues
 *
 * @author doriane kaffo
 *
 * @version 1.0
 *
 * @date    04.05.2017.(Création)
 * @date    29.05.2017 (Finalisation v1.0)
 *
 */
public class EventController {
    DBInteraction query;
    /**
     * Permet d'instancier le controlleur EventController
     *
     */
    public EventController(){
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("BASE DE DONNEES IMPOSSIBLE A CONTACTER");
        }
    }

    /**
     * Permet d'ajouter un évènement
     *
     * @param evt Evenement
     *
     * @return int
     */
    public int save(Evenement evt){
        if(query==null)return 0;
        if(evt==null){
            System.out.println("On me peut ajouter   d evenement vide");
        }
        try {
            return query.insertEvenement(evt);
        } catch (SQLException e) {
            System.out.println("On me peut ajouter   d evenement vide");
        }
        return 0;
    }
    /**
     * Permet de voir si il ya des donnees incoherantes
     *
     *
     * @return ArrayList<Evenement>
     */
    public ArrayList<Evenement> selFakeData(){
        if(query==null)return new ArrayList<Evenement>();
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
        if(query==null)return new ArrayList<Evenement>();
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
        if(query==null)return false;
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

    public ArrayList<Evenement> selAll() {
        if(query==null)return new ArrayList<Evenement>();
        ArrayList<Evenement> list = null;

        try {
            list = query.selAllEvents();
        } catch (SQLException e) {
            // e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            // exceptionDataBase.printStackTrace();
        }

        finally {
            if (list == null){
                System.out.print("La liste d evenement est vide".toUpperCase());
                list = new ArrayList<Evenement>();
            }
        }
        return list;
    }
}
