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

}
