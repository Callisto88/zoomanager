package Controller;

import Controller.Staff.StaffController;
import Controller.Animal.*;
import View.ManagerDashboard.ManagerDashboard;
import View.Show.ShowTab;
import View.Stock.StockTab;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andre on 14.03.2017.
 * Controlleur pour la fenêtre du dashboard
 */
public class ManagerDashboardController {
    private StaffController persControl = null;
    // Ajout des autres controlleur
    private StockTab stStock = null;
    protected AnimalController acAnimal = null;
    protected ShowTab showControl = null;
    
    
    // Fenêtre principale
    JFrame f = null;
    
    /**
     * Constructeur du controlleur
     *
     * @param title permet de spécifier le titre de l'application pour la fenêtre du dashboard
     */
    public ManagerDashboardController(String title) {
        f = new JFrame(title + " - Dashboard");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ManagerDashboard md = new ManagerDashboard(this);
        f.getContentPane().add(md, BorderLayout.CENTER);
        f.setSize(1366, 768);
        f.setMinimumSize(new Dimension(1280, 720));
        f.setVisible(true);
    }
    
    /**
     * permet d'instancier la fenêtre des employés
     */
    public void employeeView() {
        persControl = new StaffController();
    }

    /**
     * Permet d'instancier la fenêtre des évenements
     */
    public void showView(){
        showControl = new ShowTab();
    }

    /**
     * Permet d'instancier la fenêtre du stock
     */
    public void StockView() {
        // stStock = new StockTab();
    }

    /**
     * Permet d'instancier la fenêtre des animaux
     */
    public void AnimalView() {
        acAnimal = new AnimalController();
    }
}
