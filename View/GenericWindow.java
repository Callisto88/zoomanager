package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * Cette classe est une classe générique abstraite utilisée par toutes les fenêtres
 * de l'application sauf la fenêtre de Login pour des raisons historiques.
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
    static private String FONT_TITLE_PRINT = "Fonte Titre Impression";
    static private String BUTTON_FONT_NAME = "Fonte Bouton";
    static private String FONT_TITLE = "Fonte Titre";
    static private String FONT_WINDOW_TITLE = "Fonte Titre Fenêtre";
    static private String FONT_TABLE = "Fonte Table";
    static private String FONT_ERROR_MESSAGE = "Fonte Message Erreur";
    static private String FONT_CHECKBOX_NAME = "Fonte Checkbox";
    static private int TITLE_PRINT_FONT_SIZE = 60;
    static private int BUTTON_FONT_SIZE = 15;
    static private int TITLE_FONT_SIZE = 30;
    static private int WINDOW_TITLE_FONT_SIZE = 15;
    static private int TABLE_FONT_SIZE = 15;
    static private int ERROR_MESSAGE_FONT_SIZE = 10;
    static private int CHECKBOX_FONT_SIZE = 15;
    static private int MIN_WIDTH;
    static private int MIN_HEIGHT;
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

    public GenericWindow(String windowTitle){
        MIN_WIDTH = 1500;
        MIN_HEIGHT = 900;
        this.windowTitle = windowTitle;
        jfFrame = new JFrame(getWindowTitle());
        jfFrame.setLayout(new BorderLayout());
        jpMainPanel = new JPanel(new GridBagLayout());
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jlErrorMessage.setForeground(Color.RED);

        //gbcMainPanel.weighty = 1;
        //gbcMainPanel.anchor = PAGE_
    }

    public GenericWindow(String windowTitle, int width, int height){
        MIN_WIDTH = width;
        MIN_HEIGHT = height;
        this.windowTitle = windowTitle;
        jfFrame = new JFrame(getWindowTitle());
        jfFrame.setLayout(new BorderLayout());
        jpMainPanel = new JPanel(new GridBagLayout());
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jlErrorMessage.setForeground(Color.RED);

        //gbcMainPanel.weighty = 1;
        //gbcMainPanel.anchor = PAGE_
    }

    public static int getMinWidth() {
        return MIN_WIDTH;
    }

    public String getWindowTitle(){
        return windowTitle;
    }

    public int getMinHeight(){
        return MIN_HEIGHT;
    }

    protected JFrame getJfFrame(){
        return jfFrame;
    }

    protected JLabel getErrorMessage(){
        return jlErrorMessage;
    }

    protected void setFontTitlePrint(JLabel jlTitlePrint){
        jlTitlePrint.setFont(fTitlePrintFont);
    }

    protected void setErrorMessage(JLabel jlErrorMessage){
        this.jlErrorMessage = jlErrorMessage;
    }

    protected void setButtonConfig(JButton jbButton){
        jbButton.setMaximumSize(new Dimension(200, 30));
        jbButton.setPreferredSize(new Dimension(100, 30));
    }

    protected void setButtonConfigMedium(JButton jbButton){
        jbButton.setPreferredSize(new Dimension(200,30));
    }

    protected void setButtonLabelConfig(JLabel jlLabel){
        jlLabel.setFont(fButtonFont);
    }

    protected void setTitleConfig(JLabel jlLabel){
        jlLabel.setFont(fTitleFont);
    }

    protected void setWindowTitleConfig(JLabel jlLabel){
        jlLabel.setFont(fWindowTitleFont);
    }

    protected void setTableConfig(JTable jtTable){
        jtTable.getTableHeader().setFont(fTableFont);
    }

    protected void setErrorMessageConfig(JLabel jlLabel){
        jlLabel.setFont(fErrorMessageFont);
        jlLabel.setForeground(Color.RED);
    }

    protected void setCheckboxConfig(Checkbox cCheckbox){
        cCheckbox.setFont(fCheckboxFont);
    }

    protected void setLabelConfig(JLabel lLabel){
        lLabel.setPreferredSize(defaultFormSize);
    }

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

}
