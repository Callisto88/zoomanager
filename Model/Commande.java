package Model;

import java.sql.Date;

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
 * @date    17.04.2017 (Changement a la mise à jour de la DB - nouveau parametre "statut")
 *
 */
public class Commande {

    /**
     * Membres privés
     */
    private int id;
    private String statut;
    private java.sql.Date date;

    /**
     * Constructeur par défaut
     */
    public Commande() {}

    /**
     * Constructeur
     * @param id
     * @param statut
     * @param date
     */
    public Commande(int id, String statut, Date date) {
        this.id = id;
        this.statut = statut;
        this.date = date;
    }

    /**
     * Constructeur
     * @param id
     * @param date
     */
    public Commande(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    /**
     * Constructeur
     * @param id
     */
    public Commande(int id) {
        this.id = id;
    }


    /**
     * Getter id Commande
     * @return double
     */
    public int getId() {
        return id;
    }

    /**
     * Setter id
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter date Commande
     * @return java.sql.Date
     */
    public java.sql.Date getDate() {
        return date;
    }

    /**
     * Setter Date
     * @param date java.sql.Date
     */
    public void setDate(java.sql.Date date) {
        this.date = date;
    }


    /**
     * Getter statut Commande
     * @return String
     */
    public String getStatut() {
        return statut;
    }

    /**
     * Setter statut
     * @param statut String
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }
}

