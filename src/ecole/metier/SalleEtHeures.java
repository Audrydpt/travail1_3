package ecole.metier;

/**
 * Représente l'association entre une salle et le nombre d'heures dans une classe
 */
public class SalleEtHeures {

    private Salle salle;
    private int heures;

    public SalleEtHeures(Salle salle, int heures) {
        this.salle = salle;
        this.heures = heures;
    }

    public Salle getSalle() {
        return salle;
    }

    public int getHeures() {
        return heures;
    }
    @Override
    public String toString() {
        return "Salle: " + salle.getSigle() + ", Heures: " + heures;
    }
}
