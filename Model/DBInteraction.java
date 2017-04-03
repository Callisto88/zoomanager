package Model;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

import static java.sql.Types.NULL;

/**
 * Created by D.Hamel on 28.03.17.
 */
public class DBInteraction {

    /**
     * Ci-dessous, Liste de toutes les requêtes possibles dans le programme !
     */

    // Recupère tous les paramètre d'une personne
    // 12 Paramètres
    private static final String SEL_ALL_PERSONNE = "SELECT * " +
            "FROM Personne;";
    // 12 Paramètres Dans l'ordre ci-dessous :
    // noAVS / nom / prenom / adresse / email / téléphone / dateNaissance /
    // idResponsable / statut / salaire / dateDebut	TypeContrat /
    private static final String INSERT_EMPLOYE = "INSERT INTO Personne VALUES (?, ? , ? , ? , ? , ? , ? , ? , ? ," +
            " ? , ? , ? ); ";
    // Recupère tous les paramètre des personnne répondant à la requête.
    // 12 Paramètres
    private static final String SEL_EMPLOYE_PAR_NOM = "SELECT * " +
            "FROM Personne " +
            "WHERE Personne.nom = ? ;";
    private static final String SEL_EMPLOYE_PAR_PRENOM_NOM = "SELECT * " +
            "FROM Personne " +
            "WHERE Personne.nom = ? " +
            " AND Personne.prenom = ? ;";
    private static final String SEL_ALL_RACE_ANIMAL = "SELECT nom " +
            "FROM Race;";
    private static final String NOMBRE_PERSONNE = "SELECT COUNT(*) as nbPersonne " +
            "FROM Personne;";
    private static final String SEL_TYPE_EVENEMENT = "SELECT type FROM TypeEvenement WHERE id = ? ;";
    private static final String SEL_EMPLOYE_DETAILS = "SELECT * FROM Personne WHERE noAVS = ? ;";
    private static final String INSERT_ANIMAL = "INSERT INTO Animal (id, nom, sexe, anneeNaissance, enclos, origine, deces) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_FELIN = "INSERT INTO felin (id, poids) VALUES (?, ?);";


    private DBConnection db;
    private PreparedStatement stmt;

    public DBInteraction() throws ExceptionDataBase {
        this.db = new DBConnection();
        this.db.init();
        this.stmt = null;
    }

    /**
     * Permet d'obtenir dans les noms et prenoms de tous les employés présents dans la base de données
     *
     * @return String[][] / Prénom en première position
     */
    public String[][] selAllPreNomPersonne() throws ExceptionDataBase, SQLException {
        ArrayList<Personne> personne = this.recupererPersonne(SEL_ALL_PERSONNE);
        int nbPersonne = personne.size();
        int nbParametre = 2; // nom + prenom
        String tableau [][];

        tableau = new String[nbPersonne][nbParametre];

        for (int i = 0; i < nbPersonne; i++) {
            tableau[i][0] = personne.get(i).getNom();
            tableau[i][1] = personne.get(i).getPrenom();
        }
        return tableau;
    }

    /**
     * Permet d'obtenir dans les noms et prenoms de tous les employés présents dans la base de données
     *
     * @param REQUETE qui est une chaine de caractère contenant la requete
     * @return ArrayList<Personne>
     */
    private ArrayList<Personne> recupererPersonne (final String REQUETE) throws ExceptionDataBase, SQLException {
        ArrayList<Personne> data = new ArrayList<>();
        this.stmt = db.con.prepareStatement(REQUETE);
        ResultSet rs = this.stmt.executeQuery();
        return this.creerTableauPersonne(rs);
    }

    /**
     * Permet d'obtenir dans les noms et prenoms de tous les employés présents dans la base de données
     *
     * @param noAVS             Nouveau numéro AVS de la personne
     * @param prenom            Nouveau prénom de la personne
     * @param nom               Nouveau nom AVS de la personne
     * @param adresse           Nouveau adresse AVS de la personne
     * @param email             Nouveau email AVS de la personne
     * @param telephone         Nouveau telephone AVS de la personne
     * @param dateNaissance     Nouveau dateNaissance AVS de la personne
     * @param responsable       Nouveau responsable AVS de la personne
     * @param statut            Nouveau statut AVS de la personne
     * @param salaire           Nouveau salaire AVS de la personne
     * @param dateDebut         Nouveau dateDebut AVS de la personne
     * @param typeContrat       Nouveau typeContrat AVS de la personne
     */
    public void insPersonne(int noAVS, String prenom, String nom, String adresse, String email,
                            String telephone, String dateNaissance, int responsable, String statut,
                            double salaire, String dateDebut, String typeContrat)
                            throws ExceptionDataBase, SQLException {
        this.stmt = db.con.prepareStatement(INSERT_EMPLOYE);
        this.stmt.setInt(1, noAVS);
        this.stmt.setString(2, prenom);
        this.stmt.setString(3, nom);
        this.stmt.setString(4, adresse);
        this.stmt.setString(5, email);
        this.stmt.setString(6, telephone);
        this.stmt.setString(7, dateNaissance);
        this.stmt.setInt(8, responsable);
        this.stmt.setString(9, statut);
        this.stmt.setDouble(10, salaire);
        this.stmt.setString(11, dateDebut);
        this.stmt.setString(12, typeContrat);
        this.stmt.executeUpdate();
    }

    /**
     * Permet d'obtenir tous les employés de la base de données en fonction du nom
     *
     * @param nom               Nom de recherche
     *
     * @return ArrayList<Personne>
     */
    public ArrayList<Personne> selEmployeeParNom (String nom) throws ExceptionDataBase, SQLException {
        this.stmt = db.con.prepareStatement(SEL_EMPLOYE_PAR_NOM);
        this.stmt.setString(1, nom);

        ResultSet rs = this.stmt.executeQuery();
        return this.creerTableauPersonne(rs);
    }

    /**
     * Permet d'obtenir tous les employés de la base de données en fonction du nom et du prenom
     *
     * @param nom               Nom de recherche
     * @param prenom            Prenom de recherche
     *
     * @return ArrayList<Personne>
     */
    public ArrayList<Personne> selEmployeeParPrenomNom (String nom, String prenom) throws ExceptionDataBase, SQLException {
        this.stmt = db.con.prepareStatement(SEL_EMPLOYE_PAR_PRENOM_NOM);
        this.stmt.setString(1, nom);
        this.stmt.setString(2, prenom);
        ResultSet rs = this.stmt.executeQuery();
        return this.creerTableauPersonne(rs);
    }

    /**
     * Permet d'obtenir toutes les races d'animaux
     *
     * @return ArrayList<String>
     */

    public ArrayList<String> selAllRaceAnimal  () throws ExceptionDataBase, SQLException {
        ArrayList<String> data = new ArrayList<>();
        this.stmt = db.con.prepareStatement(SEL_ALL_RACE_ANIMAL);
        ResultSet rs = this.stmt.executeQuery();
        while (rs.next()) {
            data.add(rs.getString("nom"));
        }

        return data;
    }

    /**
     * Permet de connaitre le nombre d'employé dans la table Personne
     *
     * @return int
     */
    public int nombrePersonne () throws ExceptionDataBase, SQLException {
        int data = 0;
        this.db.init();
        this.stmt = db.con.prepareStatement(NOMBRE_PERSONNE);
        ResultSet rs = this.stmt.executeQuery();
        while (rs.next()) {
            data =  rs.getInt("nbPersonne");
        }
        return data;
    }

    /**
     * Permet de selectionner un évenement en fonction de son ID
     *
     * @param id         valeur en int
     *
     * @return String
     */
    public String selTypeEvenement (int id) throws ExceptionDataBase, SQLException {
        String res = null;
        this.stmt = db.con.prepareStatement(SEL_TYPE_EVENEMENT);
        this.stmt.setInt(1, id);
        ResultSet rs = this.stmt.executeQuery();

        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun type d'événement ne correspond à l'ID " + id);
        } else {
            // Previous check has forwarded the pointer, just put it back at the start
            rs.beforeFirst();
            while (rs.next()) {
                res =  rs.getString("type");
            }
        }
        return res;
    }

    /**
     * Permet de selectionner un évenement en fonction de son ID
     *
     * @param noAVS         valeur en int
     *
     * @return Personne
     */
    public Personne selEmployeDetails (int noAVS) throws ExceptionDataBase, SQLException {
        Personne p = new Personne();
        this.stmt = db.con.prepareStatement(SEL_EMPLOYE_DETAILS);
        this.stmt.setInt(1, noAVS);
        ResultSet rs = this.stmt.executeQuery();
        ArrayList<Personne>  pers = creerTableauPersonne(rs);

        return pers.get(0);
    }

    /**
     * Permet de créer une ArrayList de personne à partit de Resultset passé en paramètre
     *
     * @param rs            ResultSet ; Obtenu par requête SQL.
     *
     * @return ArrayList<Personne>
     */
    private ArrayList<Personne> creerTableauPersonne (ResultSet rs) throws ExceptionDataBase, SQLException {
        ArrayList<Personne> data = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun type d'événement ne correspond aux infos rentrées ");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data.add(new Personne(rs.getInt("noAVS"), rs.getString("prenom"),
                        rs.getString("nom"), rs.getString("adresse"),
                        rs.getString("email"), rs.getString("telephone"),
                        rs.getDate("dateNaissance"), rs.getInt("responsable"),
                        rs.getString("statut"), rs.getDouble("salaire"),
                        rs.getDate("dateDebut"), rs.getString("typeContrat")));
            }
        }
        // Fermeture de la DB obligatoire après le ResultSet !
        // Doit être ici !

        return data;
    }

    /**
     * Permet de créer une ArrayList d'evenement à partit de Resultset passé en paramètre
     *
     * @param rs            ResultSet ; Obtenu par requête SQL.
     *
     * @return ArrayList<Evenement>
     */
    private ArrayList<Evenement> creerTableauEvenement (ResultSet rs) throws ExceptionDataBase, SQLException {
        ArrayList<Evenement> data = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun type d'événement ne correspond aux infos rentrées ");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data.add(new Evenement(rs.getInt("id"), rs.getString("description"),
                        rs.getString("date"), rs.getInt("type")));
            }
        }
        // Fermeture de la DB obligatoire après le ResultSet !
        // Doit être ici !

        return data;
    }

    private void insFelin(Felin f) throws SQLException {

        this.stmt = null;
        this.stmt = this.db.con.prepareStatement(INSERT_FELIN);

        try {
            this.stmt.setInt(1, f.getId());
            this.stmt.setFloat(2, f.getPoids());
            this.stmt.execute();
        } catch (SQLException sqlE) {
            throw sqlE;     // Exception propagée à l'appelant
        }
    }

    public void insAnimal(Animal a) throws SQLException {

        this.stmt = this.db.con.prepareStatement(INSERT_ANIMAL, Statement.RETURN_GENERATED_KEYS);

        java.sql.Date anneeNaissance = new java.sql.Date(a.getAnneeNaissance().getTime());
        java.sql.Date dateDeces = anneeNaissance;

        System.out.println(dateDeces);

        // Attribut communs à tous les animaux
        this.stmt.setNull(1, Types.NULL);
        this.stmt.setString(2, a.getNom());
        this.stmt.setString(3, a.getSexe());
        this.stmt.setDate(4, anneeNaissance);
        this.stmt.setInt(5, a.getEnclos());
        this.stmt.setString(6, a.getOrigine());
        this.stmt.setDate(7, dateDeces);

        // En premier lieu, on enregistre l'animal dans la DB
        try {

            System.out.println(this.stmt.toString());

            this.stmt.execute();
            ResultSet rs = this.stmt.getGeneratedKeys();
            if (rs.next()) {
                int newAnimalID = rs.getInt(1);
                a.setId(newAnimalID);
            }
        } catch (SQLException sqlE) {
            throw sqlE;
        }

        if (a instanceof Felin) {
            try {
                this.insFelin((Felin) a);
            } catch (SQLException sqlE) {
                throw sqlE;
            }
        }
    }

}
