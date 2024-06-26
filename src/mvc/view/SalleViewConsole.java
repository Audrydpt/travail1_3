package mvc.view;

import ecole.metier.Classe;
import ecole.metier.Salle;

import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

import static utilitaires.Utilitaire.*;

public class SalleViewConsole extends SalleAbstractView {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void affMsg(String msg) {
        System.out.println(msg);
    }

    @Override
    public void affList(List l) {
        affListe(l);
    }

    public void menu() {
        update(salleController.getAll());
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

    private void special1(Salle s) {
        do {
            int ch = choixListe(Arrays.asList("Liste de cours pour cette salle pdft", "fin"));
            if (ch == 2) return;
            List l = switch (ch) {
                case 1 -> salleController.coursSalleDefaut(s);

                default -> null;
            };
            if (l == null || l.isEmpty()) affMsg("aucun élément trouvée");
            else affList(l);
        } while (true);
    }

    public void ajouter() {
        System.out.println("Sigle :");
        String sigle = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Capacité :");
        int capacite = scanner.nextInt();
        Salle salle = salleController.addSalle(new Salle(sigle, capacite));
        if (salle == null) affMsg("Ajout infructueux");
        else affMsg("Ajout effectué : " + salle);
    }

    public void retirer() {
        int nl = choixElt(lsa);
        Salle salle = lsa.get(nl - 1);
        if (salleController.removeSalle(salle)) affMsg("Suppression effectuée");
        else affMsg("Suppression infructueuse");
    }

    public void rechercher() {
        System.out.println("id : ");
        int id = scanner.nextInt();
        Salle salle = salleController.search(id);
        if (salle == null) affMsg("Recherche infructueuse");
        else {
            affMsg(salle.toString());
            special1(salle);

        }
    }

    public void modifier() {
        int nl = choixElt(lsa);
        Salle salle = lsa.get(nl - 1);
        String sigle = modifyIfNotBlank("sigle", salle.getSigle());
        scanner.nextLine();
        int capacite = Integer.parseInt(modifyIfNotBlank("capacité", "" + salle.getCapacite()));
        Salle salleMaj = salleController.update(new Salle(salle.getId(), sigle, capacite));
        if (salleMaj == null) affMsg("Mise à jour infructueuse");
        else affMsg("Mise à jour effectuée : " + salleMaj);
    }

    public Salle selectionner() {
        update(salleController.getAll());
        int nl = choixElt(lsa);
        Salle sa = lsa.get(nl - 1);
        return sa;

    }
}
