package View.Staff.StaffAddPanel;

import Controller.Staff.AddExternalController;
import Model.Adresse;
import Model.Pays;
import Model.Ville;
import View.GenericWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Bureau on 23.04.2017.
 */
public class AddExternal extends GenericWindow{
    private AddExternalController aecController = null;

    // String permettant de récupérer les champs de saisie
    private String sLastName;
    private String sFirstName;
    private String sCompagny;
    private String sEMail;
    private String sAddress;
    private String sNPA;
    private String sCity;
    private String sCountry;
    private String sPhone;

    private JTextField jtfLastNameInput;
    private JTextField jtfFirstNameInput;
    private JTextField jtfCompagnyInput;
    private JTextField jtfEmail;
    private JTextField jtfAddress;
    private JTextField jtfCity;
    private JTextField jtfNPA;
    private JTextField jtfCountry;
    private JTextField jtfPhone;

    private JLabel jlLastNameError = new JLabel("*", JLabel.CENTER);
    private JLabel jlFirstNameError = new JLabel("*", JLabel.CENTER);
    private JLabel jlBirthdayError = new JLabel("*", JLabel.CENTER);
    private JLabel jlCompagnyError = new JLabel("*", JLabel.CENTER);
    private JLabel jlEmailError = new JLabel("*", JLabel.CENTER);
    private JLabel jlAddressError = new JLabel("*", JLabel.CENTER);
    private JLabel jlCityError = new JLabel("*", JLabel.CENTER);
    private JLabel jlNPAError = new JLabel("*", JLabel.CENTER);
    private JLabel jlCountryError = new JLabel("*", JLabel.CENTER);
    private JLabel jlPhoneError = new JLabel("*", JLabel.CENTER);


    public AddExternal(AddExternalController controller){
        super("Ajout d'intervenant");
        aecController = controller;
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

        // Ajout des champs utiles pour l'entreprise
        JPanel jpCompagny = new JPanel();
        JLabel jlCompagny = new JLabel("Entreprise : ");
        jpCompagny.add(jlCompagny);
        jtfCompagnyInput = new JTextField("Entreprise", 7);
        jpCompagny.add(jtfCompagnyInput);
        jlCompagnyError.setFont(new Font("Serif", Font.BOLD, 32));
        jlCompagnyError.setForeground(Color.RED);
        jlCompagnyError.setHorizontalAlignment(JLabel.CENTER);
        jpCompagny.add(jlCompagnyError);
        jpMainPanel.add(jpCompagny);

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

        // Ajout des champs utiles pour le Pays
        JPanel jpCountry = new JPanel();
        JLabel jlCountry = new JLabel("Pays : ");
        jpCountry.add(jlCountry);
        jtfCountry = new JTextField("pays", 7);
        jpCountry.add(jtfCountry);
        jlCountryError.setFont(new Font("Serif", Font.BOLD, 32));
        jlCountryError.setForeground(Color.RED);
        jlCountryError.setHorizontalAlignment(JLabel.CENTER);
        jpCountry.add(jlCountryError);
        jpMainPanel.add(jpCountry);

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

        JButton add = new JButton("Ajouter");
        setButtonConfig(add);
        jpMainPanel.add(add);

        // Permet de controller et mettre à jour à chaque fois que l'on va appuyer sur le bouton ajouter
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disableError();
                System.out.println("ajout");
                sLastName = jtfLastNameInput.getText();
                sFirstName = jtfLastNameInput.getText();
                sCompagny = jtfCompagnyInput.getText();
                sEMail = jtfEmail.getText();
                sAddress = jtfAddress.getText();
                sNPA = jtfNPA.getText();
                sCity = jtfCity.getText();
                sCountry = jtfCountry.getText();
                sPhone = jtfPhone.getText();
                controller.checkExternal(sLastName, sFirstName, sCompagny, sEMail, sAddress, sNPA, sCity, sCountry, sPhone);
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
        jlCompagnyError.setVisible(false);
        jlAddressError.setVisible(false);
        jlCityError.setVisible(false);
        jlNPAError.setVisible(false);
        jlCountryError.setVisible(false);
        jlEmailError.setVisible(false);
        jlPhoneError.setVisible(false);
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
     * Méthode permettant de signaler une erreur sur le champ de saisie de la compagnie
     * @param error message indiquant plus précisément l'erreur
     */
    public void setCompagnyError(String error) {
        jlCompagnyError.setVisible(true);
        jlCompagnyError.setToolTipText(error);
        jtfCompagnyInput.setBackground(Color.RED);
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
