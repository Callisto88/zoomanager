/**
 * Created by D.Hamel on 26.03.17.
 */
public class Contenu_Commande {

    private int id;
    private String nom; // REF Stock(nom)
    private int commande; // REF Commande(id)

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
