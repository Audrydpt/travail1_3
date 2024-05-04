package mvc;

import mvc.controller.CoursController;
import mvc.controller.SalleController;
import mvc.model.CoursModelDB;
import mvc.model.DAOCours;
import mvc.model.DAOSalle;
import mvc.model.SalleModelDB;
import mvc.view.CoursAbstractView;
import mvc.view.CoursViewConsole;
import mvc.view.SalleAbstractView;
import mvc.view.SalleViewConsole;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestEcole {
    private DAOSalle salleModel;
    private DAOCours coursModel;
    private CoursController coursController;
    private SalleController salleController;
    private CoursViewConsole coursView;
    private SalleViewConsole salleView;
    private SalleAbstractView sav;
    private CoursAbstractView cav;

    public void gestion() {
        salleModel = new SalleModelDB();
        salleView = new SalleViewConsole(); // Initialisez salleView ici

        salleController = new SalleController(salleModel, salleView); // Passez salleView à SalleController ici

        coursModel = new CoursModelDB(salleController); // Passez salleController à CoursModelDB

        coursView = new CoursViewConsole(salleController); // Passez salleController à CoursViewConsole

        coursController = new CoursController(coursModel, coursView);

        coursView.setSalleView(salleView);

        salleModel.addObserver(salleView);
        coursModel.addObserver(coursView);

        List<String> loptions = Arrays.asList("salles", "cours", "fin");
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
                    System.exit(0);
            }
        } while (true);
    }

    public static void main(String[] args) {
        GestEcole gestEcole = new GestEcole();
        gestEcole.gestion();
    }
}
