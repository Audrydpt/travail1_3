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
 * @see Infos
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
    protected List<CoursEtHeures> coursEtHeuresList = new ArrayList<>();
    protected List<EnseignantEtHeures> enseignantEtHeuresList = new ArrayList<>();
    protected List<SalleEtHeures> salleEtHeuresList = new ArrayList<>();
    protected List<Infos> infoList=new ArrayList<>();


    /**
     * Constructeur de la classe avec l'identifiant
     *
     * @param id          Identifiant numérique de la classe
     * @param sigle       Sigle de la classe
     * @param annee       Année de la classe
     * @param specialite  Spécialité de la classe
     * @param nbreEleve   Nombre d'élèves dans la classe
     */
    public Classe(int id, String sigle, int annee, String specialite, int nbreEleve) {
        this.id = id;
        this.sigle = sigle;
        this.annee = annee;
        this.specialite = specialite;
        this.nbreEleve = nbreEleve;
    }



    public List<Infos> getInfo() {
        return infoList;
    }

    public int nbrHeuresTot() {
        int totalHeures = 0;
        for (CoursEtHeures coursEtHeures : coursEtHeuresList ) {
            totalHeures += coursEtHeures.getHeures();
        }
        return totalHeures;
    }

    /**
     * Récupère la liste des cours et de leurs heures dans la classe
     *
     * @return Liste des cours et de leurs heures
     */
    public List<CoursEtHeures> listeCoursEtHeures() {
        return coursEtHeuresList;
    }

    /**
     * Récupère la liste des enseignants et de leurs heures dans la classe
     *
     * @return Liste des enseignants et de leurs heures
     */
    public List<EnseignantEtHeures> listeEnseignantHeures() {
        return enseignantEtHeuresList;
    }

    /**
     * Récupère la liste des salles et de leurs heures dans la classe
     *
     * @return Liste des salles et de leurs heures
     */
    public List<SalleEtHeures> listeSallesEtHeures() {
        return salleEtHeuresList;
    }

    public List<Infos> listeInfos() {
        return infoList;
    }

    /**
     * Vérifie si la capacité d'une salle est suffisante pour la classe
     *
     * @param salle La salle à vérifier
     * @return true si la capacité est suffisante, false sinon
     */
    public boolean salleCapaciteOK(Salle salle) {
        int capaciteTotale = 0;
        for (SalleEtHeures salleEtHeures : salleEtHeuresList) {
            capaciteTotale += salleEtHeures.getSalle().getCapacite() * salleEtHeures.getHeures();
        }
        capaciteTotale += salle.getCapacite();
        return capaciteTotale >= nbreEleve;
    }


    /**
     * Ajoute un cours à la classe avec le nombre d'heures spécifié
     *
     * @param cours  Le cours à ajouter
     * @param heures Le nombre d'heures pour ce cours
     */
    public void addCours(Cours cours, int heures) {
        CoursEtHeures coursEtHeures = new CoursEtHeures(cours, heures);
        coursEtHeuresList.add(coursEtHeures);
    }

    /**
     * Modifie la salle par défaut d'un cours dans la classe
     *
     * @param cours Le cours à modifier
     * @param salle La nouvelle salle par défaut
     */
    public void modifCours(Cours cours, Salle salle) {
        for (CoursEtHeures coursEtHeures : coursEtHeuresList) {
            if (coursEtHeures.getCours().equals(cours)) {
                coursEtHeures.getCours().setSalleParDefault(salle);
            }
        }
    }

    /**
     * Modifie le nombre d'heures d'un cours dans la classe
     *
     * @param cours  Le cours à modifier
     * @param heures Le nouveau nombre d'heures
     */
    public void modifCours(Cours cours, int heures) {
        for (CoursEtHeures coursEtHeures : coursEtHeuresList) {
            if (coursEtHeures.getCours().equals(cours)) {
                coursEtHeures.setHeures(heures);
            }
        }
    }

    /**
     * Supprime un cours de la classe
     *
     * @param cours Le cours à supprimer
     */
    public void suppCours(Cours cours) {
        coursEtHeuresList.removeIf(coursEtHeures -> coursEtHeures.getCours().equals(cours));
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
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
