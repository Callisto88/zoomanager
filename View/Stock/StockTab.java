package View.Stock;

import Controller.Information.InformationController;
import Controller.Stock.*;
import Controller.Validate.Validate;
import Model.Commande;
import Model.Statut;
import Model.Stock;
//import View.Stock;
//import View.ButtonShowRenderer;
import Model.Tools.DateSQL;
import View.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
//import org.jdesktop.swingx.JXDatePicker;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

import static javax.swing.SwingConstants.CENTER;

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
    private final String[] COLUMN_STOCK_NAME = {"ID", "Description", "Quantité", "Quantité Minimum", "Unite", "Commande"};
    private final boolean[] COLUMN_STOCK_EDITABLE = {false, false, false, false, false, true};
    private final String[] COLUMN_HISTORY_NAME = {"ID", "Date", "Statut"};
    private final Statut [] ORDER_STATUS = {Statut.TOUS, Statut.CREEE, Statut.EN_COURS, Statut.TERMINEE, Statut.ANNULEE, Statut.REMBOURSEE};
    final int COLUMN_QUANTITY = 5;



    private ArrayList<Commande> alCommandeHistory;
    private MyModelTable mmtStock;
    private MyModelTable mmtCommandeHistory;
    private MyRenderer mrRendererStock;
    private MyRenderer mrRendererOrder;
    private JTable jtTableStock;
    private JTable jtTableCommandeHistory;
    private TableRowSorter<MyModelTable> trsSorter;

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

        mmtStock = new MyModelTable(vStock, COLUMN_STOCK_NAME, COLUMN_STOCK_EDITABLE);
        jtTableStock = new JTable((mmtStock));
        mrRendererStock = new MyRenderer(mmtStock, COLUMN_QUANTITY);
        trsSorter = new TableRowSorter<>(mmtStock);
        jtTableStock.setRowSorter(trsSorter);

        jtTableStock.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                Point p = e.getPoint();
                int rowIndex = jtTableStock.rowAtPoint(p);
                int columnIndex = jtTableStock.columnAtPoint(p);

                if (rowIndex > 0 && columnIndex == 5) {
                    jtTableStock.setToolTipText("Seuls les nombres positifs sont acceptés dans la colonne commande");
                }else{
                    jtTableStock.setToolTipText("");
                }
            }

        });

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

        JButton jbSearch = new JButton("Recherche");
        setButtonConfig(jbSearch);


        Properties pStartProperties = new Properties();
        pStartProperties.put("text.today", "Aujourd'hui");
        pStartProperties.put("text.month", "Mois");
        pStartProperties.put("text.year", "Année");

        SqlDateModel sdmModelStartDate = new SqlDateModel();
        SqlDateModel sdmModelEndDate = new SqlDateModel();

        JDatePanelImpl jdpliStartDatePanel = new JDatePanelImpl(sdmModelStartDate, pStartProperties);
        jdpliStartDatePanel.setPreferredSize(new Dimension(200, 200));
        JDatePickerImpl jdpriStartDatePicker = new JDatePickerImpl(jdpliStartDatePanel, new DateLabelFormatter());

        Properties pEndProperties = new Properties();
        pEndProperties.put("text.today", "Aujourd'hui");
        pEndProperties.put("text.month", "Mois");
        pEndProperties.put("text.year", "Année");

        JDatePanelImpl jdpliEndDatePanel = new JDatePanelImpl(sdmModelEndDate, pEndProperties);
        jdpliEndDatePanel.setPreferredSize(new Dimension(200, 200));
        JDatePickerImpl jdpriEndDatePicker = new JDatePickerImpl(jdpliEndDatePanel, new DateLabelFormatter());

        JLabel jlStartDate = new JLabel("De: ");
        JLabel jlEndDate = new JLabel("A: ");

        GridBagLayout gblOrderBoutton = new GridBagLayout();
        jpHistoryOrder.setLayout(gblOrderBoutton);
        GridBagConstraints gbcOrderBouton = new GridBagConstraints();

        gbcOrderBouton.insets = new Insets(15, 10, 15, 10);
        gbcOrderBouton.gridx = 0;
        gbcOrderBouton.gridy = 0;
        jpHistoryOrder.add(jbSearch, gbcOrderBouton);

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

        JComboBox jcbOrderStatus = new JComboBox(ORDER_STATUS);
        gbcOrderBouton.gridx = 5;
        gbcOrderBouton.gridy = 0;
        jpHistoryOrder.add(jcbOrderStatus, gbcOrderBouton);


        /**************************************************************/


        gbcRight.gridx = 0;
        gbcRight.gridy = 2;
        gbcRight.weighty = 20;


        JPanel jpTableOrder = new JPanel();
        jpTableOrder.setPreferredSize(new Dimension(800, 720));

        alCommandeHistory = stcStockTabController.getAllCommandeHistory();
        Vector<Vector<Object>> vCommandeHistory = new Vector<>();

        for(Commande cHistory : alCommandeHistory){
            vCommandeHistory.add(cHistory.toVector());
        }

        mmtCommandeHistory = new MyModelTable(vCommandeHistory, COLUMN_HISTORY_NAME);
        jtTableCommandeHistory = new JTable(mmtCommandeHistory);
        mrRendererOrder = new MyRenderer(mmtCommandeHistory, COLUMN_QUANTITY);
        //trsSorter = new TableRowSorter<>(mmtCommandeHistory);
        //jtTableCommandeHistory.setRowSorter(trsSorter);

        jtTableCommandeHistory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final int CLICK_COUNT = 2;

                if(e.getClickCount() == CLICK_COUNT){
                    //new OrderContentController((int)jtTableCommandeHistory.getValueAt(jtTableCommandeHistory.getSelectedRow(),COLUMN_ID));
                    new OrderContentController(mmtCommandeHistory.getValueAtRow(jtTableCommandeHistory.getSelectedRow()), mmtCommandeHistory);
                }
            }
        });


        Dimension d1 = jtTableCommandeHistory.getPreferredScrollableViewportSize();
        d1.width = jtTableCommandeHistory.getPreferredSize().width;
        jtTableCommandeHistory.setPreferredScrollableViewportSize(d1);

        JScrollPane jspStock1 = new JScrollPane(jtTableCommandeHistory);
        jspStock1.setPreferredSize(new Dimension(700, 700));


        jpTableOrder.add(jspStock1);
        jpRight.add(jpTableOrder, gbcRight);

        /**
         * Attention ne pas oublier ce bouton
         */
        jbPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel jlPrintTitle = new JLabel("Stock");
                //setFontTitlePrint(jlPrintTitle);
                String sAdditionalTest = "";
                new PrintPDF(jtTableStock, jlPrintTitle, sAdditionalTest);
            }
        });

        jbOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Vector<Vector<Object>> vOrder = new Vector<>();
                final String NO_DATA_IN_CELLS = new String("Impossible de créer une nouvelle commande car toutes les commandes sont à 0!!");

                boolean dataOK = true;

                for(int i = 0; i < jtTableStock.getRowCount(); ++i){
                    //double temp = (double) jtTableStock.getValueAt(i, COLUMN_QUANTITY);

                    double temp;
                    if(jtTableStock.getValueAt(i, COLUMN_QUANTITY) == null){
                        temp = 0;
                    }else{
                        temp = (double) jtTableStock.getValueAt(i, COLUMN_QUANTITY);
                    }

                    if(stcStockTabController.isNumericPositiveDouble(temp)){
                        mmtStock.setCellStatus(CellStatus.EMPTY, i, COLUMN_QUANTITY);
                        Stock sStock = new Stock(alStock.get(i));
                        sStock.setCommande((double)jtTableStock.getValueAt(i, COLUMN_QUANTITY));
                        vOrder.add(sStock.toVectorForOrder());
                    }else if(stcStockTabController.isNumericAndBelowZero(temp)){
                        jtTableStock.getColumnModel().getColumn(COLUMN_QUANTITY).setCellRenderer(mrRendererStock);
                        mmtStock.setCellStatus(CellStatus.RED, i, COLUMN_QUANTITY);
                        dataOK = false;
                    }
                }
                if(dataOK && !vOrder.isEmpty()){
                    new NewOrderController(vOrder, mmtCommandeHistory);
                }else if(dataOK && vOrder.isEmpty()){
                    new InformationController(NO_DATA_IN_CELLS);
                }

            }
        });

        jbAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<Object>> vAdd = new Vector<>();

                for(Stock sStock : alStock){
                    vAdd.add(sStock.toVectorAddDel());
                }

                new AddStockController(vAdd, jtTableStock);
            }
        });

        jbDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<Object>> vDelete = new Vector<>();

                for(Stock sStock : alStock){
                    vDelete.add(sStock.toVectorAddDel());
                }

                new DeleteStockController(vDelete, jtTableStock);
            }
        });

        jbReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int COLUMN_SELECTED = 5;
                final int DEFAULT_NUMBER_QUANTITY = 0;
                for(int i = 0; i < jtTableStock.getRowCount(); ++i){
                    jtTableStock.setValueAt(new Double(DEFAULT_NUMBER_QUANTITY), i, COLUMN_SELECTED);
                }
            }
        });

        /**************************/

        jbSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                Vector<Vector<Object>> vCommandeHistory = new Vector<>();
                Date dStartDate = sdmModelStartDate.getValue();
                Date dEndDate = sdmModelEndDate.getValue();
                Statut sStatus = (Statut)jcbOrderStatus.getSelectedItem();
                int total = 0;
                boolean dateOK = true;

                if(dStartDate != null){
                    jdpriStartDatePicker.getJFormattedTextField().setBackground(Color.WHITE);
                    total += 1;
                }

                if(dEndDate != null){
                    jdpriEndDatePicker.getJFormattedTextField().setBackground(Color.WHITE);
                    total += 2;
                }

                if(!sStatus.equals(Statut.TOUS)){
                    total += 4;
                }

                switch (total){
                    case 0:
                        alCommandeHistory = stcStockTabController.getAllCommandeHistory();
                        break;
                    case 1:
                        jdpriEndDatePicker.getJFormattedTextField().setBackground(Color.RED);
                        dateOK = false;
                        break;
                    case 2:
                        jdpriStartDatePicker.getJFormattedTextField().setBackground(Color.RED);
                        dateOK = false;
                        break;
                    case 3:
                        if(dStartDate.getTime() <= dEndDate.getTime()){
                            if(isDate(dStartDate) && isDate(dEndDate)){
                                alCommandeHistory = stcStockTabController.getOrderByDate(dStartDate, dEndDate);
                            }
                        }else{
                            jdpriStartDatePicker.getJFormattedTextField().setBackground(Color.RED);
                            jdpriEndDatePicker.getJFormattedTextField().setBackground(Color.RED);
                            dateOK = false;
                        }
                        break;
                    case 4:
                        alCommandeHistory = stcStockTabController.getOrderByStatus(sStatus);
                        break;
                    case 5:
                        jdpriEndDatePicker.getJFormattedTextField().setBackground(Color.RED);
                        dateOK = false;
                        break;
                    case 6:
                        jdpriEndDatePicker.getJFormattedTextField().setBackground(Color.RED);
                        dateOK = false;
                        break;
                    case 7:
                        if(dStartDate.getTime() <= dEndDate.getTime()){
                            if(isDate(dStartDate) && isDate(dEndDate)){
                                alCommandeHistory = stcStockTabController.getOrderByStatusAndDate(sStatus, dStartDate, dEndDate);
                            }
                        }else{
                            jdpriStartDatePicker.getJFormattedTextField().setBackground(Color.RED);
                            jdpriEndDatePicker.getJFormattedTextField().setBackground(Color.RED);
                            dateOK = false;
                        }
                        break;
                    default:
                }

                if(dateOK){
                    for(Commande cHistory : alCommandeHistory){
                        vCommandeHistory.add(cHistory.toVector());
                    }
/*
                    for(int i = 0; i < vCommandeHistory.size(); ++i){
                        for(int j = 0; j < vCommandeHistory.elementAt(i).size(); ++j){
                            System.out.println(vCommandeHistory.elementAt(i).elementAt(j));
                        }

                    }
                    */

                    int rowCount = jtTableCommandeHistory.getRowCount();
                    //jpRight.remove(jtTableCommandeHistory);
                    for(int i = rowCount - 1; i >= 0; --i){
                        mmtCommandeHistory.removeRow(i);
                    }

                    for(int i = 0; i < vCommandeHistory.size(); ++i){
                        mmtCommandeHistory.addRow(vCommandeHistory.elementAt(i));
                    }
                }
            }
        });

        configFrame(getJfFrame(), this);

    }

    public boolean isDate(Date date) {
        return Validate.isDate(date);
    }

    public MyModelTable getMmtCommandeHistory(){
        return mmtCommandeHistory;
    }

    public void setMmtCommandeHistory (MyModelTable mmtCommandeHistory){
        this.mmtCommandeHistory = mmtCommandeHistory;
    }

}
