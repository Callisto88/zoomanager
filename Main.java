import Controller.ManagerDashboardController;
import View.ManagerDashboard.ManagerDashboard;
import Model.*;

public class Main {

    public static void main(String[] args) {

         //DBConnection entryPoint = new DBConnection();
        ManagerDashboardController mdcDash = new ManagerDashboardController("Lourd");
        ManagerDashboard mgDash = new ManagerDashboard(mdcDash);
    }
}
