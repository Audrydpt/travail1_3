package designpatterns.composite;

import ecole.metier.Infos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Classe extends Elt {
    private int id;
    private int annee;
    private String specialite;
    private int nbrEleve;
    private List<Infos> infos = new ArrayList<>();

    public Classe(int id, int annee, String specialite, int nbrEleve) {
        super(id);
        this.annee = annee;
        this.specialite = specialite;
        this.nbrEleve = nbrEleve;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public int getNbrEleve() {
        return nbrEleve;
    }

    public void setNbrEleve(int nbrEleve) {
        this.nbrEleve = nbrEleve;
    }

    public List<Infos> getInfos() {
        return infos;
    }

    public void setInfos(List<Infos> infos) {
        this.infos = infos;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Classe classe = (Classe) o;
        return id == classe.id && annee == classe.annee && nbrEleve == classe.nbrEleve && Objects.equals(specialite, classe.specialite) && Objects.equals(infos, classe.infos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, annee, specialite, nbrEleve, infos);
    }

    @Override
    public int nbrTotEleve() {
        return nbrEleve;
    }

    @Override
    public String toString() {
        return "Classe{" +
                "id=" + id +
                ", annee=" + annee +
                ", specialite='" + specialite + '\'' +
                ", nbrEleve=" + nbrEleve +
                ", infos=" + infos +
                '}';
    }
}
