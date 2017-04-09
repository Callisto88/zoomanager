package View.Stock;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andre on 10.03.2017.
 */
public class StockTab extends JPanel {
    private static String windowTitle = new String("Stock");

    public StockTab(){

        JFrame f = new JFrame(windowTitle);

        JPanel jpMainPanel = new JPanel();
        GridBagLayout gblMainLayout = new GridBagLayout();
        jpMainPanel.setLayout(gblMainLayout);

        JLabel jlStock = new JLabel("Liste d'aliment en stock");
        jlStock.setFont(new Font("test", Font.PLAIN, 20));

        JLabel jlLowStock = new JLabel("Liste des aliment en dessous du minimum requis");
        jlLowStock.setFont(new Font("test", Font.PLAIN, 20));

        GridBagConstraints gbcMainPanel = new GridBagConstraints();
        gbcMainPanel.fill = GridBagConstraints.VERTICAL;
        gbcMainPanel.gridx = 0;
        gbcMainPanel.anchor = GridBagConstraints.CENTER;
        gbcMainPanel.gridy = 0;
        gbcMainPanel.insets = new Insets(0,15,0,0);
        jpMainPanel.add(jlStock,gbcMainPanel);

        gbcMainPanel.gridx = 1;
        gbcMainPanel.gridy = 0;
        jpMainPanel.add(jlLowStock, gbcMainPanel);

        /***************************************************************/

        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 1;
        JPanel jpButtonStock = new JPanel();
        jpMainPanel.add(jpButtonStock, gbcMainPanel);

        JButton jbCreateListOrder = new JButton("Créer liste");
        JButton jbReset = new JButton("Effacer");
        JButton jbLeave = new JButton("Quit");

        GridBagLayout gblBouton = new GridBagLayout();
        GridBagConstraints gbcBouton = new GridBagConstraints();
        gbcBouton.fill = GridBagConstraints.HORIZONTAL;
        gbcBouton.gridx = 0;
        gbcBouton.gridy = 0;
        jpButtonStock.add(jbCreateListOrder, gbcBouton);

        gbcBouton.gridx = 1;
        gbcBouton.gridy = 0;
        jpButtonStock.add(jbReset, gbcBouton);

        gbcBouton.gridx = 2;
        gbcBouton.gridy = 0;
        jpButtonStock.add(jbLeave, gbcBouton);

        /**************************************************************/

        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 2;
        JPanel jpTableStock = new JPanel();
        jpMainPanel.add(jpTableStock, gbcMainPanel);
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
        gbcStock.fill = GridBagConstraints.HORIZONTAL;
        gbcStock.gridx = 0;
        gbcStock.anchor = GridBagConstraints.FIRST_LINE_START;
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

        /******************************************************************/

        //jpMainPanel.setForeground(Color.GREEN);

        f.setContentPane(jpMainPanel);
        f.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        f.getContentPane().add(this);
        f.pack();
        f.setVisible(true);
        //f.setLocation((dim.width - f.getContentPane().getWidth())/2, (dim.height - f.getContentPane().getHeight())/2);

    }
}
