package Controller.Stock;

import Controller.Validate.Validate;
import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Stock;
import View.CellStatus;
import View.MyModelTable;
import View.MyRenderer;
import View.Stock.DeleteStock;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * Cette classe est le contrôleur de l'interface qui permet de supprimer du stock dans la DB.
 *
 * IL vérifie que les valeurs rentrées par l'utilisateur sont correctes
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
public class DeleteStockController {
    private DBInteraction dbiQuery;
    private DeleteStock dsDeleteStock;
    private Vector<Vector<Object>> vDelete;
    private JTable jtTableDeleteStock;
    private JTable jtTableDeleteStockFromGui;
    private MyModelTable mmtTableDeleteStock;
    private final int ID_REF = 0;
    private final int COLUMN_QUANTITY = 2;

    public DeleteStockController(Vector<Vector<Object>> vDelete, JTable jtTableDeleteStock){
        this.vDelete = vDelete;
        this.jtTableDeleteStock = jtTableDeleteStock;
        dsDeleteStock = new DeleteStock(this, this.vDelete);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return vDelete.elementAt(rowIndex).elementAt(columnIndex);
    }

    public void deleteStock(){
        dbiQuery = null;

        try {
            dbiQuery = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            for(int i = 0; i < vDelete.size(); ++i){
                Vector<Object> temp = vDelete.elementAt(i);
                dbiQuery.delQuantity((int)temp.elementAt(ID_REF), (double)temp.elementAt(COLUMN_QUANTITY));
                jtTableDeleteStock.setValueAt((double)jtTableDeleteStock.getValueAt(i, COLUMN_QUANTITY) - (double)temp.elementAt(COLUMN_QUANTITY), i , COLUMN_QUANTITY);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkData(JTable jtTableDeleteStockFromGui, MyModelTable mmtTableDeleteStock){
        this.jtTableDeleteStockFromGui = jtTableDeleteStockFromGui;
        this.mmtTableDeleteStock = mmtTableDeleteStock;
        MyRenderer mrRenderer = new MyRenderer(mmtTableDeleteStock, COLUMN_QUANTITY);
        boolean dataOK = true;
        for(int i = 0; i < vDelete.size(); ++i){
            Vector<Object> vTemp = vDelete.elementAt(i);

            double temp = (double) (double)vTemp.elementAt(COLUMN_QUANTITY);

            if(isNumericPositiveDouble(temp)){
                mmtTableDeleteStock.setCellStatus(CellStatus.EMPTY, i, COLUMN_QUANTITY);

            }else if(isNumericAndBelowZero(temp)){
                jtTableDeleteStockFromGui.getColumnModel().getColumn(COLUMN_QUANTITY).setCellRenderer(mrRenderer);
                mmtTableDeleteStock.setCellStatus(CellStatus.RED, i, COLUMN_QUANTITY);
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

    public void setDBInteraction(DBInteraction dbiQuery){
        this.dbiQuery = dbiQuery;
    }

    public DeleteStock getDsDeleteStock(){
        return dsDeleteStock;
    }

    public void setDsDeleteStock(DeleteStock dsDeleteStock){
        this.dsDeleteStock = dsDeleteStock;
    }

    public Vector<Vector<Object>> getVDelete(){
        return vDelete;
    }

    public void setVDelete(Vector<Vector<Object>> vDelete){
        this.vDelete = vDelete;
    }

    public JTable getJtTableDeleteStock(){
        return jtTableDeleteStock;
    }

    public void setJtTableDeleteStock(JTable jtTableDeleteStock){
        this.jtTableDeleteStock = jtTableDeleteStock;
    }

    public JTable getJtTableDeleteStockFromGui(){
        return jtTableDeleteStockFromGui;
    }

    public void setJtTableDeleteStockFromGui(JTable jtTableDeleteStockFromGui){
        this.jtTableDeleteStockFromGui = jtTableDeleteStockFromGui;
    }

    public MyModelTable getMmtTableDeleteStock(){
        return mmtTableDeleteStock;
    }

    public void setMmtTableDeleteStock(MyModelTable mmtTableDeleteStock){
        this.mmtTableDeleteStock = mmtTableDeleteStock;
    }

    public int getID_REF(){
        return ID_REF;
    }

    public int getCOLUMN_QUANTITY(){
        return COLUMN_QUANTITY;
    }
}
