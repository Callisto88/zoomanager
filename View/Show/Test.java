/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Show;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author doriane kaffo
 */
public class Test {
    View v = new View();
    
    //example usage
     public static void main (String []args){
        JFrame tab = new JFrame();
        tab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        View v = new View();
        Dimension dim = new Dimension(640,600);
        tab.add(v);
        tab.setSize(dim);
        tab.setVisible(true);
    }
}
