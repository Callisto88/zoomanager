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
    /**
     * Membres privés
     */
    private int id;
    private String nom;
    private Personne responsable;


    /**
     * Constructeur avec tous les parametres
     * @param id
     * @param nom
     * @param responsable
     */
    public Secteur(int id, String nom, Personne responsable) {
        this.id = id;
        this.nom = nom;
        this.responsable = responsable;
    }

    public Secteur(int id) {
        this.id = id;
    }

    /**
     * Getter id Secteur
     * @return id int
     */
    public int getId() {
        return id;
    }

    /**
     * Setter id
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter nom Secteur
     * @return nom String
     */
    public String getNom() {
        return nom;
    }


    /**
     * Setter nom
     * @param nom String
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter responsable Secteur
     * @return responsable int
     */
    public Personne getResponsable() {
        return responsable;
    }

    /**
     * Setter responsable
     * @param responsable int
     */
    public void setResponsable(Personne responsable) {
        this.responsable = responsable;
    }

    @Override
    public String toString() {
        return "Secteur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", responsable=" + responsable.toString() +
                '}';
    }
}
