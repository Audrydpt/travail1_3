package mvc;

import mvc.controller.*;
import mvc.model.*;
import mvc.view.*;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestEcole {
    private DAOSalle sm;
    private DAOCours com;
    private DAOClasse cm;
    private DAOEnseignant em;


    private CoursController coc;
    private SalleController sc;
    private ClasseController cc;
    private EnseignantController ec;


    private CoursAbstractView cov;
    private SalleAbstractView sv;
    private ClasseAbstractView cv;
    private EnseignantAbstractView ev;


    public void gestion() {
        sm = new SalleModelDB();
        sv = new SalleViewConsole();
        sc = new SalleController(sm, sv);


        com = new CoursModelDB(sc);
        cov = new CoursViewConsole(sc);
        coc = new CoursController(com, cov);

        cm = new ClasseModelDB();
        cv = new ClasseViewConsole();
        cc = new ClasseController(cm, cv);

        em = new EnseignantModelDB();
        ev = new EnseignantViewConsole();
        ec = new EnseignantController(em, ev);

        cv.setCoursView(cov);
        cv.setSalleView(sv);
        cv.setEnseignantView(ev);


        sm.addObserver(sv);
        com.addObserver(cov);
        cm.addObserver(cv);
        em.addObserver(ev);


        List<String> loptions = Arrays.asList("salles", "cours", "classes", "enseignants", "fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch) {
                case 1:
                    sv.menu();
                    break;
                case 2:
                    cov.menu();
                    break;
                case 3:
                    cv.menu();
                    break;
                case 4:
                    ev.menu();
                    break;
                case 5:
                    System.exit(0);
            }
        } while (true);
    }

    public static void main(String[] args) {
        GestEcole gestEcole = new GestEcole();
        gestEcole.gestion();
    }
}