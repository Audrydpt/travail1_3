package designpatterns.composite;


public abstract class Elt {
    private int id;

    public Elt(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public abstract int nbrTotEleve();

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Elt other = (Elt) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }


}
