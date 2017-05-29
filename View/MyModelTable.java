package View;

import javax.swing.table.AbstractTableModel;
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
 * @date    15.04.2017
 *
 */

public class MyModelTable extends AbstractTableModel{
    private CellStatus csTab[][];
    private String[] columnNames;
    private boolean[] columnEditable; // false par défaut
    private Vector<Vector<Object>> vData;

    /**
     * Constructeur
     * @param vData est un Vector<Vector<Object>> qui contient les données qui seront
     *              affichées dans la JTable
     * @param columnNames est un tableau de String contenant le nom des différentes
     *                    en-têtes
     */
    public MyModelTable(Vector<Vector<Object>> vData, String[] columnNames){
        //super();
        this(vData, columnNames, new boolean[columnNames.length]);
/*
        this.vData = vData;
        this.columnNames = columnNames;
        this.columnEditable = new boolean[columnNames.length]; // false par défaut
        csTab = new CellStatus[getRowCount()][getColumnCount()];
        for(int i = 0; i < getRowCount(); ++i){
            for(int j = 0; j < getColumnCount(); ++j){
                csTab[i][j] = CellStatus.EMPTY;
            }
        }
        */

    }

    /**
     * Constructeur
     * @param vData est un Vector<Vector<Object>> qui contient les données qui seront
     *              affichées dans la JTable
     * @param columnNames est un tableau de String contenant le nom des différentes
     *                    en-têtes
     * @param columnEditable est un tableau de boolean qui va permettre rendre certaine(s)
     *                       colonne(s) éditable ou pas
     *
     */
    public MyModelTable(Vector<Vector<Object>> vData, String[] columnNames, boolean[] columnEditable){

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

    /**
     * Méthode qui permet de connaître le nom de l'en-tête à l'index passé en paramètre
     * @param column est l'index de la colonne
     * @return une String qui est le nom de l'en-tête
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Méthode qui permet de connaître le type de données de la colonne à l'index passé en paramètre
     * @param columnIndex est l'index de la colonne
     * @return un objet de type Class et qui contient le type de données de la colonne
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(vData.elementAt(0).elementAt(columnIndex).getClass() != null) {
            return vData.elementAt(0).elementAt(columnIndex).getClass();
        }
        else{
            return super.getColumnClass(columnIndex);
        }
    }

    /**
     * Méthode qui permet de connaître le nombre de lignes que contient la JTable
     * @return un int qui est le nombre de lignes
     */
    @Override
    public int getRowCount() {
        return vData.size();
    }

    /**
     * Méthode qui permet de connaître le nombre de colonnes que contient la JTable
     * @return un int qui est le nombre de colonnes
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Méthode qui permet d'enlever une ligne et de mettre l'affichage à jour
     * @param row est l'index de la ligne à enlever
     */
    public void removeRow(int row) {
        vData.removeElementAt(row);
        fireTableDataChanged();
    }

    /**
     * Méthode qui permet d'ajouter une ligne et de mettre l'affichage à jour
     * @param vElement est un Vector<Object> qui est la ligne à ajouter.
     */
    public void addRow(Vector<Object> vElement){
        vData.addElement(vElement);
        fireTableDataChanged();
    }

    /**
     * Méthode qui permet de retourner la valeur de la cellule aux indexes passés en paramètre
     * @param rowIndex est l'index de la ligne
     * @param columnIndex est l'index de la colonne
     * @return la valeur contenu dans la cellule sous la forme d'un objet de type Object
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return vData.elementAt(rowIndex).elementAt(columnIndex);
    }

    /**
     * Méthode qui permet de retourner une ligne entière de la JTable sous forme d'un Vector<Object>
     * @param rowIndex est l'index de la ligne
     * @return la ligne entière de l'index passé en paramètre sous forme d'un objet de type Vector<Object>
     */
    public Vector<Object> getValueAtRow(int rowIndex){
        return vData.elementAt(rowIndex);
    }

    /**
     * Méthode qui permet de changer la valeur d'une cellule qui est aux indexes passés en paramètres
     * @param val est un objet de type Object qui contient la valeur à mettre dans la JTable
     * @param rowIndex est l'index de la ligne
     * @param columnIndex est l'index de la colonne
     */
    public void setValueAt(Object val, int rowIndex, int columnIndex) {
        vData.elementAt(rowIndex).setElementAt(val, columnIndex);
        fireTableDataChanged();
    }

    /**
     * Méthode qui permet de retourner le statut de la cellule aux indexes passés en paramètre
     * @param rowIndex est l'index de la ligne
     * @param columnIndex est l'index de la colonne
     * @return le statut de la cellule sous forme d'objet de type CellStatus
     */
    public CellStatus getCellStatus(int rowIndex, int columnIndex){
        return csTab[rowIndex][columnIndex];
    }

    /**
     * Méthode qui permet de changer le statut d'une cellule aux indexes passés en paramètre
     * @param csStatus est le statut à affecter à la cellule
     * @param rowIndex est l'index de la ligne
     * @param columnIndex est l'index de la colonne
     */
    public void setCellStatus(CellStatus csStatus, int rowIndex, int columnIndex){
        csTab[rowIndex][columnIndex] = csStatus;
        fireTableDataChanged();
    }

    /**
     * Méthode qui permet de savoir si une cellule est éditable ou non
     * @param row est l'index de la ligne
     * @param column est l'index de la colonne
     * @return true si la cellule est éditable, sinon false
     */
    public boolean isCellEditable(int row, int column){
        return columnEditable[column];
    }

    /**
     * Méthode qui permet de rendre une cellule éditable ou non-éditable
     * @param bool est le boolean à affecter à la cellule, c'est ce qui définit si elle est éditable ou non
     * @param rowIndex est l'index de la ligne
     * @param columnIndex est l'index de la colonne
     */
    public void setIsCellEditable(boolean bool, int rowIndex, int columnIndex){
        columnEditable[columnIndex] = bool;
    }

}
