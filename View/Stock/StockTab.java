package View.Stock;

import Controller.Error.ErrorController;
import Controller.Information.InformationController;
import Controller.Stock.*;

import Model.Commande;
import Model.ExceptionDataBase;
import Model.Statut;
import Model.Stock;

import View.*;
import View.Information.InformationPanel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

/**
 *
 * Cette classe est celle de l'interface principale du stock.
 *
 * A gauche le stock actuel avec plusieurs fonctionnalités (Commande, Ajouter, ...)
 *
 * A droite les commandes et leurs différents statuts.
 *
 * @author M.Silva
 *
 * @version 1.0
 *
 * @date    15.04.2017
 *
 */

public class StockTab extends GenericWindow {
    //En-têtes pour la création de la JTable concernant le stock actuel
    private final String[] COLUMN_STOCK_NAME = {"ID", "Description", "Quantité", "Quantité Minimum", "Unite", "Commande"};
    //Permet de rendre certaines cellules de la JTable éditable lors de l'instanciation de l'objet MyModelTable
    private final boolean[] COLUMN_STOCK_EDITABLE = {false, false, false, false, false, true};
    //En-têtes pour la création de la JTable concernant l'historique des commandes
    //Toutes les colonnes sont de façon à ne pas être éditables
    private final String[] COLUMN_HISTORY_NAME = {"ID", "Date", "Statut"};
    //Liste des différents statuts que peut avoir une commande, plus le statut TOUS
    private final Statut [] ORDER_STATUS = {Statut.TOUS, Statut.CREEE, Statut.EN_COURS, Statut.TERMINEE, Statut.ANNULEE, Statut.REMBOURSEE};
    //Le numéro de la colonne (Commande) de la JTable qui permet de rentrer les quantités voulues pour créer une commande
    private final int COLUMN_QUANTITY = 5;
    private ArrayList<Stock> alStock;
    private ArrayList<Commande> alCommandeHistory;
    private MyModelTable mmtStock;
    private MyModelTable mmtCommandeHistory;
    private MyRenderer mrRendererStock;
    private MyRenderer mrRendererOrder;
    private JTable jtTableStock;
    private JTable jtTableCommandeHistory;
    private TableRowSorter<MyModelTable> trsSorter;
    private StockTabController stcStockTabController;

    /**
     * Constructeur
     * @param stcStockTabController est la référence du contrôleur de cette interface
     */
    public StockTab(StockTabController stcStockTabController){
        super("Stock");

        this.stcStockTabController = stcStockTabController;
        //Instanciation du GridBagLayout et du GridBagConstraints du panel principal de gauche,
        //donc celui du stock.
        GridBagLayout gblLeft = new GridBagLayout();
        GridBagConstraints gbcLeft  = new GridBagConstraints();
        //Instanciation du panel principal de gauche et on lui "set" un layout.
        JPanel jpLeft = new JPanel();
        jpLeft.setLayout(gblLeft);
        //Ajout du panel principal de gauche dans le panel principal
        jpMainPanel.add(jpLeft);
        /***********************************************************************************/
        //Instanciation du JPanel qui contient le titre et ajout de celui-ci dans
        //le JPanel principal de gauche.
        JPanel jpLeftTitle = new JPanel();
        //Instanciation du titre de gauche
        JLabel jlStockTitle = new JLabel("Liste d'aliment en stock");
        setTitleConfig(jlStockTitle);
        jpLeftTitle.add(jlStockTitle);
        //Ajout du JPanel jpLeftTitle dans le panel principal de gauche.
        gbcLeft.fill = GridBagConstraints.CENTER;
        gbcLeft.gridx = 0;
        gbcLeft.anchor = GridBagConstraints.NORTH;
        gbcLeft.gridy = 0;
        jpLeft.add(jpLeftTitle,gbcLeft);
        /***********************************************************************************/
        //Instanciation du JPanel qui contient les boutons Imprimer, Commande, Ajouter,
        //Supprimer et Effacer
        JPanel jpButtonStock = new JPanel();
        //Ajout du JPanel jpButtonStock dans le panel principal de gauche.
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 1;
        jpLeft.add(jpButtonStock, gbcLeft);

        //Instanciation des boutons qui se trouvent à gauche dans l'interface Stock
        JButton jbPrint = new JButton("Imprimer");
        setButtonConfig(jbPrint);
        JButton jbOrder = new JButton("Commande");
        setButtonConfig(jbOrder);
        JButton jbAdd = new JButton("Ajouter");
        setButtonConfig(jbAdd);
        JButton jbDelete = new JButton("Supprimer");
        setButtonConfig(jbDelete);
        JButton jbReset = new JButton("Effacer");
        setButtonConfig(jbReset);

        //Instanciation d'un GridBagLayout et d'un GridBagConstraints pour le panel contenant
        //les boutons. Cette décision a été prise car c'est plus simple de placer les objets
        //et ils ne bougent pas si on passe en mode plein écran
        GridBagLayout gblStockBoutton = new GridBagLayout();
        jpButtonStock.setLayout(gblStockBoutton);
        GridBagConstraints gbcStockBouton = new GridBagConstraints();
        //Configuration des espaces en les composants
        gbcStockBouton.insets = new Insets(15, 5, 15, 5);

        //Ajout et placement du bouton jbPrint dans le panel jpButtonStock.
        gbcStockBouton.gridx = 0;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbPrint, gbcStockBouton);

        //Ajout et placement du bouton jbOrder dans le panel jpButtonStock.
        gbcStockBouton.gridx = 1;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbOrder, gbcStockBouton);

        //Ajout et placement du bouton jbAdd dans le panel jpButtonStock.
        gbcStockBouton.gridx = 2;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbAdd, gbcStockBouton);

        //Ajout et placement du bouton jbDelete dans le panel jpButtonStock.
        gbcStockBouton.gridx = 3;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbDelete, gbcStockBouton);

        //Ajout et placement du bouton jbReset dans le panel jpButtonStock.
        gbcStockBouton.gridx = 4;
        gbcStockBouton.gridy = 0;
        jpButtonStock.add(jbReset, gbcStockBouton);
        /***********************************************************************************/
        //Instanciation du JPanel qui contient la JTable contenant les aliments en stock
        //et ajout de celui-ci dans le JPanel principal de gauche.
        JPanel jpTableStock = new JPanel();
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        gbcLeft.weighty = 20;
        jpLeft.add(jpTableStock, gbcLeft);

        //Configuration de la taille préférée pour la JTable de gauche
        jpTableStock.setPreferredSize(new Dimension(800, 720));

        //ArrayList<Stock> contenant tous les aliments, quantités et autres
        //attributs qui se trouvent actuellement en stock
        alStock = stcStockTabController.getAllStock();
        Vector<Vector<Object>> vStock = new Vector<>();

        //Parcours de tous les objets de l'ArrayList et utilisation de la méthode
        //toVector de l'objet Stock pour les mettre dans un objet de type Vector.
        //Puis, ajout de l'objet Vector<Stock> dans un Vector<Vector<Objet>>
        for(Stock sStock : alStock){
            vStock.add(sStock.toVector());
        }

        //Instanciations d'un objet MyModelTable, d'un object JTable et d'un object
        //TableRowSorter. Le premier sera utilisé comme paramètre pour l'instanciation
        //des deux autres. La JTable permet d'afficher les différentes aliments en stock
        //à l'écran sous-forme de tableau et le TableRowSorter permet de cliquer sur les
        //en-têtes afin de les trier.
        //C'est la JTable qui affiche tout le stock en place
        mmtStock = new MyModelTable(vStock, COLUMN_STOCK_NAME, COLUMN_STOCK_EDITABLE);
        jtTableStock = new JTable((mmtStock));
        trsSorter = new TableRowSorter<>(mmtStock);
        jtTableStock.setRowSorter(trsSorter);
        //Instanciation de MyRenderer qui est le contrôleur de cellules qui va permettre
        //de mettre la cellule en rouge quand l'utilisateur va rentrer une mauvaise donnée
        mrRendererStock = new MyRenderer(mmtStock, COLUMN_QUANTITY);

        //L'objet Dimension d permet de connaitre la largeur de la JTable afin de pouvoir
        //affecter la bonne dimension au Viewport.
        Dimension d = jtTableStock.getPreferredScrollableViewportSize();
        d.width = jtTableStock.getPreferredSize().width;
        jtTableStock.setPreferredScrollableViewportSize(d);

        //Instanciation d'un panel JScroll, puis l'affecte à la taille qu'on préfère.
        JScrollPane jspStock = new JScrollPane(jtTableStock);
        jspStock.setPreferredSize(new Dimension(700, 700));

        //Ajout du panel JScroll au panel qui contient la JTable
        jpTableStock.add(jspStock);
        /***********************************************************************************/
        //Instanciation du GridBagLayout et du GridBagConstraints du panel principal de droite,
        //donc celui de l'historique des commandes.
        GridBagLayout gblRight = new GridBagLayout();
        GridBagConstraints gbcRight  = new GridBagConstraints();
        //Instanciation du panel principal de droite et on lui "set" un layout.
        JPanel jpRight = new JPanel();
        jpRight.setLayout(gblRight);
        //Ajout du panel principal de gauche dans le panel principal
        jpMainPanel.add(jpRight);
        /***********************************************************************************/
        //Instanciation du JPanel qui contient le titre et ajout de celui-ci dans
        //le JPanel principal de droite.
        JPanel jpRightTitle = new JPanel();
        //Instanciation du titre de droite
        JLabel jlOrder = new JLabel("Commandes");
        setTitleConfig(jlOrder);
        jpRightTitle.add(jlOrder);
        //Ajout du JPanel jpRightTitle dans le panel principal de droite.
        gbcRight.fill = GridBagConstraints.CENTER;
        gbcRight.gridx = 0;
        gbcRight.anchor = GridBagConstraints.NORTH;
        gbcRight.gridy = 0;
        jpRight.add(jpRightTitle,gbcRight);
        /***********************************************************************************/
        //Instanciation du JPanel qui contient le bouton Recherche, les deux JDatePickerImpl et
        //la JComboBox
        JPanel jpHistoryOrder = new JPanel();
        //Ajout du JPanel jpHistoryOrder dans le panel principal de droite.
        gbcRight.gridx = 0;
        gbcRight.gridy = 1;
        jpRight.add(jpHistoryOrder, gbcRight);
        //Instanciation du bouton Recherche
        JButton jbSearch = new JButton("Recherche");
        setButtonConfig(jbSearch);
        //Instanciation de l'objet Properties et configuration de certains
        //attributs
        Properties pStartProperties = new Properties();
        pStartProperties.put("text.today", "Aujourd'hui");
        pStartProperties.put("text.month", "Mois");
        pStartProperties.put("text.year", "Année");
        //Instanciation des deux modèles qui serviront de paramètre afin d'instancier les
        //JDatePanelImpl
        SqlDateModel sdmModelStartDate = new SqlDateModel();
        SqlDateModel sdmModelEndDate = new SqlDateModel();
        //Instanciation du JDatePanelImpl avec le modèle et les propriétés instanciées ci-dessus.
        JDatePanelImpl jdpiStartDatePanel = new JDatePanelImpl(sdmModelStartDate, pStartProperties);
        jdpiStartDatePanel.setPreferredSize(new Dimension(200, 200));
        //Instanciation d'un JDatePickerImpl, c'est la fenêtre avec un calendrier afin de pouvoir
        //choisir une date
        JDatePickerImpl jdpriStartDatePicker = new JDatePickerImpl(jdpiStartDatePanel, new DateLabelFormatter());

        //Instanciation de l'objet Properties et configuration de certains
        //attributs
        Properties pEndProperties = new Properties();
        pEndProperties.put("text.today", "Aujourd'hui");
        pEndProperties.put("text.month", "Mois");
        pEndProperties.put("text.year", "Année");
        //Instanciation du JDatePanelImpl avec le modèle et les propriétés instanciées ci-dessus.
        JDatePanelImpl jdpiEndDatePanel = new JDatePanelImpl(sdmModelEndDate, pEndProperties);
        jdpiEndDatePanel.setPreferredSize(new Dimension(200, 200));
        //Instanciation d'un JDatePickerImpl, c'est la fenêtre avec un calendrier afin de pouvoir
        //choisir une date
        JDatePickerImpl jdpriEndDatePicker = new JDatePickerImpl(jdpiEndDatePanel, new DateLabelFormatter());
        JLabel jlStartDate = new JLabel("De: ");
        JLabel jlEndDate = new JLabel("A: ");

        //Instanciation d'un GridBagLayout et d'un GridBagConstraints pour le panel jpHistoryOrder
        //qui contient le bouton de Rechercher, les JDatePickerImpl et la JComboBox,
        GridBagLayout gblOrderButton = new GridBagLayout();
        jpHistoryOrder.setLayout(gblOrderButton);
        GridBagConstraints gbcOrderButton = new GridBagConstraints();

        gbcOrderButton.insets = new Insets(15, 10, 15, 10);
        //Ajout et placement du bouton jbSearch dans le panel jpHistoryOrder.
        gbcOrderButton.gridx = 0;
        gbcOrderButton.gridy = 0;
        jpHistoryOrder.add(jbSearch, gbcOrderButton);

        //Ajout et placement du JLabel "De" dans le panel jpHistoryOrder.
        gbcOrderButton.gridx = 1;
        gbcOrderButton.gridy = 0;
        jpHistoryOrder.add(jlStartDate);

        //Ajout et placement du composant JDatePickerImpl dans le panel jpHistoryOrder.
        gbcOrderButton.gridx = 2;
        gbcOrderButton.gridy = 0;
        jpHistoryOrder.add(jdpriStartDatePicker, gbcOrderButton);

        //Ajout et placement du JLabel "A" dans le panel jpHistoryOrder.
        gbcOrderButton.gridx = 3;
        gbcOrderButton.gridy = 0;
        jpHistoryOrder.add(jlEndDate);

        //Ajout et placement du composant JDatePickerImpl dans le panel jpHistoryOrder.
        gbcOrderButton.gridx = 4;
        gbcOrderButton.gridy = 0;
        jpHistoryOrder.add(jdpriEndDatePicker, gbcOrderButton);

        //Ajout et placement du JComboBox des différents statuts des commandes dans le panel jpHistoryOrder.
        JComboBox jcbOrderStatus = new JComboBox(ORDER_STATUS);
        gbcOrderButton.gridx = 5;
        gbcOrderButton.gridy = 0;
        jpHistoryOrder.add(jcbOrderStatus, gbcOrderButton);
        /***********************************************************************************/
        //Instanciation du JPanel qui contient la JTable et ajout de celui-ci dans
        //le JPanel principal de droite.
        JPanel jpTableOrder = new JPanel();
        //Ajout du JPanel jpTableOrder dans le panel principal de droite.
        gbcRight.gridx = 0;
        gbcRight.gridy = 2;
        gbcRight.weighty = 20;

        jpTableOrder.setPreferredSize(new Dimension(800, 720));

        //Requête pour aller chercher toutes les commandes qui se trouvent
        //dans la base de donnée
        alCommandeHistory = stcStockTabController.getAllCommandeHistory();
        Vector<Vector<Object>> vCommandeHistory = new Vector<>();

        //Parcours de tous les objets de alCommandeHistory et utilisation de la méthode
        //toVector de l'objet Commande pour les mettre dans un objet de type Vector.
        //Puis, ajout de l'objet Vector<Commande> dans un Vector<Vector<Objet>>
        for(Commande cHistory : alCommandeHistory){
            vCommandeHistory.add(cHistory.toVector());
        }

        //Instantiations d'un objet MyModelTable, d'un object JTable et d'un object
        //TableRowSorter. Le premier sera utilisé comme paramètre pour l'instanciation
        //des deux autres. La JTable permet d'afficher les différentes aliments en stock
        //à l'écran sous-forme de tableau et le TableRowSorter permet de cliquer sur les
        //en-têtes afin de les trier.
        //C'est la JTable qui affiche l'historique des commandes
        mmtCommandeHistory = new MyModelTable(vCommandeHistory, COLUMN_HISTORY_NAME);
        jtTableCommandeHistory = new JTable(mmtCommandeHistory);

        //Instantiation de MyRenderer qui est le contrôleur de cellules qui va permettre
        //de mettre la cellule en rouge quand l'utilisateur va rentrer une mauvaise donnée
        mrRendererOrder = new MyRenderer(mmtCommandeHistory, COLUMN_QUANTITY);

        //L'objet Dimension d permet de connaître la largeur de la JTable afin de pouvoir
        //affecter la bonne dimension au Viewport.
        Dimension d1 = jtTableCommandeHistory.getPreferredScrollableViewportSize();
        d1.width = jtTableCommandeHistory.getPreferredSize().width;
        jtTableCommandeHistory.setPreferredScrollableViewportSize(d1);

        //Instantiation d'un panel JScroll, puis l'affecte à la taille qu'on préfère.
        JScrollPane jspStock1 = new JScrollPane(jtTableCommandeHistory);
        jspStock1.setPreferredSize(new Dimension(700, 700));

        //Ajout du panel JScroll au panel qui contient la JTable puis ajout de ce dernier
        //dans le panel principal de droite.
        jpTableOrder.add(jspStock1);
        jpRight.add(jpTableOrder, gbcRight);
        /***********************************************************************************/
        // -----------------------------------------------------------------------------------------------------------------
        // -----------------------------------------------------------------------------------------------------------------
        // Partie pour la gestion des évènements des boutons

        //Ajout d'un ActionListener au bouton Imprimer
        jbPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel jlPrintTitle = new JLabel("Stock");
                String sAdditionalTest = "";
                //Création d'un fichier pdf du stock
                new PrintPDF(jtTableStock, jlPrintTitle, sAdditionalTest);
            }
        });

        //Ajout d'un ActionListener au bouton Commande
        jbOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Vector<Vector<Object>> vOrder = new Vector<>();
                final String NO_DATA_IN_CELLS = new String("Impossible de créer une nouvelle commande car toutes les commandes sont à 0!!");
                boolean dataOK = true;

                //Parcours de la JTable contenant tous les aliments du stock et affichée
                //dans la partie gauche de l'interface stock
                for(int i = 0; i < jtTableStock.getRowCount(); ++i){

                    double temp;
                    //On affecte la valeur 0 si la cellule est vide. Cela évite un NullStackPointer.
                    //Sinon on affecte la valeur dans la cellule
                    if(jtTableStock.getValueAt(i, COLUMN_QUANTITY) == null){
                        temp = 0;
                    }else{
                        temp = (double) jtTableStock.getValueAt(i, COLUMN_QUANTITY);
                    }

                    //Vérifie si la valeur est un double positif. Si c'est le cas, cela l'ajoute
                    //à vOrder
                    if(stcStockTabController.isNumericPositiveDouble(temp)){
                        mmtStock.setCellStatus(CellStatus.EMPTY, i, COLUMN_QUANTITY);
                        Stock sStock = new Stock(alStock.get(i));
                        sStock.setCommande((double)jtTableStock.getValueAt(i, COLUMN_QUANTITY));
                        vOrder.add(sStock.toVectorForOrder());

                        //Vérifie si la valeur est inférieur à 0. Si c'est le cas, cela colorie
                        //la cellule en rouge
                    }else if(stcStockTabController.isNumericAndBelowZero(temp)){
                        jtTableStock.getColumnModel().getColumn(COLUMN_QUANTITY).setCellRenderer(mrRendererStock);
                        mmtStock.setCellStatus(CellStatus.RED, i, COLUMN_QUANTITY);
                        dataOK = false;
                    }
                }
                //Si toutes les données sont valides et que vOrder n'est pas vide,
                //cela créé une nouvelle commande
                if(dataOK && !vOrder.isEmpty()){
                    new NewOrderController(vOrder, mmtCommandeHistory);

                    //Si toutes les données sont valides mais que vOrder est vide,
                    //cela signifie qu'aucune quantité n'a été ajoutée
                }else if(dataOK && vOrder.isEmpty()){
                    new InformationController(NO_DATA_IN_CELLS);
                }

            }
        });

        //Ajout d'un ActionListener au bouton Ajouter
        jbAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<Object>> vAdd = new Vector<>();
                //Parcours les aliments afin de faire le Vector<Vector<Objet>> vAdd
                //qui est utilisé pour instancier AddStockController
                for(Stock sStock : alStock){
                    vAdd.add(sStock.toVectorAddDel());
                }

                new AddStockController(vAdd, jtTableStock);
            }
        });

        //Ajout d'un ActionListener au bouton Supprimer
        jbDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<Object>> vDelete = new Vector<>();
                //Parcours les aliments afin de faire le Vector<Vector<Objet>> vDelete
                //qui est utilisé pour instancier DeleteStockController
                for(Stock sStock : alStock){
                    vDelete.add(sStock.toVectorAddDel());
                }

                new DeleteStockController(vDelete, jtTableStock);
            }
        });

        //Ajout d'un ActionListener au bouton Effacer
        jbReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final int COLUMN_SELECTED = 5;
                final int DEFAULT_NUMBER_QUANTITY = 0;
                //Parcours de la colonne Commande dans la JTable de gauche afin de mettre tous les
                //nombres à zéro
                for(int i = 0; i < jtTableStock.getRowCount(); ++i){
                    jtTableStock.setValueAt(new Double(DEFAULT_NUMBER_QUANTITY), i, COLUMN_SELECTED);
                }
            }
        });

        //Ajout d'un ActionListener au bouton Recherche
        jbSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                Vector<Vector<Object>> vCommandeHistory = new Vector<>();
                //Récupération des valeurs des JDatePickerImpl
                Date dStartDate = sdmModelStartDate.getValue();
                Date dEndDate = sdmModelEndDate.getValue();

                //Ajout de 86400000 ce qui représente un jour en Timestamp.
                //Cela permet de pouvoir faire une recherche jusqu'à x date,
                //avec la date x incluse.
                if(dEndDate != null){
                    dEndDate.setTime(dEndDate.getTime() + 86400000);
                }

                //Affecte la valeurs dans la JComboBox
                Statut sStatus = (Statut)jcbOrderStatus.getSelectedItem();
                int total = 0;
                boolean dateOK = true;

                try{
                    //On regarde les valeurs des JDatePickerImpl et si elles ne sont pas nulles,
                    //on additionne le 1 et/ou 2 à total. Idem pour statut s'il n'est pas égal
                    //à 4, on additionne 4 à total. Cela permet de savoir un peu plus bas, quel
                    //case du switch choisir. En gros qu'elle requête effectuéer dans la base
                    //de donnée.
                    if(dStartDate != null){
                        jdpriStartDatePicker.getJFormattedTextField().setBackground(Color.WHITE);
                        total += 1;
                    }

                    if(dEndDate != null){
                        jdpriEndDatePicker.getJFormattedTextField().setBackground(Color.WHITE);
                        total += 2;
                    }

                    if(!sStatus.equals(Statut.TOUS)){
                        total += 4;
                    }

                    //Permet de choisir la requête à effectuer ou les cellules à mettre en rouge
                    switch (total){
                        case 0:
                            alCommandeHistory = stcStockTabController.getAllCommandeHistory();
                            break;
                        case 1:
                            jdpriEndDatePicker.getJFormattedTextField().setBackground(Color.RED);
                            dateOK = false;
                            break;
                        case 2:
                            jdpriStartDatePicker.getJFormattedTextField().setBackground(Color.RED);
                            dateOK = false;
                            break;
                        case 3:
                            if(dStartDate.getTime() <= dEndDate.getTime()){
                                if(isDate(dStartDate) && isDate(dEndDate)){
                                    alCommandeHistory = stcStockTabController.getOrderByDate(dStartDate, dEndDate);
                                }
                            }else{
                                jdpriStartDatePicker.getJFormattedTextField().setBackground(Color.RED);
                                jdpriEndDatePicker.getJFormattedTextField().setBackground(Color.RED);
                                dateOK = false;
                            }
                            break;
                        case 4:
                            alCommandeHistory = stcStockTabController.getOrderByStatus(sStatus);
                            break;
                        case 5:
                            jdpriEndDatePicker.getJFormattedTextField().setBackground(Color.RED);
                            dateOK = false;
                            break;
                        case 6:
                            jdpriEndDatePicker.getJFormattedTextField().setBackground(Color.RED);
                            dateOK = false;
                            break;
                        case 7:
                            if(dStartDate.getTime() <= dEndDate.getTime()){
                                if(isDate(dStartDate) && isDate(dEndDate)){
                                    alCommandeHistory = stcStockTabController.getOrderByStatusAndDate(sStatus, dStartDate, dEndDate);
                                }
                            }else{
                                jdpriStartDatePicker.getJFormattedTextField().setBackground(Color.RED);
                                jdpriEndDatePicker.getJFormattedTextField().setBackground(Color.RED);
                                dateOK = false;
                            }
                            break;
                        default:
                    }

                    //Si les données sont bonnes dateOK est true et cela va changer la JTable de droite
                    //en fonction des valeurs qu'on a obtenu après la requête.
                    if(dateOK){
                        for(Commande cHistory : alCommandeHistory){
                            vCommandeHistory.add(cHistory.toVector());
                        }

                        int rowCount = jtTableCommandeHistory.getRowCount();
                        for(int i = rowCount - 1; i >= 0; --i){
                            mmtCommandeHistory.removeRow(i);
                        }

                        for(int i = 0; i < vCommandeHistory.size(); ++i){
                            mmtCommandeHistory.addRow(vCommandeHistory.elementAt(i));
                        }
                    }
                }catch (ExceptionDataBase e) {
                    if(e.getMsg().equals("La table Commande est vide.")){
                        int rowCount = jtTableCommandeHistory.getRowCount();
                        for(int i = rowCount - 1; i >= 0; --i){
                            mmtCommandeHistory.removeRow(i);
                        }
                        new InformationController("Aucune commande avec ses spécificités n'a été trouvée.");
                    }else{
                        new ErrorController(e.getMsg());
                    }

                } catch (SQLException e) {
                    new ErrorController(e.getMessage());
                } catch (Exception e){
                    new ErrorController(e.getMessage());
                }

            }
        });

        //Ajout d'un MouseMotionListener à la JTable de gauche,
        //celle du stock.
        jtTableStock.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                Point p = e.getPoint();
                int rowIndex = jtTableStock.rowAtPoint(p);
                int columnIndex = jtTableStock.columnAtPoint(p);

                //Affiche le texte ci-dessous quand la souris passe sur la colonne avec
                //l'index 5, sinon affiche un texte vide.
                if (rowIndex > 0 && columnIndex == 5) {
                    jtTableStock.setToolTipText("Seuls les nombres positifs sont acceptés dans la colonne commande");
                }else{
                    jtTableStock.setToolTipText("");
                }
            }

        });

        //Ajout d'un MouseListener au bouton à la JTable de droite,
        //celle des commandes
        jtTableCommandeHistory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final int CLICK_COUNT = 2;

                //Instancie un objet OrderContentController si un double-clique est fait rapidement sur
                //la même cellule de la JTable
                if(e.getClickCount() == CLICK_COUNT){
                    new OrderContentController(mmtCommandeHistory.getValueAtRow(jtTableCommandeHistory.getSelectedRow()), mmtCommandeHistory);
                }
            }
        });

        //Méthode de la GenericWindow qui permet d'ajouter le jpMainPanel dans la JFrame et d'afficher
        //le tout à l'écran
        configFrame(getJfFrame(), this);

    }

    /**
     * Méthode permettant de vérifier si l'objet passé en paramètre est une date.
     * Elle fait appel à la méthode isDate de StockTabController
     * @param date
     * @return true si la valeur est une date plausible
     */
    public boolean isDate(Date date) {
        return stcStockTabController.isDate(date);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // Les getters et les setters

    /**
     * Méthode qui retourne une référence du tableau de String contenant le nom des en-têtes de la JTable
     * qui contient les aliments et leurs attributs
     * @return une référence sur le tableau de String
     */
    public String[] getCOLUMN_STOCK_NAME(){
        return COLUMN_STOCK_NAME;
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
     * Méthode qui retourne une référence du tableau de String contenant le nom des en-têtes de la JTable
     * qui contient les commandes et certains de leurs attributs
     * @return une référence sur le tableau de String
     */
    public String[] getCOLUMN_HISTORY_NAME(){
        return COLUMN_HISTORY_NAME;
    }

    /**
     * Méthode qui retourne une référence du tableau de Statut. C'est les différents statuts que peuvent
     * avoir les commandes
     * @return une référence sur le tableau de Statut
     */
    public Statut[] getORDER_STATUS(){
        return ORDER_STATUS;
    }

    /**
     * Méthode qui retourne l'index de la colonne où l'utilisateur rentre les quantités qu'il désire
     * afin de créer une nouvelle commande
     * @return l'index de la colonne
     */
    public int getCOLUMN_QUANTITY(){
        return COLUMN_QUANTITY;
    }

    /**
     * Méthode qui retourne la référence de l'objet mmtStock de type MyModelTable créée pour être le paramètre
     * de la JTable jtTableStock et l'objet TableRowSorter trsSorter lors de leurs instanciations respectives
     * @return une référence sur l'objet mmtStock.
     */
    public MyModelTable getMmtStock(){
        return mmtStock;
    }

    /**
     * Méthode qui permet d'affecter une référence de l'objet MyModelTable passé en paramètre à l'attribut privé
     * mmtStock de l'objet StockTab.
     * @param mmtStock
     */
    public void setMmtStock(MyModelTable mmtStock){
        this.mmtStock = mmtStock;
    }

    /**
     * Méthode qui retourne la référence de l'objet mmtCommandeHistory de type MyModelTable créée pour être le paramètre
     * de la JTable jtTableCommandeHistory et l'objet TableRowSorter trsSorter lors de leurs instanciations respectives
     * @return une référence sur l'objet mmtCommandeHistory.
     */
    public MyModelTable getMmtCommandeHistory(){
        return mmtCommandeHistory;
    }

    /**
     * Méthode qui permet d'affecter une référence de l'objet MyModelTable passé en paramètre à l'attribut privé
     * mmtCommandeHistory de l'objet StockTab.
     * @param mmtCommandeHistory
     */
    public void setMmtCommandeHistory (MyModelTable mmtCommandeHistory){
        this.mmtCommandeHistory = mmtCommandeHistory;
    }

    /**
     * Méthode qui retourne la référence de l'objet jtTableStock de type JTable qui sera utilisé pour être affiché
     * dans cette interface
     * @return une référence sur l'objet jtTableStock
     */
    public JTable getJtTableStock(){
        return jtTableStock;
    }

    /**
     * Méthode qui permet d'affecter la référence de la JTable passée en paramètre à l'attribut privé jtTableStock
     * de l'objet StockTab
     * @param jtTableStock
     */
    public void setJtTableStock(JTable jtTableStock){
        this.jtTableStock = jtTableStock;
    }

    /**
     * Méthode qui retourne la référence de l'objet jtTableCommandeHistory de type JTable qui sera utilisé pour être
     * affiché dans cette interface
     * @return une référence sur l'objet jtTableCommandeHistory
     */
    public JTable getJtTableCommandeHistory(){
        return jtTableCommandeHistory;
    }

    /**
     * Méthode qui permet d'affecter la référence de la JTable passée en paramètre à l'attribut privé jtTableCommandeHistory
     * de l'objet StockTab
     * @param jtTableCommandeHistory
     */
    public void setJtTableCommandeHistory(JTable jtTableCommandeHistory){
        this.jtTableCommandeHistory = jtTableCommandeHistory;
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
}