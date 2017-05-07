package Model;

import java.sql.Date;
import java.util.Vector;

/**
 *
 * Cette classes contient la conception de la table Commande de la base de données
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

enum Statut {
    CREEE,
    EN_COURS,
    TERMINEE,
    ANNULEE,
    REMBOURSEE
}

public class Commande {
    private int id;
    private java.sql.Date date;
    private Statut statut;

    public Commande(int id, Date date, Statut statut) {
        this.id = id;
        this.date = date;
        this.statut = statut;
    }

    public Commande(int id) {
        this.id = id;
    }

    public Commande(int id, Date orderDate, String statut) {
        this.id = id;
        this.date = orderDate;
        this.statut = Statut.valueOf(String.valueOf(statut));
    }

    /**
     * Retourne l'id de la commande courante
     *
     * @return un entier correspondant à l'id de la commande
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Permet de récupérer la date d'une commande
     * @return la date au format YYYY-MM-DD de la commande
     */
    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut; }

    public Vector<Object> toVector(){
        Vector<Object> vCommande = new Vector<Object>();
        vCommande.add(id);
        vCommande.add(date);
        vCommande.add(statut);

        return vCommande;
    }
}
