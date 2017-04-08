package Model;

/**
 *
 * Cette classes contient la conception de la table Animal_Evenement de la base de données
 *
 * Contient l'id des personne qui on participé à un événement (décrit par son id également)
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * animal       références la classe "Animal"
 * evenement    références la classe "Evenement"
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
