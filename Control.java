import Controller.ManagerDashboardController;
import View.Base;
import View.Stock.StockTab;

/**
 * Created by Andre on 10.03.2017.
 */
public class Control {
    public static void main(String[] args) {
        String windowTitle = "Zoo Manager";
        ManagerDashboardController mdc = new ManagerDashboardController(windowTitle);
        //StockTab tab = new StockTab();
        String[] labels = {"allo", "alhuile", "echo", "bravo", "tango", "caca"};
        String[] buttonLabel = {"soleil", "fendant", "comptoir", "vaches", "carna", "valais"};
        Base b = new Base("test", "champs de test", "renseignements", labels, buttonLabel);
    }
}