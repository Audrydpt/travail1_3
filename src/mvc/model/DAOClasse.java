package mvc.model;

import ecole.metier.*;
import mvc.observer.Subject;

import java.util.List;

public abstract class DAOClasse extends Subject {
    public abstract Classe addClasse(Classe classe);

    public abstract boolean removeClasse(Classe classe);

    public abstract Classe updateClasse(Classe classe);

    public abstract Classe readClasse(int idClasse);

    public abstract List<Classe> getClasses();

    public abstract boolean addCours(Classe classe, Cours cours, int h);

    public abstract boolean modifCours(Classe classe, Cours cours, Salle salle);

    public abstract boolean modifCours(Classe classe, Cours cours, Enseignant enseignant);

    public abstract boolean modifCours(Classe classe, Cours cours, int h);


    public abstract boolean supCours(Classe classe, Cours cours);

    public abstract List<Infos> getCours(Classe classe);

    public abstract List<EnseignantEtHeures> listeEnseignantsEtHeures(Classe classe);

    public abstract List<SalleEtHeures> listeSallesEtHeures(Classe classe);

    public abstract List<CoursEtHeures> listeCoursEtHeures(Classe classe);

    public abstract int nbrHeuresTot(Classe classe);

    public abstract boolean salleCapaciteOK(Classe classe, Salle salle);


}
