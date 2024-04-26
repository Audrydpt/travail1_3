package mvc.controller;

import ecole.metier.Classe;
import mvc.model.DAOClasse;
import mvc.view.ClasseAbstractView;

import java.util.List;

public class ClasseController {
    private DAOClasse model;
    private ClasseAbstractView view;

    public ClasseController(DAOClasse model, ClasseAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Classe> getAll(){
        return model.getClasses();
    }

    public Classe addClasse(Classe classe) {
        return model.addClasse(classe);
    }

    public boolean removeClasse(Classe classe) {
        return model.removeClasse(classe);
    }

    public Classe update(Classe classe) {
        return model.updateClasse(classe);
    }

    public Classe search(int idClasse) {
        return model.readClasse(idClasse);
    }
}
