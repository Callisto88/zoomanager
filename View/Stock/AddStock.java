package View.Stock;

import Controller.Error.ErrorController;
import Controller.Information.InformationController;
import Controller.Stock.AddStockController;
import View.GenericWindow;
import View.MyModelTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Vector;

/**
 *
 * Cette classe est la classe de l'interface d'ajout d'aliment et autres en stock.
 *
 * @author M.Silva
 *
 * @version 1.0
 *
 * @date    17.04.2017
 *
 */

public class AddStock extends GenericWindow{
    private static String[] COLUMN_ADD_NAME = {"ID", "Description", "Quantité", "Unite"};
    private static boolean[] COLUMN_STOCK_EDITABLE = {false, false, true, false};
    private static String S_ERROR_MESSAGE = "Une erreur s'est produite et l'ajout des aliments dans la base de donnée n'a pas pu être effectuée";
    private static String S_INFORMATION_MESSAGE = "L'ajout s'est bien réalisé";
    private Vector<Vector<Object>> vAdd;

    public AddStock(AddStockController ascAddStockController, Vector<Vector<Object>> vAdd){
        super("Ajout de Stock");
        this.vAdd = vAdd;


        GridBagLayout gblAddStock = new GridBagLayout();
        GridBagConstraints gbcAddStock = new GridBagConstraints();
        gbcAddStock.insets = new Insets(15, 0, 15, 0);

        JPanel jpAddStock = new JPanel();
        jpAddStock.setLayout(gblAddStock);
        jpMainPanel.add(jpAddStock);

        JPanel jpTitle = new JPanel();

        JLabel jlTitle = new JLabel("Ajout de Stock");
        setTitleConfig(jlTitle);
        jpTitle.add(jlTitle);

        gbcAddStock.gridx = 0;
        gbcAddStock.gridy = 0;
        jpAddStock.add(jpTitle, gbcAddStock);



        JPanel jpTableAdd = new JPanel();
        gbcAddStock.gridx = 0;
        gbcAddStock.gridy = 1;
        jpAddStock.add(jpTableAdd, gbcAddStock);

        MyModelTable mt = new MyModelTable(this.vAdd, COLUMN_ADD_NAME, COLUMN_STOCK_EDITABLE);
        JTable jtTableAdd = new JTable(mt);

        for(int i = 0; i < 5; ++i) {
            System.out.println(vAdd.elementAt(i).elementAt(2));
        }
        // setTableConfig(jtTableAdd);

        Dimension d = jtTableAdd.getPreferredScrollableViewportSize();
        d.width = jtTableAdd.getPreferredSize().width;
        jtTableAdd.setPreferredScrollableViewportSize(d);

        JScrollPane jspAdd = new JScrollPane(jtTableAdd);
        jspAdd.setPreferredSize(new Dimension(700, 700));

        jpTableAdd.add(jspAdd);
        jpAddStock.add(jpTableAdd, gbcAddStock);


        JPanel jpBottomLabel = new JPanel();
        JLabel jlQuestion = new JLabel(" ");
        jpBottomLabel.add(jlQuestion);

        gbcAddStock.gridx = 0;
        gbcAddStock.gridy = 2;
        jpAddStock.add(jpBottomLabel, gbcAddStock);


        JPanel jpBottomButtons = new JPanel();
        JButton jbAddButton = new JButton("Ajouter");
        setButtonConfigMedium(jbAddButton);
        JButton jbConfirm = new JButton("Confirmer");
        jbConfirm.setVisible(false);
        setButtonConfig(jbConfirm);
        JButton jbCancel = new JButton("Annuler");
        jbCancel.setVisible(false);
        setButtonConfig(jbCancel);
        jpBottomButtons.add(jbConfirm);
        jpBottomButtons.add(jbAddButton);
        jpBottomButtons.add(jbCancel);

        gbcAddStock.gridx = 0;
        gbcAddStock.gridy = 3;
        jpAddStock.add(jpBottomButtons, gbcAddStock);


        jbAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlQuestion.setText("Voulez-vous vraiment ajouter les aliments dans le stock?");
                jbAddButton.setVisible(false);
                jbConfirm.setVisible(true);
                jbCancel.setVisible(true);
            }
        });

        jbConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ascAddStockController.addStock();
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
                jbAddButton.setVisible(true);
                jbConfirm.setVisible(false);
                jbCancel.setVisible(false);
            }
        });


        configFrame(getJfFrame(), this);
    }
}
