package ecole.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Classe {
    protected int id;
    protected String sigle;
    protected int annee;
    protected String specialite;
    protected int nbreEleve;
    protected List<Cours> coursList;

    public Classe(int id, String sigle, int annee, String specialite, int nbreEleve, List<Cours> coursList) {
        this.id = id;
        this.sigle = sigle;
        this.annee = annee;
        this.specialite = specialite;
        this.nbreEleve = nbreEleve;
        this.coursList = coursList;
    }
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
            totalHeures += cours.getInfos().getNbHeures();
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
            String salleInfo = cours.getSalleParDefault().getSigle() + " - " + cours.getInfos().getNbHeures() + " heures";
            sallesEtHeuresList.add(salleInfo);
        }
        return sallesEtHeuresList;
    }

    public List<Cours> listeCoursEtHeures() {
        List<Cours> coursListWithHours = new ArrayList<>();
        for (Cours cours : coursList) {
            Infos infos = cours.getInfos();
            coursListWithHours.add(new Cours(cours.getId(), cours.getCode(), cours.getIntitule(), infos.getNbHeures()));
        }
        return coursListWithHours;
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
        Infos infos = cours.getInfos();
        infos.setNbHeures(heures);
        cours.setInfos(infos);
        coursList.add(cours);
    }

    public void modifCours(Cours cours, Salle salle) {
        cours.setSalleParDefault(salle);
    }

    public void modifCours(Cours cours, int heures) {
        Infos infos = cours.getInfos();
        infos.setNbHeures(heures);
        cours.setInfos(infos);
    }

    public void suppCours(Cours cours) {
        coursList.remove(cours);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classe classe = (Classe) o;
        return id == classe.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
