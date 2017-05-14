package Controller.Show;

import Model.Animal;
import Model.DBInteraction;
import Model.ExceptionDataBase;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by doriane kaffo  on 10/05/2017.
 */
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
        try {
            lstAn = query.selAnimalsByEventID(id);
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

    public boolean add(int idA, int idE) {
        try {
            query.insAnimalEvent(idA, idE);
            return  true;
        } catch (SQLException e) {
            System.out.println("Echec d ajout d un nouvel annimal a l evenement");
        }
        return false;
    }
    public boolean del(int idA, int idE) {
        try {
            query.delAnimalEvent(idA, idE);
            System.out.println("Echec de suppression "+idA);
            return  true;
        } catch (SQLException e) {
            System.out.println("Echec de suppression d un nouvel annimal a l evenement");
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
            e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
        return new Animal();
    }
}
