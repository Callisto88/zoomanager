package View.Staff.StaffAddPanel;

import Controller.Staff.AddStaffController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
    private boolean dateOK = false;
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
    private int iSupervisor;
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
     * @param statut ArrayList contenant les différents statuts actuellement présent
     * @param contract ArrayList contenant les différents type de contrat actuellement présent
     * @param supervisor ArrayList contenant les différents responsables actuellement présent
     * @param country ArrayList contenant les différents pays actuellement présent
     */
    public AddStaff(AddStaffController asc, ArrayList<String> statut, ArrayList<String> contract, ArrayList<Personne> supervisor, ArrayList<Pays> country) {
        super("Ajout d'employés");
        controller = asc;
        jpMainPanel.setLayout(new GridBagLayout());

        this.supervisor = supervisor;
        this.country = country;

        System.out.println(supervisor.size());

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
        // Permet de vérifier qu'une personne à bien sélectionné une date.
        jdpriStartDatePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateOK = true;
            }
        });

        GridBagLayout gblDate = new GridBagLayout();
        jpBirthdayPanel.setLayout(gblDate);
        GridBagConstraints gbcDate = new GridBagConstraints();
        gbcDate.gridx = 1;
        gbcDate.gridy = 0;
        jpBirthdayPanel.add(jdpriStartDatePicker, gbcDate);

        gbcConstraint.gridy = 2;
        jpMainPanel.add(jpBirthdayPanel, gbcConstraint);

        // Ajout des champs utiles pour le numéro AVS
        JPanel jpAVS = new JPanel();
        JLabel jlAVS = new JLabel("Numéro AVS : ");
        jlAVS.setPreferredSize(dLabel);
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
        jpSupervisor.add(jlSupervisor);
        jcbSupervisor = new JComboBox();
        jcbSupervisor.addItem("");
        for(int i = 0; i < supervisor.size(); ++i){
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
        jpStatus.add(jlStatus);
        jcbStatus = new JComboBox();
        for(int i = 0; i < statut.size(); ++i){
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
        jpContract.add(jlContract);
        jcbContract = new JComboBox();
        for(int i = 0; i < contract.size(); ++i){
            jcbContract.addItem(contract.get(i));
        }
        jcbContract.setPreferredSize(dInput);
        jpContract.add(jcbContract);
        gbcConstraint.gridy = 12;
        jpMainPanel.add(jpContract, gbcConstraint);

        // Ajout du bouton d'ajout du personnel
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
                sCountry = country.get(jcbCountry.getSelectedIndex()).getPays();
                sPhone = jtfPhone.getText();
                if(jcbSupervisor.getSelectedIndex() != 0) {
                    iSupervisor = supervisor.get(jcbSupervisor.getSelectedIndex() - 1).getIdPersonne();
                }
                else{
                    iSupervisor = 0;
                }
                sStatus = jcbStatus.getSelectedItem().toString();
                sContract = jcbContract.getSelectedItem().toString();

                // Permet de fermer la fenêtre si tout s'est bien passé
                controller.checkPersonne(sLastName, sFirstName, iDay, iMonth, iYear, dateOK, sAVS, sEMail, sAddress, sNPA,
                                            sCity, sCountry, sPhone, iSupervisor, sStatus, sContract);
            }
        });
        configFrame(getJfFrame(), this);
    }

    /**
     * Méthoed permettant de réinitialiser les états d'erreur crée lors de mauvaises saisies
     */
    public void disableError() {
        jtfLastNameInput.setBackground(Color.WHITE);
        jtfLastNameInput.setToolTipText(null);
        jtfFirstNameInput.setBackground(Color.WHITE);
        jtfFirstNameInput.setToolTipText(null);
        jdpriStartDatePicker.setBackground(Color.WHITE);
        jdpriStartDatePicker.setToolTipText(null);
        jtfAVSInput.setBackground(Color.WHITE);
        jtfAVSInput.setToolTipText(null);
        jtfAddress.setBackground(Color.WHITE);
        jtfAddress.setToolTipText(null);
        jtfCity.setBackground(Color.WHITE);
        jtfCity.setToolTipText(null);
        jtfNPA.setBackground(Color.WHITE);
        jtfNPA.setToolTipText(null);
        jcbCountry.setBackground(Color.WHITE);
        jcbCountry.setToolTipText(null);
        jtfEmail.setBackground(Color.WHITE);
        jtfEmail.setToolTipText(null);
        jtfPhone.setBackground(Color.WHITE);
        jtfPhone.setToolTipText(null);
    }

    /**
     * Méthode permettant d'afficher un état d'erreur sur le champ de saisie du prénom
     * @param error message d'erreur indiquant ce qui est autorisé
     */
    public void setFirstNameError(String error) {
        jtfFirstNameInput.setToolTipText(error);
        jtfFirstNameInput.setBackground(Color.RED);
    }

    /**
     * Méthode permettant d'afficher un état d'erreur sur le champ de saisie du nom
     * @param error message d'erreur indiquant ce qui est autorisé
     */
    public void setLastNameError(String error) {
        jtfLastNameInput.setToolTipText(error);
        jtfLastNameInput.setBackground(Color.RED);
    }

    /**
     * Méthode permettant d'afficher un état d'erreur sur le champ de saisie de la date
     * @param error message d'erreur indiquant ce qui est autorisé
     */
    public void setBirthdayError(String error) {
        jdpriStartDatePicker.setToolTipText(error);
        jdpriStartDatePicker.getJFormattedTextField().setBackground(Color.RED);
    }

    /**
     * Méthode permettant d'afficher un état d'erreur sur le champ de saisie de l'AVS
     * @param error message d'erreur indiquant ce qui est autorisé
     */
    public void setAVSError(String error) {
        jtfAVSInput.setToolTipText(error);
        jtfAVSInput.setBackground(Color.RED);
    }

    /**
     * Méthode permettant d'afficher un état d'erreur sur le champ de saisie du mail
     * @param error message d'erreur indiquant ce qui est autorisé
     */
    public void setEmailError(String error) {
        jtfEmail.setToolTipText(error);
        jtfEmail.setBackground(Color.RED);
    }

    /**
     * Méthode permettant d'afficher un état d'erreur sur le champ de saisie de l'adresse
     * @param error message d'erreur indiquant ce qui est autorisé
     */
    public void setAddressError(String error) {
        jtfAddress.setToolTipText(error);
        jtfAddress.setBackground(Color.RED);
    }

    /**
     * Méthode permettant d'afficher un état d'erreur sur le champ de saisie de la ville
     * @param error message d'erreur indiquant ce qui est autorisé
     */
    public void setCityError(String error) {
        jtfCity.setToolTipText(error);
        jtfCity.setBackground(Color.RED);
    }

    /**
     * Méthode permettant d'afficher un état d'erreur sur le champ de saisie du npa
     * @param error message d'erreur indiquant ce qui est autorisé
     */
    public void setNPAError(String error) {
        jtfNPA.setToolTipText(error);
        jtfNPA.setBackground(Color.RED);
    }

    /** TODO: a checker en cas de levé d'exception!!
     * Méthode permettant d'afficher un état d'erreur sur le champ de saisie du pays
     * @param error message d'erreur indiquant ce qui est autorisé
     */
    public void setCountryError(String error){
        jcbCountry.setToolTipText(error);
        jcbCountry.setBackground(Color.RED);
    }

    /**
     * Méthode permettant d'afficher un état d'erreur sur le champ de saisie du télephone
     * @param error message d'erreur indiquant ce qui est autorisé
     */
    public void setPhoneError(String error) {
        jtfPhone.setToolTipText(error);
        jtfPhone.setBackground(Color.RED);
    }

    /**
     * Méthode permettant de fermer la fenêtre une fois que l'on a fini d'insérer du personnel
     */
    public void close(){
        this.getJfFrame().dispatchEvent(new WindowEvent(getJfFrame(), WindowEvent.WINDOW_CLOSING));
    }
}
