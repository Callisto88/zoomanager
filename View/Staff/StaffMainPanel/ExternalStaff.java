package View.Staff.StaffMainPanel;

import Controller.Error.ErrorController;
import Controller.Staff.StaffController;
import Model.DBInteraction;
import Model.Evenement;
import Model.ExceptionDataBase;
import Model.Intervenant;
import View.GenericWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by André on 22.04.2017.
 * Class permettant d'afficher les détail des personnes externes dans une fenêtre
 */
public class ExternalStaff extends JPanel{

    private GridBagConstraints gbcDetailsStaff = new GridBagConstraints();
    private int x = 0;
    private int y = 0;

    /**
     * Constructeur de la class pour afficher les détails
     * @param controller controlleur StaffController permettant de faire remonter les informations
     * @param external intervenant pour lequel on souhaite afficher les détails
     */
    public ExternalStaff(StaffController controller, Intervenant external){

        this.setLayout(new GridLayout(2,1));
        JPanel jpLeft = new JPanel();
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        jpLeft.setLayout(new GridLayout(10,1));

        // Ajout du champ de détails pour l'entreprise
        JPanel jpCompagny = new JPanel();
        JLabel jlCompagny = new JLabel("Entreprise : ");
        JLabel jlCompagnyInfo = new JLabel(external.getEntreprise());
        jpCompagny.add(jlCompagny);
        jpCompagny.add(Box.createHorizontalStrut(50));
        jpCompagny.add(jlCompagnyInfo);
        //this.add(jpCompagny);
        jpLeft.add(jpCompagny);

        // Ajout du champ de détails pour le nom
        JPanel jpLastName = new JPanel();
        JLabel jlLastName = new JLabel("Nom : ");
        JLabel jlLastNameInfo = new JLabel(external.getNom());
        jpLastName.add(jlLastName);
        jpLastName.add(Box.createHorizontalStrut(50));
        jpLastName.add(jlLastNameInfo);
        //this.add(jpLastName);
        jpLeft.add(jpLastName);

        // Ajout du champ de détails pour le prénom
        JPanel jpFirstName = new JPanel();
        JLabel jlFirstName = new JLabel("Prénom : ");
        JLabel jlFirstNameInfo = new JLabel(external.getPrenom());
        jpFirstName.add(jlFirstName);
        jpFirstName.add(Box.createHorizontalStrut(50));
        jpFirstName.add(jlFirstNameInfo);
        //this.add(jpFirstName);
        jpLeft.add(jpFirstName);

        // Ajout du champ de détails pour l'adresse
        JPanel jpAddress = new JPanel();
        JLabel jlAddress = new JLabel("Adresse : ");
        JLabel jlAddressInfo = new JLabel("adresse TODO");
        jpAddress.add(jlAddress);
        jpAddress.add(Box.createHorizontalStrut(50));
        jpAddress.add(jlAddressInfo);
        //this.add(jpAddress);
        jpLeft.add(jpAddress);

        // Ajout du champ de détails pour la ville
        JPanel jpCity = new JPanel();
        JLabel jlCity = new JLabel("Ville : ");
        JLabel jlCityInfo = new JLabel(/*personne.getAdresse()*/);
        jpCity.add(jlCity);
        jpCity.add(Box.createHorizontalStrut(50));
        jpCity.add(jlCityInfo);
        //this.add(jpCity);
        jpLeft.add(jpCity);

        // Ajout du champ de détails pour le npa
        JPanel jpNPA = new JPanel();
        JLabel jlNPA = new JLabel("NPA : ");
        JLabel jlNPAInfo = new JLabel(/*personne.getAdresse()*/);
        jpNPA.add(jlNPA);
        jpNPA.add(Box.createHorizontalStrut(50));
        jpNPA.add(jlNPAInfo);
        //this.add(jpNPA);
        jpLeft.add(jpNPA);

        // Ajout du champ de détails pour le pays
        JPanel jpCountry = new JPanel();
        JLabel jlCountry = new JLabel("Pays : ");
        JLabel jlCountryInfo = new JLabel("pays");
        jpCountry.add(jlCountry);
        jpCountry.add(Box.createHorizontalStrut(50));
        jpCountry.add(jlCountryInfo);
        jpLeft.add(jpCountry);

        // Ajout du champ de détails pour l'email
        JPanel jpEmail = new JPanel();
        JLabel jlEmail = new JLabel("E-Mail : ");
        JLabel jlEmailInfo = new JLabel(external.getEmail());
        jpEmail.add(jlEmail);
        jpEmail.add(Box.createHorizontalStrut(50));
        jpEmail.add(jlEmailInfo);
        //this.add(jpEmail);
        jpLeft.add(jpEmail);

        // Ajout du champ de détails pour le numéro de téléphone
        JPanel jpPhone = new JPanel();
        JLabel jlPhone = new JLabel("Téléphone : ");
        JLabel jlPhoneInfo = new JLabel(external.getTelephone());
        jpPhone.add(jlPhone);
        jpPhone.add(Box.createHorizontalStrut(50));
        jpPhone.add(jlPhoneInfo);
        //this.add(jpPhone);
        jpLeft.add(jpPhone);
        JPanel fusion = new JPanel();
        fusion.setLayout(new GridLayout(1,2));
        fusion.add(jpLeft);

        /******************* Permet de tester offline **************************/
        ArrayList<Evenement> tasks = new ArrayList<>();
        Evenement e1 = new Evenement(1, "Nettoyage cage", new Timestamp(2002, 11, 20, 15, 47, 13, 2), "animation");
        Evenement e2 = new Evenement(1, "Nourrir Lion", new Timestamp(2012, 5, 25, 10, 57, 13, 2), "Représentation");
        Evenement e3 = new Evenement(1, "Médicaments Singe", new Timestamp(2015, 4, 12, 16, 50, 13, 7), "Spectacle");
        Evenement e4 = new Evenement(1, "Nourrir singes", new Timestamp(2012, 11, 20, 15, 47, 13, 2), "animation");
        Evenement e5 = new Evenement(1, "Nourrir pinguoin", new Timestamp(2011, 5, 25, 10, 57, 13, 2), "Spectacle");
        Evenement e6 = new Evenement(1, "Médicaments Yéti", new Timestamp(2016, 4, 12, 16, 50, 13, 7), "Goûter");

        tasks.add(e1);
        tasks.add(e2);
        tasks.add(e3);
        tasks.add(e4);
        tasks.add(e5);
        tasks.add(e6);

        /********************** Méthode à changer pour récupérer les tâches d'un intervenant **********************/
        fusion.add(new StaffTask(tasks));

        this.add(fusion);

        // panel permettant de mettre les trois bouttons de suppression, modification et d'ajout de tache
        JPanel jpButtons = new JPanel();

        //Ajout du bouton de suppression de l'intervenant actuel
        JButton jbDelete = new JButton("Suppression de l'intervenant");
        jpButtons.add(jbDelete);
        jpButtons.add(Box.createHorizontalStrut(20));
        jbDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Suppression de l'intervenant");
                controller.erreurPopup("Voulez vous réelement supprimer " + external.getPrenom() + " " + external.getNom());
                controller.deleteExternal(external);
            }
        });

        // Ajout du bouton d'édition de l'intervenant
        JButton jbEdit = new JButton("Modification");
        jpButtons.add(jbEdit);
        jpButtons.add(Box.createHorizontalStrut(20));
        jbEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("modification de l'intervenant");
                controller.modifyExternalView(external);
            }
        });

        // Ajout du bouton d'assignation de taches
        JButton jbAssignTask = new JButton("Assignation de tâches");
        jpButtons.add(jbAssignTask);
        jbAssignTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Assignation de tâches");
                controller.assignExternalTaskView(external);
            }
        });

        // Ajout des bouttons dans le panel
        this.add(jpButtons);
        //this.add(jpLeft);

        JPanel jpRight = new JPanel();

        //jpRight.add(new StaffTask())

    }

}
