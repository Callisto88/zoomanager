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
 *
 * @date    28.03.2017 (Création)
 * @date    28.03.2017 (Finalisation v1.0)
 *
 */
public class Enclos {

    private int id;
    private String nom;
    private int secteur;
    private String surface;

    public Enclos(int id, String nom, int secteur, String surface) {
        this.id = id;
        this.nom = nom;
        this.secteur = secteur;
        this.surface = surface;
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
