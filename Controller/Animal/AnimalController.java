package Controller.Animal;

import Model.*;
import View.Animal.*;

import javax.swing.*;
import java.awt.*;

import Model.DBInteraction;
import Model.ExceptionDataBase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import static Model.Tools.DateSQL.calculateAge;

/**
 * Created by Yannis on 23/04/2017.
 */
public class AnimalController {

    private AnimalTab acAnimalTab;

    public AnimalController() { this.acAnimalTab = new AnimalTab(this); }

    public ArrayList<Animal> getAllAnimal(){
        DBInteraction query = null;
        ArrayList<Animal> animauxDB = new ArrayList<>();

        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            animauxDB = query.selAnimaux();
        } catch (ExceptionDataBase e) {
            System.out.println(e.getMsg());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        query = null;

        return animauxDB;
    }

    public ArrayList<Enclos> getAllEnclos() {
        DBInteraction query = null;
        ArrayList<Enclos> enclosDB = new ArrayList<>();

        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            enclosDB = query.selEnclos();
        } catch (ExceptionDataBase e) {
            System.out.println(e.getMsg());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        query = null;

        return enclosDB;
    }

    public Vector<Vector<Object>> animauxToVector(ArrayList<Animal> animalTab, ArrayList<Enclos> enclosTab){
        Vector<Vector<Object>> vectAnimaux = new Vector<>();

        short age;
        String ageL;

        for (Animal animal : animalTab) {
            vectAnimaux.add(animal.toVector(1));
            age = calculateAge(animal.getAnneeNaissance());
            ageL = animal.getAnneeNaissance().toString() + " : " + age + " ans";
            vectAnimaux.lastElement().setElementAt(ageL ,4);
            if (animal.getEnclos() != 0) {
                for (Enclos enclos : enclosTab) {
                    if (enclos.getId() == animal.getEnclos()) {
                        vectAnimaux.lastElement().add(enclos.getNom());
                    }
                }
            } else {
                vectAnimaux.lastElement().add("");
            }
        }

        return vectAnimaux;
    }

    public void refreshTest(){

    }

}
