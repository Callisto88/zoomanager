package View;

import Controller.Staff.AssignStaffTaskController;
import Model.Evenement;
import View.MyModelTable;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Andre on 28.03.2017.
 * Sous-fenêtre de la fenêtre principale du personnel contenant les tâches d'un employé
 */
public class EventsTable extends JPanel {

    private String[] columnName = {"Date", "Heure", "Description"};
    private JTable jtTable = null;

    /**
     * Constructeur de la sous fenêtre affichant les tâches à faire pour un employé
     * @param events ArrayList d'évenements assigné à la personne
     */
    public EventsTable(ArrayList<Evenement> events) {
        Vector<Vector<Object>> vEvents = new Vector<>();
        if(events != null) {
            for (Evenement event : events) {
                vEvents.add(event.toVector());
            }
        }

        MyModelTable dataTable = new MyModelTable(vEvents, columnName);
        jtTable = new JTable(dataTable);
        jtTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtTable.setColumnSelectionAllowed(false);
        jtTable.setCellSelectionEnabled(false);
        jtTable.setRowSelectionAllowed(true);

        TableRowSorter<MyModelTable> sorter = new TableRowSorter<>(dataTable);
        jtTable.setRowSorter(sorter);

        JScrollPane jspTask = new JScrollPane(jtTable);

        GridBagConstraints gbcTable = new GridBagConstraints();
        gbcTable.insets = new Insets(5,5,5,5);
        gbcTable.gridx = 0;
        gbcTable.gridy = 0;
        this.add(jspTask, gbcTable);
    }

    public JTable getJTable(){
        return jtTable;
    }
}
