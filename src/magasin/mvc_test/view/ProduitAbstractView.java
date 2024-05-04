package magasin.mvc_test.view;

import magasin.metier.Produit;
import magasin.mvc_test.controller.ProduitController;
import magasin.mvc_test.observer.Observer;

import java.util.List;

public abstract class ProduitAbstractView implements Observer {

    protected ProduitController produitController;
    protected List<Produit> lp;

    public void  setController(ProduitController produitController){
        this.produitController=produitController;
    }
    public abstract void affMsg(String msg);

    public abstract Produit selectionner();

    public abstract void menu();

    public abstract void affList(List l);

    @Override
    public void update(List lp) {
        this.lp = lp;
        affList(lp);
    }

}
