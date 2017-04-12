package View.Stock;

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

    /*

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String tempS = "";
        double tempD = 0.0;

        //Checkbox tempC = new Checkbox("",false);
        // temp = new JTextField();

        boolean str = false;
        boolean dbl = false;
        boolean cbx = false;

        switch (columnIndex) {
            case 0:
                tempS = (String)test[rowIndex][columnIndex];
                str = true;
                break;
            case 1:
                tempD = (Double)test[rowIndex][columnIndex];
                dbl = true;
                break;
            case 2:
                tempD = (Double)test[rowIndex][columnIndex];
                dbl = true;
                break;
            case 3:
                tempS = (String)test[rowIndex][columnIndex];
                str = true;
                break;

            case 4: cbx = (Boolean)test[rowIndex][columnIndex];
                break;
            case 5: tempS = (String)test[rowIndex][columnIndex];
                str = true;
                break;

        }
        */
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
