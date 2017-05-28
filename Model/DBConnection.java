package Model;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * Cette classes contient les informations (URL, user, pass) d'accès à la base de données
 * Elle permet d'initier une connexion avec cette dernière
 *
 *
 * @author D.Hamel
 * @author C.Balboni
 *
 * @version 1.0
 *
 * @date    28.03.2017 (Création)
 * @date    31.04.2017 (Finalisation v1.0)
 *
 */
public class DBConnection {

    protected static Connection con;
    // Adresse Cyril
    private static String url = "jdbc:mysql://nas.lozann.ch:3306/zoomanager";
    //private static String url = "jdbc:sqlite:zoomanager.db";
    // Adresse André
    //private static String url = "jdbc:mysql://evodede.synology.me:3306/zoomanager";
    // Adresse local
    //private static String url = "jdbc:mysql://localhost:3306/zoomanager";
    private static String  user = "zmusr";
    private static String  pass = "a78DKkw9QfQV**";

    /**
     * Permet une connexion à la base de données.
     * Retourne true si la connexion est OK / False si NOK
     *
     * @return boolean
     */

    public void init() throws ExceptionDataBase {
        if (this.connect()) {
            return;
        } else {
            throw new ExceptionDataBase(0);
        }
    }

    /**
     * Classe qui fait la connexion à la base de données
     *
     * @return boolean
     */
    private boolean connect() {
        try {
            //Class.forName("org.sqlite.JDBC");
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
    protected boolean close() {
        try {
            con.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
