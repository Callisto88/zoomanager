package View.Staff.StaffAddPanel;

import Controller.Staff.AddStaffController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;

import Model.Pays;
import Model.Personne;
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
    private ArrayList<Personne> supervisor = null;
    private ArrayList<Pays> country = null;

    private Dimension dLabel = new Dimension(130, 30);
    private Dimension dInput = new Dimension(150, 30);

    private GridBagConstraints gbcConstraint = new GridBagConstraints();

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
    private JComboBox jcbCountry;
    private JTextField jtfPhone;
    private JComboBox jcbSupervisor;
    private JComboBox jcbStatus;
    private JComboBox jcbContract;

    /**
     * Constructeur de la fenêtre principale d'ajout de personnel.
     *
     * @param asc controlleur de la fenêtre pour permettre de lui remonter les information utiles.
     */
    public AddStaff(AddStaffController asc, ArrayList<String> statut, ArrayList<String> contract, ArrayList<Personne> supervisor, ArrayList<Pays> country) {
        super("Ajout");
        controller = asc;
        jpMainPanel.setLayout(new GridBagLayout());

        this.supervisor = supervisor;
        this.country = country;

        gbcConstraint.gridx = 0;
        gbcConstraint.gridy = 0;
        gbcConstraint.insets = new Insets(10,5,10,5);
        gbcConstraint.anchor = GridBagConstraints.WEST;

        // Ajout des champs utiles pour le nom
        JPanel jpLastNamePanel = new JPanel();
        JLabel jlLastNameLabel = new JLabel("Nom : ");
        jlLastNameLabel.setPreferredSize(dLabel);
        jpLastNamePanel.add(jlLastNameLabel, JPanel.LEFT_ALIGNMENT);
        jtfLastNameInput = new JTextField("Nom");
        jtfLastNameInput.setToolTipText("caractères accepté [A-Z], [a-z], [0-9], [' -]");
        jtfLastNameInput.setPreferredSize(dInput);
        jpLastNamePanel.add(jtfLastNameInput, JPanel.CENTER_ALIGNMENT);
        jpMainPanel.add(jpLastNamePanel, gbcConstraint);

        // Ajout des champs utiles pour le prénom
        JPanel jpFirstNamePanel = new JPanel();
        JLabel lastNameLabel = new JLabel("Prénom : ");
        lastNameLabel.setPreferredSize(dLabel);
        //setLabelConfig(lastNameLabel);
        jpFirstNamePanel.add(lastNameLabel,JPanel.LEFT_ALIGNMENT);
        jtfFirstNameInput = new JTextField("prénom");
        jtfFirstNameInput.setToolTipText("caractères accepté [A-Z], [a-z], [0-9], [' -]");
        jtfFirstNameInput.setPreferredSize(dInput);
        jpFirstNamePanel.add(jtfFirstNameInput, JPanel.CENTER_ALIGNMENT);
        gbcConstraint.gridy = 1;
        jpMainPanel.add(jpFirstNamePanel, gbcConstraint);

        // Ajout des champs utiles pour la date de naissance
        JPanel jpBirthdayPanel = new JPanel();
        JLabel jlBirthdayLabel = new JLabel("Date de Naissance : ");
        jlBirthdayLabel.setPreferredSize(dLabel);
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
        gbcConstraint.gridy = 2;
        jpMainPanel.add(jpBirthdayPanel, gbcConstraint);

        // Ajout des champs utiles pour le numéro AVS
        JPanel jpAVS = new JPanel();
        JLabel jlAVS = new JLabel("Numéro AVS : ");
        jlAVS.setPreferredSize(dLabel);
        //setLabelConfig(jlAVS);
        jpAVS.add(jlAVS);
        jtfAVSInput = new JTextField("sAVS");
        jtfAVSInput.setPreferredSize(dInput);
        jpAVS.add(jtfAVSInput);
        gbcConstraint.gridy = 3;
        jpMainPanel.add(jpAVS, gbcConstraint);

        // Ajout des champs utiles pour l'adresse
        JPanel jpAddress = new JPanel();
        JLabel jlAddress = new JLabel("Adresse : ");
        jlAddress.setPreferredSize(dLabel);
        //setLabelConfig(jlAddress);
        jpAddress.add(jlAddress);
        jtfAddress = new JTextField("adresse");
        jtfAddress.setPreferredSize(dInput);
        jpAddress.add(jtfAddress);
        gbcConstraint.gridy = 4;
        jpMainPanel.add(jpAddress, gbcConstraint);

        // Ajout des champs utiles pour le NPA
        JPanel jpNPA = new JPanel();
        JLabel jlNPA = new JLabel("NPA : ");
        jlNPA.setPreferredSize(dLabel);
        //setLabelConfig(jlNPA);
        jpNPA.add(jlNPA);
        jtfNPA = new JTextField("npa");
        jtfNPA.setPreferredSize(dInput);
        jpNPA.add(jtfNPA);
        gbcConstraint.gridy = 5;
        jpMainPanel.add(jpNPA, gbcConstraint);

        // Ajout des champs utiles pour la ville
        JPanel jpCity = new JPanel();
        JLabel jlCity = new JLabel("Ville : ");
        jlCity.setPreferredSize(dLabel);
        //setLabelConfig(jlCity);
        jpCity.add(jlCity);
        jtfCity = new JTextField("ville");
        jtfCity.setPreferredSize(dInput);
        jpCity.add(jtfCity);
        gbcConstraint.gridy = 6;
        jpMainPanel.add(jpCity, gbcConstraint);

        // Ajout des champs utiles pour le Pays
        JPanel jpCountry = new JPanel();
        JLabel jlCountry = new JLabel("Pays : ");
        jlCountry.setPreferredSize(dLabel);
        //setLabelConfig(jlCountry);
        jpCountry.add(jlCountry);
        jcbCountry = new JComboBox();
        for(int i = 0; i < country.size(); ++i){
            jcbCountry.addItem(country.get(i).getPays());
        }
        jcbCountry.setPreferredSize(dInput);
        jpCountry.add(jcbCountry);
        gbcConstraint.gridy = 7;
        jpMainPanel.add(jpCountry, gbcConstraint);

        // Ajout des champs utiles pour l'e-mail
        JPanel jpEmail = new JPanel();
        JLabel jlEmail = new JLabel("E-mail : ");
        jlEmail.setPreferredSize(dLabel);
        //setLabelConfig(jlEmail);
        jpEmail.add(jlEmail);
        jtfEmail = new JTextField("e-mail");
        jtfEmail.setPreferredSize(dInput);
        jpEmail.add(jtfEmail);
        gbcConstraint.gridy = 8;
        jpMainPanel.add(jpEmail, gbcConstraint);

        // Ajout des champs utiles pour le télephone
        JPanel jpPhone = new JPanel();
        JLabel jlPhone = new JLabel("Téléphone : ");
        jlPhone.setPreferredSize(dLabel);
        //setLabelConfig(jlPhone);
        jpPhone.add(jlPhone);
        jtfPhone = new JTextField("téléphone");
        jtfPhone.setPreferredSize(dInput);
        jpPhone.add(jtfPhone);
        gbcConstraint.gridy = 9;
        jpMainPanel.add(jpPhone, gbcConstraint);

        // Ajout des champs utiles pour le responsable
        JPanel jpSupervisor = new JPanel();
        JLabel jlSupervisor = new JLabel("Responsable : ");
        jlSupervisor.setPreferredSize(dLabel);
        //setLabelConfig(jlSupervisor);
        jpSupervisor.add(jlSupervisor);
        jcbSupervisor = new JComboBox();
        for(int i = 0; i < supervisor.size(); ++i){
            System.out.println(supervisor.get(i).getNom());
            jcbSupervisor.addItem(supervisor.get(i).getPrenom() + " " + supervisor.get(i).getNom());
        }
        jcbSupervisor.setPreferredSize(dInput);
        jpSupervisor.add(jcbSupervisor);
        gbcConstraint.gridy = 10;
        jpMainPanel.add(jpSupervisor, gbcConstraint);

        // Ajout des champs utiles pour le statut
        JPanel jpStatus = new JPanel();
        JLabel jlStatus = new JLabel("Status : ");
        jlStatus.setPreferredSize(dLabel);
        //setLabelConfig(jlStatus);
        jpStatus.add(jlStatus);
        jcbStatus = new JComboBox();
        for(int i = 0; i < statut.size(); ++i){
            System.out.println(statut.get(i));
            jcbStatus.addItem(statut.get(i));
        }
        jcbStatus.setPreferredSize(dInput);
        jpStatus.add(jcbStatus);
        gbcConstraint.gridy = 11;
        jpMainPanel.add(jpStatus, gbcConstraint);

        // Ajout des champs utiles pour le contrat
        JPanel jpContract = new JPanel();
        JLabel jlContract = new JLabel("Contrat : ");
        jlContract.setPreferredSize(dLabel);
        //setLabelConfig(jlContract);
        jpContract.add(jlContract);
        jcbContract = new JComboBox();
        for(int i = 0; i < contract.size(); ++i){
            System.out.println(contract.get(i));
            jcbContract.addItem(contract.get(i));
        }
        jcbContract.setPreferredSize(dInput);
        jpContract.add(jcbContract);
        gbcConstraint.gridy = 12;
        jpMainPanel.add(jpContract, gbcConstraint);

        JButton add = new JButton("Ajouter");
        setButtonConfig(add);
        gbcConstraint.gridy = 13;
        gbcConstraint.anchor = GridBagConstraints.CENTER;
        jpMainPanel.add(add, gbcConstraint);

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
                sCountry = jcbCountry.getSelectedItem().toString();
                sPhone = jtfPhone.getText();
                sSupervisor = jcbSupervisor.getSelectedItem().toString();
                sStatus = jcbStatus.getSelectedItem().toString();
                sContract = jcbContract.getSelectedItem().toString();

                controller.checkPersonne(sLastName, sFirstName, iDay, iMonth, iYear, sAVS, sEMail, sAddress, sNPA, sCity, sCountry, sPhone, sSupervisor, sStatus, sContract);
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
        jdpriStartDatePicker.setBackground(Color.WHITE);
        jtfAVSInput.setBackground(Color.WHITE);
        jtfAddress.setBackground(Color.WHITE);
        jtfCity.setBackground(Color.WHITE);
        jtfNPA.setBackground(Color.WHITE);
        jcbCountry.setBackground(Color.WHITE);
        jtfEmail.setBackground(Color.WHITE);
        jtfPhone.setBackground(Color.WHITE);
    }

    public void setFirstNameError(String error) {
        jtfFirstNameInput.setToolTipText(error);
        jtfFirstNameInput.setBackground(Color.RED);
    }

    public void setLastNameError(String error) {
        jtfLastNameInput.setToolTipText(error);
        jtfLastNameInput.setBackground(Color.RED);
    }

    public void setBirthdayError(String error) {
        jdpriStartDatePicker.setToolTipText(error);
        jdpriStartDatePicker.setBackground(Color.RED);
    }

    public void setAVSError(String error) {
        jtfAVSInput.setToolTipText(error);
        jtfAVSInput.setBackground(Color.RED);
    }

    public void setEmailError(String error) {
        jtfEmail.setToolTipText(error);
        jtfEmail.setBackground(Color.RED);
    }

    public void setAddressError(String error) {
        jtfAddress.setToolTipText(error);
        jtfAddress.setBackground(Color.RED);
    }

    public void setCityError(String error) {
        jtfCity.setToolTipText(error);
        jtfCity.setBackground(Color.RED);
    }

    public void setNPAError(String error) {
        jtfNPA.setToolTipText(error);
        jtfNPA.setBackground(Color.RED);
    }

    public void setCountryError(String error){
        jcbCountry.setToolTipText(error);
        jcbCountry.setBackground(Color.RED);
    }

    public void setPhoneError(String error) {
        jtfPhone.setToolTipText(error);
        jtfPhone.setBackground(Color.RED);
    }

    private void setLabelConfig(JLabel jlLabel){
        jlLabel.setPreferredSize(new Dimension(150 - jlLabel.getText().length(),30));
    }
}
