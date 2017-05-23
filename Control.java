import Controller.Animal.AnimalController;
import Controller.ManagerDashboardController;
import Controller.Staff.AddStaffController;
import Controller.Staff.AssignStaffTaskController;
import Controller.Staff.ModifyExternalController;
import Controller.Staff.ModifyStaffController;
import Model.Evenement;
import Model.Intervenant;
import Model.Personne;
import View.Animal.AnimalTab;

import javax.swing.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Andre on 10.03.2017.
 */
public class Control {
    public static void main(String[] args) {
        String windowTitle = "Zoo Manager";
        ManagerDashboardController mdc = new ManagerDashboardController(windowTitle);
        //StockTab tab = new StockTab();

        //AnimalController ann = new AnimalController();
        //Intervenant i = new Intervenant("entreprise", "Céline", "Dion", 2, "Celine.dion@crotte.ch", "00415678923");
        //ModifyExternalController mec = new ModifyExternalController(i);
        //Personne p = new Personne(15, "3769796628117", "Rachel", "Martin", 15, "rmartine@apple.com", "31-(687)486-2730", new Date(1985-12-15), 2, "externe", new Date(2000-12-15), "CDD");
        //ModifyStaffController m = new ModifyStaffController(p);
        ArrayList<Evenement> events = new ArrayList<>();
        events.add(new Evenement(1, "Nettoyer les enclos", new Timestamp(2002-11-24), "Nettoyage"));
        events.add(new Evenement(2, "Nourrir les tigres", new Timestamp(2004-02-17), "Nourrir"));
        events = null;
        /*
        JFrame test = new JFrame();
        StaffTask st = new StaffTask(events);
        test.add(st);
        test.pack();
        test.setVisible(true);
*/

        //AssignStaffTaskController task = new AssignStaffTaskController(p, events);
    }
}