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
 */
public class Reptile extends Animal {

    /**
     * Membres privés
     */
    private double temperature;

    /**
     * Constructeur par défaut
     */
    public Reptile() {
    }

    /**
     * Constructeur avec tous les attributs
     *
     * @param id
     * @param nomCommun
     * @param nom
     * @param sexe
     * @param anneeNaissance
     * @param enclos
     * @param origine
     * @param race
     * @param dateDeces
     * @param temperature
     */
    public Reptile(int id, String nomCommun, String nom, String sexe, Date anneeNaissance, int enclos, int origine, int race, Date dateDeces, double temperature) {
        super(id, nomCommun, nom, sexe, anneeNaissance, enclos, origine, race, dateDeces);
        this.temperature = temperature;
    }

    /**
     * Constructeur avec tous les attributs sauf l'ID
     *
     * @param nomCommun
     * @param nom
     * @param sexe
     * @param anneeNaissance
     * @param enclos
     * @param origine
     * @param race
     * @param dateDeces
     * @param temperature
     */
    public Reptile(String nomCommun, String nom, String sexe, Date anneeNaissance, int enclos, int origine, int race, Date dateDeces, double temperature) {
        super(nomCommun, nom, sexe, anneeNaissance, new Enclos(enclos), new Pays(origine), new Race(race), dateDeces);
        this.temperature = temperature;
    }

    public Reptile(int id, String nomCommun, String nom, String sexe, Date dateNaissance, Enclos enclos, Pays origine, Race race, Date dateDeces, double temperature) {
        super(id, nomCommun, nom, sexe, dateNaissance, enclos, origine, race, dateDeces);
        this.temperature = temperature;
    }
    public Reptile(String nomCommun, String nom, String sexe, Date dateNaissance, Enclos enclos, Pays origine, Race race, Date dateDeces, double temperature) {
        super(nomCommun, nom, sexe, dateNaissance, enclos, origine, race, dateDeces);
        this.temperature = temperature;
    }

    /**
     * Retourne la température du reptile
     *
     * @return un double
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Définis la température du reptile
     *
     * @param temperature un double
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
