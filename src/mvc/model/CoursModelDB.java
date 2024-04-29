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
        String query1 = "insert into APICOURS(code,intitule,salledft) values(?,?,?)";
        String query2 = "select idcours from APICOURS where code=?";
        try (java.sql.PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             java.sql.PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, cours.getCode());
            pstm1.setString(2, cours.getIntitule());
            pstm1.setInt(3, cours.getSalleParDefault().getId());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setString(1, cours.getCode());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idcours = rs.getInt(1);
                    cours.setId(idcours);
                    notifyObservers();
                    return cours;
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

    @Override
    public boolean removeCours(Cours cours) {
        String query = "delete from APICOURS where idcours = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, cours.getId());
            int n = pstm.executeUpdate();
            if (n == 0) {
                return false;
            }
            notifyObservers();
            return true;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public Cours updateCours(Cours cours) {
        String query = "update APICOURS set code=?,intitule=?,salledft=? where idcours=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, cours.getCode());
            pstm.setString(2, cours.getIntitule());
            pstm.setInt(3, cours.getSalleParDefault().getId());
            pstm.setInt(4, cours.getId());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return readCours(cours.getId());
            else return null;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public Cours readCours(int id) {
        String query = "select * from APICOURS where idcours = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    String code = rs.getString("code");
                    String intitule = rs.getString("intitule");
                    int idsalle = rs.getInt("salledft");
                    Salle salleParDefault = salleController.getSalleById(idsalle);
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
                int id = rs.getInt("idcours");
                String code = rs.getString("code");
                String intitule = rs.getString("intitule");
                int idsalle = rs.getInt("salledft");
                Salle salleParDefault = salleController.getSalleById(idsalle);
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
