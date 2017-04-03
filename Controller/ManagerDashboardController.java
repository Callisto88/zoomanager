package Controller;

import Controller.Staff.StaffController;
import View.ManagerDashboard.ManagerDashboard;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andre on 14.03.2017.
 * Controlleur pour la fenêtre du dashboard
 */
public class ManagerDashboardController {
    StaffController persControl = null;
    // Ajout des autres controlleur

    // Fenêtre principale
    JFrame f = null;

    /**
     * Constructeur du controlleur
     * @param title permet de spécifier le titre de l'application pour la fenêtre du dashboard
     */
    public ManagerDashboardController(String title){
        f = new JFrame(title + " - Dashboard");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ManagerDashboard md = new ManagerDashboard(this);
        f.getContentPane().add(md, BorderLayout.CENTER);
        f.setSize(800, 500);
        f.setVisible(true);

    }

    public void revalidateDash(){
            System.out.println("active");
        f = new JFrame(" Dashboard");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ManagerDashboard md = new ManagerDashboard(this);
        f.getContentPane().add(md, BorderLayout.CENTER);
        f.setSize(800, 500);
        f.setVisible(true);
            //f.setVisible(true);
            //f.enable();
            //f.revalidate();
    }

    /**
     * permet d'instancier la fenêtre des employée
     */
    public void employeeView(){
        if( persControl ==  null) {
            persControl = new StaffController();
            //f.dispose();
            //f.setVisible(false);
            //f.disable();
        }
        else{
            persControl.revalidateView();
            //f.setVisible(false);
        }
    }
}