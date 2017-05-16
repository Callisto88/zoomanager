package Test;

import java.util.Scanner;

/**
 * Created by lapin on 15.05.17.
 */
abstract public class CLI {

    public static int menu() {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Sélectionner une action : ");
        System.out.println("-------------------------\n");
        System.out.println("1 - Créer une nouvelle commande avec son contenu");
        System.out.println("2 - Afficher une commande d'après son ID");
        System.out.println("3 - Affiche le contenu d'une commande d'après son ID");
        System.out.println("4 - Lister les commandes selon leur statut");
        System.out.println("5 - Lister les commandes selon un range de date");
        System.out.println("6 - Lister les commandes selon un statut et un range de date");
        System.out.println("7 - Quit");

        selection = input.nextInt();
        return selection;
    }
}
