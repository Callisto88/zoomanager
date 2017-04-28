package View.Staff.StaffAddPanel;

import Controller.Staff.AddStaffController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;

import Model.Adresse;
import Model.Pays;
import Model.Ville;
import View.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

/**
 * Created by Andre on 17.03.2017.
 * Fenêtre principale pour l'ajout de personnel
 */
public class AddStaff extends GenericWindow {
    // Controlleur de la fenêtre pour faire remonté les informations
    private AddStaffController controller;

    // Variables pour enregistrer la saisie.
    private String sLastName;
    private String sFirstName;
    private int iDay;
    private int iMonth;
    private int iYear;
    private String sAVS;
    private String sEMail;
    private String sAddress;
    private String sNPA;
    private String sCity;
    private String sCountry;
    private String sPhone;
    private String sSupervisor;
    private String sStatus;
    private String sContract;

    // Champs de saisie ou sélection
    private JTextField jtfLastNameInput;
    private JTextField jtfFirstNameInput;
    private JDatePickerImpl jdpriStartDatePicker = null;
    private JTextField jtfAVSInput;
    private JTextField jtfEmail;
    private JTextField jtfAddress;
    private JTextField jtfCity;
    private JTextField jtfNPA;
    private JTextField jtfCountry;
    private JTextField jtfPhone;
    private JTextField jtfSupervisor;
    private JComboBox jcbStatus;
    private JComboBox jcbContract;

    // Label d'erreur pour les différents champs
    private JLabel jlLastNameError = new JLabel("*", JLabel.CENTER);
    private JLabel jlFirstNameError = new JLabel("*", JLabel.CENTER);
    private JLabel jlBirthdayError = new JLabel("*", JLabel.CENTER);
    private JLabel jlAVSError = new JLabel("*", JLabel.CENTER);
    private JLabel jlEmailError = new JLabel("*", JLabel.CENTER);
    private JLabel jlAddressError = new JLabel("*", JLabel.CENTER);
    private JLabel jlNPAError = new JLabel("*", JLabel.CENTER);
    private JLabel jlCityError = new JLabel("*", JLabel.CENTER);
    private JLabel jlCountryError = new JLabel("*", JLabel.CENTER);
    private JLabel jlPhoneError = new JLabel("*", JLabel.CENTER);
    private JLabel jlSupervisorError = new JLabel("*", JLabel.CENTER);
    private JLabel jlStatusError = new JLabel("*", JLabel.CENTER);
    private JLabel jlContractError = new JLabel("*", JLabel.CENTER);

    /**
     * Constructeur de la fenêtre principale d'ajout de personnel.
     *
     * @param asc controlleur de la fenêtre pour permettre de lui remonter les information utiles.
     */
    public AddStaff(AddStaffController asc, ArrayList<String> statut, ArrayList<String> contract) {
        super("Ajout");
        controller = asc;
        jpMainPanel.setLayout(new GridLayout(14,1));


        // Ajout des champs utiles pour le nom
        JPanel jpLastNamePanel = new JPanel();
        JLabel jlLastNameLabel = new JLabel("Nom : ");
        jpLastNamePanel.add(jlLastNameLabel, JPanel.LEFT_ALIGNMENT);
        jtfLastNameInput = new JTextField("Nom", 10);
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
        jtfFirstNameInput = new JTextField("prénom", 10);
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
        GridBagConstraints gbcDateLabel = new GridBagConstraints();
        gbcDateLabel.gridx = 0;
        gbcDateLabel.gridy = 0;
        jpBirthdayPanel.add(jlBirthdayLabel, gbcDateLabel);
        Properties pStartProperties = new Properties();
        pStartProperties.put("text.today", "Aujourd'hui");
        pStartProperties.put("text.month", "Mois");
        pStartProperties.put("text.year", "Année");
        SqlDateModel sdmModel1 = new SqlDateModel();
        JDatePanelImpl jdpliStartDatePanel = new JDatePanelImpl(sdmModel1, pStartProperties);
        jdpliStartDatePanel.setPreferredSize(new Dimension(200, 200));
        jdpriStartDatePicker = new JDatePickerImpl(jdpliStartDatePanel, new DateLabelFormatter());
        GridBagLayout gblDate = new GridBagLayout();
        jpBirthdayPanel.setLayout(gblDate);
        GridBagConstraints gbcDate = new GridBagConstraints();
        gbcDate.gridx = 1;
        gbcDate.gridy = 0;
        jpBirthdayPanel.add(jdpriStartDatePicker, gbcDate);
        // Permet d'obtenir un retour lors de la sélection d'une date dans le calendrier
        jdpriStartDatePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(jdpriStartDatePicker.getJDateInstantPanel().getModel().getDay());
                System.out.println(jdpriStartDatePicker.getJDateInstantPanel().getModel().getMonth());
                System.out.println(jdpriStartDatePicker.getJDateInstantPanel().getModel().getYear());
            }
        });
        jpMainPanel.add(jpBirthdayPanel);

        // Ajout des champs utiles pour le numéro AVS
        JPanel jpAVS = new JPanel();
        JLabel jlAVS = new JLabel("Numéro AVS : ");
        jpAVS.add(jlAVS);
        jtfAVSInput = new JTextField("sAVS", 10);
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
        jtfAddress = new JTextField("adresse", 15);
        jpAddress.add(jtfAddress);
        jlAddressError.setFont(new Font("Serif", Font.BOLD, 32));
        jlAddressError.setForeground(Color.RED);
        jlAddressError.setHorizontalAlignment(JLabel.CENTER);
        jpAddress.add(jlAddressError);
        jpMainPanel.add(jpAddress);

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

        // Ajout des champs utiles pour la ville
        JPanel jpCity = new JPanel();
        JLabel jlCity = new JLabel("Ville : ");
        jpCity.add(jlCity);
        jtfCity = new JTextField("ville", 10);
        jpCity.add(jtfCity);
        jlCityError.setFont(new Font("Serif", Font.BOLD, 32));
        jlCityError.setForeground(Color.RED);
        jlCityError.setHorizontalAlignment(JLabel.CENTER);
        jpCity.add(jlCityError);
        jpMainPanel.add(jpCity);

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
        jtfEmail = new JTextField("e-mail", 10);
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
        jtfPhone = new JTextField("téléphone", 10);
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
        jtfSupervisor = new JTextField("responsable", 10);
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
        jcbStatus = new JComboBox();
        for(int i = 0; i < statut.size(); ++i){
            System.out.println(statut.get(i));
            jcbStatus.addItem(statut.get(i));
        }
        jpStatus.add(jcbStatus);
        jlStatusError.setFont(new Font("Serif", Font.BOLD, 32));
        jlStatusError.setForeground(Color.RED);
        jlStatusError.setHorizontalAlignment(JLabel.CENTER);
        jpStatus.add(jlStatusError);
        jpMainPanel.add(jpStatus);

        // Ajout des champs utiles pour le contrat
        JPanel jpContract = new JPanel();
        JLabel jlContract = new JLabel("Contrat : ");
        jpContract.add(jlContract);
        jcbContract = new JComboBox();
        for(int i = 0; i < contract.size(); ++i){
            System.out.println(contract.get(i));
            jcbContract.addItem(contract.get(i));
        }
        jpContract.add(jcbContract);
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
                System.out.println("ajout");
                sLastName = jtfLastNameInput.getText();
                sFirstName = jtfLastNameInput.getText();
                iDay = jdpriStartDatePicker.getJDateInstantPanel().getModel().getDay();
                iMonth = jdpriStartDatePicker.getJDateInstantPanel().getModel().getMonth();
                iYear = jdpriStartDatePicker.getJDateInstantPanel().getModel().getYear();
                sAVS = jtfAVSInput.getText();
                sEMail = jtfEmail.getText();
                sAddress = jtfAddress.getText();
                sNPA = jtfNPA.getText();
                sCity = jtfCity.getText();
                sCountry = jtfCountry.getText();
                sPhone = jtfPhone.getText();
                sSupervisor = jtfSupervisor.getText();
                sStatus = jcbStatus.getSelectedItem().toString();
                sContract = jcbContract.getSelectedItem().toString();

                Pays pays = new Pays();
                pays.setPays(sCountry);

                Ville ville = new Ville();
                ville.setVille(sCity);
                ville.setPays(pays);

                Adresse adresse = new Adresse();
                adresse.setAdresse(sAddress);
                adresse.setVille(ville);

                controller.checkPersonne(sLastName, sFirstName, iDay, iMonth, iYear, sAVS, sEMail, adresse, ville, pays, sPhone, sSupervisor, sStatus, sContract);
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
        jlCountryError.setVisible(false);
        jlEmailError.setVisible(false);
        jlPhoneError.setVisible(false);
        jlStatusError.setVisible(false);
        jlSupervisorError.setVisible(false);
        jlContractError.setVisible(false);
    }

    public void setFirstNameError(String error) {
        jlFirstNameError.setVisible(true);
    }

    public void setLastNameError(String error) {
        jlLastNameError.setVisible(true);
    }

    public void setBirthdayError(String error) {
        jlBirthdayError.setVisible(true);
    }

    public void setAVSError(String error) {
        jlAVSError.setVisible(true);
    }

    public void setEmailError(String error) {
        jlEmailError.setVisible(true);
    }

    public void setAddressError(String error) {
        jlAddressError.setVisible(true);
    }

    public void setCityError(String error) {
        jlCityError.setVisible(true);
    }

    public void setNPAError(String error) {
        jlNPAError.setVisible(true);
    }

    public void setCountryError(String error){
        jlCountryError.setVisible(true);
    }

    public void setPhoneError(String error) {
        jlPhoneError.setVisible(true);
    }

}
