package demo.TPS6P14WebdesignMai2022.Connexion;


import java.sql.DriverManager;

/**
 *
 * @author Cedrick
 */
public class Connexion {
     public java.sql.Connection con() throws Exception{
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/smmcfil","smmcfil","smmcfil");
    }
    
}


