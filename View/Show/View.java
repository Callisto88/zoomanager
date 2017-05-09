/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Show;


import Model.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

/**
 *
 * @author doriane kaffo
 */
public class View extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAnimation;
    private javax.swing.JComboBox choiceAnimation;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    private int io = 0;
    private String TypeLast = "";
    private DBInteraction query = null;
    /**
     * Creates new form view
     */
    public View() {
        initComponents();
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
        initValue();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Content init dash">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        choiceAnimation = new javax.swing.JComboBox();
        addAnimation = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jLabel1.setText("Choix de l'animation :");

        choiceAnimation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        choiceAnimation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choiceAnimationActionPerformed(evt);
            }
        });

        addAnimation.setText("Ajouter une Animation");
        addAnimation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAnimationActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Planning", "Personnel", "Animaux"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable1PropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Ajouter une date a l'animation");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(choiceAnimation, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addAnimation, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(jButton1)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(choiceAnimation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addAnimation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(13, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addAnimationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAnimationActionPerformed
        // TODO add your handling code here:
        final JDialog dg = new JDialog();
        dg.setTitle("Ajouter une animation");
        dg.setSize(new Dimension(410,150));
        final DialogNewAnimation pan = new DialogNewAnimation();
        dg.setLocation(300+this.getWidth()/2 - dg.getWidth()/2, 20+this.getHeight()/2 - dg.getHeight()/2);
        dg.add(pan);
        dg.setResizable(false);
        dg.setVisible(true);
        ActionListener n = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt = pan.getjTextField1().getText();
                int c = choiceAnimation.getModel().getSize();
                String lst = "";
                //choiceAnimation.addItem(txt);
                System.out.println("Ajout du type evenement "+txt);
                try {
                    query.insertTypeEvenement(new TypeEvenement(txt));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                TypeLast = txt;
                initValue();
                dg.setVisible(false);
                
            }
        
        };
        pan.getjToggleButton1().addActionListener(n);
        pan.getjToggleButton2().addActionListener(n);
    }//GEN-LAST:event_addAnimationActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        final JDialog dg = new JDialog();
        dg.setTitle("Planifier une animation");
        dg.setSize(new Dimension(520,360));
        final DialogNewAnimationPlaning pan = new DialogNewAnimationPlaning();
        dg.setLocation(300+this.getWidth()/2 - dg.getWidth()/2, 20+this.getHeight()/2 - dg.getHeight()/2);
        dg.add(pan);
        if(TypeLast.length()>0){
            dg.setResizable(false);
            dg.setVisible(true);
        }else{
            new JOptionPane().showMessageDialog(null, "Il faut dabord choisir un type d'animation", "Warning",
                JOptionPane.INFORMATION_MESSAGE);  
        }
        ActionListener n = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dg.setVisible(false);
                Evenement evt = new Evenement(
                    0,
                    pan.getDescription().getText(),
                        Timestamp.valueOf(pan.getDateInput().getText()),
                    TypeLast
                );

                try {
                    query.insertEvenement(evt);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                initValue();
            }
        };
        ActionListener n2 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dg.setVisible(false);
            }
        };
        pan.getAddButton().addActionListener(n);
        pan.getCancelButton().addActionListener(n2);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void choiceAnimationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choiceAnimationActionPerformed
        // TODO add your handling code here:
        String c = choiceAnimation.getSelectedItem().toString();
        if(c.equalsIgnoreCase("Tout"))
            this.TypeLast = "";
        else
            this.TypeLast = c;
        this.initValue();
    }//GEN-LAST:event_choiceAnimationActionPerformed

    private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange
        // TODO add your handling code here:
        
        System.out.println("change property "+evt.getPropertyName());
    }//GEN-LAST:event_jTable1PropertyChange


    private void initValue() {
        ArrayList<String> listType = null;
        try {
            listType = query.selEventTypes();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
        if(listType==null)
            listType = new ArrayList<String>();
        String[] listeAnim = new String[listType.size()+1];
        
        listeAnim[0] = "Tout";
        for(int i=1;i<listType.size()+1;i++){
            listeAnim[i] = listType.get(i-1);
        }
        choiceAnimation.setModel(new javax.swing.DefaultComboBoxModel(listeAnim));
        if(this.TypeLast.length()>0)
            choiceAnimation.setSelectedItem(this.TypeLast);

        ArrayList<Evenement> list = null;
        if(this.TypeLast.length()>0)
            try {
                list = query.selEventsByEventType(this.TypeLast);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ExceptionDataBase exceptionDataBase) {
                exceptionDataBase.printStackTrace();
            }
        else {
            try {
                try {
                    list = query.selAllEvents();
                } catch (ExceptionDataBase exceptionDataBase) {
                    exceptionDataBase.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(list==null)
            list = new ArrayList<Evenement>();
        final Object[][] content1 = new Object[list.size()][];
        System.out.print(list.size()+"   --   ");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
            String anim = "none";//list.get(i).stringAnimal();
            String pers = "none";//list.get(i).stringPerson();
            content1[i]=(new Object[]{list.get(i).getDate(),pers,anim});
        }
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                content1
            ,
            new String [] {
                "Planning", "Personnel", "Animaux"
            }
        ));

        ArrayList<Evenement> finalList = list;
        jTable1.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                int col = jTable1.columnAtPoint(e.getPoint());
                final int row = jTable1.rowAtPoint(e.getPoint());
                String name = jTable1.getColumnName(col);
                System.out.println("Column index selected " + row + " " + col + " " + name);
                 final JDialog dg = new JDialog();
                dg.setTitle("Modifier une plannification");
                dg.setSize(new Dimension(565,350));
                final DialogUpdateAnimation pan = new DialogUpdateAnimation();
                dg.setLocation(300+530/2 - dg.getWidth()/2, 20+550/2 - dg.getHeight()/2);
                dg.add(pan);
                pan.setLocation(300+520/2 - pan.getWidth()/2, 20+550/2 - pan.getHeight()/2);

                ArrayList<Animal> listA = null;
                try {
                    listA = query.selAnimaux();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ExceptionDataBase exceptionDataBase) {
                    exceptionDataBase.printStackTrace();
                }
                if(listA==null)
                    listA = new ArrayList<Animal>();
                String[] animaux = new String[listA.size()];
                for(int i =0;i<listA.size();i++)
                    animaux[i] = listA.get(i).getNom()+
                            " / "+
                            listA.get(i).getRace();
                pan.getAnimauxAll().setModel(new javax.swing.DefaultComboBoxModel(animaux));

                List<Animal> listA2 = null;
                try {
                    listA2 = query.selAnimalsByEventID(finalList.get(row).getId());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ExceptionDataBase exceptionDataBase) {
                    exceptionDataBase.printStackTrace();
                }
                if(listA2==null)
                    listA2 = new ArrayList<Animal>();
                String[] animaux2 = new String[listA2.size()];
                for(int i =0;i<listA2.size();i++)
                    animaux2[i] = listA2.get(i).getNom()+
                            " / "+
                            listA2.get(i).getRace();
                pan.getAnimaux().setModel(new javax.swing.DefaultComboBoxModel(animaux2));

                ArrayList<Personne> listP = null;
                try {
                    listP = query.selAllEmployes();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ExceptionDataBase exceptionDataBase) {
                    exceptionDataBase.printStackTrace();
                }
                if(listP==null)
                    listP = new ArrayList<Personne>();
                String[] personnes = new String[listP.size()];
                for(int i =0;i<listP.size();i++)
                    personnes[i] = listP.get(i).getNom()+" "+listP.get(i).getNom();
                pan.getPersonAll().setModel(new javax.swing.DefaultComboBoxModel(personnes));


                List<Personne> listP2 = null;
                try {
                    listP2 = query.selEmployeesByEventID(finalList.get(row).getId());
                } catch (ExceptionDataBase exceptionDataBase) {
                    exceptionDataBase.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if(listP2==null)
                    listP2 = new ArrayList<Personne>();
                String[] personnes2 = new String[listP2.size()];
                for(int i =0;i<listP2.size();i++)
                    personnes2[i] = listP2.get(i).getNom()+" "+listP2.get(i).getNom();
                pan.getPersons().setModel(new javax.swing.DefaultComboBoxModel(personnes2));
                
                
                dg.setResizable(false);
                dg.setVisible(true);
                pan.getNameAnimation().setText(finalList.get(row).getType());
                pan.getDateAnimation().setText(finalList.get(row).getDate().toString());
                ActionListener n = new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        initValue();
                        pan.setVisible(false);
                    }
                };
                ActionListener deletePlaning = new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        try {
                            if(query.delEventByID(finalList.get(row).getId())){
                                dg.setVisible(false);
                                initValue();
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        } catch (ExceptionDataBase exceptionDataBase) {
                            exceptionDataBase.printStackTrace();
                        }
                    }
                };
                ArrayList<Animal> finalListA1 = listA;
                ActionListener addAnimal = new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        int animalSelected = pan.getAnimauxAll().getSelectedIndex();
                        Animal an = finalListA1.get(animalSelected);
                        try {
                            if (query.insAnimalEvent(an.getId(), finalList.get(row).getId())!=0) {
                                pan.getAnimaux().addItem(an.getNom() + "-" + an.getRace());
                                initValue();
                            } else {
                                return;
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                };

                ArrayList<Animal> finalListA = listA;
                ActionListener delAnimal = new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        int animalSelected = pan.getAnimauxAll().getSelectedIndex();
                        Animal an = finalListA.get(animalSelected);
                        try {
                            if(query.delAnimalEvent(an.getId(), finalList.get(row).getId())){
                             pan.getAnimaux().removeItem(an.getNom()+"-"+an.getRace());
                             initValue();
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                };


                ArrayList<Personne> finalListP = listP;
                ActionListener addPerson = new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        int personSelected = pan.getPersonAll().getSelectedIndex();
                        Personne an = finalListP.get(personSelected);
                        try {
                            if(query.insAnimalEvent(an.getIdPersonne(), finalList.get(row).getId()) != 0){
                             pan.getPersons().addItem(an.getNom()+"-"+an.getNom());
                             initValue();
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                };

                ArrayList<Personne> finalListP1 = listP;
                ActionListener delPerson = new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        int personSelected = pan.getPersonAll().getSelectedIndex();
                        Personne an = finalListP1.get(personSelected);
                        try {
                            if(query.delPersonEvent(an.getIdPersonne(), finalList.get(row).getId())){
                             pan.getPersons().removeItem(an.getNom()+"-"+an.getNom());
                             initValue();
                            }
                        } catch (ExceptionDataBase exceptionDataBase) {
                            exceptionDataBase.printStackTrace();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                };
                
                pan.getRemoveAnimal().addActionListener(delAnimal);
                pan.getAddAnimal().addActionListener(addAnimal);
                pan.getUpdate().addActionListener(n);
                pan.getDelete().addActionListener(deletePlaning);
            }
         });
    }
}
