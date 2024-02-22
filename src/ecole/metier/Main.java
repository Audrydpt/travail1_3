package ecole.metier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Création de la liste des enseignants
        List<Enseignant> enseignants = List.of(
                new Enseignant(1, "E001", "DUPONT", "Audry", "01 02 03 04 05", 35, new BigDecimal(2000), LocalDate.of(2010, 1, 1)),
                new Enseignant(2, "E002", "DURAND", "Jean", "06 07 08 09 10", 35, new BigDecimal(2000), LocalDate.of(2010, 1, 1)),
                new Enseignant(3, "E003", "DUBOIS", "Marie", "11 12 13 14 15", 35, new BigDecimal(2000), LocalDate.of(2010, 1, 1))
        );

            // Création de la liste des salles
            List<Salle> salles = List.of(
                    new Salle(1, "S1", 10),
                    new Salle(2, "S2", 40),
                    new Salle(3, "S3", 40)
            );

            // Création de la liste des cours
            List<Cours> cours = List.of(
                    new Cours(1, "M1", "Mathématiques", salles.get(0)),
                    new Cours(2, "F1", "Français", salles.get(1)),
                    new Cours(3, "A1", "Anglais", salles.get(2))
            );

            // Création de la liste des classes
            List<Classe> classes = List.of(
                    new Classe(1, "C1", 1, "1ère", 30),
                    new Classe(2, "C2", 2, "2ème", 30),
                    new Classe(3, "C3", 3, "3ème", 30)
            );

            // Création de la liste des informations
            List<Infos> infos = List.of(
                    new Infos(1, enseignants.get(0), salles.get(0), cours.get(0), 10),
                    new Infos(2, enseignants.get(1), salles.get(1), cours.get(1), 10),
                    new Infos(3, enseignants.get(2), salles.get(2), cours.get(2), 10)
            );

            // Création de la liste des cours et heures
            List<CoursEtHeures> coursEtHeures = List.of(
                    new CoursEtHeures(cours.get(0), 10),
                    new CoursEtHeures(cours.get(1), 10),
                    new CoursEtHeures(cours.get(2), 10)
            );

            // Création de la liste des enseignants et heures
            List<EnseignantEtHeures> enseignantEtHeures = List.of(
                    new EnseignantEtHeures(enseignants.get(0), 10),
                    new EnseignantEtHeures(enseignants.get(1), 10),
                    new EnseignantEtHeures(enseignants.get(2), 10)
            );

            // Création de la liste des salles et heures
            List<SalleEtHeures> salleEtHeures = List.of(
                    new SalleEtHeures(salles.get(0), 10),
                    new SalleEtHeures(salles.get(1), 10),
                    new SalleEtHeures(salles.get(2), 10)
            );

            // Modification de la liste des informations
            infos.get(0).setNbHeures(20);
            infos.get(1).setNbHeures(20);
            infos.get(2).setNbHeures(20);

            // Modification de la liste des infos (Enseignant)
            infos.get(0).setEnseignant(enseignants.get(1));
            infos.get(1).setEnseignant(enseignants.get(2));
            infos.get(2).setEnseignant(enseignants.get(0));

            // Modification de la liste des infos (Salle)
            infos.get(0).setSalle(salles.get(1));
            infos.get(1).setSalle(salles.get(2));
            infos.get(2).setSalle(salles.get(0));


            //Affichage des informations
            System.out.println("Liste des enseignants : " + enseignants);
            System.out.println("Liste des salles : " + salles);
            System.out.println("Liste des cours : " + cours);
            System.out.println("Liste des classes : " + classes);
            System.out.println("Liste des informations : " + infos);
            System.out.println("Liste des cours et heures : " + coursEtHeures);
            System.out.println("Liste des enseignants et heures : " + enseignantEtHeures);
            System.out.println("Liste des salles et heures : " + salleEtHeures);

            //test capcite sallevec fonctiion boolean
            System.out.println("Capacité de la salle 1 : " + classes.get(0).salleCapaciteOK(salles.get(0)));
            System.out.println("Capacité de la salle 2 : " + classes.get(0).salleCapaciteOK(salles.get(1)));
            System.out.println("Capacité de la salle 3 : " + classes.get(0).salleCapaciteOK(salles.get(2)));

            //suppression d un cours avec fonction supprimer
            classes.get(0).supprCours(cours.get(0));








    }
}
