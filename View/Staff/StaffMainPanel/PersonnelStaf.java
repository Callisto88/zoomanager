package View.Staff.StaffMainPanel;

import Controller.Staff.StaffController;
import Model.Personne;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Bureau on 22.04.2017.
 */
public class PersonnelStaf extends JPanel{

    public PersonnelStaf(StaffController controller, Personne personne){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Ajout du champ de détails pour le nom
        JPanel jpLastName = new JPanel();
        JLabel jlLastName = new JLabel("Nom : ");
        JLabel jlLastNameInfo = new JLabel(personne.getNom());
        jpLastName.add(jlLastName);
        jpLastName.add(Box.createHorizontalStrut(50));
        jpLastName.add(jlLastNameInfo);
        this.add(jpLastName);

        // Ajout du champ de détails pour le prénom
        JPanel jpFirstName = new JPanel();
        JLabel jlFirstName = new JLabel("Prénom : ");
        JLabel jlFirstNameInfo = new JLabel(personne.getPrenom());
        jpFirstName.add(jlFirstName);
        jpFirstName.add(Box.createHorizontalStrut(50));
        jpFirstName.add(jlFirstNameInfo);
        this.add(jpFirstName);

        // Ajout du champ de détails pour la date de naissance
        JPanel jpBirthday = new JPanel();
        JLabel jlBirthday = new JLabel("Date de naissance : ");
        JLabel jlBirthdayInfo = new JLabel(personne.getDateNaissance().toString());
        jpBirthday.add(jlBirthday);
        jpBirthday.add(Box.createHorizontalStrut(50));
        jpBirthday.add(jlBirthdayInfo);
        this.add(jpBirthday);

        // Ajout du champ de détails pour le numéro AVS
        JPanel jpAVS = new JPanel();
        JLabel jlAVS = new JLabel("Numéro AVS : ");
        JLabel jlAVSInfo = new JLabel(personne.getNoAVS());
        jpAVS.add(jlAVS);
        jpAVS.add(Box.createHorizontalStrut(50));
        jpAVS.add(jlAVSInfo);
        this.add(jpAVS);

        // Ajout du champ de détails pour l'email
        JPanel jpEmail = new JPanel();
        JLabel jlEmail = new JLabel("E-Mail : ");
        JLabel jlEmailInfo = new JLabel(personne.getEmail());
        jpEmail.add(jlEmail);
        jpEmail.add(Box.createHorizontalStrut(50));
        jpEmail.add(jlEmailInfo);
        this.add(jpEmail);

        // Ajout du champ de détails pour l'adresse
        JPanel jpAddress = new JPanel();
        JLabel jlAddress = new JLabel("Adresse : ");
        JLabel jlAddressInfo = new JLabel(/*personne.getAdresse()*/ "Adresse TODO");
        jpAddress.add(jlAddress);
        jpAddress.add(Box.createHorizontalStrut(50));
        jpAddress.add(jlAddressInfo);
        this.add(jpAddress);

        // Ajout du champ de détails pour la ville
        JPanel jpCity = new JPanel();
        JLabel jlCity = new JLabel("Ville : ");
        JLabel jlCityInfo = new JLabel(/*personne.getAdresse()*/);
        jpCity.add(jlCity);
        jpCity.add(Box.createHorizontalStrut(50));
        jpCity.add(jlCityInfo);
        this.add(jpCity);

        // Ajout du champ de détails pour le npa
        JPanel jpNPA = new JPanel();
        JLabel jlNPA = new JLabel("NPA : ");
        JLabel jlNPAInfo = new JLabel(/*personne.getAdresse()*/);
        jpNPA.add(jlNPA);
        jpNPA.add(Box.createHorizontalStrut(50));
        jpNPA.add(jlNPAInfo);
        this.add(jpNPA);

        // Ajout du champ de détails pour le numéro de téléphone
        JPanel jpPhone = new JPanel();
        JLabel jlPhone = new JLabel("Téléphone : ");
        JLabel jlPhoneInfo = new JLabel(personne.getTelephone());
        jpPhone.add(jlPhone);
        jpPhone.add(Box.createHorizontalStrut(50));
        jpPhone.add(jlPhoneInfo);
        this.add(jpPhone);

        // Ajout du champ de détails pour la date de début
        JPanel jpBeginingDate = new JPanel();
        JLabel jlBeginingDate = new JLabel("E-Mail : ");
        JLabel jlBeginingDateInfo = new JLabel(personne.getEmail());
        jpBeginingDate.add(jlBeginingDate);
        jpBeginingDate.add(Box.createHorizontalStrut(50));
        jpBeginingDate.add(jlBeginingDateInfo);
        this.add(jpBeginingDate);

        // Ajout du champ de détails pour le responsable
        JPanel jpAdvisor = new JPanel();
        JLabel jlAdvisor = new JLabel("Responsable : ");
        JLabel jlAdvisorInfo = new JLabel(/*personne.getResponsable()*/ "Responsable");
        jpAdvisor.add(jlAdvisor);
        jpAdvisor.add(Box.createHorizontalStrut(50));
        jpAdvisor.add(jlAdvisorInfo);
        this.add(jpAdvisor);

        // Ajout du champ de détails pour le statut
        JPanel jpStatut = new JPanel();
        JLabel jlStatut = new JLabel("Statut : ");
        JLabel jlStatutInfo = new JLabel(personne.getStatut());
        jpStatut.add(jlStatut);
        jpStatut.add(Box.createHorizontalStrut(50));
        jpStatut.add(jlStatutInfo);
        this.add(jpStatut);

        // Ajout du champ de détails pour le type de contrat
        JPanel jpContract = new JPanel();
        JLabel jlContract = new JLabel("Contrat : ");
        JLabel jlContractInfo = new JLabel(personne.getTypeContrat());
        jpContract.add(jlContract);
        jpContract.add(Box.createHorizontalStrut(50));
        jpContract.add(jlContractInfo);
        this.add(jpContract);

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

        // Ajout des bouttons dans le panel
        this.add(jpButtons);

    }
}

