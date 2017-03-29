package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.Statement;

/**
 * Created by D.Hamel on 25.03.17.
 */

public class DBConnection {

    protected static Connection con;
    private static String url = "jdbc:mysql://nas.lozann.ch:3306/zoomanager";
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
            throw new ExceptionDataBase("Erreur, impossible de se connecter à la base de données !");
        }
    }

    private boolean connect() {
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
}
