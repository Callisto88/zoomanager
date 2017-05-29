package Model;

/**
 *
 * <p>Cette classes contient la représentation objet de la table Animal_Evenement de la base de données</p>
 *
 * animal       références la classe "Animal"
 * evenement    références la classe "Evenement"
 *
 * @author D.Hamel
 * @author C.Balboni
 *
 * @version 1.0
 */
public class Animal_Evenement {

    private int id;
    private Animal animal; // REF Infrastrucutre(id)
    private Evenement evenement; // REF Evenement(id)

    public Animal_Evenement(int id, Animal animal, Evenement evenement) {
        this.id = id;
        this.animal = animal;
        this.evenement = evenement;
    }

    public Animal_Evenement(Animal animal, Evenement evenement) {
        this.animal = animal;
        this.evenement = evenement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    @Override
    public String toString() {
        return "Animal_Evenement{" +
                "animal=" + animal.toString() +
                ", evenement=" + evenement.toString() +
                '}';
    }
}
