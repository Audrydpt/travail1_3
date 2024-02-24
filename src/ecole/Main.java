package ecole;
import ecole.metier.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Création d'une salle
        Salle salle1 = new Salle(1, "A1", 10);
        Salle salle2 = new Salle(2, "B1", 45);
        Salle salle3 = new Salle(3, "C1", 30);
        Salle salle4 = new Salle(4, "D1", 20);

        // Création de cours
        Cours cours1 = new Cours(1, "C1", "Mathématiques", salle1);
        Cours cours2 = new Cours(2, "C2", "Français", salle2);
        Cours cours3 = new Cours(3, "C3", "Anglais", salle3);
        Cours cours4 = new Cours(4, "C4", "Histoire", salle4);

        // Création d'enseignants
        Enseignant enseignant1 = new Enseignant(1, "E1", "Dupont", "Audry", "0601020304", 18, new BigDecimal(2000), LocalDate.of(2010, 1, 1));
        Enseignant enseignant2 = new Enseignant(2, "E2", "Martin", "Sophie", "0601020305", 20, new BigDecimal(2500), LocalDate.of(2012, 5, 15));
        Enseignant enseignant3 = new Enseignant(3, "E3", "Durand", "Pierre", "0601020306", 15, new BigDecimal(1800), LocalDate.of(2015, 9, 1));
        Enseignant enseignant4 = new Enseignant(4, "E4", "Lefevre", "Marie", "0601020307", 25, new BigDecimal(3000), LocalDate.of(2018, 3, 1));


        // Création d'une classe
        Classe classe = new Classe(1, "SIO", 2021, "truc", 10);

        // Ajout de cours à la classe avec des heures attribuées
        classe.addCours(cours1, 10);
        classe.addCours(cours2, 15);
        classe.addCours(cours3, 20);
        classe.addCours(cours4, 25);

        // Modification de la salle par défaut d'un cours
        classe.modifCours(cours1, salle1);
        classe.modifCours(cours2, salle2);
        classe.modifCours(cours3, salle3);
        classe.modifCours(cours4, salle3);

        // Modification de l'enseignant d'un cours
        classe.modifCours(cours1, enseignant1);
        classe.modifCours(cours2, enseignant2);
        classe.modifCours(cours3, enseignant3);
        classe.modifCours(cours4, enseignant3);
        classe.modifCours(cours2, 5);
        classe.modifCours(cours3, 10);
        classe.modifCours(cours4, 15);

        // Affichage des informations de la classe
        System.out.println("Informations de la classe :");
        for (Infos infos : classe.listeInfos()) {
            System.out.println(infos);
        }




        /*
        //suppression d'un cours
        classe.supprCours(cours1);
        System.out.println("Informations de la classe :");
        for (Infos infos : classe.listeInfos()) {
            System.out.println(infos);
        }

        */

        //affichages des listes CoursetHeures
        System.out.println("Liste des cours :");
        for (CoursEtHeures ceh : classe.listeCoursEtHeures()) {
            System.out.println(ceh+"\n");
        }

        // Affichage des enseignants et de leurs heures avec Map
        System.out.println("Liste des enseignants :");
        Map<Enseignant, Integer> enseignantHeuresMap = classe.listeEnseignantHeures();
        for (Map.Entry<Enseignant, Integer> entry : enseignantHeuresMap.entrySet()) {
            System.out.println("Enseignant : " + entry.getKey().getNom() + " " + entry.getKey().getPrenom() + " - " + entry.getValue() + " heures \n");
        }

        // Affichage des salles et de leurs heures avec Map
        System.out.println("Liste des salles :");
        Map<Salle, Integer> salleHeuresMap = classe.listeSallesEtHeures();
        for (Map.Entry<Salle, Integer> entry : salleHeuresMap.entrySet()) {
            System.out.println("Salle : " + entry.getKey().getSigle() + " - " + entry.getValue() + " heures \n");
        }

        // Nombre d'heures totales
        System.out.println("Nombre d'heures total : " + classe.nbrHeuresTot() + "\n");

        //test capacité salle
        System.out.println("Capacité salle1  : " + classe.salleCapaciteOK(salle1));
        System.out.println("Capacité salle2  : " + classe.salleCapaciteOK(salle2));


    }
}
