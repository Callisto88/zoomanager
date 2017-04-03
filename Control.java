import Controller.Login;
import Controller.ManagerDashboardController;

/**
 * Created by Andre on 10.03.2017.
 */
public class Control {
    public static void main(String[] args) {
        String windowTitle = "Zoo Manager";
        Login login = new Login();
        ManagerDashboardController mdc = new ManagerDashboardController(windowTitle);
    }
}
