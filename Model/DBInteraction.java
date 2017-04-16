package Model;

import java.sql.*;
import java.util.*;

/**
 *
 * Cette classes à pour but de créer une connexion avec la base de données
 * L'attribut permettant de se connecter à la base de données est :     DBConnection db
 *
 * Elle contient également toute les requêtes disponibles afin de faire des actions sur la base de données
 *
 * La création d'un objet DBInteraction ne demande aucun paramètre
 *
 * L'attribut privé "PreparedStatement stmt" est l'objet qui contiendra les requêtes
 *
 *
 * @author D.Hamel
 * @author C.Balboni
 *
 * @version 1.0
 *
 * @date    28.03.2017 (Création)
 * @date    05.05.2017 (Finalisation v1.0)
 *
 */

public class DBInteraction {

    /**
     * Ci-dessous, Liste de toutes les requêtes possibles dans le programme !
     */
    // -----------------------------------------------------------------------------------------------------------------
    // PERSONNE :
    private static final String NOMBRE_PERSONNE = "SELECT COUNT(*) as nbPersonne FROM Personne;";
    private static final String SEL_EMPLOYE_DETAILS = "SELECT * FROM Personne WHERE noAVS = ? ;";
    private static final String SEL_EMPLOYE_PAR_PRENOM_NOM = "SELECT * " +
            "FROM Personne " +
            "WHERE Personne.nom = ? " +
            " AND Personne.prenom = ? ;";
    // Recupère tous les paramètre des personnne répondant à la requête.
    // 12 Paramètres
    private static final String SEL_EMPLOYE_PAR_NOM = "SELECT * " +
            "FROM Personne " +
            "WHERE Personne.nom = ? ;";
    // 12 Paramètres Dans l'ordre ci-dessous :
    // noAVS / nom / prenom / adresse / email / téléphone / dateNaissance /
    // idResponsable / statut / salaire / dateDebut	TypeContrat /
    private static final String INSERT_EMPLOYE = "INSERT INTO Personne VALUES (null, ?, ? , ? , ? , ? , ? , ? , ? , ? ," +
            " ? , ? , ? ); ";
    // Recupère tous les paramètre d'une personne
    // 12 Paramètres
    private static final String SEL_ALL_PERSONNE = "SELECT * " +
            "FROM Personne;";
    // Permet de modifier les informations relatives à une Personne
    private static final String UPDATE_PERSONNE = "UPDATE Personne " +
            "SET prenom = ?, " +
            "nom = ?, " +
            "adresse = ?, " +
            "email = ?, " +
            "telephone = ?, " +
            "responsable = ? " +
            "WHERE idPersonne = ?;";
    // -----------------------------------------------------------------------------------------------------------------
    // ANIMAUX :
    private static final String INSERT_ANIMAL = "INSERT INTO Animal (id, nom, sexe, dateNaissance, enclos, origine, dateDeces) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_FELIN = "INSERT INTO Animal_Fauve (id, poids) VALUES (?, ?);";
    private static final String INSERT_OISEAU = "INSERT INTO Animal_Oiseau (id, envergure, bague) VALUES (?, ?, ?);";
    private static final String INSERT_REPTILE = "INSERT INTO Animal_Reptile (id, temperature) VALUES (?, ?);";
    private static final String INSERT_PRIMATE = "INSERT INTO Animal_Primate (id, temperature) VALUES (?, ?);";
    private static final String DELETE_ANIMAL = "DELETE FROM Animal WHERE id = ?;";
    private static final String SEL_ALL_RACE_ANIMAL = "SELECT nom FROM Race;";
    // Récupérer uniquement ces 5 paramètre de l'animal
    private static final String SEL_ANIMAL_ID_NOM_RACE_SEX_DATENAISSANCE = "SELECT id, nom, race, sexe, dateNaissance " +
            "FROM Animal;";
    // Récupérer tous les paramètre d'un animal
    private static final String SEL_ANIMAL = "SELECT * FROM Animal;";
    // ----------------------------------------------------------------------------------x-------------------------------
    // ENCLOS :
    // ----------------------------------------------------------------------------------x-------------------------------
    private static final String SEL_ENCLOS = "SELECT * FROM Enclos WHERE id = ?;";

    // EVENEMENT :
    // Liste de tous les événements qui n'ont pas de personne attribué
    private static final String SEL_ALL_EVENEMENT_WHITOUT_EMPLOYEE = "SELECT * " +
            "FROM Evenement " +
            "WHERE (id) NOT IN " +
            "(SELECT evenement FROM Personne_Evenement);";
    private static final String INSERT_TYPE_EVENEMET = " INSERT INTO TypeEvenement VALUES (?) ";
    // Insertion d'un événement dans la DB
    private static final String INSERT_EVENEMENT  = "INSERT INTO Evenement VALUES (?, ?, ?, ?);";
    // Assigner un événement à un personne
    private static final String ASSIGNER_EVENEMENT_PERSONNE = "SELECT * FROM Personne_Evenement (?, ?, ?);";
    // Assigner un événement à un animal
    private static final String ASSIGNER_EVENEMENT_ANIMAL = "SELECT * FROM Animal_Evenement VALUES (?, ?, ?);";
    // Assigner un événement à un animal
    private static final String ASSIGNER_EVENEMENT_INTERVENANT = "SELECT * FROM Intervenant_Evenement VALUES (?, ?, ?);";
    // Assigner un événement à un animal
    private static final String ASSIGNER_EVENEMENT_INFRASTRUCTURE = "SELECT * FROM Infrastructure_Evenement VALUES (?, ?, ?);";
    // Selectionner le type d'un événement en fonction de son ID
    private static final String SEL_TYPE_EVENEMENT = "SELECT type FROM TypeEvenement WHERE id = ? ;";
    // -----------------------------------------------------------------------------------------------------------------
    // STOCK :
    // Récupérer l'état de tout le stock (nom, quantite, unite, quantiteMin
    private static final String SEL_ALL_STOCK = "SELECT * FROM Stock;";
    // Récupérer toutes les commandes qui ont été faites (Date et ID)
    private static final String SEL_ALL_COMMANDE = "SELECT * FROM Commande";
    // Récupérer le contenu d'une commande :
    private static final String SEL_ALL_CONTENU_COMMAND_PAR_ID = "SELECT nom, quantite " +
            " FROM Contenu_Commande " +
            " WHERE commande = ?;";
    // Récupérer l'ID et la date de toute les commandes faites entre deux dates Date1 et Date2
    private static final String SEL_COMMANDE_BETWEEN_TWO_DATES = "SELECT * FROM Commande WHERE `date` BETWEEN ? AND ? ;";
    // Récupérer le contenu d'une commande en fonction de son ID
    private static final String SEL_CONTENU_COMMANDE_PAR_ID = "SELECT nom quantite " +
            " FROM Contenu_Commande " +
            " WHERE commande = ? ;";
    // -----------------------------------------------------------------------------------------------------------------
    // PARAMETRE DE LA CLASSE :

    private DBConnection db;
    private PreparedStatement stmt;

    // -----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTEUR :
    public DBInteraction() throws ExceptionDataBase {
        this.db = new DBConnection();
        this.db.init();
        this.stmt = null;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // Partie pour la gestion des PERSONNE dans la DB

    /**
     * Permet de modifier les informations d'un employé
     * Seuls 6 paramètres sont modifiables : prénom / nom / adresse / email / telephone / responsable
     *
     * @return Personne
     */
    public void updatePersonne (Personne personne) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(UPDATE_PERSONNE);

        this.stmt.setString(1, personne.getPrenom());
        this.stmt.setString(2, personne.getNom());
        this.stmt.setInt(3, personne.getAdresse());
        this.stmt.setString(4, personne.getEmail());
        this.stmt.setString(5, personne.getTelephone());
        this.stmt.setInt(6, personne.getResponsable());
        this.stmt.setInt(7, personne.getIdPersonne());

        this.stmt.executeUpdate();
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
     * Permet d'obtenir dans les noms et prenoms de tous les employés présents dans la base de données
     *
     * @return String[][] / Prénom en première position
     */
    public ArrayList<Personne> selAllFirstLastNameEmployee() throws ExceptionDataBase, SQLException {
        ArrayList<Personne> personne = this.recupererPersonne(SEL_ALL_PERSONNE);
        ResultSet rs = this.stmt.executeQuery();
        return creerTableauPersonne(rs);
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
    public void insertPersonne(String noAVS, String prenom, String nom, String adresse, String email,
                            String telephone, java.sql.Date dateNaissance, int responsable, String statut,
                            double salaire, java.sql.Date dateDebut, String typeContrat)
            throws ExceptionDataBase, SQLException {
        this.stmt = db.con.prepareStatement(INSERT_EMPLOYE);
        this.stmt.setString(1, noAVS);
        this.stmt.setString(2, prenom);
        this.stmt.setString(3, nom);
        this.stmt.setString(4, adresse);
        this.stmt.setString(5, email);
        this.stmt.setString(6, telephone);
        this.stmt.setDate(7, dateNaissance);
        this.stmt.setInt(8, responsable);
        this.stmt.setString(9, statut);
        this.stmt.setDate(10, dateDebut);
        this.stmt.setString(11, typeContrat);
        this.stmt.executeUpdate();
    }

    /**
     * Permet d'obtenir dans les noms et prenoms de tous les employés présents dans la base de données
     *
     * @param personne(Personne)
     */
    public void insertPersonne(Personne personne) throws ExceptionDataBase, SQLException {
        this.stmt = db.con.prepareStatement(INSERT_EMPLOYE);
        this.stmt.setString(1, personne.getNoAVS());
        this.stmt.setString(2, personne.getPrenom());
        this.stmt.setString(3, personne.getNom());
        this.stmt.setInt(4, personne.getAdresse());
        this.stmt.setString(5, personne.getEmail());
        this.stmt.setString(6, personne.getTelephone());
        this.stmt.setDate(7, personne.getDateNaissance());
        this.stmt.setInt(8, personne.getResponsable());
        this.stmt.setString(9, personne.getStatut());
        this.stmt.setDate(10, personne.getDateDebut());
        this.stmt.setString(11, personne.getTypeContrat());
        this.stmt.executeUpdate();
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
     * Permet de créer une ArrayList de personne à partir de Resultset passé en paramètre
     *
     * @param rs(ResultSet)
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
                data.add(new Personne(rs.getInt("idPersonne") , rs.getString("noAVS"),
                        rs.getString("prenom"), rs.getString("nom"),
                        rs.getInt("adresse"), rs.getString("email"),
                        rs.getString("telephone"), rs.getDate("dateNaissance"),
                        rs.getInt("responsable"), rs.getString("statut"),
                        rs.getDate("dateDebut"), rs.getString("typeContrat")));
            }
        }
        // Fermeture de la DB obligatoire après le ResultSet !
        // Doit être ici !

        return data;
    }
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // Partie pour la gestion des ANIMAUX dans la DB

    /**
     * Permet d'obtenir l'id, le nom, le sexe, la dateNaissance, et la race de tous les animaux
     *
     * @return ArrayList<Animal>
     */
    public ArrayList<Animal> selAnimaux() throws SQLException, ExceptionDataBase {
        this.stmt = db.con.prepareStatement(SEL_ANIMAL);
        ResultSet rs = this.stmt.executeQuery();
        return creerTableauAnimal(rs);
    }

    /**
     * Permet de créer une ArrayList d'Animal à partir de Resultset passé en paramètre
     *
     * @param rs(ResultSet)
     *
     * @return ArrayList<Animal>
     */
    private ArrayList<Animal> creerTableauAnimal (ResultSet rs) throws ExceptionDataBase, SQLException {
        ArrayList<Animal> data = new ArrayList<Animal>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun Animal ne correspond aux infos rentrées ");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data.add(new Animal(rs.getInt("id"), rs.getString("nom"),
                        rs.getString("sexe"), rs.getDate("dateNaissance"),
                        rs.getInt("enclos"), rs.getString("origine"),
                        rs.getString("race"), rs.getDate("dateDeces")));
            }
        }
        // Fermeture de la DB obligatoire après le ResultSet !
        // Doit être ici !

        return data;
    }

    /**
     * Permet de créer une ArrayList d'Animal à partir de Resultset passé en paramètre
     *
     * @param rs(ResultSet)
     * @return ArrayList<Animal>
     */
    private ArrayList<Enclos> creerTableauEnclos(ResultSet rs) throws ExceptionDataBase, SQLException {
        ArrayList<Enclos> data = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun enclos correspondants");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data.add(new Enclos(rs.getInt("id"), rs.getInt("nom"),
                        rs.getInt("secteur"), rs.getString("surface")));
            }
        }
        // Fermeture de la DB obligatoire après le ResultSet !
        // Doit être ici !

        return data;
    }

    /**
     * Permet d'obtenir toutes les races d'animaux
     *
     * @return ArrayList<String>
     */
     public ArrayList<String> selAllRaceAnimal() throws ExceptionDataBase, SQLException {
         ArrayList<String> data = new ArrayList<>();
         this.stmt = db.con.prepareStatement(SEL_ALL_RACE_ANIMAL);
         ResultSet rs = this.stmt.executeQuery();
         while (rs.next()) {
             data.add(rs.getString("nom"));
         }
         return data;
     }

    public void delAnimal(int id) throws ExceptionDataBase, SQLException {

        Animal a = new Animal(id);
        if (a.getNom().length() == 0)
            throw new ExceptionDataBase("L'animal n'existe pas dans la DB");

        this.stmt = null;
        this.stmt = db.con.prepareStatement(DELETE_ANIMAL);
        this.stmt.setInt(1, id);
        this.stmt.execute();
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

    private void insReptile(Reptile r) throws SQLException {
        this.stmt = null;
        this.stmt = this.db.con.prepareStatement(INSERT_REPTILE);

        try {
            this.stmt.setInt(1, r.getId());
            this.stmt.setFloat(2, r.getTemperature());
            this.stmt.execute();
        } catch (SQLException sqlE) {
            throw sqlE;     // Exception propagée à l'appelant
        }
    }

    private void insOiseau(Oiseau o) throws SQLException {
        this.stmt = null;
        this.stmt = this.db.con.prepareStatement(INSERT_OISEAU);

        try {
            this.stmt.setInt(1, o.getId());
            this.stmt.setDouble(2, o.getEnvergure());
            this.stmt.setString(3, o.getBague());
        } catch (SQLException sqlE) {
            throw sqlE;
        }
    }

    private void insPrimate(Primate o) throws SQLException {
        this.stmt = null;
        this.stmt = this.db.con.prepareStatement(INSERT_PRIMATE);

        try {
            this.stmt.setInt(1, o.getId());
            this.stmt.setDouble(2, o.getTemperature());
        } catch (SQLException sqlE) {
            throw sqlE;
        }
    }

    public void insAnimal(Animal a) throws SQLException {

        this.stmt = this.db.con.prepareStatement(INSERT_ANIMAL, Statement.RETURN_GENERATED_KEYS);

        // Attribut communs à tous les animaux
        this.stmt.setNull(1, Types.NULL);
        this.stmt.setString(2, a.getNom());
        this.stmt.setString(3, a.getSexe());
        this.stmt.setDate(4, a.getAnneeNaissance());
        this.stmt.setInt(5, a.getEnclos());
        this.stmt.setString(6, a.getOrigine());
        this.stmt.setDate(7, a.getDateDeces());

        // En premier lieu, on enregistre l'animal dans la DB
        try {
            System.out.println(this.stmt.toString());

            this.stmt.execute();
            ResultSet rs = this.stmt.getGeneratedKeys();
            if (rs.next()) {    // On récupère l'ID de l'animal inséré
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
        if (a instanceof Reptile) {
            try {
                this.insReptile((Reptile) a);
            } catch (SQLException sqlE) {
                throw sqlE;
            }
        }
        if (a instanceof Oiseau) {
            try {
                this.insOiseau((Oiseau) a);
            } catch (SQLException sqlE) {
                throw sqlE;
            }
        }
        if (a instanceof Primate) {
            try {
                this.insPrimate((Primate) a);
            } catch (SQLException sqlE) {
                throw sqlE;
            }
        }
    }


    /*
    ENCLOS
     */
    public Enclos selEnclos(int id) throws SQLException, ExceptionDataBase {

        this.stmt = db.con.prepareStatement(SEL_ENCLOS);
        this.stmt.setInt(1, id);
        ResultSet rs = this.stmt.executeQuery();

        Enclos data = new Enclos();

        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun enclos correspondants");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data = new Enclos(rs.getInt("id"), rs.getInt("nom"),
                        rs.getInt("secteur"), rs.getString("surface"));
            }
        }

        return data;

    }


    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // Partie pour la gestion EVENEMENT dans la DB

    /**
     * Permet de récupérer tous les événements qui n'ont aucune personne assignée à ce dernier
     *
     * @return ArrayList<Evenement>
     */
    public ArrayList<Evenement> getAllUnassignedTaskEmployee() throws ExceptionDataBase, SQLException {

        this.stmt = db.con.prepareStatement(SEL_ALL_EVENEMENT_WHITOUT_EMPLOYEE);
        ResultSet rs = this.stmt.executeQuery();

        return creerTableauEvenement(rs);
    }

    /**
     * Permet de selectionner un évenement en fonction de son ID
     *
     * @param type         valeur en int
     *
     * @return String
     */
    public String selTypeEvenement (String type) throws ExceptionDataBase, SQLException {
        String res = null;
        this.stmt = db.con.prepareStatement(SEL_TYPE_EVENEMENT);
        this.stmt.setString(1, type);
        ResultSet rs = this.stmt.executeQuery();

        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun type d'événement ne correspond à l'ID " + type);
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
     * Permet d'insérer un type d'événement dans la DB à partir d'un objet TypeEvenement
     *
     * @param typeEvenement(TypeEvenement)
     *
     * @return void
     */
    public void insertTypeEvenement (TypeEvenement typeEvenement) throws SQLException {
        this.stmt = this.db.con.prepareStatement(INSERT_TYPE_EVENEMET);

        this.stmt.setString(1, typeEvenement.getType());
    }

    /**
     * Permet d'insérer un événement dans la DB à partir d'un objet Evenement
     *
     * @param evenement(Evenement)
     *
     * @return void
     */
    /*
    public void insertEvenement (Evenement evenement) throws SQLException {

        this.stmt = this.db.con.prepareStatement(INSERT_EVENEMENT);

        this.stmt.setNull(1, Types.NULL);
        this.stmt.setString(2, evenement.getDescription());
        this.stmt.setDate(3, evenement.getDate());
        this.stmt.setInt(4, evenement.getType());

        this.stmt.executeUpdate();
    }
    */

    /**
     * Permet d'insérer un événement dans la DB à partir d'un objet Evenement
     *
     * @param description(String)
     * @param date(java.sql.Date)
     * @param type(int)
     *
     * @return void
     */
    public void insertEvenement (String description, java.sql.Date date, int type) throws SQLException {
        // le type est une référence sur la table "TypeEvenement"

        this.stmt = this.db.con.prepareStatement(INSERT_EVENEMENT);

        this.stmt.setNull(1, Types.NULL);
        this.stmt.setString(2, description);
        this.stmt.setDate(3, date);
        this.stmt.setInt(4, type);

        this.stmt.executeUpdate();
    }


     /**
     * Permet d'assigner un événement à une personne
     *
     * @param evenement(Evenement)
     * @param employe(Personne)
     *
     * @return void
     */
    public void assignEvenementEmploye (Evenement evenement, Personne employe) throws SQLException {
        int numAVS_employe = employe.getIdPersonne();
        int id_evenement = evenement.getId();

        this.stmt = this.db.con.prepareStatement(ASSIGNER_EVENEMENT_PERSONNE);

        this.stmt.setNull(1, Types.NULL);
        this.stmt.setInt(2, numAVS_employe);
        this.stmt.setInt(3, id_evenement);

        this.stmt.executeUpdate();

    }


    /**
     * Permet d'assigner plusieurs événements à une personne
     *
     * @param evenements(ArrayList<Evenement>)
     * @param employe(Personne)
     *
     * @return void
     */
    public void assignEvenementEmploye (ArrayList<Evenement> evenements, Personne employe) throws SQLException {
        if (evenements.size() > 0) {
            int numAVS_employe = employe.getIdPersonne();

            for (int i = 0; i > evenements.size(); i++) {

                this.stmt = this.db.con.prepareStatement(ASSIGNER_EVENEMENT_PERSONNE);

                this.stmt.setNull(1, Types.NULL);
                this.stmt.setInt(2, numAVS_employe);
                this.stmt.setInt(3, evenements.get(i).getId());

                this.stmt.executeUpdate();
            }
        }

    }


    /**
     * Permet d'assigner un événement à plusieurs personnes
     *
     * @param evenement(Evenement)
     * @param tabEmploye(ArrayList<Personne>)
     *
     * @return void
     */
    public void assignEvenementEmploye (Evenement evenement, ArrayList<Personne> tabEmploye) throws SQLException {
        if (tabEmploye.size() > 0) {
            int id_evenement = evenement.getId();

            for (int i = 0; i < tabEmploye.size(); i++) {
                this.stmt = this.db.con.prepareStatement(ASSIGNER_EVENEMENT_PERSONNE);

                this.stmt.setNull(1, Types.NULL);
                this.stmt.setInt(2, tabEmploye.get(i).getIdPersonne());
                this.stmt.setInt(3, id_evenement);

                this.stmt.executeUpdate();
            }
        }
    }

    /**
     * Permet d'assigner un événement à une Animal
     *
     * @param evenement(Evenement)
     * @param animal(Animal)
     *
     * @return void
     */
    public void assignEvenementAnimal (Evenement evenement, Animal animal) throws SQLException {
        int id_animal = animal.getId();
        int id_evenement = evenement.getId();

        this.stmt = this.db.con.prepareStatement(ASSIGNER_EVENEMENT_ANIMAL);

        this.stmt.setNull(1, Types.NULL);
        this.stmt.setInt(2, id_animal);
        this.stmt.setInt(3, id_evenement);

        this.stmt.executeUpdate();

    }

    /**
     * Permet d'assigner un événement à plusieurs Animaux
     *
     * @param evenement(Evenement)
     * @param tabAnimal(ArrayList<Animal>)
     *
     * @return void
     */
    public void assignEvenementAnimal (Evenement evenement, ArrayList<Animal> tabAnimal) throws SQLException {
        if (tabAnimal.size() > 0) {
            int id_evenement = evenement.getId();

            for (int i = 0; i < tabAnimal.size(); i++) {
                this.stmt = this.db.con.prepareStatement(ASSIGNER_EVENEMENT_ANIMAL);

                this.stmt.setNull(1, Types.NULL);
                this.stmt.setInt(2, tabAnimal.get(i).getId());
                this.stmt.setInt(3, id_evenement);

                this.stmt.executeUpdate();
            }
        }
    }

    /**
     * Permet d'assigner un événement à un Intervenant
     *
     * @param evenement(Evenement)
     * @param intervenant(Intervenant)
     *
     * @return void
     */
    public void assignEvenementIntervenant (Evenement evenement, Intervenant intervenant) throws SQLException {
        int id_intervenant = intervenant.getId();
        int id_evenement = evenement.getId();

        this.stmt = this.db.con.prepareStatement(ASSIGNER_EVENEMENT_INTERVENANT);

        this.stmt.setNull(1, Types.NULL);
        this.stmt.setInt(2, id_intervenant);
        this.stmt.setInt(3, id_evenement);

        this.stmt.executeUpdate();

    }

    /**
     * Permet d'assigner un événement à plusieurs Intervenants
     *
     * @param evenement(Evenement)
     * @param tabIntervenant(ArrayList<Intervenant>)
     *
     * @return void
     */
    public void assignEvenementIntervenant (Evenement evenement, ArrayList<Intervenant> tabIntervenant) throws SQLException {
        if (tabIntervenant.size() > 0) {
            int id_evenement = evenement.getId();

            for (int i = 0; i < tabIntervenant.size(); i++) {
                this.stmt = this.db.con.prepareStatement(ASSIGNER_EVENEMENT_INTERVENANT);

                this.stmt.setNull(1, Types.NULL);
                this.stmt.setInt(2, tabIntervenant.get(i).getId());
                this.stmt.setInt(3, id_evenement);

                this.stmt.executeUpdate();
            }
        }
    }

    /**
     * Permet d'assigner un événement à un Infrastructure
     *
     * @param evenement(Evenement)
     * @param infrastructure(Infrastructure)
     *
     * @return void
     */
    public void assignEvenementInfrastructure (Evenement evenement, Infrastructure infrastructure) throws SQLException {
        int id_infrastructure = infrastructure.getId();
        int id_evenement = evenement.getId();

        this.stmt = this.db.con.prepareStatement(ASSIGNER_EVENEMENT_INFRASTRUCTURE);

        this.stmt.setNull(1, Types.NULL);
        this.stmt.setInt(2, id_infrastructure);
        this.stmt.setInt(3, id_evenement);
        this.stmt.executeUpdate();

    }

    /**
     * Permet d'assigner un événement à plusieurs Intervenants
     *
     * @param evenement(Evenement)
     * @param tabInfrastructure(ArrayList<Infrastructure>)
     *
     * @return void
     */
    public void assignEvenementInfrastructure (Evenement evenement, ArrayList<Infrastructure> tabInfrastructure) throws SQLException {
        if (tabInfrastructure.size() > 0) {
            int id_evenement = evenement.getId();

            for (int i = 0; i < tabInfrastructure.size(); i++) {
                this.stmt = this.db.con.prepareStatement(ASSIGNER_EVENEMENT_INFRASTRUCTURE);

                this.stmt.setNull(1, Types.NULL);
                this.stmt.setInt(2, tabInfrastructure.get(i).getId());
                this.stmt.setInt(3, id_evenement);

                this.stmt.executeUpdate();
            }
        }
    }

    /**
     * Permet de créer une ArrayList d'evenement à partir de Resultset passé en paramètre
     *
     * @param rs(ResultSet)
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
                        rs.getTimestamp("date"), rs.getInt("type")));
            }
        }
        // Fermeture de la DB obligatoire après le ResultSet !
        // Doit être ici !

        return data;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // Partie pour la gestion STOCK  dans la DB

    /**
     * Permet de récupérer l'entierté du stock avec tous ses paramètres
     *
     * @return ArrayList<Stock>
     */
    public ArrayList<Stock> selAllStock () throws SQLException, ExceptionDataBase {
        ArrayList<Stock> data = new ArrayList<Stock>();
        this.stmt = db.con.prepareStatement(SEL_ALL_STOCK);

        ResultSet rs = this.stmt.executeQuery();

        return this.createTabStock(rs);
    }


    /**
     * Permet de récupérer l'entierté des Commandes avec tous ses paramètres
     *
     * @return ArrayList<Commande>
     */
    public ArrayList<Commande> selAllCommande () throws SQLException, ExceptionDataBase {
        ArrayList<Commande> data = new ArrayList<Commande>();
        this.stmt = db.con.prepareStatement(SEL_ALL_COMMANDE);

        ResultSet rs = this.stmt.executeQuery();

        return this.createTabCommande(rs);
    }

    /**
     * Permet de récupérer les commandes qui ont été faites entre deux dates passées en paramètre
     *
     * @param   dateDebut(java.sql.Date)
     * @param   dateFin(java.sql.Date)
     *
     * @return ArrayList<Commande>
     */
    public ArrayList<Commande> selAllCommandeParDate (java.sql.Date dateDebut, java.sql.Date dateFin)
            throws SQLException, ExceptionDataBase {
        ArrayList<Commande> data = new ArrayList<Commande>();
        this.stmt = db.con.prepareStatement(SEL_COMMANDE_BETWEEN_TWO_DATES);

        ResultSet rs = this.stmt.executeQuery();

        return this.createTabCommande(rs);
    }

    /**
     * Permet de récupérer le contenu d'une commande en fonction de son id passé en paramètre
     *
     * @param   id_commande(int)
     *
     * @return ArrayList<Commande>
     */
    public ArrayList<Contenu_Commande> selAllContenuCommandeParID (int id_commande) throws SQLException, ExceptionDataBase {
        ArrayList<Contenu_Commande> data = new ArrayList<Contenu_Commande>();
        this.stmt = db.con.prepareStatement(SEL_CONTENU_COMMANDE_PAR_ID);

        ResultSet rs = this.stmt.executeQuery();

        return this.createTabContenuCommande(rs);
    }

    /**
     * Permet de créer une ArrayList de Stock à partir de Resultset passé en paramètre
     *
     * @param rs(ResultSet)
     *
     * @return ArrayList<Stock>
     */
    private ArrayList<Stock> createTabStock (ResultSet rs) throws ExceptionDataBase, SQLException {
        ArrayList<Stock> data = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Le stock est vide.");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data.add(new Stock(rs.getString("nom"), rs.getDouble("quantite"),
                        rs.getDouble("quantiteMin"), rs.getString("unite")));
            }
        }
        // Fermeture de la DB obligatoire après le ResultSet !
        // Doit être ici !

        return data;
    }

    /**
     * Permet de créer une ArrayList de Commande à partir de Resultset passé en paramètre
     *
     * @param rs(ResultSet)
     *
     * @return ArrayList<Commande>
     */
    private ArrayList<Commande> createTabCommande (ResultSet rs) throws ExceptionDataBase, SQLException {
        ArrayList<Commande> data = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Le stock est vide.");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data.add(new Commande(rs.getInt("id"), rs.getDate("date")));
            }
        }
        // Fermeture de la DB obligatoire après le ResultSet !
        // Doit être ici !

        return data;
    }

    /**
     * Permet de créer une ArrayList de Contenu_Commande à partir de Resultset passé en paramètre
     *
     * @param rs(ResultSet)
     *
     * @return ArrayList<Contenu_Commande>
     */
    private ArrayList<Contenu_Commande> createTabContenuCommande (ResultSet rs) throws ExceptionDataBase, SQLException {
        ArrayList<Contenu_Commande> data = new ArrayList<Contenu_Commande>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun produit n'est contenu dans la commande avec cet ID");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data.add(new Contenu_Commande(rs.getInt("id"), rs.getString("nom"),
                        rs.getDouble("quantite"), rs.getInt("commande")));
            }
        }
        // Fermeture de la DB obligatoire après le ResultSet !
        // Doit être ici !

        return data;
    }

}
