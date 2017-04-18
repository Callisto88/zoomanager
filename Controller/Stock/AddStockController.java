package Controller.Stock;

import Controller.Validate.Validate;
import View.Stock.AddStock;

/**
 *
 * Cette classe est le contrôleur de l'interface qui permet d'ajouter du stock dans la DB.
 *
 * IL vérifie que les valeurs rentrées par l'utilisateur sont correctes
 *
 * Il fait aussi le lien entre l'interface et le modèle:
 *   - méthodes pour demander au modèle de faire les requêtes dans la base de données
 *
 * @author M.Silva
 *
 * @version 1.0
 *
 * @date    17.04.2017
 *
 */

public class AddStockController {

    private Validate vAddStock;
    private AddStock asAddStock;

    public AddStockController(){
        asAddStock = new AddStock(this);
        vAddStock = new Validate();
    }

    /**
     *
     * Ici il faudra ajouter une méthode qui va chercher les noms de tous les aliments dans la DB
     * Pour l'instant c'est la méthode getAllStock qui sera utilisée
     */



    public boolean isValidate(){
        return true;
    }
}
