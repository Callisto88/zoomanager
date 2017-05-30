package Controller.Show;

import Model.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * Cette classe contient le controlleur qui  gère les évènements et reccupère les données au model pour les vues
 *
 * @author doriane kaffo
 *
 * @version 1.0
 *
 * @date    10/05/2017.(Création)
 * @date    29/05/2017 (Finalisation v1.0)
 *
 */
public class InfrastructureEventController {
    DBInteraction query;
    /**
     * Permet d'instancier le controller InfrastructureController
     *
     */
    public InfrastructureEventController(){
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("BASE DE DONNEES IMPOSSIBLE A CONTACTER");
        }
    }
    /**
     * Permet d'obtenir la listes des infrastructures(enclos) ou auront lieu un evenement
     *
     * @param id int
     *
     * @return ArrayList<Enclos>
     */
    public ArrayList<Enclos> selAllByEventId(int id) {
        ArrayList<Enclos> lstPer = new ArrayList<Enclos>();
        return lstPer;
    }
    /**
     * Permet d'obtenir la listes des infrastructures enclos
     *
     *
     * @return ArrayList<Enclos>
     */
    public ArrayList<Enclos> selAll() {
        if(query==null)return new ArrayList<Enclos>();
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

    /**
     * Permet de programmer un événement dans une infrastructure
     *
     * @param idP int
     * @param idE int
     *
     * @return boolean
     */
    public boolean add(int idP, int idE) {
        if(query==null)return false;

            return  false;

    }

    /**
     * Permet de déprogrammer un événement dans une infrastructure
     *
     * @param idP int
     * @param idE int
     *
     * @return boolean
     */
    public boolean del(int idP, int idE) {
        if(query==null)return false;

        return false;
    }
    /**
     * Permet de programmer un événement dans une infrastructure
     *
     * @param id_event int
     * @param a Enclos
     *
     * @return boolean
     */
    public void saveByEventId(Enclos a, int id_event) {
        if(query==null)return ;
        if(a!=null) {
            add(a.getId(),id_event);
            System.out.println("On AJOUTE UN INFRASTRUCTURE " + a.getNom());
        }else{
            System.out.println("On AJOUTE UNE INFRASTRUCTURE MAIS CETTE PERSONNE EST NULL");
        }
    }
    /**
     * Permet de déprogrammer un événement dans une infrastructure
     *
     * @param id_event int
     * @param a Enclos
     *
     * @return boolean
     */
    public void delByEventId(Enclos a, int id_event) {
        if(query==null)return ;
        if(a!=null) {
            del(a.getId(),id_event);
            System.out.println("On SUPPRIME UNE INFRASTRUCTURE " + a.getNom());
        }else{
            System.out.println("On SUPPRIME UNE INFRASTRUCTURE MAIS CETTE PERSONNE EST NULL");
        }
    }
}
