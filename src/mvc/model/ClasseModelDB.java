package mvc.model;

import ecole.metier.Classe;
import myconnections.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClasseModelDB extends DAOClasse {
    protected Connection dbConnect;

    public ClasseModelDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");

            System.exit(1);
        }

    }

    @Override
    public Classe addClasse(Classe classe) {
        String query1 = "insert into APICLASSE(sigle,annee,specialite,nbreleve) values(?,?,?,?)";
        String query2 = "select id_c from APICLASSE where sigle=?";
        try (java.sql.PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             java.sql.PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, classe.getSigle());
            pstm1.setInt(2, classe.getAnnee());
            pstm1.setString(3, classe.getSpecialite());
            pstm1.setInt(4, classe.getNbreEleve());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setString(1, classe.getSigle());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int id_c = rs.getInt(1);
                    classe.setId(id_c);
                    notifyObservers();
                    return classe;
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
    public boolean removeClasse(Classe classe) {
        String query = "delete from APICLASSE where id_c = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {

            pstm.setInt(1, classe.getId());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return true;
            else return false;

        } catch (SQLException e) {
            //System.err.println("erreur sql :"+e);
            return false;
        }
    }

    @Override
    public Classe updateClasse(Classe classe) {
        String query = "update APICLASSE set sigle=?, annee=?, specialite=?, nbreleve=? where id_c=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, classe.getSigle());
            pstm.setInt(2, classe.getAnnee());
            pstm.setString(3, classe.getSpecialite());
            pstm.setInt(4, classe.getNbreEleve());
            pstm.setInt(5, classe.getId());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return readClasse(classe.getId());
            else return null;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return null;
        }
    }

    @Override
    public Classe readClasse(int id_c) {
        String query = "select * from APICLASSE where id_c = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, id_c);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String sigle = rs.getString("sigle");
                int annee = rs.getInt("annee");
                String specialite = rs.getString("specialite");
                int nbreleve = rs.getInt("nbreleve");
                Classe cl = new Classe(id_c, sigle, annee, specialite, nbreleve);
                return cl;

            } else {
                System.err.println("record introuvable");
                return null;
            }
        } catch (SQLException e) {
            //System.err.println("erreur sql :"+e);
            return null;
        }
    }

    @Override
    public List<Classe> getClasses() {
        List<Classe> lc = new ArrayList<>();
        String query = "select * from APICLASSE";
        try (Statement pstm = dbConnect.createStatement()){
             ResultSet rs = pstm.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id_c");
                String sigle = rs.getString("sigle");
                int annee = rs.getInt("annee");
                String specialite = rs.getString("specialite");
                int nbreleve = rs.getInt("nbreleve");
                lc.add(new Classe(id, sigle, annee, specialite, nbreleve));
            }
            return lc;
        } catch (SQLException e) {
            //System.err.println("erreur sql :"+e);
            return null;
        }
    }

    @Override
    public List getNotification () {
        return getClasses();
    }
}
