package Model;

/**
 *
 * Cette classes contient la conception de la table Personne_Evenement de la base de données
 *
 * Contient l'id des Personne qui ont participé à un événement (décrit par son id également)
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * personne         références la classe "Personne"
 * evenement        références la classe "Evenement"
 *
 * @author D.Hamel
 * @author C.Balboni
 *
 * @version 1.0
 */
public class Personne_Evenement {
    private int id;
    private int personne; // REF Personne(noAVS)
    private int evenement; // REF Evenement(id)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonne() {
        return personne;
    }

    public void setPersonne(int personne) {
        this.personne = personne;
    }

    public int getEvenement() {
        return evenement;
    }

    public void setEvenement(int evenement) {
        this.evenement = evenement;
    }
}
