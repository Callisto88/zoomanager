package Model;

/**
 * Cette classe contient la représentation de la table Evenement_Type de la base de données
 *
 * @author C.Balboni
 * @author D.Hamel
 * @version 1.0
 */
public class TypeEvenement {

    /**
     * Membres privés
     */
    private String type;

    /**
     * Constructeur par défaut
     */
    public TypeEvenement() {
    }

    /**
     * Constructeur simple à partir du nom du type d'événement
     *
     * @param type une String représentant le type d'événement
     */
    public TypeEvenement(String type) {
        this.type = type;
    }

    /**
     * Getters & Setters
     */

    /**
     * @return une String comportant le nom du type d'événement
     */
    public String getType() {
        return type;
    }

    /**
     * @param type String correspondant au nom du type d'événement
     */
    public void setType(String type) {
        this.type = type;
    }
}
