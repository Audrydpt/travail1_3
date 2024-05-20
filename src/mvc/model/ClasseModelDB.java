package mvc.model;

import ecole.metier.*;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
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
        String addProcedure = "{ call APIADD_CLASSE(?,?,?,?) }";
        try (CallableStatement cstm = dbConnect.prepareCall(addProcedure)) {
            cstm.setString(1, classe.getSigle());
            cstm.setInt(2, classe.getAnnee());
            cstm.setString(3, classe.getSpecialite());
            cstm.setInt(4, classe.getNbreEleve());
            cstm.executeUpdate();
            notifyObservers();
            return classe;
        } catch (SQLException e) {
            System.err.println("Erreur SQL :" + e);
            return null;
        }
    }


    @Override
    public boolean removeClasse(Classe classe) {
        String removeProcedure = "{ call APIREMOVE_CLASSE(?) }";
        try (CallableStatement cstm = dbConnect.prepareCall(removeProcedure)) {
            cstm.setInt(1, classe.getId());
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
    public Classe updateClasse(Classe classe) {
        String updateProcedure = "{ call APIUPDATE_CLASSE(?,?,?,?,?) }";
        try (CallableStatement cstm = dbConnect.prepareCall(updateProcedure)) {
            cstm.setInt(1, classe.getId());
            cstm.setString(2, classe.getSigle());
            cstm.setInt(3, classe.getAnnee());
            cstm.setString(4, classe.getSpecialite());
            cstm.setInt(5, classe.getNbreEleve());
            cstm.executeUpdate();
            notifyObservers();
            return readClasse(classe.getId());
        } catch (SQLException e) {
            System.err.println("Erreur SQL :" + e);
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
        try (Statement pstm = dbConnect.createStatement()) {
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
    public boolean addCours(Classe classe, Cours cours, int h) {
        String query = "insert into APIInfos(nbheures,id_s,id_e,id_co,id_c) values(?,?,?,?,?)";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, h);
            pstm.setString(2, null);
            pstm.setString(3, null);
            pstm.setInt(4, cours.getId());
            pstm.setInt(5, classe.getId());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return false;
        }
    }

    @Override
    public boolean modifCours(Classe classe, Cours cours, Salle salle) {
        String query = "update APIInfos set id_s=? where id_c=? and id_co=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, salle.getId());
            pstm.setInt(2, classe.getId());
            pstm.setInt(3, cours.getId());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return false;
        }
    }

    @Override
    public boolean modifCours(Classe classe, Cours cours, Enseignant enseignant) {
        String query = "update APIInfos set id_e=? where id_c=? and id_co=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, enseignant.getId());
            pstm.setInt(2, classe.getId());
            pstm.setInt(3, cours.getId());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return false;
        }
    }

    @Override
    public boolean modifCours(Classe classe, Cours cours, int h) {
        String query = "update APIInfos set nbheures=? where id_c=? and id_co=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, h);
            pstm.setInt(2, classe.getId());
            pstm.setInt(3, cours.getId());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return false;
        }
    }

    @Override
    public boolean supCours(Classe classe, Cours cours) {
        String query = "delete from APIInfos where id_c=? and id_co=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, classe.getId());
            pstm.setInt(2, cours.getId());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return false;
        }
    }
    @Override
    public int nbrHeuresTot(Classe classe) {
        String query = "{ ? = call APICalcul_heurestot(?) }";
        try (CallableStatement cstmt = dbConnect.prepareCall(query)) {
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setInt(2, classe.getId());

            cstmt.execute();
            return cstmt.getInt(1);
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
            return 0;
        }
    }



    @Override
    public boolean salleCapaciteOK(Classe classe, Salle salle) {
        String query = "{ ? = call APICapaciteSalleOK(?, ?) }";
        try (CallableStatement cstmt = dbConnect.prepareCall(query)) {
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setInt(2, classe.getId());
            cstmt.setInt(3, salle.getId());

            cstmt.execute();
            int result = cstmt.getInt(1);
            return result == 1; //true
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
            return false;
        }
    }


    /*

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "ORA44"."APIINFOSVIEW" ("ID_I", "NBHEURES", "ID_C", "ID_CO", "CODE", "INTITULE", "ID_E", "MATRICULE", "NOM", "PRENOM", "TEL", "CHARGESEM", "SALAIREMENSUEL", "DATEENGAGEMENT", "ID_S", "SIGLE", "CAPACITE") AS
  SELECT
    i.id_i AS id_i,
    i.nbheures AS nbheures,
    c.id_c AS id_c,
    co.id_co AS id_co,
    co.code AS code,
    co.intitule AS intitule,
    e.id_e AS id_e,
    e.matricule AS matricule,
    e.nom AS nom,
    e.prenom AS prenom,
    e.tel AS tel,
    e.chargeSem AS chargeSem,
    e.salaireMensuel AS salaireMensuel,
    e.dateEngagement AS dateEngagement,
    s.id_s AS id_s,
    s.sigle AS sigle,
    s.capacite AS capacite
FROM
    APIInfos i
JOIN
    APICLASSE c ON i.id_c = c.id_c
JOIN
    APICours co ON i.id_co = co.id_co
LEFT JOIN
    APIENSEIGNANT e ON i.id_e = e.id_e
LEFT JOIN
    APISALLE s ON i.id_s = s.id_s;

     */

    @Override
    public List<Infos> getCours(Classe classe) {
        String query = "select * from APIInfosView where id_c=?";
        List<Infos> ll = new ArrayList<>();
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, classe.getId());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id_s = rs.getInt("id_s");
                String sigle = rs.getString("sigle");
                int capacite = rs.getInt("capacite");

                int id_e = rs.getInt("id_e");
                String matricule = rs.getString("matricule");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String tel = rs.getString("tel");
                int chargeSem = rs.getInt("chargeSem");
                BigDecimal salaireMensuel = rs.getBigDecimal("salaireMensuel");
                LocalDate dateEngagement = rs.getDate("dateEngagement").toLocalDate();

                int id_co = rs.getInt("id_co");
                String code = rs.getString("code");
                String intitule = rs.getString("intitule");

                int id_c = rs.getInt("id_c");
                int nbheures = rs.getInt("nbheures");

                Salle s = new Salle(id_s, sigle, capacite);
                Enseignant e = new Enseignant(id_e, matricule, nom, prenom, tel, chargeSem, salaireMensuel, dateEngagement);
                Cours co = new Cours(id_co, code, intitule, s);
                Infos i = new Infos(nbheures, s, e, co, id_c);
                ll.add(i);
            }
            return ll;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return null;
        }
    }

    @Override
    public List<Infos> listeEnseignantsEtHeures(Classe classe) {
        String query = "select nom,prenom,nbheures from APIInfosView where id_c=?";
        return recherche(classe, query);

    }

    @Override
    public List<Infos> listeSallesEtHeures(Classe classe) {
        String query = "select sigle,nbheures from APIInfosView where id_c=?";
        return recherche(classe, query);

    }

    @Override
    public List<Infos> listeCoursEtHeures(Classe classe) {
        String query = "select code,intitule,nbheures from APIInfosView where id_c=?";
        return recherche(classe, query);

    }




    private List<Infos> recherche(Classe classe, String query) {
        List<Infos> ll = new ArrayList<>();
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, classe.getId());
            ResultSet rs = pstm.executeQuery();
            boolean trouve = false;
            while (rs.next()) {
                trouve = true;
                int id_s = rs.getInt("id_s");
                String sigle = rs.getString("sigle");
                int capacite = rs.getInt("capacite");

                int id_e = rs.getInt("id_e");
                String matricule = rs.getString("matricule");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String tel = rs.getString("tel");
                int chargeSem = rs.getInt("chargeSem");
                BigDecimal salaireMensuel = rs.getBigDecimal("salaireMensuel");
                LocalDate dateEngagement = rs.getDate("dateEngagement").toLocalDate();

                int id_co = rs.getInt("id_co");
                String code = rs.getString("code");
                String intitule = rs.getString("intitule");

                int id_c = rs.getInt("id_c");
                int nbheures = rs.getInt("nbheures");

                Salle s = new Salle(id_s, sigle, capacite);
                Enseignant e = new Enseignant(id_e, matricule, nom, prenom, tel, chargeSem, salaireMensuel, dateEngagement);
                Cours co = new Cours(id_co, code, intitule, s);
                Infos i = new Infos(nbheures, s, e, co, id_c);
                ll.add(i);


            }
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return null;
        }
        return ll;
    }

    @Override
    public List getNotification() {
        return getClasses();
    }
}
