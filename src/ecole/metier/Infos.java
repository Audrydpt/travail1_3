package ecole.metier;
import java.util.Objects;

class Infos {
    protected int id;
    protected Enseignant enseignant;
    protected Salle salle;
    protected int nbHeures;

    public int getId() {
        return id;
    }
    public Infos() {
        this.nbHeures = 0;
    }

    public int getNbHeures() {
        return nbHeures;
    }

    public void setNbHeures(int nbHeures) {
        this.nbHeures = nbHeures;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Infos infos = (Infos) o;
        return id == infos.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
