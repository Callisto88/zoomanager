package View.Animal;

import Controller.Animal.AnimalController;
import Controller.Validate.Validate;
import Model.*;
import View.*;
import com.jidesoft.swing.AutoCompletion;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.time.LocalDate;
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
    int animalType = 0;
    protected int selectedRow;

    protected JPanel jpDetAnimal;

    protected MyModelTable dataTable;

    protected ArrayList<Enclos> enclosDB;
    protected ArrayList<Animal> animauxDB;
    protected ArrayList<Race> racesDB;
    protected ArrayList<Pays> originesDB;

    protected JTable jtTable;

    private static TableRowSorter<MyModelTable> sorter;

    protected AnimalController atAnimalController;

    Dimension defaultFormSize = new Dimension(140, 30);

    public AnimalTab(AnimalController atAnimalController) {
        super("Animaux");

        this.atAnimalController = atAnimalController;

        animauxDB = atAnimalController.getAllAnimal();
        enclosDB = atAnimalController.getAllEnclos();
        racesDB = atAnimalController.getAllRaces();
        originesDB = atAnimalController.getAllOrigines();


        Vector<Vector<Object>> vAnimal = atAnimalController.animauxToVector(animauxDB, enclosDB, racesDB);

        dataTable = new MyModelTable(vAnimal, columnName);
        jtTable = new JTable(dataTable);
        jtTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtTable.setColumnSelectionAllowed(false);
        jtTable.setCellSelectionEnabled(false);
        jtTable.setRowSelectionAllowed(true);

        sorter = new TableRowSorter<>(dataTable);
        jtTable.setRowSorter(sorter);

        Dimension d = jtTable.getPreferredScrollableViewportSize();
        d.width = jtTable.getPreferredSize().width;
        jtTable.setPreferredScrollableViewportSize(d);

        TableFilterHeader filterHeader = new TableFilterHeader(jtTable, AutoChoices.ENABLED);

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

                setView();

                super.mousePressed(e);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
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
                        break;
                    case 1:
                        jlDetAnimal.setText(S_DETANIMAL);
                        jbMod.setText("Modifier");
                        mode = 0;
                        break;
                    case 2:
                        jlDetAnimal.setText(S_MODANIMAL);
                        jbMod.setText("Détails");
                        jbAdd.setText("Ajouter");
                        mode = 1;
                        break;
                }
                setView();
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
                        break;
                    case 1:
                        jlDetAnimal.setText(S_ADDANIMAL);
                        jbAdd.setText("Détails");
                        jbMod.setText("Modifier");
                        mode = 2;
                        break;
                    case 2:
                        jlDetAnimal.setText(S_DETANIMAL);
                        jbAdd.setText("Ajouter");
                        mode = 0;
                        break;
                }
                setView();
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
        setView();
        jpRight.add(jpDetAnimal, gbcRight);

        configFrame(getJfFrame(), this);
    }


    private void setView() {
        jpDetAnimal.removeAll();

        int y = 0;

        Animal selectedAnimal = animauxDB.get(selectedRow);

        GridBagLayout gblAnimalForm = new GridBagLayout();
        jpDetAnimal.setLayout(gblAnimalForm);
        GridBagConstraints gbcAnimalForm = new GridBagConstraints();

        gbcAnimalForm.anchor = GridBagConstraints.WEST;
        gbcAnimalForm.fill = GridBagConstraints.HORIZONTAL;


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
        if(mode == 2) {
            jtNomAnimal.setText("");
        }
        else if(mode == 0){
            jtNomAnimal.setEditable(false);
        }
        jpDetAnimal.add(jtNomAnimal, gbcAnimalForm);

        y++;


        // Xe ligne : enclos
        int maxLength = 200;
        JLabel jlEnclos = new JLabel("Enclos :");
        setLabelConfig(jlEnclos);
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
        jcEnclos.setSelectedIndex(selectedAnimal.getEnclos().getId() - 1);
        jcEnclos.setMaximumSize(new Dimension(maxLength + 5, 30));
        jcEnclos.setPreferredSize(defaultFormSize);

        gbcAnimalForm.gridx = 1;
        gbcAnimalForm.gridy = y;
        if(mode == 2) {
            jcEnclos.setSelectedIndex(0);
            jpDetAnimal.add(jcEnclos, gbcAnimalForm);
        }
        else if(mode == 0){
            jpDetAnimal.add(new JLabel(String.valueOf(jcEnclos.getSelectedItem())), gbcAnimalForm);
        }
        else{
            jpDetAnimal.add(jcEnclos, gbcAnimalForm);
        }


        y++;


        // Xe ligne : Nom Commun
        JLabel jlNomCommun = new JLabel("Nom commun :");
        setLabelConfig(jlNomCommun);
        gbcAnimalForm.gridx = 0;
        gbcAnimalForm.gridy = y;
        jpDetAnimal.add(jlNomCommun, gbcAnimalForm);

        JTextField jtNomCommun = new JTextField();
        jtNomCommun.setText(selectedAnimal.getNomCommun());
        jtNomCommun.setPreferredSize(defaultFormSize);
        gbcAnimalForm.gridx = 1;
        gbcAnimalForm.gridy = y;
        if(mode == 2) {
            jtNomCommun.setText("");
        }
        else if(mode == 0){
            jtNomCommun.setEditable(false);
        }
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
        jcRaces.setSelectedIndex(selectedAnimal.getRace().getId() - 1);
        jcRaces.setMaximumSize(new Dimension(maxLength + 4, 30));
        jcRaces.setPreferredSize(defaultFormSize);

        gbcAnimalForm.gridx = 1;
        gbcAnimalForm.gridy = y;
        if(mode == 2) {
            jcRaces.setSelectedIndex(0);
            jpDetAnimal.add(jcRaces, gbcAnimalForm);
        }
        else if(mode == 0){
            JLabel jlRacesS = new JLabel(String.valueOf(jcRaces.getSelectedItem()));
            jlRace.setMaximumSize(new Dimension(350, 30));
            jpDetAnimal.add(jlRacesS, gbcAnimalForm);
        }
        else{
            jpDetAnimal.add(jcRaces, gbcAnimalForm);
        }

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
        if(mode == 2) {
            jtSexe.setText("");
        }
        else if(mode == 0){
            jtSexe.setEditable(false);
        }
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
            if(selectedAnimal.getOrigine().getPaysId() == originesDB.get(i).getPaysId()){
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
        if(mode == 2){
            jcOrigines.setSelectedIndex(0);
            jpDetAnimal.add(jcOrigines, gbcAnimalForm);
        }
        else if(mode == 0){
            jpDetAnimal.add(new Label(String.valueOf(jcOrigines.getSelectedItem())), gbcAnimalForm);
        }
        else{
            jpDetAnimal.add(jcOrigines, gbcAnimalForm);
        }

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
        sdmModel1.setSelected(true);
        JDatePanelImpl jdpliStartDatePanel = new JDatePanelImpl(sdmModel1, pStartProperties);
        jdpliStartDatePanel.setPreferredSize(new Dimension(270, 230));
        JDatePickerImpl jdpriStartDatePicker = new JDatePickerImpl(jdpliStartDatePanel, new DateLabelFormatter());

        jdpriStartDatePicker.getJDateInstantPanel().getModel().getValue();

        gbcAnimalForm.gridx = 1;
        gbcAnimalForm.gridy = y;
        if(mode == 2){
            sdmModel1.setSelected(false);
        }
        if(mode == 1 || mode == 2) {
            jpDetAnimal.add(jdpriStartDatePicker, gbcAnimalForm);
        }
        else if(mode == 0){
            jpDetAnimal.add(new JLabel(year + "-" + month + "-" + day), gbcAnimalForm);
        }

        y++;


        // Ye ligne DateDeces
        SqlDateModel sdmModel2 = new SqlDateModel();
        if(selectedAnimal.getDateDeces() != null){
            JLabel jlDateDeces = new JLabel("Date Décès :");
            setLabelConfig(jlDateDeces);
            gbcAnimalForm.gridx = 0;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jlDateDeces, gbcAnimalForm);


            LocalDate localDateD = selectedAnimal.getDateDeces().toLocalDate();
            year  = localDateD.getYear();
            month = localDateD.getMonthValue() - 1;
            day   = localDateD.getDayOfMonth();
            sdmModel2.setDate(year, month, day);
            sdmModel2.setSelected(true);
            jdpliStartDatePanel = new JDatePanelImpl(sdmModel2, pStartProperties);
            jdpliStartDatePanel.setPreferredSize(new Dimension(270, 230));
            jdpriStartDatePicker = new JDatePickerImpl(jdpliStartDatePanel, new DateLabelFormatter());

            gbcAnimalForm.gridx = 1;
            gbcAnimalForm.gridy = y;
            if(mode == 1 || mode == 2) {
                jpDetAnimal.add(jdpriStartDatePicker, gbcAnimalForm);
            }
            else if(mode == 0){
                jpDetAnimal.add(new JLabel(year + "-" + month + "-" + day), gbcAnimalForm);
            }

            y++;
        }



        // Les autre éléments dépendants du type d'animal
        JTextField jtTemperature = new JTextField();
        JTextField jtPoids = new JTextField();
        JTextField jtBague = new JTextField();
        JTextField jtEnvergure = new JTextField();


        String[] alTypes = new String[4];
        alTypes[0] = "Primate";
        alTypes[1] = "Oiseau";
        alTypes[2] = "Felin";
        alTypes[3] = "Reptile";
        WideComboBox jcAnimalType = new WideComboBox(alTypes);
        jcAnimalType.setMaximumSize(new Dimension(200, 30));
        jcAnimalType.setPreferredSize(defaultFormSize);
        jcAnimalType.setSelectedIndex(animalType);
        jcAnimalType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animalType = jcAnimalType.getSelectedIndex();
                setView();
            }
        });
        if(mode == 2){
            JLabel jlTypeAnimal = new JLabel("Type animal :");
            setLabelConfig(jlTypeAnimal);
            gbcAnimalForm.gridx = 0;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jlTypeAnimal, gbcAnimalForm);

            gbcAnimalForm.gridx = 1;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jcAnimalType, gbcAnimalForm);

            y++;
        }


        if((mode == 2 && animalType == 2) || (mode != 2 && selectedAnimal instanceof Felin)){
            JLabel jlPoids = new JLabel("Poids :");
            setLabelConfig(jlPoids);
            gbcAnimalForm.gridx = 0;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jlPoids, gbcAnimalForm);

            double poids = 0;
            if(selectedAnimal instanceof Felin){
                poids = ((Felin) selectedAnimal).getPoids();
            }

            jtPoids.setText(String.valueOf(poids));
            jtPoids.setPreferredSize(defaultFormSize);
            gbcAnimalForm.gridx = 1;
            gbcAnimalForm.gridy = y;
            if(mode == 2){
                jtPoids.setText("");
            }
            else if(mode == 0){
                jtPoids.setEditable(false);
            }
            jpDetAnimal.add(jtPoids, gbcAnimalForm);

            y++;
        }
        else if((mode == 2 && animalType == 1) || (mode != 2 && selectedAnimal instanceof Oiseau)){
            JLabel jlBague = new JLabel("Bague :");
            setLabelConfig(jlBague);
            gbcAnimalForm.gridx = 0;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jlBague, gbcAnimalForm);

            String bague = "";
            if(selectedAnimal instanceof Oiseau){
                bague = ((Oiseau) selectedAnimal).getBague();
            }

            jtBague.setText(bague);
            jtBague.setPreferredSize(defaultFormSize);
            gbcAnimalForm.gridx = 1;
            gbcAnimalForm.gridy = y;
            if(mode == 2){
                jtBague.setText("");
            }
            else if(mode == 0){
                jtBague.setEditable(false);
            }
            jpDetAnimal.add(jtBague, gbcAnimalForm);

            y++;


            JLabel jlEnvergure = new JLabel("Envergure :");
            setLabelConfig(jlEnvergure);
            gbcAnimalForm.gridx = 0;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jlEnvergure, gbcAnimalForm);

            double envergure = 0;
            if(selectedAnimal instanceof Oiseau) {
                envergure = ((Oiseau) selectedAnimal).getEnvergure();
            }
            jtEnvergure.setText(String.valueOf(envergure));
            jtEnvergure.setPreferredSize(defaultFormSize);
            gbcAnimalForm.gridx = 1;
            gbcAnimalForm.gridy = y;
            if(mode == 2){
                jtEnvergure.setText("");
            }
            else if(mode == 0){
                jtEnvergure.setEditable(false);
            }
            jpDetAnimal.add(jtEnvergure, gbcAnimalForm);

            y++;
        }
        else if((mode == 2 && animalType == 3) || (mode != 2 && selectedAnimal instanceof Reptile)){
            JLabel jlTemperature = new JLabel("Température :");
            setLabelConfig(jlTemperature);
            gbcAnimalForm.gridx = 0;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jlTemperature, gbcAnimalForm);

            double temperature = 0;
            if(selectedAnimal instanceof Reptile) {
                temperature = ((Reptile) selectedAnimal).getTemperature();
            }
            jtTemperature.setText(String.valueOf(temperature));
            jtTemperature.setPreferredSize(defaultFormSize);
            gbcAnimalForm.gridx = 1;
            gbcAnimalForm.gridy = y;
            if(mode == 2){
                jtTemperature.setText("");
            }
            else if(mode == 0){
                jtTemperature.setEditable(false);
            }
            jpDetAnimal.add(jtTemperature, gbcAnimalForm);

            y++;
        }
        else if((mode == 2 && animalType == 0) || (mode != 2 && selectedAnimal instanceof Primate)){
            JLabel jlTemperature = new JLabel("Température :");
            setLabelConfig(jlTemperature);
            gbcAnimalForm.gridx = 0;
            gbcAnimalForm.gridy = y;
            jpDetAnimal.add(jlTemperature, gbcAnimalForm);

            double temperature = 0;
            if(selectedAnimal instanceof Primate){
                temperature = ((Primate) selectedAnimal).getTemperature();
            }
            jtTemperature.setText(String.valueOf(temperature));
            jtTemperature.setPreferredSize(defaultFormSize);
            gbcAnimalForm.gridx = 1;
            gbcAnimalForm.gridy = y;
            if(mode == 2){
                jtTemperature.setText("");
            }
            else if(mode == 0){
                jtTemperature.setEditable(false);
            }
            jpDetAnimal.add(jtTemperature, gbcAnimalForm);

            y++;
        }

        //JTable pour les événements
        ArrayList<Evenement> events = atAnimalController.getTasks(selectedAnimal.getId());
        EventsTable etStaff = new EventsTable(events);
        gbcAnimalForm.gridx = 0;
        gbcAnimalForm.gridy = y;
        gbcAnimalForm.gridwidth = 2;
        etStaff.setPreferredSize(new Dimension(361, 260));
        etStaff.getJTable().setPreferredScrollableViewportSize(new Dimension(355, 230));
        jpDetAnimal.add(etStaff, gbcAnimalForm);

        y++;

        gbcAnimalForm.gridwidth = 1;

        JButton jbConfirm = new JButton("Appliquer");
        setButtonConfig(jbConfirm);
        jbConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Check des valeurs du formulaire avant de demander confirmation
                boolean formOk = true;

                String nom = jtNomAnimal.getText();
                String nomCommun = jtNomCommun.getText();
                String sexe = jtSexe.getText();
                String poids;
                double dPoids = 0;
                String temperature;
                double dTemperature = 0;
                String bague = "";
                String envergure;
                double dEnvergure = 0;

                int year = sdmModel1.getYear();
                int month = sdmModel1.getMonth();
                int day = sdmModel1.getDay();

                int yearD = 1970;
                int monthD = 1;
                int dayD = 1;


                if(mode == 2){
                     animalType = jcAnimalType.getSelectedIndex();
                }
                int enclos = enclosDB.get(jcEnclos.getSelectedIndex()).getId();
                int race = racesDB.get(jcRaces.getSelectedIndex()).getId();
                int origine = originesDB.get(jcOrigines.getSelectedIndex()).getPaysId();

                if(!Validate.isAlphabetic(nom)){
                    jtNomAnimal.setBackground(Color.RED);
                    formOk = false;
                }
                if(!Validate.isAlphabetic(nomCommun)){
                    jtNomCommun.setBackground(Color.RED);
                    formOk = false;
                }
                if(!Validate.isAlphabetic(sexe)){
                    jtSexe.setBackground(Color.RED);
                    formOk = false;
                }
                if(!Validate.isDate(year, month, day)){
                    //jdpliStartDatePanel.setBackground(Color.RED);
                    formOk = false;
                }
                if((mode == 2 && animalType == 2) || (mode != 2 && selectedAnimal instanceof Felin)){
                    poids = jtPoids.getText();
                    if(!Validate.isNumeric(poids)) {
                        formOk = false;
                        jtPoids.setBackground(Color.RED);
                    }
                    else {
                        dPoids = Double.parseDouble(poids);
                    }
                }
                else if((mode == 2 && animalType == 1) || (mode != 2 && selectedAnimal instanceof Oiseau)){
                    envergure = jtEnvergure.getText();
                    bague = jtBague.getText();
                    if(!Validate.isNumeric(envergure)){
                        formOk = false;
                        jtEnvergure.setBackground(Color.RED);
                    }
                    else{
                        dEnvergure = Double.parseDouble(envergure);
                    }
                    if(!Validate.isAlphabetic(bague)){
                        formOk = false;
                        jtBague.setBackground(Color.RED);
                    }
                }
                else if((mode == 2 && (animalType == 0 || animalType == 3)) || (mode != 2 && selectedAnimal instanceof Reptile) || (mode != 2 || selectedAnimal instanceof Primate)){
                    temperature = jtTemperature.getText();
                    if(!Validate.isNumeric(temperature)){
                        formOk = false;
                        jtTemperature.setBackground(Color.RED);
                    }
                    else{
                        dTemperature = Double.parseDouble(temperature);
                    }
                }

                if(selectedAnimal.getDateDeces() != null){
                    yearD = sdmModel2.getYear();
                    monthD = sdmModel2.getYear();
                    dayD = sdmModel2.getYear();
                }

                if(formOk){
                    Animal newAnimal;
                    if(selectedAnimal.getDateDeces() != null) {
                        if((mode == 2 && animalType == 2) || (mode != 2 && selectedAnimal instanceof Felin)){
                            newAnimal = new Felin(nomCommun, nom, sexe, new Date(year, month, day), new Enclos(enclos), new Pays(origine), new Race(race), null, dPoids);
                        }
                        else if((mode == 2 && animalType == 0) || (mode != 2 && selectedAnimal instanceof Primate)){
                            newAnimal = new Primate(nomCommun, nom, sexe, new Date(year, month, day), new Enclos(enclos), new Pays(origine), new Race(race), null, dTemperature);
                        }
                        else if((mode == 2 && animalType == 1) || (mode != 2 && selectedAnimal instanceof Oiseau)){
                            newAnimal = new Oiseau(nomCommun, nom, sexe, new Date(year, month, day), new Enclos(enclos), new Pays(origine), new Race(race), null, dEnvergure, bague);
                        }
                        else if((mode == 2 && animalType == 3) || (mode != 2 && selectedAnimal instanceof Reptile)){
                            newAnimal = new Reptile(nomCommun, nom, sexe, new Date(year, month, day), new Enclos(enclos), new Pays(origine), new Race(race), null, dTemperature);
                        }
                        else{
                            newAnimal = new Animal(nomCommun, nom, sexe, new Date(year, month, day), new Enclos(enclos), new Pays(origine), new Race(race), new Date(yearD, monthD, dayD));
                        }
                    }
                    else{
                        if((mode == 2 && animalType == 2) || (mode != 2 && selectedAnimal instanceof Felin)){
                            newAnimal = new Felin(nomCommun, nom, sexe, new java.sql.Date(year, month, day), new Enclos(enclos), new Pays(origine), new Race(race), null, dPoids);
                        }
                        else if((mode == 2 && animalType == 0) || (mode != 2 && selectedAnimal instanceof Primate)){
                            newAnimal = new Primate(nomCommun, nom, sexe, new Date(year, month, day), new Enclos(enclos), new Pays(origine), new Race(race), null, dTemperature);
                        }
                        else if((mode == 2 && animalType == 1) || (mode != 2 && selectedAnimal instanceof Oiseau)){
                            newAnimal = new Oiseau(nomCommun, nom, sexe, new Date(year, month, day), new Enclos(enclos), new Pays(origine), new Race(race), null, dEnvergure, bague);
                        }
                        else if((mode == 2 && animalType == 3) || (mode != 2 && selectedAnimal instanceof Reptile)){
                            newAnimal = new Reptile(nomCommun, nom, sexe, new Date(year, month, day), new Enclos(enclos), new Pays(origine), new Race(race), null, dTemperature);
                        }
                        else {
                            newAnimal = new Animal(nomCommun, nom, sexe, new Date(year, month, day), new Enclos(enclos), new Pays(origine), new Race(race));
                        }
                    }

                    Vector<Object> vNewAnimal = newAnimal.toVector(1);
                    int age = calculateAge(newAnimal.getAnneeNaissance());
                    String ageL = newAnimal.getAnneeNaissance().toString() + " : " + age + " ans";
                    vNewAnimal.setElementAt(ageL ,4);
                    if (newAnimal.getEnclos().getId() != 0) {
                        vNewAnimal.add(newAnimal.getEnclos().getNom());
                    } else {
                        vNewAnimal.add("");
                    }
                    if (newAnimal.getRace().getId() != 0) {
                        vNewAnimal.setElementAt(newAnimal.getRace().getNom(), 2);
                    } else {
                        vNewAnimal.setElementAt("", 2);
                    }

                    if(mode == 1){
                        newAnimal.setId(selectedAnimal.getId());
                        atAnimalController.modAnimal(newAnimal);
                        for(int i = 0; i < vNewAnimal.size(); i++) {
                            dataTable.setValueAt(vNewAnimal.get(i), selectedRow, i);
                        }
                        animauxDB.add(newAnimal);
                        jtTable.updateUI();
                    }
                    else if(mode == 2) {
                        atAnimalController.insAnimal(newAnimal);
                        dataTable.addRow(vNewAnimal);
                        animauxDB.add(newAnimal);
                        jtTable.updateUI();
                    }
                    JOptionPane.showMessageDialog(jpMainPanel, "Succès");
                }
                else{
                    JOptionPane.showMessageDialog(jpMainPanel, "Données éronées !", "Erreur", JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        gbcAnimalForm.gridx = 0;
        gbcAnimalForm.gridy = y;
        if(mode != 0) {
            jpDetAnimal.add(jbConfirm, gbcAnimalForm);
        }


        jpDetAnimal.updateUI();
    }

    private void print() {
        new PrintPDF(jtTable, "Animaux", "");
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
