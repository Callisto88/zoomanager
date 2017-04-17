package Model;

/**
 *
 * Cette classe contient la conception de la table Stock de la base de données
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
 * @date    17.04.2017 (Changement a la mise à jour de la DB - nouveau parametre "id")
 *
 *
 */
public class Stock {

    /**
     * Membres privés
     */
    private int id;
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
     * @param id
     * @param nom
     * @param quantite
     * @param quantiteMin
     * @param unite
     */
    public Stock(int id, String nom, double quantite, double quantiteMin, String unite) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.quantiteMin = quantiteMin;
        this.unite = unite;
    }

    /**
     * Constructeur avec tous les membres sauf l'ID. AUTO_INCREMENT
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
     * Getter ID
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter ID
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
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

    /**
     * Getter quantiteMin Stock
     * @return getQuantiteMin double
     */
    public double getQuantiteMin() {
        return quantiteMin;
    }


    /**
     * Setter nom
     * @param quantiteMin double
     */
    public void setQuantiteMin(double quantiteMin) {
        this.quantiteMin = quantiteMin;
    }

    /**
     * Getter unite Stock
     * @return unite String
     */
    public String getUnite() {
        return unite;
    }

    /**
     * Setter unite
     * @param unite String
     */
    public void setUnite(String unite) {
        this.unite = unite;
    }
}
