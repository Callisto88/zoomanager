package View.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andre on 13.03.2017.
 */
public class AnimalLabel extends JPanel {
    public AnimalLabel() {
        setLayout(new GridLayout(11, 1, 10, 10));
        //this.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Sous ensemble de gauche contenant les deux listes déroulantes de types et de noms d'animaux label pour les animaux

        // Liste déroulante avec les prénom et type d'animaux
        JComboBox boxChoiceAnimal = new JComboBox();
        JPanel choiceAnimal = new JPanel();
        JLabel typeAnimal = new JLabel("Type d'animal : ");
        choiceAnimal.add(typeAnimal);
        choiceAnimal.setAlignmentX(20);
        // Requête pour récupérer les nom des animaux
        boxChoiceAnimal.addItem("Elephant");
        boxChoiceAnimal.addItem("T-rex");
        choiceAnimal.add(boxChoiceAnimal);
        this.add(choiceAnimal);

        JComboBox boxChoiceAnimalName = new JComboBox();
        boxChoiceAnimalName.addItem("");
        JPanel choiceAnimalName = new JPanel();
        JLabel animalName = new JLabel("Nom de l'Animal");
        choiceAnimalName.add(animalName);
        choiceAnimalName.setAlignmentX(20);
        choiceAnimalName.add(boxChoiceAnimalName);
        this.add(choiceAnimalName);


        boxChoiceAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox src = (JComboBox) e.getSource();
                if (src.getSelectedItem().equals("Elephant")) {
                    boxChoiceAnimalName.removeAllItems();
                    boxChoiceAnimalName.addItem("René");
                    boxChoiceAnimalName.addItem("Babar");
                }
                if (src.getSelectedItem().equals("T-rex")) {
                    boxChoiceAnimalName.removeAllItems();
                    boxChoiceAnimalName.addItem("Alphonse");
                    boxChoiceAnimalName.addItem("Mangetout");
                }
            }
        });
        // Requête pour récuperer les détails d'un Animal
        // Nom
        JPanel detailFirstName = new JPanel();
        JLabel firstNameLabel = new JLabel("Nom : ");
        detailFirstName.add(firstNameLabel);
        //this.add(detailFirstName);
        this.add(firstNameLabel);

        // Age
        JPanel detailBirthday = new JPanel();
        JLabel birthdayLabel = new JLabel("Date de Naissance : ");
        detailBirthday.add(birthdayLabel);
        //this.add(detailBirthday);
        this.add(birthdayLabel);

        // Arrivé
        JPanel detailBirthdayIncorporation = new JPanel();
        JLabel birthdayIncorporationLabel = new JLabel("Date d'arrivé : ");
        detailBirthdayIncorporation.add(birthdayIncorporationLabel);
        //this.add(detailBirthdayIncorporation);
        this.add(birthdayIncorporationLabel);

        // Médical
        JPanel detailMedical = new JPanel();
        JLabel medicalLabel = new JLabel("Antécédent médicaux : ");
        detailMedical.add(medicalLabel);
        this.add(detailMedical);
    }
}
