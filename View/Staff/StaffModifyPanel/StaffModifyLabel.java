package View.Staff.StaffModifyPanel;

import Model.Personne;

import javax.swing.*;

/**
 * Created by Bureau on 10.04.2017.
 */
public class StaffModifyLabel extends JPanel {

    public StaffModifyLabel(String label, Personne personne){
        this.add(new JLabel(label));
        this.add(Box.createHorizontalStrut(50));
        //this.add(new JTextField(personne.get(label), 7));
    }
}
