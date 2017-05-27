package Controller.Stock;

import Controller.Error.ErrorController;
import Model.*;
import View.MyModelTable;
import View.Stock.OrderContent;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * Cette classe est le contrôleur de l'interface qui permet d'afficher le contenu d'une
 * commande sélectionné sur l'interface StockTab.
 *
 * Il instancie l'objet OrderContent.
 *
 * @author M.Silva
 *
 * @version 1.0
 *
 * @date    05.05.2017
 *
 */
public class OrderContentController {
    private DBInteraction dbiQuery;
    private OrderContent ocOrderContent;
    private final int ORDER_ID;
    private final Date D_ORDER;
    private final Statut S_ORDER;
    private Vector<Object> vOrder;
    private Vector<Vector<Object>> vOrderContent = new Vector<>();
    private MyModelTable mmtOrderContent;

    //Numéros des colonnes pour les recherches dans la table
    private final int COLUMN_ID = 0;
    private final int COLUMN_DATE = 1;
    private final int COLUMN_STATUS = 2;

    /**
     * Constructeur
     * @param vOrder est un Vector<Object> mais qui en réalité contient l'objet de type Commande double-cliquer
     *               par l'utilisateur.
     * @param mmtOrderContent est le MyModelTable de la JTable qui affiche les différentes commandes sur
     *                        la page principale du stock. Elle est requise ici afin de rafraîchir
     *                        l'affichage de la JTable.
     */
    public OrderContentController(Vector<Object> vOrder, MyModelTable mmtOrderContent){
        this.ORDER_ID = (int)vOrder.elementAt(COLUMN_ID);
        this.D_ORDER = (Date)vOrder.elementAt(COLUMN_DATE);
        this.S_ORDER = (Statut)vOrder.elementAt(COLUMN_STATUS);
        this.mmtOrderContent = mmtOrderContent;

        dbiQuery = null;
        ArrayList<Contenu_Commande> alOrderContent;

        //Connection à la base de donnée
        dbConnection();

        try {
            //Requête dans la base de données de tous les aliments appartenant
            //à la commande dont l'ID vaut orderID
            alOrderContent = dbiQuery.selAllContenuCommandeParID(ORDER_ID);

            //Parcours des différents aliments de la commande. Puis, création de l'objet Stock
            //en faisant une requête à la base de donnée utilisant la référence de l'aliment
            //contenu dans l'objet Contenu_Commande.
            for (Contenu_Commande c : alOrderContent) {

                Stock sArticle = dbiQuery.selArticleByRef(c.getRefArticle());
                sArticle.setQuantite(c.getQuantite());

                //Ajout de l'objet dans un Vector<Vector<Object>>. C'est celui-ci qui sera
                //passé en paramètre plus bas afin de créer la JTable qui affichera le
                //contenu de la commande
                vOrderContent.add(sArticle.toVectorToLookOrder());
            }

            ocOrderContent = new OrderContent(this, vOrderContent);

        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
            new ErrorController(exceptionDataBase.toString());
        }catch (SQLException e) {
            new ErrorController(e.getMessage());
        }

    }

    /**
     * Méthode qui permet de mettre à jour le statut de la commande
     * @param commande l'objet Commande dont il faut changer le statut
     * @return true si le changement de statut c'est bien réaliser,
     *         sinon false.
     */
    public boolean updateStatus(Commande commande){
        boolean updateOK = true;
        dbiQuery = null;

        //Connection à la base de donnée
        dbConnection();

        try{
            dbiQuery.updateCommande(commande);
        }catch (SQLException e) {
            new ErrorController(e.getMessage());
            updateOK = false;
        }
        return updateOK;
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
     * Méthode qui retourne la référence de l'objet OrderContent déclaré et instancié dans ce
     * contrôleur.
     * @return retourne une référence sur l'objet OrderContent.
     */
    public OrderContent getOrderContent(){
        return ocOrderContent;
    }

    /**
     * Méthode qui permet d'affecter une référence de l'Objet OrderContent à l'attribut privé de
     * ce contrôleur.
     * @param ocOrderContent
     */
    public void setOrderContent(OrderContent ocOrderContent){
        this.ocOrderContent = ocOrderContent;
    }

    /**
     * Méthode qui retourne la valeur de l'ID de la commande
     * @return un int
     */
    public int getOrderID(){
        return ORDER_ID;
    }

    /**
     * Méthode qui retourne la référence sur l'objet date de la commande
     * @return une référence sur l'objet Date de la commande
     */
    public Date getDateOrder(){
        return D_ORDER;
    }

    /**
     * Méthode qui retourne le statut de la commande
     * @return une référence sur l'objet Statut de la commande
     */
    public Statut getStatusOrder(){
        return S_ORDER;
    }

    /**
     * Méthode qui retourne la référence de l'objet MyModelTable passé en
     * paramètre lors de l'instanciation de ce contrôleur.
     * @return retourne une référence sur l'objet MyModelTable.
     */
    public MyModelTable getMmtOrderContent(){
        return mmtOrderContent;
    }

    /**
     * Méthode qui permet d'affecter une référence de l'objet MyModelTable à l'attribut
     * privé de ce contrôleur.
     * @param mmtOrderContent
     */
    public void setMmtOrderContent(MyModelTable mmtOrderContent){
        this.mmtOrderContent = mmtOrderContent;
    }

    /**
     * Méthode qui retourne la référence de l'objet Vector<Object> passé en
     * paramètre lors de l'instanciation de ce contrôleur.
     * @return retourne une référence sur l'objet Vector<Object>.
     */
    public Vector<Object> getVOrder(){
        return vOrder;
    }

    /**
     * Méthode qui permet d'affecter une référence de l'objet Vector<Object> passé en paramètre
     * à l'attribut privé de ce contrôleur.
     * @param vOrder
     */
    public void setVOrder(Vector<Object> vOrder){
        this.vOrder = vOrder;
    }

    /**
     * Méthode qui retourne la référence de l'objet Vector<Vector<Object>> créé dans le
     * constructeur. Celui-ci sera mis en paramètre afin de créer l'order OrderContent.
     * Dans OrderContent, il est utiliser pour créer la JTable.
     * @return retourne une référence sur l'objet Vector<Vector<Object>>.
     */
    public Vector<Vector<Object>> getVOrderContent(){
        return vOrderContent;
    }

    /**
     * Méthode qui permet d'affecter une référence de l'objet Vector<Vector<Object>> passé en paramètre
     * à l'attribut privé de ce contrôleur.
     * @param vOrderContent
     */
    public void setVOrderContent(Vector<Vector<Object>> vOrderContent){
        this.vOrderContent = vOrderContent;
    }

    /**
     * Méthode qui retourne de le numéro de colonne ID de la JTable.
     * @return un int
     */
    public int getCOLUMN_ID(){
        return COLUMN_ID;
    }

    /**
     * Méthode qui retourne de le numéro de colonne Date de la JTable.
     * @return un int
     */
    public int getCOLUMN_DATE(){
        return COLUMN_DATE;
    }

    /**
     * Méthode qui retourne de le numéro de colonne Statut de la JTable.
     * @return un int
     */
    public int getCOLUMN_STATUS(){
        return COLUMN_STATUS;
    }

}
