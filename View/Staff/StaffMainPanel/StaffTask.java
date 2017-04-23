package View.Staff.StaffMainPanel;

import Controller.Staff.AssignStaffTaskController;
import Model.Evenement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Andre on 28.03.2017.
 * Sous-fenêtre de la fenêtre principale du personnel contenant les tâches d'un employé
 */
public class StaffTask extends JPanel {

    private AssignStaffTaskController controller = null;

    /**
     * Constructeur de la sous fenêtre affichant les tâches à faire pour un employé
     */
    public StaffTask(ArrayList<Evenement> events) {
        setLayout(new GridLayout(1,events.size()));

        for(int i = 0; i < events.size(); ++i){
            add(new JLabel(events.get(i).getDescription()));
        }
    }
}
