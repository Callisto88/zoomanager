package Model;

/**
 *
 * Cette classes contient la conception de la table Intervenant_Evenement de la base de données
 *
 * Contient l'id des intervenant qui ont participé à un événement (décrit par son id également)
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * intervenant      références la classe "Intervenant"
 * evenement        références la classe "Evenement"
 *
 * @author D.Hamel
 * @author C.Balboni
 *
 * @version 1.0
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
