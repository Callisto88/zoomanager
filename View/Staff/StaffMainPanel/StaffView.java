package View.Staff.StaffMainPanel;

import Controller.Staff.StaffController;
import Model.*;
import View.GenericWindow;
import View.MyModelTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

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
    private ArrayList<Personne> personnes = null;
    private Vector<Vector<Object>> tableau = null;
    private ArrayList<Intervenant> alExternal = null;

    /**
     * Constructeur permettant d'instancier toutes les fenêtre composant la fenêtre principale
     *
     * @param persControl Controlleur de la fenêtre pour permettre de lui faire remonter les informations utiles.
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
        //setButtonConfig(jbPrint);

        // Listener pour capter l'appui sur le boutton
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

        // Bouton pour pouvoir ajouter du personnel
        JButton jbAdd = new JButton("Ajouter un employé");
        //setButtonConfig(jbAdd);

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

        JButton jbSwitchexernalInternalStaff = new JButton("Afficher les externes");
        //setButtonConfig(jbSwitchexernalInternalStaff);

        // Ajout de listener pour permettre d'afficher la liste du personnel ou des intervenant externes
        jbSwitchexernalInternalStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jbSwitchexernalInternalStaff.getText().equals("Afficher les externes")) {
                    // Permet de mettre à jour le tableau pour les externes
                    createExternalTab();
                    // Permet de recrée une nouvelle table
                    jtTable.setModel(new MyModelTable(tableau, sColumnExternal));
                    // Permet de renommer le bouton pour faire afficher les employées
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

        GridBagLayout gblStockBoutton = new GridBagLayout();
        jpButtonStock.setLayout(gblStockBoutton);
        GridBagConstraints gbcStockBouton = new GridBagConstraints();

        gbcStockBouton.insets = new Insets(0,15,0,15);
        gbcStockBouton.gridx = 0;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbPrint, gbcStockBouton);

        gbcStockBouton.gridx = 1;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbAdd, gbcStockBouton);

        gbcStockBouton.gridx = 2;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbSwitchexernalInternalStaff, gbcStockBouton);

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        gbcLeft.weighty = 20;

        JPanel jpTableStock = new JPanel();
        jpTableStock.setPreferredSize(new Dimension(800, 500));

        // Permet de mettre à jour le tableau pour les employées
        createEmployeeTab();
        // permet de crée le tableau de saisie
        jtTable = new JTable(new MyModelTable(tableau, columnName));

        // Permet de capturer l'action du clic, et crée le panel de droite avec les détails des employées ou des externes
        jtTable.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                jpRight.removeAll();
                // Permet de choisir si l'on affiche les détails d'un employée ou d'un externe
                if(jbSwitchexernalInternalStaff.getText().equals("Afficher les externes")) {
                    System.out.println(jtTable.getSelectedRow());
                    System.out.println(tableau.get(jtTable.getSelectedRow()));
                    System.out.println(jbSwitchexernalInternalStaff.getText());
                    //setRightPanelPersonnel(personnes.get(jtTable.getSelectedRow()));
                    PersonnelStaf psDetail = new PersonnelStaf(controller, personnes.get(jtTable.getSelectedRow()));
                    jpRight.add(psDetail);
                    jpRight.revalidate();
                    jpRight.repaint();
                }
                else{
                    ExternalStaff external = new ExternalStaff(controller, alExternal.get(jtTable.getSelectedRow()));
                    jpRight.add(external);
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

        JScrollPane jspStock = new JScrollPane(jtTable);
        jspStock.setPreferredSize(new Dimension(700, 450));

        // permet d'ajouter le tableau pour obtenir une liste déroulante (!!!!pas sur)
        jpTableStock.add(jspStock);
        jpLeft.add(jpTableStock, gbcLeft);


        jpRight = new JPanel();
        jpRight.setLayout(new BoxLayout(jpRight, BoxLayout.Y_AXIS));

        jpMainPanel.add(jpRight);

        JLabel jlLowStock = new JLabel("Détails du personnel");
        jlLowStock.setBorder(new EmptyBorder(10,0,0,0));

        jlLowStock.setHorizontalAlignment(SwingConstants.CENTER);
        setTitleConfig(jlLowStock);
        jpRight.add(jlLowStock, BorderLayout.NORTH);


        JLabel test1 = new JLabel("Test");
        jpRight.add(test1, BorderLayout.CENTER);

        configFrame(getJfFrame(), this);
    }

    public void createEmployeeTab(){
        tableau = new Vector<>();
        personnes = controller.getPersonnel();
        for(int i = 0; i < personnes.size(); ++i){
            tableau.add(personnes.get(i).toVector());
        }
    }

    public void createExternalTab(){
        tableau = new Vector<>();
        alExternal = controller.getExternal();
        for(int i = 0; i < alExternal.size(); ++i){
            tableau.add(alExternal.get(i).toVector());
        }
    }



}