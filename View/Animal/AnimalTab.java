package View.Animal;

import Model.Animal;
import Model.Felin;
import Model.Oiseau;
import Model.Primate;
import Model.Race;
import Model.Reptile;
import Model.Enclos;
import Model.Animal_Evenement;
import Model.DBInteraction;
import View.DateLabelFormatter;
import View.GenericWindow;
import View.MyModelTable;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Properties;
import java.util.Vector;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

/**
 * Created by Andre on 10.03.2017.
 */
public class AnimalTab extends GenericWindow {
    private String[] columnName = {"Nom", "Race", "Sexe", "Age"};

    public AnimalTab(){
        super("Animaux");

        GridBagLayout gblLeft = new GridBagLayout();
        GridBagConstraints gbcLeft  = new GridBagConstraints();

        JPanel jpLeft = new JPanel();
        jpLeft.setLayout(gblLeft);

        JPanel jpLeftTitle = new JPanel();

        jpMainPanel.add(jpLeft);

        JLabel jlStock = new JLabel("Liste des animaux");
        setTitleConfig(jlStock);
        jpLeftTitle.add(jlStock);

        gbcLeft.fill = GridBagConstraints.CENTER;
        gbcLeft.gridx = 0;
        gbcLeft.anchor = GridBagConstraints.NORTH;
        gbcLeft.gridy = 0;
        //gbcLeft.insets = new Insets(15,15,15,15);
        jpLeft.add(jpLeftTitle,gbcLeft);

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 1;


        JPanel jpButtonAnimal = new JPanel();
        jpLeft.add(jpButtonAnimal, gbcLeft);

        JButton jbPrint = new JButton("Imprimer");
        setButtonConfig(jbPrint);


        GridBagLayout gblButtonAnimal = new GridBagLayout();
        jpButtonAnimal.setLayout(gblButtonAnimal);
        GridBagConstraints gbcButtonAnimal = new GridBagConstraints();

        gbcButtonAnimal.insets = new Insets(15, 50, 15, 50);
        gbcButtonAnimal.gridx = 0;
        gbcButtonAnimal.gridy = 0;
        jpButtonAnimal.add(jbPrint, gbcButtonAnimal);


        /**************************************************************/

        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        gbcLeft.weighty = 20;


        JPanel jpTableAnimal = new JPanel();
        jpTableAnimal.setPreferredSize(new Dimension(800, 720));

        Animal sTest1 = new Animal();

        Vector<Object> sTest = sTest1.toVector();

        Vector<Vector<Object>> vAnimal = new Vector<>();
        vAnimal.add(sTest);

        JTable jtTable = new JTable(new MyModelTable(vAnimal, columnName));

        Dimension d = jtTable.getPreferredScrollableViewportSize();
        d.width = jtTable.getPreferredSize().width;
        jtTable.setPreferredScrollableViewportSize(d);

        JScrollPane jspAnimal = new JScrollPane(jtTable);
        jspAnimal.setPreferredSize(new Dimension(700, 700));


        jpTableAnimal.add(jspAnimal);
        jpLeft.add(jpTableAnimal, gbcLeft);


        /***********************************************************************************
         * Partie droite de la fenêtre
         */

        GridBagLayout gblRight = new GridBagLayout();
        GridBagConstraints gbcRight  = new GridBagConstraints();

        JPanel jpRight = new JPanel();
        jpRight.setLayout(gblRight);

        JPanel jpRightTitle = new JPanel();

        jpMainPanel.add(jpRight);

        JLabel jlOrder = new JLabel("Historique Commandes");
        setTitleConfig(jlOrder);
        jpRightTitle.add(jlOrder);

        gbcRight.fill = GridBagConstraints.CENTER;
        gbcRight.gridx = 0;
        gbcRight.anchor = GridBagConstraints.NORTH;
        gbcRight.gridy = 0;
        jpRight.add(jpRightTitle,gbcRight);

        gbcRight.gridx = 0;
        gbcRight.gridy = 1;


        JPanel jpHistoryOrder = new JPanel();
        //jpHistoryOrder.setLayout(new FlowLayout());
        jpRight.add(jpHistoryOrder, gbcRight);

        JButton jbHistory = new JButton("Historique");
        setButtonConfig(jbHistory);


        Properties pStartProperties = new Properties();
        pStartProperties.put("text.today", "Aujourd'hui");
        pStartProperties.put("text.month", "Mois");
        pStartProperties.put("text.year", "Année");

        SqlDateModel sdmModel1 = new SqlDateModel();
        SqlDateModel sdmModel2 = new SqlDateModel();

        JDatePanelImpl jdpliStartDatePanel = new JDatePanelImpl(sdmModel1, pStartProperties);
        jdpliStartDatePanel.setPreferredSize(new Dimension(200, 200));
        JDatePickerImpl jdpriStartDatePicker = new JDatePickerImpl(jdpliStartDatePanel, new DateLabelFormatter());

        Properties pEndProperties = new Properties();
        pEndProperties.put("text.today", "Aujourd'hui");
        pEndProperties.put("text.month", "Mois");
        pEndProperties.put("text.year", "Année");

        JDatePanelImpl jdpliEndDatePanel = new JDatePanelImpl(sdmModel2, pEndProperties);
        jdpliEndDatePanel.setPreferredSize(new Dimension(200, 200));
        JDatePickerImpl jdpriEndDatePicker = new JDatePickerImpl(jdpliEndDatePanel, new DateLabelFormatter());

        JLabel jlStartDate = new JLabel("De: ");
        JLabel jlEndDate = new JLabel("A: ");

        GridBagLayout gblOrderBoutton = new GridBagLayout();
        jpHistoryOrder.setLayout(gblOrderBoutton);
        GridBagConstraints gbcOrderBouton = new GridBagConstraints();

        gbcOrderBouton.insets = new Insets(15, 30, 15, 30);
        gbcOrderBouton.gridx = 0;
        gbcOrderBouton.gridy = 0;
        jpHistoryOrder.add(jbHistory, gbcOrderBouton);


        gbcOrderBouton.gridx = 1;
        gbcOrderBouton.gridy = 0;
        jpHistoryOrder.add(jlStartDate);

        gbcOrderBouton.gridx = 2;
        gbcOrderBouton.gridy = 0;
        jpHistoryOrder.add(jdpriStartDatePicker, gbcOrderBouton);
        //jpButtonOrder.add(jbChooseHistory, gbcOrderBouton);

        gbcOrderBouton.gridx = 3;
        gbcOrderBouton.gridy = 0;
        jpHistoryOrder.add(jlEndDate);

        gbcOrderBouton.gridx = 4;
        gbcOrderBouton.gridy = 0;
        jpHistoryOrder.add(jdpriEndDatePicker, gbcOrderBouton);



        configFrame(getJfFrame(), this);


    }
}
