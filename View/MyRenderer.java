package View;

import Model.Statut;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Vector;

/**
 * Created by Miguel on 15/05/2017.
 */
public class MyRenderer extends DefaultTableCellRenderer {
    MyModelTable mmtTest;
    private int column;
    private Component cTest;

    public MyRenderer(MyModelTable mmtTest, int column) {
        this.mmtTest = mmtTest;
        this.column = column;
        this.setHorizontalAlignment(SwingConstants.RIGHT);


    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        //Cells are by default rendered as a JLabel.
        cTest = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);


        if (mmtTest.getCellStatus(row, col) == CellStatus.RED) {
            cTest.setBackground(Color.RED);
        } else {
            cTest.setBackground(Color.WHITE);
        }

        //Return the JLabel which renders the cell.
        return cTest;
    }

}
