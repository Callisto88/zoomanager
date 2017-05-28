package View.Stock;

import Controller.Error.ErrorController;
import Controller.Information.InformationController;
import Controller.Stock.NewOrderController;
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
 *
 * Cette classe est la classe de l'interface de la nouvelle commande que l'utilisateur veut créer.
 * Elle afficher les différents aliments dont une quantité a été ajoutées. Puis l'utilisateur peut
 * confirmer la commande ou l'annuler.
 *
 * @author M.Silva
 *
 * @version 1.0
 *
 * @date    05.05.2017
 *
 */
public class NewOrder extends GenericWindow{
    //En-têtes pour la création de la JTable
    private final  String[] COLUMN_ORDER_NAME = {"ID", "Description", "Commande", "Unite"};
    //Message d'erreur qui sera affiché dans un showMessageDialog
    private final String S_ERROR_MESSAGE = "Une erreur s'est produite et l'ajout des aliments dans la base de donnée n'a pas pu être effectuée";
    //Message d'information qui sera affiché dans un showMessageDialog
    private final String S_INFORMATION_MESSAGE = "L'ajout s'est bien réalisé";
    private final String S_QUESTION_CONFIRMATION = "Voulez-vous vraiment créer une nouvelle commande?";
    private final String S_HIDE_LABEL = " ";
    private MyModelTable mmtNewOrderTable;
    private JTable jtTableNewOrder;
    private TableRowSorter<MyModelTable> trsSorter;
    private Vector<Vector<Object>> vOrder;

    /**
     * Constructeur
     * @param nocNewOrderController est la référence du contrôleur de cette interface
     * @param vOrder est un Vector<Vector<Object>> mais qui en réalité contient des objets de type Stock
     *               Cela représente tous les aliments du stock et ses attributs (nom, quantité, unité,...)
     */
    public NewOrder(NewOrderController nocNewOrderController, Vector<Vector<Object>> vOrder) {
        super("Nouvelle Commande");

        this.vOrder = vOrder;
        gbcMainPanel.insets = new Insets(15, 5, 15, 5);
        /***********************************************************************/
        //Instanciation et ajout du titre dans un JPanel, puis ajout du JPanel
        //du titre dans le JPanel principal en indiquant sa place dans
        //le GridBagLayout grâce au GridBagContraints
        JPanel jpTitle = new JPanel();
        JLabel jlTitle = new JLabel("Nouvelle Commande");
        setTitleConfig(jlTitle);
        jpTitle.add(jlTitle);
        //Ajout du JPanel jpTitle dans le panel principal
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 0;
        jpMainPanel.add(jpTitle, gbcMainPanel);
        /***********************************************************************/
        //Instanciation du JPanel qui contient la JTable et ajout de celui-ci dans
        //le JPanel principal
        JPanel jpTableNewOrder = new JPanel();
        //Ajout du JPanel jpTableAdd dans le panel principal
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 1;
        jpMainPanel.add(jpTableNewOrder, gbcMainPanel);
        //Instanciations d'un objet MyModelTable, d'un object JTable et d'un object
        //TableRowSorter. Le premier sera utilisé comme paramètre pour l'instanciation
        //des deux autres. La JTable permet d'afficher les différentes infos à l'écran
        //sous-forme de tableau et le TableRowSorter permet de cliquer sur les en-têtes
        //afin de mettre les trier dans l'ordre.
        mmtNewOrderTable = new MyModelTable(vOrder, COLUMN_ORDER_NAME);
        jtTableNewOrder = new JTable(mmtNewOrderTable);
        trsSorter = new TableRowSorter<>(mmtNewOrderTable);
        jtTableNewOrder.setRowSorter(trsSorter);

        //L'objet Dimension d permet de connaitre la largeur de la JTable afin de pouvoir
        //affecter la bonne dimension au Viewport.
        Dimension dTableOrder = jtTableNewOrder.getPreferredScrollableViewportSize();
        dTableOrder.width = jtTableNewOrder.getPreferredSize().width;
        jtTableNewOrder.setPreferredScrollableViewportSize(dTableOrder);

        //Instanciation d'un panel JScroll, puis l'affecte à la taille qu'on préfère.
        JScrollPane jspNewOrder = new JScrollPane(jtTableNewOrder);
        jspNewOrder.setPreferredSize(new Dimension(700, 700));

        //Ajout du panel JScroll au panel qui contient la JTable puis ajout de ce dernier
        //dans le panel principal.
        jpTableNewOrder.add(jspNewOrder);
        jpMainPanel.add(jpTableNewOrder, gbcMainPanel);
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
        JButton jbNewOrderButton = new JButton("Commander");
        setButtonConfigMedium(jbNewOrderButton);
        JButton jbConfirm = new JButton("Confirmer");
        jbConfirm.setVisible(false);
        setButtonConfig(jbConfirm);
        JButton jbCancel = new JButton("Annuler");
        jbCancel.setVisible(false);
        setButtonConfig(jbCancel);
        //Ajout des boutons dans le JPanel instancié pour.
        jpBottomButtons.add(jbConfirm);
        jpBottomButtons.add(jbNewOrderButton);
        jpBottomButtons.add(jbCancel);
        //Ajout du JPanel jpBottomButtons dans le panel principal
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 3;
        jpMainPanel.add(jpBottomButtons, gbcMainPanel);
        /***********************************************************************/
        // -----------------------------------------------------------------------------------------------------------------
        // -----------------------------------------------------------------------------------------------------------------
        // Partie pour la gestion des évènements des boutons

        //Ajout d'un ActionListener au bouton Commander
        jbNewOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Change le texte du JLabel, le bouton Commander devient invisible et les boutons
                //Annuler et Confirmer visibles.
                jlQuestion.setText(S_QUESTION_CONFIRMATION);
                jbNewOrderButton.setVisible(false);
                jbConfirm.setVisible(true);
                jbCancel.setVisible(true);
            }
        });

        //Ajout d'un ActionListener au bouton Confirmer
        jbConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    //Création d'une nouvelle commande en appelant la méthode newOrder() de l'objet
                    //NewOrderController.
                    nocNewOrderController.newOrder();
                    //Un message d'information apparaît si tout s'est bien déroulé.
                    new InformationController(S_INFORMATION_MESSAGE);
                    //Fermeture de la fenêtre Ajout de Stock.
                    getJfFrame().dispatchEvent(new WindowEvent(getJfFrame(), WindowEvent.WINDOW_CLOSING));
                }catch (Exception ex){
                    new ErrorController(S_ERROR_MESSAGE);
                }
            }
        });

        //Ajout d'un ActionListener au bouton Annuler
        jbCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Change le texte du JLabel, le bouton Commander devient visible et les boutons
                //Annuler et Confirmer invisibles.
                jlQuestion.setText(S_HIDE_LABEL);
                jbNewOrderButton.setVisible(true);
                jbConfirm.setVisible(false);
                jbCancel.setVisible(false);
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
    public String[] getCOLUMN_ORDER_NAME(){
        return COLUMN_ORDER_NAME;
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
     * Méthode qui retourne la référence de l'objet mmtNewOrderTable de type MyModelTable créée pour être le paramètre
     * de la JTable jtTableNewOrder et l'objet TableRowSorter trsSorter lors de leurs instanciations respectives
     * @return une référence sur l'objet mmtNewOrderTable.
     */
    public MyModelTable getMmtNewOrderTable(){
        return mmtNewOrderTable;
    }

    /**
     * Méthode qui permet d'affecter une référence de l'objet MyModelTable passé en paramètre à l'attribut privé
     * mmtNewOrderTable de l'objet NewOrder.
     * @param mmtNewOrderTable
     */
    public void setMmtNewOrderTable(MyModelTable mmtNewOrderTable){
        this.mmtNewOrderTable = mmtNewOrderTable;
    }

    /**
     * Méthode qui retourne la référence de l'objet jtTableNewOrder de type JTable qui sera utilisé pour être affiché
     * dans cette interface
     * @return une référence sur l'objet jtTableNewOrder
     */
    public JTable getJtTableNewOrder(){
        return jtTableNewOrder;
    }

    /**
     * Méthode qui permet d'affecter la référence de la JTable passée en paramètre à l'attribut privé jtTableNewOrder
     * de l'objet NewOrder
     * @param jtTableNewOrder
     */
    public void setJtTableNewOrder(JTable jtTableNewOrder){
        this.jtTableNewOrder = jtTableNewOrder;
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
     * privé trsSorter de l'objet NewOrder
     * @param trsSorter
     */
    public void setTrsSorter(TableRowSorter<MyModelTable> trsSorter){
        this.trsSorter = trsSorter;
    }

    /**
     * Méthode qui retourne la référence de l'objet vOrder de type Vector<Vector<Object>>. Il contient les
     * différents aliments et les quantités que l'utilisateur a mis dans la commande. Ils sont formés d'objet
     * de type Commande
     * @return la référence de l'objet vOrder
     */
    public Vector<Vector<Object>> getvOrder(){
        return vOrder;
    }

    /**
     * Méthode qui permet d'affecter la référence du Vector<Vector<Object>> passé en paramètre à l'attribut
     * privé vOrder de l'objet NewOrder
     * @param vOrder
     */
    public void setvOrder(Vector<Vector<Object>> vOrder){
        this.vOrder = vOrder;
    }
}
