package View.Stock;

import Controller.Error.ErrorController;
import Controller.Information.InformationController;
import Controller.Stock.DeleteStockController;
import View.GenericWindow;
import View.MyModelTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Vector;

/**
 * Created by Miguel on 04/05/2017.
 */
public class DeleteStock extends GenericWindow{
    private static String[] COLUMN_ADD_NAME = {"ID", "Description", "Quantité", "Unite"};
    private static boolean[] COLUMN_STOCK_EDITABLE = {false, false, true, false};
    private static String S_ERROR_MESSAGE = "Une erreur s'est produite et la suppression des aliments dans la base de donnée n'a pas pu être effectuée";
    private static String S_INFORMATION_MESSAGE = "La suppression s'est bien réalisée";
    private Vector<Vector<Object>> vDelete;

    public DeleteStock(DeleteStockController ascDeleteStockController, Vector<Vector<Object>> vDelete) {
        super("Suppression de Stock");

        this.vDelete = vDelete;


        GridBagLayout gblDeleteStock = new GridBagLayout();
        GridBagConstraints gbcDeleteStock = new GridBagConstraints();

        JPanel jpDeleteStock = new JPanel();
        jpDeleteStock.setLayout(gblDeleteStock);
        jpMainPanel.add(jpDeleteStock);

        JPanel jpTitle = new JPanel();

        JLabel jlTitle = new JLabel("Suppression de Stock");
        setTitleConfig(jlTitle);
        jpTitle.add(jlTitle);

        gbcDeleteStock.gridx = 0;
        gbcDeleteStock.gridy = 0;
        jpDeleteStock.add(jpTitle, gbcDeleteStock);



        JPanel jpTableDelete = new JPanel();
        gbcDeleteStock.insets = new Insets(15, 5, 15, 5);
        gbcDeleteStock.gridx = 0;
        gbcDeleteStock.gridy = 1;
        jpDeleteStock.add(jpTableDelete, gbcDeleteStock);

        MyModelTable mt = new MyModelTable(this.vDelete, COLUMN_ADD_NAME, COLUMN_STOCK_EDITABLE);
        JTable jtTableDelete = new JTable(mt);
        // setTableConfig(jtTableAdd);

        Dimension d = jtTableDelete.getPreferredScrollableViewportSize();
        d.width = jtTableDelete.getPreferredSize().width;
        jtTableDelete.setPreferredScrollableViewportSize(d);

        JScrollPane jspDelete = new JScrollPane(jtTableDelete);
        jspDelete.setPreferredSize(new Dimension(700, 700));

        jpTableDelete.add(jspDelete);
        jpDeleteStock.add(jpTableDelete, gbcDeleteStock);


        JPanel jpBottomLabel = new JPanel();
        JLabel jlQuestion = new JLabel(" ");
        jpBottomLabel.add(jlQuestion);

        gbcDeleteStock.gridx = 0;
        gbcDeleteStock.gridy = 2;
        jpDeleteStock.add(jpBottomLabel, gbcDeleteStock);


        JPanel jpBottomButtons = new JPanel();
        JButton jbDeleteButton = new JButton("Supprimer");
        setButtonConfigMedium(jbDeleteButton);
        JButton jbConfirm = new JButton("Confirmer");
        jbConfirm.setVisible(false);
        setButtonConfig(jbConfirm);
        JButton jbCancel = new JButton("Annuler");
        jbCancel.setVisible(false);
        setButtonConfig(jbCancel);
        jpBottomButtons.add(jbConfirm);
        jpBottomButtons.add(jbDeleteButton);
        jpBottomButtons.add(jbCancel);

        gbcDeleteStock.gridx = 0;
        gbcDeleteStock.gridy = 3;
        jpDeleteStock.add(jpBottomButtons, gbcDeleteStock);

        jbDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlQuestion.setText("Voulez-vous vraiment supprimer les aliments dans le stock?");
                jbDeleteButton.setVisible(false);
                jbConfirm.setVisible(true);
                jbCancel.setVisible(true);
            }
        });

        jbConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ascDeleteStockController.deleteStock();
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
                jbDeleteButton.setVisible(true);
                jbConfirm.setVisible(false);
                jbCancel.setVisible(false);
            }
        });

        configFrame(getJfFrame(), this);
    }
}
