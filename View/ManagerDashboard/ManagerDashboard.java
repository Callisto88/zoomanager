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
    JFrame f = null;
    Dimension dim;
    JPanel jpDashboard;
    /**
     * Constructeur de la fenêtre du dashboard.
     *
     * @param mdc Controlleur de la fenêtre du dashboard pour lui faire remonter les informations utiles.
     */
    public ManagerDashboard(ManagerDashboardController mdc, String title) {
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
                controller.showView();
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

        jpDashboard =  new JPanel(new BorderLayout());
        jpDashboard.add(this);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        f = new JFrame(title + " - Dashboard");
        f.setLayout(new BorderLayout());
        f.add(jpDashboard, BorderLayout.NORTH);
        f.setContentPane(jpDashboard);
        f.setMinimumSize(new Dimension(1280, 720));
        f.setSize(1366, 768);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setLocation((dim.width - f.getContentPane().getWidth())/2, (dim.height - f.getContentPane().getHeight())/2);
    }
}
