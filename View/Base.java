package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andre on 09.04.2017.
 */
public class Base extends GenericWindow{

    public Base(String windowTitle, String titleLeft, String titleRight, String[] labels, String[] buttonsTitle) {
        super(windowTitle);

        GridBagLayout gblLeft = new GridBagLayout();
        GridBagConstraints gbcLeft = new GridBagConstraints();

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

        /**
         * Spécifie le titre dans la fenêtre de droite (tableau)
         */
        JLabel jlLeft = new JLabel(titleLeft);
        setTitleConfig(jlLeft);
        jpLeftTest.add(jlLeft);

        /**
         * Spécifie le titre de la fenêtre d'à côté
         */
        JLabel jlRight = new JLabel(titleRight);
        setTitleConfig(jlRight);
        jpRight.add(jlRight, BorderLayout.NORTH);

        gbcLeft.fill = GridBagConstraints.CENTER;
        gbcLeft.gridx = 0;
        gbcLeft.anchor = GridBagConstraints.NORTH;
        gbcLeft.gridy = 0;
        gbcLeft.insets = new Insets(15, 15, 15, 15);
        jpLeft.add(jpLeftTest, gbcLeft);

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 1;

        JPanel jpButtonBase = new JPanel();
        jpButtonBase.setBackground(Color.cyan);
        jpLeft.add(jpButtonBase, gbcLeft);

        /**
         * Permet de crée les boutons avec les labels désiré
         */
        JButton[] buttons = new JButton[buttonsTitle.length];
        for(int i = 0; i < buttonsTitle.length; ++i){
            System.out.println(buttonsTitle[i]);
            buttons[i] = new JButton(buttonsTitle[i]);
            setButtonConfig(buttons[i]);
        }

        /**
         * Permet d'ajouter les boutons crée à notre pannel
         */
        GridBagLayout gblBaseBoutton = new GridBagLayout();
        jpButtonBase.setLayout(gblBaseBoutton);
        GridBagConstraints gbcBaseBouton = new GridBagConstraints();
        gbcBaseBouton.insets = new Insets(0, 15, 0, 15);
        gbcBaseBouton.gridx = 0;
        gbcBaseBouton.gridy = 0;
        for(int i = 0; i < buttonsTitle.length; ++i){
            gbcBaseBouton.gridx = i;
            jpButtonBase.add(buttons[i], gbcBaseBouton);
        }

        /**************************************************************/

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        gbcLeft.weighty = 20;

        String[] columnName = labels;

        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
        };
/*
        JPanel jpTableStock = new JPanel();
        jpTableStock.setBackground(Color.ORANGE);
        jpTableStock.setPreferredSize(new Dimension(800, 800));
        //jpTableStock.setMinimumSize(new Dimension(400,800));
        JTable jtTable = new JTable(data, columnName);
        //jtTable.setPreferredSize(JTable);
        Dimension d = jtTable.getPreferredScrollableViewportSize();

        d.width = jtTable.getPreferredSize().width;

        jtTable.setPreferredScrollableViewportSize(d);
        JScrollPane jspStock = new JScrollPane(jtTable);
        jspStock.setPreferredSize(new Dimension(700, 700));

        jpTableStock.add(jspStock);
        jpLeft.add(jpTableStock, gbcLeft);
*/
        configFrame(getJfFrame(), this);
    }
}
