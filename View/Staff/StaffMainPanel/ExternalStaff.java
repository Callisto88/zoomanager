package View.Staff.StaffMainPanel;

import Controller.Staff.StaffController;
import Model.Intervenant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by André on 22.04.2017.
 * Class permettant d'afficher les détail des personnes externes dans une fenêtre
 */
public class ExternalStaff extends JPanel{
    /**
     * Constructeur de la class pour afficher les détails
     * @param controller controlleur StaffController permettant de faire remonter les informations
     * @param external intervenant pour lequel on souhaite afficher les détails
     */
    public ExternalStaff(StaffController controller, Intervenant external){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Ajout du champ de détails pour l'entreprise
        JPanel jpCompagny = new JPanel();
        JLabel jlCompagny = new JLabel("Entreprise : ");
        JLabel jlCompagnyInfo = new JLabel(external.getEntreprise());
        jpCompagny.add(jlCompagny);
        jpCompagny.add(Box.createHorizontalStrut(50));
        jpCompagny.add(jlCompagnyInfo);
        this.add(jpCompagny);

        // Ajout du champ de détails pour le nom
        JPanel jpLastName = new JPanel();
        JLabel jlLastName = new JLabel("Nom : ");
        JLabel jlLastNameInfo = new JLabel(external.getNom());
        jpLastName.add(jlLastName);
        jpLastName.add(Box.createHorizontalStrut(50));
        jpLastName.add(jlLastNameInfo);
        this.add(jpLastName);

        // Ajout du champ de détails pour le prénom
        JPanel jpFirstName = new JPanel();
        JLabel jlFirstName = new JLabel("Prénom : ");
        JLabel jlFirstNameInfo = new JLabel(external.getPrenom());
        jpFirstName.add(jlFirstName);
        jpFirstName.add(Box.createHorizontalStrut(50));
        jpFirstName.add(jlFirstNameInfo);
        this.add(jpFirstName);

        // Ajout du champ de détails pour l'adresse
        JPanel jpAddress = new JPanel();
        JLabel jlAddress = new JLabel("Adresse : ");
        JLabel jlAddressInfo = new JLabel("adresse TODO");
        jpAddress.add(jlAddress);
        jpAddress.add(Box.createHorizontalStrut(50));
        jpAddress.add(jlAddressInfo);
        this.add(jpAddress);

        // Ajout du champ de détails pour l'email
        JPanel jpEmail = new JPanel();
        JLabel jlEmail = new JLabel("E-Mail : ");
        JLabel jlEmailInfo = new JLabel(external.getEmail());
        jpEmail.add(jlEmail);
        jpEmail.add(Box.createHorizontalStrut(50));
        jpEmail.add(jlEmailInfo);
        this.add(jpEmail);

        // Ajout du champ de détails pour le numéro de téléphone
        JPanel jpPhone = new JPanel();
        JLabel jlPhone = new JLabel("Téléphone : ");
        JLabel jlPhoneInfo = new JLabel(external.getTelephone());
        jpPhone.add(jlPhone);
        jpPhone.add(Box.createHorizontalStrut(50));
        jpPhone.add(jlPhoneInfo);
        this.add(jpPhone);

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
                //controller.assignStaffTaskView(personne);
            }
        });

        // Ajout des bouttons dans le panel de droite
        this.add(jpButtons);
    }
}
