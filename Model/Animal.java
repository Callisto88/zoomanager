package Model;

import java.sql.Date;
import java.util.Vector;

/**
 * <p>Cette classes a pour but de représenter un objet Animal avec tous ses attributs</p>
 *
 * @author C.Balboni
 * @author D.Hamel
 * @version 1.0
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
    private Enclos enclos;      // REF (id)
    private Pays origine;       // REF Pays(paysId)
    private Race race;          // REF (raceId)
    private Date dateDeces;

    /**
     * Constructeur par défaut
     */
    public Animal() {
    }

    /**
     * Constructeur à partir de l'id de l'animal
     * @param id
     */
    public Animal(int id) {
        this.id = id;
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
     */
    public Animal(int id, String nomCommun, String nom, String sexe, java.sql.Date anneeNaissance, int enclos,
                  int origine, int race, java.sql.Date dateDeces) {
        this.id = id;
        this.nom = nom;
        this.nomCommun = nomCommun;
        this.sexe = sexe;
        this.dateNaissance = anneeNaissance;
        this.enclos = new Enclos(enclos);
        this.origine = new Pays(origine);
        this.race = new Race(race);
        this.dateDeces = dateDeces;
    }

    /**
     * Similaire au constructeur précédent, il utilise néanmoins les classes objets pour enclos, origine et race
     * @param id
     * @param nomCommun
     * @param nom
     * @param sexe
     * @param anneeNaissance
     * @param enclos
     * @param origine
     * @param race
     * @param dateDeces
     */
    public Animal(int id, String nomCommun, String nom, String sexe, java.sql.Date anneeNaissance, Enclos enclos,
                  Pays origine, Race race, java.sql.Date dateDeces) {
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
                  Enclos enclos, Pays origine, Race race, java.sql.Date dateDeces) {
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
     * Constructeur alternatif avec moins d'attributs
     * @param nomCommun
     * @param nom
     * @param sexe
     * @param anneeNaissance
     * @param enclos
     * @param origine
     * @param race
     */
    public Animal(String nomCommun, String nom, String sexe, java.sql.Date anneeNaissance,
                  Enclos enclos, Pays origine, Race race) {
        this.nom = nom;
        this.nomCommun = nomCommun;
        this.sexe = sexe;
        this.dateNaissance = anneeNaissance;
        this.enclos = enclos;
        this.origine = origine;
        this.race = race;
    }

    /**
     * Constructeur alternatif avec moins d'attributs
     * @param id
     * @param nomCommun
     * @param nom
     * @param sexe
     * @param anneeNaissance
     */
    public Animal(int id, String nomCommun, String nom, String sexe, java.sql.Date anneeNaissance) {
        this.id = id;
        this.nomCommun = nomCommun;
        this.nom = nom;
        this.sexe = sexe;
        this.dateNaissance = anneeNaissance;
    }

    /**
     * Constructeur alternatif avec moins d'attributs
     * @param id
     * @param nomCommun
     * @param nom
     * @param sexe
     * @param anneeNaissance
     * @param race
     */
    public Animal(int id, String nomCommun, String nom, String sexe, java.sql.Date anneeNaissance, Race race) {
        this.id = id;
        this.nomCommun = nomCommun;
        this.nom = nom;
        this.sexe = sexe;
        this.dateNaissance = anneeNaissance;
        this.race = race;
    }

    /**
     * Getters & Setters
     */

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
        return nom; }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Pays getOrigine() {
        return origine;
    }

    public void setOrigine(Pays origine) {
        this.origine = origine;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
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

    public Enclos getEnclos() {
        return enclos;
    }

    public void setEnclos(Enclos enclos) {
        this.enclos = enclos;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", nomCommun='" + nomCommun + '\'' +
                ", sexe='" + sexe + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", enclos=" + enclos +
                ", origine=" + origine +
                ", race=" + race +
                ", dateDeces=" + dateDeces +
                '}';
    }

    public Vector<Object> toVector(int returnLength) {

        Vector<Object> vAnimal = new Vector<>();

        switch(returnLength){
            case 0 :
                vAnimal.add(this.getId());
                vAnimal.add(this.getNom());
                vAnimal.add(this.getSexe());
                vAnimal.add(this.getNomCommun());
                vAnimal.add(this.getRace().getNom());
                vAnimal.add(this.getAnneeNaissance());
                vAnimal.add(this.getEnclos().getNom());
                vAnimal.add(this.getOrigine().getPays());
                vAnimal.add(this.getDateDeces());
                break;
            case 1:
                vAnimal.add(this.getNom());
                vAnimal.add(this.getNomCommun());
                vAnimal.add(this.getRace().getNom());
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
        vAnimal.add(this.getNomCommun());
        vAnimal.add(this.getRace().getNom());
        vAnimal.add(this.getAnneeNaissance());
        vAnimal.add(this.getEnclos().getNom());
        vAnimal.add(this.getOrigine().getPays());
        vAnimal.add(this.getDateDeces());

        return vAnimal;
    }
}
