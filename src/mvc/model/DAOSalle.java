package mvc.model;

import ecole.metier.Salle;
import mvc.observer.Subject;

import java.util.List;

public abstract class DAOSalle extends Subject {
    public abstract Salle addSalle(Salle salle);

    public abstract boolean removeSalle(Salle salle);

    public abstract Salle updateSalle(Salle salle);

    public abstract Salle readSalle(int idSalle);

    public abstract List<Salle> getSalles();

}
