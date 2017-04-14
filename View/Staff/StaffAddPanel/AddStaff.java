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
    AddStaffInputError inputError = null;
    ErrorController error = null;
    private AddStaffController controller;
    private AddStaffInput input = null;
    private String lastName;
    private String firstName;
    private String avs;
    private String email;
    private String address;
    private String city;
    private String npa;
    private String phone;
    private String salary;

    private JTextField lastNameInput;
    private JTextField firstNameInput;
    private JTextField avsInput;
    private JTextField emailInput;
    private JTextField addressInput;
    private JTextField cityInput;
    private JTextField npaInput;
    private JTextField phoneInput;
    private JTextField salaryInput;
    private JTextField statutInput;
    private JTextField typeInput;

    private JLabel lastNameError = new JLabel("*", JLabel.CENTER);
    private JLabel firstNameError = new JLabel("*", JLabel.CENTER);
    private JLabel birthdayError = new JLabel("*", JLabel.CENTER);
    private JLabel avsError = new JLabel("*", JLabel.CENTER);
    private JLabel emailError = new JLabel("*", JLabel.CENTER);
    private JLabel addressError = new JLabel("*", JLabel.CENTER);
    private JLabel cityError = new JLabel("*", JLabel.CENTER);
    private JLabel npaError = new JLabel("*", JLabel.CENTER);
    private JLabel phoneError = new JLabel("*", JLabel.CENTER);
    private JLabel salaryError = new JLabel("*", JLabel.CENTER);

    /**
     * Constructeur de la fenêtre principale d'ajout de personnel.
     *
     * @param apc controlleur de la fenêtre pour permettre de lui remonter les information utiles.
     */
    public AddStaff(AddStaffController apc) {
        super("Ajout");
        controller = apc;
        //jpMainPanel.setLayout(new GridLayout(3,0));
        /*JPanel jpButtonStock = new JPanel();
        jpButtonStock.setBackground(Color.cyan);
        jpLeft.add(jpButtonStock, gbcLeft);*/

        GridBagLayout gblStockBoutton = new GridBagLayout();
        //jpButtonStock.setLayout(gblStockBoutton);
        GridBagConstraints gbcStockBouton = new GridBagConstraints();

        //jpMainPanel.setLayout(new GridLayout(12,1));
        AddStaffLabel label = new AddStaffLabel();
        jpMainPanel.add(label);

/*
        // Ajout des champs utiles pour le nom
        JPanel lastNamePanel = new JPanel();
        JLabel firstNameLabel = new JLabel("Nom : ");
        lastNamePanel.add(firstNameLabel, JPanel.LEFT_ALIGNMENT);
        lastNameInput = new JTextField("last", 7);
        lastNameInput.setToolTipText("caractères accepté [A-Z], [a-z], [0-9], [' -]");
        lastNamePanel.add(lastNameInput, JPanel.CENTER_ALIGNMENT);
        lastNameError.setFont(new Font("Serif", Font.BOLD, 32));
        lastNameError.setForeground(Color.RED);
        lastNameError.setHorizontalAlignment(JLabel.CENTER);
        lastNamePanel.add(lastNameError, JPanel.RIGHT_ALIGNMENT);
        jpMainPanel.add(lastNamePanel);

        // Ajout des champs utiles pour le prénom
        JPanel firstNamePanel = new JPanel();
        JLabel lastNameLabel = new JLabel("Prénom : ");
        firstNamePanel.add(lastNameLabel,JPanel.LEFT_ALIGNMENT);
        firstNameInput = new JTextField("first", 7);
        firstNameInput.setToolTipText("caractères accepté [A-Z], [a-z], [0-9], [' -]");
        firstNamePanel.add(firstNameInput, JPanel.CENTER_ALIGNMENT);
        firstNameError.setFont(new Font("Serif", Font.BOLD, 32));
        firstNameError.setForeground(Color.RED);
        firstNameError.setHorizontalAlignment(JLabel.CENTER);
        firstNamePanel.add(firstNameError,JPanel.RIGHT_ALIGNMENT);
        jpMainPanel.add(firstNamePanel);

        // Ajout des champs utiles pour la date de naissance
        JPanel birthdayPanel = new JPanel();
        JLabel birthdayLabel = new JLabel("Date de Naissance : ");
        birthdayPanel.add(birthdayLabel);
        JTextField birthdayInput = new JTextField("birthday", 7);
        birthdayPanel.add(birthdayInput);
        birthdayError.setFont(new Font("Serif", Font.BOLD, 32));
        birthdayError.setForeground(Color.RED);
        birthdayError.setHorizontalAlignment(JLabel.CENTER);
        jpMainPanel.add(birthdayPanel);
*/

        input = new AddStaffInput();
        jpMainPanel.add(input);

        inputError = new AddStaffInputError();
        jpMainPanel.add(inputError);

        JButton add = new JButton("Ajouter");
        setButtonConfig(add);
        jpMainPanel.add(add);

        // Permet de controller et mettre à jour à chaque fois que l'on va appuyer sur le bouton ajouter
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputError.disableError();
                controller.resetError();
                System.out.println("ajout");
                lastName = input.getInputLastName();
                firstName = input.getInputFirstName();
                avs = input.getInputAVS();
                email = input.getEMailInput();
                address = input.getInputAddress();
                city = input.getInputCity();
                npa = input.getInputNPA();
                phone = input.getInputPhone();
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

    public void setFirstNameError(String error) {
        inputError.setFirstNameError(error);
    }

    public void setLastNameError(String error) {
        inputError.setLastNameError(error);
    }

    public void setBirthdayError(String error) {
        inputError.setBirthdayError(error);
    }

    public void setAVSError(String error) {
        inputError.setAVSError(error);
    }

    public void setEmailError(String error) {
        inputError.setEMailError(error);
    }

    public void setAddressError(String error) {
        inputError.setAddressError(error);
    }

    public void setCityError(String error) {
        inputError.setCityError(error);
    }

    public void setNPAError(String error) {
        inputError.setNPAError(error);
    }

    public void setPhoneError(String error) {
        inputError.setPhoneError(error);
    }

}
