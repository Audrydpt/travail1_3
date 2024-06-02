package mvc.view;


import ecole.metier.*;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

import static utilitaires.Utilitaire.*;


public class ClasseViewConsole extends ClasseAbstractView {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void affMsg(String msg) {
        System.out.println("Info : " + msg);
    }

    @Override
    public void affList(List l) {
        affListe(l);
    }

    public void menu() {
        update(classeController.getAll());
        do {
            int ch = choixListe(Arrays.asList("ajout", "suppression", "rechercher", "modifier","méthodes liste", "fin"));

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
                    rechercher2();
                    break;
                case 6:
                    return;
            }
        } while (true);
    }

    private void special(Classe cr) {
        do {
            int ch = choixListe(Arrays.asList("ajouter cours", "modifier cours et heures", "modifier cours et salle", "modifier cours et enseignant", "supprimer cours", "lister cours", "nbrHeuresTot", "verifier capacite salle", "menu principal"));

            switch (ch) {
                case 1:
                    ajouterCours(cr);
                    break;
                case 2:
                    modifierCoursH(cr);
                    break;
                case 3:
                    modifierCoursS(cr);
                    break;
                case 4:
                    modifierCoursE(cr);
                    break;
                case 5:
                    supprimerCours(cr);
                    break;
                case 6:
                    listerCours(cr);
                    break;
                case 7:
                    NbrHeuresTot(cr);
                    break;
                case 8:
                    verifierCapaciteSalle(cr);
                    break;
                case 9:
                    return;
            }
        } while (true);
    }

    private void special2(Classe cr) {
        do {
            int ch = choixListe(Arrays.asList("Liste des enseignants et heures", "Liste des salles et heures", "Liste des cours et heures", "fin"));
            if (ch == 4) return;
            List l = switch (ch) {

                case 1 -> classeController.listeEnseignantsEtHeures(cr);
                case 2 -> classeController.listeSallesEtHeures(cr);
                case 3 -> classeController.listeCoursEtHeures(cr);
                default -> null;

            };
            if (l == null || l.isEmpty()){
                affMsg("aucun élément trouvée");
            } else affList(l);
        } while (true);
    }


    private void verifierCapaciteSalle(Classe cr) {
        System.out.println("salle pour verif :");
        Salle salle = sav.selectionner();

        boolean capaciteOK = classeController.salleCapaciteOK(cr, salle);
        if (capaciteOK) {
            affMsg("capacite OK");
        } else {
            affMsg("capacite insuffisante");
        }
    }


    private void NbrHeuresTot(Classe cr) {
        int totalHeures = classeController.nbrHeuresTot(cr);
        affMsg("Nombre total d'heures  : " + totalHeures);
    }




    public void ajouter() {
        System.out.println("Sigle : ");
        String sigle = sc.nextLine();
        sc.nextLine();
        System.out.println("Année : ");
        int annee = sc.nextInt();
        sc.nextLine();
        System.out.println("Specialité : ");
        String specialite = sc.nextLine();
        System.out.println("Nbr Eleves : ");
        int nbrEleves = sc.nextInt();
        Classe cr = classeController.addClasse(new Classe(sigle, annee, specialite, nbrEleves));
        if (cr == null) affMsg("Ajout raté");
        else affMsg("Ajout effectué : " + cr);
    }

    public void retirer() {
        int nl = choixElt(lc);
        Classe cr = lc.get(nl - 1);
        boolean ok = classeController.removeClasse(cr);
        if (ok) affMsg("Suppression effectuée");
        else affMsg("Suppression ratée car classe non vide");
    }

    public void rechercher() {
        System.out.println("idClasse : ");
        int idClasse = sc.nextInt();
        Classe c = classeController.search(idClasse);
        if (c == null) affMsg("recherche infructueuse");
        else {
            affMsg(c.toString());
            special(c);
            //special2(c);
        }


    }
    public void rechercher2() {
        System.out.println("idClasse : ");
        int idClasse = sc.nextInt();
        Classe c = classeController.search(idClasse);
        if (c == null) affMsg("recherche infructueuse");
        else {
            affMsg(c.toString());
            special2(c);
        }


    }


    public void modifier() {
        int nl = choixElt(lc);
        Classe cr = lc.get(nl - 1);
        String sigle = modifyIfNotBlank("sigle", cr.getSigle());
        int annee = Integer.parseInt(modifyIfNotBlank("année", "" + cr.getAnnee()));
        String specialite = modifyIfNotBlank("specialite", cr.getSpecialite());
        int nbrEleves = Integer.parseInt(modifyIfNotBlank("nombre d'élèves", "" + cr.getNbreEleve()));
        Classe crmaj = classeController.update(new Classe(cr.getId(), sigle, annee, specialite, nbrEleves));
        if (crmaj == null) affMsg("Mise à jour ratée");
        else affMsg("Mise à jour effectuée : " + crmaj);


    }

    public void listerCours(Classe cr) {
        System.out.println("Cours de la classe");
        List<Infos> ll = classeController.getCours(cr);
        if (ll.isEmpty()) affMsg("aucun cours pour cette classe");
        else affList(ll);
    }

    public void ajouterCours(Classe cr) {
        System.out.println("ajout d'un cours");
        Cours co = cav.selectionner();
        System.out.print("nombre d'heures :");
        int h = sc.nextInt();
        boolean ok = classeController.addCours(cr, co, h);
        if (ok) affMsg("cours ajouté");
        else affMsg("erreur lors de l'ajout du cours");
    }

    public void supprimerCours(Classe cr) {
        System.out.println("suppression d'un cours");
        Cours co = selectionnerCoursDansClasse(cr); //ou cav.selectionner();
        boolean ok = classeController.supCours(cr, co);
        if (ok) affMsg("cours supprimé");
        else affMsg("cours non supprimé");
    }



    public void modifierCoursH(Classe cr) {
        System.out.println("modification d'un cours");
        Cours co = selectionnerCoursDansClasse(cr); //ou cav.selectionner();
        System.out.print("nombre d'heures :");
        int h = sc.nextInt();
        boolean ok = classeController.modifCours(cr, co, h);
        if (ok) affMsg("mise à jour effectuée");
        else affMsg("mise à jour infructueuse");
    }

    public void modifierCoursE(Classe cr) {
        System.out.println("modification d'un cours");
        Cours co = selectionnerCoursDansClasse(cr); //ou cav.selectionner();
        Enseignant en = eav.selectionner();
        boolean ok = classeController.modifCours(cr, co, en);
        if (ok) affMsg("mise à jour effectuée");
        else affMsg("mise à jour infructueuse");
    }

    public void modifierCoursS(Classe cr) {
        System.out.println("modification d'un cours");
        Cours co = selectionnerCoursDansClasse(cr); //ou cav.selectionner();
        Salle sa = sav.selectionner();
        boolean ok = classeController.modifCours(cr, co, sa);
        if (ok) affMsg("mise à jour effectuée");
        else affMsg("mise à jour infructueuse");
    }

    @Override
    public Classe selectionner() {
        update(classeController.getAll());
        int nl = choixListe(lc);
        Classe classe = lc.get(nl - 1);
        return classe;

    }

    private Cours selectionnerCoursDansClasse(Classe classe) {
        List<Infos> coursDeClasse = classeController.getCours(classe);
        if (coursDeClasse.isEmpty()) {
            System.out.println("Aucun cours trouvé pour cette classe.");
            return null;
        }

        Cours choixC = null;
        while (choixC == null) {
            System.out.println("Sélectionnez un cours dans la classe :");
            for (int i = 0; i < coursDeClasse.size(); i++) {
                Infos infos = coursDeClasse.get(i);
                System.out.println((i + 1) + ". id : " + infos.getCours().getId() + " - " + infos.getCours().getCode() + " - " + infos.getCours().getIntitule());
            }

            System.out.println("Votre choix :");
            int choix = sc.nextInt();
            if (choix >= 1 && choix <= coursDeClasse.size()) {
                choixC = coursDeClasse.get(choix - 1).getCours();
            } else {
                System.out.println("choix pas ok");
            }
        }

        return choixC;
    }
}
