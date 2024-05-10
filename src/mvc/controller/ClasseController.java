package mvc.controller;

import ecole.metier.Classe;
import ecole.metier.Infos;
import ecole.metier.Cours;
import ecole.metier.Salle;
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

    public List<Classe> getAll() {
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

    public boolean addCours(Classe classe, Cours cours, int h) {
        return model.addCours(classe, cours, h);
    }

    public boolean modifCours(Classe classe, Cours cours, int h) {
        return model.modifCours(classe, cours, h);
    }

    public boolean modifCours(Classe classe, Cours cours, ecole.metier.Salle salle) {
        return model.modifCours(classe, cours, salle);
    }

    public boolean modifCours(Classe classe, Cours cours, ecole.metier.Enseignant enseignant) {
        return model.modifCours(classe, cours, enseignant);
    }

    public boolean supCours(Classe classe, Cours cours) {
        return model.supCours(classe, cours);
    }

    public List<Infos> getCours(Classe classe) {
        return model.getCours(classe);
    }

    public List<Infos> listeEnseignantsEtHeures(Classe classe) {
        return model.listeEnseignantsEtHeures(classe);
    }

    public List<Infos> listeSallesEtHeures(Classe classe) {
        return model.listeSallesEtHeures(classe);
    }

    public List<Infos> listeCoursEtHeures(Classe classe) {
        return model.listeCoursEtHeures(classe);
    }

    public int nbrHeuresTot(Classe classe) {
        return model.nbrHeuresTot(classe);
    }

    public boolean salleCapaciteOK(Classe classe, Salle salle){
        return model.salleCapaciteOK(classe,salle);
    }


}
