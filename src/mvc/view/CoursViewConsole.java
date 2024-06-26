package mvc.view;

import ecole.metier.Cours;
import ecole.metier.Salle;
import mvc.controller.SalleController;

import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

import static utilitaires.Utilitaire.*;

public class CoursViewConsole extends CoursAbstractView {
    private Scanner scanner = new Scanner(System.in);
    private SalleController salleController;

    public CoursViewConsole(SalleController salleController) {
        this.salleController = salleController;
    }

    @Override
    public void affMsg(String msg) {
        System.out.println("Infos : " + msg);
    }

    @Override
    public void affList(List l) {
        affListe(l);
    }

    public void menu() {
        update(coursController.getAll());
        do {
            int ch = choixListe(Arrays.asList("ajout", "suppression", "rechercher", "modifier", "fin"));
            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    retirer();
                    break;
                case 3:
                    rechercher();
                    break;
                case 4:
                    modifier();
                    break;
                case 5:
                    return;
            }
        } while (true);
    }

    public void ajouter() {
        System.out.println("Code : ");
        String code = scanner.next();
        scanner.nextLine();
        System.out.println("Intitule : ");
        String intitule = scanner.nextLine();
        System.out.println("Voulez-vous entrer une salle par défaut ? (oui/non) ");
        String reponse = scanner.next();
        Salle salleParDefault = null;
        if (reponse.equalsIgnoreCase("oui")) {
            System.out.println("ID de la salle par défaut : ");
            int salleId = scanner.nextInt();
            salleParDefault = salleController.search(salleId);
        }
        Cours cours = coursController.addCours(new Cours(code, intitule, salleParDefault));
        if (cours == null) affMsg("Ajout infructueux");
        else affMsg("Ajout effectué : " + cours);
    }

    public void retirer() {
        int nl = choixElt(lco);
        Cours cours = lco.get(nl - 1);
        if (coursController.removeCours(cours)) affMsg("Suppression effectuée");
        else affMsg("Suppression infructueuse");
    }

    public void rechercher() {
        System.out.println("idCours: ");
        int idCours = scanner.nextInt();
        coursController.search(idCours);
    }

    public void modifier() {
        int nl = choixElt(lco);
        Cours cours = lco.get(nl - 1);
        String code = modifyIfNotBlank("code", cours.getCode());
        String intitule = modifyIfNotBlank("intitule", cours.getIntitule());
        System.out.println("Tapez 'non' pour supprimer la salle par défaut, ou l'ID de la nouvelle salle par défaut : ");
        String spdft = scanner.next();
        Salle salle = null;
        if (!spdft.equalsIgnoreCase("non")) {
            salle = salleController.search(Integer.parseInt(spdft));
        }
        Cours co = new Cours(cours.getId(), code, intitule, salle);
        Cours coursMaj = coursController.update(co);
        if (coursMaj == null) affMsg("Mise à jour infructueuse");
        else affMsg("Mise à jour effectuée : " + coursMaj);
    }


    public Cours selectionner() {
        update(coursController.getAll());
        int nl = choixElt(lco);
        Cours cours = lco.get(nl - 1);
        return cours;
    }
}
