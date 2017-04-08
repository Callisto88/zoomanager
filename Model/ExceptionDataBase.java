package Model;

/**
 *
 * Cette classe contient une classe héritant d'EXCEPTION
 *
 * Elle permet de géré nos propres exceptions avec quelques spécificités et donc de créer d'autre erreurs
 * Elle est un supplément à la classe SQLException pour la gestion du programme
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
public class ExceptionDataBase extends Exception {

    public ExceptionDataBase(String msg) {
        super(msg);
    }

    public String getMsg () {
        return super.getMessage();
    }

}
