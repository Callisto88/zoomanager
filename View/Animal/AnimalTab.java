package View.Animal;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

/**
 * Created by Andre on 10.03.2017.
 */
public class AnimalTab extends GenericWindow {

    public AnimalTab(){
        super("Animal");

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

        JLabel jlStock = new JLabel("Liste des animaux");
        setTitleConfig(jlStock);
        jpLeftTest.add(jlStock);


        gbcLeft.fill = GridBagConstraints.CENTER;
        gbcLeft.gridx = 0;
        gbcLeft.anchor = GridBagConstraints.NORTH;
        gbcLeft.gridy = 0;
        gbcLeft.insets = new Insets(15,15,15,15);
        jpLeft.add(jpLeftTest,gbcLeft);

        jpRight.add(jlLowStock, BorderLayout.NORTH);

        JLabel test = new JLabel("Essai");
        jpRight.add(test, BorderLayout.WEST);


        /*
        gbcRight.gridx = 1;
        gbcRight.gridy = 0;
        jpRight.add(jlLowStock, gbcRight);
        *

        /***************************************************************/

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 1;

        JPanel jpButtonsAnimal = new JPanel();
        jpButtonsAnimal.setBackground(Color.cyan);
        jpLeft.add(jpButtonsAnimal, gbcLeft);

        JButton jbFilterSearch = new JButton("Filtrer/Rechercher");
        setButtonConfig(jbFilterSearch);

        JButton jbPrint = new JButton("Imprimer");
        setButtonConfig(jbPrint);

        JButton jbAddAnimal = new JButton("Ajouter");
        setButtonConfig(jbAddAnimal);


        GridBagLayout gblAnimalButtons = new GridBagLayout();
        jpButtonsAnimal.setLayout(gblAnimalButtons);
        GridBagConstraints gbcAnimalButtons = new GridBagConstraints();

       // gbcStockBouton.fill = GridBagConstraints.NORTH;
        //gbcStockBouton.anchor = GridBagConstraints.HORIZONTAL;
        gbcAnimalButtons.insets = new Insets(0,15,0,15);
        gbcAnimalButtons.gridx = 0;
        gbcAnimalButtons.gridy = 0;
        jpButtonsAnimal.add(jbFilterSearch, gbcAnimalButtons);

        gbcAnimalButtons.gridx = 1;
        gbcAnimalButtons.gridy = 0;
        jpButtonsAnimal.add(jbPrint, gbcAnimalButtons);

        gbcAnimalButtons.gridx = 2;
        gbcAnimalButtons.gridy = 0;
        jpButtonsAnimal.add(jbAddAnimal, gbcAnimalButtons);


        /**************************************************************/

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        gbcLeft.weighty = 20;


        /*
        JScrollBar jsbStock = new JScrollBar();
        JScrollPane jspStock = new JScrollPane();
        */

        String[] columnName = {"Nom", "Race", "Sexe", "Âge", "boutons modification, détails"};

        Object[][] data = {
                {"Kathy", "Lapin", 1, 5, new ImageIcon("loupe.jpg") },
                {"John", "Doe", 0, 3, new ImageIcon("loupe.jpg") },
                {"John", "Doe", 0, 3, new ImageIcon("loupe.jpg") },
                {"John", "Doe", 0, 3, new ImageIcon("loupe.jpg") },
                {"John", "Doe", 0, 3, new ImageIcon("loupe.jpg") },
                {"John", "Doe", 0, 3, new ImageIcon("loupe.jpg") },
                {"John", "Doe", 0, 3, new ImageIcon("loupe.jpg") },
                {"John", "Doe", 0, 3, new ImageIcon("loupe.jpg") },
                {"John", "Doe", 0, 3, new ImageIcon("loupe.jpg") },
                {"John", "Doe", 0, 3, new ImageIcon("loupe.jpg") },
                {"John", "Doe", 0, 3, new ImageIcon("loupe.jpg") },
                {"John", "Doe", 0, 3, new ImageIcon("loupe.jpg") },
                {"John", "Doe", 0, 3, new ImageIcon("loupe.jpg") },
                {"John", "Doe", 0, 3, new ImageIcon("loupe.jpg") },
                {"John", "Doe", 0, 3, new ImageIcon("loupe.jpg") },
                {"John", "Doe", 0, 3, new ImageIcon("loupe.jpg") },
                {"John", "Doe", 0, 3, new ImageIcon("loupe.jpg") }
        };

        JPanel jpTableAnimal = new JPanel();
        jpTableAnimal.setBackground(Color.ORANGE);
        jpTableAnimal.setPreferredSize(new Dimension(800, 800));
        //jpTableStock.setMinimumSize(new Dimension(400,800));
        JTable jtTable = new JTable(data, columnName){
            /*détection automatique des types de données
             *de toutes les colonnes
             */
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };
        Dimension d = jtTable.getPreferredScrollableViewportSize();

        d.width = jtTable.getPreferredSize().width;

        jtTable.setPreferredScrollableViewportSize(d);
        //resizeColumnWidth(jtTable);
        //jtTable.setMinimumSize(new Dimension(400,400));
        //jtTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        //jtTable.setPreferredSize(new Dimension(400,400));
        //jtTable.sizeColumnsToFit(50);
        JScrollPane jspAnimal = new JScrollPane(jtTable);
        jspAnimal.setPreferredSize(new Dimension(800, 800));


        jpTableAnimal.add(jspAnimal);
        jpLeft.add(jpTableAnimal, gbcLeft);



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
