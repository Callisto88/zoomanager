package Model;

/**
 * Created by D.Hamel on 25.03.17.
 */

public abstract class Animal {

    /**
     * Membres privés
     */
    private int id;
    private String nom;
    private String sexe;
    private String anneeNaissance;
    private int enclos; // REF (id)
    private String origine;
    private String race; // REF (nom)
    private String dateDeces;

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

    /**
     * Constructeur avec tous les membres
     * @param id
     * @param nom
     * @param sexe
     * @param anneeNaissance
     * @param enclos
     * @param origine
     * @param race
     * @param dateDeces
     */
    public Animal(int id, String nom, String sexe, String anneeNaissance, int enclos, String origine, String race, String dateDeces) {
        this.id = id;
        this.nom = nom;
        this.sexe = sexe;
        this.anneeNaissance = anneeNaissance;
        this.enclos = enclos;
        this.origine = origine;
        this.race = race;
        this.dateDeces = dateDeces;
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

    public String getDateDeces() {
        return dateDeces;
    }

    public void setDateDeces(String dateDeces) {
        this.dateDeces = dateDeces;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getAnneeNaissance() {
        return anneeNaissance;
    }

    public void setAnneeNaissance(String anneeNaissance) {
        this.anneeNaissance = anneeNaissance;
    }

    public int getEnclos() {
        return enclos;
    }

    public void setEnclos(int enclos) {
        this.enclos = enclos;
    }
}
