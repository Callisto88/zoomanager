package Model;

import java.util.Date;

public class Reptile extends Animal {

    /*
     *  MEMBRES PRIVES
     */
    private float temperature;

    /*
     *  CONSTRUCTEURS
     */

    // Par défaut
    public Reptile() {
    }

    // Avec tous les attributs
    public Reptile(String nom, String sexe, Date anneeNaissance, int enclos, String origine, String race, Date dateDeces, float temperature) {
        super(nom, sexe, anneeNaissance, enclos, origine, race, dateDeces);
        this.temperature = temperature;
    }

    /*
     *  GETTERS
     */
    public float getTemperature() {
        return temperature;
    }

    /*
     *  SETTERS
     */
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
