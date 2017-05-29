package Model;

/**
 * <p>Cette classe contient la représentation de la table Salle de la base de données</p>
 *
 * @author C.Balboni
 * @author D.Hamel
 *
 * @version 1.0
 */
public class Salle extends Infrastructure {

    /**
     * Membres privés
     */
    private int nbPlace;

    /**
     * Constructeur
     *
     * @param nbPlace un entier indiquant l'ID de la Salle
     */
    public Salle(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    /**
     * Getters et Setters
     */

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }
}
