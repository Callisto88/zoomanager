package View.Stock;

import Controller.Stock.OrderContentController;
import Model.Contenu_Commande;
import View.GenericWindow;
import View.MyModelTable;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by Miguel on 07/05/2017.
 */
public class OrderContent extends GenericWindow{
    private static String[] COLUMN_ORDER_CONTENT_NAME = {"ID", "Description", "Quantité"};
    Vector<Vector<Object>> vOrderContent;

    public OrderContent(OrderContentController occOrderContentController, Vector<Vector<Object>> vOrderContent){
        super("Contenu Commande");
        this.vOrderContent = vOrderContent;
        //occOrderContentController.getOrderContent();

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


        JPanel jpTableOrderContent = new JPanel();
        gbcOrderContent.gridx = 0;
        gbcOrderContent.gridy = 1;
        jpOrderContent.add(jpTableOrderContent, gbcOrderContent);

        JTable jtTableOrderContent = new JTable(new MyModelTable(vOrderContent, COLUMN_ORDER_CONTENT_NAME));

        Dimension d = jtTableOrderContent.getPreferredScrollableViewportSize();
        d.width = jtTableOrderContent.getPreferredSize().width;
        jtTableOrderContent.setPreferredScrollableViewportSize(d);

        JScrollPane jspAdd = new JScrollPane(jtTableOrderContent);
        jspAdd.setPreferredSize(new Dimension(700, 700));

        jpTableOrderContent.add(jspAdd);
        jpOrderContent.add(jpTableOrderContent, gbcOrderContent);
    }
}
