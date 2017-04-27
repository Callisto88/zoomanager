package View.Staff.ModifyPanel;

import Controller.Staff.ModifyExternalController;
import Model.ExceptionDataBase;
import Model.Intervenant;
import View.GenericWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Bureau on 23.04.2017.
 */
public class ModifyExternalPanel extends GenericWindow {
    private JComboBox boxChoiceLabel = null;
    private Intervenant external = null;
    private  ModifyExternalController mecExternalController = null;

    // Champs de saisie pour la modification
    private JTextField jtfLastNameInput;
    private JTextField jtfFirstNameInput;
    private JTextField jtfEmail;
    private JTextField jtfPhone;

    // String pour la récupération des champs de saisie
    private String sLastName;
    private String sFirstName;
    private String sEMail;
    private String sPhone;

    // Label signalant une erreur
    private JLabel jlLastNameError = new JLabel("*", JLabel.CENTER);
    private JLabel jlFirstNameError = new JLabel("*", JLabel.CENTER);
    private JLabel jlEmailError = new JLabel("*", JLabel.CENTER);
    private JLabel jlPhoneError = new JLabel("*", JLabel.CENTER);

    // Booléens pour savoir si les camps on été demandé
    private boolean bFirstName = false;
    private boolean bLastName = false;
    private boolean bAddress = false;
    private boolean bEMail = false;
    private boolean bPhone = false;
    private boolean bButton = false;

    public ModifyExternalPanel(ModifyExternalController mecExternalController, Intervenant external){
        super("Modification Intervenant");
        this.external = external;
        this.mecExternalController = mecExternalController;
        jpMainPanel.setLayout(new GridLayout(8,1));
        /**
         * Liste déroulante pour séléctionner les champs que l'on souhaite modifier
         */
        JPanel modification = new JPanel();
        boxChoiceLabel = new JComboBox();
        boxChoiceLabel.addItem("Tous les champs");
        boxChoiceLabel.addItem("Nom");
        boxChoiceLabel.addItem("Prénom");
        boxChoiceLabel.addItem("E-Mail");
        boxChoiceLabel.addItem("Téléphone");
        modification.add(boxChoiceLabel);
        /*****************************************************************************************/
        GridBagConstraints gbcStockBouton = new GridBagConstraints();
        gbcStockBouton.insets = new Insets(0,15,0,15);

        jpMainPanel.add(modification);
        //jpMainPanel.add(boxChoiceLabel);

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
                if(boxChoiceLabel.getSelectedItem().equals("Ville")){
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
            lastNamePanel.add(lastNameLabel);
            jtfLastNameInput = new JTextField(external.getNom(), 20);
            lastNamePanel.add(jtfLastNameInput);
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
            jtfFirstNameInput = new JTextField(external.getPrenom(), 20);
            firstNamePanel.add(jtfFirstNameInput);
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
            JLabel emailLabel = new JLabel("Ville E-Mail : ");
            emailPanel.add(emailLabel);
            jtfEmail = new JTextField(external.getEmail(), 20);
            emailPanel.add(jtfEmail);
            jpMainPanel.add(emailPanel);
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
            JPanel addressPanel = new JPanel();
            JLabel addressLabel = new JLabel("Adresse : ");
            addressPanel.add(addressLabel);
            addressPanel.add(new JTextField(/*personne.getAdresse()*/"adresse", 20));
            jpMainPanel.add(addressPanel);
            jpMainPanel.revalidate();
            System.out.println("modif Ville");
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
            telephonePanel.add(telephoneLabel);
            jtfPhone = new JTextField(external.getTelephone(), 20);
            telephonePanel.add(jtfPhone);
            jpMainPanel.add(telephonePanel);
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
            jpMainPanel.add(jbModify);
            jbModify.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sFirstName = jtfFirstNameInput.getText();
                    sLastName = jtfLastNameInput.getText();
                    sEMail = jtfEmail.getText();
                    sPhone = jtfPhone.getText();
                    mecExternalController.checkModifyExternal(sFirstName, sLastName, sEMail, sPhone);
                }
            });
        }
    }

    /**
     * Méthoed permettant de réinitialiser les états d'erreur crée lors de mauvaises saisies
     */
    public void disableError() {
        jlLastNameError.setVisible(false);
        jlFirstNameError.setVisible(false);
        jlEmailError.setVisible(false);
        jlPhoneError.setVisible(false);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie du prénom
     * @param error message indiquant plus précisément l'erreur
     */
    public void setFirstNameError(String error) {
        jlFirstNameError.setVisible(true);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie du nom
     * @param error message indiquant plus précisément l'erreur
     */
    public void setLastNameError(String error) {
        jlLastNameError.setVisible(true);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie de l'email
     * @param error message indiquant plus précisément l'erreur
     */
    public void setEmailError(String error) {
        jlEmailError.setVisible(true);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie du télephone
     * @param error message indiquant plus précisément l'erreur
     */
    public void setPhoneError(String error) {
        jlPhoneError.setVisible(true);
    }
}
