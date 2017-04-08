package Model;
import java.sql.SQLException;

/**
 *
 * Cette classe contient la conception de la table TypeEvenement de la base de données
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * @author D.Hamel
 * @author C.Balboni
 *
 * @version 1.0
 *
 * @date    28.03.2017 (Création)
 * @date    28.03.2017 (Finalisation v1.0)
 *
 */
public class TypeEvenement {

    /**
     * Membres privés
     */
    private String type;


    public TypeEvenement(String type) {
        this.type = type;
    }

    /**
     *
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
