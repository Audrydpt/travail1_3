package mvc.model;

import mvc.observer.Subject;
import ecole.metier.Classe;

import java.util.List;

public abstract class   DAOClasse extends Subject {
    public abstract Classe addClasse(Classe classe);

    public abstract boolean removeClasse(Classe classe);

    public abstract Classe updateClasse(Classe classe);

    public abstract Classe readClasse(int idClasse);

    public abstract List<Classe> getClasses();

}
