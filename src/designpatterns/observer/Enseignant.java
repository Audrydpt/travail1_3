package designpatterns.observer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Enseignant implements Observer{
    private int id;
    private String nom;
    private String prenom;
    private String tel;
    private int chargeSemaine;
    private BigDecimal salaireMensuel;
    private LocalDate dateEngagement;

    public Enseignant(int id, String nom, String prenom, String tel, int chargeSemaine, BigDecimal salaireMensuel, LocalDate dateEngagement) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.chargeSemaine = chargeSemaine;
        this.salaireMensuel = salaireMensuel;
        this.dateEngagement = dateEngagement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getChargeSemaine() {
        return chargeSemaine;
    }

    public void setChargeSemaine(int chargeSemaine) {
        this.chargeSemaine = chargeSemaine;
    }

    public BigDecimal getSalaireMensuel() {
        return salaireMensuel;
    }

    public void setSalaireMensuel(BigDecimal salaireMensuel) {
        this.salaireMensuel = salaireMensuel;
    }

    public LocalDate getDateEngagement() {
        return dateEngagement;
    }

    public void setDateEngagement(LocalDate dateEngagement) {
        this.dateEngagement = dateEngagement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enseignant that = (Enseignant) o;
        return id == that.id && chargeSemaine == that.chargeSemaine && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(tel, that.tel) && Objects.equals(salaireMensuel, that.salaireMensuel) && Objects.equals(dateEngagement, that.dateEngagement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, tel, chargeSemaine, salaireMensuel, dateEngagement);
    }

    @Override
    public void update(String msg) {
        System.out.println("Enseignant " + nom + " " + prenom + " a reçu la notification suivante: " + msg);
    }
}
