/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.TPS6P14WebdesignMai2022.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import demo.TPS6P14WebdesignMai2022.Connexion.Connexion;
import demo.TPS6P14WebdesignMai2022.generic.Attr;
import demo.TPS6P14WebdesignMai2022.generic.ClassAnotation;
import demo.TPS6P14WebdesignMai2022.generic.GenericDAO;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 *
 */
/**
 *
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ClassAnotation(table = "Attente")
public class Attente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Attr(isPrimary = true)
    int id;
    @Attr
    int numero;
    @Attr
    Date date_attente;

    //String date_attenteStr;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDate_attente() {
        return date_attente;
    }

    public void setDate_attente(Date date_attente) {
        this.date_attente = date_attente;
    }

    public Attente(int numero, Date date_attente) {
        this.numero = numero;
        this.date_attente = date_attente;
    }

    /*public void setDate_attenteStr(String date_attenteStr) {
    this.date_attenteStr = date_attenteStr;
}
 public String getDate_attenteStr() {
        return date_attenteStr;
    }*/
    public Attente() {
    }

    //**************************DELETE********************
    public ArrayList<Attente> findDernierEntrant() throws Exception {
        ArrayList<Attente> listeEmp = GenericDAO.findBySql(new Attente(), "SELECT * FROM attente WHERE numero IS NOT NULL ORDER BY id DESC LIMIT 1", new Econnect().connexion());
        return listeEmp;
    }

    public int resetNumero(Date newDateAttente) throws Exception {
        int newNumero = 0;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Initialisez la connexion à la base de données (remplacez par votre code de connexion)
            Connexion connect = new Connexion();
            connection = connect.con();

            // Recherchez le dernier numéro pour la date actuelle
            String sql = "SELECT numero FROM attente WHERE date_attente = ? ORDER BY numero DESC LIMIT 1";
            statement = connection.prepareStatement(sql);
            statement.setDate(1, (java.sql.Date) newDateAttente);
            ResultSet rs = statement.executeQuery();

            int dernierNumero = 0;

            if (rs.next()) {
                dernierNumero = rs.getInt("numero");
            }

            if (dernierNumero == 0) {
                // Aucune entrée pour la date actuelle, réinitialisez à 1000
                newNumero = 1000;
            } else {
                // Incrémentez le numéro
                newNumero = dernierNumero + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Assurez-vous de fermer la connexion et la déclaration dans le bloc finally
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return newNumero;
    }

    public void insererFileAttente(Date dateAttente) throws Exception {

        /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = dateFormat.parse(dateAttente);
    java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());*/
        int numero = resetNumero(dateAttente); // Utilisez la date convertie

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Initialisez la connexion à la base de données (remplacez par votre code de connexion)
            Connexion connect = new Connexion();
            connection = connect.con();

            // Requête SQL pour l'insertion d'une nouvelle file d'attente
            String sqlInsert = "INSERT INTO attente(numero, date_attente) VALUES (?, ?)";

            // Préparez la déclaration avec les valeurs
            statement = connection.prepareStatement(sqlInsert);
            statement.setInt(1, numero);
            statement.setDate(2, (java.sql.Date) dateAttente);

            // Exécutez la requête d'insertion
            int lignesAffectees = statement.executeUpdate();

            if (lignesAffectees > 0) {
                System.out.println("Nouvelle file d'attente insérée avec succès !");
            } else {
                System.out.println("Erreur lors de l'insertion de la file d'attente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Assurez-vous de fermer la connexion et la déclaration dans le bloc finally
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    

    //findByIdTab
    /*public ArrayList<Artistes> getEmp(Artistes id) throws Exception {
        ArrayList<Artistes> listeDept = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            Connexion connect = new Connexion();
            connection = connect.con();
            String query = "Select * from Artistes where id =?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id.getId());
            result = statement.executeQuery();
            while (result.next()) {

                Artistes bandL = new Artistes();
                bandL.setId(result.getInt("id"));
                bandL.setNom(result.getString("nom"));
                bandL.setPrenom(result.getString("prenom"));

                listeDept.add(bandL);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return listeDept;
    }*/
 /* public Artistes Login(String nom, String prenom) throws SQLException, Exception {
        Artistes emp = null;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            Connexion connect = new Connexion();
            connection = connect.con();
            String query = "select* from Artistes where nom=? and pseudo=? ";
            statement = connection.prepareStatement(query);
            statement.setString(1, nom);
            statement.setString(2, prenom);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                 emp = new Artistes();
                emp.setId(resultSet.getInt("id"));
                emp.setNom(resultSet.getString("nom"));
                emp.setTarifParHeur(resultSet.getBigDecimal(""));
               
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("disoooo mdp oohDISOOOOOOOO");
            if (connection != null) {
                connection.close();
            }
        } finally {
            if (resultSet != null) {
                statement.close();
            }
            if (statement != null) {
                statement.close();
            }

        }
        return emp;
    }
    
     */
}
