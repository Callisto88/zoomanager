package Model;

import java.sql.Date;

/**
 * Cette classe contient la conception de la table Primate de la base de données
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * @author D.Hamel
 * @author C.Balboni
 * @version 1.1
 *
 * @date 28.03.2017 (Création)
 * @date 28.03.2017 (Finalisation v1.0)
 * @date 10.04.2017 (Ajout des constructeurs et commentaires JavaDoc)
 * @data 10.04.2017 (Finalisation v1.1)
 * @date 27.04.2017 (Ajout de l'attribut nomCommun, nettoyage du code et des commentaires)
 */
public class Primate extends Animal {

    /**
     * Membres privés
     */
    private double temperature;

    /**
     * Constructeur par défaut
     * @param id
     * @param nomCommun
     * @param nom
     * @param sexe
     * @param dateNaissance
     * @param e
     * @param ps
     * @param r
     * @param dateDeces
     */
    public Primate(int id, String nomCommun, String nom, String sexe, Date dateNaissance, Enclos e, Pays ps, Race r, Date dateDeces) {
    }

    /**
     * Constructeur à partir de l'ID du primate
     * Récupère toutes les informations connu pour le primate portant l'id
     *
     * @param id un entier correspondant à l'ID de l'animal
     */
    public Primate(int id) {
        super(id);
    }

    /**
     * Constructeur à partir de la température du primate
     * Instancie un nouveau primate avec sa température
     *
     * @param temperature un double
     */
    public Primate(double temperature) {
        this.temperature = temperature;
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
    public Primate(int id, String nomCommun, String nom, String sexe, java.sql.Date anneeNaissance, Enclos enclos, Pays origine,
                   Race race, java.sql.Date dateDeces, double temperature) {
        super(id, nomCommun, nom, sexe, anneeNaissance, enclos, origine, race, dateDeces);
        this.temperature = temperature;
    }


    /**
     * Constructeur avec tous les attributs sauf ID
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
    public Primate(String nomCommun, String nom, String sexe, java.sql.Date anneeNaissance, Enclos enclos, Pays origine,
                   Race race, java.sql.Date dateDeces, double temperature) {
        super(nomCommun, nom, sexe, anneeNaissance, enclos, origine, race, dateDeces);
        this.temperature = temperature;
    }

    /**
     * Retourne la température du primate
     * @return un double
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Définis la température du primate
     *
     * @param temperature un double
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
