package View;


import javax.swing.table.AbstractTableModel;
import java.util.Vector;

/**
 * Created by MSilva1 on 11.04.2017.
 */
public class MyModelTable extends AbstractTableModel{

    private String[] columnNames;
    private boolean[] columnEditable; // false par défaut
    private Vector<Vector<Object>> vData;

    public MyModelTable(Vector<Vector<Object>> vData, String[] columnNames){
        super();

        this.vData = vData;
        this.columnNames = columnNames;
        this.columnEditable = new boolean[columnNames.length];
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
        for(int i = 0; i < column; i++){
            return columnEditable[i];
        }
    }
}
