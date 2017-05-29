package View.Stock;

import Controller.Error.ErrorController;
import Controller.Information.InformationController;
import Controller.Stock.AddStockController;
import View.GenericWindow;
import View.MyModelTable;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * Cette classe est la classe de l'interface d'ajout d'aliment en stock.
 *
 * @author M.Silva
 *
 * @version 1.0
 *
 * @date    15.04.2017
 *
 */

public class AddStock extends GenericWindow{
    //En-têtes pour la création de la JTable
    private final String[] COLUMN_ADD_NAME = {"ID", "Description", "Quantité", "Unite"};
    //Permet de rendre certaines cellules de la JTable éditable
    private final boolean[] COLUMN_STOCK_EDITABLE = {false, false, true, false};
    //Message d'erreur qui sera affiché dans un showMessageDialog
    private final String S_ERROR_MESSAGE = "Une erreur s'est produite et l'ajout des aliments dans la base de donnée n'a pas pu être effectuée";
    //Message d'information qui sera affiché dans un showMessageDialog
    private final String S_INFORMATION_MESSAGE = "L'ajout s'est bien réalisé";
    private final String S_QUESTION_CONFIRMATION = "Voulez-vous vraiment ajouter les aliments dans le stock?";
    private final String S_HIDE_LABEL = " ";
    private final int BLOCK_CELLS = 2;
    private MyModelTable mmtTableAddStock;
    private JTable jtTableAddStock;
    private TableRowSorter<MyModelTable> trsSorter;
    private Vector<Vector<Object>> vAdd;

    /**
     * Constructeur
     * @param ascAddStockController est la référence du contrôleur de cette interface
     * @param vAdd est un Vector<Vector<Object>> mais qui en réalité contient des objets de type Stock
     *             Cela représente tous les aliments du stock et ses attributs (nom, quantité, unité,...)
     */
    public AddStock(AddStockController ascAddStockController, Vector<Vector<Object>> vAdd){
        super("Ajout de Stock");

        this.vAdd = vAdd;
        gbcMainPanel.insets = new Insets(15, 0, 15, 0);
        /***********************************************************************/
        //Instanciation et ajout du titre dans un JPanel, puis ajout du JPanel
        //du titre dans le JPanel principal en indiquant sa place dans
        //le GridBagLayout grâce au GridBagContraints
        JPanel jpTitle = new JPanel();
        JLabel jlTitle = new JLabel("Ajout de Stock");
        setTitleConfig(jlTitle);
        jpTitle.add(jlTitle);
        //Ajout du JPanel jpTitle dans le panel principal
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 0;
        jpMainPanel.add(jpTitle, gbcMainPanel);
        /***********************************************************************/
        //Instanciation du JPanel qui contient la JTable et ajout de celui-ci dans
        //le JPanel principal.
        JPanel jpTableAdd = new JPanel();
        //Ajout du JPanel jpTableAdd dans le panel principal.
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 1;
        jpMainPanel.add(jpTableAdd, gbcMainPanel);

        //Instanciations d'un objet MyModelTable, d'un object JTable et d'un object
        //TableRowSorter. Le premier sera utilisé comme paramètre pour l'instanciation
        //des deux autres. La JTable permet d'afficher les différentes infos à l'écran
        //sous-forme de tableau et le TableRowSorter permet de cliquer sur les en-têtes
        //afin de mettre les trier dans l'ordre.
        mmtTableAddStock = new MyModelTable(this.vAdd, COLUMN_ADD_NAME, COLUMN_STOCK_EDITABLE);
        jtTableAddStock = new JTable(mmtTableAddStock);
        trsSorter = new TableRowSorter<>(mmtTableAddStock);
        jtTableAddStock.setRowSorter(trsSorter);

        //L'objet Dimension d permet de connaitre la largeur de la JTable afin de pouvoir
        //affecter la bonne dimension au Viewport.
        Dimension d = jtTableAddStock.getPreferredScrollableViewportSize();
        d.width = jtTableAddStock.getPreferredSize().width;
        jtTableAddStock.setPreferredScrollableViewportSize(d);

        //Instanciation d'un panel JScroll, puis l'affecte à la taille qu'on préfère.
        JScrollPane jspAdd = new JScrollPane(jtTableAddStock);
        jspAdd.setPreferredSize(new Dimension(700, 700));

        //Ajout du panel JScroll au panel qui contient la JTable puis ajout de ce dernier
        //dans le panel principal.
        jpTableAdd.add(jspAdd);
        jpMainPanel.add(jpTableAdd, gbcMainPanel);
        /***********************************************************************/
        //Instanciation du JPanel qui contient le JLabel qui demande confirmation
        //à l'utilisateur.
        JPanel jpBottomLabel = new JPanel();
        JLabel jlQuestion = new JLabel(S_HIDE_LABEL);
        jpBottomLabel.add(jlQuestion);
        //Ajout du JPanel jpBottomLabel dans le panel principal.
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 2;
        jpMainPanel.add(jpBottomLabel, gbcMainPanel);
        /***********************************************************************/
        //Instanciation des boutons pour ajouter du stock, puis pour confirmer l'ajout
        //ou l'annuler. Au départ, les deux derniers ne pas visibles, seul celui
        //pour ajouter l'est.
        JPanel jpBottomButtons = new JPanel();
        JButton jbAddButton = new JButton("Ajouter");
        setButtonConfigMedium(jbAddButton);
        JButton jbConfirm = new JButton("Confirmer");
        jbConfirm.setVisible(false);
        setButtonConfig(jbConfirm);
        JButton jbCancel = new JButton("Annuler");
        jbCancel.setVisible(false);
        setButtonConfig(jbCancel);
        //Ajout des boutons dans le JPanel instancié pour.
        jpBottomButtons.add(jbConfirm);
        jpBottomButtons.add(jbAddButton);
        jpBottomButtons.add(jbCancel);
        //Ajout du JPanel jpBottomButtons dans le panel principal.
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 3;
        jpMainPanel.add(jpBottomButtons, gbcMainPanel);
        /***********************************************************************/
        // -----------------------------------------------------------------------------------------------------------------
        // -----------------------------------------------------------------------------------------------------------------
        // Partie pour la gestion des évènements des boutons

        //Ajout d'un ActionListener au bouton Ajouter
        jbAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Appel de la méthode checkData de l'objet AddStockController pour vérifier que
                //toutes les données rentrées par l'utilisateur sont valides. Si c'est le cas,
                //le bouton Ajouter devient invisible et les boutons Confirmer et Annuler visibles.
                //Le text du JLabel est aussi modifié.
                if(ascAddStockController.checkData(jtTableAddStock, mmtTableAddStock)){
                    jlQuestion.setText(S_QUESTION_CONFIRMATION);
                    jbAddButton.setVisible(false);
                    jbConfirm.setVisible(true);
                    jbCancel.setVisible(true);
                    //Boucle qui permet de bloquer les cellules pour que l'utilisateur ne les modifie
                    //pas après avoir cliqué sur Ajouter. Il doit annuler s'il veut les remodifier.
                    for(int i = 0; i < jtTableAddStock.getRowCount(); ++i) {
                        mmtTableAddStock.setIsCellEditable(false, i,BLOCK_CELLS);
                    }
                }
            }
        });

        //Listener du bouton Confirmer
        jbConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    //Ajout des éléments dans le stock en faisant appel à la méthode addStock
                    //de l'objet AddStockController.
                    ascAddStockController.addStock();
                    //Un message d'information apparaît si tout s'est bien déroulé.
                    new InformationController(S_INFORMATION_MESSAGE);
                    //Fermeture de la fenêtre Ajout de Stock.
                    getJfFrame().dispatchEvent(new WindowEvent(getJfFrame(), WindowEvent.WINDOW_CLOSING));
                }catch (SQLException ex){
                    new ErrorController(S_ERROR_MESSAGE + " " + ex.getMessage());
                }
                catch (Exception exc){
                    new ErrorController(S_ERROR_MESSAGE + " " + exc.getMessage());
                }

            }
        });

        //Listener du bouton Annuler
        jbCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Change le texte du JLabel, le bouton Ajouter devient visible et les boutons
                //Annuler et Confirmer invisibles.
                jlQuestion.setText(S_HIDE_LABEL);
                jbAddButton.setVisible(true);
                jbConfirm.setVisible(false);
                jbCancel.setVisible(false);
                //Boucle qui rend la colonne deux de la JTable éditable.
                for(int i = 0; i < jtTableAddStock.getRowCount(); ++i) {
                    mmtTableAddStock.setIsCellEditable(true, i,BLOCK_CELLS);
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
     * Méthode qui retourne une référence du tableau de boolean contenant les colonnes de la JTable qui seront
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
     * l'utilisateur appuie sur le bouton Ajouter. Il est important de préciser que le numéro de la première
     * colonne commence à 0.
     * @return le numéro de la colonne dont les cellules vont devenir non-éditable
     */
    public int getBLOCK_CELLS(){
        return BLOCK_CELLS;
    }

    /**
     * Méthode qui retourne la référence de l'objet mmtTableAddStock de type MyModelTable créée pour être le paramètre
     * de la JTable jtTableAddStock et l'objet TableRowSorter trsSorter lors de leurs instanciations respectives
     * @return une référence sur l'objet mmtTableAddStock.
     */
    public MyModelTable getMmtTableAddStock(){
        return mmtTableAddStock;
    }

    /**
     * Méthode qui permet d'affecter une référence de l'objet MyModelTable passé en paramètre à l'attribut privé
     * mmtTableAddStock de l'objet AddStock.
     * @param mmtTableAddStock
     */
    public void setMmtTableAddStock(MyModelTable mmtTableAddStock){
        this.mmtTableAddStock = mmtTableAddStock;
    }

    /**
     * Méthode qui retourne la référence de l'objet jtTableOrder de type JTable qui sera utilisé pour être affiché
     * dans cette interface
     * @return une référence sur l'objet jtTableAddStock
     */
    public JTable getJtTableAddStock(){
        return jtTableAddStock;
    }

    /**
     * Méthode qui permet d'affecter la référence de la JTable passée en paramètre à l'attribut privé jtTableAddStock
     * de l'objet AddStock
     * @param jtTableAddStock
     */
    public void setJtTableAddStock(JTable jtTableAddStock){
        this.jtTableAddStock = jtTableAddStock;
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
     * privé trsSorter de l'objet AddStock
     * @param trsSorter
     */
    public void setTrsSorter(TableRowSorter<MyModelTable> trsSorter){
        this.trsSorter = trsSorter;
    }

    /**
     * Méthode qui retourne la référence de l'objet vAdd de type Vector<Vector<Object>>. Il contient les
     * différents aliments du stock. Les aliments sont sous forme d'objets de type Stock.
     * @return la référence de l'objet vAdd
     */
    public Vector<Vector<Object>> getvAdd(){
        return vAdd;
    }

    /**
     * Méthode qui permet d'affecter la référence du Vector<Vector<Object>> passé en paramètre à l'attribut
     * privé vAdd de l'objet AddStock
     * @param vAdd
     */
    public void setvOrder(Vector<Vector<Object>> vAdd){
        this.vAdd = vAdd;
    }
}