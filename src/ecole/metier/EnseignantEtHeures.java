package ecole.metier;

import java.util.Objects;

/**
 * Représente l'association entre un enseignant et le nombre d'heures dans une classe
 *
 * @author Audry Dupont
 * @version 1.0
 * @see Enseignant
 */
public class EnseignantEtHeures {

    /**
     * L'enseignant
     */
    private Enseignant enseignant;

    /**
     * Le nombre d'heures
     */
    private int heures;

    /**
     * Constructeur de l'association entre un enseignant et le nombre d'heures
     *
     * @param enseignant L'enseignant
     * @param heures     Le nombre d'heures
     */

    public EnseignantEtHeures(Enseignant enseignant, int heures) {
        this.enseignant = enseignant;
        this.heures = heures;
    }

    /**
     * Récupère l'enseignant
     *
     * @return L'enseignant
     */
    public Enseignant getEnseignant() {
        return enseignant;
    }

    /**
     * Récupère le nombre d'heures
     *
     * @return Le nombre d'heures
     */
    public int getHeures() {
        return heures;
    }

    /**
     * Représentation textuelle des informations
     *
     * @return La représentation textuelle
     */
    @Override
    public String toString() {
        return "Enseignant : " + enseignant.getNom() + " " + enseignant.getPrenom() + " - " + heures + " heures" + "\n";
    }


}
