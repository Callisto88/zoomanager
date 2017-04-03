import java.sql.Connection;
import java.sql.DriverManager;

    /**
     *
     */
    public class App
    {
        public static void main( String[] args )
        {
            Connection con;
            String url = "jdbc:mysql://nas.lozann.ch:3306/stock";
            String user = "zmusr";
            String pass = "ZM@H£1g_!*";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, user, pass);
            } catch (Exception e) {
                System.out.println("Erreur, la base de données n'est pas joignable ");
            }
        }
    }