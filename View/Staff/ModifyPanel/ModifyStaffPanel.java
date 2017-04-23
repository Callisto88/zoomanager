package View.Staff.ModifyPanel;

import Controller.Staff.ModifyStaffController;
import Model.Personne;
import View.GenericWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andre on 09.04.2017.
 */
public class ModifyStaffPanel extends GenericWindow{
    private Personne personne = null;
    private ModifyStaffController mscController = null;
    private JComboBox boxChoiceLabel = null;

    // Booléens pour savoir si les camps on été demandé
    private boolean bFirstName = false;
    private boolean bLastName = false;
    private boolean bCompagny = false;
    private boolean bAddress = false;
    private boolean bEMail = false;
    private boolean bPhone = false;

    // Champs de saisie pour la modification
    private JTextField jtfLastNameInput = null;
    private JTextField jtfFirstNameInput = null;
    private JTextField jtfEmail = null;
    private JTextField jtfAddress = null;
    private JTextField jtfCity = null;
    private JTextField jtfNPA = null;
    private JTextField jtfCountry = null;
    private JTextField jtfPhone = null;


    public ModifyStaffPanel(ModifyStaffController mscController, Personne personne){
        super("Modificaion");
        jpMainPanel.setLayout(new GridLayout(12,1));
        this.personne = personne;
        this.mscController = mscController;
        /**
         * Liste déroulante pour séléctionner les champs que l'on souhaite modifier
         */
        JPanel modification = new JPanel();
        boxChoiceLabel = new JComboBox();
        boxChoiceLabel.addItem("Tous les champs");
        boxChoiceLabel.addItem("Nom");
        boxChoiceLabel.addItem("Prénom");
        boxChoiceLabel.addItem("Entreprise");
        boxChoiceLabel.addItem("Adresse E-Mail");
        boxChoiceLabel.addItem("Adresse");
        boxChoiceLabel.addItem("Téléphone");

        modification.add(boxChoiceLabel);
        jpMainPanel.add(modification);
        System.out.println(personne.getNom() + " " + personne.getPrenom() + " " + personne.getTelephone());

        /**
         * Bouton pour demander l'ajout de champ à modifier
         */
        JPanel modifyLabel = new JPanel();
        JButton newLabel = new JButton("Ajouter un nouveau champ");
        modifyLabel.add(newLabel);
        jpMainPanel.add(modifyLabel);

        /**
         * Permet d'ajouter un champ en plus lors de la séléction, ou directement tous les champs
         */
        newLabel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bLastName && boxChoiceLabel.getSelectedItem().equals("Nom")){
                    addLastName();
                    bLastName = true;
                    addModifyButton();
                }

                if(!bFirstName && boxChoiceLabel.getSelectedItem().equals("Prénom")){
                    addFirstName();
                    bFirstName = true;
                    addModifyButton();
                }
                if(!bEMail && boxChoiceLabel.getSelectedItem().equals("Adresse E-Mail")){
                    addEMail();
                    bEMail = true;
                    addModifyButton();
                }

                if(!bAddress && boxChoiceLabel.getSelectedItem().equals("Adresse")){
                    addAddress();
                    bAddress = true;
                    addModifyButton();
                }
                if(!bPhone && boxChoiceLabel.getSelectedItem().equals("Téléphone")){
                    addPhone();
                    bPhone = true;
                    addModifyButton();
                }

                if(!bCompagny && boxChoiceLabel.getSelectedItem().equals("Entreprise")){
                    addSupervisor();
                    bCompagny = true;
                    addModifyButton();
                }
                if(boxChoiceLabel.getSelectedItem().equals("Tous les champs")){
                    if(!bLastName) {
                        addLastName();
                        bLastName = true;
                    }
                    if(!bFirstName) {
                        addFirstName();
                        bFirstName = true;
                    }
                    if(!bCompagny){
                        addSupervisor();
                        bCompagny = true;
                    }
                    if(!bEMail) {
                        addEMail();
                        bEMail = true;
                    }
                    if(!bAddress) {
                        addAddress();
                        bAddress = true;
                    }
                    if(!bPhone) {
                        addPhone();
                        bPhone = true;
                    }
                    addModifyButton();
                }
            }
        });



        this.setVisible(true);
        configFrame(getJfFrame(), this);
        this.setMinimumSize(new Dimension(200,400));
    }

    /**
     * Méthode pour ajouter le nom de famille au panneaux de modification
     */
    private void addLastName(){
        JPanel lastNamePanel = new JPanel();
        JLabel lastNameLabel = new JLabel("Nom : ");
        lastNamePanel.add(lastNameLabel);
        jtfLastNameInput = new JTextField(personne.getNom(), 20);
        lastNamePanel.add(jtfLastNameInput);
        jpMainPanel.add(lastNamePanel);
        jpMainPanel.revalidate();
        System.out.println("modif Nom");
    }

    /**
     * Méthode pour ajouter le prénom au panneaux de modification
     */
    private void addFirstName(){
        JPanel firstNamePanel = new JPanel();
        JLabel firstNameLabel = new JLabel("Prénom : ");
        firstNamePanel.add(firstNameLabel);
        jtfFirstNameInput = new JTextField(personne.getPrenom(), 20);
        firstNamePanel.add(jtfFirstNameInput);
        jpMainPanel.add(firstNamePanel);
        jpMainPanel.revalidate();
        System.out.println("modif Prénom");
    }

    /**
     * Méthode pour ajouter l'e-mail au panneaux de modification
     */
    private void addEMail(){
        JPanel emailPanel = new JPanel();
        JLabel emailLabel = new JLabel("Adresse E-Mail : ");
        emailPanel.add(emailLabel);
        jtfEmail = new JTextField(personne.getEmail(), 20);
        emailPanel.add(jtfEmail);
        jpMainPanel.add(emailPanel);
        jpMainPanel.revalidate();
        System.out.println("modif E-Mail");
    }

    /**
     * Méthode pour ajouter l'adresse au panneaux de modification
     */
    private void addAddress(){
        // Pour ajouter l'adresse
        JPanel addressPanel = new JPanel();
        JLabel addressLabel = new JLabel("Adresse : ");
        addressPanel.add(addressLabel);
        // Récupération de l'adresse
        jtfAddress = new JTextField("adresse", 20);
        addressPanel.add(jtfAddress);
        jpMainPanel.add(addressPanel);

        // Pour ajouter le npa
        JPanel jpNPA = new JPanel();
        JLabel jlNPA = new JLabel("NPA : ");
        jpNPA.add(jlNPA);
        // récupération du npa
        jtfNPA = new JTextField("NPA", 20);
        jpNPA.add(jtfNPA);
        jpMainPanel.add(jpNPA);

        // Pour ajouter la ville
        JPanel jpCity = new JPanel();
        JLabel jlCity = new JLabel("Ville : ");
        jpCity.add(jlCity);
        // Récupération de la ville
        jtfCity = new JTextField("Ville", 20);
        jpCity.add(jtfCity);
        jpMainPanel.add(jpCity);

        //Pour ajouter le pays
        JPanel jpCountry = new JPanel();
        JLabel jlCountry = new JLabel("Pays : ");
        jpCountry.add(jlCountry);
        // Récupération du Pays
        jtfCountry = new JTextField("Pays", 20);
        jpCountry.add(jtfCountry);
        jpMainPanel.add(jpCountry);

        jpMainPanel.revalidate();
        System.out.println("modif Adresse");
    }

    /**
     * Méthode pour ajouter le téléphone au panneaux de modification
     */
    private void addPhone(){
        JPanel telephonePanel = new JPanel();
        JLabel telephoneLabel = new JLabel("Téléphone : ");
        telephonePanel.add(telephoneLabel);
        jtfPhone = new JTextField(personne.getTelephone(), 20);
        telephonePanel.add(jtfPhone);
        jpMainPanel.add(telephonePanel);
        jpMainPanel.revalidate();
        System.out.println("modif Téléphone");
    }

    /**
     * Méthode pour ajouter le superviseur au panneaux de modification
     */
    private void addSupervisor(){
        JPanel jpSupervisor = new JPanel();
        JLabel jlSupervisor = new JLabel("Responsable : ");
        jpSupervisor.add(jlSupervisor);
        jpSupervisor.add(new JTextField(/*personne.getResponsable()*/"Responsable", 20));
        jpMainPanel.add(jpSupervisor);
        jpMainPanel.revalidate();
        System.out.println("modif Responsable");
    }

    private void addModifyButton(){
        JButton jbModify = new JButton("Modifier");
        jpMainPanel.add(jbModify);
        jbModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //mscController.checkModifyStaff();
            }
        });
    }

}
