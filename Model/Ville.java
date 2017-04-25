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
    private int ville_id;
    private String ville;
    private String cp;
    private int pays_id;

    /**
     * Constructeur avec tous les membres
     * @param ville_id
     * @param ville
     * @param cp
     * @param pays_id
     */
    public Ville(int ville_id, String ville, String cp, int pays_id) {
        this.ville_id = ville_id;
        this.ville = ville;
        this.cp = cp;
        this.ville_id = ville_id;
    }

    /**
     * Getter ville_id
     * @return int ville_id
     */
    public int getVille_id() {
        return ville_id;
    }

    /**
     * Setter ville_id
     * @param ville_id int
     */
    public void setVille_id(int ville_id) {
        this.ville_id = ville_id;
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
    public String getCp() {
        return cp;
    }

    /**
     * Setter code postal
     * @param cp int
     */
    public void setCp(String cp) {
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
