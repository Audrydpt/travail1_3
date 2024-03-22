package demodb;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test2 {

    public void demo1(){

        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        String query = "select * from APIENSEIGNANT";
        try (Statement stmt = dbConnect.createStatement();
             ResultSet rs = stmt.executeQuery(query);) {

            while (rs.next()) {
                String name = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                String n = "" + rs.getString("TEL");
                System.out.println(name + " " + prenom + " " + n);
            }
        } catch (SQLException e) {
            System.out.println("erreur SQL " + e);
        }

        DBConnection.closeConnection();
    }

    public static void main(String[] args) {
        Test2 pgm = new Test2();
        pgm.demo1();
    }
}
