package mvc.model;

import ecole.metier.Cours;
import ecole.metier.Salle;
import myconnections.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalleModelDB extends DAOSalle {
    protected Connection dbConnect;

    public SalleModelDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
    }

    @Override
    public Salle addSalle(Salle salle) {
        String addProcedure = "{ call APIADD_SALLE(?,?) }";
        try (CallableStatement cstm = dbConnect.prepareCall(addProcedure)) {
            cstm.setString(1, salle.getSigle());
            cstm.setInt(2, salle.getCapacite());
            cstm.executeUpdate();
            notifyObservers();
            return salle;
        } catch (SQLException e) {
            System.err.println("Erreur SQL :" + e);
            return null;
        }
    }


    @Override
    public boolean removeSalle(Salle salle) {
        String removeProcedure = "{ call APIREMOVE_SALLE(?) }";
        try (CallableStatement cstm = dbConnect.prepareCall(removeProcedure)) {
            cstm.setInt(1, salle.getId());
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


    @Override
    public Salle updateSalle(Salle salle) {
        String updateProcedure = "{ call APIUPDATE_SALLE(?,?,?) }";
        try (CallableStatement cstm = dbConnect.prepareCall(updateProcedure)) {
            cstm.setInt(1, salle.getId());
            cstm.setString(2, salle.getSigle());
            cstm.setInt(3, salle.getCapacite());
            cstm.executeUpdate();
            return salle;
        } catch (SQLException e) {
            System.err.println("Erreur SQL :" + e);
            return null;
        }
    }


    @Override
    public Salle readSalle(int id) {
        String query = "select * from APISALLE where id_s=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query);) {
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String sigle = rs.getString("sigle");
                int capacite = rs.getInt("capacite");
                return new Salle(id, sigle, capacite);
            } else return null;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public List<Salle> getSalles() {
        List<Salle> lsa = new ArrayList<>();
        String query = "select * from APISALLE";
        try (Statement stm = dbConnect.createStatement();) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id_s");
                String sigle = rs.getString("sigle");
                int capacite = rs.getInt("capacite");
                lsa.add(new Salle(id, sigle, capacite));
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
        }
        return lsa;
    }
        /*
        CREATE VIEW APICoursSalleView ("CODE","INTITULE","ID_S") AS
        SELECT
          co.code,
          co.intitule,
          s.id_s
        FROM APICOURS co
        JOIN APISALLE s ON co.id_s = s.id_s;
         */

    @Override
    public List<Cours> coursSalleDefaut(Salle salle) {
        List<Cours> lc = new ArrayList<>();
        String query = "select * from APICoursSalleView where id_s=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, salle.getId());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String code = rs.getString("code");
                String intitule = rs.getString("intitule");
                lc.add(new Cours(code, intitule, salle));
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
        }
        return lc;
    }

    @Override
    public List getNotification() {
        return getSalles();
    }
}
