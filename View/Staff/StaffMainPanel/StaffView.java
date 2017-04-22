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

        JButton jbPrint = new JButton("Imprimer la liste des employées");
        setButtonConfig(jbPrint);

        jbPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Impression");
            }
        });

        // Bouton pour pouvoir ajouter du personnel
        JButton jbCreateListOrder = new JButton("Ajouter du personnel");
        setButtonConfig(jbCreateListOrder);

        // Listener pour pouvoir 
        jbCreateListOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addView();
                System.out.println("ajout personnel");
            }
        });

        JButton jbSwitchexernalInternalStaff = new JButton("Afficher les externes");
        //setButtonConfig(jbSwitchexernalInternalStaff);

        // Ajout de listener pour permettre d'afficher la liste du personnel ou des intervenant externes
        jbSwitchexernalInternalStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jbSwitchexernalInternalStaff.getText().equals("Afficher les externes")) {
                    // pour les test
                    /********************************************/
                    alExternal = new ArrayList<>();
                    Vector<Vector<Object>> vInterneExterne = new Vector<>();
                    Intervenant i1 = new Intervenant("Test", "Bob", "Dylan", 1, "Bob.dylan@test.ch", "+417845123698");
                    Intervenant i2 = new Intervenant("ghfad", "gfasd", "ztwr", 4, "zgdf.hfasd@ter.ch", "+4171236547998");
                    Intervenant i3 = new Intervenant("poiurz", "ikuuzr", "errew ", 5, "pouz.fds@HJZRT.jr", "+417126873698");
                    vInterneExterne.add(i1.toVector());
                    alExternal.add(i1);
                    alExternal.add(i2);
                    alExternal.add(i3);
                    vInterneExterne.add(i2.toVector());
                    vInterneExterne.add(i3.toVector());
                    /*********************************************/
                    // Permet de recrée une nouvelle table
                    jtTable.setModel(new MyModelTable(vInterneExterne, sColumnExternal));
                    // Permet de renommer le bouton pour faire afficher les employées
                    ((JButton) e.getSource()).setText("Afficher les employés");
                    jbSwitchexernalInternalStaff.repaint();
                    jbSwitchexernalInternalStaff.revalidate();
                }
                else{
                    for(int i = 0; i < tab.size(); ++i){
                        tableau.add(personnes.get(i).toVector());
                    }
                    // Permet de recrée une nouvelle table
                    jtTable.setModel(new MyModelTable(tableau, columnName));
                    // Permet de renommer le bouton pour faire afficher les employées
                    ((JButton) e.getSource()).setText("Afficher les externes");
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
        jpButtonStock.add(jbCreateListOrder, gbcStockBouton);

        gbcStockBouton.gridx = 2;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbSwitchexernalInternalStaff, gbcStockBouton);

        tableau = new Vector<>();

        // permet d'ajouter au tableau les champs choisis par la méthode ToString des Personne
        for(int i = 0; i < tab.size(); ++i){
            tableau.add(personnes.get(i).toVector());
        }

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        gbcLeft.weighty = 20;

        JPanel jpTableStock = new JPanel();
        jpTableStock.setPreferredSize(new Dimension(800, 500));

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
        //jpRight.setLayout(flRight);
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

    // obsolète
    public void revalidateView(){
        this.setVisible(true);
        jpMainPanel.setVisible(true);
        jpMainPanel.revalidate();
        getJfFrame().setVisible(true);
    }

    /**
     * Méthode permettant d'afficher les informations d'un personnel sur le panneau de droite
     * une fois que l'utilisateur clique sur une colonne
     * @param personne personne dont on souhaite afficher les informations
     */
    private void setRightPanelPersonnel(Personne personne){

        // Ajout du champ de détails pour le nom
        JPanel jpLastName = new JPanel();
        JLabel jlLastName = new JLabel("Nom : ");
        JLabel jlLastNameInfo = new JLabel(personne.getNom());
        jpLastName.add(jlLastName);
        jpLastName.add(Box.createHorizontalStrut(50));
        jpLastName.add(jlLastNameInfo);
        jpRight.add(jpLastName);

        // Ajout du champ de détails pour le prénom
        JPanel jpFirstName = new JPanel();
        JLabel jlFirstName = new JLabel("Prénom : ");
        JLabel jlFirstNameInfo = new JLabel(personne.getPrenom());
        jpFirstName.add(jlFirstName);
        jpFirstName.add(Box.createHorizontalStrut(50));
        jpFirstName.add(jlFirstNameInfo);
        jpRight.add(jpFirstName);

        // Ajout du champ de détails pour la date de naissance
        JPanel jpBirthday = new JPanel();
        JLabel jlBirthday = new JLabel("Date de naissance : ");
        JLabel jlBirthdayInfo = new JLabel(personne.getDateNaissance().toString());
        jpBirthday.add(jlBirthday);
        jpBirthday.add(Box.createHorizontalStrut(50));
        jpBirthday.add(jlBirthdayInfo);
        jpRight.add(jpBirthday);

        // Ajout du champ de détails pour le numéro AVS
        JPanel jpAVS = new JPanel();
        JLabel jlAVS = new JLabel("Numéro AVS : ");
        JLabel jlAVSInfo = new JLabel(personne.getNoAVS());
        jpAVS.add(jlAVS);
        jpAVS.add(Box.createHorizontalStrut(50));
        jpAVS.add(jlAVSInfo);
        jpRight.add(jpAVS);

        // Ajout du champ de détails pour l'email
        JPanel jpEmail = new JPanel();
        JLabel jlEmail = new JLabel("E-Mail : ");
        JLabel jlEmailInfo = new JLabel(personne.getEmail());
        jpEmail.add(jlEmail);
        jpEmail.add(Box.createHorizontalStrut(50));
        jpEmail.add(jlEmailInfo);
        jpRight.add(jpEmail);

        // Ajout du champ de détails pour l'adresse
        JPanel jpAddress = new JPanel();
        JLabel jlAddress = new JLabel("Adresse : ");
        JLabel jlAddressInfo = new JLabel(/*personne.getAdresse()*/ "Adresse");
        jpAddress.add(jlAddress);
        jpAddress.add(Box.createHorizontalStrut(50));
        jpAddress.add(jlAddressInfo);
        jpRight.add(jpAddress);

        // Ajout du champ de détails pour la ville
        JPanel jpCity = new JPanel();
        JLabel jlCity = new JLabel("Ville : ");
        JLabel jlCityInfo = new JLabel(/*personne.getAdresse()*/);
        jpCity.add(jlCity);
        jpCity.add(Box.createHorizontalStrut(50));
        jpCity.add(jlCityInfo);
        jpRight.add(jpCity);

        // Ajout du champ de détails pour le npa
        JPanel jpNPA = new JPanel();
        JLabel jlNPA = new JLabel("NPA : ");
        JLabel jlNPAInfo = new JLabel(/*personne.getAdresse()*/);
        jpNPA.add(jlNPA);
        jpNPA.add(Box.createHorizontalStrut(50));
        jpNPA.add(jlNPAInfo);
        jpRight.add(jpNPA);

        // Ajout du champ de détails pour le numéro de téléphone
        JPanel jpPhone = new JPanel();
        JLabel jlPhone = new JLabel("Téléphone : ");
        JLabel jlPhoneInfo = new JLabel(personne.getTelephone());
        jpPhone.add(jlPhone);
        jpPhone.add(Box.createHorizontalStrut(50));
        jpPhone.add(jlPhoneInfo);
        jpRight.add(jpPhone);

        // Ajout du champ de détails pour la date de début
        JPanel jpBeginingDate = new JPanel();
        JLabel jlBeginingDate = new JLabel("E-Mail : ");
        JLabel jlBeginingDateInfo = new JLabel(personne.getEmail());
        jpBeginingDate.add(jlBeginingDate);
        jpBeginingDate.add(Box.createHorizontalStrut(50));
        jpBeginingDate.add(jlBeginingDateInfo);
        jpRight.add(jpBeginingDate);

        // Ajout du champ de détails pour le responsable
        JPanel jpAdvisor = new JPanel();
        JLabel jlAdvisor = new JLabel("Responsable : ");
        JLabel jlAdvisorInfo = new JLabel(/*personne.getResponsable()*/ "Responsable");
        jpAdvisor.add(jlAdvisor);
        jpAdvisor.add(Box.createHorizontalStrut(50));
        jpAdvisor.add(jlAdvisorInfo);
        jpRight.add(jpAdvisor);

        // Ajout du champ de détails pour le statut
        JPanel jpStatut = new JPanel();
        JLabel jlStatut = new JLabel("Statut : ");
        JLabel jlStatutInfo = new JLabel(personne.getStatut());
        jpStatut.add(jlStatut);
        jpStatut.add(Box.createHorizontalStrut(50));
        jpStatut.add(jlStatutInfo);
        jpRight.add(jpStatut);

        // Ajout du champ de détails pour le type de contrat
        JPanel jpContract = new JPanel();
        JLabel jlContract = new JLabel("Contrat : ");
        JLabel jlContractInfo = new JLabel(personne.getTypeContrat());
        jpContract.add(jlContract);
        jpContract.add(Box.createHorizontalStrut(50));
        jpContract.add(jlContractInfo);
        jpRight.add(jpContract);

        // panel permettant de mettre les trois bouttons de suppression, modification et d'ajout de tache
        JPanel jpButtons = new JPanel();

        //Ajout du bouton de suppression de l'employé actuel
        JButton jbDelete = new JButton("Suppression de l'employé");
        jpButtons.add(jbDelete);
        jpButtons.add(Box.createHorizontalStrut(20));
        jbDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Suppression de personnel");
                controller.erreurPopup("Voulez vous réelement supprimer " + personne.getPrenom() + " " + personne.getNom());
            }
        });


        // Ajout du bouton d'édition du personnel
        JButton jbEdit = new JButton("Modification");
        jpButtons.add(jbEdit);
        jpButtons.add(Box.createHorizontalStrut(20));
        jbEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("modification du personnel");
                controller.modifyView(personne);
            }
        });

        // Ajout du bouton d'assignation de taches
        JButton jbAssignTask = new JButton("Assignation de tâches");
        jpButtons.add(jbAssignTask);
        jbAssignTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Assignation de tâches");
                controller.assignTaskView(personne);
            }
        });

        // Ajout des bouttons dans le panel de droite
        jpRight.add(jpButtons);

    }
}