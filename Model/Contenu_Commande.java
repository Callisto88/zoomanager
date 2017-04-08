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
    private String nom;
    private int commande;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCommande() {
        return commande;
    }

    public void setCommande(int commande) {
        this.commande = commande;
    }
}
