package Model;

/**
 * <p>Cette classe contient la représentation de la table Pays de la base de données</p>
 *
 * @author C. Balboni
 * @author D. Hamel
 *
 * @version 1.0
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

    public Pays(int paysId) {
        this.paysId = paysId;
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
        return "Pays{" +
                "paysId=" + paysId +
                ", pays='" + pays + '\'' +
                '}';
    }
}
