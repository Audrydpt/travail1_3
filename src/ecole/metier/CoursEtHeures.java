package ecole.metier;

/**
 * Représente l'association entre un cours et le nombre d'heures dans une classe
 *
 * @author Audry Dupont
 * @version 1.0
 * @see Cours
 */
public class CoursEtHeures {

    /**
     * Le cours
     */
    private Cours cours;

    /**
     * Le nombre d'heures
     */
    private int heures;

    /**
     * Constructeur de l'association entre un cours et le nombre d'heures
     *
     * @param cours  Le cours
     * @param heures Le nombre d'heures
     */

    public CoursEtHeures(Cours cours, int heures) {
        this.cours = cours;
        this.heures = heures;
    }

    /**
     * Récupère le cours
     *
     * @return Le cours
     */
    public Cours getCours() {
        return cours;
    }

    /**
     * Récupère le nombre d'heures
     *
     * @return Le nombre d'heures
     */
    public int getHeures() {
        return heures;
    }

    /**
     * Modifie le nombre d'heures
     *
     * @param heures Le nouveau nombre d'heures
     */
    public void setHeures(int heures) {
        this.heures = heures;
    }

    /**
     * Représentation textuelle des informations
     *
     * @return La représentation textuelle
     */
    @Override
    public String toString() {
        return "Cours : " + cours.getIntitule() + " - " + heures + " heures" + "\n";
    }

}
