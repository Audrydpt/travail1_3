package mvc;

import mvc.controller.*;
import mvc.model.*;
import mvc.view.*;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestEcole {
    private DAOSalle salleModel;
    private DAOCours coursModel;
    private DAOClasse classeModel;
    private DAOEnseignant enseignantModel;


    private CoursController coursController;
    private SalleController salleController;
    private ClasseController classeController;
    private EnseignantController enseignantController;


    private CoursAbstractView coursView;
    private SalleAbstractView salleView;
    private ClasseAbstractView classeView;
    private EnseignantAbstractView enseignantView;


    public void gestion() {
        salleModel = new SalleModelDB();
        salleView = new SalleViewConsole();
        salleController = new SalleController(salleModel, salleView);


        coursModel = new CoursModelDB(salleController);
        coursView = new CoursViewConsole(salleController);
        coursController = new CoursController(coursModel, coursView);

        classeModel = new ClasseModelDB();
        classeView = new ClasseViewConsole();
        classeController = new ClasseController(classeModel, classeView);

        enseignantModel = new EnseignantModelDB();
        enseignantView = new EnseignantViewConsole();
        enseignantController = new EnseignantController(enseignantModel, enseignantView);



        salleModel.addObserver(salleView);
        coursModel.addObserver(coursView);
        classeModel.addObserver(classeView);
        enseignantModel.addObserver(enseignantView);


        List<String> loptions = Arrays.asList("salles", "cours", "classes", "enseignants", "fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch) {
                case 1:
                    salleView.menu();
                    break;
                case 2:
                    coursView.menu();
                    break;
                case 3:
                    classeView.menu();
                    break;
                case 4:
                    enseignantView.menu();
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