package ecole.metier;

import java.util.Objects;

/**
 * La classe Cours représente un cours dans l'école
 * Chaque cours est caractérisé par un identifiant unique, un code, un intitulé,
 * une salle par défaut et des informations spécifiques
 *
 * @author Audry Dupont
 * @version 1.0
 * @see Infos
 * @see Classe
 * @see Salle
 */
public class Cours {
    /**
     * Identifiant unique du cours
     */
    protected int id;

    /**
     * Code du cours
     */
    protected String code;

    /**
     * Intitulé du cours
     */
    protected String intitule;

    /**
     * Salle par défaut du cours
     */
    protected Salle salleParDefault;


    /**
     * Constructeur de la classe Cours avec un identifiant
     *
     * @param id       Identifiant du cours
     * @param code     Code du cours
     * @param intitule Intitulé du cours
     */
    public Cours(int id, String code, String intitule) {
        this.id = id;
        this.code = code;
        this.intitule = intitule;
    }

    public Cours(int id, String code, String intitule, Salle salleParDefault) {
        this.id = id;
        this.code = code;
        this.intitule = intitule;
        this.salleParDefault = salleParDefault;
    }

    /**
     * Obtient l'identifiant du cours
     *
     * @return L'identifiant du cours
     */
    public int getId() {
        return id;
    }

    /**
     * Obtient le code du cours
     *
     * @return Le code du cours
     */
    public String getCode() {
        return code;
    }

    /**
     * Obtient l'intitulé du cours
     *
     * @return L'intitulé du cours
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * Obtient la salle par défaut du cours
     *
     * @return La salle par défaut du cours
     */
    public Salle getSalleParDefault() {
        return salleParDefault;
    }

    /**
     * Modifie la salle par défaut du cours
     *
     * @param salleParDefault La nouvelle salle par défaut du cours
     */
    public void setSalleParDefault(Salle salleParDefault) {
        this.salleParDefault = salleParDefault;
    }

    /**
     * Compare ce cours à un autre objet
     *
     * @param o Objet à comparer
     * @return true si les objets sont égaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cours cours = (Cours) o;
        return id == cours.id;
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
