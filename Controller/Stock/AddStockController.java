package Controller.Stock;

import Controller.Error.ErrorController;
import Controller.Validate.Validate;
import Model.DBInteraction;
import Model.ExceptionDataBase;
import View.CellStatus;
import View.MyModelTable;
import View.MyRenderer;
import View.Stock.AddStock;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * Cette classe est le contrôleur de l'interface qui permet d'ajouter du stock dans la DB.
 *
 * IL vérifie que les valeurs rentrées par l'utilisateur sont correctes.
 *
 * Il instancie l'objet AddStock et il fait le lien entre l'interface d'ajout de stock et le modèle
 * qui fait les requêtes dans la base de données.
 *
 * @author M.Silva
 *
 * @version 1.0
 *
 * @date    15.04.2017
 *
 */

public class AddStockController {
    private DBInteraction dbiQuery;
    private AddStock asAddStock;
    private Vector<Vector<Object>> vAdd;
    private JTable jtTableAddStock;

    //Numéros des colonnes pour les recherches dans la table
    private final int ID_REF = 0;
    private final int COLUMN_QUANTITY = 2;

    /**
     * Constructeur
     * @param vAdd est un Vector<Vector<Object>> mais qui en réalité contient des objets de type Stock
     *             Cela représente tous les aliments du stock et ses attributs (nom, quantité, unité,...)
     * @param jtTableAddStock est une JTable de tous les aliments du stock et les quantités présentent
     */
    public AddStockController(Vector<Vector<Object>> vAdd, JTable jtTableAddStock){
        this.vAdd = vAdd;
        this.jtTableAddStock = jtTableAddStock;
        asAddStock = new AddStock(this, this.vAdd);
    }

    /**
     * Méthode permettant d'appeler une méthode du modèle afin d'ajouter des aliments dans le
     * stock.
     * @throws Exception
     */
    public void addStock() throws Exception {
        dbiQuery = null;

        //Connection à la base de donnée
        dbConnection();

        try {
            //Parcours des éléments dans vAdd créé avec les aliments et les quantités que l'utilisateur
            //a rentré. On fait appel à la méthode addQuantity(int, double) de l'objet DBInteraction du Model.
            //Le int est la référence de l'aliment et le double la quantité à ajouter
            for(int i = 0; i < vAdd.size(); ++i){
                Vector<Object> vTemp = vAdd.elementAt(i);
                if(vTemp.elementAt(COLUMN_QUANTITY) != null ){
                    dbiQuery.addQuantity((int)vTemp.elementAt(ID_REF), (double)vTemp.elementAt(COLUMN_QUANTITY));
                    jtTableAddStock.setValueAt((double)jtTableAddStock.getValueAt(i, COLUMN_QUANTITY) + (double)vTemp.elementAt(COLUMN_QUANTITY), i , COLUMN_QUANTITY);
                }
            }
        } catch (SQLException e) {
            throw new SQLException(e);

        } catch(Exception ex){
            throw new Exception(ex);
        }
    }


    /**
     * Méthode permettant de vérifier les données rentrer par l'utilisateur dans l'interface.
     * A relever qu'on vérifie uniquement si ce que l'utilisateur a rentré est un double plus
     * grand ou égal à 0.
     * @param jtTableAddStockFromGui est une JTable qui représente les aliments à ajouter dans le stock
     *                               et les quantités rentrées par l'utilisateur. A la différence de
     *                               celle passée en paramètre du constructeur, celle-ci de contient
     *                               que les aliments qui sont concernés par l'ajout.
     * @param mmtTableAddStock est le MyModelTable de la JTable jtTableAddStockFromGui
     * @return true si les données sont valides sinon false
     */
    public boolean checkData(JTable jtTableAddStockFromGui, MyModelTable mmtTableAddStock){
        MyRenderer mrRenderer = new MyRenderer(mmtTableAddStock, COLUMN_QUANTITY);
        boolean dataOK = true;
        //Boucle qui permet de parcourir les éléments de la JTable et de vérifie les données
        //rentrées par l'utilisateur
        for(int i = 0; i < vAdd.size(); ++i){
            Vector<Object> vTemp = vAdd.elementAt(i);
            double temp;
            if(vTemp.elementAt(COLUMN_QUANTITY) == null){
                temp = 0;
            }else{
                temp = (double)vTemp.elementAt(COLUMN_QUANTITY);
            }
            //isNumericPositiveDouble est une méthode statique de la classe Validate qui comme
            //son nom l'indique vérifie si le nombre est un double plus grand que zéro
            //l'ajoute dans le modèle de la table si c'est le cas.
            if(isNumericPositiveDouble(temp)){
                mmtTableAddStock.setCellStatus(CellStatus.EMPTY, i, COLUMN_QUANTITY);

                //isNumericAndBelowZero est une méthode statique de la classe Validate qui comme
                //son nom l'indique vérifie si le nombre est un double plus petit est que zéro
                //l'ajoute dans le modèle de la table si c'est le cas.
            }else if(isNumericAndBelowZero(temp)){
                jtTableAddStockFromGui.getColumnModel().getColumn(COLUMN_QUANTITY).setCellRenderer(mrRenderer);
                mmtTableAddStock.setCellStatus(CellStatus.RED, i, COLUMN_QUANTITY);
                dataOK = false;
            }

        }
        return dataOK;
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

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // Les getters et les setters

    /**
     * Méthode qui retourne la référence de l'objet AddStock déclaré et instancié dans ce
     * contrôleur.
     * @return retourne une référence sur l'objet AddStock.
     */
    public AddStock getAsAddStock(){
        return asAddStock;
    }

    /**
     * Méthode qui permet d'affecter une référence de l'Objet AddStock à l'attribut privé de
     * ce contrôleur.
     * @param asAddStock
     */
    public void setAsAddStock(AddStock asAddStock){
        this.asAddStock = asAddStock;
    }

    /**
     * Méthode qui retourne la référence de l'objet Vector<Vector<Object>> passé en
     * paramètre lors de l'instanciation de ce contrôleur.
     * @return retourne une référence sur l'objet Vector<Vector<Object>>.
     */
    public Vector<Vector<Object>> getVAdd(){
        return vAdd;
    }

    /**
     * Méthode qui permet d'affecter une référence de l'objet Vector<Vector<Object>> à l'attribut
     * privé de ce contrôleur.
     * @param vAdd
     */
    public void setVAdd(Vector<Vector<Object>> vAdd){
        this.vAdd = vAdd;
    }

    /**
     * Méthode qui retourne la référence de l'objet JTable passé en
     * paramètre lors de l'instanciation de ce contrôleur.
     * @return retourne une référence sur l'objet JTable
     */
    public JTable getJtTableAddStock(){
        return jtTableAddStock;
    }

    /**
     * Méthode qui permet d'affecter une référence de l'objet JTable à l'attribut privé de ce
     * contrôleur.
     * @param jtTableAddStock
     */
    public void setJtTableAddStock(JTable jtTableAddStock){
        this.jtTableAddStock = jtTableAddStock;
    }

    /**
     * Méthode qui retourne de le numéro de colonne ID de la JTable.
     * @return un int
     */
    public int getID_REF(){
        return ID_REF;
    }

    /**
     * Méthode qui retourne de le numéro de colonne quantité de la JTable
     * @return un int
     */
    public int getCOLUMN_QUANTITY(){
        return COLUMN_QUANTITY;
    }
}
