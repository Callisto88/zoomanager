package Model;

import Model.Tools.Date;

/**
 *
 * Cette classe contient la conception de la table Felin de la base de données
 *
 * Elle contient uniquement les Setters, Getters et Constructeurs.
 *
 * @author D.Hamel
 * @author C.Balboni
 *
 * @version 1.0
 *
 * @date    28.03.2017 (Création)
 * @date    28.03.2017 (Finalisation v1.0)
 *
 */
public class Felin extends Animal {

    private float poids;

    public Felin(String nom, String sexe, Date anneeNaissance, int enclos, String origine, String race, Date dateDeces, float poids) {
        super(nom, sexe, anneeNaissance, enclos, origine, race, dateDeces);
        this.poids = poids;
    }

    public float getPoids() {
        return this.poids;
    }
}
