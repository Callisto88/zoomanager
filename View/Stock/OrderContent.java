package View.Stock;

import Controller.Error.ErrorController;
import Controller.Information.InformationController;
import Controller.Stock.OrderContentController;
import Model.Commande;
import Model.Contenu_Commande;
import Model.Statut;
import View.GenericWindow;
import View.MyModelTable;
import View.PrintPDF;
import com.jidesoft.swing.JideScrollPaneLayout;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by Miguel on 07/05/2017.
 */
public class OrderContent extends GenericWindow{
    private final String[] COLUMN_ORDER_CONTENT_NAME = {"ID", "Description", "Quantité", "Unite"};
    private final String[] NO_HEADERS = {"", ""};
    private final Statut[] ORDER_STATUS = {Statut.CREEE, Statut.EN_COURS, Statut.TERMINEE, Statut.ANNULEE, Statut.REMBOURSEE};
    private final String S_INFORMATION_MESSAGE = "Le changement de statut s'est bien réalisé";
    private final String S_ERROR_MESSAGE = "Le changement de statut à échouer";
    private Vector<Vector<Object>> vOrderContent;
    private Vector<Vector<Object>> vOrderInfo;
    private JTable jtOrderInfo;
    private MyModelTable mmtOrderInfo;
    private TableRowSorter<MyModelTable> trsSorter;

    public OrderContent(OrderContentController occOrderContentController, Vector<Vector<Object>> vOrderContent){
        super("Contenu Commande");
        this.vOrderContent = vOrderContent;

        GridBagLayout gblOrderContent = new GridBagLayout();
        GridBagConstraints gbcOrderContent = new GridBagConstraints();
        gbcOrderContent.insets = new Insets(15, 0, 15, 0);

        JPanel jpOrderContent = new JPanel();
        jpOrderContent.setLayout(gblOrderContent);
        jpMainPanel.add(jpOrderContent);

        JPanel jpTitle = new JPanel();

        JLabel jlTitle = new JLabel("Contenu de la commande");
        setTitleConfig(jlTitle);
        jpTitle.add(jlTitle);

        gbcOrderContent.gridx = 0;
        gbcOrderContent.gridy = 0;
        jpOrderContent.add(jpTitle, gbcOrderContent);
/***********************************/

        JLabel jlID = new JLabel("ID:");
        JLabel jlDate = new JLabel("Date:");
        JLabel jlStatus = new JLabel("Statut:");
        JLabel jlIDData = new JLabel(String.valueOf(occOrderContentController.getOrderID()));
        JLabel jlDateData = new JLabel(String.valueOf(occOrderContentController.getDateOrder()));
        JLabel jlStatusData = new JLabel(String.valueOf(occOrderContentController.getStatusOrder()));

        vOrderInfo = new Vector<>();
        Vector<Object> vOrderInfo1= new Vector<>();
        Vector<Object> vOrderInfo2= new Vector<>();
        Vector<Object> vOrderInfo3= new Vector<>();

        vOrderInfo1.add(jlID.getText());
        vOrderInfo1.add(jlIDData.getText());
        vOrderInfo.add(vOrderInfo1);

        vOrderInfo2.add(jlDate.getText());
        vOrderInfo2.add(jlDateData.getText());
        vOrderInfo.add(vOrderInfo2);

        vOrderInfo3.add(jlStatus.getText());
        vOrderInfo3.add(jlStatusData.getText());
        vOrderInfo.add(vOrderInfo3);

        mmtOrderInfo = new MyModelTable(vOrderInfo, NO_HEADERS);
        jtOrderInfo = new JTable(mmtOrderInfo);
        jtOrderInfo.setPreferredSize(new Dimension(200,50));


        Dimension d = jtOrderInfo.getPreferredScrollableViewportSize();
        d.width = jtOrderInfo.getPreferredSize().width;
        jtOrderInfo.setPreferredScrollableViewportSize(d);

        JScrollPane jspOrderContent = new JScrollPane(jtOrderInfo);
        jspOrderContent.setPreferredSize(new Dimension(200, 58));

        JPanel jpOrderInfo = new JPanel();
        jpOrderInfo.add(jspOrderContent);


        gbcOrderContent.gridx = 0;
        gbcOrderContent.gridy = 1;
        jpOrderContent.add(jpOrderInfo, gbcOrderContent);

/*****************************************/
        JPanel jpChangeStatus = new JPanel();
        JButton jbChangeStatus = new JButton("Changer");
        JComboBox jcbChangeStatus = new JComboBox(ORDER_STATUS);
        JButton jbConfirm = new JButton("Confirmer");
        jbConfirm.setVisible(false);
        JButton jbDelete = new JButton("Annuler");
        jbDelete.setVisible(false);

        jpChangeStatus.add(jbConfirm);
        jpChangeStatus.add(jbChangeStatus);
        jpChangeStatus.add(jcbChangeStatus);
        jpChangeStatus.add(jbDelete);

        gbcOrderContent.gridx = 0;
        gbcOrderContent.gridy = 2;
        jpOrderContent.add(jpChangeStatus, gbcOrderContent);
/*****************************************/

        MyModelTable mmtOrderContentTable = new MyModelTable(vOrderContent, COLUMN_ORDER_CONTENT_NAME);
        JTable jtTableOrderContent = new JTable(mmtOrderContentTable);
        trsSorter = new TableRowSorter<>(mmtOrderContentTable);
        jtTableOrderContent.setRowSorter(trsSorter);

        Dimension dOrderContent = jtTableOrderContent.getPreferredScrollableViewportSize();
        dOrderContent.width = jtTableOrderContent.getPreferredSize().width;
        jtTableOrderContent.setPreferredScrollableViewportSize(dOrderContent);

        JScrollPane jspAdd = new JScrollPane(jtTableOrderContent);
        jspAdd.setPreferredSize(new Dimension(700, 500));

        JPanel jpTableOrderContent = new JPanel();
        jpTableOrderContent.add(jspAdd);
        jpOrderContent.add(jpTableOrderContent, gbcOrderContent);

        gbcOrderContent.gridx = 0;
        gbcOrderContent.gridy = 3;
        jpOrderContent.add(jpTableOrderContent, gbcOrderContent);

        /************************************************************/

        JButton jbPrint = new JButton("Imprimer");
        JPanel jpPrint = new JPanel();
        jpPrint.add(jbPrint);

        gbcOrderContent.gridx = 0;
        gbcOrderContent.gridy = 4;
        jpOrderContent.add(jpPrint, gbcOrderContent);

        /***************************************/

        jbChangeStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbChangeStatus.setVisible(false);
                jbConfirm.setVisible(true);
                jbDelete.setVisible(true);
                jcbChangeStatus.setVisible(false);
            }
        });

        jbConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int ROW_INDEX_INFO = 2;
                final int COLUMN_INDEX_INFO = 1;
                final int ROW_INDEX_ORDER_ID = 0;
                final int COLUMN_INDEX_ORDER_STATUS = 2;

                Statut sStatus = (Statut)jcbChangeStatus.getSelectedItem();
                MyModelTable mmtTemp = occOrderContentController.getMmtOrderContent();
                int sizeTableOrder = mmtTemp.getRowCount();
                int idOrder = occOrderContentController.getOrderID();


                Commande cOrder = new Commande(idOrder, occOrderContentController.getDateOrder(),sStatus);
                if(occOrderContentController.updateStatus(cOrder)){
                    for(int i = 0; i < sizeTableOrder; ++i){
                        if((int)mmtTemp.getValueAt(i, ROW_INDEX_ORDER_ID) == idOrder){
                            mmtTemp.setValueAt(sStatus, i, COLUMN_INDEX_ORDER_STATUS);
                        }
                    }
                    mmtOrderInfo.setValueAt(sStatus, ROW_INDEX_INFO, COLUMN_INDEX_INFO);
                    new InformationController(S_INFORMATION_MESSAGE);
                }else{
                    new ErrorController(S_ERROR_MESSAGE);
                }
            }
        });

        jbDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbChangeStatus.setVisible(true);
                jbConfirm.setVisible(false);
                jbDelete.setVisible(false);
                jcbChangeStatus.setVisible(true);
            }
        });

        jbPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel jlPrintTitle = new JLabel("Contenu Commande");
                setFontTitlePrint(jlPrintTitle);
                String sAdditionalTest = "";
                new PrintPDF(jtTableOrderContent, jtOrderInfo, jlPrintTitle, sAdditionalTest);
            }
        });

        configFrame(getJfFrame(), this);
    }
}
