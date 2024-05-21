package ecole.metier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * La classe Enseignant représente un enseignant de l'école
 *
 * @author Audry Dupont
 * @version 1.0
 * @see Infos
 */
public class Enseignant {
    /**
     * Identifiant unique de l'enseignant
     */
    protected int id;

    /**
     * Matricule de l'enseignant
     */
    protected String matricule;

    /**
     * Nom de l'enseignant
     */
    protected String nom;

    /**
     * Prénom de l'enseignant
     */
    protected String prenom;

    /**
     * Numéro de téléphone de l'enseignant
     */
    protected String tel;

    /**
     * Charge de travail par semaine de l'enseignant
     */
    protected int chargeSem;

    /**
     * Salaire mensuel de l'enseignant
     */
    protected BigDecimal salaireMensuel;

    /**
     * Date d'engagement de l'enseignant
     */
    protected LocalDate dateEngagement;

    /**
     * Constructeur de la classe Enseignant
     *
     * @param id             Identifiant unique de l'enseignant
     * @param matricule      Matricule de l'enseignant
     * @param nom            Nom de l'enseignant
     * @param prenom         Prénom de l'enseignant
     * @param tel            Numéro de téléphone de l'enseignant
     * @param chargeSem      Charge de travail par semaine de l'enseignant
     * @param salaireMensuel Salaire mensuel de l'enseignant
     * @param dateEngagement Date d'engagement de l'enseignant
     */

    public Enseignant(int id, String matricule, String nom, String prenom, String tel, int chargeSem, BigDecimal salaireMensuel, LocalDate dateEngagement) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.chargeSem = chargeSem;
        this.salaireMensuel = salaireMensuel;
        this.dateEngagement = dateEngagement;
    }

    public Enseignant(String matricule, String nom, String prenom, String telephone, int chargeSem, BigDecimal salaireMensuel, LocalDate dateEngagement) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = telephone;
        this.chargeSem = chargeSem;
        this.salaireMensuel = salaireMensuel;
        this.dateEngagement = dateEngagement;
    }

    /**
     * Obtient l'identifiant de l'enseignant
     *
     * @return L'identifiant de l'enseignant
     */
    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    /**
     * Obtient le matricule de l'enseignant
     *
     * @return Le matricule de l'enseignant
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * Modifie le matricule de l'enseignant
     *
     * @param matricule Le nouveau matricule de l'enseignant
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * Obtient le nom de l'enseignant
     *
     * @return Le nom de l'enseignant
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom de l'enseignant
     *
     * @param nom Le nouveau nom de l'enseignant
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient le prénom de l'enseignant
     *
     * @return Le prénom de l'enseignant
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Modifie le prénom de l'enseignant
     *
     * @param prenom Le nouveau prénom de l'enseignant
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Obtient le numéro de téléphone de l'enseignant
     *
     * @return Le numéro de téléphone de l'enseignant
     */
    public String getTel() {
        return tel;
    }

    /**
     * Modifie le numéro de téléphone de l'enseignant
     *
     * @param tel Le nouveau numéro de téléphone de l'enseignant
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * Obtient la charge de travail par semaine de l'enseignant
     *
     * @return La charge de travail par semaine de l'enseignant
     */
    public int getChargeSem() {
        return chargeSem;
    }

    /**
     * Modifie la charge de travail par semaine de l'enseignant
     *
     * @param chargeSem La nouvelle charge de travail par semaine de l'enseignant
     */
    public void setChargeSem(int chargeSem) {
        this.chargeSem = chargeSem;
    }

    /**
     * Obtient le salaire mensuel de l'enseignant
     *
     * @return Le salaire mensuel de l'enseignant
     */
    public BigDecimal getSalaireMensuel() {
        return salaireMensuel;
    }

    /**
     * Modifie le salaire mensuel de l'enseignant
     *
     * @param salaireMensuel Le nouveau salaire mensuel de l'enseignant
     */
    public void setSalaireMensuel(BigDecimal salaireMensuel) {
        this.salaireMensuel = salaireMensuel;
    }

    /**
     * Obtient la date d'engagement de l'enseignant
     *
     * @return La date d'engagement de l'enseignant
     */
    public LocalDate getDateEngagement() {
        return dateEngagement;
    }

    /**
     * Modifie la date d'engagement de l'enseignant
     *
     * @param dateEngagement La nouvelle date d'engagement de l'enseignant
     */
    public void setDateEngagement(LocalDate dateEngagement) {
        this.dateEngagement = dateEngagement;
    }

    /**
     * Compare cet enseignant à un autre objet
     *
     * @param o Objet à comparer.
     * @return true si les objets sont égaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enseignant that = (Enseignant) o;
        return id == that.id;
    }

    /**
     * Calcule le hascode
     *
     * @return Le code de hachage
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toString() {
        return "Enseignant{" +
                "id=" + id +
                ", matricule='" + matricule + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", tel='" + tel + '\'' +
                ", chargeSem=" + chargeSem +
                ", salaireMensuel=" + salaireMensuel +
                ", dateEngagement=" + dateEngagement +
                '}';
    }
}
