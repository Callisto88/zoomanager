package Model;

/**
 *
 * Cette classe contient la conception de la table Race de la base de données
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * @author D.Hamel
 * @author C.Balboni
 *
 * @version 1.0
 */
public class Race {

    /**
     * Membres privés
     */
    private int id;
    private String nom;

    /**
     * Constructeur par défaut
     */
    public Race() {
    }

    public Race(int id) {
        this.id = id;
    }

    /**
     * Constructeurs avec tous les attributs
     *
     * @param id  un entier
     * @param nom une String
     */
    public Race(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    /**
     * Retoure l'ID de la race
     *
     * @return un entier
     */
    public int getId() {
        return id;
    }

    /**
     * Définis l'ID de la race
     *
     * @param id un entier
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retourne le nom de la race
     *
     * @return une String
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définis le nom de la race
     * @param nom une String
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Race{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
