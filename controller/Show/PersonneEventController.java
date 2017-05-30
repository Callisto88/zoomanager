package Controller.Show;

import Model.*;

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
            //e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("Auncune personne ne correspond a cet evenement "+id);
            //exceptionDataBase.printStackTrace();
        }
//        try {
//            lstPer = query.selPeopleByEventID(id);
//        } catch (SQLException e) {
//            System.out.println("Aucune personne trouvee pour cet evenement");
//        } catch (ExceptionDataBase exceptionDataBase) {
//            System.out.println("Aucune personne trouvee pour cet evenement");
//        }
            finally {
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
    public boolean add(Personne P, Evenement E) {
            /*
            Fonction pas encore implementee
             */
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

            query.assignEvenementEmploye(E,P);
            System.out.println("ENREGISTREMENT REUSSIT");
        } catch (SQLException e) {
            System.out.println("ECHEC D ENREGISTREMENT");
        }
        return  false;

    }
    public boolean del(int idP, int idE) {

        try {
            query.delPersonneEvenement(idP, idE);
        } catch (SQLException e) {
          }
        return false;
    }

    public void saveByEventId(Personne a, Evenement evt) {
        if(a!=null) {
            add(a,evt);
            System.out.println("On AJOUTE LA PERSONNE " + a.getNom());
        }else{
            System.out.println("On AJOUTE LA PERSONNE MAIS CETTE PERSONNE EST NULL");
        }
    }

    public void delByEventId(Personne a, int id_event) {
        if(a!=null) {
            del(a.getIdPersonne(),id_event);
            System.out.println("On SUPPRIME LA PERSONNE " + a.getNom());
        }else{
            System.out.println("On SUPPRIME LA PERSONNE MAIS CETTE PERSONNE EST NULL");
        }
    }
}
