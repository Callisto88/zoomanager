package View.Staff.StaffMainPanel;

import Controller.Staff.StaffController;
import Model.Evenement;
import Model.Personne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by André on 22.04.2017.
 * Class permettant d'afficher les détail du personnel dans une fenêtre
 */
public class PersonnelStaf extends JPanel{
    /**
     * Constructeur de la class pour afficher les détails
     * @param controller controlleur StaffController permettant de faire remonter les informations
     * @param personne personne pour lequel on souhaite afficher les détails
     */
    public PersonnelStaf(StaffController controller, Personne personne){

        this.setLayout(new GridLayout(2,1));
        JPanel jpLeft = new JPanel();
        jpLeft.setLayout(new GridLayout(14,1));

        // Ajout du champ de détails pour le nom
        JPanel jpLastName = new JPanel();
        JLabel jlLastName = new JLabel("Nom : ");
        JLabel jlLastNameInfo = new JLabel(personne.getNom());
        jpLastName.add(jlLastName);
        jpLastName.add(Box.createHorizontalStrut(50));
        jpLastName.add(jlLastNameInfo);
        //this.add(jpLastName);
        jpLeft.add(jpLastName);

        // Ajout du champ de détails pour le prénom
        JPanel jpFirstName = new JPanel();
        JLabel jlFirstName = new JLabel("Prénom : ");
        JLabel jlFirstNameInfo = new JLabel(personne.getPrenom());
        jpFirstName.add(jlFirstName);
        jpFirstName.add(Box.createHorizontalStrut(50));
        jpFirstName.add(jlFirstNameInfo);
        //this.add(jpFirstName);
        jpLeft.add(jpFirstName);

        // Ajout du champ de détails pour la date de naissance
        JPanel jpBirthday = new JPanel();
        JLabel jlBirthday = new JLabel("Date de naissance : ");
        JLabel jlBirthdayInfo = new JLabel(personne.getDateNaissance().toString());
        jpBirthday.add(jlBirthday);
        jpBirthday.add(Box.createHorizontalStrut(50));
        jpBirthday.add(jlBirthdayInfo);
        //this.add(jpBirthday);
        jpLeft.add(jpBirthday);

        // Ajout du champ de détails pour le numéro AVS
        JPanel jpAVS = new JPanel();
        JLabel jlAVS = new JLabel("Numéro AVS : ");
        JLabel jlAVSInfo = new JLabel(personne.getNoAVS());
        jpAVS.add(jlAVS);
        jpAVS.add(Box.createHorizontalStrut(50));
        jpAVS.add(jlAVSInfo);
        //this.add(jpAVS);
        jpLeft.add(jpAVS);

        // Ajout du champ de détails pour l'email
        JPanel jpEmail = new JPanel();
        JLabel jlEmail = new JLabel("E-Mail : ");
        JLabel jlEmailInfo = new JLabel(personne.getEmail());
        jpEmail.add(jlEmail);
        jpEmail.add(Box.createHorizontalStrut(50));
        jpEmail.add(jlEmailInfo);
        //this.add(jpEmail);
        jpLeft.add(jpEmail);

        // Ajout du champ de détails pour l'adresse
        JPanel jpAddress = new JPanel();
        JLabel jlAddress = new JLabel("Adresse : ");
        JLabel jlAddressInfo = new JLabel(/*personne.getAdresse()*/ "Adresse TODO");
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

        // Ajout du champ de détails pour le numéro de téléphone
        JPanel jpPhone = new JPanel();
        JLabel jlPhone = new JLabel("Téléphone : ");
        JLabel jlPhoneInfo = new JLabel(personne.getTelephone());
        jpPhone.add(jlPhone);
        jpPhone.add(Box.createHorizontalStrut(50));
        jpPhone.add(jlPhoneInfo);
        //this.add(jpPhone);
        jpLeft.add(jpPhone);

        // Ajout du champ de détails pour la date de début
        JPanel jpBeginingDate = new JPanel();
        JLabel jlBeginingDate = new JLabel("E-Mail : ");
        JLabel jlBeginingDateInfo = new JLabel(personne.getEmail());
        jpBeginingDate.add(jlBeginingDate);
        jpBeginingDate.add(Box.createHorizontalStrut(50));
        jpBeginingDate.add(jlBeginingDateInfo);
        //this.add(jpBeginingDate);
        jpLeft.add(jpBeginingDate);

        // Ajout du champ de détails pour le responsable
        JPanel jpAdvisor = new JPanel();
        JLabel jlAdvisor = new JLabel("Responsable : ");
        JLabel jlAdvisorInfo = new JLabel(/*personne.getResponsable()*/ "Responsable");
        jpAdvisor.add(jlAdvisor);
        jpAdvisor.add(Box.createHorizontalStrut(50));
        jpAdvisor.add(jlAdvisorInfo);
        //this.add(jpAdvisor);
        jpLeft.add(jpAdvisor);

        // Ajout du champ de détails pour le statut
        JPanel jpStatut = new JPanel();
        JLabel jlStatut = new JLabel("Statut : ");
        JLabel jlStatutInfo = new JLabel(personne.getStatut());
        jpStatut.add(jlStatut);
        jpStatut.add(Box.createHorizontalStrut(50));
        jpStatut.add(jlStatutInfo);
        //this.add(jpStatut);
        jpLeft.add(jpStatut);

        // Ajout du champ de détails pour le type de contrat
        JPanel jpContract = new JPanel();
        JLabel jlContract = new JLabel("Contrat : ");
        JLabel jlContractInfo = new JLabel(personne.getTypeContrat());
        jpContract.add(jlContract);
        jpContract.add(Box.createHorizontalStrut(50));
        jpContract.add(jlContractInfo);
        //this.add(jpContract);
        jpLeft.add(jpContract);

        JPanel fusion = new JPanel();
        fusion.setLayout(new GridLayout(1,2));
        fusion.add(jpLeft);

        ArrayList<Evenement> tasks = new ArrayList<>();
        Evenement e1 = new Evenement(1, "Nettoyage cage", new Timestamp(2002, 11, 20, 15, 47, 13, 2), "animation");
        Evenement e2 = new Evenement(1, "Nourrir Lion", new Timestamp(2012, 5, 25, 10, 57, 13, 2), "Spectacle");
        Evenement e3 = new Evenement(1, "Médicaments Singe", new Timestamp(2015, 4, 12, 16, 50, 13, 7), "Représentation");

        tasks.add(e1);
        tasks.add(e2);
        tasks.add(e3);

        fusion.add(new StaffTask(tasks));

        this.add(fusion);

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
                controller.deleteStaff(personne);
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
                controller.modifyStaffView(personne);
            }
        });

        // Ajout du bouton d'assignation de taches
        JButton jbAssignTask = new JButton("Assignation de tâches");
        jpButtons.add(jbAssignTask);
        jbAssignTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Assignation de tâches");
                controller.assignStaffTaskView(personne);
            }
        });

        // Ajout des bouttons dans le panel
        this.add(jpButtons);

    }
}

