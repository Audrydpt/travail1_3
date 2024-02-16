package ecole.metier;

import ecole.metier.*;

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
        Infos infoMath = new Infos();
        infoMath.setEnseignant(profMath);
        infoMath.setSalle(salleA);
        math.setSalleParDefault(salleA);
        math.setInfos(infoMath);

        Infos infoFrancais = new Infos();
        infoFrancais.setEnseignant(profFrancais);
        infoFrancais.setSalle(salleB);
        francais.setSalleParDefault(salleB);
        francais.setInfos(infoFrancais);

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

        List<CoursEtHeures> coursEtHeuresList = classeA.listeCoursEtHeures();
        System.out.println("Liste des cours et de leurs heures : ");
        for (CoursEtHeures coursEtHeures : coursEtHeuresList) {
            System.out.println(coursEtHeures.getCours().getIntitule() + " - " + coursEtHeures.getHeures() + " heures");
        }

        System.out.println("Capacité de la salle A OK : " + classeA.salleCapaciteOK(salleA));
        System.out.println("Capacité de la salle B OK : " + classeA.salleCapaciteOK(salleB));
    }
}
