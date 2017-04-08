package Model;

/**
 *
 * Cette classe contient la conception de la table Oiseau de la base de données
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
public class Oiseau {

    private double envergure;
    private String bague;

    public double getEnvergure() {
        return envergure;
    }

    public void setEnvergure(double envergure) {
        this.envergure = envergure;
    }

    public String getBague() {
        return bague;
    }

    public void setBague(String bague) {
        this.bague = bague;
    }
}
