package Controller.Show;

import Model.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by doriane kaffo on 10/05/2017.
 */
public class IntervenantEventController {
    DBInteraction query;
    public IntervenantEventController(){
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }
    public ArrayList<Intervenant> selAllByEventId(int id) {
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
    public ArrayList<Intervenant> selAll() {
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
    public boolean add(Intervenant I, Evenement E) {
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
                exceptionDataBase.printStackTrace();
            }
            if(add_elt)
                query.assignEvenementIntervenant(E,I);
            System.out.println("ENREGISTREMENT REUSSIT");
        } catch (SQLException e) {
            System.out.println("ECHEC D ENREGISTREMENT");
        }
        return  false;

    }
    public boolean del(int idP, int idE) {
        try {
            query.delIntervenantEvenement(idP,idE);
            System.out.println("On SUPPRIME L INTERVENANT ------ " + idP+"-"+idE);
            return  true;
        } catch (SQLException e) {
            System.out.println("Echec de suppression d une nouvelle Intervenant a l evenement");
        }
        return false;
    }

    public void saveByEventId(Intervenant a, Evenement e) {
        if(a!=null) {
            add(a,e);
            System.out.println("On AJOUTE L INTERVENANT " + a.getNom());
        }else{
            System.out.println("On AJOUTE L INTERVENANT MAIS CETTE PERSONNE EST NULL");
        }
    }

    public void delByEventId(Intervenant a, int id_event) {

        if(a!=null) {
            del(a.getId(),id_event);
            System.out.println("On SUPPRIME L INTERVENANT " + a.getNom()+" - "+a.getId()+"-"+id_event);
        }else{
            System.out.println("On SUPPRIME L INTERVENANT MAIS CETTE PERSONNE EST NULL");
        }
    }
}
