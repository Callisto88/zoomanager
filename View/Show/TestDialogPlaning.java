package View.Show;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestDialogPlaning {
    public static void main (String []args){
        final JDialog dg = new JDialog();
        dg.setTitle("Planifier une animation");
        dg.setSize(new Dimension(600,400));
        final DialogNewAnimationPlaning pan = new DialogNewAnimationPlaning();
        dg.setLocation(300, 200);
        GridBagConstraints gbcConstraint = new GridBagConstraints();
        gbcConstraint.gridx = 0;
        gbcConstraint.gridy = 0;
        gbcConstraint.insets = new Insets(5,5,5,5);
        gbcConstraint.anchor = GridBagConstraints.WEST;
        dg.setLayout(new GridBagLayout());
        dg.getContentPane().add(new DateModel("Date Animation"),gbcConstraint);
        gbcConstraint.gridy = 1;
        dg.getContentPane().add(pan,gbcConstraint);
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
