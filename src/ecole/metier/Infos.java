package ecole.metier;

import java.util.Objects;

/**
 * La classe Infos représente les informations générales d'un cours dans l'école
 *
 * @author Audry Dupont
 * @version 1.0
 * @see Enseignant
 * @see Salle
 * @see Classe
 */
public class Infos {
    /**
     * Identifiant unique de l'ensemble d'informations
     */
    protected int id;
    /**
     * Compteur d'identifiant pour les informations
     */
    protected static int idcpt = 1;

    /**
     * Enseignant associé aux informations
     */
    protected Enseignant enseignant;

    /**
     * Salle associée aux informations
     */
    protected Salle salle;


    /**
     * Cours associé aux informations
     */
    protected Cours cours;

    /**
     * Nombre d'heures attribuées
     */
    protected int nbHeures;


    /**
     * Constructeur de la classe Infos
     *
     * @param id         Identifiant unique de l'ensemble d'informations
     * @param enseignant Enseignant associé aux informations
     * @param salle      Salle associée aux informations
     * @param cours      Cours associé aux informations
     * @param nbHeures   Nombre d'heures attribuées
     */

    public Infos(int id, Enseignant enseignant, Salle salle, Cours cours, int nbHeures) {
        this.id = idcpt++;
        this.enseignant = enseignant;
        this.salle = salle;
        this.cours = cours;
        this.nbHeures = nbHeures;
    }

    /**
     * Obtient l'identifiant unique des informations
     *
     * @return L'identifiant unique
     */
    public int getId() {
        return id;
    }

    public static int getIdcpt() {
        return idcpt;
    }

    /**
     * Obtient le nombre d'heures attribuées
     *
     * @return Le nombre d'heures attribuées
     */
    public int getNbHeures() {
        return nbHeures;
    }

    /**
     * Modifie le nombre d'heures attribuées
     *
     * @param nbHeures Le nouveau nombre d'heures attribuées
     */
    public void setNbHeures(int nbHeures) {
        this.nbHeures = nbHeures;
    }

    /**
     * Obtient l'enseignant associé aux informations
     *
     * @return L'enseignant associé
     */
    public Enseignant getEnseignant() {
        return enseignant;
    }

    /**
     * Modifie l'enseignant associé aux informations
     *
     * @param enseignant Le nouvel enseignant associé
     */
    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    /**
     * Obtient la salle associée aux informations.
     *
     * @return La salle associée.
     */
    public Salle getSalle() {
        return salle;
    }

    /**
     * Modifie la salle associée aux informations
     *
     * @param salle La nouvelle salle associée
     */
    public void setSalle(Salle salle) {
        this.salle = salle;
    }


    /**
     * Obtient le cours associé aux informations
     *
     * @return Le cours associé
     */
    public Cours getCours() {
        return cours;
    }

    /**
     * Modifie le cours associé aux informations
     * @param cours
     */
    public void setCours(Cours cours) {
        this.cours = cours;
    }



    /**
     * Compare ces informations à un autre objet
     *
     * @param o Objet à comparer
     * @return true si les objets sont égaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Infos infos = (Infos) o;
        return id == infos.id;
    }

    /**
     * Calcule le hashcode
     *
     * @return Le hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Représentation textuelle des informations
     *
     * @return La représentation textuelle
     */
    @Override
    public String toString() {
        String enseignantNom = (enseignant != null) ? enseignant.getNom() : "Aucun enseignant";

        return "\t Infos : " + id + " - " + enseignantNom + " - " + ((salle != null) ? salle.getSigle() : "Aucune salle")
                + " - " + ((cours != null) ? cours.getIntitule() : "Aucun cours") + " - " + nbHeures + " heures \n";
        //affichage généré par chatgpt
    }



















}
