/**
 * Created by D.Hamel on 26.03.17.
 */
public class Animal_Evenement {

    private int id;
    private int animal; // REF Infrastrucutre(id)
    private int evenement; // REF Evenement(id)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnimal() {
        return animal;
    }

    public void setAnimal(int animal) {
        this.animal = animal;
    }

    public int getEvenement() {
        return evenement;
    }

    public void setEvenement(int evenement) {
        this.evenement = evenement;
    }
}
