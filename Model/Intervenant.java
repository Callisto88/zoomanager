package Model;

import java.util.Vector;

/**
 *
 * Cette classe contient la conception de la table Intervenant de la base de données
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
 *
 */
public class Intervenant {

    private int id;
    private String entreprise;
    private String prenom;
    private String nom;
    private int adresse;
    private String email;
    private String telephone;

    /**
     * Constructeur d'un intervenant avec les paramètres principaux
     * @param entreprise nom de l'entreprise
     * @param nom nom de l'intervenant
     * @param prenom prénom de l'intervenant
     * @param adresse adresse de l'entreprise
     * @param email email de l'intervenant
     * @param telephone télephone de l'intervenant
     */
    public Intervenant(String entreprise, String nom, String prenom, int adresse, String email, String telephone){
        this.entreprise = entreprise;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
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

    public Vector<Object> toVector() {
        Vector<Object> vObjet = new Vector<>();
        vObjet.add(nom);
        vObjet.add(prenom);
        vObjet.add(entreprise);
        vObjet.add(telephone);
        return vObjet;
    }
}
