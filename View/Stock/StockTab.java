package View.Stock;

import Model.Stock;
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
import java.util.Calendar;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by Andre on 10.03.2017.
 */
public class StockTab extends GenericWindow {
    private String[] columnName = {"Aliment", "Quantité", "Quantité Minimum", "Unite", "Commande"};
    private Class[] columnClass = {String.class, Double.class, Double.class, String.class, Double.class};

    public StockTab(){
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
        gbcLeft.insets = new Insets(5,5,5,5);
        jpLeft.add(jpLeftTitle,gbcLeft);

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 1;


        JPanel jpButtonStock = new JPanel();
        jpLeft.add(jpButtonStock, gbcLeft);

        JButton jbPrint = new JButton("Imprimer");
        setButtonConfig(jbPrint);

        JButton jbOrder = new JButton("Commande");
        setButtonConfig(jbOrder);


        GridBagLayout gblStockBoutton = new GridBagLayout();
        jpButtonStock.setLayout(gblStockBoutton);
        GridBagConstraints gbcStockBouton = new GridBagConstraints();

        gbcStockBouton.insets = new Insets(10, 30, 10, 30);
        gbcStockBouton.gridx = 0;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbPrint, gbcStockBouton);

        gbcStockBouton.gridx = 1;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbOrder, gbcStockBouton);


        /**************************************************************/

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        gbcLeft.weighty = 20;


        JPanel jpTableStock = new JPanel();
        jpTableStock.setPreferredSize(new Dimension(820, 655));

        Stock sTest1 = new Stock("Poisson", 5.0, 7.0, "kg");

        Vector<Object> sTest = sTest1.toVector();

        //Stock sTest2 = new Stock("Poisson", 5.0, 7.0, "kg", 3.0);

        Vector<Vector<Object>> vStock = new Vector<Vector<Object>>();
        vStock.add(sTest);
        //vStock.elementAt();

        JTable jtTable = new JTable(new MyModelTable(vStock, columnName)){
            public boolean isCellEditable(int row, int column){
                if(column == 4){
                    return true;
                };
                return false;
            }
        };

        //MyPersonnalJTable jtTable = new MyPersonnalJTable();
        Dimension d = jtTable.getPreferredScrollableViewportSize();
        d.width = jtTable.getPreferredSize().width;
        jtTable.setPreferredScrollableViewportSize(d);

        JScrollPane jspStock = new JScrollPane(jtTable);
        jspStock.setPreferredSize(new Dimension(820, 640));


        jpTableStock.add(jspStock);
        jpLeft.add(jpTableStock, gbcLeft);

        /***********************************************************************************/


        GridBagLayout gblRight = new GridBagLayout();
        GridBagConstraints gbcRight  = new GridBagConstraints();

        JPanel jpRight = new JPanel();
        jpRight.setLayout(gblRight);

        JPanel jpRightTitle = new JPanel();

        jpMainPanel.add(jpRight);

        JLabel jlOrder = new JLabel("Historique Commandes");
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
        //jpHistoryOrder.setLayout(new FlowLayout());
        jpRight.add(jpHistoryOrder, gbcRight);

        JButton jbHistory = new JButton("Historique");
        setButtonConfig(jbHistory);


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
        //jpButtonOrder.add(jbChooseHistory, gbcOrderBouton);

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

        Stock sTest2 = new Stock("Poisson", 5.0, 7.0, "kg");

        Vector<Object> sTest3 = sTest1.toVector();

        //Stock sTest2 = new Stock("Poisson", 5.0, 7.0, "kg", 3.0);

        Vector<Vector<Object>> vStock1 = new Vector<Vector<Object>>();
        vStock1.add(sTest3);
        //vStock.elementAt();

        JTable jtTable1 = new JTable(new MyModelTable(vStock1, columnName)){
            public boolean isCellEditable(int row, int column){
                if(column == 4){
                    return true;
                };
                return false;
            }
        };

        //MyPersonnalJTable jtTable = new MyPersonnalJTable();
        Dimension d1 = jtTable1.getPreferredScrollableViewportSize();
        d1.width = jtTable1.getPreferredSize().width;
        jtTable1.setPreferredScrollableViewportSize(d1);

        JScrollPane jspStock1 = new JScrollPane(jtTable1);
        jspStock1.setPreferredSize(new Dimension(700, 700));


        jpTableOrder.add(jspStock1);
        jpRight.add(jpTableOrder, gbcRight);

        jbOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        jbHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        configFrame(getJfFrame(), this);


    }

}
