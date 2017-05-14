package View.Show;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andre on 10.03.2017.
 */
public class ShowTab extends JPanel {

    public ShowTab() {
        JFrame tab = new JFrame();
//        tab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        View v = new View();
        Dimension dim = new Dimension(640,600);
        tab.setLocation(295,20);
        tab.setResizable(false);
        tab.add(v);
        tab.setSize(dim);
        tab.setVisible(true);
        v.setVisible(true);
    }
}
