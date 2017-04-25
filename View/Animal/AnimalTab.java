package View.Animal;

import Model.*;
import View.GenericWindow;
import View.MyModelTable;
import Controller.Animal.*;
import com.jidesoft.swing.AutoCompletion;


import javax.swing.*;
import java.awt.*;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import static Model.Tools.DateSQL.calculateAge;


/**
 * Created by Andre on 10.03.2017.
 */
public class AnimalTab extends GenericWindow {
    private String[] columnName = {"Nom", "Race", "Sexe", "Age", "Enclos"};

    public AnimalTab(AnimalController atAnimalController) {
        super("Animaux");

        GridBagLayout gblLeft = new GridBagLayout();
        GridBagConstraints gbcLeft = new GridBagConstraints();

        JPanel jpLeft = new JPanel();
        jpLeft.setLayout(gblLeft);

        JPanel jpLeftTitle = new JPanel();

        jpMainPanel.add(jpLeft);

        JLabel jlStock = new JLabel("Liste des animaux");
        setTitleConfig(jlStock);
        jpLeftTitle.add(jlStock);

        gbcLeft.fill = GridBagConstraints.CENTER;
        gbcLeft.gridx = 0;
        gbcLeft.anchor = GridBagConstraints.NORTH;
        gbcLeft.gridy = 0;
        gbcLeft.insets = new Insets(5, 5, 5, 5);
        jpLeft.add(jpLeftTitle, gbcLeft);

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 1;

        JPanel jpButtonAnimal = new JPanel();
        jpLeft.add(jpButtonAnimal, gbcLeft);

        JButton jbPrint = new JButton("Imprimer");
        setButtonConfig(jbPrint);


        GridBagLayout gblButtonAnimal = new GridBagLayout();
        jpButtonAnimal.setLayout(gblButtonAnimal);
        GridBagConstraints gbcButtonAnimal = new GridBagConstraints();

        gbcButtonAnimal.insets = new Insets(5, 5, 5, 5);
        gbcButtonAnimal.gridx = 0;
        gbcButtonAnimal.gridy = 0;
        jpButtonAnimal.add(jbPrint, gbcButtonAnimal);


        /**************************************************************/

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        gbcLeft.weighty = 10;


        JPanel jpTableAnimal = new JPanel();
        jpTableAnimal.setPreferredSize(new Dimension(820, 720));


        ArrayList<Animal> animauxDB = atAnimalController.getAllAnimal();
        ArrayList<Enclos> enclosDB = atAnimalController.getAllEnclos();

        Vector<Vector<Object>> vAnimal = atAnimalController.animauxToVector(animauxDB, enclosDB);


        JTable jtTable = new JTable(new MyModelTable(vAnimal, columnName));

        Dimension d = jtTable.getPreferredScrollableViewportSize();
        d.width = jtTable.getPreferredSize().width;
        jtTable.setPreferredScrollableViewportSize(d);

        JScrollPane jspAnimal = new JScrollPane(jtTable);
        jspAnimal.setPreferredSize(new Dimension(820, 710));


        jpTableAnimal.add(jspAnimal);
        jpLeft.add(jpTableAnimal, gbcLeft);


        /***********************************************************************************
         * Partie droite de la fenêtre
         */

        GridBagLayout gblRight = new GridBagLayout();
        GridBagConstraints gbcRight = new GridBagConstraints();

        JPanel jpRight = new JPanel();
        jpRight.setLayout(gblRight);

        JPanel jpRightTitle = new JPanel();

        jpMainPanel.add(jpRight);

        JLabel jlDetAnimal = new JLabel("Détail animal");
        setTitleConfig(jlDetAnimal);
        jpRightTitle.add(jlDetAnimal);

        gbcRight.fill = GridBagConstraints.CENTER;
        gbcRight.gridx = 0;
        gbcRight.anchor = GridBagConstraints.NORTH;
        gbcRight.gridy = 0;
        jpRight.add(jpRightTitle, gbcRight);

        // deuxième ligne qui contient les boutons modifier et ajouter
        gbcRight.gridx = 0;
        gbcRight.gridy = 1;

        JPanel jpDetAnimal = new JPanel();
        jpRight.add(jpDetAnimal, gbcRight);

        JButton jbMod = new JButton("Modifier");
        setButtonConfig(jbMod);

        JButton jbAdd = new JButton("Ajouter");
        setButtonConfig(jbAdd);

        GridBagLayout gblDetAnimalButton = new GridBagLayout();
        jpDetAnimal.setLayout(gblDetAnimalButton);
        GridBagConstraints gbcDetAnimalButton = new GridBagConstraints();

        gbcDetAnimalButton.insets = new Insets(5, 20, 5, 20);
        gbcDetAnimalButton.gridx = 0;
        gbcDetAnimalButton.gridy = 0;
        jpDetAnimal.add(jbMod, gbcDetAnimalButton);

        gbcDetAnimalButton.gridx = 1;
        gbcDetAnimalButton.gridy = 0;
        jpDetAnimal.add(jbAdd, gbcDetAnimalButton);


        // 3 e ligne (et +) : formulaire avec les détails d'un animal
        JLabel jlEnclos = new JLabel("Enclos :");
        gbcDetAnimalButton.gridx = 0;
        gbcDetAnimalButton.gridy = 1;
        jpDetAnimal.add(jlEnclos, gbcDetAnimalButton);


        String[] sEnclos = new String[enclosDB.size()];
        for (int i = 0; i < enclosDB.size(); i++){
            sEnclos[i] = enclosDB.get(i).getNom();
        }

        JComboBox jcEnclos = new JComboBox(sEnclos);
        //jcEnclos.setEditable(true);
        AutoCompletion ac = new AutoCompletion(jcEnclos);
        //ac.setStrict(false);
        //jcEnclos.setSelectedIndex(2);


        gbcDetAnimalButton.gridx = 1;
        gbcDetAnimalButton.gridy = 1;
        jpDetAnimal.add(jcEnclos, gbcDetAnimalButton);


        configFrame(getJfFrame(), this);

    }
}
