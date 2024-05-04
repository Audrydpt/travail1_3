package magasin.mvc_test.model;

import magasin.metier.Produit;
import magasin.mvc_test.observer.Subject;

import java.util.List;

public abstract class DAOProduit extends Subject {
    public abstract Produit addProduit(Produit produit);

    public abstract boolean removeProduit(Produit produit);

    public abstract Produit updateProduit(Produit produit);

   public abstract Produit readProduit(int idProduit);

    public abstract List<Produit> getProduits();
}
