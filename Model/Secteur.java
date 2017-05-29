package Model;

/**
 *
 * <p>Cette classe contient la représentation objet de la table Secteur de la base de données</p>
 *
 * <em>L'attribut <strong>responsable</strong> références la classe "Personne"</em>
 *
 * @author C.Balboni
 * @author D.Hamel
 *
 * @version 1.0
 */
public class Secteur {

    /**
     * Membres privés
     */
    private int id;
    private String nom;
    private Personne responsable;

    /**
     * Constructeur avec tous les parametres
     * @param id un entier représentant l'ID du secteur
     * @param nom une String indiquant le nom du secteur
     * @param responsable un objet de type Personne représentant le responsable du secteur
     */
    public Secteur(int id, String nom, Personne responsable) {
        this.id = id;
        this.nom = nom;
        this.responsable = responsable;
    }

    /**
     * Constructeur simple à partir de l'ID du secteur
     *
     * @param id
     */
    public Secteur(int id) {
        this.id = id;
    }

    /**
     * Getter id Secteur
     * @return id int
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
     * Getter nom Secteur
     * @return nom String
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter nom
     * @param nom String
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter responsable Secteur
     * @return responsable int
     */
    public Personne getResponsable() {
        return responsable;
    }

    /**
     * Setter responsable
     * @param responsable int
     */
    public void setResponsable(Personne responsable) {
        this.responsable = responsable;
    }

    @Override
    public String toString() {
        return "Secteur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", responsable=" + (responsable == null ? "" : responsable.toString()) +
                '}';
    }
}
