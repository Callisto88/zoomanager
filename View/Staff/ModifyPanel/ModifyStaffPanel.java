package View.Staff.ModifyPanel;

import Controller.Staff.ModifyStaffController;
import Model.Adresse;
import Model.Pays;
import Model.Personne;
import View.GenericWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Andre on 09.04.2017.
 */
public class ModifyStaffPanel extends GenericWindow {
    private Personne personne = null;
    private ModifyStaffController mscController = null;
    private ArrayList<Personne> alpSupervisor = null;
    private ArrayList<String> alsContract = null;
    private ArrayList<String> alsStatus = null;
    private ArrayList<Pays> alpCountries = null;

    private JComboBox boxChoiceLabel = null;
    private JPanel jpModifyPanel = new JPanel();
    private int NUMBER_OF_ROW = 13;
    private int y = 0;

    private Dimension dLabel = new Dimension(90, 30);
    private Dimension dInput = new Dimension(150, 30);

    private GridBagConstraints gbcConstraint = new GridBagConstraints();

    // Booléens pour savoir si les champs on été demandé
    private boolean bFirstName = false;
    private boolean bLastName = false;
    private boolean bSupervisor = false;
    private boolean bAddress = false;
    private boolean bEMail = false;
    private boolean bPhone = false;
    private boolean bStatut = false;
    private boolean bContract = false;
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
    private String sStatut;
    private String sContract;

    // Champs de saisie pour la modification
    private JTextField jtfLastNameInput = null;
    private JTextField jtfFirstNameInput = null;
    private JTextField jtfEmail = null;
    private JTextField jtfAddress = null;
    private JTextField jtfCity = null;
    private JTextField jtfNPA = null;
    private JComboBox jcbCountry = null;
    private JTextField jtfPhone = null;
    private JComboBox jcbSupervisor = null;
    private JComboBox jcbStatut;
    private JComboBox jcbContract;


    public ModifyStaffPanel(ModifyStaffController mscController, Personne personne, ArrayList<String> contract,
                            ArrayList<String> status, ArrayList<Pays> countries, ArrayList<Personne> supervisor) {
        super("Modificaion");
        jpModifyPanel.setLayout(new GridBagLayout());
        alpSupervisor = supervisor;
        alsContract = contract;
        alsStatus = status;
        alpCountries = countries;
        this.personne = personne;
        this.mscController = mscController;
        sFirstName = personne.getPrenom();
        sLastName = personne.getNom();
        sEMail = personne.getEmail();
        sAddress = personne.getAdresse().toString();
        sCity = personne.getAdresse().getVille().toString();
        sNPA = "" + personne.getAdresse().getVille().getCp();
        sCountry = personne.getAdresse().getVille().getPays().toString();
        sStatut = personne.getStatut();
        sContract = personne.getTypeContrat();
        sSupervisor = mscController.getSupervisor(personne.getResponsable());

        // Liste déroulante pour séléctionner les champs que l'on souhaite modifier
        JPanel modification = new JPanel();
        boxChoiceLabel = new JComboBox();
        boxChoiceLabel.addItem("Tous les champs");
        boxChoiceLabel.addItem("Nom");
        boxChoiceLabel.addItem("Prénom");
        boxChoiceLabel.addItem("Responsable");
        boxChoiceLabel.addItem("Adresse E-Mail");
        boxChoiceLabel.addItem("Adresse");
        boxChoiceLabel.addItem("Téléphone");
        boxChoiceLabel.addItem("Statut");
        boxChoiceLabel.addItem("Contrat");

        modification.add(boxChoiceLabel);
        gbcConstraint.gridx = 0;
        gbcConstraint.gridy = y;
        gbcConstraint.insets = new Insets(5, 5, 10, 10);
        gbcConstraint.anchor = GridBagConstraints.NORTH;
        ++y;
        jpModifyPanel.add(modification, gbcConstraint);

        /**
         * Bouton pour demander l'ajout de champ à modifier
         */
        JPanel modifyLabel = new JPanel();
        JButton newLabel = new JButton("Ajouter un nouveau champ");
        modifyLabel.add(newLabel);
        gbcConstraint.gridy = y;
        ++y;
        jpModifyPanel.add(modifyLabel, gbcConstraint);

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
                if (boxChoiceLabel.getSelectedItem().equals("Statut")) {
                    addStatut();
                    addModifyButton();
                }
                if(boxChoiceLabel.getSelectedItem().equals("Contrat")){
                    addContract();
                    addModifyButton();
                }
                if (boxChoiceLabel.getSelectedItem().equals("Tous les champs")) {
                    addLastName();
                    addFirstName();
                    addSupervisor();
                    addEMail();
                    addAddress();
                    addPhone();
                    addStatut();
                    addContract();
                    addModifyButton();
                }
            }
        });


        this.setVisible(true);
        gbcConstraint.weighty = 1.0;
        jpModifyPanel.add(new JPanel(), gbcConstraint);
        jpMainPanel.add(jpModifyPanel, gbcConstraint);
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
            lastNameLabel.setPreferredSize(dLabel);
            lastNamePanel.add(lastNameLabel);
            jtfLastNameInput = new JTextField(sLastName);
            jtfLastNameInput.setPreferredSize(dInput);
            lastNamePanel.add(jtfLastNameInput);
            gbcConstraint.anchor = GridBagConstraints.WEST;
            gbcConstraint.gridy = y;
            ++y;
            jpModifyPanel.add(lastNamePanel, gbcConstraint);
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
            firstNameLabel.setPreferredSize(dLabel);
            firstNamePanel.add(firstNameLabel);
            jtfFirstNameInput = new JTextField(sFirstName);
            jtfFirstNameInput.setPreferredSize(dInput);
            firstNamePanel.add(jtfFirstNameInput);
            gbcConstraint.anchor = GridBagConstraints.WEST;
            gbcConstraint.gridy = y;
            ++y;
            jpModifyPanel.add(firstNamePanel, gbcConstraint);
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
            emailLabel.setPreferredSize(dLabel);
            emailPanel.add(emailLabel);
            jtfEmail = new JTextField(sEMail);
            jtfEmail.setPreferredSize(dInput);
            emailPanel.add(jtfEmail);
            gbcConstraint.anchor = GridBagConstraints.WEST;
            gbcConstraint.gridy = y;
            ++y;
            jpModifyPanel.add(emailPanel, gbcConstraint);
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
        addressLabel.setPreferredSize(dLabel);
        addressPanel.add(addressLabel);
        // Récupération de l'adresse
        jtfAddress = new JTextField(sAddress);
        jtfAddress.setPreferredSize(dInput);
        addressPanel.add(jtfAddress);
        gbcConstraint.anchor = GridBagConstraints.WEST;
        gbcConstraint.gridy = y;
        ++y;
        jpModifyPanel.add(addressPanel, gbcConstraint);

        // Pour ajouter le npa
        JPanel jpNPA = new JPanel();
        JLabel jlNPA = new JLabel("NPA : ");
        jlNPA.setPreferredSize(dLabel);
        jpNPA.add(jlNPA);
        // récupération du npa
        jtfNPA = new JTextField(sNPA);
        jtfNPA.setPreferredSize(dInput);
        jpNPA.add(jtfNPA);
        gbcConstraint.anchor = GridBagConstraints.WEST;
        gbcConstraint.gridy = y;
        ++y;
        jpModifyPanel.add(jpNPA, gbcConstraint);

        // Pour ajouter la ville
        JPanel jpCity = new JPanel();
        JLabel jlCity = new JLabel("Ville : ");
        jlCity.setPreferredSize(dLabel);
        jpCity.add(jlCity);
        // Récupération de la ville
        jtfCity = new JTextField(sCity);
        jtfCity.setPreferredSize(dInput);
        jpCity.add(jtfCity);
        gbcConstraint.anchor = GridBagConstraints.WEST;
        gbcConstraint.gridy = y;
        ++y;
        jpModifyPanel.add(jpCity, gbcConstraint);

        //Pour ajouter le pays
        JPanel jpCountry = new JPanel();
        JLabel jlCountry = new JLabel("Pays : ");
        jlCountry.setPreferredSize(dLabel);
        jpCountry.add(jlCountry);
        // Récupération du Pays
        jcbCountry = new JComboBox();
        for(int i = 0; i < alpCountries.size(); ++i){
            jcbCountry.addItem(alpCountries.get(i).getPays());
        }
        jcbCountry.setPreferredSize(dInput);
        jpCountry.add(jcbCountry);
        gbcConstraint.anchor = GridBagConstraints.WEST;
        gbcConstraint.gridy = y;
        ++y;
        jpModifyPanel.add(jpCountry, gbcConstraint);

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
            jlPhone.setPreferredSize(dLabel);
            jpPhone.add(jlPhone);
            jtfPhone = new JTextField(sPhone);
            jtfPhone.setPreferredSize(dInput);
            jpPhone.add(jtfPhone);
            gbcConstraint.anchor = GridBagConstraints.WEST;
            gbcConstraint.gridy = y;
            ++y;
            jpModifyPanel.add(jpPhone, gbcConstraint);
            jpMainPanel.revalidate();
            System.out.println("modif Téléphone");
        }
    }

    /**
     * Méthode pour ajouter le superviseur au panneaux de modification
     */
    private void addSupervisor() {
        if (!bSupervisor) {
            bSupervisor = true;
            JPanel jpSupervisor = new JPanel();
            JLabel jlSupervisor = new JLabel("Responsable : ");
            jlSupervisor.setPreferredSize(dLabel);
            jpSupervisor.add(jlSupervisor);
            jcbSupervisor = new JComboBox();
            for(int i = 0; i < alpSupervisor.size(); ++i){
                jcbSupervisor.addItem(alpSupervisor.get(i).getPrenom() + " " + alpSupervisor.get(i).getNom());
            }
            jcbSupervisor.setPreferredSize(dInput);
            jpSupervisor.add(jcbSupervisor);
            gbcConstraint.anchor = GridBagConstraints.WEST;
            gbcConstraint.gridy = y;
            ++y;
            jpModifyPanel.add(jpSupervisor, gbcConstraint);
            jpMainPanel.revalidate();
            System.out.println("modif Responsable");
        }
    }

    private void addStatut(){
        if(!bStatut){
            bStatut = true;
            JPanel jpStatut = new JPanel();
            JLabel jlStatut = new JLabel("Statut : ");
            jpStatut.add(jlStatut);
            jcbStatut = new JComboBox();
            for(String statut : alsStatus) {
                jcbStatut.addItem(statut);
            }
            jpStatut.add(jcbStatut);
            gbcConstraint.anchor = GridBagConstraints.WEST;
            gbcConstraint.gridy = y;
            ++y;
            jpModifyPanel.add(jpStatut, gbcConstraint);
            jpMainPanel.revalidate();
            System.out.println("modif Statut");
        }
    }

    private void addContract(){
        if(!bContract){
            bContract = true;
            JPanel jpContract = new JPanel();
            JLabel jlContract = new JLabel("Contrat : ");
            jpContract.add(jlContract);
            jcbContract = new JComboBox();
            for(String contract : alsContract) {
                jcbContract.addItem(contract);
            }
            jpContract.add(jcbContract);
            gbcConstraint.anchor = GridBagConstraints.WEST;
            gbcConstraint.gridy = y;
            ++y;
            jpModifyPanel.add(jpContract, gbcConstraint);
            jpMainPanel.revalidate();
            System.out.println("modif Statut");
        }
    }

    /**
     * Méthode pour ajouter le bouton de mise à jour
     */
    private void addModifyButton() {
        if (!bButton) {
            bButton = true;
            JButton jbModify = new JButton("Modifier");
            gbcConstraint.gridy = NUMBER_OF_ROW;
            gbcConstraint.anchor = GridBagConstraints.CENTER;
            jpModifyPanel.add(jbModify, gbcConstraint);
            jbModify.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    disableError();
                    sFirstName = jtfFirstNameInput.getText();
                    sLastName = jtfLastNameInput.getText();
                    sEMail = jtfEmail.getText();
                    sAddress = jtfAddress.getText();
                    sNPA = jtfNPA.getText();
                    sCity = jtfCity.getText();
                    sCountry = jcbCountry.getSelectedItem().toString();
                    sPhone = jtfPhone.getText();
                    sSupervisor = jcbSupervisor.getSelectedItem().toString();
                    sContract = jcbContract.getSelectedItem().toString();
                    sStatut = jcbStatut.getSelectedItem().toString();
                    mscController.checkModifyStaff(sFirstName, sLastName, sSupervisor, sEMail, sAddress,
                            sNPA, sCity, sCountry, sPhone, sContract, sStatut);
                }
            });
        }
    }

    /**
     * Méthoed permettant de réinitialiser les états d'erreur crée lors de mauvaises saisies
     */
    public void disableError() {
        jtfFirstNameInput.setBackground(Color.WHITE);
        jtfFirstNameInput.setToolTipText(null);
        jtfLastNameInput.setBackground(Color.WHITE);
        jtfLastNameInput.setToolTipText(null);
        jtfEmail.setBackground(Color.WHITE);
        jtfEmail.setToolTipText(null);
        jtfAddress.setBackground(Color.WHITE);
        jtfAddress.setToolTipText(null);
        jtfNPA.setBackground(Color.WHITE);
        jtfNPA.setToolTipText(null);
        jtfCity.setBackground(Color.WHITE);
        jtfCity.setToolTipText(null);
        jcbCountry.setBackground(Color.WHITE);
        jcbCountry.setToolTipText(null);
        jtfPhone.setBackground(Color.WHITE);
        jtfPhone.setToolTipText(null);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie du prénom
     * @param error message indiquant plus précisément l'erreur
     */
    public void setFirstNameError(String error) {
        jtfFirstNameInput.setToolTipText(error);
        jtfFirstNameInput.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie du nom
     * @param error message indiquant plus précisément l'erreur
     */
    public void setLastNameError(String error) {
        jtfLastNameInput.setToolTipText(error);
        jtfLastNameInput.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie de l'email
     * @param error message indiquant plus précisément l'erreur
     */
    public void setEmailError(String error) {
        jtfEmail.setToolTipText(error);
        jtfEmail.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie de l'adresse
     * @param error message indiquant plus précisément l'erreur
     */
    public void setAddressError(String error) {
        jtfAddress.setToolTipText(error);
        jtfAddress.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie de la ville
     * @param error message indiquant plus précisément l'erreur
     */
    public void setCityError(String error) {
        jtfCity.setToolTipText(error);
        jtfCity.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie du npa
     * @param error message indiquant plus précisément l'erreur
     */
    public void setNPAError(String error) {
        jtfNPA.setToolTipText(error);
        jtfNPA.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie du télephone
     * @param error message indiquant plus précisément l'erreur
     */
    public void setPhoneError(String error) {
        jtfPhone.setToolTipText(error);
        jtfPhone.setBackground(Color.RED);
    }
}
