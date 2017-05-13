package View.Show;

import Controller.Show.*;
import Model.*;

import javax.swing.*;
import java.util.ArrayList;


public class DialogNewAnimationPlaning extends javax.swing.JPanel {

    /**
     * Creates new form DialogNewAnimation
     */
    // Variables declaration - do not modify
    private javax.swing.JToggleButton addButton;
    private javax.swing.JButton addEmploye;
    private javax.swing.JButton addInfra;
    private javax.swing.JButton addPersonne;
    private javax.swing.JComboBox animal;
    private javax.swing.JTextField animaux;
    private javax.swing.JToggleButton cancelButton;
    private javax.swing.JTextArea description;
    private SlideJPanel imagePanel;
    private javax.swing.JTextField infraAdd;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel labelPersonne;
    private javax.swing.JComboBox person;
    private javax.swing.JTextField personnes;
    private javax.swing.JTextField personnesAdd;
    private javax.swing.JTextField searchEmploye;
    private javax.swing.JTextField searchInfra;
    private javax.swing.JTextField searchPersonne;
    private javax.swing.JComboBox selectInfra;
    private javax.swing.JComboBox selectPersonne;
    private EventTypeController eventTypeCtrl = null;
    private EventController eventCtrl = null;
    private AnimalEventController animalEventCtrl = null;
    private PersonneEventController personneEventCtrl = null;
    private IntervenantEventController intervenantEventCtrl = null;
    private InfrastructureEventController infrastructureEventController = null;
    public DialogNewAnimationPlaning() {
        initComponents();
        eventTypeCtrl = new EventTypeController();
        eventCtrl = new EventController();
        animalEventCtrl = new AnimalEventController();
        intervenantEventCtrl = new IntervenantEventController();
        infrastructureEventController = new InfrastructureEventController();
        personneEventCtrl = new PersonneEventController();
    }

    public void initValues(Evenement evt){
        String[] animaux = {},animauxAll = {"Vide"},
                    personnes = {},personnesAll = {"Vide"},
                    intervenant = {},intervenantAll = {"Vide"},
                    infra ={} , infraAll = {"Vide"};

        int i =0;
        /*
            CONFIGURATION DES ANIMAUX
             */
        ArrayList<Animal> lstAnimal = this.animalEventCtrl.selAll();
        if(lstAnimal.size()>0)
            animauxAll = new String[lstAnimal.size()];
        i = 0;
        for (Animal a : lstAnimal){
            animauxAll[i++] = a.getNom();
        }

            /*
            CONFIGURATION DES PERSONNES
             */
        ArrayList<Personne> lstPersonne = personneEventCtrl.selAll();
        if(lstPersonne.size()>0)
            personnesAll = new String[lstPersonne.size()];
        i = 0;
        for (Personne a : lstPersonne){
            personnesAll[i++] = a.getNom();
        }

            /*
            CONFIGURATION DES INTERVENANTS
             */
        ArrayList<Intervenant> lstIntervenant  = intervenantEventCtrl.selAll();
        if(lstIntervenant.size()>0)
            intervenantAll = new String[lstIntervenant.size()];
        i = 0;
        for (Intervenant a : lstIntervenant){
            intervenantAll[i++] = a.getNom();
        }


            /*
            CONFIGURATION DES INFRASTRUCTURES
             */
        ArrayList<Infrastructure> lstInfra = infrastructureEventController.selAll();
        if(lstInfra.size()>0)
            intervenantAll = new String[lstInfra.size()];
        i = 0;
        for (Infrastructure a : lstInfra){
            infraAll[i++] = a.getNom();
        }

        if(evt!=null) {
            /*
            CONFIGURATION DES ANIMAUX
             */
            lstAnimal = animalEventCtrl.selAllByEventId(evt.getId());
            if(lstAnimal.size()>0)
                animaux = new String[lstAnimal.size()];
            i = 0;
            for (Animal a : lstAnimal){
                animaux[i++] = a.getNom();
            }

            /*
            CONFIGURATION DES PERSONNES
             */
            lstPersonne = personneEventCtrl.selAllByEventId(evt.getId());
            if(lstPersonne.size()>0)
                personnes = new String[lstPersonne.size()];
            i = 0;
            for (Personne a : lstPersonne){
                personnes[i++] = a.getNom();
            }

            /*
            CONFIGURATION DES INTERVENANTS
             */
            lstIntervenant = intervenantEventCtrl.selAllByEventId(evt.getId());
            if(lstIntervenant.size()>0)
                intervenantAll = new String[lstIntervenant.size()];
            i = 0;
            for (Intervenant a : lstIntervenant){
                intervenantAll[i++] = a.getNom();
            }


            /*
            CONFIGURATION DES INFRASTRUCTURES
             */
            lstInfra = infrastructureEventController.selAllByEventId(evt.getId());
            if(lstIntervenant.size()>0)
                infraAll = new String[lstInfra.size()];
            i = 0;
            for (Infrastructure a : lstInfra){
                infraAll[i++] = a.getNom();
            }

        }

        person.setModel(new javax.swing.DefaultComboBoxModel(intervenantAll));
        animal.setModel(new javax.swing.DefaultComboBoxModel(animauxAll));
        selectPersonne.setModel(new javax.swing.DefaultComboBoxModel(personnesAll));
        selectInfra.setModel(new javax.swing.DefaultComboBoxModel(infraAll));
        for (String s : animaux){
            this.animaux.setText(this.animaux.getText()+","+s);
        }
        for (String s : personnes){
            this.personnes.setText(this.animaux.getText()+","+s);
        }
        for (String s : intervenant){
            this.personnesAdd.setText(this.animaux.getText()+","+s);
        }
        for (String s : infra){
            this.infraAdd.setText(this.animaux.getText()+","+s);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="CODE INIT">
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        addButton = new javax.swing.JToggleButton();
        cancelButton = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        person = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        animal = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        personnes = new javax.swing.JTextField();
        animaux = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        addEmploye = new javax.swing.JButton();
        searchEmploye = new javax.swing.JTextField();
        infraAdd = new javax.swing.JTextField();
        addInfra = new javax.swing.JButton();
        selectInfra = new javax.swing.JComboBox();
        searchInfra = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        personnesAdd = new javax.swing.JTextField();
        addPersonne = new javax.swing.JButton();
        selectPersonne = new javax.swing.JComboBox();
        searchPersonne = new javax.swing.JTextField();
        labelPersonne = new javax.swing.JLabel();
        imagePanel = new SlideJPanel();

        jTextField1.setText("jTextField1");

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
        person.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personActionPerformed(evt);
            }
        });

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

        personnes.setBackground(new java.awt.Color(204, 204, 204));
        personnes.setText(" ");
        personnes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personnesActionPerformed(evt);
            }
        });

        animaux.setBackground(new java.awt.Color(204, 204, 204));
        animaux.setText(" ");
        animaux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                animauxActionPerformed(evt);
            }
        });

        jTextField2.setText("Rechercher...");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton1.setText("Ajouter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        addEmploye.setText("Ajouter");
        addEmploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEmployeActionPerformed(evt);
            }
        });

        searchEmploye.setText("Rechercher...");
        searchEmploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchEmployeActionPerformed(evt);
            }
        });

        infraAdd.setBackground(new java.awt.Color(204, 204, 204));
        infraAdd.setText(" ");
        infraAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infraAddActionPerformed(evt);
            }
        });

        addInfra.setText("Ajouter");
        addInfra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addInfraActionPerformed(evt);
            }
        });

        selectInfra.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        selectInfra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectInfraActionPerformed(evt);
            }
        });

        searchInfra.setText("Rechercher...");
        searchInfra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchInfraActionPerformed(evt);
            }
        });

        jLabel5.setText("Infrastructures reserve");

        personnesAdd.setBackground(new java.awt.Color(204, 204, 204));
        personnesAdd.setText(" ");
        personnesAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personnesAddActionPerformed(evt);
            }
        });

        addPersonne.setText("Ajouter");
        addPersonne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPersonneActionPerformed(evt);
            }
        });

        selectPersonne.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        selectPersonne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectPersonneActionPerformed(evt);
            }
        });

        searchPersonne.setText("Rechercher...");
        searchPersonne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPersonneActionPerformed(evt);
            }
        });

        labelPersonne.setText("Personnes en charge");

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
                imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        imagePanelLayout.setVerticalGroup(
                imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 186, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(addButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(cancelButton))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(person, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(29, 29, 29)
                                                                                .addComponent(addEmploye))
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                        .addComponent(jLabel2)
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                        .addComponent(searchEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(animaux, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(personnes, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                        .addComponent(animal, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        .addComponent(jButton1))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                        .addComponent(jLabel3)
                                                                                        .addGap(28, 28, 28)
                                                                                        .addComponent(jTextField2))))
                                                                .addGap(26, 26, 26)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(selectPersonne, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addGap(29, 29, 29)
                                                                                .addComponent(addPersonne))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                                .addComponent(selectInfra, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(addInfra))
                                                                        .addComponent(personnesAdd, javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                                .addComponent(jLabel5)
                                                                                .addGap(28, 28, 28)
                                                                                .addComponent(searchInfra))
                                                                        .addComponent(infraAdd, javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                                .addComponent(labelPersonne)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(searchPersonne, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                        .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(198, 198, 198)
                                                .addComponent(jLabel4)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1)
                                                .addGap(402, 402, 402)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(searchEmploye, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(person, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(addEmploye))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(personnes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(animal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton1))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(animaux, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(labelPersonne)
                                                        .addComponent(searchPersonne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(selectPersonne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(addPersonne))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(personnesAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel5)
                                                        .addComponent(searchInfra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(selectInfra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(addInfra))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(infraAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelButton)
                                        .addComponent(addButton))
                                .addContainerGap())
        );
    }// </editor-fold>

    /*
        WHEN WE CANCEL CREATION ELEMENT
    */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    /*
        WHEN WE NEED SELECT ANIMAL
    */
    private void animalActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /*
        WHEN WE SUBMIT ALL MODIFICATIONS
    */
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {

    }
    /*
        WHEN WE FILTER ANIMAL
    */
    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /*
        WHEN WE  SELECT INFRASTRUCTURE
    */
    private void selectInfraActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /*
        WHEN WE SEARCH  INFRASTRUCTURE
    */
    private void searchInfraActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /*
        WHEN WE  SELECT PERSONNE
    */
    private void selectPersonneActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /*
        WHEN WE SEACH TO FILTER EMPLOYE ITEM INTO THE COMBO LIST
    */
    private void searchEmployeActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    /*
        WHEN WE SELECT EMPLOYE ITEM INTO THE COMBO LIST
    */
    private void personActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    /*
        WHEN WE NEED TO ADD SELECTED ELEMENT EMPLOYE ITEM INTO SELECTED LIST
    */
    private void addEmployeActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    /*
        WHEN WE MAKE ANY ACTION INTO SELECTED LIST
    */
    private void personnesActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /*
        WHEN WE ADD SELECTED ANIMAL
    */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /*
        WHEN WE MAKE ACTION TO SELECTION ANIMAL
    */
    private void animauxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /*
        WHEN WE SEARCH PERSONNE
    */
    private void searchPersonneActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /*
        WHEN WE ADD SELECT PERSONNE
    */
    private void addPersonneActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /*
        ACTION TO  SELECTION PERSONNE
    */
    private void personnesAddActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /*
        WHEN WE ADD INFRASTRUCTURE
    */
    private void addInfraActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /*
        WHEN WE ACT TO SELECTED INFRASTRUCTURE
    */
    private void infraAddActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public JToggleButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JToggleButton addButton) {
        this.addButton = addButton;
    }

    public JButton getAddEmploye() {
        return addEmploye;
    }

    public void setAddEmploye(JButton addEmploye) {
        this.addEmploye = addEmploye;
    }

    public JButton getAddInfra() {
        return addInfra;
    }

    public void setAddInfra(JButton addInfra) {
        this.addInfra = addInfra;
    }

    public JButton getAddPersonne() {
        return addPersonne;
    }

    public void setAddPersonne(JButton addPersonne) {
        this.addPersonne = addPersonne;
    }

    public JComboBox getAnimal() {
        return animal;
    }

    public void setAnimal(JComboBox animal) {
        this.animal = animal;
    }

    public JTextField getAnimaux() {
        return animaux;
    }

    public void setAnimaux(JTextField animaux) {
        this.animaux = animaux;
    }

    public JToggleButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JToggleButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public JTextArea getDescription() {
        return description;
    }

    public void setDescription(JTextArea description) {
        this.description = description;
    }

    public SlideJPanel getImagePanel() {
        return imagePanel;
    }

    public void setImagePanel(SlideJPanel imagePanel) {
        this.imagePanel = imagePanel;
    }

    public JTextField getInfraAdd() {
        return infraAdd;
    }

    public void setInfraAdd(JTextField infraAdd) {
        this.infraAdd = infraAdd;
    }

    public JButton getjButton1() {
        return jButton1;
    }

    public void setjButton1(JButton jButton1) {
        this.jButton1 = jButton1;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JTextField getjTextField1() {
        return jTextField1;
    }

    public void setjTextField1(JTextField jTextField1) {
        this.jTextField1 = jTextField1;
    }

    public JTextField getjTextField2() {
        return jTextField2;
    }

    public void setjTextField2(JTextField jTextField2) {
        this.jTextField2 = jTextField2;
    }

    public JLabel getLabelPersonne() {
        return labelPersonne;
    }

    public void setLabelPersonne(JLabel labelPersonne) {
        this.labelPersonne = labelPersonne;
    }

    public JComboBox getPerson() {
        return person;
    }

    public void setPerson(JComboBox person) {
        this.person = person;
    }

    public JTextField getPersonnes() {
        return personnes;
    }

    public void setPersonnes(JTextField personnes) {
        this.personnes = personnes;
    }

    public JTextField getPersonnesAdd() {
        return personnesAdd;
    }

    public void setPersonnesAdd(JTextField personnesAdd) {
        this.personnesAdd = personnesAdd;
    }

    public JTextField getSearchEmploye() {
        return searchEmploye;
    }

    public void setSearchEmploye(JTextField searchEmploye) {
        this.searchEmploye = searchEmploye;
    }

    public JTextField getSearchInfra() {
        return searchInfra;
    }

    public void setSearchInfra(JTextField searchInfra) {
        this.searchInfra = searchInfra;
    }

    public JTextField getSearchPersonne() {
        return searchPersonne;
    }

    public void setSearchPersonne(JTextField searchPersonne) {
        this.searchPersonne = searchPersonne;
    }

    public JComboBox getSelectInfra() {
        return selectInfra;
    }

    public void setSelectInfra(JComboBox selectInfra) {
        this.selectInfra = selectInfra;
    }

    public JComboBox getSelectPersonne() {
        return selectPersonne;
    }

    public void setSelectPersonne(JComboBox selectPersonne) {
        this.selectPersonne = selectPersonne;
    }


}
