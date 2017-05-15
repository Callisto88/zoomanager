package View.Animal;

import Model.*;
import View.DateLabelFormatter;
import View.EventsTable;
import View.GenericWindow;
import View.MyModelTable;
import Controller.Animal.*;
import com.jidesoft.swing.AutoCompletion;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;


import javax.swing.*;
import javax.swing.table.TableRowSorter;
import javax.swing.text.html.ListView;
import java.awt.*;


import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Properties;
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

    protected AnimalController atAnimalController;

    public AnimalTab(AnimalController atAnimalController) {
        super("Animaux");

        this.atAnimalController = atAnimalController;

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

        JLabel jlFiltrer = new JLabel("Recherche/Filtre");

        JTextField jtFilter = new JTextField();
        jtFilter.setPreferredSize(new Dimension(90, 30));
        jtFilter.setToolTipText("Recherche");
        jtFilter.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(jtFilter.getText())));
            }
        });


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

        gbcButtonAnimal.gridx = 1;
        gbcButtonAnimal.gridy = 0;
        jpButtonAnimal.add(jlFiltrer, gbcButtonAnimal);

        gbcButtonAnimal.gridx = 2;
        gbcButtonAnimal.gridy = 0;
        jpButtonAnimal.add(jtFilter, gbcButtonAnimal);


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
                if (jtTable.getRowSorter() != null) {
                    selectedRow = jtTable.getRowSorter().convertRowIndexToModel(selectedRow);
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
                super.mousePressed(e);
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
                    if(atAnimalController.delAnimal(animauxDB.get(selectedRow))) {
                        dataTable.removeRow(selectedRow);
                        animauxDB.remove(selectedRow);
                        jtTable.clearSelection();
                        jtTable.updateUI();
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

        int y = 0;

        Dimension defaultFormSize = new Dimension(140, 30);

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
        gbcAnimalForm.gridy = y;
        jpDetAnimal.add(jlNomAnimal, gbcAnimalForm);

        gbcAnimalForm.gridx = 1;
        gbcAnimalForm.gridy = y;
        jpDetAnimal.add(jtNomAnimal, gbcAnimalForm);

        y++;


        // Xe ligne : enclos
        int maxLength = 200;
        JLabel jlEnclos = new JLabel("Enclos :");
        jlEnclos.setPreferredSize(defaultFormSize);
        gbcAnimalForm.gridx = 0;
        gbcAnimalForm.gridy = y;
        jpDetAnimal.add(jlEnclos, gbcAnimalForm);


        String[] sEnclos = new String[enclosDB.size()];
        for (int i = 0; i < enclosDB.size(); i++) {
            sEnclos[i] = enclosDB.get(i).getNom();
            if(sEnclos[i].length() > maxLength){
                maxLength = sEnclos[i].length();
            }
        }

        WideComboBox jcEnclos = new WideComboBox(sEnclos);
        //jcEnclos.setEditable(true);
        AutoCompletion acEnclos = new AutoCompletion(jcEnclos);
        //ac.setStrict(false);
        jcEnclos.setSelectedIndex(selectedAnimal.getEnclos() - 1);
        jcEnclos.setMaximumSize(new Dimension(maxLength + 5, 30));
        jcEnclos.setPreferredSize(defaultFormSize);

        gbcAnimalForm.gridx = 1;
        gbcAnimalForm.gridy = y;
        jpDetAnimal.add(jcEnclos, gbcAnimalForm);

        y++;


        // Xe ligne : Nom Commun
        JLabel jlNomCommun = new JLabel("Nom commun :");
        setLabelConfig(jlNomCommun);
        gbcAnimalForm.gridx = 0;
        gbcAnimalForm.gridy = y;
        jpDetAnimal.add(jlNomCommun, gbcAnimalForm);

        JTextField jtNomCommun = new JTextField();
        jtNomCommun.setText(selectedAnimal.getNomCommun());
        gbcAnimalForm.gridx = 1;
        gbcAnimalForm.gridy = y;
        jtNomCommun.setPreferredSize(defaultFormSize);
        jpDetAnimal.add(jtNomCommun, gbcAnimalForm);

        y++;


        // Xe ligne : Race
        maxLength = 300;
        JLabel jlRace = new JLabel("Race :");
        jlRace.setPreferredSize(defaultFormSize);
        gbcAnimalForm.gridx = 0;
        gbcAnimalForm.gridy = y;
        jpDetAnimal.add(jlRace, gbcAnimalForm);

        String[] sRaces = new String[racesDB.size()];
        for (int i = 0; i < racesDB.size(); i++) {
            sRaces[i] = racesDB.get(i).getNom();
            if(sRaces[i].length() > maxLength){
                maxLength = sRaces[i].length();
            }
        }

        WideComboBox jcRaces = new WideComboBox(sRaces);
        //jcEnclos.setEditable(true);
        AutoCompletion acRaces = new AutoCompletion(jcRaces);
        //ac.setStrict(false);
        jcRaces.setSelectedIndex(selectedAnimal.getRace() - 1);
        jcRaces.setMaximumSize(new Dimension(maxLength + 4, 30));
        jcRaces.setPreferredSize(defaultFormSize);

        gbcAnimalForm.gridx = 1;
        gbcAnimalForm.gridy = y;
        jpDetAnimal.add(jcRaces, gbcAnimalForm);

        y++;


        // Xe ligne Sexe
        JLabel jlSexe = new JLabel("Sexe :");
        setLabelConfig(jlSexe);
        gbcAnimalForm.gridx = 0;
        gbcAnimalForm.gridy = y;
        jpDetAnimal.add(jlSexe, gbcAnimalForm);

        JTextField jtSexe = new JTextField(selectedAnimal.getSexe());
        jtSexe.setPreferredSize(defaultFormSize);
        gbcAnimalForm.gridx = 1;
        gbcAnimalForm.gridy = y;
        jpDetAnimal.add(jtSexe, gbcAnimalForm);

        y++;


        // Xe ligne Origine
        maxLength = 200;
        JLabel jlOrigine = new JLabel("Origine :");
        setLabelConfig(jlOrigine);
        gbcAnimalForm.gridx = 0;
        gbcAnimalForm.gridy = y;
        jpDetAnimal.add(jlOrigine, gbcAnimalForm);

        String[] sOrigines = new String[originesDB.size()];
        int origineID = 0;
        for (int i = 0; i < originesDB.size(); i++) {
            sOrigines[i] = originesDB.get(i).getPays();
            if(sOrigines[i].length() > maxLength){
                maxLength = sOrigines[i].length();
            }
            if(selectedAnimal.getOrigine() == originesDB.get(i).getPaysId()){
                origineID = i;
            }
        }

        WideComboBox jcOrigines = new WideComboBox(sOrigines);
        //jcEnclos.setEditable(true);
        AutoCompletion acOrigines = new AutoCompletion(jcOrigines);
        //ac.setStrict(false);
        jcOrigines.setSelectedIndex(origineID);
        jcOrigines.setMaximumSize(new Dimension(maxLength + 4, 30));
        jcOrigines.setPreferredSize(defaultFormSize);

        gbcAnimalForm.gridx = 1;
        gbcAnimalForm.gridy = y;
        jpDetAnimal.add(jcOrigines, gbcAnimalForm);

        y++;


        // Xe ligne DateNaissance
        JLabel jlDateNaissance = new JLabel("Date de naissance :");
        setLabelConfig(jlDateNaissance);
        gbcAnimalForm.gridx = 0;
        gbcAnimalForm.gridy = y;
        jpDetAnimal.add(jlDateNaissance, gbcAnimalForm);

        Properties pStartProperties = new Properties();
        pStartProperties.put("text.today", "Aujourd'hui");
        pStartProperties.put("text.month", "Mois");
        pStartProperties.put("text.year", "Année");
        SqlDateModel sdmModel1 = new SqlDateModel();
        LocalDate localDate = selectedAnimal.getDateNaissance().toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue() - 1;
        int day   = localDate.getDayOfMonth();
        sdmModel1.setDate(year, month, day);
        JDatePanelImpl jdpliStartDatePanel = new JDatePanelImpl(sdmModel1, pStartProperties);
        jdpliStartDatePanel.setPreferredSize(new Dimension(230, 230));
        JDatePickerImpl jdpriStartDatePicker = new JDatePickerImpl(jdpliStartDatePanel, new DateLabelFormatter());

        gbcAnimalForm.gridx = 1;
        gbcAnimalForm.gridy = y;
        jpDetAnimal.add(jdpriStartDatePicker, gbcAnimalForm);

        y++;


        // Ye ligne DateDeces
        if(selectedAnimal.getDateDeces() != null){
            JLabel jlDateDeces = new JLabel("Date Décès :");
            setLabelConfig(jlDateDeces);
            gbcAnimalForm.gridx = 0;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jlDateDeces, gbcAnimalForm);


            LocalDate localDateD = selectedAnimal.getDateDeces().toLocalDate();
            int yearD  = localDateD.getYear();
            int monthD = localDateD.getMonthValue() - 1;
            int dayD   = localDateD.getDayOfMonth();
            sdmModel1.setDate(yearD, monthD, dayD);
            JDatePanelImpl jdpliStartDatePanelD = new JDatePanelImpl(sdmModel1, pStartProperties);
            jdpliStartDatePanel.setPreferredSize(new Dimension(230, 230));
            JDatePickerImpl jdpriStartDatePickerD = new JDatePickerImpl(jdpliStartDatePanelD, new DateLabelFormatter());

            gbcAnimalForm.gridx = 1;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jdpriStartDatePickerD, gbcAnimalForm);

            y++;
        }




        // Les autre éléments dépendants du type d'animal
        if(selectedAnimal instanceof Felin){
            JLabel jlPoids = new JLabel("Poids :");
            setLabelConfig(jlPoids);
            gbcAnimalForm.gridx = 0;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jlPoids, gbcAnimalForm);

            double poids = ((Felin) selectedAnimal).getPoids();

            JTextField jtPoids = new JTextField(String.valueOf(poids));
            jtPoids.setPreferredSize(defaultFormSize);
            gbcAnimalForm.gridx = 1;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jtPoids, gbcAnimalForm);

            y++;
        }
        else if(selectedAnimal instanceof Oiseau){
            JLabel jlBague = new JLabel("Bague :");
            setLabelConfig(jlBague);
            gbcAnimalForm.gridx = 0;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jlBague, gbcAnimalForm);

            String bague = ((Oiseau) selectedAnimal).getBague();

            JTextField jtBague = new JTextField(bague);
            jtBague.setPreferredSize(defaultFormSize);
            gbcAnimalForm.gridx = 1;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jtBague, gbcAnimalForm);

            y++;


            JLabel jlEnvergure = new JLabel("Envergure :");
            setLabelConfig(jlEnvergure);
            gbcAnimalForm.gridx = 0;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jlEnvergure, gbcAnimalForm);

            double envergure = ((Oiseau) selectedAnimal).getEnvergure();

            JTextField jtEnvergure = new JTextField(String.valueOf(envergure));
            jtEnvergure.setPreferredSize(defaultFormSize);
            gbcAnimalForm.gridx = 1;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jtEnvergure, gbcAnimalForm);

            y++;
        }
        else if(selectedAnimal instanceof Reptile){
            JLabel jlTemperature = new JLabel("Température :");
            setLabelConfig(jlTemperature);
            gbcAnimalForm.gridx = 0;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jlTemperature, gbcAnimalForm);

            y++;

            double temperature = ((Reptile) selectedAnimal).getTemperature();
            JTextField jtTemperature = new JTextField(String.valueOf(temperature));
            gbcAnimalForm.gridx = 1;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jtTemperature, gbcAnimalForm);

            y++;
        }
        else if(selectedAnimal instanceof Primate){
            JLabel jlTemperature = new JLabel("Température :");
            setLabelConfig(jlTemperature);
            gbcAnimalForm.gridx = 0;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jlTemperature, gbcAnimalForm);

            y++;

            double temperature = ((Primate) selectedAnimal).getTemperature();
            JTextField jtTemperature = new JTextField(String.valueOf(temperature));
            gbcAnimalForm.gridx = 1;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jtTemperature, gbcAnimalForm);

            y++;
        }


        //JTable pour les événements
        ArrayList<Evenement> events = atAnimalController.getTasks(selectedAnimal.getId());
        EventsTable stStaff = new EventsTable(events);
        this.add(stStaff, gbcAnimalForm);


        JButton jbConfirm = new JButton("Appliquer");
        setButtonConfig(jbConfirm);
        jbConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Check des valeurs du formulaire avant de demander confirmation

                //selectedAnimal.setAnneeNaissance();
            }
        });

        gbcAnimalForm.gridx = 0;
        gbcAnimalForm.gridy = y;
        jpDetAnimal.add(jbConfirm, gbcAnimalForm);


        jpDetAnimal.updateUI();
    }

    private void setAddView() {
        jpDetAnimal.removeAll();


        Animal newAnimal = new Animal();

        jpDetAnimal.updateUI();
    }

    private void print() {

    }

    public class WideComboBox extends JComboBox{

        public WideComboBox() {
        }

        public WideComboBox(final Object items[]){
            super(items);
        }

        public WideComboBox(Vector items) {
            super(items);
        }

        public WideComboBox(ComboBoxModel aModel) {
            super(aModel);
        }

        private boolean layingOut = false;

        public void doLayout(){
            try{
                layingOut = true;
                super.doLayout();
            }finally{
                layingOut = false;
            }
        }

        public Dimension getSize(){
            Dimension dim = super.getMaximumSize();
            if(!layingOut) {
                dim.width = Math.max(dim.width, getPreferredSize().width);
            }
            if(dim.width > 500){
                dim.width = 500;
            }
            return dim;
        }
    }

}
