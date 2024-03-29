package demodb;

import myconnections.DBConnection;

import java.sql.*;
import java.util.Scanner;

public class GestionClasse {

    private Scanner sc = new Scanner(System.in);
    private Connection dbConnect;

    public void gestion() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        do {
            System.out.println("1.ajout\n2.recherche\n3.modification\n4.suppression\n5.Afficher tout\n6.fin");
            System.out.println("choix : ");
            int ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    ajoutClasse();
                    break;
                case 2:
                    rechercheClasse();
                    break;
                case 3:
                    modificationClasse();
                    break;
                case 4:
                    suppressionClasse();
                    break;
                case 5:
                    tous();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }



    public void ajoutClasse() {
        try {
            String query = "INSERT INTO APICLASSE(SIGLE, ANNEE, SPECIALITE, NBRELEVE) VALUES (?, ?, ?, ?)";

            PreparedStatement pstmt = dbConnect.prepareStatement(query);

            System.out.println("Entrez sigle: ");
            String sigle = sc.nextLine();
            pstmt.setString(1, sigle);

            System.out.println("Entrez annee: ");
            int annee = sc.nextInt();
            sc.nextLine();
            pstmt.setInt(2, annee);

            System.out.println("Entrez specialite: ");
            String specialite = sc.nextLine();
            pstmt.setString(3, specialite);

            System.out.println("Entrez nbreleve: ");
            int nbreleve = sc.nextInt();
            sc.nextLine();
            pstmt.setInt(4, nbreleve);

            pstmt.executeUpdate();

            System.out.println("Classe ajoutée avec succès");

        } catch (SQLException e) {
            System.out.println("Erreur : " +e.getMessage());        }
    }

    public void rechercheClasse() {
        try {
            String query = "SELECT * FROM APICLASSE WHERE ID_C = ?";

            PreparedStatement pstmt = dbConnect.prepareStatement(query);

            System.out.println("Entrez l'ID de la classe: ");
            int id = sc.nextInt();
            sc.nextLine();

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String sigle = rs.getString("SIGLE");
                int annee = rs.getInt("ANNEE");
                String specialite = rs.getString("SPECIALITE");
                int nbreleve = rs.getInt("NBRELEVE");

                System.out.println("ID: " + id);
                System.out.println("Sigle: " + sigle);
                System.out.println("Annee: " + annee);
                System.out.println("Specialite: " + specialite);
                System.out.println("Nombre d'eleves: " + nbreleve);
            } else {
                System.out.println("Aucune classe trouvée avec l'ID " + id);
            }

        } catch (SQLException e) {
            System.out.println("Erreur : " +e.getMessage());        }
    }



    public void modificationClasse() {
        try {
            String query = "UPDATE APICLASSE SET SIGLE = ?, ANNEE = ?, SPECIALITE = ?, NBRELEVE = ? WHERE ID_C = ?";

            PreparedStatement pstmt = dbConnect.prepareStatement(query);

            System.out.println("Entrez l'ID de la classe: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("Entrez sigle: ");
            String sigle = sc.nextLine();

            System.out.println("Entrez annee: ");
            int annee = sc.nextInt();
            sc.nextLine();

            System.out.println("Entrez specialite: ");
            String specialite = sc.nextLine();

            System.out.println("Entrez nbreleve: ");
            int nbreleve = sc.nextInt();
            sc.nextLine();

            pstmt.setString(1, sigle);
            pstmt.setInt(2, annee);
            pstmt.setString(3, specialite);
            pstmt.setInt(4, nbreleve);
            pstmt.setInt(5, id);

            pstmt.executeUpdate();

            System.out.println("Classe modifiée avec succès");

        } catch (SQLException e) {
            System.out.println("Erreur : " +e.getMessage());
        }
    }

    public void suppressionClasse() {
        try {
            String query = "DELETE FROM APICLASSE WHERE ID_C = ?";

            PreparedStatement pstmt = dbConnect.prepareStatement(query);

            System.out.println("Entrez l'ID de la classe: ");
            int id = sc.nextInt();
            sc.nextLine();

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            System.out.println("Classe supprimée avec succès");

        } catch (SQLException e) {
            System.out.println("Erreur : " +e.getMessage());        }
    }

    private void tous() {
        String query = "SELECT * FROM APICLASSE";
        try (Statement stmt = dbConnect.createStatement();
             ResultSet rs = stmt.executeQuery(query);) {

            while (rs.next()) {
                int id = rs.getInt("ID_C");
                String sigle = rs.getString("SIGLE");
                int annee = rs.getInt("ANNEE");
                String specialite = rs.getString("SPECIALITE");
                int nbreleve = rs.getInt("NBRELEVE");

                System.out.println("------------------------------------------------");
                System.out.println("ID: " + id);
                System.out.println("Sigle: " + sigle);
                System.out.println("Annee: " + annee);
                System.out.println("Specialite: " + specialite);
                System.out.println("Nombre d'eleves: " + nbreleve);
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " +e.getMessage());        }


    }



    public static void main(String[] args) {

        GestionClasse g = new GestionClasse();
        g.gestion();
    }

}
