package Model;

import java.sql.Date;

/**
 * Created by D.Hamel on 26.03.17.
 */
public class Evenement {

    private int id;
    private String description;
    private java.sql.Date date;
    private int type;

    public Evenement(int id, String description, java.sql.Date date, int type) {
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

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
