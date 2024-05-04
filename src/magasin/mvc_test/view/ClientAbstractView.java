package magasin.mvc_test.view;

import magasin.metier.Client;
import magasin.mvc_test.controller.ClientController;
import magasin.mvc_test.observer.Observer;

import java.util.List;

public abstract class ClientAbstractView implements  Observer {

    protected ClientController clientController;
    protected List<Client> lc;

    public void  setController(ClientController clientController){
        this.clientController=clientController;
    }

    public abstract void affMsg(String msg);

    public abstract Client selectionner();

    public abstract void menu();

    public abstract void affList(List l);

    @Override
    public void update(List lc) {
        this.lc = lc;
        affList(lc);
    }
}
