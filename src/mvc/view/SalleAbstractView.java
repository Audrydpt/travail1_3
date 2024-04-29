package mvc.view;

import ecole.metier.Salle;
import mvc.controller.SalleController;
import mvc.observer.Observer;

import java.util.List;

public abstract class SalleAbstractView implements Observer {
    protected SalleController salleController;
    protected List<Salle> lsa;

    public void setController(SalleController salleController){
        this.salleController = salleController;
    }

    public abstract void affMsg(String msg);

    public abstract Salle selectionner();

    public abstract void menu();

    public abstract void affList(List l);

    @Override
    public void update(List lsa) {
        this.lsa = lsa;
        affList(lsa);
    }
}
