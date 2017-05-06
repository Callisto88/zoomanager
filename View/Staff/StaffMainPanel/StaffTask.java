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
    private Dimension dHour = new Dimension(40, 15);
    private Dimension dDate = new Dimension(70, 15);
    private Dimension dDescription = new Dimension(120, 15);

    /**
     * Constructeur de la sous fenêtre affichant les tâches à faire pour un employé
     */
    public StaffTask(ArrayList<Evenement> events) {
        setLayout(new GridLayout(events.size(), 1));

        for(int i = 0; i < events.size(); ++i){
            JLabel jlDescription = new JLabel(events.get(i).getDescription());
            JLabel jlData = new JLabel(events.get(i).getDate().getDay() + "/" +
                    events.get(i).getDate().getMonth() + "/" + events.get(i).getDate().getYear());
            JLabel jlHour = new JLabel(events.get(i).getDate().getHours()+ "h" + events.get(i).getDate().getMinutes());
            jlDescription.setPreferredSize(dDescription);
            jlData.setPreferredSize(dDate);
            jlHour.setPreferredSize(dHour);
            add(jlData);
            add(jlDescription);
            add(jlHour);
        }
    }
}
