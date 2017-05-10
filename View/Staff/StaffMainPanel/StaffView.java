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
    private static TableRowSorter<MyModelTable> table;
    private TableRowSorter<MyModelTable> sorter = null;
    private ArrayList<Personne> personnes = null;
    private Vector<Vector<Object>> tableau = null;
    private ArrayList<Intervenant> alExternal = null;

    /**
     * Constructeur permettant d'instancier toutes les fenêtre composant la fenêtre principale
     *
     * @param persControl Controlleur de la fenêtre pour permettre de lui faire remonter les informations utiles.
     * @param tab Arraylist de tous les employés dans la base de donnée
     */
    public StaffView(StaffController persControl, ArrayList<Personne> tab) {
        super("Personnel");
        controller = persControl;
        personnes = tab;
        GridBagLayout gblLeft = new GridBagLayout();
        GridBagConstraints gbcLeft  = new GridBagConstraints();

        JPanel jpLeft = new JPanel();
        jpLeft.setLayout(gblLeft);

        JPanel jpLeftTitle = new JPanel();

        // Permet d'ajouter le Panel de Gauche qui contiendra le tableau et les bouton d'impression et d'ajout de personnel
        jpMainPanel.add(jpLeft);

        JLabel jlStock = new JLabel("Listes du personnel");
        setTitleConfig(jlStock);
        jpLeftTitle.add(jlStock);

        gbcLeft.fill = GridBagConstraints.CENTER;
        gbcLeft.gridx = 0;
        gbcLeft.anchor = GridBagConstraints.NORTH;
        gbcLeft.gridy = 0;
        jpLeft.add(jpLeftTitle,gbcLeft);

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 1;


        JPanel jpButtonStock = new JPanel();
        jpLeft.add(jpButtonStock, gbcLeft);

        JButton jbPrint = new JButton("Imprimer listing employés");

        // Listener pour capter l'appui sur le boutton d'impression
        jbPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(((JButton) e.getSource()).getText().equals("Imprimer listing employés")){
                    System.out.println("Impression listing employés");
                }
                else{
                    System.out.println("Impression listing intervenants");
                }
            }
        });

        // Champ pour pouvoir filtrer
        JTextField jtFilter = new JTextField();
        jtFilter.setPreferredSize(new Dimension(90, 30));
        jtFilter.setToolTipText("Recherche");

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
                sorter.setRowFilter(RowFilter.regexFilter(Pattern.quote(jtFilter.getText())));
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
                    System.out.println("ajout personnel");
                }
                else{
                    controller.addExternalView();
                    System.out.println("ajout intervenant");
                }
            }
        });

        GridBagConstraints gbcRight = new GridBagConstraints();

        JButton jbSwitchexernalInternalStaff = new JButton("Afficher les externes");

        // Ajout de listener pour permettre d'afficher la liste du personnel ou des intervenant externes
        jbSwitchexernalInternalStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jbSwitchexernalInternalStaff.getText().equals("Afficher les externes")) {
                    // Permet de mettre à jour le tableau pour les externes
                    createExternalTab();
                    // Permet de recrée une nouvelle table
                    createTab(tableau, sColumnExternal);
                    jtTable.setModel(new MyModelTable(tableau, sColumnExternal));
                    // nmhjtr55555555555555Permet de renommer le bouton pour faire afficher les employées
                    ((JButton) e.getSource()).setText("Afficher les employés");
                    jbAdd.setText("Ajouter un intervenant");
                    jbPrint.setText("Imprimer listing intervenants");
                    jbSwitchexernalInternalStaff.repaint();
                    jbSwitchexernalInternalStaff.revalidate();
                }
                else{
                    // Permet de mettre à jour le tableau pour le personnel
                    createEmployeeTab();
                    // Permet de recrée une nouvelle table
                    jtTable.setModel(new MyModelTable(tableau, columnName));
                    // Permet de renommer le bouton pour faire afficher les employées
                    ((JButton) e.getSource()).setText("Afficher les externes");
                    jbAdd.setText("Ajouter un employé");
                    jbPrint.setText("Imprimer listing employés");
                    jbSwitchexernalInternalStaff.repaint();
                    jbSwitchexernalInternalStaff.revalidate();
                }
            }
        });

        // Permet d'ajouter les bouton ainsi que le champ de filtre en haut de notre fenêtre
        GridBagLayout gblStockBoutton = new GridBagLayout();
        jpButtonStock.setLayout(gblStockBoutton);
        GridBagConstraints gbcStaffBouton = new GridBagConstraints();

        gbcStaffBouton.insets = new Insets(0,15,0,15);
        gbcStaffBouton.gridx = 0;
        gbcStaffBouton.gridy = 0;
        jpButtonStock.add(jbPrint, gbcStaffBouton);

        gbcStaffBouton.gridx = 1;
        jpButtonStock.add(jbAdd, gbcStaffBouton);

        gbcStaffBouton.gridx = 2;
        jpButtonStock.add(jbSwitchexernalInternalStaff, gbcStaffBouton);

        gbcStaffBouton.gridx = 3;
        jpButtonStock.add(jtFilter, gbcStaffBouton);

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        gbcLeft.weighty = 20;

        JPanel jpTableStaff = new JPanel();
        jpTableStaff.setPreferredSize(new Dimension(800, 500));

        // Permet de mettre à jour le tableau pour les employées
        createEmployeeTab();
        // permet de crée le tableau de saisie qui peut-être triable
        MyModelTable mmtListing = new MyModelTable(tableau, columnName);
        jtTable = new JTable(mmtListing);
        jtTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtTable.setColumnSelectionAllowed(false);
        jtTable.setCellSelectionEnabled(false);
        jtTable.setRowSelectionAllowed(true);

        TableRowSorter<MyModelTable> sorter = new TableRowSorter<>(mmtListing);
        jtTable.setRowSorter(sorter);

        // Permet de capturer l'action du clic, et crée le panel de droite avec les détails des employées ou des externes
        jtTable.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                jpRight.removeAll();
                // Permet de choisir si l'on affiche les détails d'un employée ou d'un externe
                if(jbSwitchexernalInternalStaff.getText().equals("Afficher les externes")) {
                    if (jtTable.getRowSorter() != null) {
                        selectedRow = jtTable.getRowSorter().convertRowIndexToModel(selectedRow);
                    }
                    System.out.println(jtTable.getSelectedRow());
                    System.out.println(tableau.get(jtTable.getSelectedRow()));
                    System.out.println(jbSwitchexernalInternalStaff.getText());
                    //setRightPanelPersonnel(personnes.get(jtTable.getSelectedRow()));
                    JLabel jlDetails = new JLabel("Détails du personnel");
                    setTitleConfig(jlDetails);
                    gbcRight.gridy = 0;
                    jpRight.add(jlDetails, gbcRight);
                    PersonnelStaf psDetail = new PersonnelStaf(controller, personnes.get(jtTable.getSelectedRow()), jtTable.getSelectedRow());
                    gbcRight.gridy = 1;
                    gbcRight.gridx = 0;
                    jpRight.add(psDetail, gbcRight);
                    jpRight.revalidate();
                    jpRight.repaint();
                }
                else{
                    if (jtTable.getRowSorter() != null) {
                        selectedRow = jtTable.getRowSorter().convertRowIndexToModel(selectedRow);
                    }
                    ExternalStaff external = new ExternalStaff(controller, alExternal.get(jtTable.getSelectedRow()), jtTable.getSelectedRow());
                    JLabel jlDetails = new JLabel("Détails des intervenants");
                    setTitleConfig(jlDetails);
                    gbcRight.gridx = 0;
                    gbcRight.gridy = 0;
                    jpRight.add(jlDetails, gbcRight);
                    gbcRight.gridy = 1;
                    jpRight.add(external, gbcRight);
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

        gbcRight.gridx = 0;
        gbcRight.gridy = 0;

        jpRight = new JPanel();
        jpRight.setLayout(new BoxLayout(jpRight, BoxLayout.Y_AXIS));

        jpMainPanel.add(jpRight);

        JLabel jlDetails = new JLabel("Détails du personnel");
        setTitleConfig(jlDetails);
        jpRight.add(jlDetails, gbcRight);


        configFrame(getJfFrame(), this);
    }

    /**
     * Méthode permettant de crée le tableau avec les employées
     */
    public void createEmployeeTab(){
        tableau = new Vector<>();
        personnes = controller.getPersonnel();
        for(int i = 0; i < personnes.size(); ++i){
            tableau.add(personnes.get(i).toVector());
        }
    }

    /**
     * Méthode permettant de crée le tableau avec les Intervenants
     */
    public void createExternalTab(){
        tableau = new Vector<>();
        alExternal = controller.getExternal();
        for(int i = 0; i < alExternal.size(); ++i){
            tableau.add(alExternal.get(i).toVector());
        }
    }

    public void createTab(Vector<Vector<Object>> vObj, String[] column){

    }

    /**
     * Méthode permettant de supprimé une ligne passé en paramètre
     * @param line numéro de ligne à supprimer
     */
    public void eraseStaffRow(int line){
        jtTable.remove(line);
    }

    /**
     * Méthode permettant de supprimé une ligne passé en paramètre
     * @param line numéro de ligne à supprimé
     */
    public void eraseExternalRow(int line){
        jtTable.remove(line);
    }

}