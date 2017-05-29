package Model;

import java.util.HashMap;

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
 */
public class ExceptionDataBase extends Exception {
    // 0.
    private static final String NO_CONNEXION =
            "Erreur, impossible de se connecter à la base de données. ";
    // 1.
    private static final String NO_ELEMENT_IN_DB =
            "Aucun élément correspondant à la recherche n'a été trouvé dans la base de données. ";
    // 2.
    private static final String ANIMAL_PARAMETER_FALSE =
            "L'animal passé en paramètre n'est pas compatible avec la base de données. ";
    // 3.
    private static final String EVENEMENT_TYPE_FALSE =
            "Impossible d'ajouter ce type d'événement à la base de données. ";
    // 4.
    private static final String NO_CODE_POSTAL_ENTRED =
            "Le code postal doit être renseigné et être de type numérique. ";

    // ... Réserve pour des messages plus globaux [5-9]

    // 10.
    private static final String NO_INTERVENANT_IN_DB =
            "Aucun Intervenant n'est présent dans la base de données. ";
    // 11.
    private static final String NO_PERSONNE_IN_DB =
            "Aucune personne n'est présente dans la base de données. ";
    // 12.
    private static final String NO_ANIMAL_IN_DB =
            "Aucun animal n'est présent dans la base de données. ";
    // 13.
    private static final String NO_ENCLOS_IN_DB =
            "Aucun enclos n'est présent dans la base de données. ";
    // 14.
    private static final String NO_FELIN_IN_DB =
            "Aucun félin n'est présent dans la base de données. ";
    // 15.
    private static final String NO_OISEAU_IN_DB =
            "Aucun oiseau n'est présent dans la base de données. ";
    // 16.
    private static final String NO_REPTILE_IN_DB =
            "Aucun reptile n'est présent dans la base de données. ";
    // 17.
    private static final String NO_PRIMATE_IN_DB =
            "Aucun primate n'est présent dans la base de données. ";
    // 18.
    private static final String NO_ANINAML_RACE_IN_DB =
            "Aucune race d'animal n'est présent dans la base de données. ";
    // 19.
    private static final String NO_EVENEMENT_TYPE_IN_DB =
            "Aucun type d'événement n'est présent dans la base de données. ";
    // 20.
    private static final String NO_COMMANDE_IN_PROGRESS =
            "Aucune commande n'est en cours pour cet article. ";
    // 21.
    private static final String NO_STOCK =
            "Aucun élément n'est présent dans le stock. ";
    // 22.
    private static final String NO_COMMANDE =
            "Aucune commande n'est présente dans le stock. ";
    // 23.
    private static final String NO_ELEMENT_IN_COMMANDE =
            "Aucun produit n'est contenu dans la commande. ";
    // 24.
    private static final String NO_PAYS =
            "Aucun pays n'est présent dans la base de données. ";
    // 25.
    private static final String NO_ANIMAL_WITH_ID =
            "Aucun animal portant l'ID suivant n'est présent dans la base de données. ID : ";
    // 26.
    private static final String NO_ADD_EVENEMENT_TYPE_WITH_NAME =
            "Impossible d'ajouter le type d'événement avec le nom suivant dans la base de données. Nom : ";
    // 27.
    private static final String NO_EVENEMENT_WITH_ID =
            "Aucun événement portant l'ID suivant n'est présent dans la base de données. ID : ";
    // 28.
    private static final String NO_EVENEMENT_TYPE_WITH_NAME =
            "Aucun type d'événement portant l'ID suivant n'est présent dans la base de données. ID : ";
    // 29.
    private static final String NO_COMMANDE_WITH_ID =
            "Aucune commande portant l'ID suivant n'est présente dans la base de données. ID : ";
    // 30.
    private static final String NO_PERSONNE_STATUT_IN_PERSONNE_TABLE =
            "Tous les status des employés sont à \"null\". Il y a aucun statut dans la base de données. ";
    // 31.
    private static final String NO_PERSONNE_CONTRACT_IN_PERSONNE_TABLE =
            "Tous les contrats des employés sont à \"null\". Il y a aucun contrat dans la base de données. ";
    // 32.
    private static final String NO_EVENEMENT_ASSIGN_TO_INTERVENANT_WITH_ID =
            "Aucune événement n'est assigné à cet intervenant. ID : ";
    // 33.
    private static final String NO_EVENEMENT_ASSIGN_TO_PERSON_WITH_ID =
            "Aucune événement n'est assigné à cet personne. ID : ";
    // HashMap qui contient tous les messages d'erreurs
    private static final HashMap<Integer, String> MSG_ERROR = new HashMap<Integer, String>();
    private int num_Error;

    /**
     * Initialisation des messages d'erreurs dans le HashMap
     */
    static {
        MSG_ERROR.put(0, NO_CONNEXION);
        MSG_ERROR.put(1, NO_ELEMENT_IN_DB);
        MSG_ERROR.put(2, ANIMAL_PARAMETER_FALSE);
        MSG_ERROR.put(3, EVENEMENT_TYPE_FALSE);
        MSG_ERROR.put(4, NO_CODE_POSTAL_ENTRED);


        MSG_ERROR.put(10, NO_INTERVENANT_IN_DB);
        MSG_ERROR.put(11, NO_PERSONNE_IN_DB);
        MSG_ERROR.put(12, NO_ANIMAL_IN_DB );
        MSG_ERROR.put(13, NO_ENCLOS_IN_DB);
        MSG_ERROR.put(14, NO_FELIN_IN_DB);
        MSG_ERROR.put(15, NO_OISEAU_IN_DB);
        MSG_ERROR.put(16, NO_REPTILE_IN_DB);
        MSG_ERROR.put(17, NO_PRIMATE_IN_DB);
        MSG_ERROR.put(18, NO_ANINAML_RACE_IN_DB);
        MSG_ERROR.put(19, NO_EVENEMENT_TYPE_IN_DB);
        MSG_ERROR.put(20, NO_COMMANDE_IN_PROGRESS);
        MSG_ERROR.put(21, NO_STOCK);
        MSG_ERROR.put(22, NO_COMMANDE);
        MSG_ERROR.put(23, NO_ELEMENT_IN_COMMANDE);
        MSG_ERROR.put(24, NO_PAYS);
        MSG_ERROR.put(25, NO_ANIMAL_WITH_ID);
        MSG_ERROR.put(26, NO_ADD_EVENEMENT_TYPE_WITH_NAME);
        MSG_ERROR.put(27, NO_EVENEMENT_WITH_ID);
        MSG_ERROR.put(28, NO_EVENEMENT_TYPE_WITH_NAME);
        MSG_ERROR.put(29, NO_COMMANDE_WITH_ID);
        MSG_ERROR.put(30, NO_PERSONNE_STATUT_IN_PERSONNE_TABLE);
        MSG_ERROR.put(31, NO_PERSONNE_CONTRACT_IN_PERSONNE_TABLE);
        MSG_ERROR.put(32, NO_EVENEMENT_ASSIGN_TO_INTERVENANT_WITH_ID);
        MSG_ERROR.put(33, NO_EVENEMENT_ASSIGN_TO_PERSON_WITH_ID);
    }

    /**
     * Constructuer avec un int pour récupérer la valeur dans le HashMap
     *
     * @param index
     */
    public ExceptionDataBase(int index) {
        super((MSG_ERROR.get(index)));
        this.num_Error = index;
    }

    /**
     * Constructuer avec un int pour récupérer la valeur dans le HashMap
     *
     * @param index
     */
    public ExceptionDataBase(int index, int infoObjet) {
        super((MSG_ERROR.get(index) + infoObjet));
        this.num_Error = index;
    }

    /**
     * Constructuer avec un string pour récupérer la valeur dans le HashMap
     *
     * @param index
     */
    public ExceptionDataBase(int index, String infoObjet) {
        super((MSG_ERROR.get(index) + infoObjet));
        this.num_Error = index;
    }

    /**
     * Constructeur avec un String directement.
     * @param msg
     */
    public ExceptionDataBase(String msg) {
        super(msg);
    }

    /**
     * Getter qui permet de récuprer le message d'erreur
     *
     * @return String
     */
    public String getMsg () {
        return super.getMessage();
    }

    /**
     * Getter qui permet de récuprer le numero du message d'erreur
     *
     * @return int
     */
    public int getNum_Error() {
        return num_Error;
    }

}
