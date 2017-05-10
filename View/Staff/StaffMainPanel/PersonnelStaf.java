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

    private GridBagConstraints gbcDetailsStaff = new GridBagConstraints();
    private int x = 0;
    private int y = 0;
    private int line;

    private Dimension dLabel = new Dimension(100,25);
    private Dimension dDetail = new Dimension(120, 25);

    /**
     * Constructeur de la class pour afficher les détails
     * @param controller controlleur StaffController permettant de faire remonter les informations
     * @param personne personne pour lequel on souhaite afficher les détails
     * @param line ligne correspondant à la ligne sélectionné de la personne
     */
    public PersonnelStaf(StaffController controller, Personne personne, int line){

        this.setLayout(new GridBagLayout());
        this.line = line;
        gbcDetailsStaff.gridx = x;
        gbcDetailsStaff.gridy = y;
        JPanel jpLeft = new JPanel();
        jpLeft.setLayout(new GridBagLayout());

        // Ajout du champ de détails pour le nom
        JPanel jpLastName = new JPanel();
        JLabel jlLastName = new JLabel("Nom : ");
        jlLastName.setPreferredSize(dLabel);
        JLabel jlLastNameInfo = new JLabel(personne.getNom());
        jlLastNameInfo.setPreferredSize(dDetail);
        jpLastName.add(jlLastName);
        jpLastName.add(jlLastNameInfo);
        this.add(jpLastName, gbcDetailsStaff);

        // Ajout du champ de détails pour le prénom
        JPanel jpFirstName = new JPanel();
        JLabel jlFirstName = new JLabel("Prénom : ");
        JLabel jlFirstNameInfo = new JLabel(personne.getPrenom());
        jlFirstName.setPreferredSize(dLabel);
        jpFirstName.add(jlFirstName);
        jlFirstNameInfo.setPreferredSize(dDetail);
        jpFirstName.add(jlFirstNameInfo);
        ++y;
        gbcDetailsStaff.gridy = y;
        this.add(jpFirstName, gbcDetailsStaff);

        // Ajout du champ de détails pour la date de naissance
        JPanel jpBirthday = new JPanel();
        JLabel jlBirthday = new JLabel("Date de naissance : ");
        jlBirthday.setPreferredSize(dLabel);
        JLabel jlBirthdayInfo = new JLabel(personne.getDateNaissance().toString());
        jpBirthday.add(jlBirthday);
        jlBirthdayInfo.setPreferredSize(dDetail);
        jpBirthday.add(jlBirthdayInfo);
        ++y;
        gbcDetailsStaff.gridy = y;
        this.add(jpBirthday, gbcDetailsStaff);

        // Ajout du champ de détails pour le numéro AVS
        JPanel jpAVS = new JPanel();
        JLabel jlAVS = new JLabel("Numéro AVS : ");
        JLabel jlAVSInfo = new JLabel(personne.getNoAVS());
        jlAVS.setPreferredSize(dLabel);
        jpAVS.add(jlAVS);
        jlAVSInfo.setPreferredSize(dDetail);
        jpAVS.add(jlAVSInfo);
        ++y;
        gbcDetailsStaff.gridy = y;
        this.add(jpAVS, gbcDetailsStaff);

        // Ajout du champ de détails pour l'email
        JPanel jpEmail = new JPanel();
        JLabel jlEmail = new JLabel("E-Mail : ");
        jlEmail.setPreferredSize(dLabel);
        JLabel jlEmailInfo = new JLabel(personne.getEmail());
        jpEmail.add(jlEmail);
        jlEmailInfo.setPreferredSize(dDetail);
        jpEmail.add(jlEmailInfo);
        ++y;
        gbcDetailsStaff.gridy = y;
        this.add(jpEmail, gbcDetailsStaff);

        // Ajout du champ de détails pour le numéro de téléphone
        JPanel jpPhone = new JPanel();
        JLabel jlPhone = new JLabel("Téléphone : ");
        jlPhone.setPreferredSize(dLabel);
        JLabel jlPhoneInfo = new JLabel(personne.getTelephone());
        jpPhone.add(jlPhone);
        jlPhoneInfo.setPreferredSize(dDetail);
        jpPhone.add(jlPhoneInfo);
        ++y;
        gbcDetailsStaff.gridy = y;
        this.add(jpPhone, gbcDetailsStaff);

        // Ajout du champ de détails pour la date de début
        JPanel jpBeginingDate = new JPanel();
        JLabel jlBeginingDate = new JLabel("Engagement : ");
        jlBeginingDate.setPreferredSize(dLabel);
        JLabel jlBeginingDateInfo = new JLabel(personne.getDateDebut().toString());
        jpBeginingDate.add(jlBeginingDate);
        jlBeginingDateInfo.setPreferredSize(dDetail);
        jpBeginingDate.add(jlBeginingDateInfo);
        ++y;
        gbcDetailsStaff.gridy = y;
        this.add(jpBeginingDate, gbcDetailsStaff);

        // Ajout du champ de détails pour l'adresse
        String sAddress = "";
        String sCity = "";
        String sNPA = "";
        String sCountry = "";
        if(personne.getAdresse() != null){
            sAddress = personne.getAdresse().toString();
            sCity = personne.getAdresse().toString();
            sNPA = "" + personne.getAdresse().getVille().getCp();
            sCountry = personne.getAdresse().getVille().getPays().toString();
        }
        JPanel jpAddress = new JPanel();
        JLabel jlAddress = new JLabel("Adresse : ");
        jlAddress.setPreferredSize(dLabel);
        JLabel jlAddressInfo = new JLabel(sAddress);
        //System.out.println(personne.getAdresse().toString());
        jlAddressInfo.setPreferredSize(dDetail);
        jpAddress.add(jlAddress);
        jpAddress.add(jlAddressInfo);
        ++x;
        y = 0;
        gbcDetailsStaff.gridy = y;
        gbcDetailsStaff.gridx = x;
        this.add(jpAddress, gbcDetailsStaff);
        //jpLeft.add(jpAddress, gbcDetailsStaff);

        // Ajout du champ de détails pour la ville
        JPanel jpCity = new JPanel();
        JLabel jlCity = new JLabel("Ville : ");
        jlCity.setPreferredSize(dLabel);
        JLabel jlCityInfo = new JLabel(sCity);
        jpCity.add(jlCity);
        jlCityInfo.setPreferredSize(dDetail);
        jpCity.add(jlCityInfo);
        ++y;
        gbcDetailsStaff.gridy = y;
        this.add(jpCity, gbcDetailsStaff);

        // Ajout du champ de détails pour le npa
        JPanel jpNPA = new JPanel();
        JLabel jlNPA = new JLabel("NPA : ");
        jlNPA.setPreferredSize(dLabel);
        JLabel jlNPAInfo = new JLabel(sNPA);
        jpNPA.add(jlNPA);
        jlNPAInfo.setPreferredSize(dDetail);
        jpNPA.add(jlNPAInfo);
        ++y;
        gbcDetailsStaff.gridy = y;
        this.add(jpNPA, gbcDetailsStaff);

        // Ajout du champ de détails pour le pays
        JPanel jpCountry = new JPanel();
        JLabel jlCountry = new JLabel("Pays : ");
        jlCountry.setPreferredSize(dLabel);
        JLabel jlCountryInfo = new JLabel(sCountry);
        jpCountry.add(jlCountry);
        jlCountryInfo.setPreferredSize(dDetail);
        jpCountry.add(jlCountryInfo);
        ++y;
        gbcDetailsStaff.gridy = y;
        this.add(jpCountry, gbcDetailsStaff);

        // Ajout du champ de détails pour le responsable
        String sSupervisor = "";
        if(personne.getResponsable() != 0){
            sSupervisor = controller.getSupervisor(personne.getResponsable());
        }
        JPanel jpAdvisor = new JPanel();
        JLabel jlAdvisor = new JLabel("Responsable : ");
        jlAdvisor.setPreferredSize(dLabel);
        JLabel jlAdvisorInfo = new JLabel(sSupervisor);
        jpAdvisor.add(jlAdvisor);
        jlAdvisorInfo.setPreferredSize(dDetail);
        jpAdvisor.add(jlAdvisorInfo);
        ++y;
        gbcDetailsStaff.gridy = y;
        this.add(jpAdvisor, gbcDetailsStaff);

        // Ajout du champ de détails pour le statut
        JPanel jpStatut = new JPanel();
        JLabel jlStatut = new JLabel("Statut : ");
        jlStatut.setPreferredSize(dLabel);
        JLabel jlStatutInfo = new JLabel(personne.getStatut());
        jpStatut.add(jlStatut);
        jlStatutInfo.setPreferredSize(dDetail);
        jpStatut.add(jlStatutInfo);
        ++y;
        gbcDetailsStaff.gridy = y;
        this.add(jpStatut, gbcDetailsStaff);

        // Ajout du champ de détails pour le type de contrat
        JPanel jpContract = new JPanel();
        JLabel jlContract = new JLabel("Contrat : ");
        jlContract.setPreferredSize(dLabel);
        JLabel jlContractInfo = new JLabel(personne.getTypeContrat());
        jpContract.add(jlContract);
        jlContractInfo.setPreferredSize(dDetail);
        jpContract.add(jlContractInfo);
        ++y;
        gbcDetailsStaff.gridy = y;
        this.add(jpContract, gbcDetailsStaff);

        JPanel fusion = new JPanel();
        fusion.setLayout(new GridBagLayout());

        ArrayList<Evenement> tasks = new ArrayList<>();
        tasks = controller.getStaffTask(personne.getIdPersonne());
/*
        Evenement e1 = new Evenement(1, "Nettoyage cage", new Timestamp(2002, 11, 20, 15, 47, 13, 2), "animation");
        Evenement e2 = new Evenement(1, "Nourrir Lion", new Timestamp(2012, 5, 25, 10, 57, 13, 2), "Spectacle");
        Evenement e3 = new Evenement(1, "Médicaments Singe", new Timestamp(2015, 4, 12, 16, 50, 13, 7), "Représentation");

        tasks.add(e1);
        tasks.add(e2);
        tasks.add(e3);
*/
        ++y;
        gbcDetailsStaff.gridy = y;
        x = 0;
        gbcDetailsStaff.gridx = x;
        gbcDetailsStaff.gridwidth = 2;
        gbcDetailsStaff.insets = new Insets(5,5,5,5);

        this.add(new StaffTask(tasks), gbcDetailsStaff);

        // panel permettant de mettre les trois bouttons de suppression, modification et d'ajout de tache
        JPanel jpButtons = new JPanel();

        //Ajout du bouton de suppression de l'employé actuel
        JButton jbDelete = new JButton("Suppression de l'employé");
        jpButtons.add(jbDelete);
        jpButtons.add(Box.createHorizontalStrut(20));
        jbDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(new JPanel(), "Voulez-vous vraiment supprimer cette personne ?",
                        "Confirmer la suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
                if(n == 0) {
                    if (controller.deleteStaff(personne)) {
                        controller.deleteStaffRow(line);
                    }
                }
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
        ++y;
        gbcDetailsStaff.gridy = y;
        this.add(jpButtons, gbcDetailsStaff);

    }
}

