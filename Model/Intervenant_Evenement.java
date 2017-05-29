package Model;

/**
 *
 * <p>Cette classes contient la conception de la table Intervenant_Evenement de la base de données</p>
 *
 * @author C.Balboni
 * @author D.Hamel
 *
 * @version 1.0
 */
public class Intervenant_Evenement {

    /**
     * Membres privés
     */
    private int id;
    private int intervenant;    // REF Intervenant(id)
    private int evenement;      // REF Evenement(id)

    /**
     * Getters & Setters
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnimal() {
        return intervenant;
    }

    public void setAnimal(int animal) {
        this.intervenant = animal;
    }

    public int getEvenement() {
        return evenement;
    }

    public void setEvenement(int evenement) {
        this.evenement = evenement;
    }
}
