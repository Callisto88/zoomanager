import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.Statement;
/**
 * Created by D.Hamel on 25.03.17.
 */

class DBConnection {
    private static HashMap<String, int> correspondance;
    private static Connection con;
    private static String url = "jdbc:mysql://nas.lozann.ch:3306/zoomanager";
    private static String  user = "zmusr";
    private static String  pass = "a78DKkw9QfQV**";

    /**
     * Permet une connexion à la base de données.
     * Retourne true si la connexion est OK / False si NOK
     *
     * @return boolean
     */
    protected boolean init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Permet de se déconnecter à la base de données.
     * Retourne true si la connexion est OK / False si NOK
     *
     * @return boolean
     */
    protected boolean close () {
        try {
            con.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Permet d'obtenir dans les noms et prenoms de tous les employés présents dans la base de données
     *
     * @return String[][] / Prénom en première position
     */
    public String[][] selAllPreNomPersonne() {
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
    private ArrayList<Personne> recupererPersonne (final String REQUETE, int[] valeur) {
        PreparedStatement preparedStatement;
        ArrayList<Personne> data = new ArrayList<Personne>();

        this.init();
        try {
            preparedStatement = con.prepareStatement(REQUETE);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                data.add(new Personne(rs.getInt("noAVS"), rs.getString("prenom"),
                        rs.getString("nom"),rs.getString("adresse"),
                        rs.getString("email"),rs.getString("telephone"),
                        rs.getString("dateNaissance"),rs.getInt("responsable"),
                        rs.getString("statut"), rs.getDouble("salaire"),
                        rs.getString("dateDebut"), rs.getString("typeContrat")));
            }
        } catch (Exception e) {
            System.out.println("Erreur dans Recuperer Personne");
        }
        this.close();
        return data;
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
     *
     * @return boolean
     */
    public boolean insertPersonne (int numAVS, String prenom, String nom, String adresse, String email,
                                                String telephone, String dateNaissance, int responsable, String statut,
                                                double salaire, String dateDebut, String typeContrat) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(INSERT_EMPLOYE);
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
        catch (Exception e) {
            return false;
        }
        return true;
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


}
