package View.Staff.StaffMainPanel;

import Controller.Staff.StaffController;
import Model.*;
import View.GenericWindow;
import View.MyModelTable;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 * Created by Andre on 10.03.2017.
 * Fenêtre principale pour les employée
 */
public class StaffView extends GenericWindow {
    private StaffController controller = null;
    // Labels des colonnes pour le personnel
    private String[] columnName ={"Nom", "Prénom", "Numéro AVS", "Date de Naissance"};
    // Labels des colonnes pour les externes
    private String[] sColumnExternal = {"Nom", "Nrénom", "Entreprise", "Télephone"};
    private JPanel jpRight = null;
    private JTable jtTable = null;
    protected int selectedRow;
    private TableRowSorter<MyModelTable> sorterStaff = null;
    private TableRowSorter<MyModelTable> sorterExternal = null;
    private ArrayList<Personne> alPersonnes = null;
    private Vector<Vector<Object>> tableauStaff = null;
    private Vector<Vector<Object>> tableauExternal = null;
    private ArrayList<Intervenant> alExternal = null;
    private MyModelTable mmtListing = null;
    private PersonnelStaf psDetails = null;
    private ExternalStaff esDetails = null;

    /**
     * Constructeur permettant d'instancier toutes les fenêtre composant la fenêtre principale
     *
     * @param persControl Controlleur de la fenêtre pour permettre de lui faire remonter les informations utiles.
     */
    public StaffView(StaffController persControl) {
        super("Personnel");
        controller = persControl;
        jtTable = new JTable();
        GridBagLayout gblLeft = new GridBagLayout();
        GridBagConstraints gbcLeft  = new GridBagConstraints();

        JPanel jpLeft = new JPanel();
        jpLeft.setLayout(gblLeft);

        JPanel jpLeftTitle = new JPanel();

        // Permet d'ajouter le Panel de Gauche qui contiendra le tableau et les bouton d'impression et d'ajout de personnel
        jpMainPanel.add(jpLeft);

        JLabel jlStaff = new JLabel("Liste des employés");
        setTitleConfig(jlStaff);
        jpLeftTitle.add(jlStaff);

        gbcLeft.fill = GridBagConstraints.CENTER;
        gbcLeft.gridx = 0;
        gbcLeft.anchor = GridBagConstraints.NORTH;
        gbcLeft.gridy = 0;
        jpLeft.add(jpLeftTitle,gbcLeft);

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 1;


        JPanel jpButtonStaff = new JPanel();
        jpLeft.add(jpButtonStaff, gbcLeft);

        JButton jbPrint = new JButton("Imprimer listing employés");

        // Listener pour capter l'appui sur le boutton d'impression
        jbPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(((JButton) e.getSource()).getText().equals("Imprimer listing employés")){
                    String title = "Listing employée";
                    controller.print(jtTable, title, null);
                }
                else{
                    String title = "Listing intervenant";
                    controller.print(jtTable, title, null);
                }
            }
        });

        JLabel jlSearch = new JLabel("Recherche");
        // Champ pour pouvoir filtrer
        JTextField jtFilter = new JTextField();
        jtFilter.setPreferredSize(new Dimension(80, 30));
        jtFilter.setToolTipText("Recherche");

        JButton jbSwitchexernalInternalStaff = new JButton("Afficher les externes");

        // Listener pour saisir l'action de relaché une touche de saisie pour filtrer
        jtFilter.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(jbSwitchexernalInternalStaff.getText().equals("Afficher les externes")) {
                    sorterStaff.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(jtFilter.getText())));
                }
                else{
                    sorterExternal.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(jtFilter.getText())));
                }
            }
        });

        // Bouton pour pouvoir ajouter du personnel
        JButton jbAdd = new JButton("Ajouter un employé");

        // Listener pour capter l'appui sur le boutton
        jbAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(((JButton) e.getSource()).getText().equals("Ajouter un employé")) {
                    controller.addStaffView();
                }
                else{
                    controller.addExternalView();
                }
            }
        });

        // Ajout de listener pour permettre d'afficher la liste du personnel ou des intervenant externes
        jbSwitchexernalInternalStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jbSwitchexernalInternalStaff.getText().equals("Afficher les externes")) {
                    // Permet de recrée une nouvelle table
                    createExternalTab();
                    //createTab(createExternalTab(), sColumnExternal);
                    // Permet de renommer le bouton pour faire afficher les employées
                    ((JButton) e.getSource()).setText("Afficher les employés");
                    jbAdd.setText("Ajouter un intervenant");
                    jbPrint.setText("Imprimer listing intervenants");
                    jlStaff.setText("Liste des intervenants");
                    jbSwitchexernalInternalStaff.repaint();
                    jbSwitchexernalInternalStaff.revalidate();
                }
                else{
                    // Permet de recrée une nouvelle table
                    createEmployeeTab();
                    //createTab(createEmployeeTab(), sColumnExternal);
                    // Permet de renommer le bouton pour faire afficher les employées
                    ((JButton) e.getSource()).setText("Afficher les externes");
                    jbAdd.setText("Ajouter un employé");
                    jbPrint.setText("Imprimer listing employés");
                    jlStaff.setText("Liste des employés");
                    jbSwitchexernalInternalStaff.repaint();
                    jbSwitchexernalInternalStaff.revalidate();
                }
            }
        });

        // Permet d'ajouter les bouton ainsi que le champ de filtre en haut de notre fenêtre
        GridBagLayout gblStaffBoutton = new GridBagLayout();
        jpButtonStaff.setLayout(gblStaffBoutton);
        GridBagConstraints gbcStaffBouton = new GridBagConstraints();

        gbcStaffBouton.insets = new Insets(0,5,0,5);
        gbcStaffBouton.gridx = 0;
        gbcStaffBouton.gridy = 0;
        jpButtonStaff.add(jbPrint, gbcStaffBouton);

        gbcStaffBouton.gridx = 1;
        jpButtonStaff.add(jbAdd, gbcStaffBouton);

        gbcStaffBouton.gridx = 2;
        jpButtonStaff.add(jbSwitchexernalInternalStaff, gbcStaffBouton);

        gbcStaffBouton.gridx = 3;
        jpButtonStaff.add(jlSearch, gbcStaffBouton);

        gbcStaffBouton.gridx = 4;
        jpButtonStaff.add(jtFilter, gbcStaffBouton);

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        gbcLeft.weighty = 20;

        JPanel jpTableStaff = new JPanel();
        jpTableStaff.setPreferredSize(new Dimension(800, 500));

        // Permet de mettre à jour le tableau pour les employées
        createEmployeeTab();

        // Permet de capturer l'action du clic, et crée le panel de droite avec les détails des employées ou des externes
        jtTable.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                jpRight.removeAll();
                // Permet de choisir si l'on affiche les détails d'un employée ou d'un externe
                if(jbSwitchexernalInternalStaff.getText().equals("Afficher les externes")) {
                    selectedRow = jtTable.getSelectedRow();
                    if (jtTable.getRowSorter() != null) {
                        selectedRow = jtTable.getRowSorter().convertRowIndexToModel(selectedRow);
                    }

                    JLabel jlDetails = new JLabel("Détails du personnel");
                    setTitleConfig(jlDetails);
                    jlDetails.setAlignmentX(Component.CENTER_ALIGNMENT);
                    jpRight.add(jlDetails, BorderLayout.CENTER);
                    psDetails = new PersonnelStaf(controller, alPersonnes.get(selectedRow), selectedRow);
                    jpRight.add(psDetails);
                    jpRight.revalidate();
                    jpRight.repaint();
                }
                else{
                    selectedRow = jtTable.getSelectedRow();
                    if (jtTable.getRowSorter() != null) {
                        selectedRow = jtTable.getRowSorter().convertRowIndexToModel(selectedRow);
                    }

                    esDetails = new ExternalStaff(controller, alExternal.get(selectedRow), selectedRow);
                    JLabel jlDetails = new JLabel("Détails des intervenants");
                    setTitleConfig(jlDetails);
                    jlDetails.setAlignmentX(Component.CENTER_ALIGNMENT);
                    jpRight.add(jlDetails);
                    jpRight.add(esDetails);
                    jpRight.revalidate();
                    jpRight.repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        Dimension d = jtTable.getPreferredScrollableViewportSize();
        d.width = jtTable.getPreferredSize().width;
        jtTable.setPreferredScrollableViewportSize(d);

        // Permet de rendre la JTable déroulante
        JScrollPane jspStaff = new JScrollPane(jtTable);
        jspStaff.setPreferredSize(new Dimension(700, 450));

        jpTableStaff.add(jspStaff);
        jpLeft.add(jpTableStaff, gbcLeft);

        jpRight = new JPanel();
        jpRight.setLayout(new BoxLayout(jpRight, BoxLayout.Y_AXIS));
        jpRight.setAlignmentY(SwingConstants.CENTER);
        jpRight.setAlignmentX(SwingConstants.CENTER);

        jpMainPanel.add(jpRight);

        JLabel jlDetails = new JLabel("Détails du personnel");
        setTitleConfig(jlDetails);
        jlDetails.setAlignmentX(Component.CENTER_ALIGNMENT);
        jpRight.add(jlDetails);

        configFrame(getJfFrame(), this);
    }

    /**
     * Méthode permettant de crée le tableau avec les employées
     */
    public void createEmployeeTab(){
        tableauStaff = new Vector<>();
        alPersonnes = null;
        alPersonnes = controller.getPersonnel();
        if(alPersonnes != null) {
            for (int i = 0; i < alPersonnes.size(); ++i) {
                tableauStaff.add(alPersonnes.get(i).toVector());
            }
        }
        mmtListing = new MyModelTable(tableauStaff, columnName);
        jtTable.setModel(mmtListing);
        jtTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtTable.setColumnSelectionAllowed(false);
        jtTable.setCellSelectionEnabled(false);
        jtTable.setRowSelectionAllowed(true);

        sorterStaff = new TableRowSorter<>(mmtListing);
        jtTable.setRowSorter(sorterStaff);

    }

    /**
     * Méthode permettant de crée le tableau avec les Intervenants
     */
    public void createExternalTab(){
        tableauExternal = new Vector<>();
        alExternal = null;
        alExternal = controller.getExternal();
        if(alExternal != null) {
            for (int i = 0; i < alExternal.size(); ++i) {
                tableauExternal.add(alExternal.get(i).toVector());
            }
        }
        mmtListing = new MyModelTable(tableauExternal, sColumnExternal);
        jtTable.setModel(mmtListing);
        jtTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtTable.setColumnSelectionAllowed(false);
        jtTable.setCellSelectionEnabled(false);
        jtTable.setRowSelectionAllowed(true);

        sorterExternal = new TableRowSorter<>(mmtListing);
        jtTable.setRowSorter(sorterExternal);

    }

    /**
     * Méthode permettant de remettre à jour le pannel de droite pour le détail d'un intervenant, une fois une
     * modification d'intervenant effectué
     * @param external Intervenant qui a été modifié
     * @param line ligne de la table permettant de la mettre à jour
     */
    public void refreshExternalView(Intervenant external, int line){
        esDetails.updateLabel(external);
        alExternal.set(line, external);
        jtTable.clearSelection();
        mmtListing.setValueAt(external.getNom(),line, 0);
        mmtListing.setValueAt(external.getPrenom(),line, 1);
        mmtListing.setValueAt(external.getTelephone(),line, 3);
        jtTable.updateUI();
    }

    /**
     * Méthode permettant de remettre à jour le pannel de droite pour le détail d'un employés, une fois une
     * modification d'intervenant effectué
     * @param personne personnel qui a été modifié
     * @param line ligne de la table permettant de la mettre à jour
     */
    public void refreshStaffView(Personne personne, int line){
        psDetails.updateLabel(personne);
        alPersonnes.set(line, personne);
        jtTable.clearSelection();
        mmtListing.setValueAt(personne.getNom(),line, 0);
        mmtListing.setValueAt(personne.getPrenom(),line, 1);
        jtTable.updateUI();
    }

    /**
     * Méthode permettant de rajouter une personne à la JTable des intervenants
     * @param external Intervenant qui a été rajouter à la BDD
     */
    public void addExternalTab(Intervenant external){
        if(alExternal == null){
            alExternal = new ArrayList<>();
        }
        System.out.println(alExternal.size());
        mmtListing.addRow(external.toVector());
        alExternal.add(external);
        jtTable.clearSelection();
        jtTable.updateUI();
    }

    /**
     * Méthode permettant de rajouter une personne à la JTable des employés
     * @param personne personnel qui a été rajouter à la BDD
     */
    public void addStaffTab(Personne personne){
        mmtListing.addRow(personne.toVector());
        alPersonnes.add(personne);
        jtTable.clearSelection();
        jtTable.updateUI();
    }

    /**
     * Méthode permettant de supprimé une ligne passé en paramètre
     * @param line numéro de ligne à supprimer
     */
    public void eraseStaffRow(int line){
        mmtListing.removeRow(line);
        alPersonnes.remove(line);
        jtTable.clearSelection();
        jtTable.updateUI();
    }

    /**
     * Méthode permettant de supprimé une ligne passé en paramètre
     * @param line numéro de ligne à supprimé
     */
    public void eraseExternalRow(int line){
        mmtListing.removeRow(line);
        alExternal.remove(line);
        jtTable.clearSelection();
        jtTable.updateUI();
    }

}