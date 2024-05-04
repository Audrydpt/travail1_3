package magasin.mvc_test.view;

import magasin.metier.ComFact;
import magasin.mvc_test.controller.ComfactController;
import magasin.mvc_test.observer.Observer;

import java.util.List;

public abstract class ComfactAbstractView implements Observer {
    protected ComfactController comfactController;

    protected ClientAbstractView clv;

    protected ProduitAbstractView pv;
    protected List<ComFact> lcf;

    public void setController(ComfactController comfactController) {
        this.comfactController = comfactController;
    }

    ;

    public void setClientView(ClientAbstractView clv) {
        this.clv = clv;
    }

    public void setProduitView(ProduitAbstractView pv) {
        this.pv = pv;
    }

    public abstract void affMsg(String msg);

    public abstract void menu();

    public abstract void affList(List l);

    @Override
    public void update(List lcf) {
        this.lcf = lcf;
        affList(lcf);
    }


}
