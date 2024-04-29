package mvc.controller;

import ecole.metier.Cours;
import ecole.metier.Salle;
import mvc.model.DAOCours;
import mvc.view.CoursAbstractView;

import java.util.List;

public class CoursController {
    private DAOCours model;
    private CoursAbstractView view;

    public CoursController(DAOCours model, CoursAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Cours> getAll(){
        return model.getCours();
    }

    public Cours addCours(Cours cours) {
        return model.addCours(cours);
    }

    public boolean removeCours(Cours cours) {
        return model.removeCours(cours);
    }

    public Cours update(Cours cours) {
        return model.updateCours(cours);
    }

    public Cours search(int idCours) {
        return model.readCours(idCours);
    }


    public Salle getSalleParDefault(Cours cours) {
        return cours.getSalleParDefault();
    }

    public void updateSalleParDefault(Cours cours, Salle newSalle) {
        cours.setSalleParDefault(newSalle);
        // Enregistrez le cours mis à jour dans la base de données
    }



}
