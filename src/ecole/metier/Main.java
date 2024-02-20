package ecole.metier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Création d'une classe
        Classe classe1 = new Classe(1, "A", 2024, "Informatique", 40000);
        Classe classe2 = new Classe(2, "B", 2024, "Math", 40);

        // Création d'enseignants
        Enseignant enseignant1 = new Enseignant(1, "E001", "Dupont", "Audry", "514-123-4567", 35, new BigDecimal(5000), LocalDate.of(2010, 1, 1));
        Enseignant enseignant2 = new Enseignant(2, "E002", "Martin", "Sophie", "514-234-5678", 30, new BigDecimal(4500), LocalDate.of(2012, 3, 15));

        // Création de salles
        Salle salle1 = new Salle(1, "S001", 3);
        Salle salle2 = new Salle(2, "S002", 100);

        // Création de cours
        Cours cours1 = new Cours(1, "C001", "Programmation orientée objet", salle1);
        Cours cours2 = new Cours(2, "C002", "Base de données", salle2);

        classe1.addCours(cours1, 5);
        classe1.addCours(cours2, 4);

        // Affichage des informations
        System.out.println("Liste des cours et heures : " + classe1.listeCoursEtHeures());
        System.out.println("Nombre total d'heures : " + classe1.nbrHeuresTot());

        // Vérification de la capacité de la salle
        System.out.println("Capacité de la salle 1 OK : " + classe1.salleCapaciteOK(salle1));
        System.out.println("Capacité de la salle 2 OK : " + classe2.salleCapaciteOK(salle2));

        // Modification du nombre d'heures d'un cours
        classe1.modifCours(cours1, 10);

        System.out.println("Liste des cours et heures après modifications : " + classe1.listeCoursEtHeures());

        // Suppression d'un cours
        classe1.suppCours(cours1);

        System.out.println("Liste des cours et heures après suppression : " + classe1.listeCoursEtHeures());

        //cration infos
        Infos info1 = new Infos(1, enseignant1, salle1, 10);
        Infos info2 = new Infos(2, enseignant2, salle2, 8);

        //affichage infos
        System.out.println("Liste des infos : " + classe1.listeInfos());
    }
}
