package View.Animal;

import Model.*;
import View.GenericWindow;
import View.MyModelTable;
import Controller.Animal.*;
import com.jidesoft.swing.AutoCompletion;


import javax.swing.*;
import java.awt.*;


import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import static Model.Tools.DateSQL.calculateAge;


/**
 * Created by Andre on 10.03.2017.
 */
public class AnimalTab extends GenericWindow {
    private String[] columnName = {"Nom", "Nom commun", "Race", "Sexe", "Age", "Enclos"};

    private static String S_DETANIMAL = "Détails d'un animal";
    private static String S_MODANIMAL = "Modification d'un animal";
    private static String S_ADDANIMAL = "Ajout d'un animal";


    protected int mode = 0;
    protected int selectedRow;

    protected JPanel jpDetAnimal;

    public AnimalTab(AnimalController atAnimalController) {
        super("Animaux");

        GridBagLayout gblLeft = new GridBagLayout();
        GridBagConstraints gbcLeft = new GridBagConstraints();

        JPanel jpLeft = new JPanel();
        jpLeft.setLayout(gblLeft);

        JPanel jpLeftTitle = new JPanel();

        jpMainPanel.add(jpLeft);

        JLabel jlAnimaux = new JLabel("Liste des animaux");
        setTitleConfig(jlAnimaux);
        jpLeftTitle.add(jlAnimaux);

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
        jbPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                print();
            }
        });
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


        MouseListener tableMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Récupérer la ligne séléctionnée
                int nbSelectedRows = jtTable.getSelectedRowCount();
                if(nbSelectedRows == 1){
                    selectedRow = jtTable.getSelectedRow();
                }
                else if(nbSelectedRows > 1){
                    selectedRow = jtTable.getSelectedRows()[0];
                }
                else{
                    selectedRow = 0;
                }
            }
        };
        jtTable.addMouseListener(tableMouseListener);

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


        /****************************************
         * Détails d'un animal
         */
        JLabel jlDetAnimal = new JLabel("Détail d'un animal");

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

        // JPanel pour les boutons modifier, ajouter et supprimer
        JPanel jpBoutons = new JPanel();

        GridBagLayout gblDetAnimalButton = new GridBagLayout();
        jpBoutons.setLayout(gblDetAnimalButton);
        GridBagConstraints gbcDetAnimalButton = new GridBagConstraints();

        JButton jbMod = new JButton("Modifier");
        JButton jbAdd = new JButton("Ajouter");
        JButton jbDel = new JButton("Supprimer");

        jbMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (mode) {
                    case 0:
                        jlDetAnimal.setText(S_MODANIMAL);
                        jbMod.setText("Détails");
                        mode = 1;
                        System.out.println(selectedRow);
                        setModView();
                        break;
                    case 1:
                        jlDetAnimal.setText(S_DETANIMAL);
                        jbMod.setText("Modifier");
                        mode = 0;
                        setDetView();
                        break;
                    case 2:
                        jlDetAnimal.setText(S_MODANIMAL);
                        jbMod.setText("Détails");
                        jbAdd.setText("Ajouter");
                        mode = 1;
                        setModView();
                        break;
                }
            }
        });
        setButtonConfig(jbMod);

        jbAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (mode) {
                    case 0:
                        jlDetAnimal.setText(S_ADDANIMAL);
                        jbAdd.setText("Détails");
                        mode = 2;
                        setAddView();
                        break;
                    case 1:
                        jlDetAnimal.setText(S_ADDANIMAL);
                        jbAdd.setText("Détails");
                        jbMod.setText("Modifier");
                        mode = 2;
                        setAddView();
                        break;
                    case 2:
                        jlDetAnimal.setText(S_DETANIMAL);
                        jbAdd.setText("Ajouter");
                        mode = 0;
                        setDetView();
                        break;
                }
            }
        });
        setButtonConfig(jbAdd);

        jbDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(jpMainPanel, "Voulez-vous vraiment supprimer cet animal ?",
                        "Confirmer la suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
                if (n == 0) {
                    //System.out.println(animauxDB.get(selectedRow).getId() + animauxDB.get(selectedRow).getNom());
                    atAnimalController.delAnimal(animauxDB.get(selectedRow));
                    jtTable.remove(selectedRow);
                    jtTable.updateUI();
                }
            }
        });
        setButtonConfig(jbDel);

        gbcDetAnimalButton.insets = new Insets(5, 10, 5, 10);
        gbcDetAnimalButton.gridx = 0;
        gbcDetAnimalButton.gridy = 0;
        jpBoutons.add(jbMod, gbcDetAnimalButton);

        gbcDetAnimalButton.gridx = 1;
        gbcDetAnimalButton.gridy = 0;
        jpBoutons.add(jbAdd, gbcDetAnimalButton);

        gbcDetAnimalButton.gridx = 2;
        gbcDetAnimalButton.gridy = 0;
        jpBoutons.add(jbDel, gbcDetAnimalButton);

        jpRight.add(jpBoutons, gbcRight);


        gbcRight.gridx = 0;
        gbcRight.gridy = 2;

        jpDetAnimal = new JPanel();

        GridBagLayout gblAnimalForm = new GridBagLayout();
        jpDetAnimal.setLayout(gblAnimalForm);
        GridBagConstraints gbcAnimalForm = new GridBagConstraints();


        // 3 e ligne (et +) : formulaire avec les détails d'un animal
        JLabel jlEnclos = new JLabel("Enclos :");
        gbcAnimalForm.gridx = 0;
        gbcAnimalForm.gridy = 0;
        jpDetAnimal.add(jlEnclos, gbcAnimalForm);


        String[] sEnclos = new String[enclosDB.size()];
        for (int i = 0; i < enclosDB.size(); i++) {
            sEnclos[i] = enclosDB.get(i).getNom();
        }

        JComboBox jcEnclos = new JComboBox(sEnclos);
        //jcEnclos.setEditable(true);
        AutoCompletion ac = new AutoCompletion(jcEnclos);
        //ac.setStrict(false);
        //jcEnclos.setSelectedIndex(2);

        gbcAnimalForm.gridx = 1;
        gbcAnimalForm.gridy = 0;
        jpDetAnimal.add(jcEnclos, gbcAnimalForm);


        //Icon addIcon = new ImageIcon(AnimalTab.class.getResource("/ajout.png"));
        //JButton jbAddEnclos = new JButton(addIcon);
        JButton jbAddEnclos = new JButton("+");
        jbAddEnclos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atAnimalController.refreshTest();
            }
        });
        setButtonConfig(jbAddEnclos);
        gbcAnimalForm.gridx = 2;
        gbcAnimalForm.gridy = 0;
        jpDetAnimal.add(jbAddEnclos, gbcAnimalForm);

        jpRight.add(jpDetAnimal, gbcRight);


        configFrame(getJfFrame(), this);
    }

    private void setDetView() {
        jpDetAnimal.removeAll();

        jpDetAnimal.updateUI();
    }

    private void setModView() {
        jpDetAnimal.removeAll();

        jpDetAnimal.updateUI();
    }

    private void setAddView() {
        jpDetAnimal.removeAll();

        jpDetAnimal.updateUI();
    }

    private void print() {

    }

}
