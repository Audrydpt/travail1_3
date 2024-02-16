package ecole.metier;

import java.util.ArrayList;
import java.util.List;

public class Classe {
    private String sigle;
    private int annee;
    private String specialite;
    private int nbreEleve;
    private List<Cours> coursList;

    public Classe(String sigle, int annee, String specialite, int nbreEleve, List<Cours> coursList) {
        this.sigle = sigle;
        this.annee = annee;
        this.specialite = specialite;
        this.nbreEleve = nbreEleve;
        this.coursList = coursList;
    }

    public int nbrHeuresTot() {
        int totalHeures = 0;
        for (Cours cours : coursList) {
            totalHeures += cours.getNbHeures();
        }
        return totalHeures;
    }

    public List<Enseignant> listeEnseignantHeures() {
        List<Enseignant> enseignantList = new ArrayList<>();
        for (Cours cours : coursList) {
            enseignantList.add(cours.getEnseignant());
        }
        return enseignantList;
    }

    public List<String> listeSallesetHeures() {
        List<String> sallesEtHeuresList = new ArrayList<>();
        for (Cours cours : coursList) {
            String salleInfo = cours.getSalleParDefault().getSigle() + " - " + cours.getNbHeures() + " heures";
            sallesEtHeuresList.add(salleInfo);
        }
        return sallesEtHeuresList;
    }



    public List<CoursEtHeures> listeCoursEtHeures() {
        List<CoursEtHeures> coursEtHeuresList = new ArrayList<>();
        for (Cours cours : coursList) {
            coursEtHeuresList.add(new CoursEtHeures(cours, cours.getNbHeures()));
        }
        return coursEtHeuresList;
    }

    public boolean salleCapaciteOK(Salle salle) {
        int capaciteTotale = 0;
        for (Cours cours : coursList) {
            if (cours.getSalleParDefault().equals(salle)) {
                capaciteTotale += salle.getCapacite();
            }
        }
        return capaciteTotale >= nbreEleve;
    }

    public void addCours(Cours cours, int heures) {
        cours.setNbHeures(heures);
        coursList.add(cours);
    }

    public void modifCours(Cours cours, Salle salle) {
        cours.setSalleParDefault(salle);
    }

    public void modifCours(Cours cours, int heures) {
        cours.setNbHeures(heures);
    }

    public void suppCours(Cours cours) {
        coursList.remove(cours);
    }
}
