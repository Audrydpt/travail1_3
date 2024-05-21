package ecole.metier;

/**
 * Représente l'association entre une salle et le nombre d'heures dans une classe
 *
 * @author Audry Dupont
 * @version 1.0
 * @see Salle
 */
public class SalleEtHeures {

    /**
     * La salle
     */
    private Salle salle;

    /**
     * Le nombre d'heures
     */
    private int heures;

    /**
     * Constructeur de l'association entre une salle et le nombre d'heures
     *
     * @param salle  La salle
     * @param heures Le nombre d'heures
     */
    public SalleEtHeures(Salle salle, int heures) {
        this.salle = salle;
        this.heures = heures;
    }

    /**
     * Récupère la salle
     *
     * @return La salle
     */
    public Salle getSalle() {
        return salle;
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
     * Représentation textuelle des informations
     *
     * @return La représentation textuelle
     */
    @Override
    public String toString() {
        return "Salle : " + salle.getSigle() + " - " + heures + " heures";
    }

}
