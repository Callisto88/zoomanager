package Model;

import java.sql.Date;
import java.util.Vector;

/**
 *
 * Cette classe contient la conception de la table Personne de la base de données
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * responsable     références la classe "Personne"
 *
 * @author D.Hamel
 * @author C.Balboni
 *
 * @version 1.0
 */
public class Personne {

    private int idPersonne;
    private String noAVS;
    private String prenom;
    private String nom;
    private Adresse adresse;
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
    public Personne(int idPersonne, String noAVS, String prenom, String nom, Adresse adresse, String email,
                    String telephone, java.sql.Date dateNaissance, int responsable, String statut,
                    java.sql.Date dateDebut, String typeContrat) {
        this.idPersonne = idPersonne;
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
     *
     * @param noAVS
     * @param prenom
     * @param nom
     * @param adresse
     * @param email
     * @param telephone
     * @param dateNaissance
     * @param responsable
     * @param statut
     * @param dateDebut
     * @param typeContrat
     */
    public Personne(String noAVS, String prenom, String nom, Adresse adresse, String email, String telephone,
                    java.sql.Date dateNaissance, int responsable, String statut, java.sql.Date dateDebut,
                    String typeContrat) {
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

    public Personne(int idPersonne, String noAVS, String prenom, String nom, String email, String telephone, Date dateNaissance, int responsable, String statut, Date dateDebut, String typeContrat) {
        this.idPersonne = idPersonne;
        this.noAVS = noAVS;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.responsable = responsable;
        this.statut = statut;
        this.dateDebut = dateDebut;
        this.typeContrat = typeContrat;
    }

    public Personne(int personneID) {
        this.idPersonne = personneID;
    }

    public static void afficherPersonne(Personne p) {

        System.out.println("ID Personne" + p.idPersonne);
        System.out.println("noAVS : " + p.noAVS);
        System.out.println("Pénom : " + p.prenom);
        System.out.println("Nom : " + p.nom);
        System.out.println("Adresse : " + p.adresse.getAdresse());
        System.out.println("Email : " + p.email);
        System.out.println("Téléphone : " + p.telephone);
        System.out.println("Date de naissance : " + p.dateNaissance);
        System.out.println("Responsable : " + p.responsable);
        System.out.println("Statut : " + p.statut);
        System.out.println("Date de début : " + p.dateDebut);
        System.out.println("Type de contrat : " + p.typeContrat);
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String toString() {

        return this.prenom + " " + this.nom + "\n" + this.adresse + "\n" + this.dateNaissance;
    }
    public String getNoAVS() {
        return noAVS;
    }

    public void setNoAVS(String noAVS) {
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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
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

    public Vector<Object> toVector(){
        Vector<Object> vObjet = new Vector<>();
        vObjet.add(nom);
        vObjet.add(prenom);
        vObjet.add(noAVS);
        //vObjet.add(adresse);
        //vObjet.add(email);
        //vObjet.add(telephone);
        vObjet.add(dateNaissance);
        //vObjet.add(responsable);
        //vObjet.add(statut);
        //vObjet.add(dateDebut);
        //vObjet.add(typeContrat);
        return vObjet;
    }
}
