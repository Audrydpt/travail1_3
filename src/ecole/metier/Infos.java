package ecole.metier;

import java.util.Objects;

/**
 * La classe Infos représente les informations générales
 * Chaque instance d'Infos est caractérisée par un identifiant unique, un enseignant,
 * une salle et le nombre d'heures attribuées.
 *
 * @author Audry Dupont
 * @version 1.0
 * @see Enseignant
 * @see Salle
 * @see Classe
 * @see Cours
 */
class Infos {
    /**
     * Identifiant unique de l'ensemble d'informations
     */
    protected int id;

    /**
     * Enseignant associé aux informations
     */
    protected Enseignant enseignant;

    /**
     * Salle associée aux informations
     */
    protected Salle salle;

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
        * @param nbHeures   Nombre d'heures attribuées
        */

    public Infos(int id, Enseignant enseignant, Salle salle, int nbHeures) {
        this.id = id;
        this.enseignant = enseignant;
        this.salle = salle;
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


}
