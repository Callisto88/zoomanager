package Controller.Stock;

import Controller.Error.ErrorController;
import Model.*;
import View.MyModelTable;
import View.Stock.NewOrder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * Cette classe est le contrôleur de l'interface qui permet d'ajouter une nouvelle commande
 * dans la liste des commandes.
 *
 * Attention, un point important à relever est que le contrôle des données pour creér une
 * nouvelle commande se fait dans le StockTabController.
 *
 * Il instancie l'objet NewOrder et il fait le lien entre l'interface d'une nouvelle commande et
 * le modèle qui fait les requêtes dans la base de données.
 *
 * @author M.Silva
 *
 * @version 1.0
 *
 * @date    05.05.2017
 *
 */
public class NewOrderController {
    private DBInteraction dbiQuery;
    private NewOrder noNewOrder;
    private Vector<Vector<Object>> vOrder ;
    private MyModelTable mmtOrderHistory;

    //Numéros des colonnes pour les recherches dans la table
    final int COLUMN_IDREF = 0;
    final int COLUMN_QUANTITY = 2;

    /**
     * Constructeur
     * @param vOrder est un Vector<Vector<Object>> mais qui en réalité contient des objets de type Stock
     *             Cela représente tous les aliments du stock et ses attributs (nom, quantité, unité,...)
     * @param mmtOrderHistory est le MyModelTable de la JTable qui affiche les différentes commandes sur
     *                        la page principale du stock. Elle est requise ici afin de rafraîchir
     *                        l'affichage de la JTable.
     */
    public NewOrderController(Vector<Vector<Object>> vOrder, MyModelTable mmtOrderHistory){
        this.vOrder = vOrder;
        this.mmtOrderHistory = mmtOrderHistory;
        noNewOrder = new NewOrder(this, this.vOrder);
    }

    /**
     * Méthode permettant d'appeler une méthode du modèle afin de créer une nouvelle commande
     * @throws Exception
     */
    public void newOrder() throws Exception {
        dbiQuery = null;
        ArrayList<Contenu_Commande> data = new ArrayList<>();

        //Connection à la base de donnée
        dbConnection();

        try {
            //Boucle qui permet de créer des objets Contenu_Commande avec les quantités rentrées
            //par l'utilisateur et l'id de l'aliment
            for(int i = 0; i < vOrder.size(); ++i){

                Contenu_Commande ccNewOrder = new Contenu_Commande((int)vOrder.elementAt(i).elementAt(COLUMN_IDREF),
                        (double)vOrder.elementAt(i).elementAt(COLUMN_QUANTITY));
                data.add(ccNewOrder);
            }

            //Création d'un nouvel ID de commande
            int orderID = dbiQuery.creerCommande(data);
            //Création d'un objet Commande avec l'ID obtenu ci-dessus
            Commande cOrder = dbiQuery.selCommande(orderID);
            //Mise à jour de l'affichage de la JTable
            mmtOrderHistory.addRow(cOrder.toVector());

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());

        } catch (Exception ex){
            throw new Exception(ex);
        }

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

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // Les getters et les setters

    /**
     * Méthode qui retourne la référence de l'objet NewOrder déclaré et instancié dans ce
     * contrôleur.
     * @return retourne une référence sur l'objet NewOrder.
     */
    public NewOrder getNewOrder(){
        return noNewOrder;
    }

    /**
     * Méthode qui permet d'affecter une référence de l'Objet NewOrder à l'attribut privé de
     * ce contrôleur.
     * @param noNewOrder
     */
    public void setNoNewOrder(NewOrder noNewOrder){
        this.noNewOrder = noNewOrder;
    }

    /**
     * Méthode qui retourne la référence de l'objet Vector<Vector<Object>> passé en
     * paramètre lors de l'instanciation de ce contrôleur.
     * @return retourne une référence sur l'objet Vector<Vector<Object>>.
     */
    public Vector<Vector<Object>> getvOrder(){
        return vOrder;
    }

    /**
     * Méthode qui permet d'affecter une référence de l'objet Vector<Vector<Object>> à l'attribut
     * privé de ce contrôleur.
     * @param vOrder
     */
    public void setvOrder(Vector<Vector<Object>> vOrder){
        this.vOrder = vOrder;
    }

    /**
     * Méthode qui retourne la référence de l'objet MyModelTable passé en
     * paramètre lors de l'instanciation de ce contrôleur.
     * @return retourne une référence sur l'objet MyModelTable.
     */
    public MyModelTable getMmtOrderHistory(){
        return mmtOrderHistory;
    }

    /**
     * Méthode qui permet d'affecter une référence de l'objet MyModelTable à l'attribut
     * privé de ce contrôleur.
     * @param mmtOrderHistory
     */
    public void setMmtOrderHistory(MyModelTable mmtOrderHistory){
        this.mmtOrderHistory = mmtOrderHistory;
    }

    /**
     * Méthode qui retourne de le numéro de colonne ID de la JTable.
     * @return un int
     */
    public int getCOLUMN_IDREF(){
        return COLUMN_IDREF;
    }

    /**
     * Méthode qui retourne de le numéro de colonne quantité de la JTable
     * @return un int
     */
    public int getCOLUMN_QUANTITY(){
        return COLUMN_QUANTITY;
    }
}
