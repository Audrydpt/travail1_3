package designpatterns.observer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        Classe classe = new Classe(1, 2022, "Informatique", 30);
        Enseignant enseignant = new Enseignant(1, "Dupont", "Jean", "0123456789", 35, new BigDecimal("2500.00"), LocalDate.now());
        classe.addObserver(enseignant);
        classe.setNbrEleves(35);
        classe.notifyObservers();
    }
}