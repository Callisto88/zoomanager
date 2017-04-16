package View;

import Model.Stock;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

/**
 * Created by MSilva1 on 11.04.2017.
 */
public class MyModelTable extends AbstractTableModel{

    private String[] columnNames;
    private Vector<Vector<Object>> vData;

    public MyModelTable(Vector<Vector<Object>> vData, String[] columnNames){
        super();

        this.vData = vData;
        this.columnNames = columnNames;
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


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return vData.elementAt(rowIndex).elementAt(columnIndex);
    }

    public boolean isCellEditable(int row, int column){
        if (column == 0 || column == 1 || column == 2 || column == 3) {
            return false;
        } else {
            return true;
        }
    }
}
