package View.Animal;

import Model.*;
import View.GenericWindow;
import View.MyModelTable;
import Controller.Animal.*;
import com.jidesoft.swing.AutoCompletion;


import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;


import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Pattern;

import static Model.Tools.DateSQL.calculateAge;


/**
 * Created by Andre on 10.03.2017.
 */
public class AnimalTab extends GenericWindow {
    private String[] columnName = {"Nom", "Nom commun", "Race", "Sexe", "Age", "Enclos"};

    private static String S_DETANIMAL = "Détails d'un animal";
    private static String S_MODANIMAL = "Modification d'un animal";
    private static String S_ADDANIMAL = "Ajout d'un animal";

    protected int mode = 0;             // 0 pour détails, 1 pour modifier, 2 pour ajout
    protected int selectedRow;

    protected JPanel jpDetAnimal;

    protected MyModelTable dataTable;

    protected ArrayList<Enclos> enclosDB;
    protected ArrayList<Animal> animauxDB;
    protected ArrayList<Race> racesDB;
    protected ArrayList<Pays> originesDB;

    private static TableRowSorter<MyModelTable> sorter;

    public AnimalTab(AnimalController atAnimalController) {
        super("Animaux");

        animauxDB = atAnimalController.getAllAnimal();
        enclosDB = atAnimalController.getAllEnclos();
        racesDB = atAnimalController.getAllRaces();
        originesDB = atAnimalController.getAllOrigines();


        Vector<Vector<Object>> vAnimal = atAnimalController.animauxToVector(animauxDB, enclosDB, racesDB);

        dataTable = new MyModelTable(vAnimal, columnName);
        JTable jtTable = new JTable(dataTable);
        jtTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtTable.setColumnSelectionAllowed(false);
        jtTable.setCellSelectionEnabled(false);
        jtTable.setRowSelectionAllowed(true);

        sorter = new TableRowSorter<>(dataTable);
        jtTable.setRowSorter(sorter);

        Dimension d = jtTable.getPreferredScrollableViewportSize();
        d.width = jtTable.getPreferredSize().width;
        jtTable.setPreferredScrollableViewportSize(d);


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

        JTextField jtFilter = new JTextField();
        jtFilter.setPreferredSize(new Dimension(90, 30));
        jtFilter.setToolTipText("Recherche");


        JButton jbPrint = new JButton("Imprimer");
        jbPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                print();
            }
        });
        setButtonConfig(jbPrint);


        JButton jbFilter = new JButton("Filtrer");
        jbFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sorter.setRowFilter( RowFilter.regexFilter(Pattern.quote(jtFilter.getText())));
            }
        });
        setButtonConfig(jbFilter);


        GridBagLayout gblButtonAnimal = new GridBagLayout();
        jpButtonAnimal.setLayout(gblButtonAnimal);
        GridBagConstraints gbcButtonAnimal = new GridBagConstraints();

        gbcButtonAnimal.insets = new Insets(5, 5, 5, 5);
        gbcButtonAnimal.gridx = 0;
        gbcButtonAnimal.gridy = 0;
        jpButtonAnimal.add(jbPrint, gbcButtonAnimal);

        gbcButtonAnimal.gridx = 1;
        gbcButtonAnimal.gridy = 0;
        jpButtonAnimal.add(jtFilter, gbcButtonAnimal);

        gbcButtonAnimal.gridx = 2;
        gbcButtonAnimal.gridy = 0;
        jpButtonAnimal.add(jbFilter, gbcButtonAnimal);


        /**************************************************************/

        JLabel jlDetAnimal = new JLabel("Détail d'un animal");

        JButton jbMod = new JButton("Modifier");
        JButton jbAdd = new JButton("Ajouter");
        JButton jbDel = new JButton("Supprimer");

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        gbcLeft.weighty = 10;

        JPanel jpTableAnimal = new JPanel();
        jpTableAnimal.setPreferredSize(new Dimension(820, 720));


        jtTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
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
                jpMainPanel.updateUI();

                switch (mode) {
                    case 0:
                        setDetView();
                        break;
                    case 1:
                        setModView();
                        break;
                    case 2:
                        setAddView();
                        break;
                }
            }
        });

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



        jbMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (mode) {
                    case 0:
                        jlDetAnimal.setText(S_MODANIMAL);
                        jbMod.setText("Détails");
                        mode = 1;
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
                    if(atAnimalController.delAnimal(animauxDB.get(selectedRow))) {
                        dataTable.removeRow(selectedRow);
                        animauxDB.remove(selectedRow);
                        jtTable.clearSelection();
                        //jtTable.updateUI();
                    }
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

        //Formulaire/détails
        jpDetAnimal = new JPanel();
        setDetView();
        jpRight.add(jpDetAnimal, gbcRight);

        configFrame(getJfFrame(), this);
    }

    private void setDetView() {
        jpDetAnimal.removeAll();



        jpDetAnimal.updateUI();
    }

    private void setModView() {
        jpDetAnimal.removeAll();

        Dimension defaultFormSize = new Dimension(120, 30);

        Animal selectedAnimal = animauxDB.get(selectedRow);

        GridBagLayout gblAnimalForm = new GridBagLayout();
        jpDetAnimal.setLayout(gblAnimalForm);
        GridBagConstraints gbcAnimalForm = new GridBagConstraints();

        gbcAnimalForm.anchor = GridBagConstraints.WEST;


        // Xe ligne : nom
        JLabel jlNomAnimal = new JLabel("Nom : ");
        //jlNomAnimal.setPreferredSize(defaultFormSize);
        setLabelConfig(jlNomAnimal);
        JTextField jtNomAnimal = new JTextField(selectedAnimal.getNom());
        jtNomAnimal.setPreferredSize(defaultFormSize);

        gbcAnimalForm.gridx = 0;
        gbcAnimalForm.gridy = 0;
        jpDetAnimal.add(jlNomAnimal, gbcAnimalForm);

        gbcAnimalForm.gridx = 1;
        gbcAnimalForm.gridy = 0;
        jpDetAnimal.add(jtNomAnimal, gbcAnimalForm);



        // Xe ligne : Ligne Enclos
        JLabel jlEnclos = new JLabel("Enclos :");
        jlEnclos.setPreferredSize(defaultFormSize);
        gbcAnimalForm.gridx = 0;
        gbcAnimalForm.gridy = 1;
        jpDetAnimal.add(jlEnclos, gbcAnimalForm);


        String[] sEnclos = new String[enclosDB.size()];
        for (int i = 0; i < enclosDB.size(); i++) {
            sEnclos[i] = enclosDB.get(i).getNom();
        }

        JComboBox jcEnclos = new JComboBox(sEnclos);
        //jcEnclos.setEditable(true);
        AutoCompletion ac = new AutoCompletion(jcEnclos);
        //ac.setStrict(false);
        jcEnclos.setSelectedIndex(selectedAnimal.getEnclos() - 1);

        gbcAnimalForm.gridx = 1;
        gbcAnimalForm.gridy = 1;
        jpDetAnimal.add(jcEnclos, gbcAnimalForm);


        //Icon addIcon = new ImageIcon(AnimalTab.class.getResource("/ajout.png"));
        //JButton jbAddEnclos = new JButton(addIcon);
        JButton jbAddEnclos = new JButton("+");
        jbAddEnclos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        });
        setButtonConfig(jbAddEnclos);
        gbcAnimalForm.gridx = 2;
        gbcAnimalForm.gridy = 1;
        jpDetAnimal.add(jbAddEnclos, gbcAnimalForm);



        JLabel jlNomCommun = new JLabel("Nom commun:");
        setLabelConfig(jlNomCommun);
        gbcAnimalForm.gridx = 0;
        gbcAnimalForm.gridy = 2;
        jpDetAnimal.add(jlNomCommun, gbcAnimalForm);

        JTextField jtNomCommun = new JTextField();
        jtNomCommun.setText(selectedAnimal.getNomCommun());
        gbcAnimalForm.gridx = 1;
        gbcAnimalForm.gridy = 2;
        jtNomCommun.setPreferredSize(defaultFormSize);
        jpDetAnimal.add(jtNomCommun, gbcAnimalForm);


        // Xe ligne Nom Commun
        selectedAnimal.getNomCommun();

        // Xe ligne Race
        selectedAnimal.getRace();

        // Xe ligne Sexe
        selectedAnimal.getSexe();

        // Xe ligne Origine
        selectedAnimal.getOrigine();

        // Xe ligne DateNaissance
        selectedAnimal.getDateNaissance();

        // Xe ligne DateDeces
        selectedAnimal.getDateDeces();

        // Le reste dans une JTable (Poids)
        if(selectedAnimal instanceof Felin){
            double poids = ((Felin) selectedAnimal).getPoids();
        }
        else if(selectedAnimal instanceof Oiseau){
            String bague = ((Oiseau) selectedAnimal).getBague();
            double envergure = ((Oiseau) selectedAnimal).getEnvergure();
        }
        else if(selectedAnimal instanceof Reptile){
            double temperature = ((Reptile) selectedAnimal).getTemperature();
        }
        else if(selectedAnimal instanceof Primate){
            double temperature = ((Primate) selectedAnimal).getTemperature();
        }



        JButton jbConfirm = new JButton("Appliquer");
        setButtonConfig(jbConfirm);
        jbConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Check des valeurs du formulaire avant de demander confirmation

                //selectedAnimal.setAnneeNaissance();
            }
        });


        jpDetAnimal.updateUI();
    }

    private void setAddView() {
        jpDetAnimal.removeAll();

        jpDetAnimal.updateUI();
    }

    private void print() {

    }

}
