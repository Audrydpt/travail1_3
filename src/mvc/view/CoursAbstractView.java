package mvc.view;

import mvc.controller.CoursController;
import mvc.observer.Observer;
import ecole.metier.Cours;

import java.util.List;

public abstract class CoursAbstractView implements Observer {

    protected CoursController coursController;
    protected List<Cours> lco;

    public void setController(CoursController coursController){
        this.coursController = coursController;
    }

    public abstract void affMsg(String msg);

    public abstract Cours selectionner();

    public abstract void menu();

    public abstract void affList(List l);

    @Override
    public void update(List lco) {
        this.lco = lco;
        affList(lco);
    }

}
