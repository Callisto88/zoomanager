package Model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

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
    private int type;

    public Evenement(int id, String description, Timestamp date, int type) {
        this.id = id;
        this.description = description;
        this.date = date;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
