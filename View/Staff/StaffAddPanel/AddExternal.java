package View.Staff.StaffAddPanel;

import Controller.Staff.AddExternalController;
import Model.Pays;
import View.GenericWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Bureau on 23.04.2017.
 */
public class AddExternal extends GenericWindow{
    private AddExternalController aecController = null;

    private GridBagConstraints gbcConstraint = new GridBagConstraints();
    private Dimension dLabel = new Dimension(90, 30);
    private Dimension dInput = new Dimension(150, 30);

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
    private JComboBox jcbCountry;
    private JTextField jtfPhone;


    public AddExternal(AddExternalController controller, ArrayList<Pays> countries){
        super("Ajout d'intervenant");
        aecController = controller;
        jpMainPanel.setLayout(new GridBagLayout());


        // Ajout des champs utiles pour le nom
        JPanel jpLastNamePanel = new JPanel();
        JLabel jlLastNameLabel = new JLabel("Nom : ");
        jlLastNameLabel.setPreferredSize(dLabel);
        jpLastNamePanel.add(jlLastNameLabel);
        jtfLastNameInput = new JTextField("last");
        jtfLastNameInput.setToolTipText("caractères accepté [A-Z], [a-z], [0-9], [' -]");
        jtfLastNameInput.setPreferredSize(dInput);
        jpLastNamePanel.add(jtfLastNameInput);
        gbcConstraint.gridx = 0;
        gbcConstraint.gridy = 0;
        gbcConstraint.insets = new Insets(10,5,10,5);
        gbcConstraint.anchor = GridBagConstraints.WEST;
        jpMainPanel.add(jpLastNamePanel, gbcConstraint);

        // Ajout des champs utiles pour le prénom
        JPanel jpFirstNamePanel = new JPanel();
        JLabel lastNameLabel = new JLabel("Prénom : ");
        lastNameLabel.setPreferredSize(dLabel);
        jpFirstNamePanel.add(lastNameLabel);
        jtfFirstNameInput = new JTextField("first");
        jtfFirstNameInput.setToolTipText("caractères accepté [A-Z], [a-z], [0-9], [' -]");
        jtfFirstNameInput.setPreferredSize(dInput);
        jpFirstNamePanel.add(jtfFirstNameInput);
        gbcConstraint.gridy = 1;
        jpMainPanel.add(jpFirstNamePanel, gbcConstraint);

        // Ajout des champs utiles pour l'entreprise
        JPanel jpCompagny = new JPanel();
        JLabel jlCompagny = new JLabel("Entreprise : ");
        jlCompagny.setPreferredSize(dLabel);
        jpCompagny.add(jlCompagny);
        jtfCompagnyInput = new JTextField("Entreprise");
        jtfCompagnyInput.setPreferredSize(dInput);
        jpCompagny.add(jtfCompagnyInput);
        gbcConstraint.gridy = 2;
        jpMainPanel.add(jpCompagny, gbcConstraint);

        // Ajout des champs utiles pour l'adresse
        JPanel jpAddress = new JPanel();
        JLabel jlAddress = new JLabel("Adresse : ");
        jlAddress.setPreferredSize(dLabel);
        jpAddress.add(jlAddress);
        jtfAddress = new JTextField("adresse");
        jtfAddress.setPreferredSize(dInput);
        jpAddress.add(jtfAddress);
        gbcConstraint.gridy = 3;
        jpMainPanel.add(jpAddress, gbcConstraint);

        // Ajout des champs utiles pour la ville
        JPanel jpCity = new JPanel();
        JLabel jlCity = new JLabel("Ville : ");
        jlCity.setPreferredSize(dLabel);
        jpCity.add(jlCity);
        jtfCity = new JTextField("Ville");
        jtfCity.setPreferredSize(dInput);
        jpCity.add(jtfCity);
        gbcConstraint.gridy = 4;
        jpMainPanel.add(jpCity, gbcConstraint);

        // Ajout des champs utiles pour le NPA
        JPanel jpNPA = new JPanel();
        JLabel jlNPA = new JLabel("NPA : ");
        jlNPA.setPreferredSize(dLabel);
        jpNPA.add(jlNPA);
        jtfNPA = new JTextField("npa");
        jtfNPA.setPreferredSize(dInput);
        jpNPA.add(jtfNPA);
        gbcConstraint.gridy = 5;
        jpMainPanel.add(jpNPA, gbcConstraint);

        // Ajout des champs utiles pour le Pays
        JPanel jpCountry = new JPanel();
        JLabel jlCountry = new JLabel("Pays : ");
        jlCountry.setPreferredSize(dLabel);
        jpCountry.add(jlCountry);
        jcbCountry = new JComboBox();
        for(int i = 0; i < countries.size(); ++i){
            jcbCountry.addItem(countries.get(i).getPays());
        }
        jcbCountry.setPreferredSize(dInput);
        jpCountry.add(jcbCountry);
        gbcConstraint.gridy = 6;
        jpMainPanel.add(jpCountry, gbcConstraint);

        // Ajout des champs utiles pour l'e-mail
        JPanel jpEmail = new JPanel();
        JLabel jlEmail = new JLabel("E-mail : ");
        jlEmail.setPreferredSize(dLabel);
        jpEmail.add(jlEmail);
        jtfEmail = new JTextField("e-mail");
        jtfEmail.setPreferredSize(dInput);
        jpEmail.add(jtfEmail);
        gbcConstraint.gridy = 7;
        jpMainPanel.add(jpEmail, gbcConstraint);

        // Ajout des champs utiles pour le télephone
        JPanel jpPhone = new JPanel();
        JLabel jlPhone = new JLabel("Téléphone : ");
        jlPhone.setPreferredSize(dLabel);
        jpPhone.add(jlPhone);
        jtfPhone = new JTextField("téléphone");
        jtfPhone.setPreferredSize(dInput);
        jpPhone.add(jtfPhone);
        gbcConstraint.gridy = 8;
        jpMainPanel.add(jpPhone, gbcConstraint);

        JButton add = new JButton("Ajouter");
        setButtonConfig(add);
        gbcConstraint.gridy = 9;
        jpMainPanel.add(add, gbcConstraint);

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
                sCity = jcbCountry.getSelectedItem().toString();
                sCountry = jcbCountry.getSelectedItem().toString();
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
        jtfLastNameInput.setBackground(Color.WHITE);
        jtfFirstNameInput.setBackground(Color.WHITE);
        jtfCompagnyInput.setBackground(Color.WHITE);
        jtfAddress.setBackground(Color.WHITE);
        jtfNPA.setBackground(Color.WHITE);
        jtfCity.setBackground(Color.WHITE);
        jtfEmail.setBackground(Color.WHITE);
        jtfPhone.setBackground(Color.WHITE);
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
     * Méthode permettant de signaler une erreur sur le champ de saisie de la compagnie
     * @param error message indiquant plus précisément l'erreur
     */
    public void setCompagnyError(String error) {
        jtfCompagnyInput.setToolTipText(error);
        jtfCompagnyInput.setBackground(Color.RED);
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
     * Méthode permettant de signaler une erreur sur le champ de saisie du npa
     * @param error message indiquant plus précisément l'erreur
     */
    public void setNPAError(String error) {
        jtfNPA.setToolTipText(error);
        jtfNPA.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie du pays
     * @param error message indiquant plus précisément l'erreur
     */
    public void setCityError(String error){
        jtfCity.setToolTipText(error);
        jtfCity.setBackground(Color.RED);
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
