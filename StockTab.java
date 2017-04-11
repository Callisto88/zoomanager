import javax.swing.*;
import java.awt.*;

/**
 * Created by Andre on 10.03.2017.
 */
public class StockTab extends GenericWindow {

    public StockTab(){
        super("Stock");


        JLabel jlStock = new JLabel("Liste d'aliment en stock");
        setTitleConfig(jlStock);

        JLabel jlLowStock = new JLabel("Liste du minimum requis");
        setTitleConfig(jlLowStock);

        gbcMainPanel.fill = GridBagConstraints.CENTER;
        gbcMainPanel.gridx = 0;
        gbcMainPanel.anchor = GridBagConstraints.NORTH;
        gbcMainPanel.gridy = 0;
        gbcMainPanel.insets = new Insets(15,15,15,15);
        jpMainPanel.add(jlStock,gbcMainPanel);

        gbcMainPanel.gridx = 1;
        gbcMainPanel.gridy = 0;
        jpMainPanel.add(jlLowStock, gbcMainPanel);

        /***************************************************************/

        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 1;

        JPanel jpButtonStock = new JPanel();
        jpMainPanel.add(jpButtonStock, gbcMainPanel);

        JButton jbPrint = new JButton("Imprimer");
        setButtonConfig(jbPrint);

        JButton jbCreateListOrder = new JButton("Créer liste");
        setButtonConfig(jbCreateListOrder);

        JButton jbReset = new JButton("Effacer");
        setButtonConfig(jbReset);

        /*
        JButton jbLeave = new JButton("Quit");
        setButtonConfig(jbLeave);
        */


        GridBagLayout gblStockBoutton = new GridBagLayout();
        jpButtonStock.setLayout(gblStockBoutton);
        GridBagConstraints gbcStockBouton = new GridBagConstraints();

       // gbcStockBouton.fill = GridBagConstraints.NORTH;
        //gbcStockBouton.anchor = GridBagConstraints.HORIZONTAL;
        gbcStockBouton.insets = new Insets(0,15,0,15);
        gbcStockBouton.gridx = 0;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbPrint, gbcStockBouton);

        gbcStockBouton.gridx = 1;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbCreateListOrder, gbcStockBouton);

        gbcStockBouton.gridx = 2;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbReset, gbcStockBouton);

        /*
        gbcStockBouton.gridx = 3;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbLeave, gbcStockBouton);
        */

        /**************************************************************/

        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 2;
        //gbcMainPanel.gridwidth = 10;
        gbcMainPanel.weighty = 20;



        /*
        JScrollBar jsbStock = new JScrollBar();
        JScrollPane jspStock = new JScrollPane();
        */

        String[] columnName = {"Aliment", "Quantité", "Quantité Minimum", "Activer Commande", "Quantité A Commander (kg)"};

        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)},
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)},
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)},
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)},
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)},
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)},
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)}
        };

        JPanel jpTableStock = new JPanel();
        jpTableStock.setPreferredSize(new Dimension(500,800));
        JTable jtTable = new JTable(data, columnName);
        jtTable.setMinimumSize(new Dimension(400,400));
        jtTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        jtTable.setPreferredSize(new Dimension(400,800));
        //jtTable.sizeColumnsToFit(50);
        JScrollPane jspStock = new JScrollPane(jtTable);


        jpTableStock.add(jspStock);
        jpMainPanel.add(jpTableStock, gbcMainPanel);




        /*
        jpMainPanel.add(jspStock);
        jspStock.setViewportView(jpTableStock);
        jspStock.add(jsbStock);
        */

        /*
        JLabel jlFoodName = new JLabel("Aliment");
        jlFoodName.setFont(new Font("test", Font.PLAIN, 15));
        JLabel jlQuantity = new JLabel("Quantité");
        jlQuantity.setFont(new Font("test", Font.PLAIN, 15));
        JLabel jlMinimumQuantity = new JLabel("Quantité Minimum");
        jlMinimumQuantity.setFont(new Font("test", Font.PLAIN, 15));
        JLabel jlActivateOrder = new JLabel("Activer Commande");
        jlActivateOrder.setFont(new Font("test", Font.PLAIN, 15));
        JLabel jlQuantityOrdered = new JLabel("Quantité A Commander (kg)");
        jlQuantityOrdered.setFont(new Font("test", Font.PLAIN, 15));

        GridBagLayout gblStock = new GridBagLayout();
        jpTableStock.setLayout(gblStock);
        GridBagConstraints gbcStock = new GridBagConstraints();

        //gbcStock.fill = GridBagConstraints.NORTH;
        gbcStock.gridx = 0;
        gbcStock.anchor = GridBagConstraints.NORTH;
        gbcStock.insets = new Insets(0,10,0,10);
        gbcStock.gridy = 0;
        jpTableStock.add(jlFoodName, gbcStock);

        gbcStock.gridx = 1;
        gbcStock.gridy = 0;
        jpTableStock.add(jlQuantity, gbcStock);

        gbcStock.gridx = 2;
        gbcStock.gridy = 0;
        jpTableStock.add(jlMinimumQuantity, gbcStock);

        gbcStock.gridx = 3;
        gbcStock.gridy = 0;
        jpTableStock.add(jlActivateOrder, gbcStock);

        gbcStock.gridx = 4;
        gbcStock.gridy = 0;
        jpTableStock.add(jlQuantityOrdered, gbcStock);
        */

        /******************************************************************/

        configFrame(getJfFrame(), this);


    }
}
