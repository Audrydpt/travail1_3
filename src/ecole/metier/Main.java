package ecole.metier;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        // Création d'une salle
        Salle salle1 = new Salle(1, "A1", 30);
        Salle salle2 = new Salle(2, "B1", 25);




        // Création de cours
        Cours cours1 = new Cours(1, "C1", "Mathématiques", salle1);
        Cours cours2 = new Cours(2, "C2", "Français", salle2);

        // Création d'enseignants
        Enseignant enseignant1 = new Enseignant(1, "E1", "Dupont", "Audry", "0601020304", 18, new BigDecimal(2000), LocalDate.of(2010, 1, 1));
        Enseignant enseignant2 = new Enseignant(2, "E2", "Martin", "Sophie", "0601020305", 20, new BigDecimal(2500), LocalDate.of(2012, 5, 15));

        // Création d'une classe
        Classe classe = new Classe(1, "SIO", 2021, "SLAM", 30);

        // Ajout de cours à la classe avec des heures attribuées
        classe.addCours(cours1, 10);
        classe.addCours(cours2, 15);

        // Modification de la salle par défaut d'un cours
        classe.modifCours(cours1, salle2);

        // Modification de l'enseignant d'un cours
        classe.modifCours(cours1, enseignant1);

        // Affichage des informations de la classe
        System.out.println("Informations de la classe :");
        for (Infos infos : classe.listeInfos()) {
            System.out.println(infos);

        }

        // Modification du nombre d'heures d'un cours
        classe.modifCours(cours1, 20);

        // Suppression d'un cours de la classe
        classe.supprCours(cours2);

        // Affichage des informations de la classe
        System.out.println("Informations de la classe :");
        for (Infos infos : classe.listeInfos()) {
            System.out.println(infos);

        }


    }
}

