import Controller.ManagerDashboardController;
import Controller.Staff.AddStaffController;
import Controller.Staff.AssignStaffTaskController;
import Controller.Staff.ModifyStaffController;
import Model.Evenement;
import Model.Personne;
import View.Base;
import View.Stock.StockTab;

import java.sql.Date;
import java.util.ArrayList;

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
        //Base b = new Base("test", "champs de test", "renseignements", labels, buttonLabel);
        //AddStaffController a = new AddStaffController();
        Personne p = new Personne(15, "3769796628117", "Rachel", "Martin", 15, "rmartine@apple.com", "31-(687)486-2730", new Date(1985-12-15), 2, "externe", new Date(2000-12-15), "CDD");
        //ModifyStaffController m = new ModifyStaffController(p);
        ArrayList<Evenement> events = new ArrayList<>();
        events.add(new Evenement(1, "Nettoyer les enclos", new Date(2002-11-24), 2));
        events.add(new Evenement(2, "Nourrir les tigres", new Date(2004-02-17), 5));
        //AssignStaffTaskController task = new AssignStaffTaskController(p, events);
    }
}