package View.Stock;

import Controller.Error.ErrorController;
import Controller.Information.InformationController;
import Controller.Stock.OrderContentController;
import Model.Commande;
import Model.Statut;
import View.GenericWindow;
import View.MyModelTable;
import View.PrintPDF;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 *
 * Cette classe est la classe de l'interface qui affiche le contenu d'une commande. L'utilisateur
 * choisit la commande en double cliquant dessus depuis l'interface Stock.
 *
 * @author M.Silva
 *
 * @version 1.0
 *
 * @date    17.04.2017
 *
 */
public class OrderContent extends GenericWindow{
    //En-têtes pour la création de la JTable avec les aliments et leurs quantités respectives
    private final String[] COLUMN_ORDER_CONTENT_NAME = {"ID", "Description", "Quantité", "Unite"};
    //En-têtes pour la création de la JTable avec les informations de la commande sélectionnée
    private final String[] COLUMN_ORDER_INFORMATION_NAME = {"", ""};
    //La liste des différents statuts que peut avoir une commande
    private final Statut[] ORDER_STATUS = {Statut.CREEE, Statut.EN_COURS, Statut.TERMINEE, Statut.ANNULEE, Statut.REMBOURSEE};
    //Message d'erreur qui sera affiché dans un showMessageDialog
    private final String S_ERROR_MESSAGE = "Le changement de statut à échouer";
    //Message d'information qui sera affiché dans un showMessageDialog
    private final String S_INFORMATION_MESSAGE = "Le changement de statut s'est bien réalisé";
    private Vector<Vector<Object>> vOrderInfo;
    private Vector<Vector<Object>> vOrderContent;
    private MyModelTable mmtOrderInfo;
    private MyModelTable mmtOrderContentTable;
    private JTable jtOrderInfo;
    private JTable jtTableOrderContent;
    private TableRowSorter<MyModelTable> trsSorter;

    public OrderContent(OrderContentController occOrderContentController, Vector<Vector<Object>> vOrderContent){
        super("Contenu Commande");
        this.vOrderContent = vOrderContent;
        /***********************************************************************/
        //Instanciation et ajout du titre dans un JPanel, puis ajout du JPanel
        //du titre dans le JPanel principal en indiquant sa place dans
        //le GridBagLayout grâce au GridBagContraints
        JPanel jpTitle = new JPanel();
        JLabel jlTitle = new JLabel("Contenu de la commande");
        setTitleConfig(jlTitle);
        jpTitle.add(jlTitle);
        //Ajout du JPanel jpTitle dans le panel principal
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 0;
        jpMainPanel.add(jpTitle, gbcMainPanel);
        /***********************************************************************/
        //Déclaration et instanciation des JLabel pour les en-têtes des informations relatives à la commande
        JLabel jlID = new JLabel("ID:");
        JLabel jlDate = new JLabel("Date:");
        JLabel jlStatus = new JLabel("Statut:");
        //Déclaration et instanciation des JLabel pour les informations relatives à la commande
        //récupérées sur la commande en question avec des méthodes de l'objet OrderContentController
        JLabel jlIDData = new JLabel(String.valueOf(occOrderContentController.getOrderID()));
        JLabel jlDateData = new JLabel(String.valueOf(occOrderContentController.getDateOrder()));
        JLabel jlStatusData = new JLabel(String.valueOf(occOrderContentController.getStatusOrder()));

        //Instanciation de trois Vector<Objet> pour y placer l'ID, la date et le statut de la commande
        vOrderInfo = new Vector<>();
        Vector<Object> vOrderInfo1 = new Vector<>();
        Vector<Object> vOrderInfo2 = new Vector<>();
        Vector<Object> vOrderInfo3 = new Vector<>();
        //Ajout de l'ID
        vOrderInfo1.add(jlID.getText());
        vOrderInfo1.add(jlIDData.getText());
        //Ajout du Vector<Objet> dans un Vector<Vector<Objet>> afin d'instancier une JTable
        vOrderInfo.add(vOrderInfo1);
        //Ajout de la date
        vOrderInfo2.add(jlDate.getText());
        vOrderInfo2.add(jlDateData.getText());
        //Ajout du Vector<Objet> dans un Vector<Vector<Objet>> afin d'instancier une JTable
        vOrderInfo.add(vOrderInfo2);
        //Ajout du statut
        vOrderInfo3.add(jlStatus.getText());
        vOrderInfo3.add(jlStatusData.getText());
        //Ajout du Vector<Objet> dans un Vector<Vector<Objet>> afin d'instancier une JTable
        vOrderInfo.add(vOrderInfo3);

        //Instanciation de l'objet mmtOrderInfo de type MyModelTable, puis
        //celui-ci est utilisé dans l'initialisation de la JTable jtOrderInfo
        mmtOrderInfo = new MyModelTable(vOrderInfo, COLUMN_ORDER_INFORMATION_NAME);
        jtOrderInfo = new JTable(mmtOrderInfo);
        jtOrderInfo.setPreferredSize(new Dimension(200,50));

        //L'objet Dimension d permet de connaitre la largeur de la JTable afin de pouvoir
        //affecter la bonne dimension au Viewport.
        Dimension d = jtOrderInfo.getPreferredScrollableViewportSize();
        d.width = jtOrderInfo.getPreferredSize().width;
        jtOrderInfo.setPreferredScrollableViewportSize(d);

        //Instanciation d'un panel JScroll, puis l'affecte à la taille qu'on préfère.
        JScrollPane jspOrderContent = new JScrollPane(jtOrderInfo);
        jspOrderContent.setPreferredSize(new Dimension(200, 58));

        //Ajout du panel JScroll au panel qui contient la JTable puis ajout de ce dernier
        //dans le panel principal.
        JPanel jpOrderInfo = new JPanel();
        jpOrderInfo.add(jspOrderContent);

        //Ajout du JPanel jpOrderInfo dans le panel principal
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 1;
        jpMainPanel.add(jpOrderInfo, gbcMainPanel);
        /***********************************************************************/
        //Instanciation du JPanel qui contient les boutons Confirmer, Annuler,
        //Cahnger, ainsi que de la JComboBox avec les différents status d'une
        //commande
        JPanel jpChangeStatus = new JPanel();
        //Ajout du JPanel jpTableAdd dans le panel principal
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 2;
        jpMainPanel.add(jpChangeStatus, gbcMainPanel);

        //Instanciation des boutons Changer, Confirmer et Annuler.
        JButton jbChangeStatus = new JButton("Changer");
        //Instanciation de la JComboBox avec les différents statuts d'une commande.
        JComboBox jcbChangeStatus = new JComboBox(ORDER_STATUS);
        JButton jbConfirm = new JButton("Confirmer");
        jbConfirm.setVisible(false);
        JButton jbDelete = new JButton("Annuler");
        jbDelete.setVisible(false);

        //Ajout des trois boutons et de la JComboBox.
        jpChangeStatus.add(jbConfirm);
        jpChangeStatus.add(jbChangeStatus);
        jpChangeStatus.add(jcbChangeStatus);
        jpChangeStatus.add(jbDelete);
        /***********************************************************************/
        //Instanciation du JPanel qui contient la JTable et ajout de celui-ci dans
        //le JPanel principal.
        JPanel jpTableOrderContent = new JPanel();
        //Ajout du JPanel jpTableAdd dans le panel principal.
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 3;
        jpMainPanel.add(jpTableOrderContent, gbcMainPanel);
        //Instanciations d'un objet MyModelTable, d'un object JTable et d'un object
        //TableRowSorter. Le premier sera utilisé comme paramètre pour l'instanciation
        //des deux autres. La JTable permet d'afficher les différentes infos à l'écran
        //sous-forme de tableau et le TableRowSorter permet de cliquer sur les en-têtes
        //afin de mettre les trier dans l'ordre.
        mmtOrderContentTable = new MyModelTable(vOrderContent, COLUMN_ORDER_CONTENT_NAME);
        jtTableOrderContent = new JTable(mmtOrderContentTable);
        trsSorter = new TableRowSorter<>(mmtOrderContentTable);
        jtTableOrderContent.setRowSorter(trsSorter);

        //L'objet Dimension d permet de connaitre la largeur de la JTable afin de pouvoir
        //affecter la bonne dimension au Viewport.
        Dimension dOrderContent = jtTableOrderContent.getPreferredScrollableViewportSize();
        dOrderContent.width = jtTableOrderContent.getPreferredSize().width;
        jtTableOrderContent.setPreferredScrollableViewportSize(dOrderContent);

        //Instanciation d'un panel JScroll, puis l'affecte à la taille qu'on préfère.
        JScrollPane jspAdd = new JScrollPane(jtTableOrderContent);
        jspAdd.setPreferredSize(new Dimension(700, 500));

        //Ajout du panel JScroll au panel qui contient la JTable puis ajout de ce dernier
        //dans le panel principal.
        jpTableOrderContent.add(jspAdd);
        jpMainPanel.add(jpTableOrderContent, gbcMainPanel);
        /***********************************************************************/
        //Instanciation du bouton imprimer. Puis du panel et on ajoute le bouton dans le panel
        JButton jbPrint = new JButton("Imprimer");
        JPanel jpPrint = new JPanel();
        jpPrint.add(jbPrint);
        //Ajout du JPanel jpPrint dans le panel principal.
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 4;
        jpMainPanel.add(jpPrint, gbcMainPanel);
        /***********************************************************************/
        // -----------------------------------------------------------------------------------------------------------------
        // -----------------------------------------------------------------------------------------------------------------
        // Partie pour la gestion des évènements des boutons

        //Ajout d'un ActionListener au bouton Changer
        jbChangeStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Le bouton Changer et la JComboBox deviennent invisibles et les boutons
                //Annuler et Confirmer visibles.
                jbChangeStatus.setVisible(false);
                jbConfirm.setVisible(true);
                jbDelete.setVisible(true);
                jcbChangeStatus.setVisible(false);
            }
        });

        //Ajout d'un ActionListener au bouton Confirmer
        jbConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int ROW_INDEX_INFO = 2;
                final int COLUMN_INDEX_INFO = 1;
                final int ROW_INDEX_ORDER_ID = 0;
                final int COLUMN_INDEX_ORDER_STATUS = 2;

                Statut sStatus = (Statut)jcbChangeStatus.getSelectedItem();
                MyModelTable mmtTemp = occOrderContentController.getMmtOrderContent();
                int sizeTableOrder = mmtTemp.getRowCount();
                int idOrder = occOrderContentController.getOrderID();


                Commande cOrder = new Commande(idOrder, occOrderContentController.getDateOrder(),sStatus);
                if(occOrderContentController.updateStatus(cOrder)){
                    for(int i = 0; i < sizeTableOrder; ++i){
                        if((int)mmtTemp.getValueAt(i, ROW_INDEX_ORDER_ID) == idOrder){
                            mmtTemp.setValueAt(sStatus, i, COLUMN_INDEX_ORDER_STATUS);
                        }
                    }
                    mmtOrderInfo.setValueAt(sStatus, ROW_INDEX_INFO, COLUMN_INDEX_INFO);
                    new InformationController(S_INFORMATION_MESSAGE);
                    jbChangeStatus.setVisible(true);
                    jbConfirm.setVisible(false);
                    jbDelete.setVisible(false);
                    jcbChangeStatus.setVisible(true);
                }else{
                    new ErrorController(S_ERROR_MESSAGE);
                }
            }
        });

        //Ajout d'un ActionListener au bouton Supprimer
        jbDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Le bouton Changer et la JComboBox deviennent visibles et les boutons
                //Annuler et Confirmer invisibles.
                jbChangeStatus.setVisible(true);
                jbConfirm.setVisible(false);
                jbDelete.setVisible(false);
                jcbChangeStatus.setVisible(true);
            }
        });

        //Ajout d'un ActionListener au bouton Imprimer
        jbPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Instancie un objet PrintPDF qui permet de mettre les infos ainsi que le
                //contenu dans un fichier et de l'enregistrer en pdf.
                JLabel jlPrintTitle = new JLabel("Contenu Commande");
                setFontTitlePrint(jlPrintTitle);
                String sAdditionalTest = "";
                new PrintPDF(jtTableOrderContent, jtOrderInfo, jlPrintTitle, sAdditionalTest);
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
     * qui contient les aliments et les quantités que la commande contient
     * @return une référence sur le tableau de String
     */
    public String[] getCOLUMN_ORDER_CONTENT_NAME(){
        return COLUMN_ORDER_CONTENT_NAME;
    }

    /**
     * Méthode qui retourne une référence du tableau de String contenant le nom des en-têtes de la JTable
     * qui contient les information (ID, date et statut) de la commande
     * @return une référence sur le tableau de String
     */
    public String[] getCOLUMN_ORDER_INFORMATION_NAME(){
        return COLUMN_ORDER_INFORMATION_NAME;
    }

    /**
     * Méthode qui retourne une référence du tableau de Statut contenant les différents statuts que peut
     * avoir une commande
     * @return une référence sur le tableau de Statut
     */
    public Statut[] getORDER_STATUS(){
        return ORDER_STATUS;
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
     * Méthode qui retourne la référence de l'objet mmtOrderInfo de type MyModelTable créée pour être le paramètre
     * de la JTable jtOrderInfo
     * @return une référence sur l'objet mmtOrderInfo.
     */
    public MyModelTable getMmtOrderInfo(){
        return mmtOrderInfo;
    }

    /**
     * Méthode qui permet d'affecter une référence de l'objet MyModelTable passé en paramètre à l'attribut privé
     * mmtOrderInfo de l'objet OrderContent.
     * @param mmtOrderInfo
     */
    public void setMmtOrderInfo(MyModelTable mmtOrderInfo){
        this.mmtOrderInfo = mmtOrderInfo;
    }

    /**
     * Méthode qui retourne la référence de l'objet mmtOrderContentTable de type MyModelTable créée pour être le
     * paramètre de la JTable jtTableOrderContent
     * @return une référence sur l'objet mmtOrderContentTable
     */
    public MyModelTable getMmtOrderContentTable(){
        return mmtOrderContentTable;
    }

    /**
     * Méthode qui permet d'affecter une référence de l'objet MyModelTable passé en paramètre à l'attribut privé
     * mmtOrderContentTable de l'objet OrderContent
     */
    public void setMmtOrderContentTable(MyModelTable mmtOrderContentTable){
        this.mmtOrderContentTable = mmtOrderContentTable;
    }

    /**
     * Méthode qui retourne la référence de l'objet jtOrderInfo de type JTable qui sera utilisé pour être affiché
     * dans cette interface
     * @return une référence sur l'objet jtOrderInfo
     */
    public JTable getJtOrderInfo(){
        return jtOrderInfo;
    }

    /**
     * Méthode qui permet d'affecter la référence de la JTable passée en paramètre à l'attribut privé jtOrderInfo
     * de l'objet OrderContent
     * @param jtOrderInfo
     */
    public void setJtOrderInfo(JTable jtOrderInfo){
        this.jtOrderInfo = jtOrderInfo;
    }

    /**
     * Méthode qui retourne la référence de l'objet jtTableOrderContent de type JTable qui sera utilisé pour être affiché
     * dans cette interface
     * @return une référence sur l'objet jtTableOrderContent
     */
    public JTable getJtTableOrderContent(){
        return jtTableOrderContent;
    }

    /**
     * Méthode qui permet d'affecter la référence de la JTable passée en paramètre à l'attribut privé jtTableOrderContent
     * de l'objet OrderContent
     * @param jtTableOrderContent
     */
    public void setJtTableOrderContent(JTable jtTableOrderContent){
        this.jtTableOrderContent = jtTableOrderContent;
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
     * Méthode qui retourne la référence de l'objet vOrderInfo de type Vector<Vector<Object>>. Il contient les
     * informations concernant la commande.
     * @return la référence de l'objet vOrderInfo
     */
    public Vector<Vector<Object>> getvOrderInfo(){
        return vOrderInfo;
    }

    /**
     * Méthode qui permet d'affecter la référence du Vector<Vector<Object>> passé en paramètre à l'attribut
     * privé vOrderInfo de l'objet OrderContent
     * @param vOrderInfo
     */
    public void setvOrderInfo(Vector<Vector<Object>> vOrderInfo){
        this.vOrderInfo = vOrderInfo;
    }

    /**
     * Méthode qui retourne la référence de l'objet vOrderContent de type Vector<Vector<Object>>. Il contient les
     * différents aliments et les quantités de la commande. Les aliments sont sous forme d'objets de type Stock.
     * @return la référence de l'objet vOrderContent
     */
    public Vector<Vector<Object>> getvOrderContent(){
        return vOrderContent;
    }

    /**
     * Méthode qui permet d'affecter la référence du Vector<Vector<Object>> passé en paramètre à l'attribut
     * privé vOrderContent de l'objet OrderContent
     * @param vOrderContent
     */
    public void setvOrderContent(Vector<Vector<Object>> vOrderContent){
        this.vOrderContent = vOrderContent;
    }
}
