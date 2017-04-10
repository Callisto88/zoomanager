package View.Staff.StaffMainPanel;

import Controller.Staff.StaffController;
import View.GenericWindow;

import javax.management.Query;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andre on 10.03.2017.
 * Fenêtre principale pour les employée
 */

//public class StaffView extends GenericWindow{
//    StaffController controller = null;
//
//    public StaffView(StaffController controller){
//        super("Staff");
//        this.controller = controller;
//    }
//}
public class StaffView extends GenericWindow {
    private StaffController controller = null;

    /**
     * Constructeur permettant d'instancier toutes les fenêtre composant la fenêtre principale
     *
     * @param persControl Controlleur de la fenêtre pour permettre de lui faire remonter les informations utiles.
     */
    public StaffView(StaffController persControl) {
        super("Personnel");
        controller = persControl;

        this.setLayout(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
        // Liste déroulante avec les noms et prénom des employé
        JComboBox boxChoicePersonne = new JComboBox();
        JPanel choicePersonnel = new JPanel();
        // Requête pour récupérer les nom et prénoms des employé
        boxChoicePersonne.addItem("Guillaume Tell");
        boxChoicePersonne.addItem("Arsène Lupin");
        choicePersonnel.add(boxChoicePersonne);
        top.add(choicePersonnel);
        top.add(Box.createHorizontalStrut(150));

        // Création de notre boutton d'ajout de personnel
        JButton addPersonnelButton = new JButton("Ajout de personnel");
        JPanel addPersonnel = new JPanel();
        addPersonnel.add(addPersonnelButton);
        top.add(addPersonnel);
        this.add(top, BorderLayout.NORTH);

        // permet de demander l'instanciation de la fenêtre d'ajout de personnel
        addPersonnelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addView();
                System.out.println("ajout personnel");
            }
        });

        StaffLabel label = new StaffLabel();
        //this.add(label, BorderLayout.WEST);

        StaffInfo personnel = new StaffInfo();
        //this.add(personnel, BorderLayout.CENTER);

        //FlowLayout test = new FlowLayout(FlowLayout.CENTER, 20, 20);
        //test.addLayoutComponent("label",label);
        //test.addLayoutComponent("info", personnel);

        JPanel test = new JPanel();
        test.add(label, JPanel.RIGHT_ALIGNMENT);
        test.add(personnel, JPanel.LEFT_ALIGNMENT);

        this.add(test, BorderLayout.WEST);

        StaffTask taskView = new StaffTask();
        taskView.setBackground(Color.GREEN);
        this.add(taskView, BorderLayout.CENTER);


        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
        // Bouton de modification
        JButton modify = new JButton("Modification");
        JPanel panelModify = new JPanel();
        panelModify.add(modify);
        bottom.add(modify);
        bottom.add(Box.createHorizontalStrut(150));

        // permet de demander l'instanciation de la fenêtre de modification de personnel
        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("modification personnel");
                controller.modifyView();
            }
        });

        // Bouton d'assignation de taches
        JButton task = new JButton("Ajout de Tâches");
        JPanel taskPanel = new JPanel();
        taskPanel.add(task);
        bottom.add(taskPanel);

        // permet de demander l'instanciation de la fenêtre d'assignation de tâches au personnel
        task.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Assignation taches");
            }
        });

        this.add(bottom, BorderLayout.SOUTH);
    }
}