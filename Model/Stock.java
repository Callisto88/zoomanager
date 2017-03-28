/**
 * Created by D.Hamel on 26.03.17.
 */
public class Stock {

    private String nom;
    private double quantite;
    private double quantitieMin;
    private String unite;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getQuantitieMin() {
        return quantitieMin;
    }

    public void setQuantitieMin(double quantitieMin) {
        this.quantitieMin = quantitieMin;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
}
