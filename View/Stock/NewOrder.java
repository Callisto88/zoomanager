package View.Stock;

import Controller.Error.ErrorController;
import Controller.Information.InformationController;
import Controller.Stock.NewOrderController;
import View.GenericWindow;
import View.MyModelTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Vector;

/**
 * Created by Miguel on 05/05/2017.
 */
public class NewOrder extends GenericWindow{
    private final  String[] COLUMN_ORDER_NAME = {"ID", "Description", "Commande", "Unite"};
    private final String S_ERROR_MESSAGE = "Une erreur s'est produite et l'ajout des aliments dans la base de donnée n'a pas pu être effectuée";
    private final String S_INFORMATION_MESSAGE = "L'ajout s'est bien réalisé";

    Vector<Vector<Object>> vOrder;

    public NewOrder(NewOrderController nocNewOrderController, Vector<Vector<Object>> vOrder) {
        super("Nouvelle Commande");

        this.vOrder = vOrder;

        GridBagLayout gblNewOrder = new GridBagLayout();
        GridBagConstraints gbcNewOrder = new GridBagConstraints();

        JPanel jpNewOrder = new JPanel();
        jpNewOrder.setLayout(gblNewOrder);
        jpMainPanel.add(jpNewOrder);

        JPanel jpTitle = new JPanel();

        JLabel jlTitle = new JLabel("Nouvelle Commande");
        setTitleConfig(jlTitle);
        jpTitle.add(jlTitle);

        gbcNewOrder.gridx = 0;
        gbcNewOrder.gridy = 0;
        jpNewOrder.add(jpTitle, gbcNewOrder);



        JPanel jpTableDelete = new JPanel();
        gbcNewOrder.insets = new Insets(15, 5, 15, 5);
        gbcNewOrder.gridx = 0;
        gbcNewOrder.gridy = 1;
        jpNewOrder.add(jpTableDelete, gbcNewOrder);

        JTable jtTableOrder = new JTable(new MyModelTable(vOrder, COLUMN_ORDER_NAME));
        // setTableConfig(jtTableAdd);

        Dimension d = jtTableOrder.getPreferredScrollableViewportSize();
        d.width = jtTableOrder.getPreferredSize().width;
        jtTableOrder.setPreferredScrollableViewportSize(d);

        JScrollPane jspNewOrder = new JScrollPane(jtTableOrder);
        jspNewOrder.setPreferredSize(new Dimension(700, 700));

        jpTableDelete.add(jspNewOrder);
        jpNewOrder.add(jpTableDelete, gbcNewOrder);


        JPanel jpBottomLabel = new JPanel();
        JLabel jlQuestion = new JLabel(" ");
        jpBottomLabel.add(jlQuestion);

        gbcNewOrder.gridx = 0;
        gbcNewOrder.gridy = 2;
        jpNewOrder.add(jpBottomLabel, gbcNewOrder);


        JPanel jpBottomButtons = new JPanel();
        JButton jbNewOrderButton = new JButton("Commander");
        setButtonConfigMedium(jbNewOrderButton);
        JButton jbConfirm = new JButton("Confirmer");
        jbConfirm.setVisible(false);
        setButtonConfig(jbConfirm);
        JButton jbCancel = new JButton("Annuler");
        jbCancel.setVisible(false);
        setButtonConfig(jbCancel);
        jpBottomButtons.add(jbConfirm);
        jpBottomButtons.add(jbNewOrderButton);
        jpBottomButtons.add(jbCancel);

        gbcNewOrder.gridx = 0;
        gbcNewOrder.gridy = 3;
        jpNewOrder.add(jpBottomButtons, gbcNewOrder);

        jbNewOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlQuestion.setText("Voulez-vous vraiment créer une nouvelle commande?");
                jbNewOrderButton.setVisible(false);
                jbConfirm.setVisible(true);
                jbCancel.setVisible(true);
            }
        });

        jbConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    nocNewOrderController.newOrder();
                    new InformationController(S_INFORMATION_MESSAGE);
                    getJfFrame().dispatchEvent(new WindowEvent(getJfFrame(), WindowEvent.WINDOW_CLOSING));
                }catch (Exception ex){
                    new ErrorController(S_ERROR_MESSAGE);
                }
            }
        });

        jbCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlQuestion.setText(" ");
                jbNewOrderButton.setVisible(true);
                jbConfirm.setVisible(false);
                jbCancel.setVisible(false);
            }
        });

        configFrame(getJfFrame(), this);
    }

}
