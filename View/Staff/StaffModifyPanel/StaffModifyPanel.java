package View.Staff.StaffModifyPanel;

import View.GenericWindow;

import javax.swing.*;

/**
 * Created by Andre on 09.04.2017.
 */
public class StaffModifyPanel extends GenericWindow{

    public StaffModifyPanel(){
        super("Modificaion");

        /*
        Récupérations des différents champs modifiable
         */
        this.add(new JLabel("Test"));
        this.add(new JButton("Blabla"));
        jpMainPanel.add(new JButton("Test"));
        this.setVisible(true);

    }

}
