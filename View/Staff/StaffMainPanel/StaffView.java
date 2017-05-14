package View.Staff.StaffMainPanel;

import Controller.Staff.StaffController;
import Model.*;
import View.GenericWindow;
import View.MyModelTable;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import com.itextpdf.text.Document;

import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
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
    private ArrayList<Personne> personnes = null;
    private Vector<Vector<Object>> tableauStaff = null;
    private Vector<Vector<Object>> tableauExternal = null;
    private ArrayList<Intervenant> alExternal = null;
    MyModelTable mmtListing = null;

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
        jtTable = new JTable();
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


        JPanel jpButtonStaff = new JPanel();
        jpLeft.add(jpButtonStaff, gbcLeft);

        JButton jbPrint = new JButton("Imprimer listing employés");

        // Listener pour capter l'appui sur le boutton d'impression
        jbPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(((JButton) e.getSource()).getText().equals("Imprimer listing employés")){
                    System.out.println("Impression listing employés");
                    Document document = new Document(PageSize.A4.rotate());
                    try {
                        PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Bureau\\Desktop\\jTable.pdf"));

                        document.open();
                        document.addTitle("Impression listing employée");
                        Paragraph p = new Paragraph("Impression listing employée");
                        p.setAlignment(1);
                        document.add(p);
                        PdfPTable pdfTable = new PdfPTable(jtTable.getColumnCount());
                        //adding table headers
                        for (int i = 0; i < jtTable.getColumnCount(); i++) {
                            pdfTable.addCell(jtTable.getColumnName(i));
                        }
                        //extracting data from the JTable and inserting it to PdfPTable
                        for (int rows = 0; rows < jtTable.getRowCount() - 1; rows++) {
                            for (int cols = 0; cols < jtTable.getColumnCount(); cols++) {
                                pdfTable.addCell(jtTable.getModel().getValueAt(rows, cols).toString());

                            }
                        }

                        document.add(pdfTable);


                    } catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    }
                    document.close();
                }
                else{
                    System.out.println("Impression listing intervenants");
                }
            }
        });

        JLabel jlSearch = new JLabel("Recherche");
        // Champ pour pouvoir filtrer
        JTextField jtFilter = new JTextField();
        jtFilter.setPreferredSize(new Dimension(90, 30));
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
                    System.out.println("ajout personnel");
                }
                else{
                    controller.addExternalView();
                    System.out.println("ajout intervenant");
                }
            }
        });

        GridBagConstraints gbcRight = new GridBagConstraints();

        // Ajout de listener pour permettre d'afficher la liste du personnel ou des intervenant externes
        jbSwitchexernalInternalStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jbSwitchexernalInternalStaff.getText().equals("Afficher les externes")) {
                    // Permet de recrée une nouvelle table
                    System.out.println("création tableau externe");
                    createExternalTab();
                    //createTab(createExternalTab(), sColumnExternal);
                    // Permet de renommer le bouton pour faire afficher les employées
                    ((JButton) e.getSource()).setText("Afficher les employés");
                    jbAdd.setText("Ajouter un intervenant");
                    jbPrint.setText("Imprimer listing intervenants");
                    jbSwitchexernalInternalStaff.repaint();
                    jbSwitchexernalInternalStaff.revalidate();
                }
                else{
                    // Permet de recrée une nouvelle table
                    System.out.println("création tableau staff");
                    createEmployeeTab();
                    //createTab(createEmployeeTab(), sColumnExternal);
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
        jpButtonStaff.setLayout(gblStockBoutton);
        GridBagConstraints gbcStaffBouton = new GridBagConstraints();

        gbcStaffBouton.insets = new Insets(0,15,0,15);
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
        //createTab(createEmployeeTab(), columnName);

        // Permet de capturer l'action du clic, et crée le panel de droite avec les détails des employées ou des externes
        jtTable.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                jpRight.removeAll();
                // Permet de choisir si l'on affiche les détails d'un employée ou d'un externe
                if(jbSwitchexernalInternalStaff.getText().equals("Afficher les externes")) {
                    jtTable.updateUI();
                    selectedRow = jtTable.getSelectedRow();
                    if (jtTable.getRowSorter() != null) {
                        selectedRow = jtTable.getRowSorter().convertRowIndexToModel(selectedRow);
                    }
                    //setRightPanelPersonnel(personnes.get(jtTable.getSelectedRow()));
                    JLabel jlDetails = new JLabel("Détails du personnel");
                    setTitleConfig(jlDetails);
                    gbcRight.gridwidth = 2;
                    gbcRight.anchor = GridBagConstraints.CENTER;
                    gbcRight.gridx = 0;
                    gbcRight.gridy = 0;
                    jpRight.add(jlDetails, gbcRight);
                    PersonnelStaf psDetail = new PersonnelStaf(controller, personnes.get(selectedRow), jtTable.getSelectedRow());
                    gbcRight.gridy = 1;
                    gbcRight.gridx = 0;
                    jpRight.add(psDetail, gbcRight);
                    jpRight.revalidate();
                    jpRight.repaint();
                }
                else{
                    selectedRow = jtTable.getSelectedRow();
                    if (jtTable.getRowSorter() != null) {
                        selectedRow = jtTable.getRowSorter().convertRowIndexToModel(selectedRow);
                    }
                    ExternalStaff external = new ExternalStaff(controller, alExternal.get(selectedRow), jtTable.getSelectedRow());
                    JLabel jlDetails = new JLabel("Détails des intervenants");
                    setTitleConfig(jlDetails);
                    gbcRight.gridx = 0;
                    gbcRight.gridy = 0;
                    gbcRight.anchor = GridBagConstraints.CENTER;
                    gbcRight.gridwidth = 2;
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
    public Vector<Vector<Object>> createEmployeeTab(){
        tableauStaff = new Vector<>();
        personnes = controller.getPersonnel();
        if(personnes!= null) {
            for (int i = 0; i < personnes.size(); ++i) {
                tableauStaff.add(personnes.get(i).toVector());
            }
        }
        mmtListing = new MyModelTable(tableauStaff, columnName);
        jtTable.setModel(mmtListing);
        //jtTable = new JTable(mmtListing);
        jtTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtTable.setColumnSelectionAllowed(false);
        jtTable.setCellSelectionEnabled(false);
        jtTable.setRowSelectionAllowed(true);

        sorterStaff = new TableRowSorter<>(mmtListing);
        jtTable.setRowSorter(sorterStaff);
        return tableauStaff;
    }

    /**
     * Méthode permettant de crée le tableau avec les Intervenants
     */
    public Vector<Vector<Object>> createExternalTab(){
        tableauExternal = new Vector<>();
        alExternal = controller.getExternal();
        if(alExternal != null) {
            for (int i = 0; i < alExternal.size(); ++i) {
                tableauExternal.add(alExternal.get(i).toVector());
            }
        }
        mmtListing = new MyModelTable(tableauExternal, sColumnExternal);
        jtTable.setModel(mmtListing);
        //jtTable = new JTable(mmtListing);
        jtTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtTable.setColumnSelectionAllowed(false);
        jtTable.setCellSelectionEnabled(false);
        jtTable.setRowSelectionAllowed(true);

        sorterExternal = new TableRowSorter<>(mmtListing);
        jtTable.setRowSorter(sorterExternal);
        return tableauExternal;
    }

    /*
    public void createTab(Vector<Vector<Object>> vObj, String[] column){
        // permet de crée le tableau de saisie qui peut-être triable
        mmtListing = new MyModelTable(vObj, column);
        System.out.println(vObj.size());
        jtTable.setModel(mmtListing);
        //jtTable = new JTable(mmtListing);
        jtTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtTable.setColumnSelectionAllowed(false);
        jtTable.setCellSelectionEnabled(false);
        jtTable.setRowSelectionAllowed(true);

        sorter = new TableRowSorter<>(mmtListing);
        jtTable.setRowSorter(sorter);
    }
    */

    /**
     * Méthode permettant de supprimé une ligne passé en paramètre
     * @param line numéro de ligne à supprimer
     */
    public void eraseStaffRow(int line){
        /*
        tableau.removeElementAt(line);
        mmtListing.removeRow(line);
        jtTable.clearSelection();
        jtTable.updateUI();
        */
        createEmployeeTab();
        //createTab(createEmployeeTab(), columnName);
    }

    /**
     * Méthode permettant de supprimé une ligne passé en paramètre
     * @param line numéro de ligne à supprimé
     */
    public void eraseExternalRow(int line){
        /*
        tableau.removeElementAt(line);
        jtTable.clearSelection();
        jtTable.updateUI();
        */
        createExternalTab();
        //createTab(createExternalTab(), sColumnExternal);
    }

}