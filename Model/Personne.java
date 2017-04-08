package Model;

import java.util.Date;

/**
 * Created by D.Hamel on 25.03.17.
 */
public class Personne {

    private int noAVS;
    private String prenom;
    private String nom;
    private int adresse;
    private String email;
    private String telephone;
    private java.sql.Date dateNaissance;
    private int responsable;
    private String statut;
    private java.sql.Date dateDebut;
    private String typeContrat;

    /**
     * Constructeur par défaut
     */
    public Personne() {
    }

    /**
     * Constructeur avec tous les paramètre
     *
     */
    public Personne(int noAVS, String prenom, String nom, int adresse, String email,
                    String telephone, java.sql.Date dateNaissance, int responsable, String statut, java.sql.Date dateDebut, String typeContrat) {
        this.noAVS = noAVS;
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.responsable = responsable;
        this.statut = statut;
        this.dateDebut = dateDebut;
        this.typeContrat = typeContrat;
    }

    /**
     * Constructeur avec unqiuement les noms prenoms
     * @param prenom
     * @param nom
     */
    public Personne(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public String toString() {
        return this.prenom + " " + this.nom + "\n" + this.adresse + "\n" + this.dateNaissance;
    }
    public int getNoAVS() {
        return noAVS;
    }

    public void setNoAVS(int noAVS) {
        this.noAVS = noAVS;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAdresse() {
        return adresse;
    }

    public void setAdresse(int adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public java.sql.Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(java.sql.Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getResponsable() {
        return responsable;
    }

    public void setResponsable(int responsable) {
        this.responsable = responsable;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public java.sql.Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(java.sql.Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }
}
