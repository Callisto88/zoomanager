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
    // ADRESSE :
    // Récupérer toutes les informations sur les villes
    private static final String SEL_ALL_VILLE = "SELECT * FROM Ville;";
    // Récupérer toutes les informations sur les pays
    private static final String SEL_ALL_PAYS = "SELECT * FROM Pays;";
    // Insérer un nouveau Pays dans la DB
    private static final String INSERT_PAYS = "INSERT INTO Pays VALUES (null , ? );";
    // Insérer une nouvelle Ville dans la DB
    private static final String INSERT_VILLE = "INSERT INTO Ville VALUES (null, ? , ? , ? );";
    // Insérer une nouvelle Adresse dans la DB
    private static final String INSERT_ADRESSE = "INSERT INTO Adresse VALUES (null, ? , ? );";
    // Récupérer le pays_id d'une ville "String"
    private static final String SEL_PAYS_ID = "SELECT pays_id FROM Pays WHERE pays = ? ;";
    // Récupérer la ville en fonction d'un code postal
    private static final String SEL_VILLE_PAR_CP = "SELECT ville FROM Ville WHERE codePostal = ? ;";
    // Récupérer la ville en fonction d'un code postal
    private static final String SEL_VILLE_ID_PAR_CP = "SELECT ville_id FROM Ville WHERE codePostal = ? ;";
    // Récupérer les informations sur une adresse et la ville en relation
    private static final String SEL_ADRESSE_PAR_ADR_VILLE_ID =
            "SELECT * FROM Adresse WHERE adresse = ? AND ville_id = ? ;";
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
    private static final String SEL_ENCLOS_ALL = "SELECT * FROM Enclos";

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

    // Récupérer toutes les commandes qui ont été faites (id, date et statut)
    private static final String SEL_ALL_COMMANDE = "SELECT * FROM Commande";

    // Récupérer l'ID et la date de toute les commandes faites entre deux dates Date1 et Date2
    private static final String SEL_COMMANDE_BETWEEN_TWO_DATES = "SELECT * FROM Commande WHERE `date` BETWEEN ? AND ? ;";

    // Récupérer le contenu d'une commande en fonction de son ID
    private static final String SEL_CONTENU_COMMANDE_PAR_ID = "SELECT * FROM Commande_Contenu WHERE idCommande = ? ;";

    // Pour un article donné, récupère la quantité en cours de commande (si commandé)
    private static final String SEL_ARTICLE_COMMANDE_EN_COURS = "SELECT SUM(quantite) AS `quantiteEnCours` \n" +
            "FROM `Commande_Contenu` \n" +
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
     * @param pays String
     * @param cp String
     * @param pays String
     */
    public void insAddress (String adresse, String cp, String pays) throws SQLException {
        // Check si le pays est déjà dans la DB
        // l'insère si non
        if (this.countryIsInDB(pays) == 0) {
            this.insPays(pays);
        }

        // Check si la ville est déjà dans la DB
        // l'insère si non
        if (this.cpIsInDB(cp) == 0) {
            this.insVille(this.getVilleParCP(cp), cp, pays);
        }

        // Check si l'adresse en fonction de la ville est déjà dans la DB
        // l'insère si non
        if (this.addressIsInDB(adresse, this.getVilleIDParCP(cp))) {
            this.insAdresse(adresse, this.getVilleIDParCP(cp));
        }
    }

    /**
     * Permet d'insérer une nouvelle adresse dans la base de données
     *
     * @param adresse String
     */
    private void insAdresse (String adresse, int ville_id) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(INSERT_ADRESSE);
        this.stmt.setString(1, adresse);
        this.stmt.setInt(2, ville_id);
        this.stmt.executeUpdate();
    }


    /**
     * Permet d'insérer un nouveau Pays dans la base de données
     *
     * @param pays String
     */
    private void insPays (String pays) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(INSERT_PAYS);
        this.stmt.setString(1, pays);
        this.stmt.executeUpdate();
    }

    /**
     * Permet d'insérer une nouvelle Ville dans la base de données
     *
     * @param ville String
     */
    private void insVille (String ville, String codePostal, String pays) throws SQLException {
        int pays_id = this.getPaysID(pays);

        this.stmt = DBConnection.con.prepareStatement(INSERT_VILLE);
        this.stmt.setString(1, ville);
        this.stmt.setString(2, codePostal);
        this.stmt.setInt(3, pays_id);
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

        return rs.getInt("pays_id");
    }

    /**
     * Permet de récupérer le nom d'une ville en fonction d'un code postal passé en paramètre
     *
     * @param cp String
     *
     * @return String
     */
    public String getVilleParCP (String cp) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(SEL_VILLE_PAR_CP);
        this.stmt.setString(1, cp);
        ResultSet rs = this.stmt.executeQuery();
        rs.next();

        return rs.getString("ville");
    }

    /**
     * Permet de récupérer le ville_id d'une ville en fonction d'un code postal passé en paramètre
     *
     * @param cp String
     *
     * @return int
     */
    public int getVilleIDParCP (String cp) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(SEL_VILLE_ID_PAR_CP);
        this.stmt.setString(1, cp);
        ResultSet rs = this.stmt.executeQuery();
        rs.next();

        return rs.getInt("ville_id");
    }


    /**
     * Permet de savoir si un code postal est déjà présent dans la db
     * Retourne la ville_id du code Postal si présent
     * Retourne 0 si non présent
     *
     * @param cp Int
     *
     * @return int
     */
    public int cpIsInDB (String cp) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(SEL_ALL_VILLE);
        ResultSet rs = this.stmt.executeQuery();

        ArrayList<Ville> data = new ArrayList<Ville>();

        while (rs.next()) data.add(new Ville(rs.getInt("ville_id"), rs.getString("ville"),
                rs.getString("codePostal"), rs.getInt("ville_id")));

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getCp().equals(cp))
                return data.get(i).getVille_id();
        }
        return 0;
    }

    /**
     * Permet de savoir si un Pays est déjà présent dans la db
     * Retourne le pays_id du Pays si présent
     * Retourne 0 si non présent
     *
     * @param pays String
     *
     * @return int
     */
    public int countryIsInDB (String pays) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(SEL_ALL_PAYS);
        ResultSet rs = this.stmt.executeQuery();

        ArrayList<Pays> data = new ArrayList<Pays>();

        while (rs.next()) data.add(new Pays(rs.getInt("pays_id"), rs.getString("pays")));

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getPays().equals(pays))
                return data.get(i).getPays_id();
        }
        return 0;
    }

    /**
     * Permet de savoir si une Adresse est déjà présent dans la db en fonction de son lien avec la ville
     *
     * @param adresse String
     * @param ville_id int
     *
     * @return boolean
     */
    public boolean addressIsInDB (String adresse, int ville_id) throws SQLException {
        this.stmt = DBConnection.con.prepareStatement(SEL_ADRESSE_PAR_ADR_VILLE_ID);
        ResultSet rs = this.stmt.executeQuery();

        if (!rs.next()) {
            return false;
        } else {
            return true;
        }
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

        this.stmt = db.con.prepareStatement(SEL_ALL_STATUTS);
        ResultSet rs = this.stmt.executeQuery();

        ArrayList<String> listStatuts = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Cette requête n'a retourné aucun résultat");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                listStatuts.add(new String(rs.getString("statut")));
            }
        }
        return listStatuts;
    }


    public ArrayList<String> getAllDifferentStatus() throws SQLException, ExceptionDataBase {

        this.stmt = db.con.prepareStatement(SEL_TYPE_CONTRAT);
        ResultSet rs = this.stmt.executeQuery();

        ArrayList<String> listTypeContrat = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Cette requête n'a retourné aucun résultat");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                listTypeContrat.add(new String(rs.getString("typeContrat")));
            }
        }
        return listTypeContrat;
    }


    public ArrayList<String> selAllContractType() throws SQLException, ExceptionDataBase {

        this.stmt = db.con.prepareStatement(SEL_TYPE_CONTRAT);
        ResultSet rs = this.stmt.executeQuery();

        ArrayList<String> listTypeContrat = new ArrayList<>();
        if (!rs.next()) {
            throw new ExceptionDataBase("Cette requête n'a retourné aucun résultat");
        } else {
            rs.beforeFirst();
            while (rs.next()) {
                listTypeContrat.add(new String(rs.getString("typeContrat")));
            }
        }
        return listTypeContrat;
    }


    public void delPersonne(int id) throws SQLException {

        this.stmt = db.con.prepareStatement(DEL_PERSONNE);
        this.stmt.setInt(1, id);
        this.stmt.executeQuery();
    }


    public ArrayList<Personne> selAllEmployes() throws SQLException, ExceptionDataBase {

        ArrayList<Personne> listEmployes = new ArrayList<Personne>();
        this.stmt = db.con.prepareStatement(SEL_ALL_EMPLOYES);
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
        this.stmt = db.con.prepareStatement(SEL_EMPLOYE_DETAILS);
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
    public void insertPersonne(String noAVS, String prenom, String nom, int adresse, String email,
                            String telephone, java.sql.Date dateNaissance, int responsable, String statut,
                            double salaire, java.sql.Date dateDebut, String typeContrat)
            throws ExceptionDataBase, SQLException {
        this.stmt = db.con.prepareStatement(INSERT_EMPLOYE);
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
                        rs.getInt("enclos"), rs.getInt("origine"),
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
            this.stmt.setDouble(2, f.getPoids());
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
            this.stmt.setDouble(2, r.getTemperature());
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
        this.stmt.setInt(6, a.getOrigine());
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

        this.stmt = db.con.prepareStatement(SEL_ENCLOS_ALL);
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
    public void insertEvenement (Evenement evenement) throws SQLException {

        this.stmt = this.db.con.prepareStatement(INSERT_EVENEMENT);

        this.stmt.setNull(1, Types.NULL);
        this.stmt.setString(2, evenement.getDescription());
        this.stmt.setTimestamp(3, evenement.getDate());
        this.stmt.setInt(4, evenement.getType());

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
