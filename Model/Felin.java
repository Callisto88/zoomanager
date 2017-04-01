package Model;

import java.util.Date;

/**
 * Created by D.Hamel on 26.03.17.
 */
public class Felin extends Animal {

    private float poids;

    public Felin(String nom, String sexe, Date anneeNaissance, int enclos, String origine, String race, Date dateDeces, float poids) {
        super(nom, sexe, anneeNaissance, enclos, origine, race, dateDeces);
        this.poids = poids;
    }

    public float getPoids() {
        return this.poids;
    }
}
