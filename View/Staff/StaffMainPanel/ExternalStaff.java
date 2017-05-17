package View.Staff.StaffMainPanel;

import Controller.Staff.StaffController;
import Model.Evenement;
import Model.Intervenant;
import View.EventsTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by André on 22.04.2017.
 * Class permettant d'afficher les détail des personnes externes dans une fenêtre
 */
public class ExternalStaff extends JPanel{

    private GridBagConstraints gbcDetailsExternal = new GridBagConstraints();
    private int x = 0;
    private int y = 0;
    private int line;

    private JLabel jlFirstNameInfo;
    private JLabel jlLastNameInfo;
    private JLabel jlEmailInfo;
    private JLabel jlPhoneInfo;
    private JLabel jlAddressInfo;
    private JLabel jlCityInfo;
    private JLabel jlNPAInfo;
    private JLabel jlCountryInfo;
    private JLabel jlCompagnyInfo;

    private Dimension dLabel = new Dimension(100,25);
    private Dimension dDetail = new Dimension(120, 25);

    /**
     * Constructeur de la class pour afficher les détails
     * @param controller controlleur StaffController permettant de faire remonter les informations
     * @param external intervenant pour lequel on souhaite afficher les détails
     * @param line ligne correspondant à la ligne sélectionné de l'intervenant TODO : ne marche pas très bien
     */
    public ExternalStaff(StaffController controller, Intervenant external, int line){

        this.setLayout(new GridBagLayout());
        this.line = line;
        gbcDetailsExternal.gridx = x;
        gbcDetailsExternal.gridy = y;
        JPanel jpLeft = new JPanel();
        jpLeft.setLayout(new GridBagLayout());

        // Ajout du champ de détails pour le nom
        JPanel jpLastName = new JPanel();
        JLabel jlLastName = new JLabel("Nom : ");
        jlLastName.setPreferredSize(dLabel);
        jlLastNameInfo = new JLabel(external.getNom());
        jpLastName.add(jlLastName);
        jlLastNameInfo.setPreferredSize(dDetail);
        jpLastName.add(jlLastNameInfo);
        gbcDetailsExternal.gridy = y;
        this.add(jpLastName, gbcDetailsExternal);

        // Ajout du champ de détails pour le prénom
        JPanel jpFirstName = new JPanel();
        JLabel jlFirstName = new JLabel("Prénom : ");
        jlFirstName.setPreferredSize(dLabel);
        jlFirstNameInfo = new JLabel(external.getPrenom());
        jlFirstNameInfo.setPreferredSize(dLabel);
        jpFirstName.add(jlFirstName);
        jlFirstNameInfo.setPreferredSize(dDetail);
        jpFirstName.add(jlFirstNameInfo);
        ++y;
        gbcDetailsExternal.gridy = y;
        this.add(jpFirstName, gbcDetailsExternal);

        // Ajout du champ de détails pour l'email
        JPanel jpEmail = new JPanel();
        JLabel jlEmail = new JLabel("E-Mail : ");
        jlEmail.setPreferredSize(dLabel);
        jlEmailInfo = new JLabel(external.getEmail());
        jlEmailInfo.setPreferredSize(dDetail);
        jpEmail.add(jlEmail);
        jpEmail.add(jlEmailInfo);
        ++y;
        gbcDetailsExternal.gridy = y;
        this.add(jpEmail, gbcDetailsExternal);

        // Ajout du champ de détails pour le numéro de téléphone
        JPanel jpPhone = new JPanel();
        JLabel jlPhone = new JLabel("Téléphone : ");
        jlPhone.setPreferredSize(dLabel);
        jlPhoneInfo = new JLabel(external.getTelephone());
        jpPhone.add(jlPhone);
        jlPhoneInfo.setPreferredSize(dDetail);
        jpPhone.add(jlPhoneInfo);
        ++y;
        gbcDetailsExternal.gridy = y;
        this.add(jpPhone, gbcDetailsExternal);

        // Ajout du champ de détails pour l'entreprise
        JPanel jpCompagny = new JPanel();
        JLabel jlCompagny = new JLabel("Entreprise : ");
        jlCompagny.setPreferredSize(dLabel);
        jlCompagnyInfo = new JLabel(external.getEntreprise());
        jpCompagny.add(jlCompagny);
        jlCompagny.setPreferredSize(dDetail);
        jpCompagny.add(jlCompagnyInfo);
        y = 0;
        x = 1;
        gbcDetailsExternal.gridx = x;
        gbcDetailsExternal.gridy = y;
        this.add(jpCompagny, gbcDetailsExternal);


        // Ajout du champ de détails pour l'adresse
        // TODO A VIRER UNE FOIS LA BDD RETOUCHER!!!!! ADRESSE OBLIGATOIRE!
        String sAddress = "";
        String sCity = "";
        String sNPA = "";
        String sCountry = "";
        if(external.getAdresse() != null){
            sAddress = external.getAdresse().getAdresse();
            sCity = external.getAdresse().getVille().getVille();
            sNPA = "" + external.getAdresse().getVille().getCp();
            sCountry = external.getAdresse().getVille().getPays().getPays();
        }
        JPanel jpAddress = new JPanel();
        JLabel jlAddress = new JLabel("Adresse : ");
        jlAddress.setPreferredSize(dLabel);
        jlAddressInfo = new JLabel(sAddress);
        jpAddress.add(jlAddress);
        jlAddressInfo.setPreferredSize(dDetail);
        jpAddress.add(jlAddressInfo);
        ++y;
        gbcDetailsExternal.gridy = y;
        this.add(jpAddress, gbcDetailsExternal);

        // Ajout du champ de détails pour la ville
        JPanel jpCity = new JPanel();
        JLabel jlCity = new JLabel("Ville : ");
        jlCity.setPreferredSize(dLabel);
        jlCityInfo = new JLabel(sCity);
        jpCity.add(jlCity);
        jlCityInfo.setPreferredSize(dDetail);
        jpCity.add(jlCityInfo);
        ++y;
        gbcDetailsExternal.gridy = y;
        this.add(jpCity, gbcDetailsExternal);

        // Ajout du champ de détails pour le npa
        JPanel jpNPA = new JPanel();
        JLabel jlNPA = new JLabel("NPA : ");
        jlNPA.setPreferredSize(dLabel);
        jlNPAInfo = new JLabel(sNPA);
        jpNPA.add(jlNPA);
        jlNPAInfo.setPreferredSize(dDetail);
        jpNPA.add(jlNPAInfo);
        ++y;
        gbcDetailsExternal.gridy = y;
        this.add(jpNPA, gbcDetailsExternal);

        // Ajout du champ de détails pour le pays
        JPanel jpCountry = new JPanel();
        JLabel jlCountry = new JLabel("Pays : ");
        jlCountry.setPreferredSize(dLabel);
        jlCountryInfo = new JLabel(sCountry);
        jpCountry.add(jlCountry);
        jlCountryInfo.setPreferredSize(dDetail);
        ++y;
        gbcDetailsExternal.gridy = y;
        jpCountry.add(jlCountryInfo);
        this.add(jpCountry, gbcDetailsExternal);

        ArrayList<Evenement> tasks = controller.getExternalTask(external.getId());
        ++y;
        gbcDetailsExternal.gridy = y;
        x = 0;
        gbcDetailsExternal.gridx = x;
        gbcDetailsExternal.gridwidth = 2;
        gbcDetailsExternal.insets = new Insets(5,5,5,5);
        EventsTable etExternal = new EventsTable(tasks);
        this.add(etExternal, gbcDetailsExternal);

        // panel permettant de mettre les trois bouttons de suppression, modification et d'ajout de tache
        JPanel jpButtons = new JPanel();

        //Ajout du bouton de suppression de l'intervenant actuel
        JButton jbDelete = new JButton("Suppression de l'intervenant");
        jpButtons.add(jbDelete);
        jpButtons.add(Box.createHorizontalStrut(5));
        jbDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(new JPanel(), "Voulez-vous vraiment supprimer cette personne ?",
                        "Confirmer la suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
                if(n == 0) {
                    if (controller.deleteExternal(external)) {
                        controller.deleteExternalRow(line);
                    }
                }
            }
        });

        // Ajout du bouton d'édition de l'intervenant
        JButton jbEdit = new JButton("Modification");
        jpButtons.add(jbEdit);
        jpButtons.add(Box.createHorizontalStrut(5));
        jbEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("modification de l'intervenant");
                controller.modifyExternalView(external);
            }
        });

        // Ajout du bouton d'assignation de taches
        JButton jbAssignTask = new JButton("Assignation de tâches");
        jpButtons.add(jbAssignTask);
        jpButtons.add(Box.createHorizontalStrut(5));
        jbAssignTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Assignation de tâches");
                controller.assignExternalTaskView(external);
            }
        });
        // Ajout du bouton d'impression de taches
        JButton jbPrintTask = new JButton("Impression tâches");
        jpButtons.add(jbPrintTask);
        jbPrintTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Impression de tâches");
                String pdfName = "\\Personnal_Task_" + external.getNom() + ".pdf";
                String title = "Tâches personnels";
                String name = external.getNom() + " " + external.getPrenom();
                controller.print(etExternal.getJTable(), pdfName, title, name);
            }
        });

        // Ajout des bouttons dans le panel
        ++y;
        gbcDetailsExternal.gridy = y;
        this.add(jpButtons, gbcDetailsExternal);

    }

    public void updateLabel(Intervenant external){
        jlFirstNameInfo.setText(external.getPrenom());
        jlLastNameInfo.setText(external.getNom());
        jlEmailInfo.setText(external.getEmail());
        jlPhoneInfo.setText(external.getTelephone());
        jlAddressInfo.setText(external.getAdresse().getAdresse());
        jlCityInfo.setText(external.getAdresse().getVille().getVille());
        jlNPAInfo.setText("" + external.getAdresse().getVille().getCp());
        jlCountryInfo.setText(external.getAdresse().getVille().getPays().getPays());
        jlCompagnyInfo.setText(external.getEntreprise());
        this.updateUI();
    }

}
