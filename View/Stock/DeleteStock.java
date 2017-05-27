package View.Stock;

import Controller.Error.ErrorController;
import Controller.Information.InformationController;
import Controller.Stock.DeleteStockController;
import View.GenericWindow;
import View.MyModelTable;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Vector;

/**
 * Created by Miguel on 04/05/2017.
 */
public class DeleteStock extends GenericWindow{
    //En-têtes pour la création de la JTable
    private static String[] COLUMN_ADD_NAME = {"ID", "Description", "Quantité", "Unite"};
    //Permet de rendre certaines cellules de la JTable éditable
    private static boolean[] COLUMN_STOCK_EDITABLE = {false, false, true, false};
    //Message d'erreur qui sera affiché dans un showMessageDialog
    private static String S_ERROR_MESSAGE = "Une erreur s'est produite et la suppression des aliments dans la base de donnée n'a pas pu être effectuée";
    //Message d'information qui sera affiché dans un showMessageDialog
    private static String S_INFORMATION_MESSAGE = "La suppression s'est bien réalisée";
    private final String S_QUESTION_CONFIRMATION = "Voulez-vous vraiment supprimer les aliments dans le stock?";
    private final String S_HIDE_LABEL = " ";
    private final int BLOCK_CELLS = 2;
    private MyModelTable mmtTableDeleteStock;
    private JTable jtTableDeleteStock;
    private TableRowSorter<MyModelTable> trsSorter;
    private Vector<Vector<Object>> vDelete;

    public DeleteStock(DeleteStockController dscDeleteStockController, Vector<Vector<Object>> vDelete) {
        super("Suppression de Stock");

        this.vDelete = vDelete;
        gbcMainPanel.insets = new Insets(15, 5, 15, 5);
        /***********************************************************************/
        //Instanciation et ajout du titre dans un JPanel, puis ajout du JPanel
        //du titre dans le JPanel principal en indiquant sa place dans
        //le GridBagLayout grâce au GridBagContraints
        JPanel jpTitle = new JPanel();
        JLabel jlTitle = new JLabel("Suppression de Stock");
        setTitleConfig(jlTitle);
        jpTitle.add(jlTitle);
        //Ajout du JPanel jpTitle dans le panel principal
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 0;
        jpMainPanel.add(jpTitle, gbcMainPanel);
        /***********************************************************************/
        //Instanciation du JPanel qui contient la JTable et ajout de celui-ci dans
        //le JPanel principal
        JPanel jpTableDelete = new JPanel();
        //Ajout du JPanel jpTableAdd dans le panel principal
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 1;
        jpMainPanel.add(jpTableDelete, gbcMainPanel);
        //Instanciations d'un objet MyModelTable, d'un object JTable et d'un object
        //TableRowSorter. Le premier sera utilisé comme paramètre pour l'instanciation
        //des deux autres. La JTable permet d'afficher les différentes infos à l'écran
        //sous-forme de tableau et le TableRowSorter permet de cliquer sur les en-têtes
        //afin de mettre les trier dans l'ordre
        mmtTableDeleteStock = new MyModelTable(this.vDelete, COLUMN_ADD_NAME, COLUMN_STOCK_EDITABLE);
        jtTableDeleteStock = new JTable(mmtTableDeleteStock);
        trsSorter = new TableRowSorter<>(mmtTableDeleteStock);
        jtTableDeleteStock.setRowSorter(trsSorter);

        //L'objet Dimension d permet de connaitre la largeur de la JTable afin de pouvoir
        //affecter la bonne dimension au Viewport
        Dimension d = jtTableDeleteStock.getPreferredScrollableViewportSize();
        d.width = jtTableDeleteStock.getPreferredSize().width;
        jtTableDeleteStock.setPreferredScrollableViewportSize(d);

        //Instanciation d'un panel JScroll, puis l'affecte à la taille qu'on préfère
        JScrollPane jspDelete = new JScrollPane(jtTableDeleteStock);
        jspDelete.setPreferredSize(new Dimension(700, 700));

        //Ajout du panel JScroll au panel qui contient la JTable puis ajout de ce dernier
        //dans le panel principal
        jpTableDelete.add(jspDelete);
        jpMainPanel.add(jpTableDelete, gbcMainPanel);
        /***********************************************************************/
        //Instanciation du JPanel qui contient le JLabel qui demande confirmation
        //à l'utilisateur
        JPanel jpBottomLabel = new JPanel();
        JLabel jlQuestion = new JLabel(S_HIDE_LABEL);
        jpBottomLabel.add(jlQuestion);
        //Ajout du JPanel jpBottomLabel dans le panel principal
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 2;
        jpMainPanel.add(jpBottomLabel, gbcMainPanel);
        /***********************************************************************/
        //Instanciation des boutons pour supprimer du stock, puis pour confirmer la
        //suppression ou l'annuler. Au départ, les deux derniers ne pas visibles,
        //seul celui pour supprimer l'est.
        JPanel jpBottomButtons = new JPanel();
        JButton jbDeleteButton = new JButton("Supprimer");
        setButtonConfigMedium(jbDeleteButton);
        JButton jbConfirm = new JButton("Confirmer");
        jbConfirm.setVisible(false);
        setButtonConfig(jbConfirm);
        JButton jbCancel = new JButton("Annuler");
        jbCancel.setVisible(false);
        setButtonConfig(jbCancel);
        //Ajout des boutons dans le JPanel instancié pour.
        jpBottomButtons.add(jbConfirm);
        jpBottomButtons.add(jbDeleteButton);
        jpBottomButtons.add(jbCancel);
        //Ajout du JPanel jpBottomButtons dans le panel principal
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 3;
        jpMainPanel.add(jpBottomButtons, gbcMainPanel);
        /***********************************************************************/
        // -----------------------------------------------------------------------------------------------------------------
        // -----------------------------------------------------------------------------------------------------------------
        // Partie pour la gestion des évènements des boutons

        //Ajout d'un ActionListener au bouton Supprimer
        jbDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Appel de la méthode checkData de l'objet DeleteStockController pour vérifier que
                //toutes les données rentrées par l'utilisateur sont valides. Si c'est le cas,
                //le bouton Supprimer devient invisible et les boutons Confirmer et Annuler visibles.
                //Le text du JLabel est aussi modifié.
                if (dscDeleteStockController.checkData(jtTableDeleteStock, mmtTableDeleteStock)) {
                    jlQuestion.setText(S_QUESTION_CONFIRMATION);
                    jbDeleteButton.setVisible(false);
                    jbConfirm.setVisible(true);
                    jbCancel.setVisible(true);
                    //Boucle qui permet de bloquer les cellules pour que l'utilisateur ne les modifie
                    //pas après avoir cliqué sur Supprimer. Il doit annuler s'il veut les remodifier.
                    for (int i = 0; i < jtTableDeleteStock.getRowCount(); ++i) {
                        mmtTableDeleteStock.setIsCellEditable(false, i, BLOCK_CELLS);
                    }
                }
            }
        });

        //Listener du bouton Confirmer
        jbConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    //Ajout des éléments dans le stock en faisant appel à la méthode deleteStock
                    //de l'objet DeleteStockController
                    dscDeleteStockController.deleteStock();
                    //Un message d'information apparaît si tout s'est bien déroulé
                    new InformationController(S_INFORMATION_MESSAGE);
                    //Fermeture de la fenêtre Ajout de Stock
                    getJfFrame().dispatchEvent(new WindowEvent(getJfFrame(), WindowEvent.WINDOW_CLOSING));
                }catch (Exception ex){
                    new ErrorController(S_ERROR_MESSAGE);
                }
            }
        });

        //Listener du bouton Annuler
        jbCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Change le texte du JLabel, le bouton Supprimer devient visible et les boutons
                //Annuler et Confirmer invisibles.
                jlQuestion.setText(S_HIDE_LABEL);
                jbDeleteButton.setVisible(true);
                jbConfirm.setVisible(false);
                jbCancel.setVisible(false);
                //Boucle qui rend la colonne deux de la JTable éditable.
                for(int i = 0; i < jtTableDeleteStock.getRowCount(); ++i) {
                    mmtTableDeleteStock.setIsCellEditable(true, i,BLOCK_CELLS);
                }
            }
        });

        //Méthode de la GenericWindow qui permet d'ajouter le jpMainPanel dans la JFrame et d'afficher
        //le tout à l'écran
        configFrame(getJfFrame(), this);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // Les getters et les setters

    /**
     * Méthode qui retourne une référence du tableau de String contenant le nom des en-têtes de la JTable
     * @return une référence sur le tableau de String
     */
    public String[] getCOLUMN_ADD_NAME(){
        return COLUMN_ADD_NAME;
    }

    /**
     * Méthode qui retourne une référence du tableau de boolan contenant les colonnes de la JTable qui seront
     * ou non éditable
     * @return une référence sur le tableau de boolean
     */
    public boolean[] getCOLUMN_STOCK_EDITABLE(){
        return COLUMN_STOCK_EDITABLE;
    }

    /**
     * Méthode qui retourne une référence sur la String contenant le message qui s'affiche lors d'une erreur
     * @return une référence sur la String
     */
    public String getS_ERROR_MESSAGE(){
        return S_ERROR_MESSAGE;
    }

    /**
     * Méthode qui retourne une référence sur la String contenant le message qui s'affiche lors d'une information.
     * @return une référence sur la String
     */
    public String getS_INFORMATION_MESSAGE(){
        return S_INFORMATION_MESSAGE;
    }

    /**
     * Méthode qui retourne une référence sur la String contenant le message qui demande confirmation à l'utilisateur
     * @return une référence sur la String
     */
    public String getS_QUESTION_CONFIRMATION(){
        return S_QUESTION_CONFIRMATION;
    }

    /**
     * Méthode qui retourne une référence sur la String contenant un simple espace. N'affiche rien à l'écran.
     * Ou plutôt, cela permet de cacher le JLabel
     * @return une référence sur la String
     */
    public String getS_HIDE_LABEL(){
        return S_HIDE_LABEL;
    }

    /**
     * Méthode qui retourne le numéro de la colonne dont les cellules vont devenir non-éditable lorsque
     * l'utilisateur appuie sur le bouton Supprimer. Il est important de préciser que le numéro de la première
     * colonne commence à 0.
     * @return le numéro de la colonne dont les cellules vont devenir non-éditable
     */
    public int getBLOCK_CELLS(){
        return BLOCK_CELLS;
    }

    /**
     * Méthode qui retourne la référence de l'objet mmtTableDeleteStock de type MyModelTable créée pour être le paramètre
     * de la JTable jtTableDeleteStock et l'objet TableRowSorter trsSorter lors de leurs instanciations respectives
     * @return une référence sur l'objet mmtTableDeleteStock.
     */
    public MyModelTable getMmtTableDeleteStock(){
        return mmtTableDeleteStock;
    }

    /**
     * Méthode qui permet d'affecter une référence de l'objet MyModelTable passé en paramètre à l'attribut privé
     * mmtTableAddStock de l'objet DeleteStock.
     * @param mmtTableDeleteStock
     */
    public void setMmtTableDeleteStock(MyModelTable mmtTableDeleteStock){
        this.mmtTableDeleteStock = mmtTableDeleteStock;
    }

    /**
     * Méthode qui retourne la référence de l'objet jtTableDeleteStock de type JTable qui sera utilisé pour être affiché
     * dans cette interface
     * @return une référence sur l'objet jtTableDeleteStock
     */
    public JTable getJtTableDeleteStock(){
        return jtTableDeleteStock;
    }

    /**
     * Méthode qui permet d'affecter la référence de la JTable passée en paramètre à l'attribut privé jtTableDeleteStock
     * de l'objet DeleteStock
     * @param jtTableDeleteStock
     */
    public void setJtTableDeleteStock(JTable jtTableDeleteStock){
        this.jtTableDeleteStock = jtTableDeleteStock;
    }

    /**
     * Méthode qui retourne la référence de l'objet trsSorter TableRowSorter<MyModelTable>
     * @return la référence de l'objet trsSorter
     */
    public TableRowSorter<MyModelTable> getTrsSorter(){
        return trsSorter;
    }

    /**
     * Méthode qui permet d'affecter la référence de la TableRowSorter<MyModelTable> passé en paramètre à l'attribut
     * privé trsSorter de l'objet DeleteStock
     * @param trsSorter
     */
    public void setTrsSorter(TableRowSorter<MyModelTable> trsSorter){
        this.trsSorter = trsSorter;
    }

    /**
     * Méthode qui retourne la référence de l'objet vDelete de type Vector<Vector<Object>>. Il contient les
     * différents aliments du stock. Les aliments sont sous forme d'objets de type Stock.
     * @return la référence de l'objet vDelete
     */
    public Vector<Vector<Object>> getvDelete(){
        return vDelete;
    }

    /**
     * Méthode qui permet d'affecter la référence du Vector<Vector<Object>> passé en paramètre à l'attribut
     * privé vDelete de l'objet DeleteStock
     * @param vDelete
     */
    public void setvDelete(Vector<Vector<Object>> vDelete){
        this.vDelete = vDelete;
    }
}
