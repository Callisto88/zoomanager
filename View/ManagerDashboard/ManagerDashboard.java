package View.ManagerDashboard;

import Controller.ManagerDashboardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andre on 14.03.2017.
 * Fenêtre du dashboard permettant de lancer différentes fenêtres selon ce que l'on désire.
 */
public class ManagerDashboard extends JPanel {
    ManagerDashboardController controller;

    /**
     * Constructeur de la fenêtre du dashboard.
     *
     * @param mdc Controlleur de la fenêtre du dashboard pour lui faire remonter les informations utiles.
     */
    public ManagerDashboard(ManagerDashboardController mdc) {
        controller = mdc;
        this.setLayout(new GridLayout(2, 2));

        JButton employee = new JButton("Employé");
        this.add(employee);
        // Permet de demander l'instanciation de la fenêtre des employée
        employee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.employeeView();
            }
        });

        JButton stock = new JButton("Stock");
        this.add(stock);
        // Permet de demander l'instanciation de la fenêetre du stock
        stock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Lancement fenêtre Stock");
                controller.StockView();
            }
        });

        JButton show = new JButton("Animation");
        this.add(show);
        // Permet de demander l'instanciation de la fenêtre des spectacles
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Lancement fenêtre Animation");
            }
        });

        JButton animal = new JButton("Animal");
        this.add(animal);
        // Permet de demander l'instanciation de la fenêtre des annimaux.
        animal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Lancement fenêtre Animal");
                controller.AnimalView();
            }
        });

    }
}
