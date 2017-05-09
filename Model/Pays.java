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
    private int paysId;
    private String pays;

    /**
     * Constructeur par défaut
     */
    public Pays() {
    }

    /**
     * Constructeur avec tous les membres
     *
     * @param paysId
     * @param pays
     */
    public Pays(int paysId, String pays) {
        this.paysId = paysId;
        this.pays = pays;
    }

    /**
     * Getter paysId
     * @return int paysId
     */
    public int getPaysId() {
        return paysId;
    }

    /**
     * Setter paysId
     * @param paysId int
     */
    public void setPaysId(int paysId) {
        this.paysId = paysId;
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

    @Override
    public String toString() {
        return pays;
    }
}
