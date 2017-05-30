package View.Show;

import javax.swing.*;
/**
 *
 * Cette classe contient le template du JPanel qui permet de fournir un chargement en pourcentage pour les longs processus
 *
 * @author doriane kaffo
 *
 * @version 1.0
 *
 * @date    28/05/2017.(Création)
 * @date    39/05/2017 (Finalisation v1.0)
 *
 */
public class LoadingPanel extends javax.swing.JPanel {

    public LoadingPanel() {
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        level = new javax.swing.JLabel();
        progPanel = new javax.swing.JPanel();

        jLabel1.setText("Chargement en cours...");

        level.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        level.setText("0%");

        javax.swing.GroupLayout progPanelLayout = new javax.swing.GroupLayout(progPanel);
        progPanel.setLayout(progPanelLayout);
        progPanelLayout.setHorizontalGroup(
                progPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 260, Short.MAX_VALUE)
        );
        progPanelLayout.setVerticalGroup(
                progPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(132, 132, 132)
                                                .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(141, 141, 141)
                                                .addComponent(level))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(65, 65, 65)
                                                .addComponent(progPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(37, 37, 37)
                                .addComponent(level)
                                .addGap(34, 34, 34)
                                .addComponent(progPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(46, Short.MAX_VALUE))
        );
    }


    // Variables declaration
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel level;
    private javax.swing.JPanel progPanel;
    // End of variables

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JLabel getLevel() {
        return level;
    }

    public void setLevel(JLabel level) {
        this.level = level;
    }

    public JPanel getProgPanel() {
        return progPanel;
    }

    public void setProgPanel(JPanel progPanel) {
        this.progPanel = progPanel;
    }
}
