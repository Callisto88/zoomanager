package View.Stock;

import View.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

/**
 * Created by Andre on 10.03.2017.
 */
public class StockTab extends GenericWindow {

    public StockTab(){
        super("Stock");

        GridBagLayout gblLeft = new GridBagLayout();
        GridBagConstraints gbcLeft  = new GridBagConstraints();

        BorderLayout gblRight = new BorderLayout();
        //GridBagConstraints gbcRight = new GridBagConstraints();

        JPanel jpLeft = new JPanel();
        jpLeft.setBackground(Color.blue);
        jpLeft.setLayout(gblLeft);

        JPanel jpRight = new JPanel();
        jpRight.setBackground(Color.gray);
        jpRight.setLayout(gblRight);

        JPanel jpLeftTest = new JPanel();
        jpLeftTest.setBackground(Color.magenta);

        jpMainPanel.add(jpLeft);
        jpMainPanel.add(jpRight);

        JLabel jlStock = new JLabel("Liste d'aliment en stock");
        setTitleConfig(jlStock);
        jpLeftTest.add(jlStock);

        JLabel jlLowStock = new JLabel("Liste du minimum requis");
        setTitleConfig(jlLowStock);


        gbcLeft.fill = GridBagConstraints.CENTER;
        gbcLeft.gridx = 0;
        gbcLeft.anchor = GridBagConstraints.NORTH;
        gbcLeft.gridy = 0;
        gbcLeft.insets = new Insets(15,15,15,15);
        jpLeft.add(jpLeftTest,gbcLeft);

        jpRight.add(jlLowStock, BorderLayout.NORTH);

        JLabel test = new JLabel("test");
        jpRight.add(test, BorderLayout.WEST);


        /*
        gbcRight.gridx = 1;
        gbcRight.gridy = 0;
        jpRight.add(jlLowStock, gbcRight);
        *

        /***************************************************************/

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 1;

        JPanel jpButtonStock = new JPanel();
        jpButtonStock.setBackground(Color.cyan);
        jpLeft.add(jpButtonStock, gbcLeft);

        JButton jbPrint = new JButton("Imprimer");
        setButtonConfig(jbPrint);

        JButton jbCreateListOrder = new JButton("Liste minimum requis");
        setButtonConfig(jbCreateListOrder);

        JButton jbAllOrderHistory = new JButton("Historique Commande");
        setButtonConfig(jbAllOrderHistory);

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
        jpButtonStock.add(jbAllOrderHistory, gbcStockBouton);

        gbcStockBouton.gridx = 3;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbReset, gbcStockBouton);

        /*
        gbcStockBouton.gridx = 3;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbLeave, gbcStockBouton);
        */

        /**************************************************************/

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        gbcLeft.weighty = 20;



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
                {"Miguel", "Brown",
                        "Pool", new Integer(10), new Boolean(false)}
        };

        JPanel jpTableStock = new JPanel();
        jpTableStock.setBackground(Color.ORANGE);
        jpTableStock.setPreferredSize(new Dimension(800, 800));
        //jpTableStock.setMinimumSize(new Dimension(400,800));
        JTable jtTable = new JTable(data, columnName);
        //jtTable.setPreferredSize(JTable);
        Dimension d = jtTable.getPreferredScrollableViewportSize();

        d.width = jtTable.getPreferredSize().width;

        jtTable.setPreferredScrollableViewportSize(d);
        //resizeColumnWidth(jtTable);
        //jtTable.setMinimumSize(new Dimension(400,400));
        //jtTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        //jtTable.setPreferredSize(new Dimension(400,400));
        //jtTable.sizeColumnsToFit(50);
        JScrollPane jspStock = new JScrollPane(jtTable);
        jspStock.setPreferredSize(new Dimension(700, 700));


        jpTableStock.add(jspStock);
        jpLeft.add(jpTableStock, gbcLeft);




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

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +10 , width);
            }
            if(width > 300)
                width=500;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
