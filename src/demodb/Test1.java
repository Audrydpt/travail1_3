package demodb;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test1 {

    public void demo(){

        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        String query = "select * from APICOURS";
        try (Statement stmt = dbConnect.createStatement();
             ResultSet rs = stmt.executeQuery(query);) {

            while (rs.next()) {
                String name = rs.getString("INTITULE");
                String code = rs.getString("CODE");
                String n = "" + rs.getInt("ID_CO");
                System.out.println(name + " " + code + " " + n);
            }
        } catch (SQLException e) {
            System.out.println("erreur SQL " + e);
        }

        DBConnection.closeConnection();
    }

    public static void main(String[] args) {
        Test1 pgm = new Test1();
        pgm.demo();
    }
}
