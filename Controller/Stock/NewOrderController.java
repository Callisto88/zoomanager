package Controller.Stock;

import Controller.Validate.Validate;
import Model.*;
import View.MyModelTable;
import View.Stock.NewOrder;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Miguel on 05/05/2017.
 */
public class NewOrderController {
    private Validate vNewOrder;
    private NewOrder noNewOrder;
    Vector<Vector<Object>> vOrder ;
    MyModelTable mtCommandeHistory;

    public NewOrderController(Vector<Vector<Object>> vOrder, MyModelTable mtCommandeHistory){
        vNewOrder = new Validate();
        this.vOrder = vOrder;
        this.mtCommandeHistory = mtCommandeHistory;
        noNewOrder = new NewOrder(this, this.vOrder);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return vOrder.elementAt(rowIndex).elementAt(columnIndex);
    }

    public void newOrder(){
        DBInteraction query = null;
        int idCommande;
        ArrayList<Contenu_Commande> data = new ArrayList<>();

        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            final int COLUMN_IDREF = 0;
            final int COLUM_QUANTITY = 2;
            //idCommande = query.creerCommande();
            for(int i = 0; i < vOrder.size(); ++i){
                Contenu_Commande ccNewOrder = new Contenu_Commande((int)vOrder.elementAt(i).elementAt(COLUMN_IDREF),
                        (double)vOrder.elementAt(i).elementAt(COLUM_QUANTITY));
                data.add(ccNewOrder);
            }

            int orderID = query.creerCommande(data);

            Commande cTemp = query.selCommande(orderID);

            mtCommandeHistory.addRow(cTemp.toVector());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

    }

    public boolean isValidate(){
        return true;
    }

}
