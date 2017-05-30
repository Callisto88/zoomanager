package Controller.Show;

import Model.Animal;
import Model.DBInteraction;
import Model.Evenement;
import Model.ExceptionDataBase;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * Cette classe contient le controlleur qui gère les animaux qui ont une relation avec les évènements
 *
 * @author doriane kaffo
 *
 * @version 1.0
 *
 * @date    04.05.2017.(Création)
 * @date    29.05.2017 (Finalisation v1.0)
 *
 */
public class AnimalEventController {
    DBInteraction query;
    /**
     * Permet d'instancier le controlleur AnimalEventController
     *
     */
    public AnimalEventController(){
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("BASE DE DONNEES IMPOSSIBLE A CONTACTER");
        }
    }
    /**
     * Permet d'obtenir la liste des animaux qui interviennent dans un évènement
     *
     * @param id int
     *
     * @return ArrayList<Animal>
     */
    public ArrayList<Animal> selAllByEventId(int id) {
        if(query==null)return new ArrayList<Animal>();
        ArrayList<Animal> lstAn = null;
        try {
            lstAn = query.selAnimalsByEventID(id);
        } catch (SQLException e) {
            System.out.println("LISTE D'ANIMAUX NON DISPONIBLE PB SQL");
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("PAS D ANIMAUX ENREGISTRES POUR CET EVENEMENT");
        }
        finally {
            if(lstAn == null){
                lstAn = new ArrayList<Animal>();
            }
        }
        return lstAn;
    }

    /**
     * Permet d'obtenir la liste de tous les animaux
     *
     *
     * @return ArrayList<Animal>
     */
    public ArrayList<Animal> selAll() {
        if(query==null)return new ArrayList<Animal>();
        ArrayList<Animal> lstAn = null;
        try {
            lstAn = query.selAnimaux();
        } catch (SQLException e) {
            System.out.println("LISTE D'ANIMAUX NON DISPONIBLE PB SQL");
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("PAS D ANIMAUX ENREGISTRES POUR CET EVENEMENT");
        }finally {
            if(lstAn == null){
                lstAn = new ArrayList<Animal>();
            }
        }
        return lstAn;
    }

    /**
     * Permet d'ajouter un animal à un évènement
     *
     *
     * @param A Animal
     * @param E Evenement
     *
     * @return boolean
     */
    public boolean add(Animal A, Evenement E) {
        if(query==null)return false;
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
        }

        return false;
    }
    /**
     * Permet de supprimer un animal d'un évènement
     *
     *
     * @param idA int
     * @param idE int
     *
     * @return boolean
     */
    public boolean del(int idA, int idE) {
        if(query==null)return false;
        try {
            query.delAnimalEvent(idA, idE);
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return false;
    }
    /**
     * Permet de trouver l'animal qui porte un nom precis
     *
     *
     * @param nom String
     *
     * @return Animal
     */
    public Animal selByNom(String nom) {
        if(query==null)return new Animal();
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
        if(query==null)return ;
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
        if(query==null)return ;
        if(a!=null) {
            del(a.getId(),id_event);
            System.out.println("On SUPPRIME L ANNIMAL " + a.getNom()+" "+a.getId()+" "+" EVENEMENT "+id_event);
        }else{
            System.out.println("On SUPPRIME L ANNIMAL MAIS CETTE PERSONNE EST NULL EVENEMENT "+id_event);
        }
    }
}
