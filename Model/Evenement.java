package Model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Vector;

/**
 *
 * Cette classe contient la conception de la table Enclos de la base de données
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * type   références la classe "TypeEvenement"
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
public class Evenement {

    private int id;
    private String description;
    private java.sql.Timestamp date;
    private String type;

    public Evenement(int id, String description, Timestamp date, String type) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.type = type;
    }

    public Evenement(String description, Timestamp date, String type) {
        this.description = description;
        this.date = date;
        this.type = type;
    }

    public Evenement(String description, String type) {
        this.description = description;
        this.type = type;
    }

    public Evenement(String description, Date date, String spectacle) {
        this.description = description;
        this.date = new Timestamp(date.getTime());
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Timestamp getDate() {
        return date;
    }

    public void setDate(java.sql.Timestamp date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Vector<Object> toVector(){
        Vector<Object> vObject = new Vector<>();
        vObject.add("" + date.getDay() + "/" + date.getMonth() + "/" + date.getYear());
        vObject.add("" + date.getHours() + "H" + date.getMinutes());
        vObject.add(description);
        return vObject;
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                '}';
    }
}
