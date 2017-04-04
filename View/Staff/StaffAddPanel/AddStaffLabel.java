package View.Staff.StaffAddPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andre on 12.03.2017.
 * Sous-fenêtre de la fenêtre d'ajout contenant les différents labels.
 */
public class AddStaffLabel extends JPanel {

    /**
     * Constructeur de la sous fenêtre d'ajout contenant les labels.
     */
    public AddStaffLabel() {
        setLayout(new GridLayout(12, 1, 10, 10));
        //this.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Sous ensemble de gauche contenant les label pour les employées

        // Nom
        JPanel detailFirstName = new JPanel();
        JLabel firstNameLabel = new JLabel("Nom : ");
        detailFirstName.add(firstNameLabel);
        //this.add(detailFirstName);
        this.add(firstNameLabel);

        // Prénom
        JPanel detailLastName = new JPanel();
        JLabel lastNameLabel = new JLabel("Prénom : ");
        detailLastName.add(lastNameLabel);
        //this.add(detailLastName);
        this.add(lastNameLabel);

        // Date de naissance
        JPanel detailBirthday = new JPanel();
        JLabel birthdayLabel = new JLabel("Date de Naissance : ");
        detailBirthday.add(birthdayLabel);
        //this.add(detailBirthday);
        this.add(birthdayLabel);

        // AVS
        JLabel avsLabel = new JLabel("Numéro AVS : ");
        this.add(avsLabel);

        // E-mail
        JLabel emailLabel = new JLabel("Adresse mail : ");
        this.add(emailLabel);

        // Adresse
        JPanel detailAddress = new JPanel();
        JLabel addressLabel = new JLabel("Adresse : ");
        detailAddress.add(addressLabel);
        //this.add(detailAddress);
        this.add(addressLabel);

        // Ville
        JPanel detailCity = new JPanel();
        JLabel cityLabel = new JLabel("Ville : ");
        detailCity.add(cityLabel);
        //this.add(detailCity);
        this.add(cityLabel);

        // NPA
        JPanel detailNPA = new JPanel();
        JLabel npaLabel = new JLabel("NPA : ");
        detailNPA.add(npaLabel);
        //this.add(detailNPA);
        this.add(npaLabel);

        // Téléphone
        JPanel detailPhone = new JPanel();
        JLabel phoneLabel = new JLabel("Numéro de Téléphone : ");
        detailPhone.add(phoneLabel);
        //this.add(detailPhone);
        this.add(phoneLabel);

        // Salaire
        JPanel detailSalaire = new JPanel();
        JLabel salaireLabel = new JLabel("Salaire annuel : ");
        detailSalaire.add(salaireLabel);
        this.add(salaireLabel);

        // Statut
        JLabel statutLabel = new JLabel("Statut : ");
        this.add(statutLabel);

        // Type
        JLabel typeLabel = new JLabel("Type de contrat : ");
        this.add(typeLabel);
    }
}
