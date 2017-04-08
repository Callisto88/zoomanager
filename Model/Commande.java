package Model;

import java.sql.Date;

/**
 * Created by D.Hamel on 26.03.17.
 */
public class Commande {
    private int id;
    private java.sql.Date date;

    public Commande(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Commande(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }
}
