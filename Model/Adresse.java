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
    private int codePostal;

    /**
     * Constructeur par défaut
     */
    public Adresse() {}

    /**
     * Constructeur avec tous les attributs sauf l'ID
     * @param adresse
     * @param codePostal
     */
    public Adresse(String adresse, int codePostal) {
        this.id = id;
        this.adresse = adresse;
        this.codePostal = codePostal;
    }

    /**
     * Constructeur avec tous les attributs
     * @param id
     * @param adresse
     * @param codePostal
     */
    public Adresse(int id, String adresse, int codePostal) {
        this.id = id;
        this.adresse = adresse;
        this.codePostal = codePostal;
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

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }
}
