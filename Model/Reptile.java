package Model;

import java.sql.Date;

/**
 *
 * Cette classe contient la conception de la table Reptile de la base de données
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
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
