package Controller.Stock;

import Controller.Validate.Validate;
import Model.*;
import View.Stock.StockTab;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * Cette classes est le contrôleur de l'interface principale du Stock.
 *
 * Elle vérifie que les valeurs rentrées par l'utilisateur sont corrects
 *
 * Il fait aussi le lien entre l'interface et le modèle:
 *   - méthodes pour demander au modèle de faire les requêtes dans la base de données
 *
 * @author M.Silva
 *
 * @version 1.0
 *
 * @date    17.04.2017
 *
 */

public class StockTabController {

    private DBInteraction dbiQuery;

    private StockTab stStockTab;


    public StockTabController(){
        stStockTab = new StockTab(this);
    }

    public ArrayList<Stock> getAllStock(){
        dbiQuery = null;
        ArrayList<Stock> data = new ArrayList<>();

        try {
            dbiQuery = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            data = dbiQuery.selAllStock ();

        } catch (ExceptionDataBase e) {
            System.out.println(e.getMsg());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return data;
    }

    public ArrayList<Commande> getAllCommandeHistory(){
        dbiQuery = null;
        ArrayList<Commande> data = new ArrayList<>();

        try {
            dbiQuery = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            data = dbiQuery.selAllCommande();

        } catch (ExceptionDataBase e) {
            System.out.println(e.getMsg());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return data;
    }


    public ArrayList<Commande> getOrderByStatusAndDate(Statut sStatut, Date sStartDate, Date sEndDate){

        dbiQuery = null;
        ArrayList<Commande> data = new ArrayList<>();

        try {
            dbiQuery = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            data = dbiQuery.selOrdersByStateAndDate(sStatut, sStartDate, sEndDate);

        } catch (ExceptionDataBase e) {
            System.out.println(e.getMsg());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return data;
    }

    public ArrayList<Commande> getOrderByDate(Date sStartDate, Date sEndDate){
        dbiQuery = null;
        ArrayList<Commande> data = new ArrayList<>();

        try {
            dbiQuery = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            data = dbiQuery.selAllCommandeParDate(sStartDate, sEndDate);

        } catch (ExceptionDataBase e) {
            System.out.println(e.getMsg());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return data;
    }

    public ArrayList<Commande> getOrderByStatus(Statut sStatut){
        dbiQuery = null;
        ArrayList<Commande> data = new ArrayList<>();

        try {
            dbiQuery = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            data = dbiQuery.selOrdersByStatut(sStatut);
        } catch (ExceptionDataBase e) {
            System.out.println(e.getMsg());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return data;
    }

    public boolean isNumericPositiveDouble(Object object){
        return Validate.isNumericPositiveDouble(object);
    }

    public boolean isNumericAndBelowZero(Object object){
        return Validate.isNumericAndBelowZero(object);
    }
}
