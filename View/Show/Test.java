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
 * Cette classe contient le code qui permet de lancer le test du module Animation indépendament des autres modules
 *
 * @author doriane kaffo
 *
 * @version 1.0
 *
 * @date    20/03/2017.(Création)
 * @date    39/05/2017 (Finalisation v1.0)
 *
 */
public class Test {
    View v = new View();
    
    //example usage
     public static void main (String []args){
        JFrame tab = new JFrame();
        tab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         tab.setDefaultLookAndFeelDecorated(false);
         tab.setExtendedState(tab.MAXIMIZED_BOTH);
        View v = new View();
        tab.setLocation(50,25);
        tab.add(v);
        tab.setVisible(true);
    }
}
