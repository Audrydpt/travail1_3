package mvc.model;

import ecole.metier.Enseignant;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class EnseignantModelDB extends DAOEnseignant{
    protected Connection dbConnect;

    public EnseignantModelDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");

            System.exit(1);
        }

    }



    public Enseignant addEnseignant(Enseignant enseignant) {
        String query1 = "insert into APIENSEIGNANT(matricule,nom,prenom,tel,chargesem,salairemen,dateengagement) values(?,?,?,?,?,?,?)";
        String query2 = "select idenseignant from APIENSEIGNANT where matricule=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, enseignant.getMatricule());
            pstm1.setString(2, enseignant.getNom());
            pstm1.setString(3, enseignant.getPrenom());
            pstm1.setString(4, enseignant.getTel());
            pstm1.setInt(5, enseignant.getChargeSem());
            pstm1.setBigDecimal(6, enseignant.getSalaireMensuel());
            pstm1.setDate(7, Date.valueOf(enseignant.getDateEngagement()));
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setString(1, enseignant.getMatricule());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idenseignant = rs.getInt(1);
                    enseignant.setID(idenseignant);
                    notifyObservers();
                    return enseignant;
                } else {

                    System.err.println("record introuvable");
                    return null;
                }
            } else return null;

        } catch (SQLException e) {
            //System.err.println("erreur sql :"+e);
            return null;
        }
    }

    public boolean removeEnseignant(Enseignant enseignant) {
        String query = "delete from APIENSEIGNANT where idenseignant = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {

            pstm.setInt(1, enseignant.getId());
            int n = pstm.executeUpdate();
            return n == 1;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    public Enseignant updateEnseignant(Enseignant enseignant) {
        String query = "update APIENSEIGNANT set matricule=?,nom=?,prenom=?,tel=?,chargesem=?,salairemen=?,dateengagement=? where idenseignant=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, enseignant.getMatricule());
            pstm.setString(2, enseignant.getNom());
            pstm.setString(3, enseignant.getPrenom());
            pstm.setString(4, enseignant.getTel());
            pstm.setInt(5, enseignant.getChargeSem());
            pstm.setBigDecimal(6, enseignant.getSalaireMensuel());
            pstm.setDate(7, Date.valueOf(enseignant.getDateEngagement()));
            pstm.setInt(8, enseignant.getId());
            int n = pstm.executeUpdate();
            if (n == 0) {
                return null;
            }
            return enseignant;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }

    public Enseignant readEnseignant(int id) {
        String query = "select * from APIENSEIGNANT where idenseignant = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String matricule = rs.getString("matricule");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String tel = rs.getString("tel");
                int chargeSem = rs.getInt("chargesem");
                BigDecimal salaireMen = rs.getBigDecimal("salairemen");
                LocalDate dateEngagement = rs.getDate("dateengagement").toLocalDate();

                return new Enseignant(id, matricule, nom, prenom, tel, chargeSem, salaireMen, dateEngagement);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }


    public List<Enseignant> getEnseignants() {
        List<Enseignant> enseignants = new ArrayList<>();
        String query = "select * from APIENSEIGNANT";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("idenseignant");
                String matricule = rs.getString("matricule");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String tel = rs.getString("tel");
                int chargeSem = rs.getInt("chargesem");
                BigDecimal salaireMen = rs.getBigDecimal("salairemen");
                LocalDate dateEngagement = rs.getDate("dateengagement").toLocalDate();

                Enseignant enseignant = new Enseignant(id, matricule, nom, prenom, tel, chargeSem, salaireMen, dateEngagement);
                enseignants.add(enseignant);
            }
            return enseignants;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }

    public List getNotification() {
        return getEnseignants();
    }
}



