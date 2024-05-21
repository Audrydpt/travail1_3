package mvc.view;

import mvc.controller.CoursController;
import mvc.observer.Observer;
import ecole.metier.Cours;

import java.util.List;

public abstract class CoursAbstractView implements Observer {

    protected CoursController coursController;
    protected SalleAbstractView sav;
    protected CoursAbstractView cav;
    protected List<Cours> lco;

    public void setController(CoursController coursController) {
        this.coursController = coursController;
    }

    public void setCoursView(CoursAbstractView cav) {
        this.cav = cav;
    }

    public void setSalleView(SalleAbstractView sav) {
        this.sav = sav;
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
