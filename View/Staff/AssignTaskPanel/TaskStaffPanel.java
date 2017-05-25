package View.Staff.AssignTaskPanel;

import Controller.Staff.AssignStaffTaskController;
import Model.Evenement;
import Model.Personne;
import View.GenericWindow;
import View.MyModelTable;
import sun.rmi.transport.ObjectTable;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * Classe pour l'assignation de tâches à un employé
 * Created by Andre on 28.03.2017.
 */
public class TaskStaffPanel extends GenericWindow{

    private String columnName[] = {"Date", "Heure", "Description"};

    /**
     * Constructeur de la fenêtre d'assignation de tâche à un employé
     * @param astcController controlleur de la fenêtre d'assignation de tache du personnel
     * @param personne personne à qui l'on souhaite assigner une / des tâches
     * @param tasks liste de tâches non assigné à un employé
     */
    public TaskStaffPanel(AssignStaffTaskController astcController, Personne personne, ArrayList<Evenement> tasks){
        super("Tâches Staff");
        jpMainPanel.setLayout(new GridBagLayout());
        JLabel jlTitle = new JLabel("Assignation de tâches des employées pour : ");
        setTitleConfig(jlTitle);
        GridBagConstraints gbcTable = new GridBagConstraints();
        gbcTable.gridx = 0;
        gbcTable.gridy = 0;
        gbcTable.insets = new Insets(10,5,10,5);
        jpMainPanel.add(jlTitle, gbcTable);

        JLabel jlPersonne = new JLabel(personne.getPrenom() + " " + personne.getNom());
        setTitleConfig(jlPersonne);
        gbcTable.gridy = 1;
        jpMainPanel.add(jlPersonne, gbcTable);

        Vector<Vector<Object>> voTasks = new Vector<>();
        if(tasks != null) {
            for (int i = 0; i < tasks.size(); ++i) {
                voTasks.add(tasks.get(i).toVector());
            }
        }
        MyModelTable mmtListing = new MyModelTable(voTasks, columnName);
        JTable jtTable = new JTable();
        jtTable.setModel(mmtListing);
        jtTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtTable.setColumnSelectionAllowed(false);
        jtTable.setCellSelectionEnabled(false);
        jtTable.setRowSelectionAllowed(true);
        jtTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        jtTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        jtTable.getColumnModel().getColumn(2).setPreferredWidth(300);

        JScrollPane jspStaff = new JScrollPane(jtTable);
        //jspStaff.setPreferredSize(new Dimension(800, 700));


        gbcTable.gridy = 2;
        jpMainPanel.add(jspStaff, gbcTable);

        jtTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                astcController.assignTask(personne, tasks.get(jtTable.getSelectedRow()));
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
