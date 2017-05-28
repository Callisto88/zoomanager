package View;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 *
 * Cette classe permet de colorier la cellule spécifique de la JTable.
 *
 *
 * @author M.Silva
 *
 * @version 1.0
 *
 * @date    29.04.2017
 *
 */
public class MyRenderer extends DefaultTableCellRenderer {
    MyModelTable mmtModelTable;
    private int column;
    private Component cColor;

    /**
     * Constructeur
     * @param mmtModelTable est le modèle de la JTable à laquelle on veut changer la couleur d'une cellule
     * @param column est l'index de la colonne
     */
    public MyRenderer(MyModelTable mmtModelTable, int column) {
        this.mmtModelTable = mmtModelTable;
        this.column = column;
        this.setHorizontalAlignment(SwingConstants.RIGHT);

    }

    /**
     * Méthode qui permet de voir la couleur d'une cellule du modèle de la JTable, puis de colorié
     * la cellule de la même couleur. C'est ce qui permet de colorier les cellules de la JTable
     * @param table est la JTable en question
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row est l'index de la ligne
     * @param col est l'index de la colonne
     * @return le composant utilisé pour colorier la cellule
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        cColor = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        //Vérifie le CellStatus de la cellule qui dans ce cas signifie la couleur.
        //et affecte la couleur en arrière plan à la variable cColor
        if (mmtModelTable.getCellStatus(row, col) == CellStatus.RED) {
            cColor.setBackground(Color.RED);
        } else {
            cColor.setBackground(Color.WHITE);
        }

        //Renvoie le composant colorié
        return cColor;
    }

}
