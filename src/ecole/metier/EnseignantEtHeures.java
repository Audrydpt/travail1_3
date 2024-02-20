package ecole.metier;

/**
 * Représente l'association entre un enseignant et le nombre d'heures dans une classe
 */
public class EnseignantEtHeures {

    private Enseignant enseignant;
    private int heures;

    public EnseignantEtHeures(Enseignant enseignant, int heures) {
        this.enseignant = enseignant;
        this.heures = heures;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public int getHeures() {
        return heures;
    }

    @Override
    public String toString() {
        return "Enseignant: " + enseignant.getNom() + ", Heures: " + heures;
    }

}
