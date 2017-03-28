/**
 * Created by D.Hamel on 26.03.17.
 */
public class Enclos {

    private int id;
    private int nom;
    private int secteur; // REF Secteur(id)
    private String surface;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNom() {
        return nom;
    }

    public void setNom(int nom) {
        this.nom = nom;
    }

    public int getSecteur() {
        return secteur;
    }

    public void setSecteur(int secteur) {
        this.secteur = secteur;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }
}
