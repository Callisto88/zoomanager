package Controller.Stock;

import Controller.Validate.Validate;
import Model.Commande;
import Model.Contenu_Commande;
import Model.DBInteraction;
import Model.ExceptionDataBase;
import View.MyModelTable;
import View.Stock.OrderContent;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Miguel on 07/05/2017.
 */
public class OrderContentController {
    private OrderContent ocOrderContent;
    private int OrderID;
    private Vector<Vector<Object>> vOrderContent = new Vector<>();

    public OrderContentController(int OrderID){
        this.OrderID = OrderID;

        DBInteraction query = null;

        ArrayList<Contenu_Commande> alOrderContent;

        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            alOrderContent = query.selAllContenuCommandeParID(OrderID);

/*
            for(Contenu_Commande ccOrderContent : alOrderContent){
                vOrderContent.add(ccOrderContent.toVector());
            }
            */

            ocOrderContent = new OrderContent(this, vOrderContent);

        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void getOrderContent(){

    }

}
