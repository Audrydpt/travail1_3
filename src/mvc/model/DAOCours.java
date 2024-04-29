package mvc.model;

import ecole.metier.Cours;
import mvc.observer.Subject;

import java.util.List;

public abstract class DAOCours extends Subject{
    public abstract Cours addCours(Cours cours);

    public abstract boolean removeCours(Cours cours);

    public abstract Cours updateCours(Cours cours);

    public abstract Cours readCours(int idCours);

    public abstract List<Cours> getCours();



}
