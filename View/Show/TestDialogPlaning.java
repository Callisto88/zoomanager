package View.Show;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * Cette classe contient code qui permet de tester la vue d'ajoute des evenements
 *
 * @author doriane kaffo
 * @version 1.0
 *
 * @date    10/03/2017.(Création)
 * @date    39/05/2017 (Finalisation v1.0)
 *
 */
public class TestDialogPlaning {
    public static void main (String []args){
        final JDialog dg = new JDialog();
        dg.setTitle("Planifier une animation");
        dg.setSize(new Dimension(600,400));
        final DialogNewAnimationPlaning pan = new DialogNewAnimationPlaning();
        dg.setLocation(300, 200);
        dg.setVisible(true);
        ActionListener n = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dg.setVisible(false);
            }
        };
        ActionListener n2 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dg.setVisible(false);
            }
        };
        pan.getAddButton().addActionListener(n);
        pan.getCancelButton().addActionListener(n2);
    }
}
