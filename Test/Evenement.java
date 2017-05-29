package Test;

import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Intervenant;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

public class Evenement {

    public static void main(String arg[]) throws SQLException {

        int userChoice;

        do {
            userChoice = menu();

            switch (userChoice) {

                case 1:
                    System.out.println("Entrer l'ID de l'événement : ");
                    Scanner input = new Scanner(System.in);
                    int eventID = Integer.parseInt(input.next());
                    selAllIntervenantsParEvenementId(eventID);
                    break;

                case 2:
                    selEventTypes();
                    break;

                case 3:
                    System.out.println("Indiquez le nom du nouveau type d'événement : ");
                    Scanner input3 = new Scanner(System.in);
                    String eventType = String.valueOf(input3.next());
                    insEventType(eventType);
                    break;

                case 4:
                    System.out.println("Indiquez le type d'événement à supprimer : ");
                    Scanner input4 = new Scanner(System.in);
                    String eventType2 = String.valueOf(input4.next());
                    delEventType(eventType2);
                    break;

                case 5:
                    System.out.println("Pour l'exemple, l'objet Evenement suivant sera crée : ");
                    Model.Evenement testEvent = new Model.Evenement(104,
                            "Evenement de test",
                            new Timestamp(29177829),
                            "Animation");
                    testEvent.toString();
                    insEvent(testEvent);
                    break;

                case 6:
                    System.out.println("Entrer l'ID de l'événement à supprimer : ");
                    Scanner input5 = new Scanner(System.in);
                    int eventID2 = Integer.parseInt(input5.next());
                    delEvent(eventID2);
                    break;

                case 7:
                    System.out.println("Entrer l'ID de l'événement concerné : ");
                    Scanner input6 = new Scanner(System.in);
                    int eventID3 = Integer.parseInt(input6.next());
                    selAnimalsByEventID(eventID3);
                    break;

                case 8:
                    System.out.println("Entrer l'ID de l'événement concerné : ");
                    Scanner input7 = new Scanner(System.in);
                    int eventID4 = Integer.parseInt(input7.next());
                    selPeopleByEventID(eventID4);
                    break;

                case 9:
                    selEventsHavingAnimal();
                    break;

                case 10:
                    System.out.println("Entrer l'ID de l'événement concerné : ");
                    Scanner input10 = new Scanner(System.in);
                    int eventID10 = Integer.parseInt(input10.next());
                    System.out.println("Entrer l'ID de l'animal concerné : ");
                    Scanner input11 = new Scanner(System.in);
                    int animalID10 = Integer.parseInt(input11.next());
                    delAnimalFromEvent(animalID10, eventID10);
                    break;

                case 11:
                    System.out.println("Entrer l'ID de l'événement concerné : ");
                    Scanner input111 = new Scanner(System.in);
                    int eventID11 = Integer.parseInt(input111.next());
                    System.out.println("Entrer l'ID de l'intervenant concerné : ");
                    Scanner s11 = new Scanner(System.in);
                    int intervenantID11 = Integer.parseInt(s11.next());
                    assignEvenementIntervenant(eventID11, intervenantID11);
                    break;

                case 12:
                    System.out.println("Entrer l'ID de l'événement concerné : ");
                    Scanner input112 = new Scanner(System.in);
                    int eventID12 = Integer.parseInt(input112.next());
                    System.out.println("Entrer l'ID de l'intervenant concerné : ");
                    Scanner s12 = new Scanner(System.in);
                    int intervenantID12 = Integer.parseInt(s12.next());
                    delIntervenantEvenement(intervenantID12, eventID12);
                    break;

                case 13:
                    System.out.println("Entrer le type d'événement : ");
                    Scanner input113 = new Scanner(System.in);
                    String eventType3 = String.valueOf(input113.next());
                    selEventsByEventType(eventType3);
                    break;

                case 14:
                    selAllEvents();
                    break;

                case 0:
                    break;
            }

        } while (userChoice != 0);
    }

    public static int menu() {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Sélectionner une action : ");
        System.out.println("-------------------------\n");
        System.out.println("1 - Afficher tous les intervenants impliqués par événement");
        System.out.println("2 - Afficher tous les types d'événements existants");
        System.out.println("3 - Insérer un nouveau type d'événement");
        System.out.println("4 - Supprimer un type d'événement");
        System.out.println("5 - Insérer un nouvel événement");
        System.out.println("6 - Supprimer un événement");
        System.out.println("7 - Afficher tous les animaux impliqués dans un événement");
        System.out.println("8 - Afficher toutes les personnes impliqués dans un événement");
        System.out.println("9 - Afficher tous les événements impliquant au moins un animal");
        System.out.println("10 - Retirer un animal d'un événement");
        System.out.println("11 - Assigner un intervenant à un événement");
        System.out.println("12 - Retirer un intervenant d'un événement");
        System.out.println("13 - Afficher tous les événements d'un type donné");
        System.out.println("14 - Afficher tous les événements");
        System.out.println("0 - Quitter le programme");

        selection = input.nextInt();
        return selection;
    }

    public static void selAllIntervenantsParEvenementId(int eventID) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            ArrayList<Intervenant> result = query.selAllIntervenantsParEvenementId(eventID);
            for (Intervenant i : result) {
                System.out.println(i.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }

    public static void selEventTypes() {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            ArrayList<String> result = query.selEventTypes();
            for (String s : result) {
                System.out.println(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase e) {
            e.printStackTrace();
        }
    }

    public static void insEventType(String eventType) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            query.insEventType(eventType);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase e) {
            e.printStackTrace();
        }
    }

    public static void delEventType(String eventType) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            query.delEventType(eventType);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase e) {
            e.printStackTrace();
        }
    }

    public static void insEvent(Model.Evenement newEvent) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            int newEventID = query.insertEvenement(newEvent);
            if (newEventID != 0) {
                System.out.println("L'événement a été enregistré avec succès, son ID est : " + newEventID);
            } else {
                System.out.println("Il y a eu un problème lors de l'enregistrement de l'événement");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delEvent(int eventID) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            boolean success = query.delEventByID(eventID);
            System.out.println("L'événement " + (success ? "a été supprimé avec succès" : "n'a pas pu être supprimé"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }

    public static void selAnimalsByEventID(int eventID) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            ArrayList<Model.Animal> result = query.selAnimalsByEventID(eventID);
            for (Model.Animal a : result) {
                System.out.println(a.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }

    public static void selPeopleByEventID(int eventID) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            ArrayList<Model.Personne> result = query.selPeopleByEventID(eventID);
            for (Model.Personne p : result) {
                System.out.println(p.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }

    public static void selEventsHavingAnimal() {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            ArrayList<Model.Animal_Evenement> result = query.selEventsHavingAnimal();
            for (Model.Animal_Evenement s : result) {
                System.out.println(s.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }

    public static void delAnimalFromEvent(int animalID, int eventID) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            boolean success = query.delAnimalEvent(animalID, eventID);
            System.out.println((success ? "L'animal portant l'ID " + animalID + "a bien été retiré de l'événement ID " + eventID : "Erreur"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void assignEvenementIntervenant(int eventID, int intervenantID) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            Model.Evenement e = new Model.Evenement(eventID);
            Model.Intervenant i = new Model.Intervenant(intervenantID);
            int interEventID = query.assignEvenementIntervenant(e, i);
            if (interEventID != 0) {
                System.out.println("L'événement ID " + eventID + " a bien été assigné à l'intervenant : " + intervenantID);
            } else {
                System.out.println("Erreur lors de l'assignation, soit l'événement soit l'intervenant n'existe pas");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void delIntervenantEvenement(int intervenantID, int eventID) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            Model.Evenement e = new Model.Evenement(eventID);
            Model.Intervenant i = new Model.Intervenant(intervenantID);
            boolean success = query.delIntervenantEvenement(intervenantID, eventID);
            System.out.println(success);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selEventsByEventType(String eventType) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            ArrayList<Model.Evenement> results = query.selEventsByEventType(eventType);
            for (Model.Evenement e : results) {
                System.out.println(e.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }

    public static void selAllEvents() {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            ArrayList<Model.Evenement> results = query.selAllEvents();
            for (Model.Evenement e : results) {
                System.out.println(e.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }
}
