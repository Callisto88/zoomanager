package View.Staff.AddPanel;

import Controller.Staff.AddExternalController;
import Model.Pays;
import View.GenericWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Class permettant d'ajouter un intervenant
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

    // Champs ou choix permettant la récupérations des données de l'utilisateur
    private JTextField jtfLastName;
    private JTextField jtfFirstName;
    private JTextField jtfCompagny;
    private JTextField jtfEmail;
    private JTextField jtfAddress;
    private JTextField jtfCity;
    private JTextField jtfNPA;
    private JComboBox jcbCountry;
    private JTextField jtfPhone;

    /**
     * Constructeur de la fenêtre d'ajout d'intervenants
     * @param controller controlleur de la fenêtre d'ajout permettant de faire remonter les informations
     * @param countries ArrayList contenant les différents pays actuellement présent
     */
    public AddExternal(AddExternalController controller, ArrayList<Pays> countries){
        super("Ajout d'intervenant");
        aecController = controller;
        jpMainPanel.setLayout(new GridBagLayout());


        Dimension dLabel = new Dimension(90, 30);
        Dimension dInput = new Dimension(150, 30);

        // Ajout des champs utiles pour le nom
        JPanel jpLastName = new JPanel();
        JLabel jlLastName = new JLabel("Nom : ");
        jlLastName.setPreferredSize(dLabel);
        jpLastName.add(jlLastName);
        jtfLastName = new JTextField("nom");
        jtfLastName.setToolTipText("caractères accepté [A-Z], [a-z], [0-9], [' -]");
        jtfLastName.setPreferredSize(dInput);
        jpLastName.add(jtfLastName);
        GridBagConstraints gbcConstraint = new GridBagConstraints();
        gbcConstraint.gridx = 0;
        gbcConstraint.gridy = 0;
        gbcConstraint.insets = new Insets(10,5,10,5);
        jpMainPanel.add(jpLastName, gbcConstraint);

        // Ajout des champs utiles pour le prénom
        JPanel jpFirstName = new JPanel();
        JLabel jFirstName = new JLabel("Prénom : ");
        jFirstName.setPreferredSize(dLabel);
        jpFirstName.add(jFirstName);
        jtfFirstName = new JTextField("prénom");
        jtfFirstName.setToolTipText("caractères accepté [A-Z], [a-z], [0-9], [' -]");
        jtfFirstName.setPreferredSize(dInput);
        jpFirstName.add(jtfFirstName);
        gbcConstraint.gridy = 1;
        jpMainPanel.add(jpFirstName, gbcConstraint);

        // Ajout des champs utiles pour l'entreprise
        JPanel jpCompagny = new JPanel();
        JLabel jlCompagny = new JLabel("Entreprise : ");
        jlCompagny.setPreferredSize(dLabel);
        jpCompagny.add(jlCompagny);
        jtfCompagny = new JTextField("entreprise");
        jtfCompagny.setPreferredSize(dInput);
        jpCompagny.add(jtfCompagny);
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
        jtfCity = new JTextField("ville");
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
        gbcConstraint.anchor = GridBagConstraints.CENTER;
        jpMainPanel.add(add, gbcConstraint);

        // Permet de controller et mettre à jour à chaque fois que l'on va appuyer sur le bouton ajouter
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disableError();
                sLastName = jtfLastName.getText();
                sFirstName = jtfFirstName.getText();
                sCompagny = jtfCompagny.getText();
                sEMail = jtfEmail.getText();
                sAddress = jtfAddress.getText();
                sNPA = jtfNPA.getText();
                sCity = jtfCity.getText();
                sCountry = jcbCountry.getSelectedItem().toString();
                sPhone = jtfPhone.getText();

                controller.checkExternal(sLastName, sFirstName, sCompagny, sEMail, sAddress, sNPA, sCity, sCountry, sPhone);
            }
        });
        configFrame(getJfFrame(), this);
    }

    /**
     * Méthode permettant de réinitialiser les états d'erreur crée lors de mauvaises saisies
     */
    public void disableError() {
        jtfLastName.setBackground(Color.WHITE);
        jtfLastName.setToolTipText(null);
        jtfFirstName.setBackground(Color.WHITE);
        jtfFirstName.setToolTipText(null);
        jtfCompagny.setBackground(Color.WHITE);
        jtfCompagny.setToolTipText(null);
        jtfAddress.setBackground(Color.WHITE);
        jtfAddress.setToolTipText(null);
        jtfNPA.setBackground(Color.WHITE);
        jtfNPA.setToolTipText(null);
        jtfCity.setBackground(Color.WHITE);
        jtfCity.setToolTipText(null);
        jtfEmail.setBackground(Color.WHITE);
        jtfEmail.setToolTipText(null);
        jtfPhone.setBackground(Color.WHITE);
        jtfPhone.setToolTipText(null);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie du prénom
     * @param error message indiquant plus précisément l'erreur
     */
    public void setFirstNameError(String error) {
        jtfFirstName.setToolTipText(error);
        jtfFirstName.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie du nom
     * @param error message indiquant plus précisément l'erreur
     */
    public void setLastNameError(String error) {
        jtfLastName.setToolTipText(error);
        jtfLastName.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie de la compagnie
     * @param error message indiquant plus précisément l'erreur
     */
    public void setCompagnyError(String error) {
        jtfCompagny.setToolTipText(error);
        jtfCompagny.setBackground(Color.RED);
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
     * Méthode permettant de signaler une erreur sur le champ de saisie de la ville
     * @param error message indiquant plus précisément l'erreur
     */
    public void setCityError(String error){
        jtfCity.setToolTipText(error);
        jtfCity.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de signaler une erreur sur le champ de saisie du pays
     * @param error message indiquant plus précisément l'erreur
     */
    public void setCountryError(String error){
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
    /**
     * Méthode permettant de fermer la fenêtre une fois que l'on a fini d'insérer des intervenants
     */
    public void close(){
        this.getJfFrame().dispatchEvent(new WindowEvent(getJfFrame(), WindowEvent.WINDOW_CLOSING));
    }
}
