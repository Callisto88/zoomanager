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
    private double temperature;

    /*
     *  CONSTRUCTEURS
     */
    public Reptile() {
    }

    /*
     *  CONSTRUCTEURS avec tous les attributs
     */
    public Reptile(String nom, String sexe, Date anneeNaissance, int enclos, int origine, int race, Date dateDeces, int id, double temperature) {
        super(id, nom, sexe, anneeNaissance, enclos, origine, race, dateDeces);
        this.temperature = temperature;
    }

    /*
     *  CONSTRUCTEURS avec tous les attributs sauf l'ID
     */
    public Reptile(String nom, String sexe, Date anneeNaissance, int enclos, int origine, int race, Date dateDeces, double temperature) {
        super(nom, sexe, anneeNaissance, enclos, origine, race, dateDeces);
        this.temperature = temperature;
    }

    /*
     *  GETTERS
     */
    public double getTemperature() {
        return temperature;
    }

    /*
     *  SETTERS
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
