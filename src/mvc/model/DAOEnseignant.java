package mvc.model;


import mvc.observer.Subject;
import ecole.metier.Enseignant;

import java.util.List;

public abstract class DAOEnseignant extends Subject {
    public abstract Enseignant addEnseignant(Enseignant enseignant);

    public abstract boolean removeEnseignant(Enseignant enseignant);

    public abstract Enseignant updateEnseignant(Enseignant enseignant);

    public abstract Enseignant readEnseignant(int idEnseignant);

    public abstract List<Enseignant> getEnseignants();

}
