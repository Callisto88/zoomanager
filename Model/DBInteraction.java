package Model;

import jdk.nashorn.internal.runtime.ECMAException;
//import sun.jvm.hotspot.debugger.win32.coff.AuxFunctionDefinitionRecord;

import javax.jws.WebParam;
import java.sql.*;
import java.sql.Date;
import java.time.Month;
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
    // REQUETES RELATIVES AUX PAYS, VILLES, ADRESSES
    // -----------------------------------------------------------------------------------------------------------------

    // Récupére la liste de toutes les villes
    private static final String SEL_ALL_VILLE = "SELECT * FROM Ville;";

    // Récupére la liste de tous les pays
    private static final String SEL_ALL_PAYS = "SELECT * FROM Pays ORDER BY pays ASC;";

    // Récupère un pays d'après son nom
    private static final String SEL_PAYS_PAR_NOM = "SELECT * FROM Pays WHERE pays LIKE ?;";

    // Récupère une ville d'après son nom
    private static final String SEL_VILLE_PAR_NOM = "SELECT * FROM Ville WHERE ville LIKE ?;";

    // Insère un nouveau pays
    private static final String INSERT_PAYS = "INSERT INTO Pays VALUES (null , ? );";

    // Insère une nouvelle ville
    private static final String INSERT_VILLE = "INSERT INTO Ville(villeId, codePostal, ville, paysId) VALUES (null, ?, ?, ?);";

    // Insère une nouvelle adresse
    private static final String INSERT_ADRESSE = "INSERT INTO Adresse(id, adresse, villeId) VALUES (?, ?, ?);";

    // Récupère l'identifiant d'un pays d'après son nom
    private static final String SEL_PAYS_ID = "SELECT paysId FROM Pays WHERE pays LIKE ? ;";

    private static final String SEL_PAYS_FROM_ID = "SELECT * FROM Pays WHERE paysId = ?;";

    // Récupère l'identifiant d'une ville d'après son nom
    private static final String SEL_VILLEID_FROM_CITY_NAME = "SELECT villeId FROM Ville WHERE ville LIKE ?;";

    private static final String SEL_VILLE_FROM_ID = "SELECT * FROM Ville WHERE villeId = ?;";

    // Récupère le nom de la ville d'après son identifiant
    private static final String SEL_VILLE_PAR_CP = "SELECT ville FROM Ville WHERE villeId = ? ;";

    // Récupère une ville en fonction d'un code postal
    private static final String SEL_VILLE_ID_PAR_CP = "SELECT villeId FROM Ville WHERE codePostal = ? ;";

    // Récupère les infos d'une ville dans un pays donné
    private static final String SEL_CITY_IN_COUNTRY = "SELECT * FROM `Ville`\n" +
            "INNER JOIN `Pays`\n" +
            "    ON `Ville`.`paysId` = `Pays`.`paysId`\n" +
            "WHERE `ville` LIKE ?\n" +
            "AND `Pays`.`paysId` = ?;";

    // Récupère les infos d'une adresse dans une ville donnée
    private static final String SEL_ADRESSE_IN_CITY = "SELECT * FROM `Adresse` INNER JOIN `Ville` ON `Adresse`.`villeId` = `Ville`.`villeId` WHERE `Adresse`.`adresse` LIKE ? AND `Ville`.`villeId` = ?;";

    // Récupère les infos d'une adresse en fonction de l'adresse et de l'identifiant de la ville dans laquelle elle se trouve
    private static final String SEL_ADRESSE_PAR_CP_ET_ADRESSE =
            "SELECT * FROM Adresse WHERE adresse LIKE ? AND villeId = ? ;";

    private static final String SEL_ADRESSE_FROM_ID = "SELECT * FROM Adresse WHERE id = ?;";


    // -----------------------------------------------------------------------------------------------------------------
    // REQUETES RELATIVES AUX PERSONNES, EMPLOYES, INTERVENANTS
    // -----------------------------------------------------------------------------------------------------------------

    private static final String NOMBRE_PERSONNE = "SELECT COUNT(*) as nbPersonne FROM Personne;";
    private static final String SEL_ALL_EMPLOYES = "SELECT * FROM Personne;";
    private static final String SEL_EMPLOYE_DETAILS = "SELECT * FROM Personne WHERE idPersonne = ? ;";
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
    // idResponsable / statut / dateDebut	TypeContrat /
    private static final String INSERT_EMPLOYE = "INSERT INTO Personne(idPersonne, noAVS, prenom, nom, adresse, email, telephone, dateNaissance, responsable, statut, dateDebut, typeContrat) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
    // Recupère tous les paramètre d'une personne
    // 12 Paramètres
    private static final String SEL_ALL_PERSONNE = "SELECT * " +
            "FROM Personne;";

    // Permet de modifier les informations relatives à une Personne
    // Expected : noAVS, prenom, nom, adresse, email, telephone, dateNaissance, responsable, statut, dateDebut, typeContrat
    private static final String UPDATE_PERSONNE = "UPDATE Personne SET noAVS = ?, prenom = ?, nom = ?, adresse = ?, email = ?, telephone = ?, dateNaissance = ?, responsable = ?, statut = ?, dateDebut = ?, typeContrat = ? WHERE idPersonne = ?;";

    private static final String DEL_PERSONNE = "DELETE FROM Personne WHERE idPersonne = ?;";
    private static final String SEL_TYPE_CONTRAT = "SELECT DISTINCT typeContrat FROM Personne";
    private static final String SEL_ALL_STATUTS = "SELECT DISTINCT statut FROM Personne";
    // -----------------------------------------------------------------------------------------------------------------
    // INTERVENANT :
    // Selectionne tous les intervenants
    private static final String SELECT_INTERVENANT = "SELECT * FROM Intervenant;";
    // Supprime un intervenant de la DB, pour garder les "traces" on passe uniquement son "statut" à 1
    private static final String DELETE_INTERVENANT = "UPDATE Intervenant " +
            "SET statut = 1 " +
            "WHERE id = ? ;";
    // Modifie les données d'un intervenant externe
    private static final String UPDATE_INTERVENANT =
            "UPDATE Intervenant " +
                    "SET entreprise = ? , " +
                    "  prenom = ? , " +
                    "  nom = ? , " +
                    "  adresse = ? , " +
                    "  email = ? , " +
                    "  telephone = ? , " +
                    "  statut = ? " +
                    "WHERE id = ? ;";
    // Insertion d'un nouvel intervenant
    // Expected :: id, entreprise, prenom, nom, adresse, email, telephone, statut
    private static final String INSERT_INTERVANT = "INSERT INTO Intervenant VALUES (null, ?, ?, ?, ?, ?, ?, ?);";
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
            "(SELECT DISTINCT evenement FROM Personne_Evenement);";


    // Liste de tous les événements qui n'ont pas d'intervenant attribué
    private static final String SEL_ALL_EVENEMENT_WHITOUT_INTERVENANT =
            "SELECT * " +
                    "FROM Evenement " +
                    "WHERE id NOT IN " +
                    "(SELECT DISTINCT evenement " +
                    "FROM Intervenant_Evenement);";

    private static final String INSERT_TYPE_EVENEMET = " INSERT INTO TypeEvenement VALUES (?) ";
    // Insertion d'un événement dans la DB
    private static final String INSERT_EVENEMENT  = "INSERT INTO Evenement VALUES (null, ?, ?, ?);";
    // Assigner un événement à un personne
    private static final String ASSIGNER_EVENEMENT_PERSONNE = "INSERT INTO Personne_Evenement VALUES (null , ? , ? );";
    // Assigner un événement à un animal
    private static final String ASSIGNER_EVENEMENT_ANIMAL = "INSERT INTO Animal_Evenement VALUES (null , ? , ? );";
    // Assigner un événement à un animal
    private static final String ASSIGNER_EVENEMENT_INTERVENANT = "INSERT INTO Intervenant_Evenement VALUES (null, ? , ? );";
    // Assigner un événement à un animal
    private static final String ASSIGNER_EVENEMENT_INFRASTRUCTURE = "INSERT INTO Infrastructure_Evenement VALUES (null , ? , ? );";

    // Selectionner le nom du type d'un événement en fonction de son ID
    private static final String SEL_EVENT_BY_ID = "SELECT * FROM Evenement WHERE id = ?;";
    private static final String DEL_EVENT_BY_ID = "DELETE FROM Evenement WHERE id = ?;";

    private static final String DEL_EVENT_TYPE = "DELETE FROM Evenement_Type WHERE type LIKE ?;";
    private static final String INS_EVENT_TYPE = "INSERT INTO Evenement_Type VALUES (?);";
    private static final String SEL_EVENT_TYPES = "SELECT DISTINCT(type) FROM Evenement_Type;";
    private static final String SEL_EVENT_TYPE_FROM_EVENT_ID = "SELECT type FROM Evenement WHERE id = ? ;";
    private static final String SEL_EVENT_TYPE_FROM_EVENT_NAME = "SELECT type FROM Evenement WHERE description LIKE ? ;";

    private static final String SEL_ANIMALS_FROM_EVENT_ID = "SELECT * FROM Animal INNER JOIN Animal_Evenement ON Animal.id = Animal_Evenement.animal WHERE Animal_Evenement.evenement = ?;";
    private static final String ADD_ANIMAL_TO_EVENT = "INSERT INTO Animal_Evenement VALUES(?,?,?);";
    private static final String DEL_ANIMAL_FROM_EVENT = "DELETE FROM Animal_Evenement WHERE animal = ? AND evenement = ?;";
    private static final String SEL_EVENT_ANIMAL_FROM_ANIMAL_AND_EVENT = "SELECT * FROM Animal_Evenement WHERE Animal_Evenement.animal = ? AND Animal_Evenement.evenement = ?";
    private static final String SEL_PERSONNE_CONCERNED_IN_EVENT = "SELECT * FROM Personne INNER JOIN Personne_Evenement ON Personne_Evenement.personne = Personne.idPersonne WHERE Personne_Evenement.evenement = ?;";
    private static final String DEL_PERSONNE_IN_EVENT = "DELETE FROM Personne_Evenement WHERE evenement = ? AND personne = ?;";

    private static final String SEL_EVENTS = "SELECT * FROM Evenement;";

    // Selectionne les événements auxquels participe un intervenant externe. Intervenant externe par ID
    private static final String SEL_ASSIGN_EVENEMENT_INTERVENANT =
            "SELECT evenement " +
                    "FROM Intervenant_Evenement " +
                    "WHERE intervenant = ? ;";
    // Selectionne les événements auxquels participe une personne interne. Personne interne par ID
    private static final String SEL_ASSIGN_EVENEMENT_PERSON =
            "SELECT evenement " +
                    "FROM Personne_Evenement " +
                    "WHERE personne = ? ;";


    // En cours
    private static final String SEL_PERSONNE_CONCERNED_BY_EVENT = "SELECT * FROM Personne_Evenement WHERE evenement = ?";

    private static final String SEL_RESPONSABLES = "SELECT * FROM Personne WHERE statut LIKE 'responsable'";

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
    private static final String INS_COMMANDE = "INSERT INTO Commande (id, dateHeure, statut) VALUES(null, NOW(), 'CREEE');";
    private static final String INS_CONTENU_COMMANDE = "INSERT INTO Commande_Contenu (id, idCommande, quantite, refArticle) VALUES(null, ?, ?, ?);";
    private static final String SEL_STOCK_BY_REF = "SELECT * FROM Stock WHERE id = ?;";

    // Récupérer l'état de tout le stock (nom, quantite, unite, quantiteMin
    private static final String SEL_ALL_STOCK = "SELECT * FROM Stock;";

    // Récupérer toutes les commandes qui ont été faites (id, date et statut)
    private static final String SEL_ALL_COMMANDE = "SELECT * FROM Commande";
    private static final String SEL_ORDERS_BY_STATUS = "SELECT * FROM Commande WHERE Statut LIKE ?";

    private static final String SEL_ORDER = "SELECT * FROM Commande WHERE id = ?";

    private static final String UPDATE_ORDER = "UPDATE Commande SET statut = ? WHERE id = ?;";

    // Récupérer l'ID et la date de toute les commandes faites entre deux dates Date1 et Date2
    private static final String SEL_COMMANDE_BETWEEN_TWO_DATES = "SELECT * FROM Commande WHERE `dateHeure` BETWEEN ? AND ? ;";

    // Récupérer le contenu d'une commande en fonction de son ID
    private static final String SEL_CONTENU_COMMANDE_PAR_ID = "SELECT * FROM Commande_Contenu WHERE idCommande = ? ;";

    // Pour un article donné, récupère la quantité en cours de commande (si commandé)
    private static final String SEL_ARTICLE_COMMANDE_EN_COURS = "SELECT SUM(quantite) AS quantiteEnCours \n" +
            "FROM Commande_Contenu \n" +
            "INNER JOIN Commande \n" +
            "\tON Commande_Contenu.idCommande = Commande.id \n" +
            "WHERE refArticle = ?\n" +
            "AND Commande.statut = \"EN_COURS\";";

    private static final String SEL_ORDERS_BY_STATE_AND_DATE = "SELECT * FROM Commande WHERE `statut` LIKE ? AND `dateHeure` BETWEEN ? AND ?;";

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
    // Partie pour la gestion des INTERVENANT dans la DB

    /**
     * Insere un intervenant dans la DB
     *
     * @param intervenant(Intervenant)
     *
     */
    public void insertIntervenant (Intervenant intervenant) throws SQLException, ExceptionDataBase {

        // Expected :: id, entreprise, prenom, nom, adresse, email, telephone, statut
        this.stmt = DBConnection.con.prepareStatement(INSERT_INTERVANT);
        this.stmt.setString(1, intervenant.getEntreprise());
        this.stmt.setString(2, intervenant.getPrenom());
        this.stmt.setString(3, intervenant.getNom());
        this.stmt.setInt(4, intervenant.getAdresse().getId());
        this.stmt.setString(5, intervenant.getEmail());
        this.stmt.setString(6, intervenant.getTelephone());
        this.stmt.setInt(7, intervenant.getStatut());
        int row = this.stmt.executeUpdate();
        System.out.println(row);
    }

    /**
     * Selectionne tous les intervenant de la DB
     *
     * @return ArrayList<Intervenant>
     *
     */
    public ArrayList<Intervenant> selIntervenant () throws SQLException, ExceptionDataBase {
        ArrayList<Personne> listEmployes;
        this.stmt = DBConnection.con.prepareStatement(SELECT_INTERVENANT);
        ResultSet rs = this.stmt.executeQuery();

        return this.createTabIntervenant(rs);
    }

    /**
     * Permet de "supprimer" un intervenant de la DB
     * En faite, son statut passe simplement à 1
     *
     * @param id(int)
     *
     */
    public void delIntervenant (int id) throws SQLException, ExceptionDataBase {
        this.stmt = DBConnection.con.prepareStatement(DELETE_INTERVENANT);
        this.stmt.setInt(1, id);
        this.stmt.executeUpdate();

    }

    /**
     * Permet de "supprimer" un intervenant de la DB
     * En faite, son statut passe simplement à 1
     *
     * @param intervenant(Intervenant)
     *
     */
    public void delIntervenant (Intervenant intervenant) throws SQLException, ExceptionDataBase {
        this.stmt = DBConnection.con.prepareStatement(DELETE_INTERVENANT);
        this.stmt.setInt(1, intervenant.getId());
        this.stmt.executeUpdate();

    }

    /**
     * Permet de modifier un intervenant dans la DB
     *
     * @param intervenant(Intervenant)
     *
     */
    public void updateIntervenant (Intervenant intervenant) throws SQLException, ExceptionDataBase {
        this.stmt = DBConnection.con.prepareStatement(UPDATE_INTERVENANT);
        this.stmt.setString(1, intervenant.getEntreprise());
        this.stmt.setString(2, intervenant.getPrenom());
        this.stmt.setString(3, intervenant.getNom());
        this.stmt.setInt(4, intervenant.getAdresse().getId());
        this.stmt.setString(5, intervenant.getEmail());
        this.stmt.setString(6, intervenant.getTelephone());
        this.stmt.setInt(7, intervenant.getStatut());
        this.stmt.setInt(8, intervenant.getId());
        this.stmt.executeUpdate();

    }

    /**
     * Permet de créer une ArrayList d'intervenant à partir de Resultset passé en paramètre
     *
     * @param rs(ResultSet)
     *
     * @return ArrayList<Intervenant>
     */
    private ArrayList<Intervenant> createTabIntervenant (ResultSet rs) throws ExceptionDataBase, SQLException {
        ArrayList<Intervenant> data = new ArrayList<Intervenant>();
        if (!rs.next()) {
            throw new ExceptionDataBase(10);
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                Intervenant i = new Intervenant(
                        rs.getInt("id"),
                        rs.getString("entreprise"),
                        rs.getString("prenom"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getInt("statut")
                );

                Adresse a = null;
                int adresseID = rs.getInt("adresse");
                if (adresseID > 0) {
                    a = this.selAdresseFromID(adresseID);
                    if (a != null) {
                        i.setAdresse(a);
                    }
                }
                data.add(i);
            }
        }
        return data;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // Partie pour la gestion des ADRESSES dans la DB

    private Adresse selAdresseFromID(int addressID) throws SQLException {

        this.stmt = DBConnection.con.prepareStatement(SEL_ADRESSE_FROM_ID);
        this.stmt.setInt(1, addressID);
        ResultSet rs = this.stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        Adresse a = new Adresse();
        a.setId(rs.getInt("id"));
        a.setAdresse(rs.getString("adresse"));

        Ville v = this.selVilleFromID(rs.getInt("villeId"));
        a.setVille(v);

        return a;
    }

    private Ville selVilleFromID(int villeID) throws SQLException {

        if (villeID == 0) {
            return null;
        }

        // DB
        this.stmt = DBConnection.con.prepareStatement(SEL_VILLE_FROM_ID);
        this.stmt.setInt(1, villeID);
        ResultSet rs = this.stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }

        /**
         * Objet
         */

        // City
        Ville v = new Ville(rs.getInt("villeId"));
        v.setVille(rs.getString("ville"));
        v.setCp(rs.getInt("codePostal"));

        // Country
        Pays p = this.selCountryFromID(rs.getInt("paysId"));
        v.setPays(p);

        return v;
    }

    private Pays selCountryFromID(int countryID) throws SQLException {

        if (countryID == 0) {
            return null;
        }

        // DB
        this.stmt = DBConnection.con.prepareStatement(SEL_PAYS_FROM_ID);
        this.stmt.setInt(1, countryID);
        ResultSet rs = this.stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }

        // Objet
        Pays p = new Pays();
        p.setPaysId(rs.getInt("paysId"));
        p.setPays(rs.getString("pays"));

        return p;
    }

    private ArrayList<Pays> creerTableauPays(ResultSet rs) throws ExceptionDataBase, SQLException {
        ArrayList<Pays> data = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase(24);
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data.add(new Pays(rs.getInt(1), rs.getString(2)));
            }
        }
        return data;
    }

    public ArrayList<Pays> selCountries() throws SQLException, ExceptionDataBase {

        this.stmt = DBConnection.con.prepareStatement(SEL_ALL_PAYS);
        ResultSet rs = this.stmt.executeQuery();

        return creerTableauPays(rs);
    }

    /**
     * Permet d'insérer une nouvelle adresse complète (Adresse, Ville , Pays)
     *
     * @param adresse String
     * @param ville String
     * @param pays Pays
     */
    public int insAddress(Adresse adresse, Ville ville, Pays pays) throws SQLException, ExceptionDataBase {
        // Pays
        int paysID;
        if (!this.countryIsInDB(pays)) {
            paysID = this.insPays(pays);
        } else {
            paysID = getPaysID(pays.getPays());
        }

        pays.setPaysId(paysID);
        ville.setPays(pays);

        // Ville
        int villeID = this.villeExists(ville, pays);
        if (villeID == 0) {
            Ville v = new Ville(ville.getCp(), ville.getVille(), pays);
            villeID = this.insVille(v);
        }

        ville.setId(villeID);
        adresse.setVille(ville);

        // Adresse
        int addressID = this.adresseExists(adresse, ville);
        if (addressID == 0) {
            Adresse a = new Adresse(adresse.getAdresse(), ville);
            addressID = this.insAdresse(a);
        }
        adresse.setId(addressID);

        System.out.println("==============VILLE INFO==============");
        System.out.println(ville.getId());
        System.out.println(ville.getCp());
        System.out.println(ville.getPays().getPays());
        System.out.println(ville.getVille());

        System.out.println("==============ADRESSE INFO==============");
        System.out.println(adresse.getId());
        System.out.println(adresse.getAdresse());
        System.out.println(adresse.getVille().getVille());
        System.out.println(addressID);

        return addressID;
    }

    private int getVilleIDFromVilleName(String villeName) throws SQLException {

        this.stmt = DBConnection.con.prepareStatement(SEL_VILLEID_FROM_CITY_NAME);
        this.stmt.setString(1, villeName);
        ResultSet rs = this.stmt.executeQuery();

        if (rs.next()) {
            // rs.beforeFirst();
            return rs.getInt("villeId");
        } else {
            return 0;
        }
    }

    private int adresseExists(Adresse adresse, Ville ville) throws SQLException {

        System.out.println("Check if address exists");
        System.out.println(adresse.getAdresse());
        System.out.println(ville.getId());

        this.stmt = DBConnection.con.prepareStatement(SEL_ADRESSE_IN_CITY);
        this.stmt.setString(1, adresse.getAdresse());
        this.stmt.setInt(2, ville.getId());
        ResultSet rs = this.stmt.executeQuery();
        int adresseID = 0;

        if (rs.next()) {
            adresseID = rs.getInt("id");
        }
        System.out.println("L'adresse : " + adresse.getAdresse() + " " + ((adresseID > 0) ? "existe à " + ville.getVille() + "" : "n'existe pas à " + ville.getVille() + ""));

        return adresseID;
    }

    private int villeExists(Ville ville, Pays pays) throws SQLException {

        this.stmt = DBConnection.con.prepareStatement(SEL_CITY_IN_COUNTRY);
        this.stmt.setString(1, ville.getVille());
        this.stmt.setInt(2, pays.getPaysId());
        ResultSet rs = this.stmt.executeQuery();
        int villeID = 0;

        if (rs.next()) {
            villeID = rs.getInt("villeId");
        }
        System.out.println("La ville '" + ville.getVille() + "' " + ((villeID > 0) ? "existe en " + pays.getPays() : "n'existe pas en " + pays.getPays()));

        return villeID;
    }

    /**
     * Permet d'insérer une nouvelle adresse dans la base de données
     *
     * @param adresse Adresse
     */
    private int insAdresse(Adresse adresse) throws SQLException {

        this.stmt = DBConnection.con.prepareStatement(INSERT_ADRESSE, Statement.RETURN_GENERATED_KEYS);
        this.stmt.setNull(1, Types.NULL);
        this.stmt.setString(2, adresse.getAdresse());
        this.stmt.setInt(3, adresse.getVille().getId());
        this.stmt.executeUpdate();
        ResultSet rs = this.stmt.getGeneratedKeys();

        if (rs.next()) {        // On récupère l'ID de la nouvelle adresse
            // rs.beforeFirst();   // On remet le curseur au début
            return rs.getInt(1);
        } else {
            return 0;
        }
    }


    /**
     * Permet d'insérer un nouveau Pays dans la base de données
     *
     * @param pays String
     */
    private int insPays(Pays pays) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(INSERT_PAYS, Statement.RETURN_GENERATED_KEYS);
        this.stmt.setString(1, pays.getPays());
        ResultSet rs = this.stmt.getGeneratedKeys();

        if (rs.next()) {        // On récupère l'ID de la nouvelle adresse
            // rs.beforeFirst();   // On remet le curseur au début
            return rs.getInt(1);
        } else {
            return 0;
        }
    }

    /**
     * Permet d'insérer une nouvelle Ville dans la base de données
     *
     * @param ville String
     */
    private int insVille(Ville ville) throws SQLException, ExceptionDataBase {

        // Basic checks
        if (ville.getCp() == 0) {
            throw new ExceptionDataBase(4);

            //if ( ! (ville.getCp() == (int) ville.getCp() ) ) {
            //
            //}
        }

        this.stmt = DBConnection.con.prepareStatement(INSERT_VILLE, Statement.RETURN_GENERATED_KEYS);
        this.stmt.setInt(1, ville.getCp());
        this.stmt.setString(2, ville.getVille());
        this.stmt.setInt(3, ville.getPays().getPaysId());   // paysId as foreign key
        this.stmt.executeUpdate();
        ResultSet rs = this.stmt.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
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

    /*public Ville getVilleFromVilleID(int villeId) throws SQLException {

        this.stmt = DBConnection.con.prepareStatement(SEL_VILLE_FROM_VILLE_ID);
        this.stmt.setInt(1, villeId);
        ResultSet rs = this.stmt.executeQuery();

        if (rs.next()) {
            rs.beforeFirst();
            Ville result = new Ville(
                    rs.getInt("villeId"),
                    rs.getInt("codePostal"),
                    rs.getString("ville")
            );

            getPaysID()
        }
    }*/

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
    public int getVilleIDParCP(int cp) throws SQLException {

        this.stmt = DBConnection.con.prepareStatement(SEL_VILLE_ID_PAR_CP);
        this.stmt.setInt(1, cp);
        ResultSet rs = this.stmt.executeQuery();

        if (rs.next()) {
            rs.beforeFirst();
            return rs.getInt("villeId");
        } else {
            return 0;
        }
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

        boolean exists = rs.next();
        System.out.println("Le pays : " + pays.getPays() + " " + (exists ? "existe" : "n'existe pas"));

        return exists;
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

    public ArrayList<Personne> selResponsables() throws SQLException, ExceptionDataBase {

        this.stmt = DBConnection.con.prepareStatement(SEL_RESPONSABLES);
        ResultSet rs = this.stmt.executeQuery();
        return creerTableauPersonne(rs);
    }

    /**
     * Permet de modifier les informations d'un employé
     *
     * // Expected : noAVS, prenom, nom, adresse, email, telephone, dateNaissance, responsable, statut, dateDebut, typeContrat, personneID
     *
     * @return Personne
     */
    public void updatePersonne (Personne personne) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(UPDATE_PERSONNE);

        this.stmt.setString(1, personne.getNoAVS());
        this.stmt.setString(2, personne.getPrenom());
        this.stmt.setString(3, personne.getNom());
        this.stmt.setInt(4, personne.getAdresse().getId());
        this.stmt.setString(5, personne.getEmail());
        this.stmt.setString(6, personne.getTelephone());
        this.stmt.setDate(7, personne.getDateNaissance());
        this.stmt.setInt(8, personne.getResponsable());
        this.stmt.setString(9, personne.getStatut());
        this.stmt.setDate(10, personne.getDateDebut());
        this.stmt.setString(11, personne.getStatut());
        this.stmt.setInt(12, personne.getIdPersonne());

        this.stmt.executeUpdate();
    }

    public ArrayList<String> getAllStatuts() throws SQLException, ExceptionDataBase {

        //noinspection AccessStaticViaInstance
        this.stmt = DBConnection.con.prepareStatement(SEL_ALL_STATUTS);
        ResultSet rs = this.stmt.executeQuery();

        ArrayList<String> listStatuts = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase(30);
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                listStatuts.add(rs.getString("statut"));
            }
        }
        return listStatuts;
    }

    public ArrayList<String> selAllContractType() throws SQLException, ExceptionDataBase {

        this.stmt = DBConnection.con.prepareStatement(SEL_TYPE_CONTRAT);
        ResultSet rs = this.stmt.executeQuery();

        ArrayList<String> listTypeContrat = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase(31);
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
     * Retourne tous les détails d'une personne en fonction de son ID
     *
     * @param idPersonne         valeur en int
     *
     * @return Personne
     */
    public Personne selEmployeDetails(int idPersonne) throws ExceptionDataBase, SQLException {
        Personne p = new Personne();
        this.stmt = DBConnection.con.prepareStatement(SEL_EMPLOYE_DETAILS);
        this.stmt.setInt(1, idPersonne);
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
     * @param personne(Personne)
     */
    public void insertPersonne(Personne personne) throws SQLException {

        Personne.afficherPersonne(personne);

        // Expected : idPersonne, noAVS, prenom, nom, adresse, email, telephone, dateNaissance, responsable, statut, dateDebut, typeContrat
        this.stmt = DBConnection.con.prepareStatement(INSERT_EMPLOYE);
        this.stmt.setString(1, personne.getNoAVS());
        this.stmt.setString(2, personne.getPrenom());
        this.stmt.setString(3, personne.getNom());
        this.stmt.setInt(4, personne.getAdresse().getId());
        this.stmt.setString(5, personne.getEmail());
        this.stmt.setString(6, personne.getTelephone());
        this.stmt.setDate(7, personne.getDateNaissance());
        this.stmt.setInt(8, personne.getResponsable());
        this.stmt.setString(9, personne.getStatut());
        this.stmt.setDate(10, personne.getDateDebut());
        this.stmt.setString(10, personne.getTypeContrat());
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
            throw new ExceptionDataBase(11);
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                Personne p = new Personne(
                        rs.getInt("idPersonne"),
                        rs.getString("noAVS"),
                        rs.getString("prenom"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("telephone"), rs.getDate("dateNaissance"),
                        rs.getInt("responsable"), rs.getString("statut"),
                        rs.getDate("dateDebut"), rs.getString("typeContrat")
                );
                Adresse a = null;
                int adresseID = rs.getInt("adresse");
                if (adresseID > 0) {
                    a = this.selAdresseFromID(adresseID);
                    if (a != null) {
                        p.setAdresse(a);
                    }
                }
                data.add(p);
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
            throw new ExceptionDataBase(2);
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
            throw new ExceptionDataBase(12);
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
            throw new ExceptionDataBase(13);
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

    public boolean delAnimal(Animal a) throws ExceptionDataBase, SQLException {

        int numericId = a.getId();
        if (numericId != (int) numericId || !animalExists(numericId)) {
            throw new ExceptionDataBase(20, numericId);
        }

        this.stmt = DBConnection.con.prepareStatement(DELETE_ANIMAL);
        this.stmt.setInt(1, a.getId());
        int result = this.stmt.executeUpdate();
        if (result > 0) {
            this.stmt = null;
            if (a instanceof Felin) {
                this.stmt = DBConnection.con.prepareStatement(DELETE_FAUVE);
                this.stmt.setInt(1, a.getId());
                result += this.stmt.executeUpdate();
            } else if (a instanceof Oiseau) {
                this.stmt = DBConnection.con.prepareStatement(DELETE_OISEAU);
                this.stmt.setInt(1, a.getId());
                result += this.stmt.executeUpdate();
            } else if (a instanceof Primate) {
                this.stmt = DBConnection.con.prepareStatement(DELETE_PRIMATE);
                this.stmt.setInt(1, a.getId());
                result += this.stmt.executeUpdate();
            } else if (a instanceof Reptile) {
                this.stmt = DBConnection.con.prepareStatement(DELETE_REPTILE);
                this.stmt.setInt(1, a.getId());
                result += this.stmt.executeUpdate();
            }
        }

        return (result > 0);
    }

    private boolean animalExists(int idAnimal) throws SQLException {

        this.stmt = DBConnection.con.prepareStatement(SEL_ANIMAL_ID);
        this.stmt.setInt(1, idAnimal);
        ResultSet rs = this.stmt.executeQuery();

        return rs.next();
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
            throw new ExceptionDataBase(13);
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
            throw new ExceptionDataBase(13);
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
            throw new ExceptionDataBase(14);
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
            throw new ExceptionDataBase(15);
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
            throw new ExceptionDataBase(16);
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
            throw new ExceptionDataBase(17);
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
            throw new ExceptionDataBase(18);
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

    public ArrayList<Evenement> selAllEvents() throws SQLException, ExceptionDataBase {
        this.stmt = DBConnection.con.prepareStatement(SEL_EVENTS);
        ResultSet rs = this.stmt.executeQuery();
        return creerTableauEvenement(rs);
    }

    public ArrayList<Personne> selPeopleByEventID(int eventID) throws SQLException, ExceptionDataBase {

        this.stmt = DBConnection.con.prepareStatement(SEL_PERSONNE_CONCERNED_IN_EVENT);
        this.stmt.setInt(1, eventID);
        ResultSet rs = this.stmt.executeQuery();

        return creerTableauPersonne(rs);
    }

    public int insAnimalEvent(int idAnimal, int idEvenement) throws SQLException {

        this.stmt = DBConnection.con.prepareStatement(ADD_ANIMAL_TO_EVENT, Statement.RETURN_GENERATED_KEYS);
        this.stmt.setInt(1, idAnimal);
        this.stmt.setInt(2, idEvenement);

        // En premier lieu, on enregistre l'animal dans la DB
        this.stmt.execute();
        ResultSet rs = this.stmt.getGeneratedKeys();
        if (rs.next()) {    // On récupère l'ID de l'événement animal
            rs.beforeFirst();   // On remet le curseur au début
            return rs.getInt(1);
        } else {
            return 0;
        }
    }

    public boolean delAnimalEvent(int idAnimal, int idEvenement) throws SQLException {

        this.stmt = DBConnection.con.prepareStatement(DEL_ANIMAL_FROM_EVENT);
        this.stmt.setInt(1, idAnimal);
        this.stmt.setInt(2, idEvenement);

        int rowCount = this.stmt.executeUpdate();   // Retourne le nombre de lignes affectées

        return (rowCount == 0) ? false : true;
    }

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
            throw new ExceptionDataBase(26, eventType);
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
            throw new ExceptionDataBase(19);
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
     * Permet de récupérer tous les événements qui n'ont aucune personne assignée
     *
     * @return ArrayList<Evenement>
     */
    public ArrayList<Evenement> getAllUnassignedTaskEmployee() throws ExceptionDataBase, SQLException {

        this.stmt = DBConnection.con.prepareStatement(SEL_ALL_EVENEMENT_WHITOUT_EMPLOYEE);
        ResultSet rs = this.stmt.executeQuery();

        return creerTableauEvenement(rs);
    }

    /**
     * Permet de récupérer tous les événements qui n'ont aucun INTERVENANT assignée
     *
     * @return ArrayList<Evenement>
     */
    public ArrayList<Evenement> getAllUnassignedTaskIntervenant() throws ExceptionDataBase, SQLException {

        this.stmt = DBConnection.con.prepareStatement(SEL_ALL_EVENEMENT_WHITOUT_INTERVENANT);
        ResultSet rs = this.stmt.executeQuery();

        return creerTableauEvenement(rs);
    }


    public String SelEventTypeFromEventId(int eventID) throws ExceptionDataBase, SQLException {
        String res = null;
        this.stmt = DBConnection.con.prepareStatement(SEL_EVENT_TYPE_FROM_EVENT_ID);
        this.stmt.setInt(1, eventID);
        ResultSet rs = this.stmt.executeQuery();

        if (!rs.next()) {
            throw new ExceptionDataBase( 27, eventID);
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
            throw new ExceptionDataBase(28, type);
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

        this.stmt.setString(1, evenement.getDescription());
        this.stmt.setTimestamp(2, evenement.getDate());
        this.stmt.setString(3, evenement.getType());

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

        this.stmt.setString(1, description);
        this.stmt.setDate(2, date);
        this.stmt.setInt(3, type);

        this.stmt.executeUpdate();
    }

    /*public ArrayList<Intervenant> selPeopleConcernedByEventID(int eventID) throws SQLException {

        ArrayList<Intervenant> result;
        this.stmt = DBConnection.con.prepareStatement(SEL_PERSONNE_CONCERNED_BY_EVENT);
        this.stmt.setInt(1, eventID);
        ResultSet rs = this.stmt.executeQuery();

        while (rs.next()) {
            result.add(new Intervenant())
        }
    }*/

    public boolean delPersonneEvenement(int personneID, int eventID) throws SQLException {

        this.stmt = DBConnection.con.prepareStatement(DEL_PERSONNE_IN_EVENT);
        this.stmt.setInt(1, eventID);
        this.stmt.setInt(2, personneID);
        ResultSet rs = this.stmt.executeQuery();

        return rs.next();
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
        int personneID = employe.getIdPersonne();
        int eventID = evenement.getId();

        this.stmt = DBConnection.con.prepareStatement(ASSIGNER_EVENEMENT_PERSONNE);

        this.stmt.setInt(1, personneID);
        this.stmt.setInt(2, eventID);

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

                this.stmt.setInt(1, numAVS_employe);
                this.stmt.setInt(2, evenements.get(i).getId());

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

                this.stmt.setInt(1, aTabEmploye.getIdPersonne());
                this.stmt.setInt(2, id_evenement);

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

        this.stmt.setInt(1, id_animal);
        this.stmt.setInt(2, id_evenement);

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

                this.stmt.setInt(1, aTabAnimal.getId());
                this.stmt.setInt(2, id_evenement);

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

        this.stmt.setInt(1, id_intervenant);
        this.stmt.setInt(2, id_evenement);

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

                this.stmt.setInt(1, aTabIntervenant.getId());
                this.stmt.setInt(2, id_evenement);

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

        this.stmt.setInt(1, id_infrastructure);
        this.stmt.setInt(2, id_evenement);
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

                this.stmt.setInt(1, aTabInfrastructure.getId());
                this.stmt.setInt(2, id_evenement);

                this.stmt.executeUpdate();
            }
        }
    }

    /**
     * Permet d'assigner un événement à une personne
     *
     * @param evenement(Evenement)
     * @param intervenant(Intervenant)
     *
     * @return void
     */
    public void assignEvenementEmploye (Evenement evenement, Intervenant intervenant) throws SQLException {
        int id_intervenant = intervenant.getId();
        int id_evenement = evenement.getId();

        this.stmt = DBConnection.con.prepareStatement(ASSIGNER_EVENEMENT_PERSONNE);

        this.stmt.setInt(1, id_intervenant);
        this.stmt.setInt(2, id_evenement);

        this.stmt.executeUpdate();

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
            throw new ExceptionDataBase(19);
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

    /**
     * Permet de récupérer les événements assignés à un intervenant externe par son ID
     *
     * @param id_intervenant(int)
     *
     * @return ArrayList<Evenement>
     */
    public ArrayList<Evenement> getEvenementAssignToIntervenantByID (int id_intervenant) throws SQLException, ExceptionDataBase {
        // Faire la requête
        this.stmt = DBConnection.con.prepareStatement(SEL_ASSIGN_EVENEMENT_INTERVENANT);
        this.stmt.setInt(1, id_intervenant);
        ResultSet rs = this.stmt.executeQuery();

        ArrayList<Integer> dataID = new ArrayList<Integer>();
        // Récupérer tous les id d'évenement dans un tableau
        if (!rs.next()) {
            throw new ExceptionDataBase(32, id_intervenant);
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                dataID.add(new Integer(rs.getInt("evenement")));
            }
        }

        return createTabEvenemnt(dataID);
    }


    /**
     * Permet de récupérer les événements assignés à personne interne par son ID
     *
     * @param idPersonne(int)
     *
     * @return ArrayList<Evenement>
     */
    public ArrayList<Evenement> getEvenementAssignToPersonByID (int idPersonne) throws SQLException, ExceptionDataBase {
        // Faire la requête
        this.stmt = DBConnection.con.prepareStatement(SEL_ASSIGN_EVENEMENT_PERSON);
        this.stmt.setInt(1, idPersonne);
        ResultSet rs = this.stmt.executeQuery();

        ArrayList<Integer> dataID = new ArrayList<Integer>();
        // Récupérer tous les id d'évenement dans un tableau
        if (!rs.next()) {
            throw new ExceptionDataBase(33, idPersonne);
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                dataID.add(new Integer(rs.getInt("evenement")));
            }
        }
        return createTabEvenemnt(dataID);
    }

    /**
     * Permet de créer un tableau d'EVENEMENT à partir d'un tableau d'événement_id
     *
     * @param idEvenement(ArrayList<Integer>)
     *
     * @return ArrayList<Evenement>
     */
    private ArrayList<Evenement> createTabEvenemnt (ArrayList<Integer> idEvenement) throws SQLException {
        ArrayList<Evenement> dataEvenement = new ArrayList<Evenement>();
        int id_evenement;
        this.stmt = DBConnection.con.prepareStatement(SEL_EVENT_BY_ID);

        for (int i = 0; i < idEvenement.size(); i++) {
            id_evenement = idEvenement.get(0);
            this.stmt.setInt(1, id_evenement);
            ResultSet rs2 = this.stmt.executeQuery();

            while (rs2.next()) {
                dataEvenement.add(new Evenement(rs2.getInt("id"), rs2.getString("description"),
                        rs2.getTimestamp("date"), rs2.getString("type")));
            }
        }

        return dataEvenement;
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
        this.stmt.setInt(2, id);
        this.stmt.setDouble(1, quantity);

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
        this.stmt.setInt(2, id);
        this.stmt.setDouble(1, quantity);

        this.stmt.executeUpdate();
    }

    /**
     * Permet de récupérer l'entierté du stock avec tous ses paramètres
     *
     * @return ArrayList<Stock>
     */
    public ArrayList<Stock> selAllStock () throws SQLException, ExceptionDataBase {
        ArrayList<Stock> data = new ArrayList<Stock>();
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
        ArrayList<Commande> data = new ArrayList<Commande>();
        this.stmt = db.con.prepareStatement(SEL_ALL_COMMANDE);

        ResultSet rs = this.stmt.executeQuery();

        return this.createTabCommande(rs);
    }

    public ArrayList<Commande> selOrdersByStatut(Statut state) throws SQLException, ExceptionDataBase {
        ArrayList<Commande> data = new ArrayList<>();
        this.stmt = DBConnection.con.prepareStatement(SEL_ORDERS_BY_STATUS);
        this.stmt.setString(1, String.valueOf(state));
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
        this.stmt = DBConnection.con.prepareStatement(SEL_COMMANDE_BETWEEN_TWO_DATES);
        this.stmt.setDate(1, dateDebut);
        this.stmt.setDate(2, dateFin);
        ResultSet rs = this.stmt.executeQuery();

        return this.createTabCommande(rs);
    }

    public ArrayList<Commande> selOrdersByStateAndDate(
            Statut statut,
            java.sql.Date startDate,
            java.sql.Date endDate) throws SQLException, ExceptionDataBase {

        System.out.println(startDate);
        System.out.println(endDate);

        this.stmt = DBConnection.con.prepareStatement(SEL_ORDERS_BY_STATE_AND_DATE);
        this.stmt.setString(1, String.valueOf(statut));
        this.stmt.setDate(2, startDate);
        this.stmt.setDate(3, endDate);
        ResultSet rs = this.stmt.executeQuery();

        return createTabCommande(rs);
    }

    public int creerCommande() throws SQLException {

        this.stmt = DBConnection.con.prepareStatement(INS_COMMANDE, Statement.RETURN_GENERATED_KEYS);
        this.stmt.executeUpdate();
        ResultSet rs = this.stmt.getGeneratedKeys();

        if (rs.next()) {        // On récupère l'ID de la nouvelle commande
            return rs.getInt(1);
        } else {
            return 0;
        }
    }

    public int creerCommande(ArrayList<Contenu_Commande> orderContent) throws SQLException {

        this.stmt = DBConnection.con.prepareStatement(INS_COMMANDE, Statement.RETURN_GENERATED_KEYS);
        this.stmt.executeUpdate();
        ResultSet rs = this.stmt.getGeneratedKeys();

        if (rs.next()) {        // On récupère l'ID de la nouvelle commande
            int orderID = rs.getInt(1);

            // Assignation de l'ID de commande aux contenus
            for (int i = 0; i < orderContent.size(); ++i) {
                Contenu_Commande orderItem = orderContent.get(i);
                orderItem.setOrderID(orderID);
                insertOrderContent(orderItem);
            }

            return orderID;
        } else {
            return 0;
        }
    }

    private void insertOrderContent(Contenu_Commande c) throws SQLException {

        // id, idCommande, quantite, refArticle
        this.stmt = DBConnection.con.prepareStatement(INS_CONTENU_COMMANDE);
        this.stmt.setInt(1, c.getOrderID());
        this.stmt.setDouble(2, c.getQuantite());
        this.stmt.setInt(3, c.getRefArticle());
        this.stmt.execute();
    }

    public Commande selCommande(int orderID) throws SQLException, ExceptionDataBase {

        this.stmt = DBConnection.con.prepareStatement(SEL_ORDER);
        this.stmt.setInt(1, orderID);
        ResultSet rs = this.stmt.executeQuery();

        if (rs.next()) {
            Timestamp timestamp = rs.getTimestamp("dateHeure");
            java.sql.Date orderDate = null;
            if (timestamp != null)
                orderDate = new java.sql.Date(timestamp.getTime());

            Commande order = new Commande(
                    rs.getInt("id"),
                    orderDate,
                    rs.getString("statut"));
            return order;
        } else {
            throw new ExceptionDataBase("No such order in database");
        }
    }

    public void updateCommande(Commande commande) throws SQLException {

        this.stmt = DBConnection.con.prepareStatement(UPDATE_ORDER);
        this.stmt.setString(1, String.valueOf(commande.getStatut()));
        this.stmt.setInt(2, commande.getId());
        this.stmt.execute();
    }

    public Stock selArticleByRef(int refArticle) throws SQLException, ExceptionDataBase {

        Stock s = new Stock();
        this.stmt = DBConnection.con.prepareStatement(SEL_STOCK_BY_REF);
        this.stmt.setInt(1, refArticle);
        ResultSet rs = this.stmt.executeQuery();

        if (rs.next()) {
            s.setId(rs.getInt("id"));
            s.setDescription(rs.getString("description"));
            s.setQuantite(rs.getDouble("quantite"));
            s.setUnite(rs.getString("unite"));
        } else {
            throw new ExceptionDataBase("Aucun article ne correspondant à l'ID : " + refArticle);
        }

        return s;
    }

    /**
     * Permet de récupérer le contenu d'une commande en fonction de son id passé en paramètre
     *
     * @param   id_commande(int)
     *
     * @return ArrayList<Commande>
     */
    public ArrayList<Contenu_Commande> selAllContenuCommandeParID (int id_commande) throws SQLException, ExceptionDataBase {
        this.stmt = db.con.prepareStatement(SEL_CONTENU_COMMANDE_PAR_ID);
        this.stmt.setInt(1, id_commande);
        ResultSet rs = this.stmt.executeQuery();

        return this.createTabContenuCommande(rs);
    }

    public String selCommandeEnCours(int idRefArticle) throws SQLException, ExceptionDataBase {

        this.stmt = db.con.prepareStatement(SEL_ARTICLE_COMMANDE_EN_COURS);
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
                data.add(new Stock(rs.getInt("id"), rs.getString("description"), rs.getDouble("quantite"),
                        rs.getString("unite"), rs.getDouble("quantiteMin")));
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
            throw new ExceptionDataBase("La table Commande est vide.");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                data.add(new Commande(
                                rs.getInt("id"),
                                rs.getDate("dateHeure"),
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
                data.add(
                        new Contenu_Commande(
                                rs.getInt("id"),
                                rs.getInt("idCommande"),
                                rs.getInt("refArticle"),
                                rs.getDouble("quantite")
                        )
                );
            }
        }
        this.stmt.close();

        return data;
    }

}
