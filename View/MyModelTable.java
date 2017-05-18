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
    private CellStatus csTab[][];
    private String[] columnNames;
    private boolean[] columnEditable; // false par défaut
    private Vector<Vector<Object>> vData;

    public MyModelTable(Vector<Vector<Object>> vData, String[] columnNames){
        super();

        this.vData = vData;
        this.columnNames = columnNames;
        this.columnEditable = new boolean[columnNames.length]; // false par défaut
        csTab = new CellStatus[getRowCount()][getColumnCount()];
        for(int i = 0; i < getRowCount(); ++i){
            for(int j = 0; j < getColumnCount(); ++j){
                csTab[i][j] = CellStatus.EMPTY;
            }
        }
    }

    public MyModelTable(Vector<Vector<Object>> vData, String[] columnNames, boolean[] columnEditable){
        super();

        this.vData = vData;
        this.columnNames = columnNames;
        this.columnEditable = columnEditable;
        csTab = new CellStatus[getRowCount()][getColumnCount()];
        for(int i = 0; i < getRowCount(); ++i){
            for(int j = 0; j < getColumnCount(); ++j){
                csTab[i][j] = CellStatus.EMPTY;
            }
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return vData.elementAt(0).elementAt(columnIndex).getClass();
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

    public CellStatus getCellStatus(int rowIndex, int columnIndex){
        return csTab[rowIndex][columnIndex];
    }

    public void setCellStatus(CellStatus csStatus, int rowIndex, int columnIndex){
        csTab[rowIndex][columnIndex] = csStatus;
        fireTableDataChanged();
    }


    public boolean isCellEditable(int row, int column){
        return columnEditable[column];
    }

    public void setIsCellEditable(boolean bool, int rowIndex, int columnIndex){
        columnEditable[columnIndex] = bool;
    }

}
