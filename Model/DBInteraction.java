package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by D.Hamel on 28.03.17.
 */
public class DBInteraction {

    private DBConnection db;

    public DBInteraction() {
        this.db = new DBConnection();
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
        PreparedStatement preparedStatement;
        ArrayList<Personne> data = new ArrayList<>();
        this.db.init();
        preparedStatement = db.con.prepareStatement(REQUETE);
        ResultSet rs = preparedStatement.executeQuery();
        return this.creerTableauPersonne(rs);
    }

    /**
     * Permet d'obtenir dans les noms et prenoms de tous les employés présents dans la base de données
     *
     * @param numAVS            Nouveau numéro AVS de la personne
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
    public void insPersonne (int numAVS, String prenom, String nom, String adresse, String email,
                                   String telephone, String dateNaissance, int responsable, String statut,
                                   double salaire, String dateDebut, String typeContrat)
                            throws ExceptionDataBase, SQLException {
        PreparedStatement preparedStatement;
        this.db.init();
        preparedStatement = db.con.prepareStatement(INSERT_EMPLOYE);
        preparedStatement.setInt(1, numAVS);
        preparedStatement.setString(2, prenom);
        preparedStatement.setString(3, nom);
        preparedStatement.setString(4, adresse);
        preparedStatement.setString(5, email);
        preparedStatement.setString(6, telephone);
        preparedStatement.setString(7, dateNaissance);
        preparedStatement.setInt(8, responsable);
        preparedStatement.setString(9, statut);
        preparedStatement.setDouble(10, salaire);
        preparedStatement.setString(11, dateDebut);
        preparedStatement.setString(12, typeContrat);
        preparedStatement.executeUpdate();
    }

    /**
     * Permet d'obtenir tous les employés de la base de données en fonction du nom
     *
     * @param nom               Nom de recherche
     *
     * @return ArrayList<Personne>
     */
    public ArrayList<Personne> selEmployeeParNom (String nom) throws ExceptionDataBase, SQLException {
        PreparedStatement preparedStatement;
        this.db.init();
        preparedStatement = db.con.prepareStatement(SEL_EMPLOYE_PAR_NOM);
        preparedStatement.setString(1, nom);

        ResultSet rs = preparedStatement.executeQuery();
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
        PreparedStatement preparedStatement;
        this.db.init();
        preparedStatement = db.con.prepareStatement(SEL_EMPLOYE_PAR_PRENOM_NOM);
        preparedStatement.setString(1, nom);
        preparedStatement.setString(2, prenom);
        ResultSet rs = preparedStatement.executeQuery();
        return this.creerTableauPersonne(rs);
    }

    /**
     * Permet d'obtenir toutes les races d'animaux
     *
     * @return ArrayList<String>
     */

    public ArrayList<String> selAllRaceAnimal  () throws ExceptionDataBase, SQLException {
        PreparedStatement preparedStatement;
        ArrayList<String> data = new ArrayList<>();
        this.db.init();
        preparedStatement = db.con.prepareStatement(SEL_ALL_RACE_ANIMAL);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            data.add(rs.getString("nom"));
        }
        this.db.close();
        return data;
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
        while (rs.next()) {
            data.add(new Personne(rs.getInt("noAVS"), rs.getString("prenom"),
                    rs.getString("nom"), rs.getString("adresse"),
                    rs.getString("email"), rs.getString("telephone"),
                    rs.getDate("dateNaissance"), rs.getInt("responsable"),
                    rs.getString("statut"), rs.getDouble("salaire"),
                    rs.getDate("dateDebut"), rs.getString("typeContrat")));
        }
        // Fermeture de la DB obligatoire après le ResultSet !
        // Doit être ici !
        this.db.close();
        return data;
    }


    public int nombrePersonne () throws ExceptionDataBase, SQLException {
        PreparedStatement preparedStatement;
        int data = 0;
        this.db.init();
        preparedStatement = db.con.prepareStatement(NOMBRE_PERSONNE);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            data =  rs.getInt("nbPersonne");
        }
        return data;
    }

    public String selTypeEvenement (int id) throws ExceptionDataBase, SQLException {
        PreparedStatement stmt;
        String res = null;

        this.db.init();
        stmt = db.con.prepareStatement(SEL_TYPE_EVENEMENT);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

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

    public Personne selEmployeDetails (int noAVS) throws ExceptionDataBase, SQLException {

        PreparedStatement stmt;
        Personne p = new Personne();

        this.db.init();
        stmt = db.con.prepareStatement(SEL_EMPLOYE_DETAILS);
        stmt.setInt(1, noAVS);
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun employé trouvé avec le noAVS " + noAVS);
        } else {
            // Previous check has forwarded the pointer, just put it back at the start
            rs.beforeFirst();
            while (rs.next()) {
                p.setNoAVS(rs.getInt("noAVS"));
                p.setPrenom(rs.getString("prenom"));
                p.setNom(rs.getString("nom"));
                p.setAdresse(rs.getString("adresse"));
                p.setEmail(rs.getString("email"));
                p.setTelephone(rs.getString("telephone"));
                p.setDateNaissance(rs.getDate("dateNaissance"));
                p.setResponsable(rs.getInt("responsable"));
                p.setStatut(rs.getString("statut"));
                p.setSalaire(Double.parseDouble(rs.getString("salaire")));
                p.setDateDebut(rs.getDate("dateDebut"));
                p.setTypeContrat(rs.getString("typeContrat"));
            }
        }
        return p;
    }


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

}
