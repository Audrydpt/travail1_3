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
            System.err.println("erreur sql :" + e);
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
            //System.err.println("erreur sql :"+e);
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


    @Override
    public int nbrHeuresTot(Classe classe) {
        String query = "select nbheures from APIInfos where id_c=?";
        int total = 0;
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, classe.getId());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                total += rs.getInt("nbheures");
            }
            return total;
        } catch (SQLException e) {
            //System.err.println("erreur sql :"+e);
            return 0;
        }

    }

    @Override
    public boolean salleCapaciteOK(Classe classe, Salle salle) {
        int nbreleve = classe.getNbreEleve();
        int capacite = salle.getCapacite();
        if (nbreleve <= capacite) return true;
        else return false;
    }

    private List<Infos> recherche(Classe classe, String query) {
        List<Infos> ll = new ArrayList<>();
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, classe.getId());
            ResultSet rs = pstm.executeQuery();
            boolean trouve = false;
            while (rs.next()) {
                trouve = true;
                int id_i = rs.getInt(1);
                int nbh = rs.getInt(2);
                int id_c = rs.getInt(3);
                int id_co = rs.getInt(4);
                String code = rs.getString(5);
                String intitule = rs.getString(6);
                int id_e = rs.getInt(7);
                String matricule = rs.getString(8);
                String nom = rs.getString(9);
                String prenom = rs.getString(10);
                String tel = rs.getString(11);
                int chargeSem = rs.getInt(12);
                BigDecimal salaireMensuel = rs.getBigDecimal(13);
                LocalDate dateEngagement = rs.getDate(14).toLocalDate();
                int id_s = rs.getInt(15);
                String sigle = rs.getString(16);
                int capacite = rs.getInt(17);

            }
        } catch (SQLException e) {
            //System.err.println("erreur sql :"+e);
            return null;
        }
        return ll;
    }

    @Override
    public List getNotification() {
        return getClasses();
    }
}
