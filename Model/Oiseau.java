package Model;

import java.sql.Date;

/**
 * Cette classe contient la conception de la table Oiseau de la base de données
 * <p>
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * @author D.Hamel
 * @author C.Balboni
 * @version 1.1
 * @date 28.03.2017 (Création)
 * @date 28.03.2017 (Finalisation v1.0)
 * @date 10.04.2017 (Ajouts des constructeurs et des commentaires JavaDoc)
 * @date 10.04.2017 (Finalisation de la v1.1
 */
public class Oiseau extends Animal {

    /**
     * MEMBRES PRIVES
     */
    private double envergure;
    private String bague;

    /**
     * Constructeurs
     */

    /**
     * Constructeur par défaut
     */
    public Oiseau() {
    }

    /**
     * Constructeur avec tous les membres sauf ID
     *
     * @param nom
     * @param sexe
     * @param anneeNaissance
     * @param enclos
     * @param origine
     * @param race
     * @param dateDeces
     */
    public Oiseau(String nom, String sexe, java.sql.Date anneeNaissance, int enclos, String origine, String race,
                  java.sql.Date dateDeces, double envergure, String bague) {
        super(nom, sexe, anneeNaissance, enclos, origine, race, dateDeces);
        this.envergure = envergure;
        this.bague = bague;
    }


    /**
     * GETTERS & SETTERS
     *
     * @return
     */
    public double getEnvergure() {
        return envergure;
    }

    public void setEnvergure(double envergure) {
        this.envergure = envergure;
    }

    public String getBague() {
        return bague;
    }

    public void setBague(String bague) {
        this.bague = bague;
    }
}
