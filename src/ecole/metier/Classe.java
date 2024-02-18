package ecole.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Représente une classe dans une école avec ses propriétés
 *
 * @author Audry Dupont
 * @version 1.0
 * @see Cours
 * @see Enseignant
 * @see Salle
 */
public class Classe {

    /**
     * Identifiant numérique de la classe
     */
    protected int id;

    /**
     * Sigle de la classe
     */
    protected String sigle;

    /**
     * Année de la classe
     */
    protected int annee;

    /**
     * Spécialité de la classe
     */
    protected String specialite;

    /**
     * Nombre d'élèves dans la classe
     */
    protected int nbreEleve;

    /**
     * Liste des cours dispensés dans la classe
     */
    protected List<Cours> coursList;

    /**
     * Constructeur de la classe avec l'identifiant
     *
     * @param id          Identifiant numérique de la classe
     * @param sigle       Sigle de la classe
     * @param annee       Année de la classe
     * @param specialite  Spécialité de la classe
     * @param nbreEleve   Nombre d'élèves dans la classe
     * @param coursList   Liste des cours dispensés dans la classe
     */
    public Classe(int id, String sigle, int annee, String specialite, int nbreEleve, List<Cours> coursList) {
        this.id = id;
        this.sigle = sigle;
        this.annee = annee;
        this.specialite = specialite;
        this.nbreEleve = nbreEleve;
        this.coursList = coursList;
    }

    /**
     * Constructeur de la classe sans identifiant (à utiliser lors de la création de nouvelles classes)
     *
     * @param sigle       Sigle de la classe
     * @param annee       Année de la classe
     * @param specialite  Spécialité de la classe
     * @param nbreEleve   Nombre d'élèves dans la classe
     * @param coursList   Liste des cours dispensés dans la classe
     */
    public Classe(String sigle, int annee, String specialite, int nbreEleve, List<Cours> coursList) {
        this.sigle = sigle;
        this.annee = annee;
        this.specialite = specialite;
        this.nbreEleve = nbreEleve;
        this.coursList = coursList;
    }

    /**
     * Calcule le nombre total d'heures de tous les cours dans la classe
     *
     * @return Le nombre total d'heures
     */
    public int nbrHeuresTot() {
        int totalHeures = 0;
        for (Cours cours : coursList) {
            totalHeures += cours.getInfos().getNbHeures();
        }
        return totalHeures;
    }

    /**
     * Récupère la liste des enseignants et de leurs heures dans la classe
     *
     * @return Liste des enseignants et de leurs heures
     */
    public List<Enseignant> listeEnseignantHeures() {
        List<Enseignant> enseignantList = new ArrayList<>();
        for (Cours cours : coursList) {
            enseignantList.add(cours.getEnseignant());
        }
        return enseignantList;
    }

    /**
     * Récupère la liste des salles et de leurs heures dans la classe
     *
     * @return Liste des salles et de leurs heures
     */
    public List<String> listeSallesetHeures() {
        List<String> sallesEtHeuresList = new ArrayList<>();
        for (Cours cours : coursList) {
            String salleInfo = cours.getSalleParDefault().getSigle() + " - " + cours.getInfos().getNbHeures() + " heures";
            sallesEtHeuresList.add(salleInfo);
        }
        return sallesEtHeuresList;
    }

    /**
     * Récupère la liste des cours et de leurs heures dans la classe
     *
     * @return Liste des cours et de leurs heures
     */
    public List<Cours> listeCoursEtHeures() {
        List<Cours> coursListWithHours = new ArrayList<>();
        for (Cours cours : coursList) {
            Infos infos = cours.getInfos();
            coursListWithHours.add(new Cours(cours.getId(), cours.getCode(), cours.getIntitule(), infos.getNbHeures()));
        }
        return coursListWithHours;
    }

    /**
     * Vérifie si la capacité d'une salle est suffisante pour la classe
     *
     * @param salle La salle à vérifier
     * @return true si la capacité est suffisante, false sinon
     */
    public boolean salleCapaciteOK(Salle salle) {
        int capaciteTotale = 0;
        for (Cours cours : coursList) {
            if (cours.getSalleParDefault().equals(salle)) {
                capaciteTotale += salle.getCapacite();
            }
        }
        return capaciteTotale >= nbreEleve;
    }

    /**
     * Ajoute un cours à la classe avec le nombre d'heures spécifié
     *
     * @param cours  Le cours à ajouter
     * @param heures Le nombre d'heures pour ce cours
     */
    public void addCours(Cours cours, int heures) {
        Infos infos = cours.getInfos();
        infos.setNbHeures(heures);
        cours.setInfos(infos);
        coursList.add(cours);
    }

    /**
     * Modifie la salle par défaut d'un cours dans la classe
     *
     * @param cours Le cours à modifier
     * @param salle La nouvelle salle par défaut
     */
    public void modifCours(Cours cours, Salle salle) {
        coursList.remove(cours);
        cours.setSalleParDefault(salle);
        coursList.add(cours);
    }


    /**
     * Modifie le nombre d'heures d'un cours dans la classe
     *
     * @param cours  Le cours à modifier
     * @param heures Le nouveau nombre d'heures
     */
    public void modifCours(Cours cours, int heures) {
        Infos infos = cours.getInfos();
        infos.setNbHeures(heures);
        cours.setInfos(infos);
    }


    /**
     * Supprime un cours de la classe
     *
     * @param cours Le cours à supprimer
     */
    public void suppCours(Cours cours) {
        coursList.remove(cours);
    }

    /**
     * Compare cette classe à un autre objet
     *
     * @param o L'objet à comparer
     * @return true si les objets sont égaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classe classe = (Classe) o;
        return id == classe.id;
    }

    /**
     * calcul du hashcode
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
