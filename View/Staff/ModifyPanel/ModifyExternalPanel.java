package View.Staff.ModifyPanel;

import Model.ExceptionDataBase;
import Model.Intervenant;
import View.GenericWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Bureau on 23.04.2017.
 */
public class ModifyExternalPanel extends GenericWindow {
    private JComboBox boxChoiceLabel = null;
    private Intervenant external = null;

    // Champs de saisie pour la modification
    private JTextField jtfLastNameInput;
    private JTextField jtfFirstNameInput;
    private JTextField jtfCompagnyInput;
    private JTextField jtfEmail;
    private JTextField jtfAddress;
    private JTextField jtfCity;
    private JTextField jtfNPA;
    private JTextField jtfCountry;
    private JTextField jtfPhone;

    public ModifyExternalPanel(Intervenant external){
        super("Modification Intervenant");
        this.external = external;
        jpMainPanel.setLayout(new GridLayout(8,1));
        /**
         * Liste déroulante pour séléctionner les champs que l'on souhaite modifier
         */
        JPanel modification = new JPanel();
        boxChoiceLabel = new JComboBox();
        boxChoiceLabel.addItem("Tous les champs");
        boxChoiceLabel.addItem("Nom");
        boxChoiceLabel.addItem("Prénom");
        boxChoiceLabel.addItem("Adresse E-Mail");
        boxChoiceLabel.addItem("Adresse");
        boxChoiceLabel.addItem("Téléphone");
        modification.add(boxChoiceLabel);
        jpMainPanel.add(modification);
        //jpMainPanel.add(boxChoiceLabel);

        /**
         * Bouton pour demander l'ajout de champ à modifier
         */
        JPanel modifyLabel = new JPanel();
        JButton newLabel = new JButton("Ajouter un nouveau champ");
        modifyLabel.add(newLabel);
        jpMainPanel.add(modifyLabel);

        /**
         * Permet d'ajouter un champ en plus lors de la séléction, ou directement tous les champs
         */
        newLabel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(boxChoiceLabel.getSelectedItem().equals("Nom")){
                    addLastName();
                }

                if(boxChoiceLabel.getSelectedItem().equals("Prénom")){
                    addFirstName();
                }
                if(boxChoiceLabel.getSelectedItem().equals("Adresse E-Mail")){
                    addEMail();
                }

                if(boxChoiceLabel.getSelectedItem().equals("Adresse")){
                    addAddress();
                }
                if(boxChoiceLabel.getSelectedItem().equals("Téléphone")){
                    addPhone();
                }

                if(boxChoiceLabel.getSelectedItem().equals("Tous les champs")){
                    addLastName();
                    addFirstName();
                    addEMail();
                    addAddress();
                    addPhone();
                }
            }
        });

        this.setVisible(true);
        configFrame(getJfFrame(), this);
        this.setMinimumSize(new Dimension(200,400));
    }

    /**
     * Méthode pour ajouter le nom de famille au panneaux de modification
     */
    private void addLastName(){
        JPanel lastNamePanel = new JPanel();
        JLabel lastNameLabel = new JLabel("Nom : ");
        lastNamePanel.add(lastNameLabel);
        jtfLastNameInput = new JTextField(external.getNom(), 20);
        lastNamePanel.add(jtfLastNameInput);
        jpMainPanel.add(lastNamePanel);
        jpMainPanel.revalidate();
        System.out.println("modif Nom");
    }

    /**
     * Méthode pour ajouter le prénom au panneaux de modification
     */
    private void addFirstName(){
        JPanel firstNamePanel = new JPanel();
        JLabel firstNameLabel = new JLabel("Prénom : ");
        firstNamePanel.add(firstNameLabel);
        jtfFirstNameInput = new JTextField(external.getPrenom(),20);
        firstNamePanel.add(jtfFirstNameInput);
        jpMainPanel.add(firstNamePanel);
        jpMainPanel.revalidate();
        System.out.println("modif Prénom");
    }

    /**
     * Méthode pour ajouter l'e-mail au panneaux de modification
     */
    private void addEMail(){
        JPanel emailPanel = new JPanel();
        JLabel emailLabel = new JLabel("Adresse E-Mail : ");
        emailPanel.add(emailLabel);
        jtfEmail = new JTextField(external.getEmail(),20);
        emailPanel.add(jtfEmail);
        jpMainPanel.add(emailPanel);
        jpMainPanel.revalidate();
        System.out.println("modif E-Mail");
    }

    /**
     * Méthode pour ajouter l'adresse au panneaux de modification
     */
    private void addAddress(){
        JPanel addressPanel = new JPanel();
        JLabel addressLabel = new JLabel("Adresse : ");
        addressPanel.add(addressLabel);
        addressPanel.add(new JTextField(/*personne.getAdresse()*/"adresse",20));
        jpMainPanel.add(addressPanel);
        jpMainPanel.revalidate();
        System.out.println("modif Adresse");
    }

    /**
     * Méthode pour ajouter le téléphone au panneaux de modification
     */
    private void addPhone(){
        JPanel telephonePanel = new JPanel();
        JLabel telephoneLabel = new JLabel("Téléphone : ");
        telephonePanel.add(telephoneLabel);
        jtfPhone = new JTextField(external.getTelephone(),20);
        telephonePanel.add(jtfPhone);
        jpMainPanel.add(telephonePanel);
        jpMainPanel.revalidate();
        System.out.println("modif Téléphone");
    }
}
