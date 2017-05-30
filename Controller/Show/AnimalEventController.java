package Controller.Show;

import Model.*;
import com.jidesoft.swing.AnimatorListener;

import java.sql.SQLException;
import java.util.ArrayList;

public class AnimalEventController {
    DBInteraction query;
    public AnimalEventController(){
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }

    public ArrayList<Animal> selAllByEventId(int id) {
        ArrayList<Animal> lstAn = null;
        System.out.println("EVENEMENT "+id);
        try {
            lstAn = query.selAnimalsByEventID(id);
        } catch (SQLException e) {
            //e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
           // exceptionDataBase.printStackTrace();
            System.out.println("PAS D ANIMAUX ENREGISTRES POUR CET EVENEMENT");
        }
        finally {
            if(lstAn == null){
                lstAn = new ArrayList<Animal>();
            }
        }
        return lstAn;
    }

    public ArrayList<Animal> selAll() {
        ArrayList<Animal> lstAn = null;
        try {
            lstAn = query.selAnimaux();
        } catch (SQLException e) {
            System.out.println("Aucun animal trouve pour cet evenement");
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("Aucun animal trouve pour cet evenement");
        }finally {
            if(lstAn == null){
                lstAn = new ArrayList<Animal>();
            }
        }
        return lstAn;
    }

    public boolean add(Animal A, Evenement E) {

        try {
            boolean add_elt = true;
            ArrayList<Animal> inter =  this.selAllByEventId(E.getId());
            if(inter.size()>0){
                for (Animal i : inter){
                    if(i.getId()==A.getId()){
                        add_elt = false;
                        break;
                    }
                }
            }

            if(add_elt)
                query.assignEvenementAnimal(E,A);
            return  true;
        } catch (SQLException e) {
            System.out.println("Echec d ajout d un nouvel annimal "+A.getNom()+" a l evenement "+E.getId());
           // e.printStackTrace();
        }

        return false;
    }
    public boolean del(int idA, int idE) {
        try {
            query.delAnimalEvent(idA, idE);
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return false;
    }

    public Animal selByNom(String nom) {
        try {
            ArrayList<Animal> animaux = query.selAnimaux();
            for (Animal a : animaux){
                if(a.getNom().equalsIgnoreCase(nom)){
                    return a;
                }
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            //exceptionDataBase.printStackTrace();
        }
        return new Animal();
    }


    public void saveByEventId(Animal a, int id_event) {
        if(a!=null) {
            try {
                ArrayList<Evenement> evt = query.selEventByID(id_event);
                add(a,evt.get(0));
            } catch (ExceptionDataBase exceptionDataBase) {
                //exceptionDataBase.printStackTrace();
            } catch (SQLException e) {
                //e.printStackTrace();
            }
            System.out.println("On ENREGISTRE L ANNIMAL " + a.getNom()+" "+a.getId()+" "+" EVENEMENT "+id_event);
        }else{
            System.out.println("On ENREGISTRE L ANNIMAL MAIS CETTE PERSONNE EST NULL  EVENEMENT "+id_event);
        }
    }

    public void delByEventId(Animal a, int id_event) {
        if(a!=null) {
            del(a.getId(),id_event);
            System.out.println("On SUPPRIME L ANNIMAL " + a.getNom()+" "+a.getId()+" "+" EVENEMENT "+id_event);
        }else{
            System.out.println("On SUPPRIME L ANNIMAL MAIS CETTE PERSONNE EST NULL EVENEMENT "+id_event);
        }
    }
}
