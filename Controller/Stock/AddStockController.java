package Controller.Stock;

import Controller.Validate.Validate;
import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Stock;
import View.CellStatus;
import View.MyModelTable;
import View.MyRenderer;
import View.Stock.AddStock;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * Cette classe est le contrôleur de l'interface qui permet d'ajouter du stock dans la DB.
 *
 * IL vérifie que les valeurs rentrées par l'utilisateur sont correctes.
 *
 * Il instancie AddStock() et il fait le lien entre l'interface d'ajout de stock et le modèle
 * qui fait les requêtes dans la base de données.
 *
 * @author M.Silva
 *
 * @version 1.0
 *
 * @date    17.04.2017
 *
 */

public class AddStockController {
    private DBInteraction dbiQuery;
    private AddStock asAddStock;
    private Vector<Vector<Object>> vAdd;
    private JTable jtTableAddStock;
    private JTable jtTableAddStockFromGui;
    private MyModelTable mmtTableAddStock;
    private final int ID_REF = 0;
    private final int COLUMN_QUANTITY = 2;

    /**
     * Constructeur
     * @param vAdd
     * @param jtTableAddStock
     */
    public AddStockController(Vector<Vector<Object>> vAdd, JTable jtTableAddStock){
        this.vAdd = vAdd;
        this.jtTableAddStock = jtTableAddStock;
        asAddStock = new AddStock(this, this.vAdd);
    }

    /**
     * Méthode permettant d'obtenir la valeur contenu dans une cellule spécifique du tableau
     * @param rowIndex entier représentant le numéro de la ligne
     * @param columnIndex entier représentant le numéro de la colonne
     * @return Object contenu dans la cellule
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        return vAdd.elementAt(rowIndex).elementAt(columnIndex);
    }

    public void addStock() throws Exception {
        dbiQuery = null;

        try {
            dbiQuery = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {

            for(int i = 0; i < vAdd.size(); ++i){
                Vector<Object> vTemp = vAdd.elementAt(i);
                dbiQuery.addQuantity((int)vTemp.elementAt(ID_REF), (double)vTemp.elementAt(COLUMN_QUANTITY));
                jtTableAddStock.setValueAt((double)jtTableAddStock.getValueAt(i, COLUMN_QUANTITY) + (double)vTemp.elementAt(COLUMN_QUANTITY), i , COLUMN_QUANTITY);
            }
        } catch (SQLException e) {
            throw new Exception();
        }
    }

    public boolean checkData(JTable jtTableAddStockFromGui, MyModelTable mmtTableAddStock){
        this.jtTableAddStockFromGui = jtTableAddStockFromGui;
        this.mmtTableAddStock = mmtTableAddStock;
        MyRenderer mrRenderer = new MyRenderer(mmtTableAddStock, COLUMN_QUANTITY);
        boolean dataOK = true;
        for(int i = 0; i < vAdd.size(); ++i){
            Vector<Object> vTemp = vAdd.elementAt(i);
            double temp;
            if(vTemp.elementAt(COLUMN_QUANTITY) == null){
                temp = 0;
            }else{
                temp = (double)vTemp.elementAt(COLUMN_QUANTITY);
            }

            if(isNumericPositiveDouble(temp)){
                mmtTableAddStock.setCellStatus(CellStatus.EMPTY, i, COLUMN_QUANTITY);

            }else if(isNumericAndBelowZero(temp)){
                jtTableAddStockFromGui.getColumnModel().getColumn(COLUMN_QUANTITY).setCellRenderer(mrRenderer);
                mmtTableAddStock.setCellStatus(CellStatus.RED, i, COLUMN_QUANTITY);
                dataOK = false;
            }

        }
        return dataOK;
    }

    public boolean isNumericPositiveDouble(Object object){
        return Validate.isNumericPositiveDouble(object);
    }

    public boolean isNumericAndBelowZero(Object object){
        return Validate.isNumericAndBelowZero(object);
    }

    public DBInteraction getDBInteraction(){
        return dbiQuery;
    }

    public void setDBIteraction(DBInteraction dbiQuery){
        this.dbiQuery = dbiQuery;
    }

    public AddStock getAsAddStock(){
        return asAddStock;
    }

    public void setAsAddStock(AddStock asAddStock){
        this.asAddStock = asAddStock;
    }

    public Vector<Vector<Object>> getVAdd(){
        return vAdd;
    }

    public void setVAdd(Vector<Vector<Object>> vAdd){
        this.vAdd = vAdd;
    }

    public JTable getJtTableAddStock(){
        return jtTableAddStock;
    }

    public void setJtTableAddStock(JTable jtTableAddStock){
        this.jtTableAddStock = jtTableAddStock;
    }

    public JTable getJtTableAddStockFromGui(){
        return jtTableAddStockFromGui;
    }

    public void setJtTableAddStockFromGui(JTable jtTableAddStockFromGui){
        this.jtTableAddStockFromGui = jtTableAddStockFromGui;
    }

    public MyModelTable getMmtTableAddStock(){
        return mmtTableAddStock;
    }

    public void setMmtTableAddStock(MyModelTable mmtTableAddStock){
        this.mmtTableAddStock = mmtTableAddStock;
    }

    public int getID_REF(){
        return ID_REF;
    }

    public int getCOLUMN_QUANTITY(){
        return COLUMN_QUANTITY;
    }
}
