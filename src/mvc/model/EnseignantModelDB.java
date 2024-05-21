package mvc.model;

import ecole.metier.Enseignant;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EnseignantModelDB extends DAOEnseignant {
    protected Connection dbConnect;

    public EnseignantModelDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");

            System.exit(1);
        }

    }


    public Enseignant addEnseignant(Enseignant enseignant) {
        String insertProcedure = "{ call APIADD_ENSEIGNANT(?,?,?,?,?,?,?) }";
        String selectQuery = "SELECT id_e FROM APIENSEIGNANT WHERE matricule = ?";
        try (CallableStatement cstm = dbConnect.prepareCall(insertProcedure);
             PreparedStatement pstm2 = dbConnect.prepareStatement(selectQuery)) {

            cstm.setString(1, enseignant.getMatricule());
            cstm.setString(2, enseignant.getNom());
            cstm.setString(3, enseignant.getPrenom());
            cstm.setString(4, enseignant.getTel());
            cstm.setInt(5, enseignant.getChargeSem());
            cstm.setBigDecimal(6, enseignant.getSalaireMensuel());
            cstm.setDate(7, Date.valueOf(enseignant.getDateEngagement()));
            cstm.execute();

            pstm2.setString(1, enseignant.getMatricule());
            ResultSet rs = pstm2.executeQuery();
            if (rs.next()) {
                int id_e = rs.getInt(1);
                enseignant.setID(id_e);
                notifyObservers();
                return enseignant;
            } else {
                System.err.println("Record introuvable");
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL :" + e);
            return null;
        }
    }


    public boolean removeEnseignant(Enseignant enseignant) {
        String removeProcedure = "{ call APIREMOVE_ENSEIGNANT(?) }";
        try (CallableStatement cstm = dbConnect.prepareCall(removeProcedure)) {
            cstm.setInt(1, enseignant.getId());
            int n = cstm.executeUpdate();
            if (n == 1) {
                notifyObservers();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL :" + e);
            return false;
        }
    }


    public Enseignant updateEnseignant(Enseignant enseignant) {
        String updateProcedure = "{ call APIUPDATE_ENSEIGNANT(?,?,?,?,?,?,?,?) }";
        try (CallableStatement cstm = dbConnect.prepareCall(updateProcedure)) {
            cstm.setInt(1, enseignant.getId());
            cstm.setString(2, enseignant.getMatricule());
            cstm.setString(3, enseignant.getNom());
            cstm.setString(4, enseignant.getPrenom());
            cstm.setString(5, enseignant.getTel());
            cstm.setInt(6, enseignant.getChargeSem());
            cstm.setBigDecimal(7, enseignant.getSalaireMensuel());
            cstm.setDate(8, Date.valueOf(enseignant.getDateEngagement()));

            int n = cstm.executeUpdate();
            if (n == 1) {
                return enseignant;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL :" + e);
            return null;
        }
    }


    public Enseignant readEnseignant(int id) {
        String query = "select * from APIENSEIGNANT where id_e = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String matricule = rs.getString("matricule");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String tel = rs.getString("tel");
                int chargeSem = rs.getInt("chargesem");
                BigDecimal salaireMen = rs.getBigDecimal("salairemensuel");
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

                int id = rs.getInt("id_e");
                String matricule = rs.getString("matricule");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String tel = rs.getString("tel");
                int chargeSem = rs.getInt("chargesem");
                BigDecimal salaireMen = rs.getBigDecimal("salairemensuel");
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



