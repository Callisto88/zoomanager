package Model;

import java.sql.Date;
import java.util.Vector;

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
 * @date    17.04.2017 (Modifié - Type de "race" passe de "String" à "int"
 *
 */
public class Animal {

    /**
     * Membres privés
     */
    private int id;
    private String nom;
    private String nomCommun;
    private String sexe;
    private Date dateNaissance;
    private int enclos;     // REF (id)
    private int origine;    // REF Pays(pays_id)
    private int race;       // REF (nom)
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

    public Animal(int id, String nomCommun, String nom, String sexe, java.sql.Date anneeNaissance, int enclos,
                  int origine, int race, java.sql.Date dateDeces) {
        this.id = id;
        this.nom = nom;
        this.nomCommun = nomCommun;
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
    public Animal(String nomCommun, String nom, String sexe, java.sql.Date anneeNaissance,
                  int enclos, int origine, int race, java.sql.Date dateDeces) {
        this.nom = nom;
        this.nomCommun = nomCommun;
        this.sexe = sexe;
        this.dateNaissance = anneeNaissance;
        this.enclos = enclos;
        this.origine = origine;
        this.race = race;
        this.dateDeces = dateDeces;
    }

    public Animal(String nomCommun, String nom, String sexe, java.sql.Date anneeNaissance,
                  int enclos, int origine, int race) {
        this.nom = nom;
        this.nomCommun = nomCommun;
        this.sexe = sexe;
        this.dateNaissance = anneeNaissance;
        this.enclos = enclos;
        this.origine = origine;
        this.race = race;
    }

    public Animal(int id, String nomCommun, String nom, String sexe, java.sql.Date anneeNaissance) {
        this.id = id;
        this.nomCommun = nomCommun;
        this.nom = nom;
        this.sexe = sexe;
        this.dateNaissance = anneeNaissance;
    }

    public Animal(int id, String nomCommun, String nom, String sexe, java.sql.Date anneeNaissance, int race) {
        this.id = id;
        this.nomCommun = nomCommun;
        this.nom = nom;
        this.sexe = sexe;
        this.dateNaissance = anneeNaissance;
        this.race = race;
    }

    public String getNomCommun() {
        return nomCommun;
    }

    public void setNomCommun(String nomCommun) {
        this.nomCommun = nomCommun;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
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

    public int getOrigine() {
        return origine;
    }

    public void setOrigine(int origine) {
        this.origine = origine;
    }

    public int getRace() {
        return race;
    }

    public void setRace(int race) {
        this.race = race;
    }

    public java.sql.Date getDateDeces() {
        return dateDeces;
    }

    public void setDateDeces(java.sql.Date dateDeces) {
        this.dateDeces = dateDeces;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public java.sql.Date getAnneeNaissance() {
        return dateNaissance;
    }

    public void setAnneeNaissance(java.sql.Date anneeNaissance) {
        this.dateNaissance = anneeNaissance;
    }

    public int getEnclos() {
        return enclos;
    }

    public void setEnclos(int enclos) {
        this.enclos = enclos;
    }

    public String toString() {

        System.out.println("-----------------------------");
        System.out.printf("%-10s : %s\n", "Nom", this.nom);
        System.out.printf("%-10s : %s\n", "Sexe", this.sexe);
        System.out.printf("%-10s : %s\n", "Naissance", this.dateNaissance);
        System.out.printf("%-10s : %s\n", "Enclos", this.enclos);
        System.out.printf("%-10s : %s\n", "Origine", this.origine);
        System.out.printf("%-10s : %s\n", "Race", this.race);
        System.out.printf("%-10s : %s\n", "Décès", this.dateDeces);
        System.out.println("-----------------------------\n");
        return null;
    }

    public Vector<Object> toVector(int returnLength) {

        Vector<Object> vAnimal = new Vector<>();

        switch(returnLength){
            case 0 :
                vAnimal.add(this.getId());
                vAnimal.add(this.getNom());
                vAnimal.add(this.getSexe());
                vAnimal.add(this.getRace());
                vAnimal.add(this.getAnneeNaissance());
                vAnimal.add(this.getEnclos());
                vAnimal.add(this.getOrigine());
                vAnimal.add(this.getDateDeces());
                break;
            case 1:
                vAnimal.add(this.getNom());
                vAnimal.add(this.getRace());
                vAnimal.add(this.getSexe());
                vAnimal.add(this.getAnneeNaissance());
                break;
            default:
                break;
        }

        return vAnimal;
    }

    public Vector<Object> toVector() {

        Vector<Object> vAnimal = new Vector<>();

        vAnimal.add(this.getId());
        vAnimal.add(this.getNom());
        vAnimal.add(this.getSexe());
        vAnimal.add(this.getRace());
        vAnimal.add(this.getAnneeNaissance());
        vAnimal.add(this.getEnclos());
        vAnimal.add(this.getOrigine());
        vAnimal.add(this.getDateDeces());

        return vAnimal;
    }
}
