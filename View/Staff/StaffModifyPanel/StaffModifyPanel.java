package View.Staff.StaffModifyPanel;

import Model.Personne;
import View.GenericWindow;

import javax.swing.*;
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

        this.personne = personne;
        /**
         * Liste déroulante pour séléctionner les champs que l'on souhaite modifier
         */
        JPanel modification = new JPanel();
        boxChoiceLabel = new JComboBox();
        boxChoiceLabel.addItem("Tous les champs");
        boxChoiceLabel.addItem("Nom");
        boxChoiceLabel.addItem("Prénom");
        boxChoiceLabel.addItem("Date de naissance");
        boxChoiceLabel.addItem("Numéro AVS");
        boxChoiceLabel.addItem("Adresse E-Mail");
        boxChoiceLabel.addItem("Adresse");
        boxChoiceLabel.addItem("Ville");
        boxChoiceLabel.addItem("NPA");
        boxChoiceLabel.addItem("Téléphone");
        boxChoiceLabel.addItem(/*getAllModifyObject()*/"listes des champs modifiables");
        modification.add(boxChoiceLabel);
        jpMainPanel.add(modification);

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
                    JPanel lastNamePanel = new JPanel();
                    JLabel lastNameLabel = new JLabel("Nom : ");
                    lastNamePanel.add(lastNameLabel);
                    lastNamePanel.add(new JTextField(personne.getNom()));
                    jpMainPanel.add(lastNamePanel);
                    jpMainPanel.revalidate();
                    System.out.println("modif Nom");
                }

                if(boxChoiceLabel.getSelectedItem().equals("Prénom")){
                    JPanel firstNamePanel = new JPanel();
                    JLabel firstNameLabel = new JLabel("Prénom : ");
                    firstNamePanel.add(firstNameLabel);
                    firstNamePanel.add(new JTextField(personne.getPrenom()));
                    jpMainPanel.add(firstNamePanel);
                    jpMainPanel.revalidate();
                    System.out.println("modif Prénom");
                }
                if(boxChoiceLabel.getSelectedItem().equals("Date de Naissance")){
                    JPanel birthdayPanel = new JPanel();
                    JLabel birthdayLabel = new JLabel("Date de Naissance : ");
                    birthdayPanel.add(birthdayLabel);
                    birthdayPanel.add(new JTextField(personne.getDateNaissance().toString()));
                    jpMainPanel.add(birthdayPanel);
                    jpMainPanel.revalidate();
                    System.out.println("modif Date Naissance");
                }

                if(boxChoiceLabel.getSelectedItem().equals("Numéro AVS")){
                    JPanel avsPanel = new JPanel();
                    JLabel avsLabel = new JLabel("Numéro AVS : ");
                    avsPanel.add(avsLabel);
                    avsPanel.add(new JTextField(personne.getNoAVS()));
                    jpMainPanel.add(avsPanel);
                    jpMainPanel.revalidate();
                    System.out.println("modif AVS");
                }
                if(boxChoiceLabel.getSelectedItem().equals("Adresse E-Mail")){
                    JPanel emailPanel = new JPanel();
                    JLabel emailLabel = new JLabel("Adresse E-Mail : ");
                    emailPanel.add(emailLabel);
                    emailPanel.add(new JTextField(personne.getEmail()));
                    jpMainPanel.add(emailPanel);
                    jpMainPanel.revalidate();
                    System.out.println("modif E-Mail");
                }

                if(boxChoiceLabel.getSelectedItem().equals("Adresse")){
                    JPanel addressPanel = new JPanel();
                    JLabel addressLabel = new JLabel("Adresse : ");
                    addressPanel.add(addressLabel);
                    addressPanel.add(new JTextField(personne.getAdresse()));
                    jpMainPanel.add(addressPanel);
                    jpMainPanel.revalidate();
                    System.out.println("modif Adresse");
                }
                if(boxChoiceLabel.getSelectedItem().equals("Ville")){
                    JPanel cityPanel = new JPanel();
                    JLabel cityLabel = new JLabel("Ville : ");
                    cityPanel.add(cityLabel);
                    cityPanel.add(new JTextField(personne.getAdresse()));
                    jpMainPanel.add(cityPanel);
                    jpMainPanel.revalidate();
                    System.out.println("modif Ville");
                }

                if(boxChoiceLabel.getSelectedItem().equals("NPA")){
                    JPanel npaPanel = new JPanel();
                    JLabel npaLabel = new JLabel("NPA : ");
                    npaPanel.add(npaLabel);
                    npaPanel.add(new JTextField(personne.getAdresse()));
                    jpMainPanel.add(npaPanel);
                    jpMainPanel.revalidate();
                    System.out.println("modif NPA");
                }
                if(boxChoiceLabel.getSelectedItem().equals("Téléphone")){
                    JPanel telephonePanel = new JPanel();
                    JLabel telephoneLabel = new JLabel("Téléphone : ");
                    telephonePanel.add(telephoneLabel);
                    telephonePanel.add(new JTextField(personne.getTelephone()));
                    jpMainPanel.add(telephonePanel);
                    jpMainPanel.revalidate();
                    System.out.println("modif Téléphone");
                }
            }
        });

        this.setVisible(true);
        configFrame(getJfFrame(), this);
    }

}
