package Controller.Stock;

import Controller.Error.ErrorController;
import Controller.Validate.Validate;
import Model.*;
import View.MyModelTable;
import View.Stock.OrderContent;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Miguel on 07/05/2017.
 */
public class OrderContentController {
    private DBInteraction dbiQuery;
    private OrderContent ocOrderContent;
    private int orderID;
    private Date dOrder;
    private Statut sOrder;
    private Vector<Object> vOrder;
    private Vector<Vector<Object>> vOrderContent = new Vector<>();
    private MyModelTable mmtOrderContent;
    final int COLUMN_ID = 0;
    final int COLUMN_DATE = 1;
    final int COLUMN_STATUS = 2;

    public OrderContentController(Vector<Object> vOrder, MyModelTable mmtOrderContent){
        this.orderID = (int)vOrder.elementAt(COLUMN_ID);
        this.dOrder = (Date)vOrder.elementAt(COLUMN_DATE);
        this.sOrder = (Statut)vOrder.elementAt(COLUMN_STATUS);
        this.mmtOrderContent = mmtOrderContent;

        dbiQuery = null;

        ArrayList<Contenu_Commande> alOrderContent;

        try {
            dbiQuery = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            alOrderContent = dbiQuery.selAllContenuCommandeParID(orderID);

            for (Contenu_Commande c : alOrderContent) {

                System.out.println(c.getOrderID());  // Celui-là

                Stock sArticle = dbiQuery.selArticleByRef(c.getRefArticle());
                sArticle.setQuantite(c.getQuantite());

                vOrderContent.add(sArticle.toVectorToLookOrder());
            }

            ocOrderContent = new OrderContent(this, vOrderContent);

        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }catch (SQLException e) {
            new ErrorController(e.getMessage());
        }

    }

    public boolean updateStatus(Commande commande){
        boolean updateOK = true;
        dbiQuery = null;
        try {
            dbiQuery = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try{
            dbiQuery.updateCommande(commande);
        }catch (SQLException e) {
            new ErrorController(e.getMessage());
            updateOK = false;
        }
        return updateOK;
    }

    public int getOrderID(){
        return orderID;
    }

    public void setOrderID(int orderID){
        this.orderID = orderID;
    }

    public void getOrderContent(){

    }

    public Date getDateOrder(){
        return dOrder;
    }

    public void setDateOrder(Date dOrder){
        this.dOrder = dOrder;
    }

    public Statut getStatusOrder(){
        return sOrder;
    }

    public void setStatusOrder(Statut sOrder){
        this.sOrder = sOrder;
    }

    public MyModelTable getMmtOrderContent(){
        return mmtOrderContent;
    }

    public void setMmtOrderContent(MyModelTable mmtOrderContent){
        this.mmtOrderContent = mmtOrderContent;
    }

}
