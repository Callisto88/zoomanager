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
 *
 * @date    28.03.2017 (Création)
 * @date    28.03.2017 (Finalisation v1.0)
 * @date 27.04.2017 (Ajout de l'attribut ID + nettoyage du code et des commentaires + ajouts des constructeurs)
 *
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
}
