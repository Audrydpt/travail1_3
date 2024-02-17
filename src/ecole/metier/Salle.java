package ecole.metier;

import java.math.BigDecimal;
import java.util.Objects;

public class Salle {
    protected int id;
    protected String sigle;
    protected int capacite;

    public Salle(String sigle, int capacite) {
        this.sigle = sigle;
        this.capacite = capacite;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salle salle = (Salle) o;
        return id == salle.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
