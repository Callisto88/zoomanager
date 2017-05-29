package Controller.Stock;

import Controller.Error.ErrorController;
import Controller.Validate.Validate;
import Model.*;
import View.Stock.StockTab;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

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
 * @date    15.04.2017
 *
 */

public class StockTabController {

    private DBInteraction dbiQuery;
    private StockTab stStockTab;

    /**
     * Constructeur
     */
    public StockTabController(){
        stStockTab = new StockTab(this);
    }

    /**
     * Méthode qui va chercher tous les aliments qui sont dans le stock ainsi que leur
     * différents attributs (ID, quantité, ...)
     * @return ArrayList d'objets Stock dont chacun des objets Stock représente un aliment
     */
    public ArrayList<Stock> getAllStock(){
        dbiQuery = null;
        ArrayList<Stock> data = new ArrayList<>();

        //Connection à la base de donnée
        dbConnection();

        try {
            data = dbiQuery.selAllStock ();

        } catch (ExceptionDataBase e) {
            new ErrorController(e.getMsg());
        } catch (SQLException e) {
            new ErrorController(e.getMessage());
        } catch (Exception e){
            new ErrorController(e.getMessage());
        }

        return data;
    }

    /**
     * Méthode qui va chercher toutes les commandes qui ont été faites jusqu'à maintenant.
     * @return ArrayList d'objets Commande dont chacun des objets Commande représente une commande.
     */
    public ArrayList<Commande> getAllCommandeHistory() {
        dbiQuery = null;
        ArrayList<Commande> data = new ArrayList<>();

        //Connection à la base de donnée
        dbConnection();

        try {
            data = dbiQuery.selAllCommande();

        } catch (ExceptionDataBase e) {
            new ErrorController(e.getMessage());
        } catch (SQLException e) {
            new ErrorController(e.getMessage());
        } catch (Exception e){
            new ErrorController(e.getMessage());
        }

        return data;
    }

    /**
     * Méthode qui recherche les commandes par date et par statut.
     * @param sStatut
     * @param sStartDate
     * @param sEndDate
     * @return ArrayList avec les commandes
     */
    public ArrayList<Commande> getOrderByStatusAndDate(Statut sStatut, Date sStartDate, Date sEndDate) throws Exception {
        dbiQuery = null;
        ArrayList<Commande> data = new ArrayList<>();

        dbConnection();

        try {
            data = dbiQuery.selOrdersByStateAndDate(sStatut, sStartDate, sEndDate);

        } catch (ExceptionDataBase e) {
            throw new ExceptionDataBase(e.getMessage());
        } catch (SQLException e) {
            throw new SQLException(e);
        } catch (Exception e){
            throw new Exception(e);
        }

        return data;
    }

    /**
     * Méthode qui recherche les commandes par date.
     * @param sStartDate
     * @param sEndDate
     * @return ArrayList avec les commandes
     */
    public ArrayList<Commande> getOrderByDate(Date sStartDate, Date sEndDate) throws Exception {
        dbiQuery = null;
        ArrayList<Commande> data = new ArrayList<>();

        dbConnection();

        try {
            data = dbiQuery.selAllCommandeParDate(sStartDate, sEndDate);

        } catch (ExceptionDataBase e) {
            throw new ExceptionDataBase(e.getMessage());
        } catch (SQLException e) {
            throw new SQLException(e);
        } catch (Exception e){
            throw new Exception(e);
        }

        return data;
    }

    /**
     * Méthode qui recherche les commandes par statut.
     * @param sStatut
     * @return ArrayList avec les commandes
     */
    public ArrayList<Commande> getOrderByStatus(Statut sStatut) throws Exception {
        dbiQuery = null;
        ArrayList<Commande> data = new ArrayList<>();

        dbConnection();

        try {
            data = dbiQuery.selOrdersByStatut(sStatut);
        } catch (ExceptionDataBase e) {
            throw new ExceptionDataBase(e.getMessage());
        } catch (SQLException e) {
            throw new SQLException(e);
        } catch (Exception e){
            throw new Exception(e);
        }

        return data;
    }

    /**
     * Méthode permettant d'établir la connection avec la DB
     */
    private void dbConnection(){
        try {
            dbiQuery = new DBInteraction();
        } catch (ExceptionDataBase exceptionDB) {
            exceptionDB.printStackTrace();
            new ErrorController(exceptionDB.toString());
        }
    }

    /**
     * Méthode permettant de vérifier si l'objet passé en paramètre est un nombre positif.
     * Elle fait appel à la méthode statique isNumericPositiveDouble de Validate qui cast
     * l'object en double et vérifie si s'il est plus grand que 0.
     * @param object qui contient un nombre
     * @return true si la valeur est plus grande que 0.
     */
    public boolean isNumericPositiveDouble(Object object){
        return Validate.isNumericPositiveDouble(object);
    }

    /**
     * Méthode permettant de vérifier si l'objet passé en paramètre est un nombre négatif.
     * Elle fait appel à la méthode statique isNumericAndBelowZero de Validate qui cast
     * l'object en double et vérifie si s'il est plus petit que 0.
     * @param object qui contient un nombre
     * @return true si la valeur est plus petite que 0.
     */
    public boolean isNumericAndBelowZero(Object object){
        return Validate.isNumericAndBelowZero(object);
    }

    /**
     * Méthode permettant de vérifier si l'objet passé en paramètre est une date.
     * Elle fait appel à la méthode statique isDate de Validate
     * @param date
     * @return true si la valeur est une date plausible
     */
    public boolean isDate(Date date) {
        return Validate.isDate(date);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // Les getters et les setters

    /**
     * Méthode qui retourne la référence de l'objet StockTab déclaré et instancié dans ce
     * contrôleur.
     * @return retourne une référence sur l'objet StockTab.
     */
    public StockTab getStStockTab(){
        return stStockTab;
    }

    /**
     * Méthode qui permet d'affecter une référence de l'Objet StockTab à l'attribut privé de
     * ce contrôleur.
     * @param stStockTab
     */
    public void setStStockTab(StockTab stStockTab){
        this.stStockTab = stStockTab;
    }
}
