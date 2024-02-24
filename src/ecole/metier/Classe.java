package ecole.metier;

import java.util.*;

/**
 * Représente une classe dans une école avec ses propriétés
 *
 * @author Audry Dupont
 * @version 1.0
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

    /**
     * Liste des informations générales de la classe
     */
    protected List<Infos> infoList = new ArrayList<>();

    /**
     * Constructeur de la classe avec l'identifiant
     *
     * @param id         Identifiant numérique de la classe
     * @param sigle      Sigle de la classe
     * @param annee      Année de la classe
     * @param specialite Spécialité de la classe
     * @param nbreEleve  Nombre d'élèves dans la classe
     */
    public Classe(int id, String sigle, int annee, String specialite, int nbreEleve) {
        this.id = id;
        this.sigle = sigle;
        this.annee = annee;
        this.specialite = specialite;
        this.nbreEleve = nbreEleve;
    }

    /**
     * Obtient Info de la classe
     *
     * @return Info de la classe
     */
    public List<Infos> getInfo() {
        return infoList;
    }

    /**
     * Obtient le nombre d'heures totales de la classe
     *
     * @return Nombre d'heures totales
     */
    public int nbrHeuresTot() {
        int totalHeures = 0;
        for (Infos infos : infoList) {
            totalHeures += infos.getNbHeures();
        }
        return totalHeures;
    }

    /**
     * Récupère la liste des cours et de leurs heures dans la classe
     *
     * @return Liste des cours et de leurs heures
     */
    public List<CoursEtHeures> listeCoursEtHeures() {
        List<CoursEtHeures> coursEtHeuresList = new ArrayList<>();

        for (Infos infos : infoList) {
            Cours cours = infos.getCours();
            if (cours != null) {
                CoursEtHeures coursEtHeures = new CoursEtHeures(cours, infos.getNbHeures());
                coursEtHeuresList.add(coursEtHeures);
            }
        }

        return coursEtHeuresList;
    }

    /**
     * Récupère la liste des enseignants et de leurs heures dans la classe
     *
     * @return Liste des enseignants et de leurs heures
     */
    public Map<Enseignant, Integer> listeEnseignantHeures() {
        Map<Enseignant, Integer> enseignantHeuresMap = new HashMap<>();

        for (Infos infos : infoList) {
            Enseignant enseignant = infos.getEnseignant();
            if (enseignant != null) {
                enseignantHeuresMap.put(enseignant, enseignantHeuresMap.getOrDefault(enseignant, 0) + infos.getNbHeures());
            }
        }

        return enseignantHeuresMap;

        //recherhe sur internet pour touver moyen d'empecher "doublons" dans les listes, donc utilisation de Map au lieu de ArrayList
        //mais si pas autorisé ou problème que je ne connais pas, on peut utiliser ArrayList
    }

    /**
     * Récupère la liste des salles et de leurs heures dans la classe
     *
     * @return Liste des salles et de leurs heures
     */
    public Map<Salle, Integer> listeSallesEtHeures() {
        Map<Salle, Integer> salleHeuresMap = new HashMap<>();

        for (Infos infos : infoList) {
            Salle salle = infos.getSalle();
            if (salle != null) {
                salleHeuresMap.put(salle, salleHeuresMap.getOrDefault(salle, 0) + infos.getNbHeures());
            }
        }

        return salleHeuresMap;

        //recherhe sur internet pour touver moyen d'empecher "doublons" dans les listes, donc utilisation de Map au lieu de ArrayList
        //mais si pas autorisé ou que je ne connais pas, on peut utiliser ArrayList

    }

    /**
     * Récupère la liste des informations de la classe
     *
     * @return Liste des informations
     */
    public List<Infos> listeInfos() {
        return infoList;
    }


    /**
     * Vérifie si la capacité d'une salle est suffisante pour la classe
     *
     * @param salle La salle à vérifier
     * @return true si la capacité est suffisante, false si insuffisante
     */
    public boolean salleCapaciteOK(Salle salle) {
        int capacite = salle.getCapacite();
        return capacite >= nbreEleve;
    }

    /**
     * Ajoute un cours à la classe avec le nombre d'heures spécifié
     *
     * @param cours  Le cours à ajouter
     * @param heures Le nombre d'heures pour ce cours
     */
    public void addCours(Cours cours, int heures) {
        Infos infos = new Infos(Infos.getIdcpt(), null, null, cours, heures);
        infoList.add(infos);
    }

    /**
     * Modifie la salle par défaut d'un cours dans la classe
     *
     * @param cours Le cours à modifier
     * @param salle La nouvelle salle par défaut
     */
    public void modifCours(Cours cours, Salle salle) {
        for (Infos infos : infoList) {
            if (infos.getCours().equals(cours)) {
                infos.setSalle(salle);
            }
        }
    }

    /**
     * Modifie l'enseignant d'un cours dans la classe
     *
     * @param cours      Le cours à modifier
     * @param enseignant Le nouvel enseignant
     */
    public void modifCours(Cours cours, Enseignant enseignant) {
        for (Infos infos : infoList) {
            if (infos.getCours().equals(cours)) {
                infos.setEnseignant(enseignant);
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
        for (Infos infos : infoList) {
            if (infos.getCours().equals(cours)) {
                infos.setNbHeures(heures);
            }
        }
    }

    /**
     * Supprime un cours de la classe
     *
     * @param cours Le cours à supprimer
     */
    public void supprCours(Cours cours) {
        Iterator<Infos> iterator = infoList.iterator();
        while (iterator.hasNext()) {
            Infos infos = iterator.next();
            if (infos.getCours().equals(cours)) {
                iterator.remove();
                break;
            }
        }
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
