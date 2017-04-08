package Model;

/**
 *
 * Cette classe contient la conception de la table Secteur de la base de données
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * responsable     références la classe "Personne"
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
