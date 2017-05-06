package View.Staff.AssignTaskPanel;

import Controller.Staff.AssignExternalTaskController;
import Model.Evenement;
import Model.Intervenant;
import View.GenericWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by André on 23.04.2017.§
 */
public class TaskExternalPanel extends GenericWindow {
    private ArrayList<Evenement> aleTasks = null;
    private Intervenant external = null;
    private AssignExternalTaskController aetcAssignTaskController = null;

    private GridBagConstraints gbcConstraint = new GridBagConstraints();
    private int y = 0;

    public TaskExternalPanel(AssignExternalTaskController aetcAssignTaskController, Intervenant external, ArrayList<Evenement> tasks){
        super("Tâches Intervenant");
        aleTasks = tasks;
        this.external = external;
        this.aetcAssignTaskController = aetcAssignTaskController;
        JLabel jlTitre = new JLabel("Détails tâches Intervenant");
        setTitleConfig(jlTitre);
        gbcConstraint.gridx = 0;
        gbcConstraint.gridy = y;
        ++y;
        jpMainPanel.add(jlTitre, gbcConstraint);
        jpMainPanel.setLayout(new GridBagLayout());
        JLabel jlPersonne = new JLabel(external.getPrenom() + " " + external.getNom());
        gbcConstraint.gridy = y;
        ++y;
        gbcConstraint.insets = new Insets(10,10,10,10);
        jpMainPanel.add(jlPersonne, gbcConstraint);

        // Boucle permettant de crée les boutons correspondant au tâches non-assignés, et les efface
        // en attribuant la tache sélectionné (cliqué) à la personne
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
                    checkTask(jb.getText());
                    Container c = jb.getParent();
                    c.remove(jb);
                    c.revalidate();
                    c.repaint();
                }
            });
        }

        // Permet d'ajouter chaque bouttons au pannel
        for(int i = 0; i < buttons.size(); ++i){
            if(i % 2 == 0) {
                gbcConstraint.gridx = 0;
                gbcConstraint.gridy = y;
                ++y;
            }
            else{
                gbcConstraint.gridx = 1;
            }
            jpMainPanel.add(buttons.get(i), gbcConstraint);
            System.out.println(buttons.get(i).getName());
        }

        configFrame(getJfFrame(), this);
    }

    private void checkTask(String task){
        for (Evenement event : aleTasks){
            if (event.getDescription().equals(task)){
                aetcAssignTaskController.assignTask(external, event);
            }
        }
    }
}
