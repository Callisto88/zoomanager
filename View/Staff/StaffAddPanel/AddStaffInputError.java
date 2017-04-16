package View.Staff.StaffAddPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andre on 30.03.2017.
 */
public class AddStaffInputError extends JPanel {
    private JLabel lastNameError = new JLabel("*", JLabel.CENTER);
    private JLabel firstNameError = new JLabel("*", JLabel.CENTER);
    private JLabel birthdayError = new JLabel("*", JLabel.CENTER);
    private JLabel avsError = new JLabel("*", JLabel.CENTER);
    private JLabel emailError = new JLabel("*", JLabel.CENTER);
    private JLabel addressError = new JLabel("*", JLabel.CENTER);
    private JLabel cityError = new JLabel("*", JLabel.CENTER);
    private JLabel npaError = new JLabel("*", JLabel.CENTER);
    private JLabel phoneError = new JLabel("*", JLabel.CENTER);
    private JLabel responsableError = new JLabel("*", JLabel.CENTER);

    public AddStaffInputError() {
        setLayout(new GridLayout(12, 1, 10, 10));
        setMinimumSize(new Dimension(50, 500));
        //this.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Sous ensemble de gauche contenant les label pour les employées

        // Nom
        lastNameError.setFont(new Font("Serif", Font.BOLD, 32));
        lastNameError.setForeground(Color.RED);
        lastNameError.setHorizontalAlignment(JLabel.CENTER);
        this.add(lastNameError);

        // Prénom
        firstNameError.setFont(new Font("Serif", Font.BOLD, 32));
        firstNameError.setForeground(Color.RED);
        firstNameError.setHorizontalAlignment(JLabel.CENTER);
        this.add(firstNameError);

        // Date de naissance
        birthdayError.setFont(new Font("Serif", Font.BOLD, 32));
        birthdayError.setForeground(Color.RED);
        birthdayError.setHorizontalAlignment(JLabel.CENTER);
        this.add(birthdayError);

        // AVS
        avsError.setFont(new Font("Serif", Font.BOLD, 32));
        avsError.setForeground(Color.RED);
        avsError.setHorizontalAlignment(JLabel.CENTER);
        this.add(avsError);

        // E-mail
        emailError.setFont(new Font("Serif", Font.BOLD, 32));
        emailError.setForeground(Color.RED);
        emailError.setHorizontalAlignment(JLabel.CENTER);
        this.add(emailError);

        // Adresse
        addressError.setFont(new Font("Serif", Font.BOLD, 32));
        addressError.setForeground(Color.RED);
        addressError.setHorizontalAlignment(JLabel.CENTER);
        this.add(addressError);

        // Ville
        cityError.setFont(new Font("Serif", Font.BOLD, 32));
        cityError.setForeground(Color.RED);
        cityError.setHorizontalAlignment(JLabel.CENTER);
        this.add(cityError);

        // NPA
        npaError.setFont(new Font("Serif", Font.BOLD, 32));
        npaError.setForeground(Color.RED);
        npaError.setHorizontalAlignment(JLabel.CENTER);
        this.add(npaError);

        // Téléphone
        phoneError.setFont(new Font("Serif", Font.BOLD, 32));
        phoneError.setForeground(Color.RED);
        phoneError.setHorizontalAlignment(JLabel.CENTER);
        this.add(phoneError);

        // Responsable
        responsableError.setFont(new Font("Serif", Font.BOLD, 32));
        responsableError.setForeground(Color.RED);
        responsableError.setHorizontalAlignment(JLabel.CENTER);
        this.add(responsableError);

        disableError();

        this.setBackground(Color.green);
        this.setPreferredSize(new Dimension(300, 700));
    }

    public void disableError() {
        firstNameError.setVisible(false);
        lastNameError.setVisible(false);
        birthdayError.setVisible(false);
        avsError.setVisible(false);
        emailError.setVisible(false);
        addressError.setVisible(false);
        cityError.setVisible(false);
        npaError.setVisible(false);
        phoneError.setVisible(false);
        responsableError.setVisible(false);
    }

    public void setLastNameError(String error) {
        lastNameError.setVisible(true);
        lastNameError.setToolTipText(error);
    }

    public void setFirstNameError(String error) {
        firstNameError.setVisible(true);
        firstNameError.setToolTipText(error);
    }

    public void setBirthdayError(String error) {
        birthdayError.setVisible(true);
        birthdayError.setToolTipText(error);
    }

    public void setAVSError(String error) {
        avsError.setVisible(true);
        avsError.setToolTipText(error);
    }

    public void setEMailError(String error) {
        emailError.setVisible(true);
        emailError.setToolTipText(error);
    }

    public void setAddressError(String error) {
        addressError.setVisible(true);
        addressError.setToolTipText(error);
    }

    public void setCityError(String error) {
        cityError.setVisible(true);
        cityError.setToolTipText(error);
    }

    public void setNPAError(String error) {
        npaError.setVisible(true);
        npaError.setToolTipText(error);
    }

    public void setPhoneError(String error) {
        phoneError.setVisible(true);
        phoneError.setToolTipText(error);
    }

    public void setResponsableError(String error) {
        responsableError.setVisible(true);
        responsableError.setToolTipText(error);
    }

}
