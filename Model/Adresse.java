package Model;

/**
 * Created by lapin on 28.04.17.
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
                ", ville=" + ville.getVille() +
                '}';
    }
}
