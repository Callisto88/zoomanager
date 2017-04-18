package View.Stock;

import Controller.Stock.AddStockController;
import Controller.Stock.StockTabController;
import Model.Commande;
import Model.Stock;
//import View.Stock;
import View.DateLabelFormatter;
import View.GenericWindow;
import View.MyModelTable;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilDateModel;
//import org.jdesktop.swingx.JXDatePicker;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.Vector;

/**
 *
 * Cette classe est celle de l'interface principale du stock.
 *
 * A gauche le stock actuel avec plusieurs fonctionnalités (Commande, Ajouter, ...)
 *
 * A droite les commandes et leurs différents statuts.
 *
 * @author M.Silva
 *
 * @version 1.0
 *
 * @date    17.04.2017
 *
 */

public class StockTab extends GenericWindow {
    private static String[] COLUMN_STOCK_NAME = {"Aliment", "Quantité", "Quantité Minimum", "Unite", "Commande"};
    private static boolean[] COLUMN_STOCK_EDITABLE = {false, false, false, false, true};

    private static String[] COLUMN_HISTORY_NAME = {"ID", "Date"};

    public StockTab(StockTabController stcStockTabController){
        super("Stock");

        GridBagLayout gblLeft = new GridBagLayout();
        GridBagConstraints gbcLeft  = new GridBagConstraints();

        JPanel jpLeft = new JPanel();
        jpLeft.setLayout(gblLeft);

        JPanel jpLeftTitle = new JPanel();

        jpMainPanel.add(jpLeft);

        JLabel jlStock = new JLabel("Liste d'aliment en stock");
        setTitleConfig(jlStock);
        jpLeftTitle.add(jlStock);

        gbcLeft.fill = GridBagConstraints.CENTER;
        gbcLeft.gridx = 0;
        gbcLeft.anchor = GridBagConstraints.NORTH;
        gbcLeft.gridy = 0;
        jpLeft.add(jpLeftTitle,gbcLeft);

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 1;


        JPanel jpButtonStock = new JPanel();
        jpLeft.add(jpButtonStock, gbcLeft);

        JButton jbPrint = new JButton("Imprimer");
        setButtonConfig(jbPrint);

        JButton jbOrder = new JButton("Commande");
        setButtonConfig(jbOrder);

        JButton jbAdd = new JButton("Ajouter");
        setButtonConfig(jbAdd);

        JButton jbDelete = new JButton("Supprimer");
        setButtonConfig(jbDelete);

        JButton jbReset = new JButton("Effacer");
        setButtonConfig(jbReset);


        GridBagLayout gblStockBoutton = new GridBagLayout();
        jpButtonStock.setLayout(gblStockBoutton);
        GridBagConstraints gbcStockBouton = new GridBagConstraints();

        gbcStockBouton.insets = new Insets(15, 5, 15, 5);
        gbcStockBouton.gridx = 0;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbPrint, gbcStockBouton);

        gbcStockBouton.gridx = 1;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbOrder, gbcStockBouton);

        gbcStockBouton.gridx = 2;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbAdd, gbcStockBouton);

        gbcStockBouton.gridx = 3;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbDelete, gbcStockBouton);

        gbcStockBouton.gridx = 4;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbReset, gbcStockBouton);


        /**************************************************************/

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        gbcLeft.weighty = 20;


        JPanel jpTableStock = new JPanel();
        jpTableStock.setPreferredSize(new Dimension(800, 720));

        ArrayList<Stock> alStock = stcStockTabController.getAllStock();
        Vector<Vector<Object>> vStock = new Vector<>();

        for(Stock sStock : alStock){
            vStock.add(sStock.toVector());
        }

        JTable jtTableStock = new JTable(new MyModelTable(vStock, COLUMN_STOCK_NAME, COLUMN_STOCK_EDITABLE));
        //setTableConfig(jtTableStock);

        Dimension d = jtTableStock.getPreferredScrollableViewportSize();
        d.width = jtTableStock.getPreferredSize().width;
        jtTableStock.setPreferredScrollableViewportSize(d);

        JScrollPane jspStock = new JScrollPane(jtTableStock);
        jspStock.setPreferredSize(new Dimension(700, 700));


        jpTableStock.add(jspStock);
        jpLeft.add(jpTableStock, gbcLeft);

        /***********************************************************************************/


        GridBagLayout gblRight = new GridBagLayout();
        GridBagConstraints gbcRight  = new GridBagConstraints();

        JPanel jpRight = new JPanel();
        jpRight.setLayout(gblRight);

        JPanel jpRightTitle = new JPanel();

        jpMainPanel.add(jpRight);

        JLabel jlOrder = new JLabel("Commandes");
        setTitleConfig(jlOrder);
        jpRightTitle.add(jlOrder);

        gbcRight.fill = GridBagConstraints.CENTER;
        gbcRight.gridx = 0;
        gbcRight.anchor = GridBagConstraints.NORTH;
        gbcRight.gridy = 0;
        jpRight.add(jpRightTitle,gbcRight);

        gbcRight.gridx = 0;
        gbcRight.gridy = 1;


        JPanel jpHistoryOrder = new JPanel();
        jpRight.add(jpHistoryOrder, gbcRight);

        JButton jbHistory = new JButton("Recherche");
        setButtonConfig(jbHistory);

        Checkbox cWaiting = new Checkbox("En cours");
        setCheckboxConfig(cWaiting);


        Properties pStartProperties = new Properties();
        pStartProperties.put("text.today", "Aujourd'hui");
        pStartProperties.put("text.month", "Mois");
        pStartProperties.put("text.year", "Année");

        SqlDateModel sdmModel1 = new SqlDateModel();
        SqlDateModel sdmModel2 = new SqlDateModel();

        JDatePanelImpl jdpliStartDatePanel = new JDatePanelImpl(sdmModel1, pStartProperties);
        jdpliStartDatePanel.setPreferredSize(new Dimension(200, 200));
        JDatePickerImpl jdpriStartDatePicker = new JDatePickerImpl(jdpliStartDatePanel, new DateLabelFormatter());

        Properties pEndProperties = new Properties();
        pEndProperties.put("text.today", "Aujourd'hui");
        pEndProperties.put("text.month", "Mois");
        pEndProperties.put("text.year", "Année");

        JDatePanelImpl jdpliEndDatePanel = new JDatePanelImpl(sdmModel2, pEndProperties);
        jdpliEndDatePanel.setPreferredSize(new Dimension(200, 200));
        JDatePickerImpl jdpriEndDatePicker = new JDatePickerImpl(jdpliEndDatePanel, new DateLabelFormatter());

        JLabel jlStartDate = new JLabel("De: ");
        JLabel jlEndDate = new JLabel("A: ");

        GridBagLayout gblOrderBoutton = new GridBagLayout();
        jpHistoryOrder.setLayout(gblOrderBoutton);
        GridBagConstraints gbcOrderBouton = new GridBagConstraints();

        gbcOrderBouton.insets = new Insets(15, 30, 15, 30);
        gbcOrderBouton.gridx = 0;
        gbcOrderBouton.gridy = 0;
        jpHistoryOrder.add(jbHistory, gbcOrderBouton);


        gbcOrderBouton.gridx = 1;
        gbcOrderBouton.gridy = 0;
        jpHistoryOrder.add(jlStartDate);

        gbcOrderBouton.gridx = 2;
        gbcOrderBouton.gridy = 0;
        jpHistoryOrder.add(jdpriStartDatePicker, gbcOrderBouton);

        gbcOrderBouton.gridx = 3;
        gbcOrderBouton.gridy = 0;
        jpHistoryOrder.add(jlEndDate);

        gbcOrderBouton.gridx = 4;
        gbcOrderBouton.gridy = 0;
        jpHistoryOrder.add(jdpriEndDatePicker, gbcOrderBouton);


        /**************************************************************/


        gbcRight.gridx = 0;
        gbcRight.gridy = 2;
        gbcRight.weighty = 20;


        JPanel jpTableOrder = new JPanel();
        jpTableOrder.setPreferredSize(new Dimension(800, 720));

        ArrayList<Commande> alCommandeHistory = stcStockTabController.getAllCommandeHistory();
        Vector<Vector<Object>> vCommandeHistory = new Vector<>();

        for(Commande cHistory : alCommandeHistory){
            // vCommandeHistory.add(cHistory.toVector());
        }

        JTable jtTableCommandeHistory = new JTable(new MyModelTable(vCommandeHistory, COLUMN_HISTORY_NAME));

        Dimension d1 = jtTableCommandeHistory.getPreferredScrollableViewportSize();
        d1.width = jtTableCommandeHistory.getPreferredSize().width;
        jtTableCommandeHistory.setPreferredScrollableViewportSize(d1);

        JScrollPane jspStock1 = new JScrollPane(jtTableCommandeHistory);
        jspStock1.setPreferredSize(new Dimension(700, 700));


        jpTableOrder.add(jspStock1);
        jpRight.add(jpTableOrder, gbcRight);

        jbPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        jbOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Stock> sOrder = new Vector<Stock>();
                int column = 4;
                for(int i = 0; i < jtTableStock.getRowCount(); ++i){
                    if((double)jtTableStock.getValueAt(i, column) > 0){}


                }
            }
        });

        jbAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStockController();
            }
        });

        jbDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /*
        cWaiting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        */

        jbReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = 4;
                for(int i = 0; i < jtTableStock.getRowCount(); ++i){
                    jtTableStock.setValueAt(new Double(0), i, column);
                }
            }
        });


        /**************************/

        jbHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        configFrame(getJfFrame(), this);


    }

}
