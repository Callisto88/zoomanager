import Controller.ManagerDashboardController;
import View.Stock.StockTab;

/**
 * Created by Andre on 10.03.2017.
 */
public class Control {
    public static void main(String[] args) {
        String windowTitle = "Zoo Manager";
        ManagerDashboardController mdc = new ManagerDashboardController(windowTitle);
        StockTab tab = new StockTab();

    }
}