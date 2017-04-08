package Model;
import java.sql.SQLException;

/**
 * Created by D.Hamel on 26.03.17.
 * Modified by C.Balboni on 29.3.17
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
