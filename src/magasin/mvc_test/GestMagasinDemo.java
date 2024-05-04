package magasin.mvc_test;

import magasin.mvc_test.controller.ProduitController;
import magasin.mvc_test.model.DAOProduit;
import magasin.mvc_test.model.ProduitModelDB;
import magasin.mvc_test.view.ProduitAbstractView;
import magasin.mvc_test.view.ProduitViewConsole;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestMagasinDemo {
     private DAOProduit pm;
     private ProduitController pc;
     private ProduitAbstractView pv;
    public void gestion(){
        pm=new ProduitModelDB();
        pv =  new ProduitViewConsole();
        pc= new ProduitController(pm,pv);
        pm.addObserver(pv);
        List<String> loptions = Arrays.asList("produits","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
               case 1: pv.menu();
                        break;
                case 2: System.exit(0);
            }
        }while(true);
    }
    public static void main(String[] args) {
      GestMagasinDemo gm = new GestMagasinDemo();
       gm.gestion();
    }
}
