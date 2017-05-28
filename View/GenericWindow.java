package View;

import javax.swing.*;
import java.awt.*;

/**
 *
 * Cette classe est une classe générique abstraite utilisée par toutes les fenêtres
 * de l'application.
 *
 * Elle met à disposition plusieurs méthodes et variables communes à toutes les fenêtres.
 * C'est dans cette classe que les JFrame sont instanciées.
 *
 * @author M.Silva
 *
 * @version 1.0
 *
 * @date    08.04.2017
 *
 */

abstract public class GenericWindow extends JPanel {
    //Nom des différentes fontes
    private final String FONT_TITLE_PRINT = "Fonte Titre Impression";
    private final String BUTTON_FONT_NAME = "Fonte Bouton";
    private final String FONT_TITLE = "Fonte Titre";
    private final String FONT_WINDOW_TITLE = "Fonte Titre Fenêtre";
    private final String FONT_TABLE = "Fonte Table";
    private final String FONT_ERROR_MESSAGE = "Fonte Message Erreur";
    private final String FONT_CHECKBOX_NAME = "Fonte Checkbox";
    //Taille des caractères
    private final int TITLE_PRINT_FONT_SIZE = 60;
    private final int BUTTON_FONT_SIZE = 15;
    private final int TITLE_FONT_SIZE = 30;
    private final int WINDOW_TITLE_FONT_SIZE = 15;
    private final int TABLE_FONT_SIZE = 15;
    private final int ERROR_MESSAGE_FONT_SIZE = 10;
    private final int CHECKBOX_FONT_SIZE = 15;
    //Largeur et longueur de la fenêtre
    private int MIN_WIDTH;
    private int MIN_HEIGHT;
    protected JPanel jpMainPanel;
    protected GridBagConstraints gbcMainPanel = new GridBagConstraints();
    private Dimension dim;
    //Titre de la JFrame
    private String windowTitle;
    protected JLabel jlErrorMessage = new JLabel();
    private JFrame jfFrame;
    //Les différentes fontes
    private Font fTitlePrintFont = new Font(FONT_TITLE_PRINT, Font.PLAIN, TITLE_PRINT_FONT_SIZE);
    private Font fButtonFont = new Font(BUTTON_FONT_NAME, Font.PLAIN, BUTTON_FONT_SIZE);
    private Font fTitleFont = new Font(FONT_TITLE, Font.PLAIN, TITLE_FONT_SIZE);
    private Font fWindowTitleFont = new Font(FONT_WINDOW_TITLE, Font.PLAIN, WINDOW_TITLE_FONT_SIZE);
    private Font fTableFont = new Font(FONT_TABLE, Font.PLAIN, TABLE_FONT_SIZE);
    private Font fErrorMessageFont = new Font(FONT_ERROR_MESSAGE, Font.PLAIN, ERROR_MESSAGE_FONT_SIZE);
    private Font fCheckboxFont = new Font(FONT_CHECKBOX_NAME, Font.PLAIN, CHECKBOX_FONT_SIZE);
    static protected Dimension defaultFormSize = new Dimension(140, 30);

    /**
     * Constructeur
     * @param windowTitle est le titre de la JFrame
     */
    public GenericWindow(String windowTitle){
        MIN_WIDTH = 1500;
        MIN_HEIGHT = 900;
        this.windowTitle = windowTitle;
        //Instanciation de la JFrame, puis on lui "set" un layout
        jfFrame = new JFrame(getWindowTitle());
        jfFrame.setLayout(new BorderLayout());
        //Instanciation du panel principal et on lui "set" un layout
        jpMainPanel = new JPanel(new GridBagLayout());
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jlErrorMessage.setForeground(Color.RED);
    }

    /**
     * Constructeur avec la taille de la fenêtre passée en paramètre
     * @param windowTitle est le titre de la JFrame
     * @param width est la largeur de la fenêtre
     * @param height est la heuteur de la fenêtre
     */
    public GenericWindow(String windowTitle, int width, int height){
        MIN_WIDTH = width;
        MIN_HEIGHT = height;
        this.windowTitle = windowTitle;
        //Instanciation de la JFrame, puis on lui "set" un layout
        jfFrame = new JFrame(getWindowTitle());
        jfFrame.setLayout(new BorderLayout());
        //Instanciation du panel principal et on lui "set" un layout
        jpMainPanel = new JPanel(new GridBagLayout());
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jlErrorMessage.setForeground(Color.RED);
    }

    /*
    private int MIN_WIDTH;
    private int MIN_HEIGHT;
    protected JPanel jpMainPanel;
    protected GridBagConstraints gbcMainPanel = new GridBagConstraints();
    private Dimension dim;
    private String windowTitle;
    protected JLabel jlErrorMessage = new JLabel();
    private JFrame jfFrame;
    private Font fTitlePrintFont = new Font(FONT_TITLE_PRINT, Font.PLAIN, TITLE_PRINT_FONT_SIZE);
    private Font fButtonFont = new Font(BUTTON_FONT_NAME, Font.PLAIN, BUTTON_FONT_SIZE);
    private Font fTitleFont = new Font(FONT_TITLE, Font.PLAIN, TITLE_FONT_SIZE);
    private Font fWindowTitleFont = new Font(FONT_WINDOW_TITLE, Font.PLAIN, WINDOW_TITLE_FONT_SIZE);
    private Font fTableFont = new Font(FONT_TABLE, Font.PLAIN, TABLE_FONT_SIZE);
    private Font fErrorMessageFont = new Font(FONT_ERROR_MESSAGE, Font.PLAIN, ERROR_MESSAGE_FONT_SIZE);
    private Font fCheckboxFont = new Font(FONT_CHECKBOX_NAME, Font.PLAIN, CHECKBOX_FONT_SIZE);
    static protected Dimension defaultFormSize = new Dimension(140, 30);
     */

    /**
     * Méthode qui configure la JFrame et qui la rend visible.
     * @param jfFrame est la JFrame en question
     * @param gw est l'objet de type GenericWindow qui a l'attribut privé JFrame
     */
    protected void configFrame(JFrame jfFrame, GenericWindow gw){
        jfFrame.add(jpMainPanel,BorderLayout.NORTH);
        jfFrame.setContentPane(jpMainPanel);
        //jfFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jfFrame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        jfFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        //jfFrame.getContentPane().add(gw);
        jfFrame.pack();
        jfFrame.setVisible(true);

        jfFrame.setLocation((dim.width - jfFrame.getContentPane().getWidth())/2, (dim.height - jfFrame.getContentPane().getHeight())/2);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // Les getters et les setters

    /**
     * Méthode qui retourne la largeur de la JFrame
     * @return un int qui la largeur de la JFrame
     */
    public int getMinWidth() {
        return MIN_WIDTH;
    }

    /**
     * Méthode qui retourne la hauteur de la JFrame
     * @return un int qui la hauteur de la JFrame
     */
    public int getMinHeight(){
        return MIN_HEIGHT;
    }

    /**
     * Méthode qui retourne le titre de la JFrame
     * @return une String qui est le titre de la JFrame
     */
    public String getWindowTitle(){
        return windowTitle;
    }

    /**
     * Méthode qui retourne une référence sur la JFrame instancié dans le constructeur de
     * cet objet
     * @return une référence sur la JFrame
     */
    protected JFrame getJfFrame(){
        return jfFrame;
    }

    /**
     * Méthode qui retourne une référence sur le JLabel qui écrit le texte en rouge pour
     * annoncer qu'il y a une erreur
     * @return une référence sur le JLabel pour les messages d'erreur
     */
    protected JLabel getErrorMessage(){
        return jlErrorMessage;
    }

    /**
     * Méthode qui permet de changer le message d'erreur affiché
     * @param jlErrorMessage est le JLabel avec le nouveau texte
     */
    protected void setErrorMessage(JLabel jlErrorMessage){
        this.jlErrorMessage.setText(jlErrorMessage.getText());
    }

    /**
     * Méthode qui configure la fonte pour les titres des fichiers à imprimer
     * @param jlTitlePrint est le JLabel dont il faut changer la fonte
     */
    protected void setFontTitlePrint(JLabel jlTitlePrint){
        jlTitlePrint.setFont(fTitlePrintFont);
    }

    /**
     * Méthode qui configure un bouton dans un standard définit. Dans ce cas,
     * seul la taille des boutons est changée.
     * @param jbButton est le bouton qu'il faut configuré
     */
    protected void setButtonConfig(JButton jbButton){
        jbButton.setMaximumSize(new Dimension(200, 30));
        jbButton.setPreferredSize(new Dimension(100, 30));
    }

    /**
     * Méthode qui configure un bouton de taille moyenne. Dans ce cas, seul la taille
     * des boutons est changée.
     * @param jbButton est le bouton qu'il faut configuré
     */
    protected void setButtonConfigMedium(JButton jbButton){
        jbButton.setPreferredSize(new Dimension(200,30));
    }

    /**
     * Méthode qui configure la fonte du texte dans les boutons
     * @param jlLabel est le JLabel dont il faut changer la fonte
     */
    protected void setButtonLabelConfig(JLabel jlLabel){
        jlLabel.setFont(fButtonFont);
    }

    /**
     * Méthode qui configure la fonte pour les titres dans les interfaces
     * @param jlLabel est le JLabel dont il faut changer la fonte
     */
    protected void setTitleConfig(JLabel jlLabel){
        jlLabel.setFont(fTitleFont);
    }

    /**
     * Méthode qui configure la fonte pour les titres des JFrames
     * @param jlLabel est le JLabel avec le titre de la JFrame
     */
    protected void setWindowTitleConfig(JLabel jlLabel){
        jlLabel.setFont(fWindowTitleFont);
    }

    /**
     * Méthode qui configure la font des en-têtes d'une JTable
     * @param jtTable est la JTable dont il faut changer la fonte des en-têtes
     */
    protected void setTableConfig(JTable jtTable){
        jtTable.getTableHeader().setFont(fTableFont);
    }

    /**
     * Méthode qui permet de changer la fonte et de mettre le texte du JLabel passé en
     * paramètre en rouge
     * @param jlLabel est le JLabel à configurer
     */
    protected void setErrorMessageConfig(JLabel jlLabel){
        jlLabel.setFont(fErrorMessageFont);
        jlLabel.setForeground(Color.RED);
    }

    /**
     * Méthode qui configure la fonte de la Checkbox passé en paramètre
     * @param cCheckbox est la Checkbox à configurer
     */
    protected void setCheckboxConfig(Checkbox cCheckbox){
        cCheckbox.setFont(fCheckboxFont);
    }

    /**
     * Méthode qui configure le JLabel passé en paramètre avec une taille préférentielle.
     * Le JLabel va donc prendre cette taille si cela lui est possible.
     * @param lLabel est le JLabel à configurer
     */
    protected void setLabelConfig(JLabel lLabel){
        lLabel.setPreferredSize(defaultFormSize);
    }



}
