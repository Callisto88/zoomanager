package Controller.Show;

import Model.*;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * Cette classe contient le controlleur qui  gère interaction entre les évènements
 * et les différents personnes internes au zoo puis reccupère les données au model pour les vues
 *
 * @author doriane kaffo
 *
 * @version 1.0
 *
 * @date    10/05/2017.(Création)
 * @date    29.05.2017 (Finalisation v1.0)
 *
 */
public class PersonneEventController {
    DBInteraction query;
    public PersonneEventController(){
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("BASE DE DONNEES IMPOSSIBLE A CONTACTER");
        }
    }

    /**
     * Permet d'obtenir la liste des intervenants qui interviennent dans un évènement
     *
     * @param id int
     *
     * @return ArrayList<Personne>
     */
    public ArrayList<Personne> selAllByEventId(int id) {
        if(query==null)return new ArrayList<Personne>();
        ArrayList<Personne> lstPer = null;
        try {
            lstPer = query.selPeopleByEventID(id);
        } catch (SQLException e) {
            //e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("Auncune personne ne correspond a cet evenement "+id);
            //exceptionDataBase.printStackTrace();
        }
            finally {
            if(lstPer == null){
                lstPer = new ArrayList<Personne>();
            }
        }
        return lstPer;
    }
    /**
     * Permet d'obtenir la liste des personnes
     *
     * @return ArrayList<Personne>
     */
    public ArrayList<Personne> selAll() {
        if(query==null)return new ArrayList<Personne>();
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

    /**
     * Permet d'assigner une personne a un évènement
     *
     * @param P Personne
     * @param E Evenement
     *
     * @return boolean
     */
    public boolean add(Personne P, Evenement E) {
        if(query==null)return false;
        try {

            boolean add_elt = true;
            ArrayList<Personne> inter =  this.selAllByEventId(E.getId());
            if(inter.size()>0){
                for (Personne i : inter){
                    if(i.getIdPersonne()==P.getIdPersonne()){
                        add_elt = false;
                        break;
                    }
                }
            }

            if(add_elt)
                    query.assignEvenementEmploye(E,P);
            System.out.println("ENREGISTREMENT REUSSIT");
        } catch (SQLException e) {
            System.out.println("ECHEC D ENREGISTREMENT");
        }
        return  false;

    }
    /**
     * Permet de déassigner une personne a un évènement
     *
     * @param idP int
     * @param idE int
     *
     * @return boolean
     */
    public boolean del(int idP, int idE) {
        if(query==null)return false;
        try {
            query.delPersonneEvenement(idP, idE);
        } catch (SQLException e) {
          }
        return false;
    }

    /**
     * Permet d'assigner un intervenant a un évènement
     *
     * @param a Personne
     * @param evt Evenement
     *
     */
    public void saveByEventId(Personne a, Evenement evt) {
        if(query==null)return ;
        if(a!=null) {
            add(a,evt);
            System.out.println("On AJOUTE LA PERSONNE " + a.getNom());
        }else{
            System.out.println("On AJOUTE LA PERSONNE MAIS CETTE PERSONNE EST NULL");
        }
    }
    /**
     * Permet de déassigner un intervenant a un évènement
     *
     * @param a Personne
     * @param id_event int
     *
     */
    public void delByEventId(Personne a, int id_event) {
        if(query==null)return ;
        if(a!=null) {
            del(a.getIdPersonne(),id_event);
            System.out.println("On SUPPRIME LA PERSONNE " + a.getNom());
        }else{
            System.out.println("On SUPPRIME LA PERSONNE MAIS CETTE PERSONNE EST NULL");
        }
    }
}
