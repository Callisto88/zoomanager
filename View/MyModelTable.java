package View;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Vector;

/**
 *
 * Cette classe est un modèle créé pour instancier les différentes JTable.
 *
 * Ce modèle est requis afin de permettre de bloquer certaines cellules de la Jtable
 * et de pouvoir éditer dans d'autre.
 *
 *
 * @author M.Silva
 * @author Y.Ansermoz
 *
 * @version 1.0
 *
 * @date    17.04.2017
 *
 */

public class MyModelTable extends AbstractTableModel{

    private String[] columnNames;
    private boolean[] columnEditable; // false par défaut
    private Vector<Vector<Object>> vData;
    //private JButton jbTest = new JButton("Show");

    public MyModelTable(Vector<Vector<Object>> vData, String[] columnNames){
        super();

        this.vData = vData;
        this.columnNames = columnNames;
        this.columnEditable = new boolean[columnNames.length]; // false par défaut
    }

    public MyModelTable(Vector<Vector<Object>> vData, String[] columnNames, boolean[] columnEditable){
        super();

        this.vData = vData;
        this.columnNames = columnNames;
        this.columnEditable = columnEditable;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex);
    }


    @Override
    public int getRowCount() {
        return vData.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public void removeRow(int row) {
        vData.removeElementAt(row);
        fireTableDataChanged();
    }

    public void addRow(Vector<Object> vElement){
        vData.addElement(vElement);
        fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return vData.elementAt(rowIndex).elementAt(columnIndex);
    }

    public Vector<Object> getValueAtRow(int rowIndex){
        return vData.elementAt(rowIndex);
    }

    public void setValueAt(Object val, int rowIndex, int columnIndex) {
        vData.elementAt(rowIndex).setElementAt(val, columnIndex);
        fireTableDataChanged();
    }

    public boolean isCellEditable(int row, int column){
        return columnEditable[column];
    }


}
