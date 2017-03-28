package Model;

/**
 * Created by D.Hamel on 26.03.17.
 */
public class Intervenant_Evenement {

    private int id;
    private int intervenant; // REF Intervenant(id)
    private int evenement; // REF Evenement(id)

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
