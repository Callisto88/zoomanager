package View.Staff.StaffAssignTaskPanel;

import Model.Evenement;
import Model.Personne;
import View.GenericWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Andre on 28.03.2017.
 */
public class TaskStaffPanel extends GenericWindow{

    public TaskStaffPanel(Personne personne, ArrayList<Evenement> tasks){
        super("Tâches");
        jpMainPanel.setLayout(new GridLayout(tasks.size(), 1));
        JLabel jlPersonne = new JLabel(personne.getPrenom() + " " + personne.getNom());
        jpMainPanel.add(jlPersonne);

        ArrayList<JButton> buttons = new ArrayList<>();
        for(int i = 0; i < tasks.size(); ++i){
            JButton task = new JButton(tasks.get(i).getDescription());
            buttons.add(task);
            System.out.println(tasks.get(i).getDescription());
            task.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(e.getActionCommand());
                    JButton jb = (JButton)e.getSource();
                    Container c = jb.getParent();
                    c.remove(jb);
                    c.revalidate();
                    c.repaint();
                }
            });
        }

        for(int i = 0; i < buttons.size(); ++i){
            jpMainPanel.add(buttons.get(i));
            System.out.println(buttons.get(i).getName());
        }

        configFrame(getJfFrame(), this);
    }
}
