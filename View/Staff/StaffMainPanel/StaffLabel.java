package View.Staff.StaffMainPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andre on 12.03.2017.
 * Sous-fenêtre de la fenêtre principale du personnel contenant les labels
 */
public class StaffLabel extends JPanel {

    /**
     * Constructeur de la sous-fenêtre qui contient les labels des employées
     */
    public StaffLabel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //setLayout(new GridLayout(13,1,10,10));
        this.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Sous ensemble de gauche contenant les label pour les employées

        // Requête pour récuperer les détails d'une personne
        // Nom
        JPanel detailFirstName = new JPanel();
        JLabel firstNameLabel = new JLabel("Nom : ", JLabel.LEFT);
        detailFirstName.add(firstNameLabel, JPanel.LEFT_ALIGNMENT);
        this.add(detailFirstName);
        //this.add(firstNameLabel);

        // Prénom
        JPanel detailLastName = new JPanel();
        JLabel lastNameLabel = new JLabel("Prénom : ", JLabel.LEFT);
        detailLastName.add(lastNameLabel, JPanel.LEFT_ALIGNMENT);
        this.add(detailLastName, JPanel.RIGHT_ALIGNMENT);
        //this.add(lastNameLabel);

        // Age
        JPanel detailBirthday = new JPanel();
        JLabel birthdayLabel = new JLabel("Date de Naissance : ", JLabel.LEFT);
        detailBirthday.add(birthdayLabel, JPanel.LEFT_ALIGNMENT);
        this.add(detailBirthday);
        //this.add(birthdayLabel);

        // AVS
        JPanel detailAVS = new JPanel();
        JLabel avs = new JLabel("Numéro AVS : ", JLabel.LEFT);
        detailAVS.add(avs, JPanel.LEFT_ALIGNMENT);
        this.add(detailAVS);
        // /this.add(avs);

        // email
        JPanel detailMail = new JPanel();
        JLabel mail = new JLabel("Mail : ", JLabel.LEFT);
        detailMail.add(mail, JPanel.LEFT_ALIGNMENT);
        this.add(detailMail);
        //this.add(mail);

        // Arrivé
        JPanel detailBirthdayIncorporation = new JPanel();
        JLabel birthdayIncorporationLabel = new JLabel("Date d'arrivé : ", JLabel.LEFT);
        detailBirthdayIncorporation.add(birthdayIncorporationLabel, JPanel.LEFT_ALIGNMENT);
        this.add(detailBirthdayIncorporation);
        //this.add(birthdayIncorporationLabel);

        // Adresse
        JPanel detailAddress = new JPanel();
        JLabel addressLabel = new JLabel("Adresse : ", JLabel.LEFT);
        detailAddress.add(addressLabel, JPanel.LEFT_ALIGNMENT);
        this.add(detailAddress);
        //this.add(addressLabel);

        // Ville
        JPanel detailCity = new JPanel();
        JLabel cityLabel = new JLabel("Ville : ", JLabel.LEFT);
        detailCity.add(cityLabel, JPanel.LEFT_ALIGNMENT);
        this.add(detailCity);
        //this.add(cityLabel);

        // NPA
        JPanel detailNPA = new JPanel();
        JLabel npaLabel = new JLabel("NPA : ", JLabel.LEFT);
        detailNPA.add(npaLabel, JPanel.LEFT_ALIGNMENT);
        this.add(detailNPA);
        //this.add(npaLabel);

        // Téléphone
        JPanel detailPhone = new JPanel();
        JLabel phoneLabel = new JLabel("Numéro de Téléphone : ", JLabel.LEFT);
        detailPhone.add(phoneLabel, JPanel.LEFT_ALIGNMENT);
        this.add(detailPhone);
        //this.add(phoneLabel);

        // Salaire
        JPanel detailSalary = new JPanel();
        JLabel salaryLabel = new JLabel("Salaire : ", JLabel.LEFT);
        detailSalary.add(salaryLabel, JPanel.LEFT_ALIGNMENT);
        this.add(detailSalary);
        //this.add(salaryLabel);

        //Statut
        JPanel detailStatut = new JPanel();
        JLabel statutLabel = new JLabel("Statut : ", JLabel.LEFT);
        detailStatut.add(statutLabel, JPanel.LEFT_ALIGNMENT);
        this.add(detailStatut);
        //this.add(statutLabel);

        // Type Contrat
        JPanel detailContrat = new JPanel();
        JLabel contratLabel = new JLabel("Type de Contrat : ", JLabel.LEFT);
        detailContrat.add(contratLabel, JPanel.LEFT_ALIGNMENT);
        this.add(detailContrat);
        //this.add(contratLabel);
    }
}
