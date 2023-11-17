
package demo.TPS6P14WebdesignMai2022.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Econnect {
      public static Connection connexion() {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/smmcfil", "smmcfil", "smmcfil");
           con.setAutoCommit(false);
            System.out.println("Connectee ahn");

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Tsy mety mi-connecte❌");
        }
        return con;
    }

    // Close Connection 
    public static void closeConnection(Connection con) throws Exception {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
