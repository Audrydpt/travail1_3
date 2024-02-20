package ecole.metier;

import java.util.Objects;

/**
 * Représente une salle dans une école avec ses caractéristiques
 *
 * @author Audry Dupont
 * @version 1.0
 * @see Infos
 * @see Cours
 */
public class Salle {

    /**
     * Identifiant numérique de la salle
     */
    protected int id;

    /**
     * Sigle de la salle
     */
    protected String sigle;

    /**
     * Capacité d'accueil de la salle
     */
    protected int capacite;

    /**
     * Constructeur de la salle avec un sigle et une capacité
     *
     * @param sigle    Le sigle de la salle
     * @param capacite La capacité d'accueil de la salle
     */
    public Salle(String sigle, int capacite) {
        this.sigle = sigle;
        this.capacite = capacite;
    }


    public Salle(int id, String sigle, int capacite) {
        this.id = id;
        this.sigle = sigle;
        this.capacite = capacite;
    }

    /**
     * Récupère le sigle de la salle
     *
     * @return Le sigle de la salle
     */
    public String getSigle() {
        return sigle;
    }

    /**
     * Modifie le sigle de la salle
     *
     * @param sigle Le nouveau sigle de la salle
     */
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    /**
     * Récupère la capacité d'accueil de la salle
     *
     * @return La capacité d'accueil de la salle
     */
    public int getCapacite() {
        return capacite;
    }

    /**
     * Modifie la capacité d'accueil de la salle
     *
     * @param capacite La nouvelle capacité d'accueil de la salle
     */
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    /**
     * Récupère l'identifiant numérique de la salle
     *
     * @return L'identifiant numérique de la salle
     */
    public int getId() {
        return id;
    }

    /**
     * Compare cette salle à un autre objet pour l'égalité
     *
     * @param o L'objet à comparer
     * @return true si les objets sont égaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salle salle = (Salle) o;
        return id == salle.id;
    }

    /**
     * Calcule le hashcode
     *
     * @return Le code de hachage
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
