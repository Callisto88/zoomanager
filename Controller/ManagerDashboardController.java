package Controller;

import Controller.Staff.StaffController;
import Controller.Animal.*;
import Controller.Stock.StockTabController;
import View.ManagerDashboard.ManagerDashboard;
import View.Show.ShowTab;

/**
 * Created by Andre on 14.03.2017.
 * Controlleur pour la fenêtre du dashboard
 */
public class ManagerDashboardController {
    private StaffController persControl = null;
    // Ajout des autres controlleur
    private StockTabController stcStockTabController = null;
    protected AnimalController acAnimal = null;
    protected ShowTab showControl = null;


    // Fenêtre principale


    /**
     * Constructeur du controlleur
     *
     * @param title permet de spécifier le titre de l'application pour la fenêtre du dashboard
     */
    public ManagerDashboardController(String title) {
        ManagerDashboard md = new ManagerDashboard(this, title);
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
        stcStockTabController = new StockTabController();
    }

    /**
     * Permet d'instancier la fenêtre des animaux
     */
    public void AnimalView() {
        acAnimal = new AnimalController();
    }
}
