package Model;

/**
 *
 * Cette classes contient la conception de la table Contenu_Commande de la base de données
 *
 * Elle fait le lien entre l'id d'une commande et l'id d'un élément du stock qu'elle contient
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * nom         références la classe "Stock"
 * commande    références la classe "Commande"
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
public class Contenu_Commande {

    private int id;
    private int orderID;
    private int refArticle;
    private double quantite;

    public Contenu_Commande(int id, int orderID, int refArticle, double quantite) {
        this.id = id;
        this.orderID = orderID;
        this.refArticle = refArticle;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getRefArticle() {
        return refArticle;
    }

    public void setRefArticle(int refArticle) {
        this.refArticle = refArticle;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

}
