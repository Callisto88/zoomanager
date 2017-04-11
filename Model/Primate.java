package Model;

import java.util.Date;

/**
 * Cette classe contient la conception de la table Primate de la base de données
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * @author D.Hamel
 * @author C.Balboni
 * @version 1.1
 * @date 28.03.2017 (Création)
 * @date 28.03.2017 (Finalisation v1.0)
 * @date 10.04.2017 (Ajout des constructeurs et commentaires JavaDoc)
 * @data 10.04.2017 (Finalisation v1.1)
 */
public class Primate extends Animal {

    /**
     * MEMBRES PRIVES
     */
    private double temperature;

    /**
     * Constructeurs
     */

    /**
     * Constructeur par défaut
     */
    public Primate() {
    }

    /**
     * Constructeur à partir de l'ID du primate
     * Récupère toutes les informations connu pour le primate portant l'id
     *
     * @param id
     */
    public Primate(int id) {
        super(id);
    }

    /**
     * Constructeur à partir de la température du primate
     * Instancie un nouveau primate avec sa température
     *
     * @param temperature
     */
    public Primate(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Constructeur avec tous les paramètres du parent ( Animal ) et du primate
     *
     * @param nom
     * @param sexe
     * @param anneeNaissance
     * @param enclos
     * @param origine
     * @param race
     * @param dateDeces
     * @param temperature
     */
    public Primate(String nom, String sexe, Date anneeNaissance, int enclos, String origine, String race, Date dateDeces, double temperature) {
        super(nom, sexe, anneeNaissance, enclos, origine, race, dateDeces);
        this.temperature = temperature;
    }

    /**
     * Getters & Setters propres aux primates
     *
     * @return
     */
    public double getTemperature() {
        return temperature;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
