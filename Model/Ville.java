package Model;

/**
 *
 * <p>Cette classe contient la conception de la table Ville de la base de données</p>
 *
 * @author C. Balboni
 * @author D. Hamel
 * @version 1.0
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
     * Constructeur à partir de l'ID de la ville
     *
     * @param villeId
     */
    public Ville(int villeId) {
        this.id = villeId;
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

    /**
     * Constructeur alternatif
     *
     * @param villeId
     * @param codePostal
     */
    public Ville(int villeId, int codePostal) {
        this.id = villeId;
        this.cp = codePostal;
    }

    /**
     * Constructeur altnerativ
     *
     * @param villeId
     * @param codePostal
     * @param ville
     */
    public Ville(int villeId, int codePostal, String ville) {
        this.id = villeId;
        this.cp = codePostal;
        this.ville = ville;
    }


    /**
     * Getters & Setters
     */

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

    @Override
    public String toString() {
        return "Ville{" +
                "id=" + id +
                ", cp=" + cp +
                ", ville='" + ville + '\'' +
                ", pays=" + pays +
                '}';
    }
}
