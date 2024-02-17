package ecole.metier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Création de quelques cours
        Cours math = new Cours("MATH101", "Mathématiques", 0);
        Cours francais = new Cours("FR101", "Français", 0);

        // Création de quelques enseignants
        Enseignant profMath = new Enseignant("123", "Dupont", "Jean", "123456789", 10, BigDecimal.valueOf(3000), LocalDate.now());
        Enseignant profFrancais = new Enseignant("456", "Durant", "Marie", "987654321", 12, BigDecimal.valueOf(3500), LocalDate.now());

        // Création de quelques salles
        Salle salleA = new Salle("A", 30);
        Salle salleB = new Salle("B", 25);

        // Création de la classe
        List<Cours> coursList = new ArrayList<>();
        coursList.add(math);
        coursList.add(francais);

        Classe classeA = new Classe("1A", 2024, "Scientifique", 25, coursList);

        // Association des enseignants et des salles aux cours
        Infos infoMath = math.getInfos();
        infoMath.setEnseignant(profMath);
        infoMath.setSalle(salleA);
        math.setSalleParDefault(salleA);

        Infos infoFrancais = francais.getInfos();
        infoFrancais.setEnseignant(profFrancais);
        infoFrancais.setSalle(salleB);
        francais.setSalleParDefault(salleB);

        // Ajout des infos à la classe
        classeA.addCours(math, 5);
        classeA.addCours(francais, 4);

        // Affichage des résultats
        System.out.println("Total des heures de la classe : " + classeA.nbrHeuresTot());

        List<Enseignant> enseignantList = classeA.listeEnseignantHeures();
        System.out.println("Liste des enseignants et de leurs heures : ");
        for (Enseignant enseignant : enseignantList) {
            System.out.println(enseignant.getNom() + " - " + enseignant.getChargeSem() + " heures");
        }

        List<String> sallesEtHeuresList = classeA.listeSallesetHeures();
        System.out.println("Liste des salles et de leurs heures : ");
        for (String salleInfo : sallesEtHeuresList) {
            System.out.println(salleInfo);
        }

        System.out.println("Capacité de la salle A OK : " + classeA.salleCapaciteOK(salleA));
        System.out.println("Capacité de la salle B OK : " + classeA.salleCapaciteOK(salleB));
    }
}
