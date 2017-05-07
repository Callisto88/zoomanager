package Model;

/**
 *
 * Cette classe contient la conception de la table Ville de la base de données
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * paysId    références la classe "Pays"
 *
 * @author D.Hamel
 *
 * @version 1.0
 *
 * @date    22.04.2017 (Création)
 * @date    22.04.2017 (Finalisation v1.0)
 * @date 28.04.2017 (Référence à un objet Pays plutôt qu'à son id)
 *
 */
public class Ville {

    /**
     * Membres privés
     */
    private int id;
    private int cp;
    private String ville;
    private Pays pays;
    /**
     * Constructeur par défaut
     */
    public Ville() {
    }
    /**
     * Constructeur à partir du nom de la ville
     */
    public Ville(String villeName) {

    }

    /**
     * Constructeur avec tous les membres
     * @param ville
     * @param cp
     * @param pays
     */
    public Ville(int cp, String ville, Pays pays) {
        this.ville = ville;
        this.cp = cp;
        this.pays = pays;
    }

    /**
     * Constructeur avec tous les membres
     *
     * @param id
     * @param cp
     * @param ville
     * @param pays
     */
    public Ville(int id, int cp, String ville, Pays pays) {
        this.id = id;
        this.cp = cp;
        this.ville = ville;
        this.pays = pays;
    }

    public Ville(int villeId, int codePostal) {
        this.id = villeId;
        this.cp = codePostal;
    }

    public Ville(int villeId, int codePostal, String ville) {
        this.id = villeId;
        this.cp = codePostal;
        this.ville = ville;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
     * @param ville String
     */
    public void setVille(String ville) {
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
     * Getter pays
     * @return pays
     */
    public Pays getPays() {
        return pays;
    }

    /**
     * Setter pays référence sur la table Pays
     * @param pays int
     */
    public void setPays(Pays pays) {
        this.pays = pays;
    }
}
