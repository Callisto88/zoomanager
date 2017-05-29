package Model;

/**
 * <p>Cette classes a pour but de représenter un objet Adresse avec tous ses attributs</p>
 *
 * @author C.Balboni
 * @author D.Hamel
 * @version 1.0
 * @date 28.03.2017 (Création)
 * @date 05.05.2017 (Finalisation v1.0)
 * @date 29.05.2017 (Nettoyage complet du code source)
 */
public class Adresse {

    /**
     * Membres privés
     */
    private int id;
    private String adresse;
    private Ville ville;

    /**
     * Constructeur par défaut
     */
    public Adresse() {}

    /**
     * Constructeur via ID de l'adresse
     *
     * @param id
     */
    public Adresse(int id) {
        this.id = id;
    }

    /**
     * Constructeur avec tous les attributs sauf l'ID
     * @param adresse
     * @param ville
     */
    public Adresse(String adresse, Ville ville) {
        this.adresse = adresse;
        this.ville = ville;
    }

    /**
     * Constructeur avec tous les attributs
     * @param id
     * @param adresse
     * @param ville
     */
    public Adresse(int id, String adresse, Ville ville) {
        this.id = id;
        this.adresse = adresse;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", adresse='" + adresse + '\'' +
                ", ville=" + ville.toString() +
                '}';
    }
}
