package mvc.view;


import ecole.metier.Classe;

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
        affList(l);
    }

    public void menu() {
        update(classeController.getAll());
        do{
            int ch = choixListe(Arrays.asList("ajout", "suppression", "rechercher", "modifier", "fin"));

            switch(ch){
                case 1: ajouter();
                    break;
                case 2 : retirer();
                    break;
                case 3: rechercher();
                    break;
                case 4 : modifier();
                    break;
                case 5 : return;
            }
        }while(true);
    }

public void ajouter(){
    System.out.println("Sigle : ");
    String sigle = sc.nextLine();
    System.out.println("Année : ");
    int annee = sc.nextInt();
    System.out.println("Specialité : ");
    String specialite = sc.nextLine();
    System.out.println("Nbr Eleves : ");
    int nbrEleves = sc.nextInt();
    Classe cr = classeController.addClasse(new Classe(0, sigle, annee, specialite, nbrEleves));
    if(cr==null) affMsg("Ajout raté");
    else affMsg("Ajout effectué : "+cr);
}

    public void retirer(){
        int nl = choixElt(lc);
        Classe cr = lc.get(nl-1);
        boolean ok = classeController.removeClasse(cr);
        if(ok) affMsg("Suppression effectuée");
        else affMsg("Suppression ratée");
    }

    public void rechercher(){
        System.out.println("idClasse : ");
        int idClasse = sc.nextInt();
        classeController.search(idClasse);

    }

    public void modifier() {
        int nl = choixElt(lc);
        Classe cr = lc.get(nl-1);
        String sigle = modifyIfNotBlank("sigle", cr.getSigle());
        int annee = Integer.parseInt(modifyIfNotBlank("année", ""+cr.getAnnee()));
        String specialite = modifyIfNotBlank("specialite", cr.getSpecialite());
        int nbrEleves = Integer.parseInt(modifyIfNotBlank("nombre d'élèves", ""+cr.getNbreEleve()));
        Classe crmaj = classeController.update(new Classe(cr.getId(), sigle, annee, specialite, nbrEleves));
        if(crmaj==null) affMsg("Mise à jour ratée");
        else affMsg("Mise à jour effectuée : "+crmaj);



    }

    public Classe selectionner(){
        update(classeController.getAll());
        int nl =  choixListe(lc);
        Classe cr = lc.get(nl-1);
        return cr;
    }

}
