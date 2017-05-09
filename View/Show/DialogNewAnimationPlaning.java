/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Show;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author doriane kaffo
 */
public class DialogNewAnimationPlaning extends javax.swing.JPanel {

    /**
     * Creates new form DialogNewAnimation
     */
    public DialogNewAnimationPlaning() {
        initComponents();
        person.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jean Jacque", "Orneon loepold", "Brice laroche", "Babel torn" }));
        animal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lion", "Leopard", "Zebre", "Aigle" }));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        dateInput = new javax.swing.JTextField();
        addButton = new javax.swing.JToggleButton();
        cancelButton = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        person = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        animal = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();

        jLabel1.setText("Date de l'animation");

        dateInput.setText("YYYY-MM-DD hh:mm:ss");

        addButton.setText("Ajouter");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Annuler");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Employe en charge");

        person.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Animal reserve");

        animal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        animal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                animalActionPerformed(evt);
            }
        });

        jLabel4.setText("Description");

        description.setColumns(20);
        description.setRows(5);
        jScrollPane1.setViewportView(description);

        jLabel5.setText("Exemple : 2017-04-19 12:30:21");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(dateInput)
                            .addComponent(person, 0, 348, Short.MAX_VALUE)
                            .addComponent(animal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dateInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(person, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(animal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(addButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:  
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void animalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_animalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_animalActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        
    }//GEN-LAST:event_addButtonActionPerformed

    public JComboBox getjComboBox1() {
        return person;
    }

    public void setjComboBox1(JComboBox jComboBox1) {
        this.person = jComboBox1;
    }

    public JComboBox getjComboBox2() {
        return animal;
    }

    public void setjComboBox2(JComboBox jComboBox2) {
        this.animal = jComboBox2;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JLabel getjLabel2() {
        return jLabel2;
    }

    public void setjLabel2(JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }

    public JLabel getjLabel3() {
        return jLabel3;
    }

    public void setjLabel3(JLabel jLabel3) {
        this.jLabel3 = jLabel3;
    }

    public JTextField getjTextField1() {
        return dateInput;
    }

    public void setjTextField1(JTextField jTextField1) {
        this.dateInput = jTextField1;
    }

    public JToggleButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JToggleButton addButton) {
        this.addButton = addButton;
    }

    public JToggleButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JToggleButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JToggleButton getjToggleButton1() {
        return addButton;
    }

    public void setjToggleButton1(JToggleButton jToggleButton1) {
        this.addButton = jToggleButton1;
    }

    public JToggleButton getjToggleButton2() {
        return cancelButton;
    }

    public void setjToggleButton2(JToggleButton jToggleButton2) {
        this.cancelButton = jToggleButton2;
    }

    public JTextArea getDescription() {
        return description;
    }

    public void setDescription(JTextArea description) {
        this.description = description;
    }

    public JComboBox getAnimal() {
        return animal;
    }

    public void setAnimal(JComboBox animal) {
        this.animal = animal;
    }

    public JTextField getDateInput() {
        return dateInput;
    }

    public void setDateInput(JTextField dateInput) {
        this.dateInput = dateInput;
    }

    public JComboBox getPerson() {
        return person;
    }

    public void setPerson(JComboBox person) {
        this.person = person;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton addButton;
    private javax.swing.JComboBox animal;
    private javax.swing.JToggleButton cancelButton;
    private javax.swing.JTextField dateInput;
    private javax.swing.JTextArea description;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox person;
    // End of variables declaration//GEN-END:variables
}
