package mvc.view;

import ecole.metier.Enseignant;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

import static utilitaires.Utilitaire.*;

public class EnseignantViewConsole extends EnseignantAbstractView {

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
        update(enseignantController.getAll());
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
        System.out.println("Matricule : ");
        String matricule = sc.nextLine();
        System.out.println("Nom : ");
        String nom = sc.nextLine();
        System.out.println("Prenom : ");
        String prenom = sc.nextLine();
        System.out.println("Telephone : ");
        String telephone = sc.nextLine();
        System.out.println("Charge Semaine : ");
        int chargeSem = sc.nextInt();
        System.out.println("Salaire Mensuel : ");
        BigDecimal salaireMensuel = sc.nextBigDecimal();
        sc.nextLine();
        System.out.println("Date Engagement : ");
        LocalDate dateEngagement = LocalDate.parse(sc.nextLine());
        Enseignant en = enseignantController.addEnseignant(new Enseignant(matricule, nom, prenom, telephone, chargeSem, salaireMensuel, dateEngagement));
        if (en == null) affMsg("Ajout raté");
        else affMsg("Ajout effectué : " + en);
    }

    public void retirer() {
        int nl = choixElt(le);
        Enseignant en = le.get(nl - 1);
        boolean res = enseignantController.removeEnseignant(en);
        if (res) affMsg("Suppression effectuée");
        else affMsg("Suppression ratée");
    }

    public void rechercher() {
        System.out.println("Id : ");
        int id = sc.nextInt();
        enseignantController.search(id);
    }

    public void modifier() {
        int nl = choixElt(le);

        Enseignant en = le.get(nl - 1);
        String matricule = modifyIfNotBlank("matricule", en.getMatricule());
        String nom = modifyIfNotBlank("nom", en.getNom());
        String prenom = modifyIfNotBlank("prenom", en.getPrenom());
        String telephone = modifyIfNotBlank("telephone", en.getTel());
        int chargeSem = Integer.parseInt(modifyIfNotBlank("charge semaine", "" + en.getChargeSem()));
        BigDecimal salaireMensuel = new BigDecimal(modifyIfNotBlank("salaire mensuel", "" + en.getSalaireMensuel()));
        LocalDate dateEngagement = LocalDate.parse(modifyIfNotBlank("date engagement", en.getDateEngagement().toString()));
        Enseignant enmaj = enseignantController.update(new Enseignant(en.getId(), matricule, nom, prenom, telephone, chargeSem, salaireMensuel, dateEngagement));
        if (enmaj == null) affMsg("mise à jour infructueuse");
        else affMsg("mise à jour effectuée : " + enmaj);
    }

    public Enseignant selectionner() {
        update(enseignantController.getAll());
        int nl = choixElt(le);
        Enseignant en = le.get(nl - 1);
        return en;
    }
}
