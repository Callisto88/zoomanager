package View.Staff.AssignTaskPanel;

import Controller.Staff.AssignStaffTaskController;
import Model.Evenement;
import Model.Personne;
import View.GenericWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Classe pour l'assignation de tâches à un employé
 * Created by Andre on 28.03.2017.
 */
public class TaskStaffPanel extends GenericWindow{
    private ArrayList<Evenement> tasks = null;
    private AssignStaffTaskController astcController = null;
    private Personne personne = null;

    /**
     * Constructeur de la fenêtre d'assignation de tâche à un employé
     * @param astcController controlleur de la fenêtre d'assignation de tache du personnel
     * @param personne personne à qui l'on souhaite assigner une / des tâches
     * @param tasks liste de tâches non assigné à un employé
     */
    public TaskStaffPanel(AssignStaffTaskController astcController, Personne personne, ArrayList<Evenement> tasks){
        super("Tâches Staff");
        this.tasks = tasks;
        this.astcController = astcController;
        this.personne = personne;
        jpMainPanel.setLayout(new GridLayout(tasks.size(), 1));
        JLabel jlPersonne = new JLabel(personne.getPrenom() + " " + personne.getNom());
        jpMainPanel.add(jlPersonne);

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
            jpMainPanel.add(buttons.get(i));
            System.out.println(buttons.get(i).getName());
        }

        configFrame(getJfFrame(), this);
    }

    /** TODO : Vérifier si on ne pourrait pas faire mieux... (cf idresponsable)
     * Méthode permettant de retrouver la tâche à assigner
     * @param task tâche à retrouver
     */
    private void checkTask(String task){
        for (Evenement event : tasks){
            if (event.getDescription().equals(task)){
                astcController.assignTask(personne, event);
            }
        }
    }
}
