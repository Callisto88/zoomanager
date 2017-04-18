package View.Stock;

import Controller.Stock.AddStockController;
import View.GenericWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 *
 * Cette classe est la classe de l'interface d'ajout d'aliment et autres en stock.
 *
 * @author M.Silva
 *
 * @version 1.0
 *
 * @date    17.04.2017
 *
 */

public class AddStock extends GenericWindow{

    public AddStock(AddStockController ascAddStockController){
        super("Ajout de Stock");

        GridBagLayout gblAddStock = new GridBagLayout();
        GridBagConstraints gbcAddStock = new GridBagConstraints();

        JPanel jpAddStock = new JPanel();
        jpAddStock.setLayout(gblAddStock);
        jpMainPanel.add(jpAddStock);

        JPanel jpTitle = new JPanel();

        JLabel jlTitle = new JLabel("Ajout de Stock");
        setTitleConfig(jlTitle);
        jpTitle.add(jlTitle);

        gbcAddStock.gridx = 0;
        gbcAddStock.gridy = 0;
        jpAddStock.add(jpTitle, gbcAddStock);

        Vector<Vector<Object>> vAddStock = new Vector<>();





        configFrame(getJfFrame(), this);
    }
}
