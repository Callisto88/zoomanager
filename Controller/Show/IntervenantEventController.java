package Controller.Show;

import Model.*;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * Cette classe contient le controlleur qui  gère interaction entre les évènements
 * et les différents personnes externes au zoo puis reccupère les données au model pour les vues
 *
 * @author doriane kaffo
 *
 * @version 1.0
 *
 * @date    10/05/2017.(Création)
 * @date    29.05.2017 (Finalisation v1.0)
 *
 */
public class IntervenantEventController {
    DBInteraction query;
    /**
     * Permet d'instancier le controleur IntervenantEventController
     */
    public IntervenantEventController(){
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("BASE DE DONNEES IMPOSSIBLE A CONTACTER");
        }
    }
    /**
     * Permet d'obtenir la liste des intervenants assignés a un évènement
     *
     * @param id int
     *
     * @return ArrayList<Intervenant>
     */
    public ArrayList<Intervenant> selAllByEventId(int id) {
        if(query==null)return new ArrayList<Intervenant>();
        ArrayList<Intervenant> lstPer = new ArrayList<Intervenant>();
        try {
            lstPer = query.selAllIntervenantsParEvenementId(id);
        } catch (SQLException e) {
            System.out.println("Echec de reccuperation des intervenants");
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("Echec de reccuperation des intervenants");
        } finally {
            if(lstPer == null){
                lstPer = new ArrayList<Intervenant>();
            }
        }
        return lstPer;
    }
    /**
     * Permet d'obtenir la liste des intervenants
     *
     *
     * @return ArrayList<Intervenant>
     */
    public ArrayList<Intervenant> selAll() {
        if(query==null){
            System.out.println("Impossible de contacter la bd");
            return new ArrayList<Intervenant>();
        }
        ArrayList<Intervenant> lstPer = new ArrayList<Intervenant>();
        try {
            lstPer = query.selIntervenant();
        } catch (SQLException e) {
            System.out.println("Aucun intervenant trouve pour cet evenement");
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("Aucun intervenant trouve pour cet evenement");
        }finally {
            if(lstPer == null){
                lstPer = new ArrayList<Intervenant>();
            }
        }
        return lstPer;
    }
    /**
     * Permet d'assigner un intervenant a un évènement
     *
     * @param I Intervenant
     * @param E Evenement
     *
     * @return boolean
     */
    public boolean add(Intervenant I, Evenement E) {
        if(query==null)return false;
        try {
            boolean add_elt = true;
            try {
                ArrayList<Intervenant> inter =  query.selAllIntervenantsParEvenementId(E.getId());
                if(inter.size()>0){
                    for (Intervenant i : inter){
                        if(i.getId()==I.getId()){
                            add_elt = false;
                            break;
                        }
                    }
                }
            } catch (ExceptionDataBase exceptionDataBase) {
                System.out.println("Aucun intervenant trouve");
            }
            if(add_elt)
                query.assignEvenementIntervenant(E,I);
            System.out.println("ENREGISTREMENT REUSSIT");
        } catch (SQLException e) {
            System.out.println("ECHEC D ENREGISTREMENT");
        }
        return  false;

    }
    /**
     * Permet de déassigner un intervenant a un évènement
     *
     * @param idP int
     * @param idE int
     *
     * @return boolean
     */
    public boolean del(int idP, int idE) {
        if(query==null)return false;
        try {
            query.delIntervenantEvenement(idP,idE);
            System.out.println("On SUPPRIME L INTERVENANT ------ " + idP+"-"+idE);
            return  true;
        } catch (SQLException e) {
            System.out.println("Echec de suppression d une nouvelle Intervenant a l evenement");
        }
        return false;
    }
    /**
     * Permet d'assigner un intervenant a un évènement
     *
     * @param a Intervenant
     * @param e Evenement
     *
     */
    public void saveByEventId(Intervenant a, Evenement e) {
        if(query==null)return ;
        if(a!=null) {
            add(a,e);
            System.out.println("On AJOUTE L INTERVENANT " + a.getNom());
        }else{
            System.out.println("On AJOUTE L INTERVENANT MAIS CETTE PERSONNE EST NULL");
        }
    }
    /**
     * Permet de déassigner un intervenant a un évènement
     *
     * @param a Intervenant
     * @param id_event int
     *
     */
    public void delByEventId(Intervenant a, int id_event) {
        if(query==null)return ;

        if(a!=null) {
            del(a.getId(),id_event);
            System.out.println("On SUPPRIME L INTERVENANT " + a.getNom()+" - "+a.getId()+"-"+id_event);
        }else{
            System.out.println("On SUPPRIME L INTERVENANT MAIS CETTE PERSONNE EST NULL");
        }
    }
}
