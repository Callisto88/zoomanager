package View.Information;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Miguel on 07/05/2017.
 */
public class InformationPanel extends JPanel {
    private static String WINDOW_TITLE = "Information";

    public InformationPanel(String sInformationString){
        JOptionPane.showMessageDialog(this, sInformationString, WINDOW_TITLE, JOptionPane.INFORMATION_MESSAGE);
    }
}
