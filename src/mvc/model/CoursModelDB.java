package mvc.model;

import ecole.metier.Cours;
import ecole.metier.Salle;
import mvc.controller.SalleController;
import myconnections.DBConnection;
import mvc.view.SalleAbstractView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursModelDB extends DAOCours {
    protected Connection dbConnect;
    protected SalleController salleController;

    public CoursModelDB(SalleController salleController) {
        this.salleController = salleController;
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
    }

    @Override
    public Cours addCours(Cours cours) {
        String addProcedure = "{ call APIADD_COURS(?,?,?) }";
        try (CallableStatement cstm = dbConnect.prepareCall(addProcedure)) {
            cstm.setString(1, cours.getCode());
            cstm.setString(2, cours.getIntitule());
            if (cours.getSalleParDefault() != null) {
                cstm.setInt(3, cours.getSalleParDefault().getId());
            } else {
                cstm.setNull(3, Types.INTEGER);
            }
            cstm.executeUpdate();
            notifyObservers();
            return cours;
        } catch (SQLException e) {
            System.err.println("Erreur SQL :" + e);
            return null;
        }
    }


    @Override
    public boolean removeCours(Cours cours) {
        String removeProcedure = "{ call APIREMOVE_COURS(?) }";
        try (CallableStatement cstm = dbConnect.prepareCall(removeProcedure)) {
            cstm.setInt(1, cours.getId());
            int n = cstm.executeUpdate();
            if (n == 0) {
                return false;
            }
            notifyObservers();
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur SQL :" + e);
            return false;
        }
    }


    @Override
    public Cours updateCours(Cours cours) {
        String updateProcedure = "{ call APIUPDATE_COURS(?,?,?,?) }";
        try (CallableStatement cstm = dbConnect.prepareCall(updateProcedure)) {
            cstm.setInt(1, cours.getId());
            cstm.setString(2, cours.getCode());
            cstm.setString(3, cours.getIntitule());
            cstm.setInt(4, cours.getSalleParDefault().getId());
            cstm.executeUpdate();
            notifyObservers();
            return readCours(cours.getId());
        } catch (SQLException e) {
            System.err.println("Erreur SQL :" + e);
            return null;
        }
    }


    @Override
    public Cours readCours(int id) {
        String query = "select * from APICOURS where id_co = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    String code = rs.getString("code");
                    String intitule = rs.getString("intitule");
                    int idsalle = rs.getInt("id_s");
                    Salle salleParDefault = salleController.search(idsalle);
                    Cours c = new Cours(id, code, intitule, salleParDefault);
                    return c;
                }
            } catch (SQLException e) {
                System.err.println("erreur sql :" + e);
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
        return null;
    }


    @Override
    public List<Cours> getCours() {
        List<Cours> lco = new ArrayList<>();
        String query = "select * from APICOURS";
        try (Statement stm = dbConnect.createStatement();
             ResultSet rs = stm.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id_co");
                String code = rs.getString("code");
                String intitule = rs.getString("intitule");
                int idsalle = rs.getInt("id_s");
                Salle salleParDefault = salleController.search(idsalle);
                Cours c = new Cours(id, code, intitule, salleParDefault);
                lco.add(c);
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
        }
        return lco;
    }

    @Override
    public List getNotification() {
        return getCours();
    }


}
