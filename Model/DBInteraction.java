package Model;

import jdk.nashorn.internal.runtime.ECMAException;

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
    // ADRESSE :
    // Récupérer toutes les informations sur les villes
    private static final String SEL_ALL_VILLE = "SELECT * FROM Ville;";

    // Récupérer toutes les informations sur les pays
    private static final String SEL_ALL_PAYS = "SELECT * FROM Pays;";

    // Récupère un pays en particulier d'après son nom
    private static final String SEL_PAYS_PAR_NOM = "SELECT * FROM Pays WHERE pays LIKE ?;";

    // Récupère une ville d'après son nom
    private static final String SEL_VILLE_PAR_NOM = "SELECT * FROM Ville WHERE ville LIKE ?;";

    // Insérer un nouveau Pays dans la DB
    private static final String INSERT_PAYS = "INSERT INTO Pays VALUES (null , ? );";

    // Insérer une nouvelle Ville dans la DB
    private static final String INSERT_VILLE = "INSERT INTO Ville VALUES ( ? , ? , ? );";

    // Insérer une nouvelle Adresse dans la DB
    private static final String INSERT_ADRESSE = "INSERT INTO Adresse VALUES (null, ? , ? );";

    // Récupérer le paysId d'une ville "String"
    private static final String SEL_PAYS_ID = "SELECT paysId FROM Pays WHERE pays LIKE ? ;";

    // Récupère le code postal d'après le nom de la ville
    private static final String SEL_CP_PAR_VILLE = "SELECT codePostal FROM Ville WHERE ville LIKE ?;";

    // Récupérer la ville en fonction d'un code postal
    private static final String SEL_VILLE_PAR_CP = "SELECT ville FROM Ville WHERE codePostal = ? ;";

    // Récupérer la ville en fonction d'un code postal
    private static final String SEL_VILLE_ID_PAR_CP = "SELECT ville FROM Ville WHERE codePostal = ? ;";

    // Récupérer les informations sur une adresse et la ville en relation
    private static final String SEL_ADRESSE_PAR_CP_ET_ADRESSE =
            "SELECT * FROM Adresse WHERE adresse LIKE ? AND codePostal = ? ;";
    // -----------------------------------------------------------------------------------------------------------------
    // PERSONNE :
    private static final String NOMBRE_PERSONNE = "SELECT COUNT(*) as nbPersonne FROM Personne;";
    private static final String SEL_ALL_EMPLOYES = "SELECT * FROM Personne;";
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
    private static final String DEL_PERSONNE = "DELETE FROM Personne WHERE idPersonne = ?;";
    private static final String SEL_TYPE_CONTRAT = "SELECT DISTINCT typeContrat FROM Personne";
    private static final String SEL_ALL_STATUTS = "SELECT DISTINCT statut FROM Personne";
    // -----------------------------------------------------------------------------------------------------------------
    // ANIMAUX :
    private static final String INSERT_ANIMAL = "INSERT INTO Animal (id, nom, sexe, dateNaissance, enclos, origine, dateDeces) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_FELIN = "INSERT INTO Animal_Fauve (id, poids) VALUES (?, ?);";
    private static final String INSERT_OISEAU = "INSERT INTO Animal_Oiseau (id, envergure, bague) VALUES (?, ?, ?);";
    private static final String INSERT_REPTILE = "INSERT INTO Animal_Reptile (id, temperature) VALUES (?, ?);";
    private static final String INSERT_PRIMATE = "INSERT INTO Animal_Primate (id, temperature) VALUES (?, ?);";
    private static final String DELETE_ANIMAL = "DELETE FROM Animal WHERE id = ?;";
    private static final String DELETE_FAUVE = "DELETE FROM Animal_Fauve WHERE id = ?;";
    private static final String DELETE_OISEAU = "DELETE FROM Animal_Oiseau WHERE id = ?;";
    private static final String DELETE_PRIMATE = "DELETE FROM Animal_Primate WHERE id = ?;";
    private static final String DELETE_REPTILE = "DELETE FROM Animal_Reptile WHERE id = ?;";

    private static final String SEL_ALL_RACE_ANIMAL = "SELECT nom FROM Race;";
    // Récupérer uniquement ces 5 paramètress de l'animal
    private static final String SEL_ANIMAL_ID_NOM_RACE_SEX_DATENAISSANCE = "SELECT id, nom, race, sexe, dateNaissance " +
            "FROM Animal;";
    // Récupérer tous les paramètre d'un animal
    private static final String SEL_ANIMAL = "SELECT * FROM Animal;";
    // Récupérer les informations d'un animal en fonction de son ID
    private static final String SEL_ANIMAL_ID = "SELECT * FROM Animal WHERE id = ? ;";
    // Modifier les parametre d'un animal
    private static final String UPDATE_ANIMAL = "UPDATE Animal " +
            "SET nom = ?, " +
            "sexe = ?, " +
            "dateNaissance = ?, " +
            "enclos = ?, " +
            "origine = ?, " +
            "dateDeces = ?, " +
            "race = ? " +
            "WHERE id = ?;";
    // Modifier les parametre d'un FAUVE
    private static final String UPDATE_ANIMAL_FAUVE = "UPDATE Animal_Fauve " +
            "SET poids = ? " +
            "WHERE id = ?;";
    // Modifier les parametre d'un REPTILE
    private static final String UPDATE_ANIMAL_REPTILE = "UPDATE Animal_Reptile " +
            "SET temperature = ? " +
            "WHERE id = ?;";
    // Modifier les parametre d'un PRIMATE
    private static final String UPDATE_ANIMAL_PRIMATE = "UPDATE Animal_Primate " +
            "SET temperature = ? " +
            "WHERE id = ?;";
    // Modifier les parametre d'un OISEAU
    private static final String UPDATE_ANIMAL_OISEAU = "UPDATE Animal_Oiseau " +
            "SET envergure = ?, " +
            "bague = ? " +
            "WHERE id = ?;";
    // Récupérer tous les ID des OISEAUX
    private static final String SELECT_ALL_ID_OISEAU = "SELECT id FROM Animal_Oiseau;";
    // Récupérer tous les ID des FAUVES
    private static final String SELECT_ALL_ID_FAUVE = "SELECT id FROM Animal_Fauve;";
    // Récupérer tous les ID des FAUVES
    private static final String SELECT_ALL_ID_PRIMATE = "SELECT id FROM Animal_Primate;";
    // Récupérer tous les ID des FAUVES
    private static final String SELECT_ALL_ID_REPTILE = "SELECT id FROM Animal_Reptile;";
    // Récupérer toues les infos d'un FAUVE par rapport à un ID
    private static final String SELECT_ALL_INFO_FAUVE_ID =
            "SELECT Animal_Fauve.id AS id, poids, nom, sexe, dateNaissance, enclos, origine, dateDeces, race " +
                    "FROM Animal_Fauve " +
                    "INNER JOIN Animal " +
                    "ON Animal.id = Animal_Fauve.id " +
                    "WHERE Animal.id =  ? ;";
    // Récupérer toues les infos d'un OISEAU par rapport à un ID
    private static final String SELECT_ALL_INFO_OISEAU_ID =
            "SELECT Animal_Oiseau.id AS id , envergure, bague , nom, sexe, dateNaissance, enclos, origine, dateDeces, race " +
                    "FROM Animal_Oiseau " +
                    "INNER JOIN Animal " +
                    "ON Animal.id = Animal_Oiseau.id " +
                    "WHERE Animal.id =  ? ;";
    // Récupérer toues les infos d'un REPTILE par rapport à un ID
    private static final String SELECT_ALL_INFO_REPTILE_ID =
            "SELECT Animal_Reptile.id AS id , temperature , nom, sexe, dateNaissance, enclos, origine, dateDeces, race " +
                    "FROM Animal_Reptile " +
                    "INNER JOIN Animal " +
                    "ON Animal.id = Animal_Reptile.id " +
                    "WHERE Animal.id =  ? ;";
    // Récupérer tous les infos d'un REPTILE par rapport à un ID
    private static final String SELECT_ALL_INFO_PRIMATE_ID =
            "SELECT Animal_Primate.id AS id , temperature , nom, sexe, dateNaissance, enclos, origine, dateDeces, race " +
                    "FROM Animal_Primate " +
                    "INNER JOIN Animal " +
                    "ON Animal.id = Animal_Primate.id " +
                    "WHERE Animal.id =  ? ;";
    // Récupérer toutes les races d'animal
    private static final String SEL_ALL_ANIMAL_RACE = "SELECT * FROM Animal_Race;";
    // -----------------------------------------------------------------------------------------------------------------
    // ENCLOS :

    private static final String SEL_ENCLOS = "SELECT * FROM Enclos WHERE id = ?;";
    private static final String SEL_ENCLOS_ALL = "SELECT * FROM Enclos";
    // -----------------------------------------------------------------------------------------------------------------
    // EVENEMENT :
    private static final String SEL_EVENEMENTS_PAR_TYPE = "SELECT * FROM Evenement WHERE type LIKE ?;";

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

    // Selectionner le nom du type d'un événement en fonction de son ID
    private static final String SEL_EVENT_BY_ID = "SELECT * FROM Evenement WHERE id = ?;";
    private static final String DEL_EVENT_BY_ID = "DELETE FROM Evenement WHERE id = ?;";

    private static final String DEL_EVENT_TYPE = "DELETE FROM Evenement_Type WHERE type LIKE ?;";
    private static final String INS_EVENT_TYPE = "INSERT INTO Evenement_Type VALUES (?);";
    private static final String SEL_EVENT_TYPES = "SELECT DISTINCT(type) FROM Evenement_Type;";
    private static final String SEL_EVENT_TYPE_FROM_EVENT_ID = "SELECT type FROM Evenement WHERE id = ? ;";
    private static final String SEL_EVENT_TYPE_FROM_EVENT_NAME = "SELECT type FROM Evenement WHERE description LIKE ? ;";

    private static final String SEL_ANIMALS_FROM_EVENT_ID = "SELECT * FROM Animal INNER JOIN Animal_Evenement ON Animal.id = Animal_Evenement.animal WHERE Animal_Evenement.evenement = ?;";

    // Enlever un peu de quantité d'un produit
    private static final String UPDATE_DELETE_QUANTITE_OF_DESCRIPTION =
            "UPDATE Stock " +
                    "SET quantite = quantite - ? " +
                    "WHERE id = ?;";
    // Ajouter un peu de quantité d'un produit
    private static final String UPDATE_ADD_QUANTITE_OF_DESCRIPTION =
            "UPDATE Stock " +
                    "SET quantite = quantite + ? " +
                    "WHERE id = ?;";
    // -----------------------------------------------------------------------------------------------------------------
    // STOCK :
    // Récupérer l'état de tout le stock (nom, quantite, unite, quantiteMin
    private static final String SEL_ALL_STOCK = "SELECT * FROM Stock;";

    // Récupérer toutes les commandes qui ont été faites (id, date et statut)
    private static final String SEL_ALL_COMMANDE = "SELECT * FROM Commande";

    // Récupérer l'ID et la date de toute les commandes faites entre deux dates Date1 et Date2
    private static final String SEL_COMMANDE_BETWEEN_TWO_DATES = "SELECT * FROM Commande WHERE `date` BETWEEN ? AND ? ;";

    // Récupérer le contenu d'une commande en fonction de son ID
    private static final String SEL_CONTENU_COMMANDE_PAR_ID = "SELECT * FROM Commande_Contenu WHERE idCommande = ? ;";

    // Pour un article donné, récupère la quantité en cours de commande (si commandé)
    private static final String SEL_ARTICLE_COMMANDE_EN_COURS = "SELECT SUM(quantite) AS quantiteEnCours \n" +
            "FROM Commande_Contenu \n" +
            "INNER JOIN Commande \n" +
            "\tON Commande_Contenu.idCommande = Commande.id \n" +
            "WHERE refArticle = ?\n" +
            "AND Commande.statut = \"EN_COURS\";";

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
    // Partie pour la gestion des ADRESSES dans la DB
    /**
     * Permet d'insérer une nouvelle adresse complète (Adresse, Ville , Pays)
     *
     * @param adresse String
     * @param ville String
     * @param pays Pays
     */
    public void insAddress(Adresse adresse, Ville ville, Pays pays) throws SQLException, ExceptionDataBase {
        // Check si le pays est déjà dans la DB
        // l'insère si non
        if (!this.countryIsInDB(pays)) {
            this.insPays(pays);
        }

        // Check si la ville est déjà dans la DB
        // l'insère si non
        if (!this.cpIsInDB(ville.getCp())) {
            this.insVille(ville);
        }

        // Check si l'adresse en fonction de la ville est déjà dans la DB
        // l'insère si non
        int cpVille = this.getCodePostalParVille(ville.getVille());
        if (cpVille == 0) {
            throw new ExceptionDataBase("Aucun code postal trouvé pour cette adresse : " + adresse);
        }

        if (!this.addressIsInDB(adresse)) {
            this.insAdresse(adresse);
        }
    }

    private int getCodePostalParVille(String ville) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(SEL_CP_PAR_VILLE);
        this.stmt.setString(1, ville);
        ResultSet rs = this.stmt.executeQuery();

        if (rs.next()) {
            rs.beforeFirst();
            return rs.getInt("codePostal");
        } else {
            return 0;
        }
    }

    /**
     * Permet d'insérer une nouvelle adresse dans la base de données
     *
     * @param adresse Adresse
     */
    private void insAdresse(Adresse adresse) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(INSERT_ADRESSE);
        this.stmt.setString(2, adresse.getAdresse());
        this.stmt.setInt(3, adresse.getVille().getCp());
        this.stmt.executeUpdate();
    }


    /**
     * Permet d'insérer un nouveau Pays dans la base de données
     *
     * @param pays String
     */
    private void insPays(Pays pays) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(INSERT_PAYS);
        this.stmt.setString(1, pays.getPays());
        this.stmt.executeUpdate();
    }

    /**
     * Permet d'insérer une nouvelle Ville dans la base de données
     *
     * @param ville String
     */
    private void insVille(Ville ville) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(INSERT_VILLE);
        this.stmt.setInt(1, ville.getCp());
        this.stmt.setString(2, ville.getVille());
        this.stmt.setInt(3, ville.getPays().getPaysId());   // paysId as foreign key
        this.stmt.executeUpdate();
    }

    /**
     * Permet de récupérer l'id d'un Pays
     *
     * @param pays String
     *
     * @return int
     */
    public int getPaysID (String pays) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(SEL_PAYS_ID);
        this.stmt.setString(1, pays);
        ResultSet rs = this.stmt.executeQuery();
        rs.next();

        return rs.getInt("paysId");
    }

    /**
     * Permet de récupérer le nom d'une ville en fonction d'un code postal passé en paramètre
     *
     * @param cp String
     *
     * @return String
     */
    public String getVilleParCP (int cp) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(SEL_VILLE_PAR_CP);
        this.stmt.setInt(1, cp);
        ResultSet rs = this.stmt.executeQuery();


        return rs.getString("ville");
    }

    /**
     * Permet de récupérer le nom d'une ville en fonction de son code postal
     *
     * @param cp String
     *
     * @return int
     */
    public String getVilleIDParCP(int cp) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(SEL_VILLE_ID_PAR_CP);
        this.stmt.setInt(1, cp);
        ResultSet rs = this.stmt.executeQuery();
        rs.next();

        return rs.getString("ville");
    }


    /**
     * Permet de savoir si un code postal est déjà présent dans la db
     * Retourne la codePostal du code Postal si présent
     * Retourne 0 si non présent
     *
     * @param cp Int
     *
     * @return int
     */
    public boolean cpIsInDB(int cp) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(SEL_VILLE_PAR_CP);
        this.stmt.setInt(1, cp);
        ResultSet rs = this.stmt.executeQuery();

        return rs.next();
    }

    /**
     * Permet de savoir si un Pays est déjà présent dans la db
     * Retourne le paysId du Pays si présent
     * Retourne 0 si non présent
     *
     * @param pays String
     *
     * @return int
     */
    public boolean countryIsInDB(Pays pays) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(SEL_PAYS_PAR_NOM);
        this.stmt.setString(1, pays.getPays());
        ResultSet rs = this.stmt.executeQuery();

        return rs.next();
    }

    /**
     * Permet de savoir si une adresse existe déjà dans une ville
     *
     * @param adresse Adresse
     *
     * @return boolean
     */
    public boolean addressIsInDB(Adresse adresse) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(SEL_ADRESSE_PAR_CP_ET_ADRESSE);
        this.stmt.setString(1, adresse.getAdresse());
        this.stmt.setInt(2, adresse.getVille().getCp());
        ResultSet rs = this.stmt.executeQuery();

        return rs.next();
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

    public ArrayList<String> getAllStatuts() throws SQLException, ExceptionDataBase {

        //noinspection AccessStaticViaInstance
        this.stmt = DBConnection.con.prepareStatement(SEL_ALL_STATUTS);
        ResultSet rs = this.stmt.executeQuery();

        ArrayList<String> listStatuts = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Cette requête n'a retourné aucun résultat");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                listStatuts.add(rs.getString("statut"));
            }
        }
        return listStatuts;
    }


    public ArrayList<String> getAllDifferentStatus() throws SQLException, ExceptionDataBase {

        this.stmt = DBConnection.con.prepareStatement(SEL_TYPE_CONTRAT);
        ResultSet rs = this.stmt.executeQuery();

        ArrayList<String> listTypeContrat = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Cette requête n'a retourné aucun résultat");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                listTypeContrat.add(rs.getString("typeContrat"));
            }
        }
        return listTypeContrat;
    }


    public ArrayList<String> selAllContractType() throws SQLException, ExceptionDataBase {

        this.stmt = DBConnection.con.prepareStatement(SEL_TYPE_CONTRAT);
        ResultSet rs = this.stmt.executeQuery();

        ArrayList<String> listTypeContrat = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Cette requête n'a retourné aucun résultat");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                listTypeContrat.add(rs.getString("typeContrat"));
            }
        }
        return listTypeContrat;
    }


    public void delPersonne(int id) throws SQLException {

        this.stmt = DBConnection.con.prepareStatement(DEL_PERSONNE);
        this.stmt.setInt(1, id);
        this.stmt.execute();
    }


    public ArrayList<Personne> selAllEmployes() throws SQLException, ExceptionDataBase {

        ArrayList<Personne> listEmployes;
        this.stmt = DBConnection.con.prepareStatement(SEL_ALL_EMPLOYES);
        ResultSet rs = this.stmt.executeQuery();
        listEmployes = creerTableauPersonne(rs);

        return listEmployes;
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
        this.stmt = DBConnection.con.prepareStatement(SEL_EMPLOYE_DETAILS);
        this.stmt.setInt(1, noAVS);
        ResultSet rs = this.stmt.executeQuery();
        ArrayList<Personne>  pers = creerTableauPersonne(rs);

        return pers.get(0);
    }

    /**
     * Permet d'obtenir dans les noms et prenoms de tous les employés présents dans la base de données
     *
     * @return ArrayList<Personne>
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
        this.stmt = DBConnection.con.prepareStatement(SEL_EMPLOYE_PAR_PRENOM_NOM);
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
        this.stmt = DBConnection.con.prepareStatement(SEL_EMPLOYE_PAR_NOM);
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
        this.stmt = DBConnection.con.prepareStatement(NOMBRE_PERSONNE);
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
    public void insertPersonne(String noAVS, String prenom, String nom, int adresse, String email,
                            String telephone, java.sql.Date dateNaissance, int responsable, String statut,
                            double salaire, java.sql.Date dateDebut, String typeContrat)
            throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(INSERT_EMPLOYE);
        this.stmt.setString(1, noAVS);
        this.stmt.setString(2, prenom);
        this.stmt.setString(3, nom);
        this.stmt.setInt(4, adresse);
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
    public void insertPersonne(Personne personne) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(INSERT_EMPLOYE);
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
        this.stmt = DBConnection.con.prepareStatement(REQUETE);
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
     * Permet d'obtenir tous les nom des races d'animaux disponibles
     *
     * @date 27.04.2017 Modification du type de retour : ArrayList<String> vers ArrayList<Race>
     *
     * @return ArrayList<Race>
     */
    public ArrayList<Race> getAllRaceAnimal() throws SQLException, ExceptionDataBase {
        this.stmt = DBConnection.con.prepareStatement(SEL_ALL_ANIMAL_RACE);
        ResultSet rs = this.stmt.executeQuery();

        return createTabAnimalRace(rs);
    }
    /**
     * Permet de récupérer toutes les informations d'un ANIMAL en fonction de son ID
     *
     * @param id int
     *
     * @return Object
     */
    public Object getAnimal (int id) throws SQLException, ExceptionDataBase {
        Object data = new Object();
        if (isFelin(id)) {
            ArrayList<Felin> dataF = selAllFelinByID(id);
            data = dataF.get(0);
        } else if (isOiseau(id)) {
            ArrayList<Oiseau> dataO = selAllOiseauByID(id);
            data = dataO.get(0);
        } else if (isReptile(id)) {
            ArrayList<Reptile> dataR = selAllReptileByID(id);
            data = dataR.get(0);
        } else if (isPrimate(id)) {
            ArrayList<Primate> dataP = selAllPrimateByID(id);
            data = dataP.get(0);
        }
        return data;
    }
    /**
     * Permet de récupérer toutes les informations d'un PRIMATE
     *
     * @param id int
     *
     * @return ArrayList<Primate>
     */
    private ArrayList<Primate> selAllPrimateByID (int id) throws SQLException, ExceptionDataBase {
        this.stmt = DBConnection.con.prepareStatement(SELECT_ALL_INFO_PRIMATE_ID);
        this.stmt.setInt(1, id);
        ResultSet rs = this.stmt.executeQuery();

        return createTabPrimate(rs);
    }

    /**
     * Permet de récupérer toutes les informations d'un REPTILE
     *
     * @param id int
     *
     * @return ArrayList<Reptile>
     */
    private ArrayList<Reptile> selAllReptileByID (int id) throws SQLException, ExceptionDataBase {
        this.stmt = DBConnection.con.prepareStatement(SELECT_ALL_INFO_REPTILE_ID);
        this.stmt.setInt(1, id);
        ResultSet rs = this.stmt.executeQuery();

        return createTabReptile(rs);

    }

    /**
     * Permet de récupérer toutes les informations d'un FAUVE
     *
     * @param id int
     *
     * @return ArrayList<Felin>
     */
    private ArrayList<Felin> selAllFelinByID (int id) throws SQLException, ExceptionDataBase {
        this.stmt = DBConnection.con.prepareStatement(SELECT_ALL_INFO_FAUVE_ID);
        this.stmt.setInt(1, id);
        ResultSet rs = this.stmt.executeQuery();

        return createTabFelin(rs);

    }

    /**
     * Permet de récupérer toutes les informations d'un OISEAU
     *
     * @param id int
     *
     * @return ArrayList<Oiseau>
     */
    private ArrayList<Oiseau> selAllOiseauByID (int id) throws SQLException, ExceptionDataBase {
        this.stmt = DBConnection.con.prepareStatement(SELECT_ALL_INFO_OISEAU_ID);
        this.stmt.setInt(1, id);
        ResultSet rs = this.stmt.executeQuery();

        return createTabOiseau(rs);

    }

    /**
     * Permet de savoir si un Animal est de type FELIN
     *
     * @param id int
     *
     * @return boolean
     */
    private boolean isFelin (int id) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(SELECT_ALL_ID_FAUVE);
        ResultSet rs = this.stmt.executeQuery();

        while (rs.next()) {
            if (rs.getInt("id") == id)
                return true;
        }
        return false;
    }

    /**
     * Permet de savoir si un Animal est de type OISEAU
     *
     * @param id int
     *
     * @return boolean
     */
    private boolean isOiseau (int id) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(SELECT_ALL_ID_OISEAU);
        ResultSet rs = this.stmt.executeQuery();

        while (rs.next()) {
            if (rs.getInt("id") == id)
                return true;
        }
        return false;
    }

    /**
     * Permet de savoir si un Animal est de type REPTILE
     *
     * @param id int
     *
     * @return boolean
     */
    private boolean isReptile (int id) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(SELECT_ALL_ID_REPTILE);
        ResultSet rs = this.stmt.executeQuery();

        while (rs.next()) {
            if (rs.getInt("id") == id)
                return true;
        }
        return false;
    }

    /**
     * Permet de savoir si un Animal est de type PRIMATE
     *
     * @param id int
     *
     * @return boolean
     */
    private boolean isPrimate (int id) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(SELECT_ALL_ID_PRIMATE);
        ResultSet rs = this.stmt.executeQuery();

        while (rs.next()) {
            if (rs.getInt("id") == id)
                return true;
        }
        return false;
    }

    /**
     * Permet de mettre a jour les informations d'un animal
     *
     * @param animal Animal
     */
    public void updateAnimal (Animal animal) throws SQLException, ExceptionDataBase {
        int id_animal = animal.getId();

        // Modification dans la table ANIMAL
        this.stmt = DBConnection.con.prepareStatement(UPDATE_ANIMAL);
        this.stmt.setString(1, animal.getNom());
        this.stmt.setString(2, animal.getSexe());
        this.stmt.setDate(3, animal.getAnneeNaissance());
        this.stmt.setInt(4, animal.getEnclos());
        this.stmt.setInt(5, animal.getOrigine());
        this.stmt.setDate(6, animal.getDateDeces());
        this.stmt.setInt(7, animal.getRace());
        this.stmt.setInt(8, animal.getId());
        this.stmt.executeUpdate();


        // Definition du type de l'animal
        if (animal instanceof Oiseau)
            updateAnimalOiseau((Oiseau) animal);
        else if (animal instanceof Reptile)
            updateAnimalReptile((Reptile) animal);
        else if (animal instanceof Felin)
            updateAnimalFauve((Felin) animal);
        else if (animal instanceof Primate)
            updateAnimalPrimate((Primate) animal);
        else
            throw new ExceptionDataBase("L'animal passé en paramètre n'est pas Compatible avec la base de données");
    }

    /**
     * Permet de mettre a jour les informations d'un Felin
     *
     * @param animal Felin
     */
    private void updateAnimalFauve (Felin animal) throws SQLException {
        int id_animal = animal.getId();

        // Modification dans la table ANIMAL
        this.stmt = DBConnection.con.prepareStatement(UPDATE_ANIMAL_FAUVE);
        this.stmt.setDouble(1, animal.getPoids());
        this.stmt.executeUpdate();
    }

    /**
     * Permet de mettre a jour les informations d'un Reptile
     *
     * @param animal Reptile
     */
    private void updateAnimalReptile (Reptile animal) throws SQLException {
        int id_animal = animal.getId();

        // Modification dans la table ANIMAL
        this.stmt = DBConnection.con.prepareStatement(UPDATE_ANIMAL_REPTILE);
        this.stmt.setDouble(1, animal.getTemperature());
        this.stmt.executeUpdate();
    }

    /**
     * Permet de mettre a jour les informations d'un Primate
     *
     * @param animal Primate
     */
    private void updateAnimalPrimate (Primate animal) throws SQLException {
        int id_animal = animal.getId();

        // Modification dans la table ANIMAL
        this.stmt = DBConnection.con.prepareStatement(UPDATE_ANIMAL_PRIMATE);
        this.stmt.setDouble(1, animal.getTemperature());
        this.stmt.executeUpdate();
    }

    /**
     * Permet de mettre a jour les informations d'un Oiseau
     *
     * @param animal Oiseau
     */
    private void updateAnimalOiseau (Oiseau animal) throws SQLException {
        int id_animal = animal.getId();

        // Modification dans la table ANIMAL
        this.stmt = DBConnection.con.prepareStatement(UPDATE_ANIMAL_OISEAU);
        this.stmt.setDouble(1, animal.getEnvergure());
        this.stmt.setString(2, animal.getBague());
        this.stmt.executeUpdate();
    }

    /**
     * Permet d'obtenir l'id, le nom, le sexe, la dateNaissance, et la race de tous les animaux
     *
     * @return ArrayList<Animal>
     */
    public ArrayList<Animal> selAnimaux() throws SQLException, ExceptionDataBase {
        this.stmt = DBConnection.con.prepareStatement(SEL_ANIMAL);
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
        ArrayList<Animal> data = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun Animal ne correspond aux infos rentrées ");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data.add(new Animal(rs.getInt("id"), rs.getString("nomCommun"),
                        rs.getString("nom"), rs.getString("sexe"),
                        rs.getDate("dateNaissance"), rs.getInt("enclos"), rs.getInt("origine"),
                        rs.getInt("race"), rs.getDate("dateDeces")));
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
                data.add(new Enclos(rs.getInt("id"), rs.getString("nom"),
                        rs.getInt("secteur"), rs.getDouble("surface")));
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
    public ArrayList<String> selAllRaceAnimal() throws SQLException {
         ArrayList<String> data = new ArrayList<>();
        this.stmt = DBConnection.con.prepareStatement(SEL_ALL_RACE_ANIMAL);
         ResultSet rs = this.stmt.executeQuery();
         while (rs.next()) {
             data.add(rs.getString("nom"));
         }
         return data;
     }

    public void delAnimal(Animal a) throws ExceptionDataBase, SQLException {
        this.stmt = DBConnection.con.prepareStatement(DELETE_ANIMAL);
        this.stmt.setInt(1, a.getId());
        this.stmt.execute();
        this.stmt = null;

        if (a instanceof Felin) {
            this.stmt = DBConnection.con.prepareStatement(DELETE_FAUVE);
            this.stmt.setInt(1, a.getId());
        } else if (a instanceof Oiseau) {
            this.stmt = DBConnection.con.prepareStatement(DELETE_OISEAU);
            this.stmt.setInt(1, a.getId());
        } else if (a instanceof Primate) {
            this.stmt = DBConnection.con.prepareStatement(DELETE_PRIMATE);
            this.stmt.setInt(1, a.getId());
        } else if (a instanceof Reptile) {
            this.stmt = DBConnection.con.prepareStatement(DELETE_REPTILE);
            this.stmt.setInt(1, a.getId());
        }

        this.stmt.execute();
    }

    private void insFelin(Felin f) throws SQLException {
        this.stmt = null;
        this.stmt = DBConnection.con.prepareStatement(INSERT_FELIN);
        this.stmt.setInt(1, f.getId());
        this.stmt.setDouble(2, f.getPoids());
        this.stmt.execute();
    }

    private void insReptile(Reptile r) throws SQLException {
        this.stmt = null;
        this.stmt = DBConnection.con.prepareStatement(INSERT_REPTILE);
        this.stmt.setInt(1, r.getId());
        this.stmt.setDouble(2, r.getTemperature());
        this.stmt.execute();
    }

    private void insOiseau(Oiseau o) throws SQLException {
        this.stmt = null;
        this.stmt = DBConnection.con.prepareStatement(INSERT_OISEAU);
        this.stmt.setInt(1, o.getId());
        this.stmt.setDouble(2, o.getEnvergure());
        this.stmt.setString(3, o.getBague());
    }

    private void insPrimate(Primate o) throws SQLException {
        this.stmt = null;
        this.stmt = DBConnection.con.prepareStatement(INSERT_PRIMATE);
        this.stmt.setInt(1, o.getId());
        this.stmt.setDouble(2, o.getTemperature());
    }

    public void insAnimal(Animal a) throws SQLException {

        this.stmt = DBConnection.con.prepareStatement(INSERT_ANIMAL, Statement.RETURN_GENERATED_KEYS);

        // Attribut communs à tous les animaux
        this.stmt.setNull(1, Types.NULL);
        this.stmt.setString(2, a.getNom());
        this.stmt.setString(3, a.getSexe());
        this.stmt.setDate(4, a.getAnneeNaissance());
        this.stmt.setInt(5, a.getEnclos());
        this.stmt.setInt(6, a.getOrigine());
        this.stmt.setDate(7, a.getDateDeces());

        // En premier lieu, on enregistre l'animal dans la DB
        this.stmt.execute();
        ResultSet rs = this.stmt.getGeneratedKeys();
        if (rs.next()) {    // On récupère l'ID de l'animal inséré
            rs.beforeFirst();   // On remet le curseur au début
            int newAnimalID = rs.getInt(1);
            a.setId(newAnimalID);
        }

        if (a instanceof Felin) {
            this.insFelin((Felin) a);
        } else if (a instanceof Reptile) {
            this.insReptile((Reptile) a);
        } else if (a instanceof Oiseau) {
            this.insOiseau((Oiseau) a);
        } else if (a instanceof Primate) {
            this.insPrimate((Primate) a);
        }
    }

    /*
    ENCLOS
     */
    public Enclos selEnclos(int id) throws SQLException, ExceptionDataBase {

        this.stmt = DBConnection.con.prepareStatement(SEL_ENCLOS);
        this.stmt.setInt(1, id);
        ResultSet rs = this.stmt.executeQuery();

        Enclos data = null;

        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun enclos correspondants");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data = new Enclos(rs.getInt("id"), rs.getString("nom"),
                        rs.getInt("secteur"), rs.getDouble("surface"));
            }
        }

        return data;
    }


    public ArrayList<Enclos> selEnclos() throws SQLException, ExceptionDataBase {

        this.stmt = DBConnection.con.prepareStatement(SEL_ENCLOS_ALL);
        ResultSet rs = this.stmt.executeQuery();

        ArrayList<Enclos> data = new ArrayList<>();

        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun enclos correspondants");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data.add(new Enclos(rs.getInt("id"), rs.getString("nom"),
                        rs.getInt("secteur"), rs.getDouble("surface")));
            }
        }

        return data;
    }

    /**
     * Permet de créer une ArrayList de FAUVE à partir de Resultset passé en paramètre
     *
     * @param rs(ResultSet)
     *
     * @return ArrayList<Felin>
     */
    private ArrayList<Felin> createTabFelin (ResultSet rs) throws ExceptionDataBase, SQLException {
        ArrayList<Felin> data = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun FELIN n'existe avec cet ID");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data.add(new Felin(rs.getInt("id"), rs.getString("nomCommun"),
                        rs.getString("nom"), rs.getString("sexe"),
                        rs.getDate("dateNaissance"), rs.getInt("enclos"),
                        rs.getInt("origine"), rs.getInt("race"),
                        rs.getDate("dateDeces"),
                        rs.getDouble("race")));
            }

            return data;
        }
    }
    /**
     * Permet de créer une ArrayList de OISEAU à partir de Resultset passé en paramètre
     *
     * @param rs(ResultSet)
     *
     * @return ArrayList<Oiseau>
     */
    private ArrayList<Oiseau> createTabOiseau (ResultSet rs) throws ExceptionDataBase, SQLException {
        ArrayList<Oiseau> data = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun Oiseau n'existe avec cet ID");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data.add(new Oiseau(rs.getInt("id"), rs.getString("nomCommun"),
                        rs.getString("nom"), rs.getString("sexe"),
                        rs.getDate("dateNaissance"), rs.getInt("enclos"),
                        rs.getInt("origine"), rs.getInt("race"),
                        rs.getDate("dateDeces"), rs.getDouble("envergure"),
                        rs.getString("bague")));
            }
            return data;
        }
    }
    /**
     * Permet de créer une ArrayList de REPTILE à partir de Resultset passé en paramètre
     *
     * @param rs(ResultSet)
     *
     * @return ArrayList<Reptile>
     */
    private ArrayList<Reptile> createTabReptile (ResultSet rs) throws ExceptionDataBase, SQLException {
        ArrayList<Reptile> data = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun Reptile n'existe avec cet ID");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data.add(new Reptile(rs.getInt("id"), rs.getString("nomCommun"), rs.getString("nom"),
                        rs.getString("sexe"), rs.getDate("dateNaissance"),
                        rs.getInt("enclos"), rs.getInt("origine"),
                        rs.getInt("race"), rs.getDate("dateDeces"),
                        rs.getDouble("temperature")));
            }
            return data;
        }
    }

    /**
     * Permet de créer une ArrayList de PRIMATE à partir de Resultset passé en paramètre
     *
     * @param rs(ResultSet)
     *
     * @return ArrayList<Primate>
     */
    private ArrayList<Primate> createTabPrimate (ResultSet rs) throws ExceptionDataBase, SQLException {
        ArrayList<Primate> data = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun Reptile n'existe avec cet ID");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data.add(new Primate(rs.getInt("id"), rs.getString("nomCommun"),
                        rs.getString("nom"), rs.getString("sexe"),
                        rs.getDate("dateNaissance"), rs.getInt("enclos"),
                        rs.getInt("origine"), rs.getInt("race"),
                        rs.getDate("dateDeces"), rs.getDouble("temperature")));
            }
            return data;
        }
    }


    /**
     * Permet de créer un tableau contenant le nom de toutes les races d'animax à partir de Resultset passé en paramètre
     *
     * @date 27.04.2017 Modification du type de retour de ArrayList<String> vers ArrayList<Race>
     *
     * @param rs(ResultSet)
     *
     * @return ArrayList<Race>
     */
    private ArrayList<Race> createTabAnimalRace(ResultSet rs) throws ExceptionDataBase, SQLException {
        ArrayList<Race> data = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun Enclos n'existe dans la base de données");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data.add(new Race(
                        rs.getInt("id"),
                        rs.getString("nom"))
                );
            }
            return data;
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // Partie pour la gestion des INFRASTRUCTURE dans la DB



    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // Partie pour la gestion EVENEMENT dans la DB

    public ArrayList<Animal> selAnimalsByEventID(int eventID) throws SQLException, ExceptionDataBase {

        this.stmt = DBConnection.con.prepareStatement(SEL_ANIMALS_FROM_EVENT_ID);
        this.stmt.setInt(1, eventID);
        ResultSet rs = this.stmt.executeQuery();

        return creerTableauAnimal(rs);
    }

    public boolean insEventType(String eventType) throws SQLException, ExceptionDataBase {

        this.stmt = DBConnection.con.prepareStatement(INS_EVENT_TYPE);
        this.stmt.setString(1, eventType);

        int result = stmt.executeUpdate();  // Throws an exception if eventType already exists
        if (result == 0) {
            throw new ExceptionDataBase("Impossible d'ajouter le type d'événement : " + eventType);
        } else {
            return true;
        }
    }

    public boolean delEventType(String eventType) throws SQLException, ExceptionDataBase {
        this.stmt = DBConnection.con.prepareStatement(DEL_EVENT_TYPE);
        this.stmt.setString(1, eventType);
        int rowCount = this.stmt.executeUpdate();   // Retourne le nombre de lignes affectées

        return (rowCount == 0) ? false : true;
    }

    public boolean delEventByID(int eventID) throws SQLException, ExceptionDataBase {

        this.stmt = DBConnection.con.prepareStatement(DEL_EVENT_BY_ID);
        this.stmt.setInt(1, eventID);
        int rowCount = this.stmt.executeUpdate();   // Retourne le nombre de lignes affectées

        return (rowCount == 0) ? false : true;
    }

    public ArrayList<String> selEventTypes() throws SQLException, ExceptionDataBase {

        ArrayList<String> result = new ArrayList<>();
        this.stmt = DBConnection.con.prepareStatement(SEL_EVENT_TYPES);
        ResultSet rs = this.stmt.executeQuery();

        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun type d'événement dans la base de données");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                result.add(new String(rs.getString("type")));
            }
        }
        return result;
    }

    public ArrayList<Evenement> selEventsByEventType(String eventType) throws SQLException, ExceptionDataBase {

        this.stmt = DBConnection.con.prepareStatement(SEL_EVENEMENTS_PAR_TYPE);
        this.stmt.setString(1, eventType);
        ResultSet rs = this.stmt.executeQuery();

        return creerTableauEvenement(rs);
    }

    /**
     * Permet de récupérer tous les événements qui n'ont aucune personne assignée à ce dernier
     *
     * @return ArrayList<Evenement>
     */
    public ArrayList<Evenement> getAllUnassignedTaskEmployee() throws ExceptionDataBase, SQLException {

        this.stmt = DBConnection.con.prepareStatement(SEL_ALL_EVENEMENT_WHITOUT_EMPLOYEE);
        ResultSet rs = this.stmt.executeQuery();

        return creerTableauEvenement(rs);
    }

    public String SelEventTypeFromEventId(int eventID) throws ExceptionDataBase, SQLException {
        String res = null;
        this.stmt = DBConnection.con.prepareStatement(SEL_EVENT_TYPE_FROM_EVENT_ID);
        this.stmt.setInt(1, eventID);
        ResultSet rs = this.stmt.executeQuery();

        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun événement correspondant à l'ID " + eventID + " n'a été trouvé");
        } else {
            // Previous check has forwarded the pointer, just put it back at the start
            rs.beforeFirst();
            while (rs.next()) {
                res = rs.getString("type");
            }
        }
        return res;
    }

    /**
     * Permet de selectionner un évenement en fonction de son ID
     *
     * @param type         valeur en int
     *
     * @return String
     */
    public String selEventTypeFromEventName(String type) throws ExceptionDataBase, SQLException {
        String res = null;
        this.stmt = DBConnection.con.prepareStatement(SEL_EVENT_TYPE_FROM_EVENT_NAME);
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

    public ArrayList<Evenement> selEventByID(int eventID) throws ExceptionDataBase, SQLException {

        this.stmt = DBConnection.con.prepareStatement(SEL_EVENT_BY_ID);
        this.stmt.setInt(1, eventID);
        ResultSet rs = this.stmt.executeQuery();

        return creerTableauEvenement(rs);
    }

    /**
     * Permet d'insérer un type d'événement dans la DB à partir d'un objet TypeEvenement
     *
     * @param typeEvenement(TypeEvenement)
     *
     * @return void
     */
    public void insertTypeEvenement (TypeEvenement typeEvenement) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(INSERT_TYPE_EVENEMET);
        this.stmt.setString(1, typeEvenement.getType());
        this.stmt.executeQuery();
    }

    /**
     * Permet d'insérer un événement dans la DB à partir d'un objet Evenement
     *
     * @param evenement(Evenement)
     *
     * @return void
     */
    public void insertEvenement (Evenement evenement) throws SQLException {

        this.stmt = DBConnection.con.prepareStatement(INSERT_EVENEMENT);

        this.stmt.setNull(1, Types.NULL);
        this.stmt.setString(2, evenement.getDescription());
        this.stmt.setTimestamp(3, evenement.getDate());
        this.stmt.setString(4, evenement.getType());

        this.stmt.executeUpdate();
    }

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

        this.stmt = DBConnection.con.prepareStatement(INSERT_EVENEMENT);

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

        this.stmt = DBConnection.con.prepareStatement(ASSIGNER_EVENEMENT_PERSONNE);

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

                this.stmt = DBConnection.con.prepareStatement(ASSIGNER_EVENEMENT_PERSONNE);

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

            for (Personne aTabEmploye : tabEmploye) {
                this.stmt = DBConnection.con.prepareStatement(ASSIGNER_EVENEMENT_PERSONNE);

                this.stmt.setNull(1, Types.NULL);
                this.stmt.setInt(2, aTabEmploye.getIdPersonne());
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

        this.stmt = DBConnection.con.prepareStatement(ASSIGNER_EVENEMENT_ANIMAL);

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

            for (Animal aTabAnimal : tabAnimal) {
                this.stmt = DBConnection.con.prepareStatement(ASSIGNER_EVENEMENT_ANIMAL);

                this.stmt.setNull(1, Types.NULL);
                this.stmt.setInt(2, aTabAnimal.getId());
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

        this.stmt = DBConnection.con.prepareStatement(ASSIGNER_EVENEMENT_INTERVENANT);

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

            for (Intervenant aTabIntervenant : tabIntervenant) {
                this.stmt = DBConnection.con.prepareStatement(ASSIGNER_EVENEMENT_INTERVENANT);

                this.stmt.setNull(1, Types.NULL);
                this.stmt.setInt(2, aTabIntervenant.getId());
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

        this.stmt = DBConnection.con.prepareStatement(ASSIGNER_EVENEMENT_INFRASTRUCTURE);

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

            for (Infrastructure aTabInfrastructure : tabInfrastructure) {
                this.stmt = DBConnection.con.prepareStatement(ASSIGNER_EVENEMENT_INFRASTRUCTURE);

                this.stmt.setNull(1, Types.NULL);
                this.stmt.setInt(2, aTabInfrastructure.getId());
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
                        rs.getTimestamp("date"), rs.getString("type")));
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
     * Permet d'ajouter de la quantite à une ligne dans le stock
     *
     * @param id int
     * @param quantity double
     */
    public void addQuantity (int id, double quantity) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(UPDATE_ADD_QUANTITE_OF_DESCRIPTION);
        this.stmt.setInt(1, id);
        this.stmt.setDouble(2, quantity);

        this.stmt.executeUpdate();
    }

    /**
     * Permet de supprimer de la quantite à une ligne dans le stock
     *
     * @param id int
     * @param quantity double
     */
    public void delQuantity (int id, double quantity) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(UPDATE_DELETE_QUANTITE_OF_DESCRIPTION);
        this.stmt.setInt(1, id);
        this.stmt.setDouble(2, quantity);

        this.stmt.executeUpdate();
    }


    /**
     * Permet de récupérer l'entierté du stock avec tous ses paramètres
     *
     * @return ArrayList<Stock>
     */
    public ArrayList<Stock> selAllStock () throws SQLException, ExceptionDataBase {
        ArrayList<Stock> data = new ArrayList<>();
        this.stmt = DBConnection.con.prepareStatement(SEL_ALL_STOCK);

        ResultSet rs = this.stmt.executeQuery();

        return this.createTabStock(rs);
    }


    /**
     * Permet de récupérer l'entierté des Commandes avec tous ses paramètres
     *
     * @return ArrayList<Commande>
     */
    public ArrayList<Commande> selAllCommande () throws SQLException, ExceptionDataBase {
        ArrayList<Commande> data = new ArrayList<>();
        this.stmt = DBConnection.con.prepareStatement(SEL_ALL_COMMANDE);

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
        ArrayList<Commande> data = new ArrayList<>();
        this.stmt = DBConnection.con.prepareStatement(SEL_COMMANDE_BETWEEN_TWO_DATES);

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
        this.stmt = DBConnection.con.prepareStatement(SEL_CONTENU_COMMANDE_PAR_ID);
        this.stmt.setInt(1, id_commande);
        ResultSet rs = this.stmt.executeQuery();

        return this.createTabContenuCommande(rs);
    }

    public String selCommandeEnCours(int idRefArticle) throws SQLException, ExceptionDataBase {

        this.stmt = DBConnection.con.prepareStatement(SEL_ARTICLE_COMMANDE_EN_COURS);
        this.stmt.setInt(1, idRefArticle);
        ResultSet rs = this.stmt.executeQuery();
        ResultSetMetaData md = rs.getMetaData();
        String result = "";

        if (!rs.next()) {
            throw new ExceptionDataBase("Aucune commande en cours pour cette article");
        } else {
            rs.beforeFirst();
            rs.next();

            // lecture du type de notre colonne
            int columnType = md.getColumnType(1);

            if (columnType == Types.INTEGER) {
                String columnName = md.getColumnName(1);
                int value = rs.getInt(1);
                result = String.valueOf(value);
            } else if (columnType == Types.VARCHAR) {
                String columnName = md.getColumnName(1);
                String value = rs.getString(columnName);
            } else if (columnType == Types.DECIMAL) {
                double value = rs.getDouble(1);
                result = String.valueOf(value);
            }
        }

        return result;
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
        this.stmt.close();

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
                data.add(new Commande(
                                rs.getInt("id"),
                                rs.getDate("date"),
                                Statut.valueOf(rs.getString("statut"))
                        )
                );
            }
        }
        this.stmt.close();

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
        ArrayList<Contenu_Commande> data = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Aucun produit n'est contenu dans la commande avec cet ID");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data.add(new Contenu_Commande(rs.getInt("id"), rs.getString("nom"),
                        rs.getDouble("quantite"), rs.getInt("commande")));
            }
        }
        this.stmt.close();

        return data;
    }

}
