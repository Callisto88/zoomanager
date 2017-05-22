package Model;

import java.sql.Date;

/**
 *
 * Cette classe contient la conception de la table Felin de la base de données
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
 * @date    17.04.2017 (Modifié)
 *
 */
public class Felin extends Animal {
    /**
     * Membres privés
     */
    private double poids;

    /*
     *  CONSTRUCTEURS par defaut
     */
    public Felin() {}

    /*
     *  CONSTRUCTEURS avec tous les parametres
     */
    public Felin(int id, String nomCommun, String nom, String sexe, java.sql.Date anneeNaissance, int enclos, int origine,
                 int race, java.sql.Date dateDeces, double poids) {
        super(id, nomCommun, nom, sexe, anneeNaissance, enclos, origine, race, dateDeces);
        this.poids = poids;
    }

    /*
     *  CONSTRUCTEURS avec tous les parametres
     */
    public Felin(String nomCommun, String nom, String sexe, java.sql.Date anneeNaissance, int enclos, int origine,
                 int race, java.sql.Date dateDeces, double poids) {
        super(nomCommun, nom, sexe, anneeNaissance, new Enclos(enclos), new Pays(origine), new Race(race), dateDeces);
        this.poids = poids;
    }

    /**
     * Getter poids  Felin
     * @return poids double
     */
    public double getPoids() {
        return this.poids;
    }

    /**
     * Setter poids
     * @param poids double
    */
    public void setPoids(double poids) {
        this.poids = poids;
    }
}




