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
public class ModifyStaffPanel extends GenericWindow {
    private Personne personne = null;
    private ModifyStaffController mscController = null;
    private JComboBox boxChoiceLabel = null;

    // Booléens pour savoir si les camps on été demandé
    private boolean bFirstName = false;
    private boolean bLastName = false;
    private boolean bSupervisor = false;
    private boolean bAddress = false;
    private boolean bEMail = false;
    private boolean bPhone = false;
    private boolean bButton = false;

    // String pour la récupération des champs de saisie
    private String sLastName;
    private String sFirstName;
    private String sEMail;
    private String sAddress;
    private String sNPA;
    private String sCity;
    private String sCountry;
    private String sPhone;
    private String sSupervisor;

    // Champs de saisie pour la modification
    private JTextField jtfLastNameInput = null;
    private JTextField jtfFirstNameInput = null;
    private JTextField jtfEmail = null;
    private JTextField jtfAddress = null;
    private JTextField jtfCity = null;
    private JTextField jtfNPA = null;
    private JTextField jtfCountry = null;
    private JTextField jtfPhone = null;

    // Label indiquant une erreur de saisie
    private JLabel jlLastNameError = new JLabel("*", JLabel.CENTER);
    private JLabel jlFirstNameError = new JLabel("*", JLabel.CENTER);
    private JLabel jlEmailError = new JLabel("*", JLabel.CENTER);
    private JLabel jlAddressError = new JLabel("*", JLabel.CENTER);
    private JLabel jlNPAError = new JLabel("*", JLabel.CENTER);
    private JLabel jlCityError = new JLabel("*", JLabel.CENTER);
    private JLabel jlCountryError = new JLabel("*", JLabel.CENTER);
    private JLabel jlPhoneError = new JLabel("*", JLabel.CENTER);


    public ModifyStaffPanel(ModifyStaffController mscController, Personne personne) {
        super("Modificaion");
        jpMainPanel.setLayout(new GridLayout(12, 1));
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
        boxChoiceLabel.addItem("Responsable");
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
                if (boxChoiceLabel.getSelectedItem().equals("Nom")) {
                    addLastName();
                    addModifyButton();
                }

                if (boxChoiceLabel.getSelectedItem().equals("Prénom")) {
                    addFirstName();
                    addModifyButton();
                }
                if (boxChoiceLabel.getSelectedItem().equals("Adresse E-Mail")) {
                    addEMail();
                    addModifyButton();
                }

                if (boxChoiceLabel.getSelectedItem().equals("Adresse")) {
                    addAddress();
                    addModifyButton();
                }
                if (boxChoiceLabel.getSelectedItem().equals("Téléphone")) {
                    addPhone();
                    addModifyButton();
                }

                if (boxChoiceLabel.getSelectedItem().equals("Responsable")) {
                    addSupervisor();
                    addModifyButton();
                }
                if (boxChoiceLabel.getSelectedItem().equals("Tous les champs")) {
                    addLastName();
                    addFirstName();
                    addSupervisor();
                    addEMail();
                    addAddress();
                    addPhone();
                    addModifyButton();
                }
            }
        });


        this.setVisible(true);
        configFrame(getJfFrame(), this);
        this.setMinimumSize(new Dimension(200, 400));
    }

    /**
     * Méthode pour ajouter le nom de famille au panneaux de modification
     */
    private void addLastName() {
        if (!bLastName) {
            bLastName = true;
            JPanel lastNamePanel = new JPanel();
            JLabel lastNameLabel = new JLabel("Nom : ");
            lastNamePanel.add(lastNameLabel);
            jtfLastNameInput = new JTextField(personne.getNom(), 20);
            lastNamePanel.add(jtfLastNameInput);
            lastNameLabel.add(jlLastNameError);
            jpMainPanel.add(lastNamePanel);
            jpMainPanel.revalidate();
            System.out.println("modif Nom");
        }
    }

    /**
     * Méthode pour ajouter le prénom au panneaux de modification
     */
    private void addFirstName() {
        if (!bFirstName) {
            bFirstName = true;
            JPanel firstNamePanel = new JPanel();
            JLabel firstNameLabel = new JLabel("Prénom : ");
            firstNamePanel.add(firstNameLabel);
            jtfFirstNameInput = new JTextField(personne.getPrenom(), 20);
            firstNamePanel.add(jtfFirstNameInput);
            firstNamePanel.add(jlFirstNameError);
            jpMainPanel.add(firstNamePanel);
            jpMainPanel.revalidate();
            System.out.println("modif Prénom");
        }
    }

    /**
     * Méthode pour ajouter l'e-mail au panneaux de modification
     */
    private void addEMail() {
        if (!bEMail) {
            bEMail = true;
            JPanel emailPanel = new JPanel();
            JLabel emailLabel = new JLabel("Adresse E-Mail : ");
            emailPanel.add(emailLabel);
            jtfEmail = new JTextField(personne.getEmail(), 20);
            emailPanel.add(jtfEmail);
            emailPanel.add(jlEmailError);
            jpMainPanel.add(emailPanel);
            jpMainPanel.revalidate();
            System.out.println("modif E-Mail");
        }
    }

    /**
     * Méthode pour ajouter l'adresse au panneaux de modification
     */
    private void addAddress() {
        if(!bAddress){
            bAddress = true;
        // Pour ajouter l'adresse
        JPanel addressPanel = new JPanel();
        JLabel addressLabel = new JLabel("Adresse : ");
        addressPanel.add(addressLabel);
        // Récupération de l'adresse
        jtfAddress = new JTextField("adresse", 20);
        addressPanel.add(jtfAddress);
        addressLabel.add(jlAddressError);
        jpMainPanel.add(addressPanel);

        // Pour ajouter le npa
        JPanel jpNPA = new JPanel();
        JLabel jlNPA = new JLabel("NPA : ");
        jpNPA.add(jlNPA);
        // récupération du npa
        jtfNPA = new JTextField("NPA", 20);
        jpNPA.add(jtfNPA);
        jpNPA.add(jlNPAError);
        jpMainPanel.add(jpNPA);

        // Pour ajouter la ville
        JPanel jpCity = new JPanel();
        JLabel jlCity = new JLabel("Ville : ");
        jpCity.add(jlCity);
        // Récupération de la ville
        jtfCity = new JTextField("Ville", 20);
        jpCity.add(jtfCity);
        jpCity.add(jlCityError);
        jpMainPanel.add(jpCity);

        //Pour ajouter le pays
        JPanel jpCountry = new JPanel();
        JLabel jlCountry = new JLabel("Pays : ");
        jpCountry.add(jlCountry);
        // Récupération du Pays
        jtfCountry = new JTextField("Pays", 20);
        jpCountry.add(jtfCountry);
        jpCountry.add(jlCountryError);
        jpMainPanel.add(jpCountry);

        jpMainPanel.revalidate();
        System.out.println("modif Adresse");
    }

}

    /**
     * Méthode pour ajouter le téléphone au panneaux de modification
     */
    private void addPhone() {
        if (!bPhone) {
            JPanel jpPhone = new JPanel();
            JLabel jlPhone = new JLabel("Téléphone : ");
            jpPhone.add(jlPhone);
            jtfPhone = new JTextField(personne.getTelephone(), 20);
            jpPhone.add(jtfPhone);
            jpPhone.add(jlPhoneError);
            jpMainPanel.add(jpPhone);
            jpMainPanel.revalidate();
            System.out.println("modif Téléphone");
        }
    }

    /**
     * Méthode pour ajouter le superviseur au panneaux de modification
     */
    private void addSupervisor() {
        if (!bSupervisor) {
            JPanel jpSupervisor = new JPanel();
            JLabel jlSupervisor = new JLabel("Responsable : ");
            jpSupervisor.add(jlSupervisor);
            jpSupervisor.add(new JTextField(/*personne.getResponsable()*/"Responsable", 20));
            jpMainPanel.add(jpSupervisor);
            jpMainPanel.revalidate();
            System.out.println("modif Responsable");
        }
    }

    /**
     * Méthode pour ajouter le bouton de mise à jour
     */
    private void addModifyButton() {
        if (!bButton) {
            bButton = true;
            JButton jbModify = new JButton("Modifier");
            jpMainPanel.add(jbModify);
            jbModify.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sFirstName = jtfFirstNameInput.getText();
                    sLastName = jtfLastNameInput.getText();
                    //sSupervisor =
                    sEMail = jtfEmail.getText();
                    sAddress = jtfAddress.getText();
                    sNPA = jtfNPA.getText();
                    sCity = jtfCity.getText();
                    sCountry = jtfCountry.getText();
                    sPhone = jtfPhone.getText();
                    mscController.checkModifyStaff(sFirstName, sLastName, sSupervisor, sEMail, sAddress,
                            sNPA, sCity, sCountry, sPhone);
                }
            });
        }
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie du prénom
     * @param error message indiquant plus précisément l'erreur
     */
    public void setFirstNameError(String error) {
        jlFirstNameError.setVisible(true);
        jlFirstNameError.setToolTipText(error);
        jtfFirstNameInput.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie du nom
     * @param error message indiquant plus précisément l'erreur
     */
    public void setLastNameError(String error) {
        jlLastNameError.setVisible(true);
        jlLastNameError.setToolTipText(error);
        jtfLastNameInput.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie de l'email
     * @param error message indiquant plus précisément l'erreur
     */
    public void setEmailError(String error) {
        jlEmailError.setVisible(true);
        jlEmailError.setToolTipText(error);
        jtfEmail.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie de l'adresse
     * @param error message indiquant plus précisément l'erreur
     */
    public void setAddressError(String error) {
        jlAddressError.setVisible(true);
        jlAddressError.setToolTipText(error);
        jtfAddress.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie de la ville
     * @param error message indiquant plus précisément l'erreur
     */
    public void setCityError(String error) {
        jlCityError.setVisible(true);
        jlCityError.setToolTipText(error);
        jtfCity.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie du npa
     * @param error message indiquant plus précisément l'erreur
     */
    public void setNPAError(String error) {
        jlNPAError.setVisible(true);
        jlNPAError.setToolTipText(error);
        jtfNPA.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie du pays
     * @param error message indiquant plus précisément l'erreur
     */
    public void setCountryError(String error){
        jlCountryError.setVisible(true);
        jlCountryError.setToolTipText(error);
        jtfCountry.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie du télephone
     * @param error message indiquant plus précisément l'erreur
     */
    public void setPhoneError(String error) {
        jlPhoneError.setVisible(true);
        jlPhoneError.setToolTipText(error);
        jtfPhone.setBackground(Color.RED);
    }
}
