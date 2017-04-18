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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Andre on 10.03.2017.
 * Fenêtre principale pour les employée
 */
public class StaffView extends GenericWindow {
    private StaffController controller = null;
    private String[] columnName ={"Nom", "Prénom", "Numéro AVS", "Date de Naissance"};
    private JPanel jpRight = null;

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

        // Création de l'objet pour interragir avec la DB
        DBInteraction querry = null;
        try {
            querry = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        ArrayList<Personne> tab = null;

        // Permet de récupérer l'Arraylist du personnel
        try{
            tab = querry.selAllFirstLastNameEmployee();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
        }

        ArrayList<Personne> personnes = new ArrayList<>();
        Vector<Vector<Object>> tableau = new Vector<>();
        /*
        Personne p = new Personne(15, "3769796628117", "Rachel", "Martin", 15, "rmartine@apple.com", "31-(687)486-2730", new Date(1985-12-15), 2, "externe", new Date(2000-12-15), "CDD");
        Personne p1 = new Personne(2, "3712345698117", "Betty", "Boop", 1, "bettyboop@msn.com", "41-(687)123-8514", new Date(1966-11-19), 2, "interne", new Date(2000-12-15), "CDI");
        Personne p2 = new Personne(5, "1298546628117", "Bob", "Dylan", 3, "b.d@yahoo.com", "44-(456)789-3657", new Date(1982-02-21), 3, "externe", new Date(2000-12-15), "CDD");
        Personne p3 = new Personne(7, "3769796987123", "Shrek", "Ogre", 9, "shrek@hotmail.com", "1-(684)196-3485", new Date(1935-05-30), 5, "externe", new Date(2000-12-15), "CDI");
        Personne p4 = new Personne(9, "1234567890123", "Tom", "Pouce", 11, "tom.pouce@google.com", "24-(375)642-1534", new Date(1995-06-28), 5, "externe", new Date(2000-12-15), "CDD");
        tableau.add(p.toVector());
        tableau.add(p1.toVector());
        tableau.add(p2.toVector());
        tableau.add(p3.toVector());
        tableau.add(p4.toVector());

        personnes.add(p);
        personnes.add(p1);
        personnes.add(p2);
        personnes.add(p3);
        personnes.add(p4);
        */

        // Boucle permettant de mettre dans un ArrayList les personnes pour pouvoir travailler encore dessus plus tard
        // permet d'ajouter au tableau les champs choisis par la méthode ToString des Personne
        for(int i = 0; i < tab.size(); ++i){
            personnes.add(tab.get(i));
             tableau.add(tab.get(i).toVector());
        }


        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        gbcLeft.weighty = 20;

        JPanel jpTableStock = new JPanel();
        jpTableStock.setPreferredSize(new Dimension(800, 500));

        // permet de crée le tableau de saisie
        JTable jtTable = new JTable(new MyModelTable(tableau, columnName));

        // Permet de capturer l'action du clic, et crée le panel de droite avec les détails des employées
        jtTable.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                jpRight.removeAll();
                System.out.println(jtTable.getSelectedRow());
                System.out.println(tableau.get(jtTable.getSelectedRow()));
                System.out.println();
                setRightPanel(personnes.get(jtTable.getSelectedRow()));
                jpRight.revalidate();
                jpRight.repaint();
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


        JLabel test1 = new JLabel("test");
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
    private void setRightPanel(Personne personne){

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