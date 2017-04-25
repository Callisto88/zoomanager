package View.Staff.StaffModifyPanel;

import Model.Personne;
import View.GenericWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andre on 09.04.2017.
 */
public class StaffModifyPanel extends GenericWindow{
    private Personne personne = null;
    private JComboBox boxChoiceLabel = null;


    public StaffModifyPanel(Personne personne){
        super("Modificaion");
        jpMainPanel.setLayout(new GridLayout(8,1));
        this.personne = personne;
        /**
         * Liste déroulante pour séléctionner les champs que l'on souhaite modifier
         */
        JPanel modification = new JPanel();
        boxChoiceLabel = new JComboBox();
        boxChoiceLabel.addItem("Tous les champs");
        boxChoiceLabel.addItem("Nom");
        boxChoiceLabel.addItem("Prénom");
        boxChoiceLabel.addItem("Ville E-Mail");
        boxChoiceLabel.addItem("Ville");
        boxChoiceLabel.addItem("Téléphone");
        boxChoiceLabel.addItem("Responsable");
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
                if(boxChoiceLabel.getSelectedItem().equals("Ville E-Mail")){
                    addEMail();
                }

                if(boxChoiceLabel.getSelectedItem().equals("Ville")){
                    addAddress();
                }
                if(boxChoiceLabel.getSelectedItem().equals("Téléphone")){
                    addPhone();
                }

                if(boxChoiceLabel.getSelectedItem().equals("Responsable")){
                    addSupervisor();
                }
                if(boxChoiceLabel.getSelectedItem().equals("Tous les champs")){
                    addLastName();
                    addFirstName();
                    addEMail();
                    addAddress();
                    addPhone();
                    addSupervisor();
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
        lastNamePanel.add(new JTextField(personne.getNom(), 20));
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
        firstNamePanel.add(new JTextField(personne.getPrenom(), 20));
        jpMainPanel.add(firstNamePanel);
        jpMainPanel.revalidate();
        System.out.println("modif Prénom");
    }

    /**
     * Méthode pour ajouter l'e-mail au panneaux de modification
     */
    private void addEMail(){
        JPanel emailPanel = new JPanel();
        JLabel emailLabel = new JLabel("Ville E-Mail : ");
        emailPanel.add(emailLabel);
        emailPanel.add(new JTextField(personne.getEmail(),20));
        jpMainPanel.add(emailPanel);
        jpMainPanel.revalidate();
        System.out.println("modif E-Mail");
    }

    /**
     * Méthode pour ajouter l'adresse au panneaux de modification
     */
    private void addAddress(){
        JPanel addressPanel = new JPanel();
        JLabel addressLabel = new JLabel("Ville : ");
        addressPanel.add(addressLabel);
        addressPanel.add(new JTextField(/*personne.getAdresse()*/"adresse",20));
        jpMainPanel.add(addressPanel);
        jpMainPanel.revalidate();
        System.out.println("modif Ville");
    }

    /**
     * Méthode pour ajouter le téléphone au panneaux de modification
     */
    private void addPhone(){
        JPanel telephonePanel = new JPanel();
        JLabel telephoneLabel = new JLabel("Téléphone : ");
        telephonePanel.add(telephoneLabel);
        telephonePanel.add(new JTextField(personne.getTelephone(), 20));
        jpMainPanel.add(telephonePanel);
        jpMainPanel.revalidate();
        System.out.println("modif Téléphone");
    }

    /**
     * Méthode pour ajouter le superviseur au panneaux de modification
     */
    private void addSupervisor(){
        JPanel npaPanel = new JPanel();
        JLabel npaLabel = new JLabel("Responsable : ");
        npaPanel.add(npaLabel);
        npaPanel.add(new JTextField(/*personne.getResponsable()*/"Responsable", 20));
        jpMainPanel.add(npaPanel);
        jpMainPanel.revalidate();
        System.out.println("modif Responsable");
    }

}
