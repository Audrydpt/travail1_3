package ecole.metier;

class Cours {
    private String code;
    private String intitule;
    private int nbHeures;
    private Salle salleParDefault;
    private Infos infos;

    public Cours(String code, String intitule) {
        this.code = code;
        this.intitule = intitule;
    }
    public Cours(String code, String intitule, int nbHeures) {
        this.code = code;
        this.intitule = intitule;
        this.nbHeures = nbHeures;

    }
    public String getCode() {
        return code;
    }

    public int getNbHeures() {
        return nbHeures;
    }

    public void setNbHeures(int nbHeures) {
        this.nbHeures = nbHeures;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Salle getSalleParDefault() {
        return salleParDefault;
    }

    public void setSalleParDefault(Salle salleParDefault) {
        this.salleParDefault = salleParDefault;
    }

    public Enseignant getEnseignant() {
        return infos.getEnseignant();
    }

    public void setInfos(Infos infos) {
        this.infos = infos;
    }
}
