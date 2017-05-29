package View.Staff.ModifyPanel;

import Controller.Staff.ModifyStaffController;
import Model.Pays;
import Model.Personne;
import View.GenericWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Classe pour la modification d'un employé
 * Created by Andre on 09.04.2017.
 */
public class ModifyStaffPanel extends GenericWindow {
    // Permet d'avoir une référence sur des objets
    private Personne personne = null;
    private ModifyStaffController mscController = null;
    private ArrayList<Personne> alpSupervisor = null;
    private ArrayList<String> alsContract = null;
    private ArrayList<String> alsStatus = null;
    private ArrayList<Pays> alpCountries = null;

    private int NUMBER_OF_ROW = 13;
    private int y = 0;

    // Permet d'avoir des dimensions identiques pour aligner les champs
    private Dimension dLabel = new Dimension(120, 30);
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
    private int iSupervisor = 0;
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

    /**
     * Constructeur de la fenêtre de modification de personnel
     * @param mscController controlleur de la fenêtre de modification d'employé
     * @param personne personne que l'on souhaite modifier
     * @param contract liste des contrats présents dans la BDD
     * @param status liste des statut présents dans la BDD
     * @param countries liste des pays présents dans la BDD
     * @param supervisor Listes des personnes responsables dans la BDD
     */
    public ModifyStaffPanel(ModifyStaffController mscController, Personne personne, ArrayList<String> contract,
                            ArrayList<String> status, ArrayList<Pays> countries, ArrayList<Personne> supervisor) {
        super("Modification");
        jpMainPanel.setLayout(new GridBagLayout());
        alpSupervisor = supervisor;
        alsContract = contract;
        alsStatus = status;
        alpCountries = countries;
        this.personne = personne;
        this.mscController = mscController;
        sFirstName = personne.getPrenom();
        sLastName = personne.getNom();
        if(personne.getEmail() == null){
            sEMail = "";
        }
        else {
            sEMail = personne.getEmail();
        }
        sAddress = personne.getAdresse().getAdresse();
        sCity = personne.getAdresse().getVille().getVille();
        sNPA = "" + personne.getAdresse().getVille().getCp();
        sCountry = personne.getAdresse().getVille().getPays().getPays();
        if(personne.getTelephone() == null){
            sPhone = "";
        }
        else{
            sPhone = personne.getTelephone();
        }
        sStatut = personne.getStatut();
        sContract = personne.getTypeContrat();
        if(personne.getResponsable() != 0) {
            iSupervisor = personne.getResponsable();
        }

        // Liste déroulante pour séléctionner les champs que l'on souhaite modifier
        JComboBox jcbChoiceLabel = new JComboBox();
        jcbChoiceLabel.addItem("Tous les champs");
        jcbChoiceLabel.addItem("Nom");
        jcbChoiceLabel.addItem("Prénom");
        jcbChoiceLabel.addItem("Responsable");
        jcbChoiceLabel.addItem("Adresse E-Mail");
        jcbChoiceLabel.addItem("Adresse");
        jcbChoiceLabel.addItem("Téléphone");
        jcbChoiceLabel.addItem("Statut");
        jcbChoiceLabel.addItem("Contrat");

        gbcConstraint.gridx = 0;
        gbcConstraint.gridy = y;
        gbcConstraint.insets = new Insets(5, 5, 10, 10);
        gbcConstraint.anchor = GridBagConstraints.NORTH;
        ++y;
        jpMainPanel.add(jcbChoiceLabel, gbcConstraint);

        // Bouton pour demander l'ajout de champ à modifier
        JButton newLabel = new JButton("Ajouter un nouveau champ");
        gbcConstraint.gridy = y;
        ++y;
        jpMainPanel.add(newLabel, gbcConstraint);

        /**
         * Permet d'ajouter un champ en plus lors de la séléction, ou directement tous les champs
         */
        newLabel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jcbChoiceLabel.getSelectedItem().equals("Nom")) {
                    addLastName();
                    addModifyButton();
                }

                if (jcbChoiceLabel.getSelectedItem().equals("Prénom")) {
                    addFirstName();
                    addModifyButton();
                }
                if (jcbChoiceLabel.getSelectedItem().equals("Adresse E-Mail")) {
                    addEMail();
                    addModifyButton();
                }

                if (jcbChoiceLabel.getSelectedItem().equals("Adresse")) {
                    addAddress();
                    addModifyButton();
                }
                if (jcbChoiceLabel.getSelectedItem().equals("Téléphone")) {
                    addPhone();
                    addModifyButton();
                }

                if (jcbChoiceLabel.getSelectedItem().equals("Responsable")) {
                    addSupervisor();
                    addModifyButton();
                }
                if (jcbChoiceLabel.getSelectedItem().equals("Statut")) {
                    addStatut();
                    addModifyButton();
                }
                if(jcbChoiceLabel.getSelectedItem().equals("Contrat")){
                    addContract();
                    addModifyButton();
                }
                if (jcbChoiceLabel.getSelectedItem().equals("Tous les champs")) {
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
        jpMainPanel.add(new JPanel(), gbcConstraint);
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
            jpMainPanel.add(lastNamePanel, gbcConstraint);
            jpMainPanel.revalidate();
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
            jpMainPanel.add(firstNamePanel, gbcConstraint);
            jpMainPanel.revalidate();
        }
    }

    /**
     * Méthode permettant d'ajouter le champ de l'E-Mail si celui ci n'est pas déjà présent
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
            jpMainPanel.add(emailPanel, gbcConstraint);
            jpMainPanel.revalidate();
        }
    }

    /**
     * Méthode permettant d'ajouter le champ adresse si celui ci n'est pas déjà présent
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
            jpMainPanel.add(addressPanel, gbcConstraint);

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
            jpMainPanel.add(jpNPA, gbcConstraint);

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
            jpMainPanel.add(jpCity, gbcConstraint);

        //Pour ajouter le pays
        JPanel jpCountry = new JPanel();
        JLabel jlCountry = new JLabel("Pays : ");
        jlCountry.setPreferredSize(dLabel);
        jpCountry.add(jlCountry);
        // Récupération du Pays
        jcbCountry = new JComboBox();
        int index = 0;
        for(int i = 0; i < alpCountries.size(); ++i){
            jcbCountry.addItem(alpCountries.get(i).getPays());
            if(personne.getAdresse() != null && alpCountries.get(i).getPays().equals(
                                                personne.getAdresse().getVille().getPays().getPays())){
                index = i;
            }
        }
        jcbCountry.setSelectedIndex(index);
        jcbCountry.setPreferredSize(dInput);
        jpCountry.add(jcbCountry);
        gbcConstraint.anchor = GridBagConstraints.WEST;
        gbcConstraint.gridy = y;
        ++y;
            jpMainPanel.add(jpCountry, gbcConstraint);

        jpMainPanel.revalidate();
    }

}

    /**
     * Méthode permettant d'ajouter le champ télephone si celui ci n'est pas déjà présent
     */
    private void addPhone() {
        if (!bPhone) {
            bPhone = true;
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
            jpMainPanel.add(jpPhone, gbcConstraint);
            jpMainPanel.revalidate();
        }
    }

    /**
     * Méthode permettant d'ajouter le champ du superviseur si celui ci n'est pas déjà présent
     */
    private void addSupervisor() {
        if (!bSupervisor) {
            bSupervisor = true;
            JPanel jpSupervisor = new JPanel();
            JLabel jlSupervisor = new JLabel("Responsable : ");
            jlSupervisor.setPreferredSize(dLabel);
            jpSupervisor.add(jlSupervisor);
            jcbSupervisor = new JComboBox();
            jcbSupervisor.addItem("");
            int index = 0;
            if(alpSupervisor != null) {
                for (int i = 0; i < alpSupervisor.size(); ++i) {
                    jcbSupervisor.addItem(alpSupervisor.get(i).getPrenom() + " " + alpSupervisor.get(i).getNom());
                    if (personne.getResponsable() != 0 && mscController.getSupervisor(personne.getResponsable()).equals(
                            alpSupervisor.get(i).getPrenom() + " " + alpSupervisor.get(i).getNom())) {
                        index = i + 1;
                    }
                }
            }
            jcbSupervisor.setSelectedIndex(index);
            jcbSupervisor.setPreferredSize(dInput);
            jpSupervisor.add(jcbSupervisor);
            gbcConstraint.anchor = GridBagConstraints.WEST;
            gbcConstraint.gridy = y;
            ++y;
            jpMainPanel.add(jpSupervisor, gbcConstraint);
            jpMainPanel.revalidate();
        }
    }

    /**
     * Méthode permettant d'ajouter le champ de statut si celui ci n'est pas déjà présent
     */
    private void addStatut(){
        if(!bStatut){
            bStatut = true;
            JPanel jpStatut = new JPanel();
            JLabel jlStatut = new JLabel("Statut : ");
            jlStatut.setPreferredSize(dLabel);
            jpStatut.add(jlStatut);
            jcbStatut = new JComboBox();
            int index = 0;
            for(int i = 0; i < alsStatus.size(); ++i){
                jcbStatut.addItem(alsStatus.get(i));
                if(alsStatus.get(i).equals(personne.getStatut())){
                    index = i;
                }
            }
            jcbStatut.setSelectedIndex(index);
            jcbStatut.setPreferredSize(dInput);
            jpStatut.add(jcbStatut);
            gbcConstraint.anchor = GridBagConstraints.WEST;
            gbcConstraint.gridy = y;
            ++y;
            jpMainPanel.add(jpStatut, gbcConstraint);
            jpMainPanel.revalidate();
        }
    }

    /**
     * Méthode permettant d'ajouter le champ de contrat si celui ci n'est pas déjà présent
     */
    private void addContract(){
        if(!bContract){
            bContract = true;
            JPanel jpContract = new JPanel();
            JLabel jlContract = new JLabel("Contrat : ");
            jlContract.setPreferredSize(dLabel);
            jpContract.add(jlContract);
            jcbContract = new JComboBox();
            int index = 0;
            for(int i = 0; i < alsContract.size(); ++i){
                jcbContract.addItem(alsContract.get(i));
                if(alsContract.get(i).equals(personne.getTypeContrat())){
                    index = i;
                }
            }
            jcbContract.setSelectedIndex(index);
            jcbContract.setPreferredSize(dInput);
            jpContract.add(jcbContract);
            gbcConstraint.anchor = GridBagConstraints.WEST;
            gbcConstraint.gridy = y;
            ++y;
            jpMainPanel.add(jpContract, gbcConstraint);
            jpMainPanel.revalidate();
        }
    }

    /**
     * Méthode permettant d'ajouter le bouton de mise à jour si celui ci n'est pas déjà présent
     */
    private void addModifyButton() {
        if (!bButton) {
            bButton = true;
            JButton jbModify = new JButton("Modifier");
            gbcConstraint.gridy = NUMBER_OF_ROW;
            gbcConstraint.anchor = GridBagConstraints.CENTER;
            jpMainPanel.add(jbModify, gbcConstraint);
            // Permet de récupérer l'action d'appui sur le bouton
            jbModify.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    disableError();
                    if(bFirstName) {
                        sFirstName = jtfFirstNameInput.getText();
                    }
                    if(bLastName) {
                        sLastName = jtfLastNameInput.getText();
                    }
                    if(bEMail) {
                        sEMail = jtfEmail.getText();
                    }
                    if(bAddress) {
                        sAddress = jtfAddress.getText();
                        sNPA = jtfNPA.getText();
                        sCity = jtfCity.getText();
                        sCountry = jcbCountry.getSelectedItem().toString();
                    }
                    if(bPhone) {
                        sPhone = jtfPhone.getText();
                    }
                    if(bSupervisor) {
                        if(jcbSupervisor.getSelectedIndex() != 0) {
                            iSupervisor = alpSupervisor.get(jcbSupervisor.getSelectedIndex() - 1).getIdPersonne();
                        }
                        else {
                            iSupervisor = 0;
                        }
                    }
                    if(bContract) {
                        sContract = jcbContract.getSelectedItem().toString();
                    }
                    if(bStatut) {
                        sStatut = jcbStatut.getSelectedItem().toString();
                    }

                    mscController.checkModifyStaff(sFirstName, sLastName, iSupervisor, sEMail, sAddress,
                            sNPA, sCity, sCountry, sPhone, sContract, sStatut);
                }
            });
        }
    }

    /**
     * Méthoed permettant de réinitialiser les états d'erreur crée lors de mauvaises saisies
     */
    public void disableError() {
        if(jtfFirstNameInput != null) {
            jtfFirstNameInput.setBackground(Color.WHITE);
            jtfFirstNameInput.setToolTipText(null);
        }
        if(jtfLastNameInput != null) {
            jtfLastNameInput.setBackground(Color.WHITE);
            jtfLastNameInput.setToolTipText(null);
        }
        if(jtfEmail != null) {
            jtfEmail.setBackground(Color.WHITE);
            jtfEmail.setToolTipText(null);
        }
        if(jtfAddress != null) {
            jtfAddress.setBackground(Color.WHITE);
            jtfAddress.setToolTipText(null);
        }
        if(jtfNPA != null) {
            jtfNPA.setBackground(Color.WHITE);
            jtfNPA.setToolTipText(null);
        }
        if(jtfCity != null) {
            jtfCity.setBackground(Color.WHITE);
            jtfCity.setToolTipText(null);
        }
        if(jcbCountry != null) {
            jcbCountry.setBackground(Color.WHITE);
            jcbCountry.setToolTipText(null);
        }
        if(jtfPhone != null) {
            jtfPhone.setBackground(Color.WHITE);
            jtfPhone.setToolTipText(null);
        }
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

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie du télephone
     * @param error message indiquant plus précisément l'erreur
     */
    public void setCountryError(String error) {
        jcbCountry.setToolTipText(error);
        jcbCountry.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de fermer la fenêtre une fois que l'on a fini de modifier du personnel
     */
    public void close(){
        this.getJfFrame().dispatchEvent(new WindowEvent(getJfFrame(), WindowEvent.WINDOW_CLOSING));
    }
}
