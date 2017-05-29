package Model;

/**
 *
 * Cette classes contient la conception de la table Infrastructure_Evenement de la base de données
 *
 * Contient l'id des infrastructure qui ont été utilisés pour un événement (décrit par son id également)
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * infrastrucutre   références la classe "Infrastructure"
 * evenement        références la classe "Evenement"
 *
 * @author D.Hamel
 * @author C.Balboni
 *
 * @version 1.0
 */
public class Infrastructure_Evenement {

    private int id;
    private int infrastrucutre;
    private int evenement;


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
