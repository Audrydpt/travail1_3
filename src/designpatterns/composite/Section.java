package designpatterns.composite;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Section extends Elt {

    private String nom;

    private Set<Elt> elts = new HashSet<>();

    public Section(int id, String nom) {
        super(id);
        this.nom = nom;
    }

    @Override
    public String toString() {
        StringBuilder aff = new StringBuilder(getId() + " " + nom + "\n");

        for (Elt e : elts) {
            aff.append(e).append("\n");
        }
        return aff + "Nombre totale : " + nom + " = " + nbrTotEleve() + "\n";
    }


    @Override
    public int nbrTotEleve() {
        int total = 0;
        for (Elt elt : elts) {
            total = total + elt.nbrTotEleve();
        }
        return total;
    }

    public Set<Elt> getElts() {
        return elts;
    }
}
