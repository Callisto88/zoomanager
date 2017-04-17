package Model;

import java.util.Vector;

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
 *
 */
public class Stock {

    /**
     * Membres privés
     */
    private String nom;
    private double quantite;
    //private double ordered;
    private double quantiteMin;
    private String unite;
    private double commande;

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

        double temp = quantiteMin - quantite;
        if(temp > 0){
            this.commande = temp;
        }else{
            this.commande = 0;
        }

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

    public double getCommande(){
        return commande;
    }

    public Vector<Object> toVector(){
        Vector<Object> vStock = new Vector<Object>();
        vStock.add(getNom());
        vStock.add(getQuantite());
        vStock.add(getQuantiteMin());
        vStock.add(getUnite());
        vStock.add(getCommande());

        return vStock;
    }

}
