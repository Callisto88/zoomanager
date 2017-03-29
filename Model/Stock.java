package Model;

/**
 * Created by D.Hamel on 26.03.17.
 */
public class Stock {

    /**
     * Membres privés
     */
    private String nom;
    private double quantite;
    private double quantiteMin;
    private String unite;

    /**
     * Constructeur par défaut
     */
    public Stock() {}

    /**
     * Constructeur avec tous les membres
     * @param nom
     * @param quantite
     * @param quantiteMin
     * @param unite
     */
    public Stock(String nom, double quantite, double quantiteMin, String unite) {
        this.nom = nom;
        this.quantite = quantite;
        this.quantiteMin = quantiteMin;
        this.unite = unite;
    }

    /**
     * Getter nom
     * @return String nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter nom
     * @param nom une String
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter quantité en stock
     * @return double
     */
    public double getQuantite() {
        return quantite;
    }

    /**
     * Setter quantité en stock
     * @param quantite double ( potentiellement en kilogramme )
     */
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getQuantiteMin() {
        return quantiteMin;
    }

    public void setQuantiteMin(double quantiteMin) {
        this.quantiteMin = quantiteMin;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
}
