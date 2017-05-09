package Controller.Stock;

import Controller.Validate.Validate;
import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Stock;
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
    private Validate vAddStock;
    private DeleteStock asDeleteStock;
    Vector<Vector<Object>> vDelete;
    JTable jtTableStock;

    public DeleteStockController(Vector<Vector<Object>> vDelete, JTable jtTableStock){
        vAddStock = new Validate();
        this.vDelete = vDelete;
        this.jtTableStock = jtTableStock;
        asDeleteStock = new DeleteStock(this, this.vDelete);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return vDelete.elementAt(rowIndex).elementAt(columnIndex);
    }

    public void deleteStock(){
        DBInteraction query = null;
        ArrayList<Stock> data = new ArrayList<>();

        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            for(int i = 0; i < vDelete.size(); ++i){
                Vector<Object> temp = vDelete.elementAt(i);
                query.delQuantity((int)temp.elementAt(0), (double)temp.elementAt(2));
                jtTableStock.setValueAt((double)jtTableStock.getValueAt(i, 2) - (double)temp.elementAt(2), i , 2);
            }

            //} catch (ExceptionDataBase e) {
            //   System.out.println(e.getMsg());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isValidate(){
        return true;
    }
}
