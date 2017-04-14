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
        boxChoiceLabel.addItem("Adresse E-Mail");
        boxChoiceLabel.addItem("Adresse");
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
         * Permet d'ajouter un champ en plus lors de la séléction
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

    private void addLastName(){
        JPanel lastNamePanel = new JPanel();
        JLabel lastNameLabel = new JLabel("Nom : ");
        lastNamePanel.add(lastNameLabel);
        lastNamePanel.add(new JTextField(personne.getNom()));
        jpMainPanel.add(lastNamePanel);
        jpMainPanel.revalidate();
        System.out.println("modif Nom");
    }

    private void addFirstName(){
        JPanel firstNamePanel = new JPanel();
        JLabel firstNameLabel = new JLabel("Prénom : ");
        firstNamePanel.add(firstNameLabel);
        firstNamePanel.add(new JTextField(personne.getPrenom()));
        jpMainPanel.add(firstNamePanel);
        jpMainPanel.revalidate();
        System.out.println("modif Prénom");
    }

    private void addEMail(){
        JPanel emailPanel = new JPanel();
        JLabel emailLabel = new JLabel("Adresse E-Mail : ");
        emailPanel.add(emailLabel);
        emailPanel.add(new JTextField(personne.getEmail()));
        jpMainPanel.add(emailPanel);
        jpMainPanel.revalidate();
        System.out.println("modif E-Mail");
    }

    private void addAddress(){
        JPanel addressPanel = new JPanel();
        JLabel addressLabel = new JLabel("Adresse : ");
        addressPanel.add(addressLabel);
        addressPanel.add(new JTextField(personne.getAdresse()));
        jpMainPanel.add(addressPanel);
        jpMainPanel.revalidate();
        System.out.println("modif Adresse");
    }

    private void addPhone(){
        JPanel telephonePanel = new JPanel();
        JLabel telephoneLabel = new JLabel("Téléphone : ");
        telephonePanel.add(telephoneLabel);
        telephonePanel.add(new JTextField(personne.getTelephone()));
        jpMainPanel.add(telephonePanel);
        jpMainPanel.revalidate();
        System.out.println("modif Téléphone");
    }

    private void addSupervisor(){
        JPanel npaPanel = new JPanel();
        JLabel npaLabel = new JLabel("Responsable : ");
        npaPanel.add(npaLabel);
        npaPanel.add(new JTextField(personne.getResponsable()));
        jpMainPanel.add(npaPanel);
        jpMainPanel.revalidate();
        System.out.println("modif Responsable");
    }
}
