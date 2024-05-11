package mvc.controller;

import ecole.metier.Cours;
import ecole.metier.Salle;
import mvc.model.DAOSalle;
import mvc.view.SalleAbstractView;

import java.util.List;

public class SalleController {

    private DAOSalle model;
    private SalleAbstractView view;

    public SalleController(DAOSalle model, SalleAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Salle> getAll(){
        return model.getSalles();
    }

    public Salle addSalle(Salle salle) {
        return model.addSalle(salle);
    }

    public boolean removeSalle(Salle salle) {
        return model.removeSalle(salle);
    }

    public Salle update(Salle salle) {
        return model.updateSalle(salle);
    }

    public Salle search(int idSalle) {
        return model.readSalle(idSalle);
    }

    public List<Cours> coursSalleDefaut(Salle salle) {
        return model.coursSalleDefaut(salle);
    }
}
