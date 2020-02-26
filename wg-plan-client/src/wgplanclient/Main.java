package wgplanclient;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.setOut;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("__          _______        _____  _               ___  _  _   \n" +
                "\\ \\        / / ____|      |  __ \\| |             |__ \\| || |  \n" +
                " \\ \\  /\\  / / |  __ ______| |__) | | __ _ _ __      ) | || |_ \n" +
                "  \\ \\/  \\/ /| | |_ |______|  ___/| |/ _` | '_ \\    / /|__   _|\n" +
                "   \\  /\\  / | |__| |      | |    | | (_| | | | |_ / /_   | |  \n" +
                "    \\/  \\/   \\_____|      |_|    |_|\\__,_|_| |_(_)____|  |_|  \n\n");
        Requests requests = new Requests();
        Scanner myObj = new Scanner(System.in);

        while (true) {
            System.out.println("Methode Auswählen GET/PUT/DELETE:");
            String input = myObj.nextLine();
            switch (input) {
                case "get":
                    clearScreen();
                    System.out.println("   ********  ******** **********\n" +
                            "  **//////**/**///// /////**/// \n" +
                            " **      // /**          /**    \n" +
                            "/**         /*******     /**    \n" +
                            "/**    *****/**////      /**    \n" +
                            "//**  ////**/**          /**    \n" +
                            " //******** /********    /**    \n" +
                            "  ////////  ////////     //     \n \n");

                    System.out.println("Bitte geben Sie eine 0 für die ganze Liste ein oder eine spezifische ID");
                    String id = myObj.nextLine();
                    int idInt = Integer.parseInt(id);
                    requests.get(idInt);
                    break;
                case "delete":
                    clearScreen();
                    System.out.println(" *******   ******** **       ******** ********** ********\n" +
                            "/**////** /**///// /**      /**///// /////**/// /**///// \n" +
                            "/**    /**/**      /**      /**          /**    /**      \n" +
                            "/**    /**/******* /**      /*******     /**    /******* \n" +
                            "/**    /**/**////  /**      /**////      /**    /**////  \n" +
                            "/**    ** /**      /**      /**          /**    /**      \n" +
                            "/*******  /********/********/********    /**    /********\n" +
                            "///////   //////// //////// ////////     //     //////// \n \n");
                    System.out.println("Bitte geben Sie die ID des zu löschenden Elements ein!");
                    String Intid2 = myObj.nextLine();
                    int delid = Integer.parseInt(Intid2);
                    if (delid == 0) {
                        System.out.println("Das Item 0 gibt es nicht, bitte geben Sie eine valide ID ein");
                        break;
                    } else {
                    requests.delete(delid);
                        System.out.println("Das Item " + delid + " wurde gelöscht \n");
                    break;}


                case "put":
                    clearScreen();
                    System.out.println(" *******  **     ** **********\n" +
                            "/**////**/**    /**/////**/// \n" +
                            "/**   /**/**    /**    /**    \n" +
                            "/******* /**    /**    /**    \n" +
                            "/**////  /**    /**    /**    \n" +
                            "/**      /**    /**    /**    \n" +
                            "/**      //*******     /**    \n" +
                            "//        ///////      //     \n \n");

                    System.out.println("Bitte geben Sie die ID des Elements ein, welches bearbeitet werden soll ein!");
                    String Intid3 = myObj.nextLine();
                    int putid = Integer.parseInt(Intid3);
                    if (putid == 0){
                        System.out.println("Das Item 0 gibt es nicht, bitte geben Sie eine valide ID ein");
                        break;
                    }
                    System.out.println("Bitte geben Sie die den Namen der Person ein, die den Auftrag bearbeiten soll (60 Zeichen)");
                    String putname = myObj.nextLine();

                    System.out.println("Bitte geben Sie den Titel der Aufgabe ein!");
                    String putaufTitel = myObj.nextLine();

                    System.out.println("Bitte beschreiben Sie die Aufgabe kurz (280 Zeichen)");
                    String putaufBeschr = myObj.nextLine();

                    System.out.println("Setzen Sie den Status des Aufgabe (true/false)");
                    String putdone = myObj.nextLine();
                    boolean boolputdone = Boolean.parseBoolean(putdone);

                    requests.put(putid, putname, putaufTitel, putaufBeschr, boolputdone);
                    break;
                case "post":
                    clearScreen();
                    System.out.println(" *******                     **  \n" +
                            "/**////**                   /**  \n" +
                            "/**   /**  ******   ****** ******\n" +
                            "/*******  **////** **//// ///**/ \n" +
                            "/**////  /**   /**//*****   /**  \n" +
                            "/**      /**   /** /////**  /**  \n" +
                            "/**      //******  ******   //** \n" +
                            "//        //////  //////     //  \n \n");

                    System.out.println("Bitte geben Sie die den Namen der Person ein, die den Auftrag bearbeiten soll (60 Zeichen)");
                    String postname = myObj.nextLine();

                    System.out.println("Bitte geben Sie den Titel der Aufgabe ein!");
                    String postaufTitel = myObj.nextLine();

                    System.out.println("Bitte beschreiben Sie die Aufgabe kurz (280 Zeichen)");
                    String postaufBeschr = myObj.nextLine();

                    requests.post(postname, postaufTitel, postaufBeschr, false);
                    break;
                case "exit":
                    clearScreen();
                    exit(0);
                default:
                    System.out.println("Bitte geben Sie eine gültige Methode ein. Diese sind: get, put, post, delete. Oder beenden Sie das Programm mithilfe der eingabe \"exit\"!!");
                    break;
            }
        }



        //requests.get(0);
        //requests.post("jan", "saugen", "boden und Küche", false);
        //requests.delete(2);
        //requests.put(11, "schwachkopf_java", "saugen", "boden und Küche", true);
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

