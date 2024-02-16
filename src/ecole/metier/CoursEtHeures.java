package ecole.metier;

class CoursEtHeures {
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
}
