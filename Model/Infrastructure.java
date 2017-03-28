package Model;

/**
 * Created by D.Hamel on 26.03.17.
 */
public abstract class Infrastructure {

    private int id;
    private String nom;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
