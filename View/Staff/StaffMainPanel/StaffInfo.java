package View.Staff.StaffMainPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andre on 10.03.2017.
 * Sous-fenêtre de la fenêtre principale du personnel contenant les informations du personnel
 */
public class StaffInfo extends JPanel {

    /**
     * Constructeur de la sous-fenêtre qui récupère les informations du personnel
     */
    public StaffInfo() {

        // Sous ensemble de gauche contenant les détails sur les employées
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //setLayout(new GridLayout(13,1,10,10));

        // Requête pour récuperer les détails d'une personne
        // Nom
        JPanel detailFirstName = new JPanel();
        JLabel firstName = new JLabel("Marley", JLabel.LEFT);
        detailFirstName.add(firstName, JPanel.LEFT_ALIGNMENT);
        this.add(detailFirstName);
        //this.add(firstName);

        // Prénom
        JPanel detailLastName = new JPanel();
        JLabel lastName = new JLabel("Bob", JLabel.LEFT);
        detailLastName.add(lastName, JPanel.LEFT_ALIGNMENT);
        this.add(detailLastName);
        //this.add(lastName);

        // Age
        JPanel detailBirthday = new JPanel();
        JLabel birthday = new JLabel("09/02/1983", JLabel.LEFT);
        detailBirthday.add(birthday, JPanel.LEFT_ALIGNMENT);
        this.add(detailBirthday);
        //this.add(birthday);

        // AVS
        JPanel detailAVS = new JPanel();
        JLabel avs = new JLabel("1234567890", JLabel.LEFT);
        detailAVS.add(avs, JPanel.LEFT_ALIGNMENT);
        this.add(detailAVS);
        //this.add(avs);

        // email
        JPanel detailMail = new JPanel();
        JLabel mail = new JLabel("Mail@toto.ch", JLabel.LEFT);
        detailMail.add(mail, JPanel.LEFT_ALIGNMENT);
        this.add(detailMail, JPanel.LEFT_ALIGNMENT);
        //this.add(mail);

        // Arrivé
        JPanel detailBirthdayIncorporation = new JPanel();
        JLabel birthdayIncorporation = new JLabel("01/03/2017", JLabel.LEFT);
        detailBirthdayIncorporation.add(birthdayIncorporation, JPanel.LEFT_ALIGNMENT);
        this.add(detailBirthdayIncorporation);
        //this.add(birthdayIncorporation);

        // Adresse
        JPanel detailAddress = new JPanel();
        JLabel address = new JLabel("Avenue de Tourbillon 21", JLabel.LEFT);
        detailAddress.add(address, JPanel.LEFT_ALIGNMENT);
        this.add(detailAddress);
        //this.add(address);

        // Ville
        JPanel detailCity = new JPanel();
        JLabel city = new JLabel("Sion", JLabel.LEFT);
        detailCity.add(city, JPanel.LEFT_ALIGNMENT);
        this.add(detailCity);
        //this.add(city);

        // NPA
        JPanel detailNPA = new JPanel();
        JLabel npa = new JLabel("1950", JLabel.LEFT);
        detailNPA.add(npa, JPanel.LEFT_ALIGNMENT);
        this.add(detailNPA);
        //this.add(npa);

        // Téléphone
        JPanel detailPhone = new JPanel();
        JLabel phone = new JLabel("079 123 45 67", JLabel.LEFT);
        detailPhone.add(phone, JPanel.LEFT_ALIGNMENT);
        this.add(detailPhone);
        //this.add(phone);

        // Salaire
        JPanel detailSalary = new JPanel();
        JLabel salaryLabel = new JLabel("40000", JLabel.LEFT);
        detailSalary.add(salaryLabel, JPanel.LEFT_ALIGNMENT);
        this.add(detailSalary);
        //this.add(salaryLabel);

        //Statut
        JPanel detailStatut = new JPanel();
        JLabel statutLabel = new JLabel("Marié", JLabel.LEFT);
        detailStatut.add(statutLabel, JPanel.LEFT_ALIGNMENT);
        this.add(detailStatut);
        //this.add(statutLabel);

        // Type Contrat
        JPanel detailContrat = new JPanel();
        JLabel contratLabel = new JLabel("horaire", JLabel.LEFT);
        detailContrat.add(contratLabel, JPanel.LEFT_ALIGNMENT);
        this.add(detailContrat);
        //this.add(contratLabel);

        // Bouton de modification
        JButton modify = new JButton("Modification");
        //this.add(modify);
    }
}
