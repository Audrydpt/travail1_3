package ecole.metier;

class Salle {
    private String sigle;
    private int capacite;

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
}
