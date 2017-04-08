package Model;

import java.util.Date;

/**
 * Cette classes contient la conception de la table Personne de la base de données
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * enclos   références la classe "Enclos"
 * race     références la classe "race"
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
public class Animal {

    /**
     * Membres privés
     */
    private int id;
    private String nom;
    private String sexe;
    private Date dateNaissance;
    private int enclos; // REF (id)
    private String origine;
    private String race; // REF (nom)
    private Date dateDeces;

    /**
     * Constructeur par défaut
     */
    public Animal() {
    }

    /**
     * Constructeur à partir de l'id de l'animal, permet de récupérer tous les attributs connus en base de données
     * @param id
     */
    public Animal(int id) {
        this.id = id;
    }

    public Animal(int id, String nom, String sexe, Date anneeNaissance, int enclos, String origine, String race, Date dateDeces) {
        this.id = id;
        this.nom = nom;
        this.sexe = sexe;
        this.dateNaissance = anneeNaissance;
        this.enclos = enclos;
        this.origine = origine;
        this.race = race;
        this.dateDeces = dateDeces;
    }

    /**
     * Constructeur avec tous les membres sauf ID
     * @param nom
     * @param sexe
     * @param anneeNaissance
     * @param enclos
     * @param origine
     * @param race
     * @param dateDeces
     */
    public Animal(String nom, String sexe, Date anneeNaissance, int enclos, String origine, String race, Date dateDeces) {
        this.nom = nom;
        this.sexe = sexe;
        this.dateNaissance = anneeNaissance;
        this.enclos = enclos;
        this.origine = origine;
        this.race = race;
        this.dateDeces = dateDeces;
    }

    public Animal(int id, String nom, String sexe, Date anneeNaissance) {
        this.id = id;
        this.nom = nom;
        this.sexe = sexe;
        this.dateNaissance = anneeNaissance;
    }

    public Animal(int id, String nom, String sexe, Date anneeNaissance, String race) {
        this.id = id;
        this.nom = nom;
        this.sexe = sexe;
        this.dateNaissance = anneeNaissance;
        this.race = race;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;

    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Date getDateDeces() {
        return dateDeces;
    }

    public void setDateDeces(Date dateDeces) {
        this.dateDeces = dateDeces;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getAnneeNaissance() {
        return dateNaissance;
    }

    public void setAnneeNaissance(Date anneeNaissance) {
        this.dateNaissance = anneeNaissance;
    }

    public int getEnclos() {
        return enclos;
    }

    public void setEnclos(int enclos) {
        this.enclos = enclos;
    }
}
