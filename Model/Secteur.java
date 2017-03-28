package Model;

/**
 * Created by D.Hamel on 26.03.17.
 */
public class Secteur {

    private int id;
    private String nom;
    private int responsable;

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

    public int getResponsable() {
        return responsable;
    }

    public void setResponsable(int responsable) {
        this.responsable = responsable;
    }
}
