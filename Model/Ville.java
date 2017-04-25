package Model;

/**
 *
 * Cette classe contient la conception de la table Ville de la base de données
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * pays_id    références la classe "Pays"
 *
 * @author D.Hamel
 *
 * @version 1.0
 *
 * @date    22.04.2017 (Création)
 * @date    22.04.2017 (Finalisation v1.0)
 *
 */
public class Ville {
    /**
     * Membres privés
     */
    private int cp;
    private String ville;
    private int pays_id;

    /**
     * Constructeur avec tous les membres
     * @param ville
     * @param cp
     * @param pays_id
     */
    public Ville( int cp, String ville, int pays_id) {
        this.ville = ville;
        this.cp = cp;
        this.pays_id = pays_id;
    }

    /**
     * Getter Ville
     * @return String ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * Setter ville
     * @param ville int
     */
    public void setPays(String ville) {
        this.ville = ville;
    }

    /**
     * Getter code postal
     * @return int cp
     */
    public int getCp() {
        return cp;
    }

    /**
     * Setter code postal
     * @param cp int
     */
    public void setCp(int cp) {
        this.cp = cp;
    }

    /**
     * Getter pays ID référence sur la table Pays
     * @return int pays_id
     */
    public int getPays_id() {
        return pays_id;
    }

    /**
     * Setter pays ID référence sur la table Pays
     * @param pays_id int
     */
    public void setPays_id(int pays_id) {
        this.pays_id = pays_id;
    }
}
