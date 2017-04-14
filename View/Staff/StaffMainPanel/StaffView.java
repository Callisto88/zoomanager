package View.Staff.StaffMainPanel;

import Controller.Staff.StaffController;
import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Personne;
import Model.Stock;
import View.GenericWindow;
import View.MyModelTable;

import javax.management.Query;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Andre on 10.03.2017.
 * Fenêtre principale pour les employée
 */
public class StaffView extends GenericWindow {
    private StaffController controller = null;
    private String[] columnName ={"Nom", "Prénom"};

    /**
     * Constructeur permettant d'instancier toutes les fenêtre composant la fenêtre principale
     *
     * @param persControl Controlleur de la fenêtre pour permettre de lui faire remonter les informations utiles.
     */
    public StaffView(StaffController persControl) {
        super("Personnel");
        controller = persControl;
        GridBagLayout gblLeft = new GridBagLayout();
        GridBagConstraints gbcLeft  = new GridBagConstraints();

        JPanel jpLeft = new JPanel();
        jpLeft.setLayout(gblLeft);

        JPanel jpLeftTitle = new JPanel();

        jpMainPanel.add(jpLeft);

        JLabel jlStock = new JLabel("Listes du personnel");
        setTitleConfig(jlStock);
        jpLeftTitle.add(jlStock);

        gbcLeft.fill = GridBagConstraints.CENTER;
        gbcLeft.gridx = 0;
        gbcLeft.anchor = GridBagConstraints.NORTH;
        gbcLeft.gridy = 0;
        //gbcLeft.insets = new Insets(15,15,15,15);
        jpLeft.add(jpLeftTitle,gbcLeft);

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 1;


        JPanel jpButtonStock = new JPanel();
        jpLeft.add(jpButtonStock, gbcLeft);

        JButton jbPrint = new JButton("Imprimer la liste des employées");
        setButtonConfig(jbPrint);

        jbPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Impression");
            }
        });

        JButton jbCreateListOrder = new JButton("Ajouter du personnel");
        setButtonConfig(jbCreateListOrder);

        jbCreateListOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addView();
                System.out.println("ajout personnel");
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
        jpButtonStock.add(jbCreateListOrder, gbcStockBouton);


        DBInteraction querry = null;
        try {
            querry = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        ArrayList<Personne> tab = null;

        try{
            tab = querry.selAllFirstLastNameEmployee();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
        }

        Vector<Object> personnes = new Vector<>();
        for (Personne p : tab) {
            personnes.add(p.toVector());
        }

        Vector<Vector<Object>> tableaux = new Vector<>();
        tableaux.add(personnes);
/*
        for(int i = 0; i < data.length; ++i){
            for(int j = 0; j < data[i].length; ++i){
                System.out.println(data[j].length);
                System.out.println(tab[i][j]);
                data[i][j] = tab[i][j];
            }
        }
        */

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        gbcLeft.weighty = 20;


        JPanel jpTableStock = new JPanel();
        jpTableStock.setPreferredSize(new Dimension(800, 500));

        JTable jtTable = new JTable(new MyModelTable(tableaux, columnName)){
            public boolean isCellEditable(int row, int column){
                if(column == 4){
                    return true;
                };
                return false;
            }
        };

        jtTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

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

        //MyPersonnalJTable jtTable = new MyPersonnalJTable();
        Dimension d = jtTable.getPreferredScrollableViewportSize();
        d.width = jtTable.getPreferredSize().width;
        jtTable.setPreferredScrollableViewportSize(d);

        JScrollPane jspStock = new JScrollPane(jtTable);
        jspStock.setPreferredSize(new Dimension(700, 450));


        jpTableStock.add(jspStock);
        jpLeft.add(jpTableStock, gbcLeft);



        // Liste déroulante avec les noms et prénom des employé
        /*JComboBox boxChoicePersonne = new JComboBox();
        JPanel choicePersonnel = new JPanel();
        // Requête pour récupérer les nom et prénoms des employé
        boxChoicePersonne.addItem("Guillaume Tell");
        boxChoicePersonne.addItem("Arsène Lupin");
        choicePersonnel.add(boxChoicePersonne);
        top.add(choicePersonnel);*/
/*
        top.add(Box.createHorizontalStrut(150));

        // Création de notre boutton d'ajout de personnel
        JButton addPersonnelButton = new JButton("Ajout de personnel");
        JPanel addPersonnel = new JPanel();
        addPersonnel.add(addPersonnelButton);
        top.add(addPersonnel);
        this.add(top, BorderLayout.NORTH);

        // permet de demander l'instanciation de la fenêtre d'ajout de personnel
        addPersonnelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addView();
                System.out.println("ajout personnel");
            }
        });

        StaffLabel label = new StaffLabel();
        //this.add(label, BorderLayout.WEST);

        StaffInfo personnel = new StaffInfo();
        //this.add(personnel, BorderLayout.CENTER);

        //FlowLayout test = new FlowLayout(FlowLayout.CENTER, 20, 20);
        //test.addLayoutComponent("label",label);
        //test.addLayoutComponent("info", personnel);

        JPanel test = new JPanel();
        test.add(label, JPanel.RIGHT_ALIGNMENT);
        test.add(personnel, JPanel.LEFT_ALIGNMENT);

        this.add(test, BorderLayout.WEST);

        StaffTask taskView = new StaffTask();
        taskView.setBackground(Color.GREEN);
        this.add(taskView, BorderLayout.CENTER);


        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
        // Bouton de modification
        JButton modify = new JButton("Modification");
        JPanel panelModify = new JPanel();
        panelModify.add(modify);
        bottom.add(modify);
        bottom.add(Box.createHorizontalStrut(150));

        // permet de demander l'instanciation de la fenêtre de modification de personnel
        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("modification personnel");
                Personne personne = new Personne();
                personne.setNom("paul");
                personne.setPrenom("marcel");
                personne.setAdresse(1);
                personne.setResponsable(3);
                personne.setEmail("marcel.paul@test.ch");
                personne.setTelephone("1234567890");
                controller.modifyView(personne);
            }
        });

        // Bouton d'assignation de taches
        JButton task = new JButton("Ajout de Tâches");
        JPanel taskPanel = new JPanel();
        taskPanel.add(task);
        bottom.add(taskPanel);

        // permet de demander l'instanciation de la fenêtre d'assignation de tâches au personnel
        task.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Assignation taches");
                controller.assignTaskView();
            }
        });

        this.add(bottom, BorderLayout.SOUTH);
        */
        BorderLayout gblRight = new BorderLayout();

        JPanel jpRight = new JPanel();
        jpRight.setLayout(gblRight);

        jpMainPanel.add(jpRight);

        JLabel jlLowStock = new JLabel("Détails du personnel");
        jlLowStock.setBorder(new EmptyBorder(10,0,0,0));

        jlLowStock.setHorizontalAlignment(SwingConstants.CENTER);
        setTitleConfig(jlLowStock);
        jpRight.add(jlLowStock, BorderLayout.NORTH);


        JLabel test1 = new JLabel("test");
        jpRight.add(test1, BorderLayout.CENTER);

        configFrame(getJfFrame(), this);
    }

    public void revalidateView(){
        this.setVisible(true);
        jpMainPanel.setVisible(true);
        jpMainPanel.revalidate();
        getJfFrame().setVisible(true);
    }

    private void setRightPanel(Personne personne){

        // Ajout du champ de détails pour le nom
        JPanel jpLastName = new JPanel();
        JLabel jlLastName = new JLabel("Nom : ");
        JLabel jlLastNameInfo = new JLabel(personne.getNom());
        jpLastName.add(jlLastName);
        jpLastName.add(Box.createHorizontalStrut(50));
        jpLastName.add(jlLastNameInfo);

        // Ajout du champ de détails pour le prénom
        JPanel jpFirstName = new JPanel();
        JLabel jlFirstName = new JLabel("Prénom : ");
        JLabel jlFirstNameInfo = new JLabel(personne.getNom());
        jpFirstName.add(jlFirstName);
        jpFirstName.add(Box.createHorizontalStrut(50));
        jpFirstName.add(jlFirstNameInfo);

        // Ajout du champ de détails pour la date de naissance
        JPanel jpBirthday = new JPanel();
        JLabel jlBirthday = new JLabel("Date de naissance : ");
        JLabel jlBirthdayInfo = new JLabel(personne.getNom());
        jpBirthday.add(jlBirthday);
        jpBirthday.add(Box.createHorizontalStrut(50));
        jpBirthday.add(jlBirthdayInfo);

        // Ajout du champ de détails pour le numéro AVS
        JPanel jpAVS = new JPanel();
        JLabel jlAVS = new JLabel("Numéro AVS : ");
        JLabel jlAVSInfo = new JLabel(personne.getNom());
        jpAVS.add(jlAVS);
        jpAVS.add(Box.createHorizontalStrut(50));
        jpAVS.add(jlAVSInfo);

        // Ajout du champ de détails pour l'email
        JPanel jpEmail = new JPanel();
        JLabel jlEmail = new JLabel("E-Mail : ");
        JLabel jlEmailInfo = new JLabel(personne.getNom());
        jpEmail.add(jlEmail);
        jpEmail.add(Box.createHorizontalStrut(50));
        jpEmail.add(jlEmailInfo);

        // Ajout du champ de détails pour l'adresse
        JPanel jpAddress = new JPanel();
        JLabel jlAddress = new JLabel("Adresse : ");
        JLabel jlAddressInfo = new JLabel(personne.getNom());
        jpAddress.add(jlAddress);
        jpAddress.add(Box.createHorizontalStrut(50));
        jpAddress.add(jlAddressInfo);

        // Ajout du champ de détails pour la ville
        JPanel jpCity = new JPanel();
        JLabel jlCity = new JLabel("Ville : ");
        JLabel jlCityInfo = new JLabel(personne.getNom());
        jpCity.add(jlCity);
        jpCity.add(Box.createHorizontalStrut(50));
        jpCity.add(jlCityInfo);

        // Ajout du champ de détails pour le npa
        JPanel jpNPA = new JPanel();
        JLabel jlNPA = new JLabel("NPA : ");
        JLabel jlNPAInfo = new JLabel(personne.getNom());
        jpNPA.add(jlNPA);
        jpNPA.add(Box.createHorizontalStrut(50));
        jpNPA.add(jlNPAInfo);

        // Ajout du champ de détails pour le numéro de téléphone
        JPanel jpPhone = new JPanel();
        JLabel jlPhone = new JLabel("Téléphone : ");
        JLabel jlPhoneInfo = new JLabel(personne.getNom());
        jpPhone.add(jlPhone);
        jpPhone.add(Box.createHorizontalStrut(50));
        jpPhone.add(jlPhoneInfo);

        // Ajout du champ de détails pour la date de début
        JPanel jpBeginingDate = new JPanel();
        JLabel jlBeginingDate = new JLabel("E-Mail : ");
        JLabel jlBeginingDateInfo = new JLabel(personne.getNom());
        jpBeginingDate.add(jlBeginingDate);
        jpBeginingDate.add(Box.createHorizontalStrut(50));
        jpBeginingDate.add(jlBeginingDateInfo);

        // Ajout du champ de détails pour le responsable
        JPanel jpAdvisor = new JPanel();
        JLabel jlAdvisor = new JLabel("Responsable : ");
        JLabel jlAdvisorInfo = new JLabel(personne.getNom());
        jpAdvisor.add(jlAdvisor);
        jpAdvisor.add(Box.createHorizontalStrut(50));
        jpAdvisor.add(jlAdvisorInfo);

        // Ajout du champ de détails pour le statut
        JPanel jpStatut = new JPanel();
        JLabel jlStatut = new JLabel("Statut : ");
        JLabel jlStatutInfo = new JLabel(personne.getNom());
        jpStatut.add(jlStatut);
        jpStatut.add(Box.createHorizontalStrut(50));
        jpStatut.add(jlStatutInfo);

        // Ajout du champ de détails pour le type de contrat
        JPanel jpContract = new JPanel();
        JLabel jlContract = new JLabel("E-Mail : ");
        JLabel jlContractInfo = new JLabel(personne.getNom());
        jpContract.add(jlContract);
        jpContract.add(Box.createHorizontalStrut(50));
        jpContract.add(jlContractInfo);
    }
}