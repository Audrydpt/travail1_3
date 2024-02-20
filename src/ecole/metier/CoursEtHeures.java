package ecole.metier;

/**
 * Représente l'association entre un cours et le nombre d'heures dans une classe
 */
public class CoursEtHeures {

    private Cours cours;
    private int heures;

    public CoursEtHeures(Cours cours, int heures) {
        this.cours = cours;
        this.heures = heures;
    }

    public Cours getCours() {
        return cours;
    }

    public int getHeures() {
        return heures;
    }

    public void setHeures(int heures) {
        this.heures = heures;
    }

}
