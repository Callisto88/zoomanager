package View.Show;

import javax.swing.*;
import java.awt.*;

/**
 *
 * Cette classe contient le template du JPanel qui permet de lancer l'interface du module Animation dans l'application
 *
 * @author doriane kaffo
 * @author doriane Andre
 * @version 1.0
 *
 * @date    10/03/2017.(Création)
 * @date    39/05/2017 (Finalisation v1.0)
 *
 */
public class ShowTab extends JPanel {

    public ShowTab() {
        JFrame tab = new JFrame();
        View v = new View();
        tab.setLocation(150,25);
        tab.add(v);
        tab.setDefaultLookAndFeelDecorated(false);
        tab.setExtendedState(tab.MAXIMIZED_BOTH);
        tab.setVisible(true);
    }
}
