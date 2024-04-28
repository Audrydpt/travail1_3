package designpatterns.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Classe {
    private int id;
    private String sigle;
    private int annee;
    private String specialite;
    private int nbreEleve;

    private Classe(ClasseBuilder builder) {
        this.id = builder.id;
        this.sigle = builder.sigle;
        this.annee = builder.annee;
        this.specialite = builder.specialite;
        this.nbreEleve = builder.nbreEleve;
    }

    public int getId() {
        return id;
    }

    public String getSigle() {
        return sigle;
    }

    public int getAnnee() {
        return annee;
    }

    public String getSpecialite() {
        return specialite;
    }

    public int getNbreEleve() {
        return nbreEleve;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classe classe = (Classe) o;
        return id == classe.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Classe{" +
                "id=" + id +
                ", sigle='" + sigle + '\'' +
                ", annee=" + annee +
                ", specialite='" + specialite + '\'' +
                ", nbreEleve=" + nbreEleve +
                '}';
    }

    public static class ClasseBuilder {
        private int id;
        private String sigle;
        private int annee;
        private String specialite;
        private int nbreEleve;

        public ClasseBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ClasseBuilder setSigle(String sigle) {
            this.sigle = sigle;
            return this;
        }

        public ClasseBuilder setAnnee(int annee) {
            this.annee = annee;
            return this;
        }

        public ClasseBuilder setSpecialite(String specialite) {
            this.specialite = specialite;
            return this;
        }

        public ClasseBuilder setNbreEleve(int nbreEleve) {
            this.nbreEleve = nbreEleve;
            return this;
        }


        public Classe build() throws Exception {
            if(sigle == null || annee == 0) {
                throw new Exception("info incomplètes");
            }
            return new Classe(this);
        }
    }
}