/**
 * Created by D.Hamel on 26.03.17.
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
