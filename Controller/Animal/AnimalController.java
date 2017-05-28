package Controller.Animal;

import Controller.Error.ErrorController;
import Model.*;
import View.Animal.*;

import javax.swing.*;
import java.awt.*;

import Model.DBInteraction;
import Model.ExceptionDataBase;

import java.sql.Date;
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
            new ErrorController(exceptionDataBase.getMsg());
        }

        try {
            animauxDB = query.selAnimaux();
        } catch (ExceptionDataBase e) {
            e.printStackTrace();
            new ErrorController(e.getMsg());
        } catch (SQLException e) {
            e.printStackTrace();
            new ErrorController(e.getMessage());
        }

        query = null;

        return animauxDB;
    }

    public int getAnimal(String name, Date dateNaissance){
        DBInteraction query = null;
        int animalID = 0;

        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
            new ErrorController(exceptionDataBase.getMsg());
        }

        try {
            animalID = query.selAnimal(name, dateNaissance);
        } catch (ExceptionDataBase e) {
            e.printStackTrace();
            new ErrorController(e.getMsg());
        } catch (SQLException e) {
            e.printStackTrace();
            new ErrorController(e.getMessage());
        }

        query = null;

        return animalID;
    }

    public ArrayList<Enclos> getAllEnclos() {
        DBInteraction query = null;
        ArrayList<Enclos> enclosDB = new ArrayList<>();

        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
            new ErrorController(exceptionDataBase.toString());
        }

        try {
            enclosDB = query.selEnclos();
        } catch (ExceptionDataBase e) {
            e.printStackTrace();
            new ErrorController(e.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            new ErrorController(e.toString());
        }

        query = null;

        return enclosDB;
    }


    public ArrayList<Race> getAllRaces() {
        DBInteraction query = null;
        ArrayList<Race> racesDB = new ArrayList<>();

        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
            new ErrorController(exceptionDataBase.toString());
        }

        try {
            racesDB = query.getAllRaceAnimal();
        } catch (ExceptionDataBase e) {
            e.printStackTrace();
            new ErrorController(e.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            new ErrorController(e.toString());
        }

        query = null;

        return racesDB;
    }

    public ArrayList<Pays> getAllOrigines() {
        DBInteraction query = null;
        ArrayList<Pays> originesDB = new ArrayList<>();

        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
            new ErrorController(exceptionDataBase.toString());
        }

        try {
            originesDB = query.selCountries();
        } catch (ExceptionDataBase e) {
            e.printStackTrace();
            new ErrorController(e.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            new ErrorController(e.toString());
        }

        query = null;

        return originesDB;
    }



    public Vector<Vector<Object>> animauxToVector(ArrayList<Animal> animalTab, ArrayList<Enclos> enclosTab, ArrayList<Race> racesDB){
        Vector<Vector<Object>> vectAnimaux = new Vector<>();

        short age;
        String ageL;

        for (Animal animal : animalTab) {
            vectAnimaux.add(animal.toVector(1));
            age = calculateAge(animal.getAnneeNaissance());
            ageL = animal.getAnneeNaissance().toString() + " : " + age + " ans";
            vectAnimaux.lastElement().setElementAt(ageL ,4);
            if (animal.getEnclos().getId() != 0) {
                for (Enclos enclos : enclosTab) {
                    if (enclos.getId() == animal.getEnclos().getId()) {
                        vectAnimaux.lastElement().add(enclos.getNom());
                    }
                }
            } else {
                vectAnimaux.lastElement().add("");
            }
            if (animal.getRace().getId() != 0) {
                for (Race race : racesDB) {
                    if (race.getId() == animal.getRace().getId()) {
                        vectAnimaux.lastElement().setElementAt(race.getNom(), 2);
                    }
                }
            } else {
                vectAnimaux.lastElement().setElementAt("", 2);
            }
        }

        return vectAnimaux;
    }

    public boolean delAnimal(Animal animalToDel){
        DBInteraction query = null;

        boolean success = false;

        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
            new ErrorController(exceptionDataBase.toString());
        }

        try {
            success = query.delAnimal(animalToDel);
        } catch (ExceptionDataBase e) {
            e.printStackTrace();
            new ErrorController(e.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            new ErrorController(e.toString());
        }

        query = null;

        return success;
    }

    public boolean insAnimal(Animal animalToAdd){
        DBInteraction query = null;

        boolean success = false;

        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
            new ErrorController(exceptionDataBase.toString());
        }

        try {
            //success = query.insAnimal(animalToAdd);
            query.insAnimal(animalToAdd);
        /*
        } catch (ExceptionDataBase e) {
            success = false;
            e.printStackTrace();
            System.out.println(e.getMsg());
        */
        } catch (SQLException e) {
            success = false;
            e.printStackTrace();
            new ErrorController(e.toString());
        }

        query = null;

        return success;
    }

    public boolean modAnimal(Animal animalToMod){
        DBInteraction query = null;

        boolean success = false;

        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
            new ErrorController(exceptionDataBase.toString());
        }

        try {
            //success = query.insAnimal(animalToAdd);
            query.updateAnimal(animalToMod);
            success = true;
        } catch (ExceptionDataBase e) {
            success = false;
            e.printStackTrace();
            new ErrorController(e.toString());
        } catch (SQLException e) {
            success = false;
            e.printStackTrace();
            new ErrorController(e.getMessage());
        }

        query = null;

        return success;
    }


    public ArrayList<Animal_Evenement> getTasks(){
        DBInteraction query = null;

        ArrayList<Animal_Evenement> events = new ArrayList<>();

        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
            new ErrorController(exceptionDataBase.getMessage());
        }


        try {
            events = query.selEventsHavingAnimal();
        } catch (ExceptionDataBase e) {
            e.printStackTrace();
            //System.out.println(e.getMsg());
        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }


        query = null;

        return events;
    }



}
