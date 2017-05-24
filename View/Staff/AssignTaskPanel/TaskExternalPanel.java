package View.Staff.AssignTaskPanel;

import Controller.Staff.AssignExternalTaskController;
import Model.Evenement;
import Model.Intervenant;
import View.GenericWindow;
import View.MyModelTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Classe pour l'assignation de tâches à un intervenant
 * Created by André on 23.04.2017.§
 */
public class TaskExternalPanel extends GenericWindow {
    // tableau de string décrivant les différents colonnes.
    private String columnName[] = {"Date", "Heure", "Description"};

    /**
     * Constructeur de la fenêtre d'assignation de tâches des intervenants
     * @param aetcAssignTaskController controlleur de l'assignation de tâches des intervenants pour faire remonter les infos
     * @param external intervenants à qui l'on souhaite attribuer une / des tâches
     * @param tasks listes de tâches disponibles pour les intervenants
     */
    public TaskExternalPanel(AssignExternalTaskController aetcAssignTaskController, Intervenant external, ArrayList<Evenement> tasks){
        super("Tâches Intervenant");
        jpMainPanel.setLayout(new GridBagLayout());
        JLabel jlTitre = new JLabel("Assignation de tâches Intervenant pour : ");
        setTitleConfig(jlTitre);
        GridBagConstraints gbcConstraint = new GridBagConstraints();
        int y = 0;
        gbcConstraint.gridx = 0;
        gbcConstraint.gridy = y;
        ++y;
        jpMainPanel.add(jlTitre, gbcConstraint);
        JLabel jlPersonne = new JLabel(external.getPrenom() + " " + external.getNom());
        gbcConstraint.gridy = y;
        ++y;
        gbcConstraint.insets = new Insets(10,10,10,10);
        jpMainPanel.add(jlPersonne, gbcConstraint);

        Vector<Vector<Object>> voTasks = new Vector<>();

        if(tasks != null) {
            for (int i = 0; i < tasks.size(); ++i) {
                voTasks.add(tasks.get(i).toVector());
            }
        }
        MyModelTable mmtListing = new MyModelTable(voTasks, columnName);
        JTable jtTable = new JTable();
        jtTable.setModel(mmtListing);
        //jtTable = new JTable(mmtListing);
        jtTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtTable.setColumnSelectionAllowed(false);
        jtTable.setCellSelectionEnabled(false);
        jtTable.setRowSelectionAllowed(true);
        jtTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        jtTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        jtTable.getColumnModel().getColumn(2).setPreferredWidth(300);

        JScrollPane jspStaff = new JScrollPane(jtTable);

        ++y;
        gbcConstraint.gridy = y;
        jpMainPanel.add(jspStaff, gbcConstraint);

        jtTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                aetcAssignTaskController.assignTask(external, tasks.get(jtTable.getSelectedRow()));
                mmtListing.removeRow(jtTable.getSelectedRow());
                tasks.remove(jtTable.getSelectedRow());
                jtTable.clearSelection();
                jtTable.updateUI();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        configFrame(getJfFrame(), this);
    }
}
