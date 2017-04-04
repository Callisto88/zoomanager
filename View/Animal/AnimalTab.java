package View.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andre on 10.03.2017.
 */
public class AnimalTab extends JPanel {

    public AnimalTab() {
        this.setLayout(new BorderLayout());

        JPanel panelAddAnimal = new JPanel();
        JButton addAnimal = new JButton("Ajout Animal");
        panelAddAnimal.add(addAnimal);
        this.add(panelAddAnimal, BorderLayout.NORTH);

        AnimalLabel label = new AnimalLabel();
        this.add(label, BorderLayout.WEST);

        AnimalInfo information = new AnimalInfo();
        this.add(information, BorderLayout.CENTER);

    }
}
