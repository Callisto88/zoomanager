package Model;

/**
 *
 * Cette classe contient la conception de la table Pays de la base de données
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * @author D.Hamel
 *
 * @version 1.0
 *
 * @date    22.04.2017 (Création)
 * @date    22.04.2017 (Finalisation v1.0)
 *
 */
public class Pays {
    /**
     * Membres privés
     */
    private int pays_id;
    private  String pays;

    /**
     * Constructeur avec tous les membres
     *
     * @param pays_id
     * @param pays
     */
    public Pays(int pays_id, String pays) {
        this.pays_id = pays_id;
        this.pays = pays;
    }

    /**
     * Getter pays_id
     * @return int pays_id
     */
    public int getPays_id() {
        return pays_id;
    }

    /**
     * Setter pays_id
     * @param pays_id int
     */
    public void setPays_id(int pays_id) {
        this.pays_id = pays_id;
    }

    /**
     * Getter pays
     * @return String pays
     */
    public String getPays() {
        return pays;
    }

    /**
     * Setter pays
     * @param pays String
     */
    public void setPays(String pays) {
        this.pays = pays;
    }
}
