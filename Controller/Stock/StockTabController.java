package Controller.Stock;

import Model.Commande;
import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Stock;
import View.Stock.StockTab;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * Cette classe est le contrôleur de l'interface principale du Stock.
 *
 * Il vérifie que les valeurs rentrées par l'utilisateur sont corrects
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

    private StockTab stStockTab;


    public StockTabController(){
        stStockTab = new StockTab(this);
    }

    public ArrayList<Stock> getAllStock(){
        DBInteraction query = null;
        ArrayList<Stock> data = new ArrayList<>();

        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            data = query.selAllStock ();

        } catch (ExceptionDataBase e) {
            System.out.println(e.getMsg());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return data;
    }

    public ArrayList<Commande> getAllCommandeHistory(){
        DBInteraction query = null;
        ArrayList<Commande> data = new ArrayList<>();

        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            data = query.selAllCommande();

        } catch (ExceptionDataBase e) {
            System.out.println(e.getMsg());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return data;
    }

}
