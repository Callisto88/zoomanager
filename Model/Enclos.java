package Model;

/**
 *
 * Cette classe contient la conception de la table Enclos de la base de données
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * secteur    références la classe "Secteur"
 *
 * @author D.Hamel
 * @author C.Balboni
 *
 * @version 1.0
 */
public class Enclos extends Infrastructure {

    private int id;
    private String nom;
    private Secteur secteur;
    private double surface;

    public Enclos(int id, String nom, Secteur secteur, double surface) {
        this.id = id;
        this.nom = nom;
        this.secteur = secteur;
        this.surface = surface;
    }

    public Enclos(int id, String nom, int secteur, double surface) {
        this.id = id;
        this.nom = nom;
        this.secteur = new Secteur(secteur);
        this.surface = surface;
    }

    public Enclos(int id) {
        this.id = id;
    }

    public Enclos(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

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

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    @Override
    public String toString() {
        return "Enclos{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", secteur=" + secteur +
                ", surface=" + surface +
                '}';
    }
}
