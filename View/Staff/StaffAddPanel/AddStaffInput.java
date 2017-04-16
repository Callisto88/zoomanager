package View.Staff.StaffAddPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andre on 17.03.2017.
 * Sous-fenêtre de la fenêtre d'ajout contenant les champs à remplir.
 */
public class AddStaffInput extends JPanel {
    private JTextField lastNameInput;
    private JTextField firstNameInput;
    private JTextField avsInput;
    private JTextField emailInput;
    private JTextField addressInput;
    private JTextField cityInput;
    private JTextField npaInput;
    private JTextField phoneInput;
    private JTextField statutInput;
    private JTextField typeInput;
    private JTextField responsableInput;

    /**
     * Constructeur de la sous-fenêtre d'ajout contenant les champs à remplir.
     */
    public AddStaffInput() {
        setLayout(new GridLayout(12, 1, 10, 10));
        setMinimumSize(new Dimension(200,500));

        // Nom
        JPanel lastName = new JPanel();
        lastNameInput = new JTextField("last", 7);
        lastNameInput.setToolTipText("caractères accepté [A-Z], [a-z], [0-9], [' -]");
        lastName.add(lastNameInput);
        //this.add(lastName);
        this.add(lastNameInput);

        // Prénom
        JPanel firstName = new JPanel();
        firstNameInput = new JTextField("first", 7);
        firstNameInput.setToolTipText("caractères accepté [A-Z], [a-z], [0-9], [' -]");
        firstName.add(firstNameInput);
        //this.add(firstName);
        this.add(firstNameInput);

        // Date de naissance
        JPanel birthday = new JPanel();
        JTextField birthdayInput = new JTextField("birthday", 7);
        birthday.add(birthdayInput);
        //this.add(birthday);
        this.add(birthdayInput);
        // a tester d'insérer un calendrier pour choisir la date

        // AVS
        JPanel avs = new JPanel();
        avsInput = new JTextField("avs", 7);
        avsInput.setToolTipText("caractères accepté [0-9] 13 chiffres");
        avs.add(avsInput);
        //this.add(avs);
        this.add(avsInput);

        // E-mail
        JPanel email = new JPanel();
        emailInput = new JTextField("email", 7);
        emailInput.setToolTipText("format toto@domaine.extension");
        email.add(emailInput);
        //this.add(email);
        this.add(emailInput);

        // Adresse
        JPanel address = new JPanel();
        addressInput = new JTextField("address", 7);
        addressInput.setToolTipText("caractères accepté [A-Z], [a-z], [0-9], [' -]");
        address.add(addressInput);
        //this.add(address);
        this.add(addressInput);

        // Ville
        JPanel city = new JPanel();
        cityInput = new JTextField("city", 7);
        cityInput.setToolTipText("caractères accepté [A-Z], [a-z], [' -]");
        city.add(cityInput);
        //this.add(city);
        this.add(cityInput);

        // NPA
        JPanel npa = new JPanel();
        npaInput = new JTextField("npa", 7);
        npaInput.setToolTipText("caractères accepté [0-9] 4 chiffres");
        npa.add(npaInput);
        //this.add(npa);
        this.add(npaInput);

        // Téléphone
        JPanel phone = new JPanel();
        phoneInput = new JTextField("phone", 7);
        phoneInput.setToolTipText("caractères accepté [0-9], 10 chiffres");
        phone.add(phoneInput);
        //this.add(phone);
        this.add(phoneInput);

        // Responsable
        responsableInput = new JTextField("responsable", 7);
        responsableInput.setToolTipText("Responsable");
        //this.add(type);
        this.add(responsableInput);

        // Statut  aide, employee, interne, externe, responsable
        JPanel statut = new JPanel();
        statutInput = new JTextField("statut", 7);
        statutInput.setToolTipText("statut matrimoniale");
        statut.add(statutInput);
        //this.add(statut);
        this.add(statutInput);

        // Type   mandat, cdi, cdd, stage
        JPanel type = new JPanel();
        typeInput = new JTextField("type", 7);
        typeInput.setToolTipText("type de contrat");
        type.add(typeInput);
        //this.add(type);
        this.add(typeInput);

        /* Arrivé
        * La date d'arrivé est ajouté automatiquement par le serveut ou la DB avec la date actuelle */

        this.setBackground(Color.YELLOW);
    }

    public String getInputLastName() {
        //System.out.println(lastNameInput.getText());
        return lastNameInput.getText();
    }

    public String getInputFirstName() {
        //System.out.println(firstNameInput.getText());
        return firstNameInput.getText();
    }

    public String getInputAVS() {
        //System.out.println(avsInput.getText());
        return avsInput.getText();
    }

    public String getEMailInput() {
        //System.out.println(emailInput.getText());
        return emailInput.getText();
    }

    public String getInputAddress() {
        //System.out.println(addressInput.getText());
        return addressInput.getText();
    }

    public String getInputCity() {
        //System.out.println(cityInput.getText());
        return cityInput.getText();
    }

    public String getInputNPA() {
        //System.out.println(npaInput.getText());
        return npaInput.getText();
    }

    public String getInputPhone() {
        //System.out.println(phoneInput.getText());
        return phoneInput.getText();
    }

    public String getInputStatut() {
        //System.out.println(statutInput.getText());
        return statutInput.getText();
    }

    public String getInputTypeContrat() {
        //System.out.println(typeInput.getText());
        return typeInput.getText();
    }
}
