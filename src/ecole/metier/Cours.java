package ecole.metier;

import java.util.Objects;

public class Cours {
    protected int id; // Identifiant numérique
    protected String code;
    protected String intitule;
    protected Salle salleParDefault;
    protected Infos infos;

    public Cours(String code, String intitule, int nbHeures) {
        this.code = code;
        this.intitule = intitule;
        this.infos = new Infos();
        this.infos.setNbHeures(nbHeures);
    }


    public Cours(int id, String code, String intitule, int nbHeures) {
        this.id = id;
        this.code = code;
        this.intitule = intitule;
        this.infos = new Infos();
        this.infos.setNbHeures(nbHeures);
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getIntitule() {
        return intitule;
    }

    public Salle getSalleParDefault() {
        return salleParDefault;
    }

    public void setSalleParDefault(Salle salleParDefault) {
        this.salleParDefault = salleParDefault;
    }

    public Infos getInfos() {
        return infos;
    }

    public void setInfos(Infos infos) {
        this.infos = infos;
    }

    public Enseignant getEnseignant() {
        return infos.getEnseignant();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cours cours = (Cours) o;
        return id == cours.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
