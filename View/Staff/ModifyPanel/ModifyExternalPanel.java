package View.Staff.ModifyPanel;

import Controller.Staff.ModifyExternalController;
import Model.Adresse;
import Model.Intervenant;
import Model.Pays;
import View.GenericWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Classe pour la fenêtre de modification d'un intervenant
 * Created by Bureau on 23.04.2017.
 */
public class ModifyExternalPanel extends GenericWindow {
    private JComboBox boxChoiceLabel = null;
    private Intervenant external = null;
    private  ModifyExternalController mecExternalController = null;

    // Permet d'avoir la liste actuel des pays
    private ArrayList<Pays> alpCountries = null;

    // variable pour permettre l'ajout dynamique des champs avec le bouton en position finale
    private JPanel jpModifyPanel = new JPanel();
    private GridBagConstraints gbcConstraint = new GridBagConstraints();
    private int x = 0;
    private int y = 0;
    private int NUMBER_OF_ROW = 11;

    private Dimension dLabel = new Dimension(90, 30);
    private Dimension dInput = new Dimension(120, 30);

    // Champs de saisie pour la modification
    private JTextField jtfLastNameInput;
    private JTextField jtfFirstNameInput;
    private JTextField jtfAddress;
    private JTextField jtfNPA;
    private JTextField jtfCity;
    private JComboBox jcbCountry = null;
    private JTextField jtfEmail;
    private JTextField jtfPhone;

    // String pour la récupération des champs de saisie
    private String sLastName;
    private String sFirstName;
    private String sEMail;
    private String sPhone;
    private String sAddress;
    private String sNPA;
    private String sCity;
    private String sCountry;

    // Booléens pour savoir si les camps on été demandé
    private boolean bFirstName = false;
    private boolean bLastName = false;
    private boolean bAddress = false;
    private boolean bEMail = false;
    private boolean bPhone = false;
    private boolean bButton = false;

    /**
     * Constructeur de la fenêtre de modification d'un intervenant
     * @param mecExternalController controlleur de la fenêtre de modification d'intervenant pour faire remonter les informations
     * @param external intervenant à modifier
     * @param countries Array list de pays
     */
    public ModifyExternalPanel(ModifyExternalController mecExternalController, Intervenant external, ArrayList<Pays> countries){
        super("Modification Intervenant");
        jpModifyPanel.setLayout(new GridBagLayout());
        this.external = external;
        this.mecExternalController = mecExternalController;
        alpCountries = countries;
        sLastName = external.getNom();
        sFirstName = external.getPrenom();
        sEMail = external.getEmail();
        sPhone = external.getTelephone();
        sAddress = external.getAdresse().toString();
        sCity = external.getAdresse().getVille().toString();
        sNPA = "" + external.getAdresse().getVille().getCp();
        sCountry = external.getAdresse().getVille().getPays().toString();
        GridBagLayout gblModify = new GridBagLayout();
        jpModifyPanel.setLayout(gblModify);

        // Liste déroulante pour séléctionner les champs que l'on souhaite modifier
        JPanel modification = new JPanel();
        boxChoiceLabel = new JComboBox();
        boxChoiceLabel.addItem("Tous les champs");
        boxChoiceLabel.addItem("Nom");
        boxChoiceLabel.addItem("Prénom");
        boxChoiceLabel.addItem("E-Mail");
        boxChoiceLabel.addItem("Téléphone");
        boxChoiceLabel.addItem("Adresse");
        modification.add(boxChoiceLabel);

        // contraintes permettant d'insérer les panels en incrémmentant y
        gbcConstraint.gridx = x;
        gbcConstraint.gridy = y;
        // Permet de crée des marges autour des panels lorsqu'on les inserera
        gbcConstraint.insets = new Insets(5, 5, 10, 10);
        gbcConstraint.anchor = GridBagConstraints.NORTH;
        ++y;
        jpModifyPanel.add(modification, gbcConstraint);

        // Bouton pour demander l'ajout de champ à modifier

        JPanel modifyLabel = new JPanel();
        JButton newLabel = new JButton("Ajouter un nouveau champ");
        modifyLabel.add(newLabel);
        gbcConstraint.gridy = y;
        ++y;
        jpModifyPanel.add(modifyLabel, gbcConstraint);

        // Permet d'ajouter un champ en plus lors de la séléction, ou directement tous les champs
        newLabel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(boxChoiceLabel.getSelectedItem().equals("Nom")){
                    addLastName();
                    addModifyButton();
                }

                if(boxChoiceLabel.getSelectedItem().equals("Prénom")){
                    addFirstName();
                    addModifyButton();
                }
                if(boxChoiceLabel.getSelectedItem().equals("E-Mail")){
                    addEMail();
                    addModifyButton();
                }
                if(boxChoiceLabel.getSelectedItem().equals("Adresse")){
                    addAddress();
                    addModifyButton();
                }
                if(boxChoiceLabel.getSelectedItem().equals("Téléphone")){
                    addPhone();
                    addModifyButton();
                }

                if(boxChoiceLabel.getSelectedItem().equals("Tous les champs")){
                    addLastName();
                    addFirstName();
                    addEMail();
                    addAddress();
                    addPhone();
                    addModifyButton();
                }
            }
        });
        this.setVisible(true);
        gbcConstraint.weighty = 1.0;
        jpModifyPanel.add(new JPanel(), gbcConstraint);
        jpMainPanel.add(jpModifyPanel, gbcConstraint);
        configFrame(getJfFrame(), this);
        this.setMinimumSize(new Dimension(200,400));
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
            JLabel emailLabel = new JLabel("E-Mail : ");
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
        if (!bAddress) {
            bAddress = true;
            // Pour ajouter l'adresse
            JPanel addressPanel = new JPanel();
            JLabel addressLabel = new JLabel("Adresse : ");
            addressLabel.setPreferredSize(dLabel);
            addressPanel.add(addressLabel);
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
            bPhone = true;
            JPanel telephonePanel = new JPanel();
            JLabel telephoneLabel = new JLabel("Téléphone : ");
            telephoneLabel.setPreferredSize(dLabel);
            telephonePanel.add(telephoneLabel);
            jtfPhone = new JTextField(sPhone);
            jtfPhone.setPreferredSize(dInput);
            telephonePanel.add(jtfPhone);
            gbcConstraint.anchor = GridBagConstraints.WEST;
            gbcConstraint.gridy = y;
            ++y;
            jpModifyPanel.add(telephonePanel, gbcConstraint);
            jpMainPanel.revalidate();
            System.out.println("modif Téléphone");
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
                    mecExternalController.checkModifyExternal(sFirstName, sLastName, sEMail, sAddress, sNPA, sCity, sCountry, sPhone);
                }
            });
        }
    }

    /**
     * Méthoed permettant de réinitialiser les états d'erreur crée lors de mauvaises saisies
     */
    public void disableError() {
        if(jtfLastNameInput != null) {
            jtfLastNameInput.setBackground(Color.WHITE);
            jtfLastNameInput.setToolTipText(null);
        }
        if(jtfFirstNameInput != null) {
            jtfFirstNameInput.setBackground(Color.WHITE);
            jtfFirstNameInput.setToolTipText(null);
        }
        if(jtfEmail != null) {
            jtfEmail.setBackground(Color.WHITE);
            jtfEmail.setToolTipText(null);
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
