package View.Show;

import Controller.Show.*;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;


public class DialogNewAnimationPlaning extends javax.swing.JPanel {
    // Variables declaration - do not modify
    private javax.swing.JTextField IntervenantField;
    private javax.swing.JToggleButton addButton;
    private javax.swing.JButton addInfra;
    private javax.swing.JButton addIntervenant;
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
    private javax.swing.JTextField personnesField;
    private javax.swing.JTextField searchInfra;
    private javax.swing.JTextField searchIntervenant;
    private javax.swing.JTextField searchPersonne;
    private javax.swing.JComboBox selectInfra;
    private javax.swing.JComboBox selectIntervenant;
    private javax.swing.JComboBox selectPersonne;
    private javax.swing.JLabel oldDate;
    private DateModel datePanel;
    private EventTypeController eventTypeCtrl = null;
    private EventController eventCtrl = null;
    private AnimalEventController animalEventCtrl = null;
    private PersonneEventController personneEventCtrl = null;
    private IntervenantEventController intervenantEventCtrl = null;
    private InfrastructureEventController infrastructureEventController = null;
    private JLabel jLabel1;
    private ArrayList<Animal> lstAnimals,lstAnimal;
    private ArrayList<Personne> lstPersonnes,lstPersonne;
    private ArrayList<Intervenant> lstIntervenants,lstIntervenant;
    private ArrayList<Infrastructure> lstInfras,lstInfra;
    private int ID_EVENT = 0;
    private TypeEvenement TYPE_EVENT = null;

    public DialogNewAnimationPlaning() {
        initComponents();
        eventTypeCtrl = new EventTypeController();
        eventCtrl = new EventController();
        animalEventCtrl = new AnimalEventController();
        intervenantEventCtrl = new IntervenantEventController();
        infrastructureEventController = new InfrastructureEventController();
        personneEventCtrl = new PersonneEventController();
    }
    public Animal getAnimalByName(String name){
        Animal b = null;
        for (Animal a: lstAnimals){
            if(!a.getNom().equalsIgnoreCase("none") && a.getNom().equalsIgnoreCase(name) && a.getId()>0)
                b = a;
        }
        return b;
    }
    private Intervenant getIntervenantByName(String name) {
        Intervenant b = null;
        for (Intervenant a: lstIntervenants){
            if(!a.getNom().equalsIgnoreCase("none") && a.getNom().equalsIgnoreCase(name) && a.getId()>0)
                b = a;
        }
        return b;
    }
    private Infrastructure getInfrastructureByName(String name) {
        Infrastructure b = null;
        for (Infrastructure a: lstInfras){
            if(!a.getNom().equalsIgnoreCase("none") && a.getNom().equalsIgnoreCase(name) && a.getId()>0)
                b = a;
        }
        return b;
    }
    private Personne getPersonneByName(String name) {
        Personne b = null;
        for (Personne a: lstPersonnes){
            if(!a.getNom().equalsIgnoreCase("none") && a.getNom().equalsIgnoreCase(name) && a.getIdPersonne()>0)
                b = a;
        }
        return b;
    }
    public void initV(Evenement evt,TypeEvenement type){
        lstPersonnes=new ArrayList<Personne>();
        lstPersonne=new ArrayList<Personne>();
        lstAnimal = new ArrayList<Animal>();
        lstAnimals = new ArrayList<Animal>();
        lstIntervenants = new ArrayList<Intervenant>();
        lstIntervenant = new ArrayList<Intervenant>();
        lstInfras = new ArrayList<Infrastructure>();
        lstInfra = new ArrayList<Infrastructure>();
        TYPE_EVENT = type;
        if(evt!=null)
            ID_EVENT = evt.getId();
        String[] animaux = {},animauxAll = {"Vide"},
                personnes = {},personnesAll = {"Vide"},
                intervenant = {},intervenantAll = {"Vide"},
                infra ={} , infraAll = {"Vide"};

        int i =0;
        /*
            CONFIGURATION DES ANIMAUX
        */
        lstAnimals = this.animalEventCtrl.selAll();
        if(lstAnimals.size()>0)
            animauxAll = new String[lstAnimals.size()];
        i = 0;
        for (Animal a : lstAnimals){
            animauxAll[i++] = a.getNom();
        }

            /*
            CONFIGURATION DES PERSONNES
             */
        lstPersonnes = personneEventCtrl.selAll();
        if(lstPersonnes.size()>0)
            personnesAll = new String[lstPersonnes.size()];
        i = 0;
        for (Personne a : lstPersonnes){
            personnesAll[i++] = a.getNom();
        }

            /*
            CONFIGURATION DES INTERVENANTS
             */
        lstIntervenants  = intervenantEventCtrl.selAll();
        if(lstIntervenants.size()>0)
            intervenantAll = new String[lstIntervenants.size()];
        i = 0;
        for (Intervenant a : lstIntervenants){
            intervenantAll[i++] = a.getNom();
        }


            /*
            CONFIGURATION DES INFRASTRUCTURES
             */
        lstInfras = infrastructureEventController.selAll();
        if(lstInfras.size()>0)
            infraAll = new String[lstInfras.size()];
        i = 0;
        for (Infrastructure a : lstInfras){
            infraAll[i++] = a.getNom();
        }

        if(evt!=null) {
            this.description.setText(evt.getDescription());
            this.oldDate.setText(evt.getDate().toString());
            this.addButton.setText("Modifier");
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
                intervenant = new String[lstIntervenant.size()];
            i = 0;
            for (Intervenant a : lstIntervenant){
                intervenant[i++] = a.getNom();
            }


            /*
            CONFIGURATION DES INFRASTRUCTURES
             */
            lstInfra = infrastructureEventController.selAllByEventId(evt.getId());
            if(lstInfra.size()>0)
                infra = new String[lstInfra.size()];
            i = 0;
            for (Infrastructure a : lstInfra){
                infra[i++] = a.getNom();
            }

        }

        selectIntervenant.setModel(new javax.swing.DefaultComboBoxModel(intervenantAll));
        animal.setModel(new javax.swing.DefaultComboBoxModel(animauxAll));
        selectPersonne.setModel(new javax.swing.DefaultComboBoxModel(personnesAll));
        selectInfra.setModel(new javax.swing.DefaultComboBoxModel(infraAll));
        for (String s : animaux){
            this.animaux.setText(this.animaux.getText()+","+s);
        }
        for (String s : personnes){
            this.personnesField.setText(this.personnesField.getText()+","+s);
        }
        for (String s : intervenant){
            this.IntervenantField.setText(this.IntervenantField.getText()+","+s);
        }
        for (String s : infra){
            this.infraAdd.setText(this.infraAdd.getText()+","+s);
        }
    }
    public void initInfrastructure(Evenement evt,TypeEvenement type){
        lstAnimal = new ArrayList<Animal>();
        lstAnimals = new ArrayList<Animal>();
        TYPE_EVENT = type;
        if(evt!=null)
            ID_EVENT = evt.getId();
        String[] infra ={} , infraAll = {"Vide"};

        int i =0;

            /*
            CONFIGURATION DES INFRASTRUCTURES
             */
        lstInfras = infrastructureEventController.selAll();
        if(lstInfras.size()>0)
            infraAll = new String[lstInfras.size()];
        i = 0;
        for (Infrastructure a : lstInfras){
            infraAll[i++] = a.getNom();
        }

        if(evt!=null) {
            /*
            CONFIGURATION DES INFRASTRUCTURES
             */
            lstInfra = infrastructureEventController.selAllByEventId(evt.getId());
            if(lstInfra.size()>0)
                infra = new String[lstInfra.size()];
            i = 0;
            for (Infrastructure a : lstInfra){
                infra[i++] = a.getNom();
            }

        }
        selectInfra.setModel(new javax.swing.DefaultComboBoxModel(infraAll));
        for (String s : infra){
            this.infraAdd.setText(this.infraAdd.getText()+","+s);
        }
    }
    public void initAnimaux(Evenement evt,TypeEvenement type){
        lstAnimal = new ArrayList<Animal>();
        lstAnimals = new ArrayList<Animal>();
        TYPE_EVENT = type;
        if(evt!=null)
            ID_EVENT = evt.getId();
        String[] animaux = {},animauxAll = {"Vide"};

        int i =0;
        /*
            CONFIGURATION DES ANIMAUX
        */
        lstAnimals = this.animalEventCtrl.selAll();
        if(lstAnimals.size()>0)
            animauxAll = new String[lstAnimals.size()];
        i = 0;
        for (Animal a : lstAnimals){
            animauxAll[i++] = a.getNom();
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

        }

        animal.setModel(new javax.swing.DefaultComboBoxModel(animauxAll));
        for (String s : animaux) {
            this.animaux.setText(this.animaux.getText() + "," + s);
        }
    }
    public void initPersonne(Evenement evt,TypeEvenement type){
        lstPersonnes=new ArrayList<Personne>();
        lstPersonne=new ArrayList<Personne>();
        TYPE_EVENT = type;
        if(evt!=null)
            ID_EVENT = evt.getId();
        String[] personnes = {},personnesAll = {"Vide"};

            /*
            CONFIGURATION DES PERSONNES
             */
        lstPersonnes = personneEventCtrl.selAll();
        if(lstPersonnes.size()>0)
            personnesAll = new String[lstPersonnes.size()];
        int i = 0;
        for (Personne a : lstPersonnes){
            personnesAll[i++] = a.getNom();
        }
        if(evt!=null) {
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
        }

        selectPersonne.setModel(new javax.swing.DefaultComboBoxModel(personnesAll));
        for (String s : personnes){
            this.personnesField.setText(this.personnesField.getText()+","+s);
        }
    }
    public void initIntervenant(Evenement evt,TypeEvenement type){
        lstIntervenants = new ArrayList<Intervenant>();
        lstIntervenant = new ArrayList<Intervenant>();
        TYPE_EVENT = type;
        if(evt!=null)
            ID_EVENT = evt.getId();
        String[] intervenant = {},intervenantAll = {"Vide"};

        int i =0;

            /*
            CONFIGURATION DES INTERVENANTS
             */
        lstIntervenants  = intervenantEventCtrl.selAll();
        if(lstIntervenants.size()>0)
            intervenantAll = new String[lstIntervenants.size()];
        i = 0;
        for (Intervenant a : lstIntervenants){
            intervenantAll[i++] = a.getNom();
        }


        if(evt!=null) {

            /*
            CONFIGURATION DES INTERVENANTS
             */
            lstIntervenant = intervenantEventCtrl.selAllByEventId(evt.getId());
            if(lstIntervenant.size()>0)
                intervenant = new String[lstIntervenant.size()];
            i = 0;
            for (Intervenant a : lstIntervenant){
                intervenant[i++] = a.getNom();
            }

        }

        selectIntervenant.setModel(new javax.swing.DefaultComboBoxModel(intervenantAll));
        for (String s : intervenant){
            this.IntervenantField.setText(this.IntervenantField.getText()+","+s);
        }
    }
    public void initValues(Evenement evt,TypeEvenement type){
        if(evt!=null) {
            this.description.setText(evt.getDescription());
            this.oldDate.setText(evt.getDate().toString());
            this.addButton.setText("Modifier");
        }
        (new Thread(new Runnable() {
            @Override
            public void run() {
                initIntervenant(evt,type);
            }
        })).start();

        (new Thread(new Runnable() {
            @Override
            public void run() {
                initInfrastructure(evt,type);
            }
        })).start();
        (new Thread(new Runnable() {
            @Override
            public void run() {
                initPersonne(evt,type);
            }
        })).start();
        (new Thread(new Runnable() {
            @Override
            public void run() {
                initAnimaux(evt,type);
            }
        })).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Code component">
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        addButton = new javax.swing.JToggleButton();
        cancelButton = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        selectIntervenant = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        animal = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        IntervenantField = new javax.swing.JTextField();
        animaux = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        addIntervenant = new javax.swing.JButton();
        searchIntervenant = new javax.swing.JTextField();
        infraAdd = new javax.swing.JTextField();
        addInfra = new javax.swing.JButton();
        selectInfra = new javax.swing.JComboBox();
        searchInfra = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        personnesField = new javax.swing.JTextField();
        addPersonne = new javax.swing.JButton();
        selectPersonne = new javax.swing.JComboBox();
        searchPersonne = new javax.swing.JTextField();
        labelPersonne = new javax.swing.JLabel();
        imagePanel = new SlideJPanel();
        jLabel1 = new javax.swing.JLabel();
        oldDate = new javax.swing.JLabel();
        datePanel = new DateModel();

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

        jLabel2.setText("Intervenants");

        selectIntervenant.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        selectIntervenant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectIntervenantActionPerformed(evt);
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

        IntervenantField.setBackground(new java.awt.Color(204, 204, 204));
        IntervenantField.setText(" ");
        IntervenantField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IntervenantFieldActionPerformed(evt);
            }
        });

        animaux.setBackground(new java.awt.Color(204, 204, 204));
        animaux.setText(" ");
        animaux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                animalActionPerformed(evt);
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

        addIntervenant.setText("Ajouter");
        addIntervenant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addIntervenantActionPerformed(evt);
            }
        });

        searchIntervenant.setText("Rechercher...");
        searchIntervenant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchIntervenantActionPerformed(evt);
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

        personnesField.setBackground(new java.awt.Color(204, 204, 204));
        personnesField.setText(" ");
        personnesField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personnesFieldActionPerformed(evt);
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

        labelPersonne.setText("Personnes");

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


        jLabel1.setText("DATE");

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
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(selectIntervenant, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(29, 29, 29)
                                                                        .addComponent(addIntervenant))
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(animaux)
                                                                        .addComponent(IntervenantField)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(animal, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(jButton1))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel3)
                                                                                .addGap(28, 28, 28)
                                                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(4, 4, 4)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel1)
                                                                                .addGap(73, 73, 73)
                                                                                .addComponent(datePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(jLabel2)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(searchIntervenant, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                .addGap(26, 26, 26)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(selectPersonne, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(29, 29, 29)
                                                                .addComponent(addPersonne))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(selectInfra, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(addInfra))
                                                        .addComponent(personnesField)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel5)
                                                                .addGap(28, 28, 28)
                                                                .addComponent(searchInfra))
                                                        .addComponent(infraAdd)
                                                        .addComponent(imagePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(oldDate, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(labelPersonne)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(searchPersonne, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(188, 188, 188)
                                                .addComponent(jLabel4)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1)
                                                .addGap(402, 402, 402)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(44, 44, 44)
                                                .addComponent(oldDate, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(jLabel1)
                                                                .addGap(8, 8, 8))
                                                        .addComponent(datePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(labelPersonne)
                                                        .addComponent(searchPersonne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(searchIntervenant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(selectIntervenant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(addIntervenant))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(IntervenantField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(selectPersonne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(addPersonne))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(personnesField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt){
        JOptionPane bg = new JOptionPane();
        bg.showMessageDialog(this, "Enregistrer les données de votre evenement", "Enregistrement",
                JOptionPane.QUESTION_MESSAGE);
        int y = this.datePanel.datePicker.getModel().getYear();
        int m = this.datePanel.datePicker.getModel().getMonth();
        int d = this.datePanel.datePicker.getModel().getDay();
        String text = y+"-"+(m+1)+"-"+d+" 12:00:00";
//        text = "yyyy-mm-"+D+" 12:00:00";
        System.out.println(text +"|" +"2011-10-02 18:48:05.123456");
        Timestamp ts = Timestamp.valueOf(text);//"2011-10-02 18:48:05.123456");
        Evenement evnt = new Evenement(ID_EVENT,this.description.getText(),ts,TYPE_EVENT.getType());
        int f = eventCtrl.save(evnt);
        if(f>0){
            ID_EVENT = f;
        }
        System.out.println("OLD ID "+evnt.getId()+" AND NEW ONE "+ID_EVENT);
        evnt.setId(ID_EVENT);
        (new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        //lstAnimal
                        ArrayList<Personne> pers = new ArrayList<Personne>();
                        //System.out.println("PERSONNES");
                        for (String a : personnesField.getText().split(",")){
                            if(!a.trim().equalsIgnoreCase("")){
                                for(Personne b : lstPersonne){
                                    if(b.getNom().trim().equalsIgnoreCase(a.trim())){
                                        pers.add(b);
                                        break;
                                    }
                                }
                            }
                        }
                        for (String a : personnesField.getText().split(",")){
                            if(!a.trim().equalsIgnoreCase("")){
                                boolean save = true;
                                for(Personne b : pers){
                                    if(b.getNom().trim().equalsIgnoreCase(a.trim())){
                                        save = false;
                                    }
                                }
                                if(save){
                                    if(!a.equalsIgnoreCase("none")) {
                                        personneEventCtrl.saveByEventId(getPersonneByName(a), evnt);
                                    }
                                }
                            }
                        }


                        for(Personne b : lstPersonne){
                            boolean save = true;
                            for (String a : personnesField.getText().split(",")){
                                if(!a.trim().equalsIgnoreCase("")){
                                    if(b.getNom().trim().equalsIgnoreCase(a.trim())){
                                        save = false;
                                    }
                                }
                            }
                            if(save){
                                if(!b.getNom().equalsIgnoreCase("none")) {
                                    personneEventCtrl.delByEventId(b, ID_EVENT);
                                }
                            }
                        }

                        ArrayList<Intervenant> interv = new ArrayList<Intervenant>();
                        //System.out.println("intervAUX");
                        ArrayList<Intervenant> tmpinter = lstIntervenant;
                        for (String a : IntervenantField.getText().split(",")){
                            if(!a.trim().equalsIgnoreCase("")){
                                //System.out.println("NEW intervAUX - "+a);
                                for(Intervenant b : lstIntervenant){
                                    if(b.getNom().trim().equalsIgnoreCase(a.trim())){
                                        interv.add(b);
                                        break;
                                    }
                                }
                            }
                        }
                        for (String a : IntervenantField.getText().split(",")){
                            if(!a.trim().equalsIgnoreCase("")){
                                boolean save = true;
                                for(Intervenant b : interv){
                                    if(b.getNom().trim().equalsIgnoreCase(a.trim())){
                                        save = false;
                                    }
                                }
                                if(save){
                                    if(!a.equalsIgnoreCase("none"))
                                        intervenantEventCtrl.add(getIntervenantByName(a),evnt);
                                }
                            }
                        }
                        for (Intervenant a : lstIntervenant){
                            boolean del = true;
                            for(Intervenant b : interv){
                                if(b.getNom().trim().equalsIgnoreCase(a.getNom().trim())){
                                    del = false;
                                }
                            }
                            if(del){
                                intervenantEventCtrl.delByEventId(a,ID_EVENT);
                            }
                        }




                    }
                }
        )).start();
        (new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<Animal> anim = new ArrayList<Animal>();
                        //System.out.println("ANIMAUX");
                        ArrayList<Animal> tmpAnimaux = lstAnimal;
                        for (String a : animaux.getText().split(",")){
                            if(!a.trim().equalsIgnoreCase("")){
                                //System.out.println("NEW ANIMAUX - "+a);
                                for(Animal b : lstAnimal){
                                    if(b.getNom().trim().equalsIgnoreCase(a.trim())){
                                        anim.add(b);
                                        break;
                                    }
                                }
                            }
                        }
                        for (String a : animaux.getText().split(",")){
                            if(!a.trim().equalsIgnoreCase("")){
                                boolean save = true;
                                for(Animal b : anim){
                                    if(b.getNom().trim().equalsIgnoreCase(a.trim())){
                                        save = false;
                                    }
                                }
                                if(save){
                                    if(!a.equalsIgnoreCase("none"))
                                        animalEventCtrl.add(getAnimalByName(a),evnt);
                                }
                            }
                        }
                        for (Animal a : lstAnimal){
                            boolean del = true;
                            for(Animal b : anim){
                                if(b.getNom().trim().equalsIgnoreCase(a.getNom().trim())){
                                    del = false;
                                }
                            }
                            if(del){
                                animalEventCtrl.delByEventId(a,ID_EVENT);
                            }
                        }


	                        /*
	                        * INFRASTRUCTURES
	                        * */

                        ArrayList<Infrastructure> infra = new ArrayList<Infrastructure>();
                        //System.out.println("INFRASTUCTURE");
                        ArrayList<Infrastructure> tmpInfra = lstInfra;
                        for (String a : infraAdd.getText().split(",")){
                            if(!a.trim().equalsIgnoreCase("")){
                                //System.out.println("NEW INFRASTUCTURE - "+a);
                                for(Infrastructure b : lstInfra){
                                    if(b.getNom().trim().equalsIgnoreCase(a.trim())){
                                        infra.add(b);
                                        break;
                                    }
                                }
                            }
                        }
                        for (String a : infraAdd.getText().split(",")){
                            if(!a.trim().equalsIgnoreCase("")){
                                boolean save = true;
                                for(Infrastructure b : infra){
                                    if(b.getNom().trim().equalsIgnoreCase(a.trim())){
                                        save = false;
                                    }
                                }
                                if(save){
                                    if(!a.equalsIgnoreCase("none"))
                                        infrastructureEventController.saveByEventId(getInfrastructureByName(a),ID_EVENT);
                                }
                            }
                        }
                        for (Infrastructure a : lstInfra){
                            boolean del = true;
                            for(Infrastructure b : infra){
                                if(b.getNom().trim().equalsIgnoreCase(a.getNom().trim())){
                                    del = false;
                                }
                            }
                            if(del){
                                infrastructureEventController.delByEventId(a,ID_EVENT);
                            }
                        }
                    }
                }
        )).start();

    }
    /*
        WHEN WE SEARCH ANIMAL
    */
    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("On filtre un animal "+this.jTextField2.getText());
        int i =0;
        String c = this.jTextField2.getText();
        /*
            CONFIGURATION DES ANIMAUX
        */
        String[] animauxAll={};
        int k = 0;
        for (Animal a : lstAnimals)
            if(a.getNom().toLowerCase().contains(c.toLowerCase()))
                k++;
        if(k>0)
            animauxAll = new String[lstAnimals.size()];
        i = 0;
        for (Animal a : lstAnimals){
            if(a.getNom().toLowerCase().contains(c.toLowerCase()))
                animauxAll[i++] = a.getNom();
        }
        animal.setModel(new javax.swing.DefaultComboBoxModel(animauxAll));
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
        System.out.println("On filtre une infrastructure "+this.searchInfra.getText());

        int i =0;
        String c = this.searchInfra.getText();
           /*
            CONFIGURATION DES INFRASTRUCTURES
             */
        lstInfras = infrastructureEventController.selAll();
        String[] infraAll={};
        int k = 0;
        for (Infrastructure a : lstInfras)
            if(a.getNom().toLowerCase().contains(c.toLowerCase()))
                k++;
        if(k>0)
            infraAll = new String[k];
        i = 0;
        for (Infrastructure a : lstInfras){
            if(a.getNom().toLowerCase().contains(c.toLowerCase()))
                infraAll[i++] = a.getNom();
        }
        selectInfra.setModel(new javax.swing.DefaultComboBoxModel(infraAll));
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
    private void searchIntervenantActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("On filtre un employe "+this.searchIntervenant.getText());

        int i =0;
        String c = this.searchIntervenant.getText();
        /*
            CONFIGURATION DES PERSONNES
         */
        String[] personnesAll={};
        int k = 0;
        for (Intervenant a : lstIntervenants) {
            if (a.getNom().toLowerCase().contains(c.toLowerCase())) {
                k++;
                System.out.println(a.getNom());
            }
        }
        if(k>0)
            personnesAll = new String[k];
        i = 0;
        for (Intervenant a : lstIntervenants){
            if(a.getNom().toLowerCase().contains(c.toLowerCase()))
                personnesAll[i++] = a.getNom();
        }
        selectIntervenant.setModel(new javax.swing.DefaultComboBoxModel(personnesAll));
    }
    /*
        WHEN WE SELECT EMPLOYE ITEM INTO THE COMBO LIST
    */
    private void selectIntervenantActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    /*
        WHEN WE NEED TO ADD SELECTED ELEMENT EMPLOYE ITEM INTO SELECTED LIST
    */
    private void addIntervenantActionPerformed(java.awt.event.ActionEvent evt) {
        String c = selectIntervenant.getSelectedItem().toString();
        this.IntervenantField.setText(this.IntervenantField.getText()+","+c);
    }
    /*
        WHEN WE MAKE ANY ACTION INTO SELECTED LIST
    */
    private void IntervenantFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /*
        WHEN WE ADD SELECTED ANIMAL
    */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String c = animal.getSelectedItem().toString();
        this.animaux.setText(this.animaux.getText()+","+c);
    }

    /*
        WHEN WE MAKE ACTION TO SELECTION ANIMAL
    */
    private void animalActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /*
        WHEN WE SEARCH PERSONNE
    */
    private void searchPersonneActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("On filtre un personne "+this.searchPersonne.getText());

        int i =0;
        String c = this.searchPersonne.getText();
        /*
            CONFIGURATION DES PERSONNES
         */
        String[] personnesAll={};
        int k = 0;
        for (Personne a : lstPersonnes)
            if(a.getNom().toLowerCase().contains(c.toLowerCase()))
                k++;
        if(k>0)
            personnesAll = new String[k];
        i = 0;
        for (Personne a : lstPersonnes){
            if(a.getNom().toLowerCase().contains(c.toLowerCase()))
                personnesAll[i++] = a.getNom();
        }
        selectPersonne.setModel(new javax.swing.DefaultComboBoxModel(personnesAll));
    }

    /*
        WHEN WE ADD SELECT PERSONNE
    */
    private void addPersonneActionPerformed(java.awt.event.ActionEvent evt) {
        String c = selectPersonne.getSelectedItem().toString();
        this.personnesField.setText(this.personnesField.getText()+","+c);
    }

    /*
        ACTION TO  SELECTION PERSONNE
    */
    private void personnesFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /*
        WHEN WE ADD INFRASTRUCTURE
    */
    private void addInfraActionPerformed(java.awt.event.ActionEvent evt) {

        String c = selectInfra.getSelectedItem().toString();
        this.infraAdd.setText(this.infraAdd.getText()+","+c);
    }

    /*
        WHEN WE ACT TO SELECTED INFRASTRUCTURE
    */
    private void infraAddActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public JTextField getIntervenantField() {
        return IntervenantField;
    }

    public void setIntervenantField(JTextField intervenantField) {
        IntervenantField = intervenantField;
    }

    public JToggleButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JToggleButton addButton) {
        this.addButton = addButton;
    }

    public JButton getAddInfra() {
        return addInfra;
    }

    public void setAddInfra(JButton addInfra) {
        this.addInfra = addInfra;
    }

    public JButton getAddIntervenant() {
        return addIntervenant;
    }

    public void setAddIntervenant(JButton addIntervenant) {
        this.addIntervenant = addIntervenant;
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

    public JPanel getImagePanel() {
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

    public JLabel getjLabel4() {
        return jLabel4;
    }

    public void setjLabel4(JLabel jLabel4) {
        this.jLabel4 = jLabel4;
    }

    public JLabel getjLabel5() {
        return jLabel5;
    }

    public void setjLabel5(JLabel jLabel5) {
        this.jLabel5 = jLabel5;
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

    public JTextField getPersonnesField() {
        return personnesField;
    }

    public void setPersonnesField(JTextField personnesField) {
        this.personnesField = personnesField;
    }

    public JTextField getSearchInfra() {
        return searchInfra;
    }

    public void setSearchInfra(JTextField searchInfra) {
        this.searchInfra = searchInfra;
    }

    public JTextField getSearchIntervenant() {
        return searchIntervenant;
    }

    public void setSearchIntervenant(JTextField searchIntervenant) {
        this.searchIntervenant = searchIntervenant;
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

    public JComboBox getSelectIntervenant() {
        return selectIntervenant;
    }

    public void setSelectIntervenant(JComboBox selectIntervenant) {
        this.selectIntervenant = selectIntervenant;
    }

    public JComboBox getSelectPersonne() {
        return selectPersonne;
    }

    public void setSelectPersonne(JComboBox selectPersonne) {
        this.selectPersonne = selectPersonne;
    }

    public JLabel getOldDate() {
        return oldDate;
    }

    public void setOldDate(JLabel oldDate) {
        this.oldDate = oldDate;
    }

    public DateModel getDatePanel() {
        return datePanel;
    }

    public void setDatePanel(DateModel datePanel) {
        this.datePanel = datePanel;
    }

    public EventTypeController getEventTypeCtrl() {
        return eventTypeCtrl;
    }

    public void setEventTypeCtrl(EventTypeController eventTypeCtrl) {
        this.eventTypeCtrl = eventTypeCtrl;
    }

    public EventController getEventCtrl() {
        return eventCtrl;
    }

    public void setEventCtrl(EventController eventCtrl) {
        this.eventCtrl = eventCtrl;
    }

    public AnimalEventController getAnimalEventCtrl() {
        return animalEventCtrl;
    }

    public void setAnimalEventCtrl(AnimalEventController animalEventCtrl) {
        this.animalEventCtrl = animalEventCtrl;
    }

    public PersonneEventController getPersonneEventCtrl() {
        return personneEventCtrl;
    }

    public void setPersonneEventCtrl(PersonneEventController personneEventCtrl) {
        this.personneEventCtrl = personneEventCtrl;
    }

    public IntervenantEventController getIntervenantEventCtrl() {
        return intervenantEventCtrl;
    }

    public void setIntervenantEventCtrl(IntervenantEventController intervenantEventCtrl) {
        this.intervenantEventCtrl = intervenantEventCtrl;
    }

    public InfrastructureEventController getInfrastructureEventController() {
        return infrastructureEventController;
    }

    public void setInfrastructureEventController(InfrastructureEventController infrastructureEventController) {
        this.infrastructureEventController = infrastructureEventController;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public ArrayList<Animal> getLstAnimals() {
        return lstAnimals;
    }

    public void setLstAnimals(ArrayList<Animal> lstAnimals) {
        this.lstAnimals = lstAnimals;
    }

    public ArrayList<Animal> getLstAnimal() {
        return lstAnimal;
    }

    public void setLstAnimal(ArrayList<Animal> lstAnimal) {
        this.lstAnimal = lstAnimal;
    }

    public ArrayList<Personne> getLstPersonnes() {
        return lstPersonnes;
    }

    public void setLstPersonnes(ArrayList<Personne> lstPersonnes) {
        this.lstPersonnes = lstPersonnes;
    }

    public ArrayList<Personne> getLstPersonne() {
        return lstPersonne;
    }

    public void setLstPersonne(ArrayList<Personne> lstPersonne) {
        this.lstPersonne = lstPersonne;
    }

    public ArrayList<Intervenant> getLstIntervenants() {
        return lstIntervenants;
    }

    public void setLstIntervenants(ArrayList<Intervenant> lstIntervenants) {
        this.lstIntervenants = lstIntervenants;
    }

    public ArrayList<Intervenant> getLstIntervenant() {
        return lstIntervenant;
    }

    public void setLstIntervenant(ArrayList<Intervenant> lstIntervenant) {
        this.lstIntervenant = lstIntervenant;
    }

    public ArrayList<Infrastructure> getLstInfras() {
        return lstInfras;
    }

    public void setLstInfras(ArrayList<Infrastructure> lstInfras) {
        this.lstInfras = lstInfras;
    }

    public ArrayList<Infrastructure> getLstInfra() {
        return lstInfra;
    }

    public void setLstInfra(ArrayList<Infrastructure> lstInfra) {
        this.lstInfra = lstInfra;
    }
}
