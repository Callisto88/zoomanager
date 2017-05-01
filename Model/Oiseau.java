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
 * @date 10.04.2017 (Finalisation de la v1.1)
 * @date 27.04.2017 (Ajout de l'attribut nomCommun, nettoyage du code et des commentaires)
 */
public class Oiseau extends Animal {

    /**
     * Membres privés
     */
    private double envergure;
    private String bague;

    /**
     * Constructeur par défaut
     */
    public Oiseau() {
    }

    /**
     * Constructeur à partir de l'ID de l'animal
     *
     * @param id
     */
    public Oiseau(int id) {
        super(id);
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
     * @param envergure
     * @param bague
     */
    public Oiseau(int id, String nomCommun, String nom, String sexe, java.sql.Date anneeNaissance, int enclos, int origine, int race,
                  java.sql.Date dateDeces, double envergure, String bague) {
        super(id, nomCommun, nom, sexe, anneeNaissance, enclos, origine, race, dateDeces);
        this.envergure = envergure;
        this.bague = bague;
    }

    /**
     * Constructeur avec tous les membres sauf ID
     *
     * @param nomCommun
     * @param nom
     * @param sexe
     * @param anneeNaissance
     * @param enclos
     * @param origine
     * @param race
     * @param dateDeces
     */
    public Oiseau(String nomCommun, String nom, String sexe, java.sql.Date anneeNaissance, int enclos, int origine, int race,
                  java.sql.Date dateDeces, double envergure, String bague) {
        super(nomCommun, nom, sexe, anneeNaissance, enclos, origine, race, dateDeces);
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
