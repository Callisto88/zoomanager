package View.Error;

import Controller.Error.ErrorController;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Bureau on 27.03.2017.
 * Fenêtre permettant l'affichage d'erreur lors de saisie
 */
public class ErrorPanel extends JPanel {

    /**
     * Constructeur de la fenêtre d'erreur
     *
     * @param errorString String contenant les erreurs à afficher
     */
    public ErrorPanel(String errorString) {
        //JDialog dialog = new JDialog();
        //JOptionPane option = new JOptionPane();
        JOptionPane.showMessageDialog(this, errorString, "Erreur", JOptionPane.ERROR_MESSAGE);
        System.out.println(errorString);
    }
}
