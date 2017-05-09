package Controller.Stock;

import Controller.Validate.Validate;
import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Stock;
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

    private Validate vAddStock;
    private AddStock asAddStock;
    Vector<Vector<Object>> vAdd;
    JTable jtTableStock;

    public AddStockController(Vector<Vector<Object>> vAdd, JTable jtTableStock){
        vAddStock = new Validate();
        this.vAdd = vAdd;
        this.jtTableStock = jtTableStock;
        asAddStock = new AddStock(this, this.vAdd);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return vAdd.elementAt(rowIndex).elementAt(columnIndex);
    }

    public void addStock() throws Exception {
        DBInteraction query = null;
        ArrayList<Stock> data = new ArrayList<>();

        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            //int i = 0;

            for(int i = 0; i < vAdd.size(); ++i){
                Vector<Object> temp = vAdd.elementAt(i);
                //System.out.println((int)temp.elementAt(0) + " + " + (double)temp.elementAt(2));

                query.addQuantity((int)temp.elementAt(0), (double)temp.elementAt(2));

                //System.out.println((double)jtTableStock.getValueAt(i, 2));
                //System.out.println((double)temp.elementAt(2));


                jtTableStock.setValueAt((double)jtTableStock.getValueAt(i, 2) + (double)temp.elementAt(2), i , 2);
            }

            /*
            for(Vector<Object> temp : vAdd){
                System.out.println((int)temp.elementAt(0) + " + " + (double)temp.elementAt(2));

                query.addQuantity((int)temp.elementAt(0), (double)temp.elementAt(2));

                System.out.println((double)jtTableStock.getValueAt(i, 2));
                System.out.println((double)temp.elementAt(2));


                jtTableStock.setValueAt((double)jtTableStock.getValueAt(i, 2) + (double)temp.elementAt(2), i, 2);
                ++i;
            }
            */

            //} catch (ExceptionDataBase e) {
            //   System.out.println(e.getMsg());
        } catch (SQLException e) {
            throw new Exception();
        }

        //return data;

    }

    public boolean isValidate(){
        return true;
    }
}
