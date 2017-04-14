package View.Staff.StaffAssignTaskPanel;

import Model.Evenement;
import Model.Personne;
import View.GenericWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Andre on 28.03.2017.
 */
public class TaskStaffPanel extends GenericWindow{

    public TaskStaffPanel(Personne personne, ArrayList<Evenement> task){
        super("Tâches");
        jpMainPanel.add(new JLabel(personne.getPrenom() + " " + personne.getNom()), BorderLayout.NORTH);

        for(int i = 0; i < task.size(); ++i){
            System.out.println(task.get(i).getDescription());
        }


        configFrame(getJfFrame(), this);
    }
}
