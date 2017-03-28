/**
 * Created by D.Hamel on 26.03.17.
 */
public class Infrastructure_Evenement {

    private int id;
    private int infrastrucutre; // REF Infrastrucutre(id)
    private int evenement; // REF Evenement(id)


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInfrastrucutre() {
        return infrastrucutre;
    }

    public void setInfrastrucutre(int infrastrucutre) {
        this.infrastrucutre = infrastrucutre;
    }

    public int getEvenement() {
        return evenement;
    }

    public void setEvenement(int evenement) {
        this.evenement = evenement;
    }
}
