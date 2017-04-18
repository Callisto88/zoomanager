package View.Staff.StaffAddPanel;

import Controller.Error.ErrorController;
import Controller.Staff.AddStaffController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.*;

/**
 * Created by Andre on 17.03.2017.
 * Fenêtre principale pour l'ajout de personnel
 */
public class AddStaff extends GenericWindow {
    // Controlleur de la fenêtre pour faire remonté les informations
    private AddStaffController controller;

    // String pour enregistrer la saisie.
    private String lastName;
    private String firstName;
    private String avs;
    private String email;
    private String address;
    private String city;
    private String npa;
    private String phone;
    private String supervisor;
    private String status;
    private String contract;

    // Champs de saisie
    private JTextField jtfLastNameInput;
    private JTextField jtfFirstNameInput;
    private JTextField jtfBirthdayInput;
    private JTextField jtfAVSInput;
    private JTextField jtfEmail;
    private JTextField jtfAddress;
    private JTextField jtfCity;
    private JTextField jtfNPA;
    private JTextField jtfPhone;
    private JTextField jtfSupervisor;
    private JTextField jtfStatus;
    private JTextField jtfContract;

    // Label d'erreur pour les différents champs
    private JLabel jlLastNameError = new JLabel("*", JLabel.CENTER);
    private JLabel jlFirstNameError = new JLabel("*", JLabel.CENTER);
    private JLabel jlBirthdayError = new JLabel("*", JLabel.CENTER);
    private JLabel jlAVSError = new JLabel("*", JLabel.CENTER);
    private JLabel jlEmailError = new JLabel("*", JLabel.CENTER);
    private JLabel jlAddressError = new JLabel("*", JLabel.CENTER);
    private JLabel jlCityError = new JLabel("*", JLabel.CENTER);
    private JLabel jlNPAError = new JLabel("*", JLabel.CENTER);
    private JLabel jlPhoneError = new JLabel("*", JLabel.CENTER);
    private JLabel jlSupervisorError = new JLabel("*", JLabel.CENTER);
    private JLabel jlStatusError = new JLabel("*", JLabel.CENTER);
    private JLabel jlContractError = new JLabel("*", JLabel.CENTER);

    /**
     * Constructeur de la fenêtre principale d'ajout de personnel.
     *
     * @param apc controlleur de la fenêtre pour permettre de lui remonter les information utiles.
     */
    public AddStaff(AddStaffController apc) {
        super("Ajout");
        controller = apc;
        jpMainPanel.setLayout(new GridLayout(13,1));


        // Ajout des champs utiles pour le nom
        JPanel jpLastNamePanel = new JPanel();
        JLabel jlLastNameLabel = new JLabel("Nom : ");
        jpLastNamePanel.add(jlLastNameLabel, JPanel.LEFT_ALIGNMENT);
        jtfLastNameInput = new JTextField("last", 7);
        jtfLastNameInput.setToolTipText("caractères accepté [A-Z], [a-z], [0-9], [' -]");
        jpLastNamePanel.add(jtfLastNameInput, JPanel.CENTER_ALIGNMENT);
        jlLastNameError.setFont(new Font("Serif", Font.BOLD, 32));
        jlLastNameError.setForeground(Color.RED);
        jlLastNameError.setHorizontalAlignment(JLabel.CENTER);
        jpLastNamePanel.add(jlLastNameError, JPanel.RIGHT_ALIGNMENT);
        jpMainPanel.add(jpLastNamePanel);

        // Ajout des champs utiles pour le prénom
        JPanel jpFirstNamePanel = new JPanel();
        JLabel lastNameLabel = new JLabel("Prénom : ");
        jpFirstNamePanel.add(lastNameLabel,JPanel.LEFT_ALIGNMENT);
        jtfFirstNameInput = new JTextField("first", 7);
        jtfFirstNameInput.setToolTipText("caractères accepté [A-Z], [a-z], [0-9], [' -]");
        jpFirstNamePanel.add(jtfFirstNameInput, JPanel.CENTER_ALIGNMENT);
        jlFirstNameError.setFont(new Font("Serif", Font.BOLD, 32));
        jlFirstNameError.setForeground(Color.RED);
        jlFirstNameError.setHorizontalAlignment(JLabel.CENTER);
        jpFirstNamePanel.add(jlFirstNameError,JPanel.RIGHT_ALIGNMENT);
        jpMainPanel.add(jpFirstNamePanel);

        // Ajout des champs utiles pour la date de naissance
        JPanel jpBirthdayPanel = new JPanel();
        JLabel jlBirthdayLabel = new JLabel("Date de Naissance : ");
        jpBirthdayPanel.add(jlBirthdayLabel);
        jtfBirthdayInput = new JTextField("birthday", 7);
        jpBirthdayPanel.add(jtfBirthdayInput);
        jlBirthdayError.setFont(new Font("Serif", Font.BOLD, 32));
        jlBirthdayError.setForeground(Color.RED);
        jlBirthdayError.setHorizontalAlignment(JLabel.CENTER);
        jpBirthdayPanel.add(jlBirthdayError);
        jpMainPanel.add(jpBirthdayPanel);

        // Ajout des champs utiles pour le numéro AVS
        JPanel jpAVS = new JPanel();
        JLabel jlAVS = new JLabel("Numéro AVS : ");
        jpAVS.add(jlAVS);
        jtfAVSInput = new JTextField("avs", 7);
        jpAVS.add(jtfAVSInput);
        jlAVSError.setFont(new Font("Serif", Font.BOLD, 32));
        jlAVSError.setForeground(Color.RED);
        jlAVSError.setHorizontalAlignment(JLabel.CENTER);
        jpAVS.add(jlAVSError);
        jpMainPanel.add(jpAVS);

        // Ajout des champs utiles pour l'adresse
        JPanel jpAddress = new JPanel();
        JLabel jlAddress = new JLabel("Adresse : ");
        jpAddress.add(jlAddress);
        jtfAddress = new JTextField("adresse", 7);
        jpAddress.add(jtfAddress);
        jlAddressError.setFont(new Font("Serif", Font.BOLD, 32));
        jlAddressError.setForeground(Color.RED);
        jlAddressError.setHorizontalAlignment(JLabel.CENTER);
        jpAddress.add(jlAddressError);
        jpMainPanel.add(jpAddress);

        // Ajout des champs utiles pour la ville
        JPanel jpCity = new JPanel();
        JLabel jlCity = new JLabel("Ville : ");
        jpCity.add(jlCity);
        jtfCity = new JTextField("ville", 7);
        jpCity.add(jtfCity);
        jlCityError.setFont(new Font("Serif", Font.BOLD, 32));
        jlCityError.setForeground(Color.RED);
        jlCityError.setHorizontalAlignment(JLabel.CENTER);
        jpCity.add(jlCityError);
        jpMainPanel.add(jpCity);

        // Ajout des champs utiles pour le NPA
        JPanel jpNPA = new JPanel();
        JLabel jlNPA = new JLabel("NPA : ");
        jpNPA.add(jlNPA);
        jtfNPA = new JTextField("npa", 7);
        jpNPA.add(jtfNPA);
        jlNPAError.setFont(new Font("Serif", Font.BOLD, 32));
        jlNPAError.setForeground(Color.RED);
        jlNPAError.setHorizontalAlignment(JLabel.CENTER);
        jpNPA.add(jlNPAError);
        jpMainPanel.add(jpNPA);

        // Ajout des champs utiles pour l'e-mail
        JPanel jpEmail = new JPanel();
        JLabel jlEmail = new JLabel("E-mail : ");
        jpEmail.add(jlEmail);
        jtfEmail = new JTextField("e-mail", 7);
        jpEmail.add(jtfEmail);
        jlEmailError.setFont(new Font("Serif", Font.BOLD, 32));
        jlEmailError.setForeground(Color.RED);
        jlEmailError.setHorizontalAlignment(JLabel.CENTER);
        jpEmail.add(jlEmailError);
        jpMainPanel.add(jpEmail);

        // Ajout des champs utiles pour le télephone
        JPanel jpPhone = new JPanel();
        JLabel jlPhone = new JLabel("Téléphone : ");
        jpPhone.add(jlPhone);
        jtfPhone = new JTextField("téléphone", 7);
        jpPhone.add(jtfPhone);
        jlPhoneError.setFont(new Font("Serif", Font.BOLD, 32));
        jlPhoneError.setForeground(Color.RED);
        jlPhoneError.setHorizontalAlignment(JLabel.CENTER);
        jpPhone.add(jlPhoneError);
        jpMainPanel.add(jpPhone);

        // Ajout des champs utiles pour le responsable
        JPanel jpSupervisor = new JPanel();
        JLabel jlSupervisor = new JLabel("Responsable : ");
        jpSupervisor.add(jlSupervisor);
        jtfSupervisor = new JTextField("responsable", 7);
        jpSupervisor.add(jtfSupervisor);
        jlSupervisorError.setFont(new Font("Serif", Font.BOLD, 32));
        jlSupervisorError.setForeground(Color.RED);
        jlSupervisorError.setHorizontalAlignment(JLabel.CENTER);
        jpSupervisor.add(jlSupervisorError);
        jpMainPanel.add(jpSupervisor);

        // Ajout des champs utiles pour le statut
        JPanel jpStatus = new JPanel();
        JLabel jlStatus = new JLabel("Status : ");
        jpStatus.add(jlStatus);
        jtfStatus = new JTextField("statut", 7);
        jpStatus.add(jtfStatus);
        jlStatusError.setFont(new Font("Serif", Font.BOLD, 32));
        jlStatusError.setForeground(Color.RED);
        jlStatusError.setHorizontalAlignment(JLabel.CENTER);
        jpStatus.add(jlStatusError);
        jpMainPanel.add(jpStatus);

        // Ajout des champs utiles pour le contrat
        JPanel jpContract = new JPanel();
        JLabel jlContract = new JLabel("Contrat : ");
        jpContract.add(jlContract);
        jtfContract = new JTextField("contrat", 7);
        jpContract.add(jtfContract);
        jlContractError.setFont(new Font("Serif", Font.BOLD, 32));
        jlContractError.setForeground(Color.RED);
        jlContractError.setHorizontalAlignment(JLabel.CENTER);
        jpContract.add(jlContractError);
        jpMainPanel.add(jpContract);

        JButton add = new JButton("Ajouter");
        setButtonConfig(add);
        jpMainPanel.add(add);

        // Permet de controller et mettre à jour à chaque fois que l'on va appuyer sur le bouton ajouter
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disableError();
                controller.resetError();
                System.out.println("ajout");
                lastName = jtfLastNameInput.getText();
                firstName = jtfLastNameInput.getText();
                avs = jtfAVSInput.getText();
                email = jtfEmail.getText();
                address = jtfAddress.getText();
                city = jtfCity.getText();
                npa = jtfNPA.getText();
                phone = jtfPhone.getText();
                supervisor = jtfSupervisor.getText();
                status = jtfStatus.getText();
                contract = jtfContract.getText();
                controller.checkFirstNameInput(firstName);
                controller.checkLastNameInput(lastName);
                controller.checkAVSInput(avs);
                controller.checkEmailInput(email);
                controller.checkAdress(address);
                controller.checkCityInput(city);
                controller.checkNPAInput(npa);
                controller.checkPhoneInput(phone);
            }
        });
        configFrame(getJfFrame(), this);
    }

    /**
     * Méthoed permettant de réinitialiser les états d'erreur crée lors de mauvaises saisies
     */
    public void disableError() {
        jlLastNameError.setVisible(false);
        jlFirstNameError.setVisible(false);
        jlBirthdayError.setVisible(false);
        jlAVSError.setVisible(false);
        jlAddressError.setVisible(false);
        jlCityError.setVisible(false);
        jlNPAError.setVisible(false);
        jlEmailError.setVisible(false);
        jlPhoneError.setVisible(false);
        jlStatusError.setVisible(false);
        jlSupervisorError.setVisible(false);
        jlContractError.setVisible(false);
    }

    /**
     * Méthode permettant d'afficher un astérix rouge signalant une erreur de saisie,
     * en plus de mettre en information sur l'astérix le problème
     * @param error permet de spécifier ce qui à déclenché l'erreur
     */
    public void setJlFirstNameError(String error) {
        jlFirstNameError.setToolTipText(error);
        jlFirstNameError.setVisible(true);
    }

    /**
     * Méthode permettant d'afficher un astérix rouge signalant une erreur de saisie,
     * en plus de mettre en information sur l'astérix le problème
     * @param error permet de spécifier ce qui à déclenché l'erreur
     */
    public void setJlLastNameError(String error) {
        jlLastNameError.setToolTipText(error);
        jlLastNameError.setVisible(true);
    }

    /**
     * Méthode permettant d'afficher un astérix rouge signalant une erreur de saisie,
     * en plus de mettre en information sur l'astérix le problème
     * @param error permet de spécifier ce qui à déclenché l'erreur
     */
    public void setJlBirthdayError(String error) {
        jlBirthdayError.setToolTipText(error);
        jlBirthdayError.setVisible(true);
    }

    /**
     * Méthode permettant d'afficher un astérix rouge signalant une erreur de saisie,
     * en plus de mettre en information sur l'astérix le problème
     * @param error permet de spécifier ce qui à déclenché l'erreur
     */
    public void setAVSError(String error) {
        jlAVSError.setToolTipText(error);
        jlAVSError.setVisible(true);
    }

    /**
     * Méthode permettant d'afficher un astérix rouge signalant une erreur de saisie,
     * en plus de mettre en information sur l'astérix le problème
     * @param error permet de spécifier ce qui à déclenché l'erreur
     */
    public void setJlEmailError(String error) {
        jlEmailError.setToolTipText(error);
        jlEmailError.setVisible(true);
    }

    /**
     * Méthode permettant d'afficher un astérix rouge signalant une erreur de saisie,
     * en plus de mettre en information sur l'astérix le problème
     * @param error permet de spécifier ce qui à déclenché l'erreur
     */
    public void setJlAddressError(String error) {
        jlAddressError.setToolTipText(error);
        jlAddressError.setVisible(true);
    }

    /**
     * Méthode permettant d'afficher un astérix rouge signalant une erreur de saisie,
     * en plus de mettre en information sur l'astérix le problème
     * @param error permet de spécifier ce qui à déclenché l'erreur
     */
    public void setJlCityError(String error) {
        jlCityError.setToolTipText(error);
        jlCityError.setVisible(true);
    }

    /**
     * Méthode permettant d'afficher un astérix rouge signalant une erreur de saisie,
     * en plus de mettre en information sur l'astérix le problème
     * @param error permet de spécifier ce qui à déclenché l'erreur
     */
    public void setNPAError(String error) {
        jlNPAError.setToolTipText(error);
        jlNPAError.setVisible(true);
    }

    /**
     * Méthode permettant d'afficher un astérix rouge signalant une erreur de saisie,
     * en plus de mettre en information sur l'astérix le problème
     * @param error permet de spécifier ce qui à déclenché l'erreur
     */
    public void setJlPhoneError(String error) {
        jlPhoneError.setToolTipText(error);
        jlPhoneError.setVisible(true);
    }
}
