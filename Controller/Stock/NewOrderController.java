package Controller.Stock;

import Controller.Validate.Validate;
import Model.*;
import View.MyModelTable;
import View.Stock.NewOrder;
import View.Stock.OrderContent;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Miguel on 05/05/2017.
 */
public class NewOrderController {
    private DBInteraction dbiQuery;
    private NewOrder noNewOrder;
    private Vector<Vector<Object>> vOrder ;
    private MyModelTable mmtOrderHistory;
    final int COLUMN_IDREF = 0;
    final int COLUMN_QUANTITY = 2;

    public NewOrderController(Vector<Vector<Object>> vOrder, MyModelTable mmtOrderHistory){
        this.vOrder = vOrder;
        this.mmtOrderHistory = mmtOrderHistory;
        noNewOrder = new NewOrder(this, this.vOrder);
    }

    public void newOrder(){
        dbiQuery = null;
        ArrayList<Contenu_Commande> data = new ArrayList<>();

        try {
            dbiQuery = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {

            for(int i = 0; i < vOrder.size(); ++i){

                Contenu_Commande ccNewOrder = new Contenu_Commande((int)vOrder.elementAt(i).elementAt(COLUMN_IDREF),
                        (double)vOrder.elementAt(i).elementAt(COLUMN_QUANTITY));
                data.add(ccNewOrder);
            }

            int orderID = dbiQuery.creerCommande(data);

            Commande cOrder = dbiQuery.selCommande(orderID);
            mmtOrderHistory.addRow(cOrder.toVector());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

    }

    public DBInteraction getDBInteraction(){
        return dbiQuery;
    }

    public void setDBInteraction(DBInteraction query){
        this.dbiQuery = query;
    }

    public NewOrder getNewOrder(){
        return noNewOrder;
    }

    public void setNoNewOrder(NewOrder noNewOrder){
        this.noNewOrder = noNewOrder;
    }

    public Vector<Vector<Object>> getvOrder(){
        return vOrder;
    }

    public void setvOrder(Vector<Vector<Object>> vOrder){
        this.vOrder = vOrder;
    }

    public MyModelTable getMmtOrderHistory(){
        return mmtOrderHistory;
    }

    public void setMmtOrderHistory(MyModelTable mmtOrderHistory){
        this.mmtOrderHistory = mmtOrderHistory;
    }

    public int getCOLUMN_IDREF(){
        return COLUMN_IDREF;
    }

    public int getCOLUMN_QUANTITY(){
        return COLUMN_QUANTITY;
    }
}
