package designpatterns.observer;

import ecole.metier.Infos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Classe extends Subject {
    private int id;
    private int annee;
    private String specialite;
    private int nbrEleves;
    private List<Infos> infos = new ArrayList<>();

    public Classe(int id, int annee, String specialite, int nbrEleves) {
        this.id = id;
        this.annee = annee;
        this.specialite = specialite;
        this.nbrEleves = nbrEleves;
    }

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

    public int getNbrEleves() {
        return nbrEleves;
    }

    public void setNbrEleves(int nbrEleves) {
        this.nbrEleves = nbrEleves;
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
        Classe classe = (Classe) o;
        return id == classe.id && annee == classe.annee && nbrEleves == classe.nbrEleves && Objects.equals(specialite, classe.specialite) && Objects.equals(infos, classe.infos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, annee, specialite, nbrEleves, infos);
    }

    @Override
    public String getNotification() {
        return "Classe " + id + " : " + nbrEleves + " élèves";
    }
}
