package View;

import javax.swing.*;
import java.awt.*;

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
    static private String BUTTON_FONT_NAME = "Fonte Bouton";
    static private String FONT_TITLE = "Fonte Titre";
    static private String FONT_WINDOW_TITLE = "Fonte Titre Fenêtre";
    static private String FONT_TABLE = "Fonte Table";
    static private String FONT_ERROR_MESSAGE = "Fonte Message Erreur";
    static private String FONT_CHECKBOX_NAME = "Fonte Checkbox";
    static private int BUTTON_FONT_SIZE = 15;
    static private int TITLE_FONT_SIZE = 30;
    static private int WINDOW_TITLE_FONT_SIZE = 15;
    static private int TABLE_FONT_SIZE = 15;
    static private int ERROR_MESSAGE_FONT_SIZE = 15;
    static private int CHECKBOX_FONT_SIZE = 15;
    static private int MIN_WIDTH = 1440;
    static private int MIN_HEIGHT = 900;
    protected JPanel jpMainPanel;
    protected GridBagConstraints gbcMainPanel = new GridBagConstraints();
    private Dimension dim;
    private String windowTitle;
    private JLabel jlErrorMessage = new JLabel();
    private JFrame jfFrame;
    private Font fButtonFont = new Font(BUTTON_FONT_NAME, Font.PLAIN, BUTTON_FONT_SIZE);
    private Font fTitleFont = new Font(FONT_TITLE, Font.PLAIN, TITLE_FONT_SIZE);
    private Font fWindowTitleFont = new Font(FONT_WINDOW_TITLE, Font.PLAIN, WINDOW_TITLE_FONT_SIZE);
    private Font fTableFont = new Font(FONT_TABLE, Font.PLAIN, TABLE_FONT_SIZE);
    private Font fErrorMessageFont = new Font(FONT_ERROR_MESSAGE, Font.PLAIN, ERROR_MESSAGE_FONT_SIZE);
    private Font fCheckboxFont = new Font(FONT_CHECKBOX_NAME, Font.PLAIN, CHECKBOX_FONT_SIZE);

    public GenericWindow(String windowTitle){
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

    protected void setErrorMessage(JLabel jlErrorMessage){
        this.jlErrorMessage = jlErrorMessage;
    }

    protected void setButtonConfig(JButton jbButton){
        jbButton.setMaximumSize(new Dimension(200, 30));
        jbButton.setPreferredSize(new Dimension(100, 30));
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
    }

    protected void setCheckboxConfig(Checkbox cCheckbox){
        cCheckbox.setFont(fCheckboxFont);
    }

    protected void configFrame(JFrame jfFrame, GenericWindow gw){
        jfFrame.add(jpMainPanel,BorderLayout.NORTH);
        jfFrame.setContentPane(jpMainPanel);
        //jfFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jfFrame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        jfFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //jfFrame.getContentPane().add(gw);
        jfFrame.pack();
        jfFrame.setVisible(true);

        jfFrame.setLocation((dim.width - jfFrame.getContentPane().getWidth())/2, (dim.height - jfFrame.getContentPane().getHeight())/2);
    }

}
