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
    private int id;
    private String type;

    /**
     * Constructeur à partir de l'identifiant, remplit automatiquement les attributs
     * @param id un entier correspondant à l'ID du type d'événement
     */
    public TypeEvenement(int id) {
        this.id = id;

        // Récupération du type d'événement depuis la base de données
        DBInteraction db = new DBInteraction();
        try {
            this.type = db.selTypeEvenement(id);
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return un entier qui correspond à l'ID du type d'événement
     */
    public int getId() {
        return id;
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
