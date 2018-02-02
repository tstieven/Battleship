import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Math.abs;

public class GameBoard {

    //min and max values for x and y
    static private int min = -5;
    static private int max = 5;

    static Bateau[][] gameBoardArray = new Bateau[max + abs(min)][max + abs(min)];

    static HashMap<String, Bateau> player1Boat = new HashMap<String, Bateau>();
    static HashMap<String, Bateau> player2Boat = new HashMap<String, Bateau>();

    private static String[] boatNameList = {"ContreTorpilleur", "Croiseur", "Porte Avion", "Sous-Marin", "Torpilleur"};
    private static String[] orientationList = {"Horizontal", "Vertical"};


    private static void print(Object a) {
        System.out.println(a);
    }

    private static int boatChoice(int player, Scanner sc) {
        int choiceDone = 0;
        int nbr = -1;
        while (choiceDone == 0) {
            nbr = -1;
            boolean nbrNotOk = true;
            while (nbrNotOk) {
                print("joueur " + (player + 1) + " choisissez un bateau (1 à 5)");
                // -48 for Ascii conversion, -1 in order to avec nbr between 0 and 4
                nbr = sc.next().charAt(0) - 49;
                if ((nbr < 5) && (nbr > -1)) {
                    if (player == 0) {
                        if (player1Boat.isEmpty()) {
                            nbrNotOk = false;
                        } else {
                            if (!player1Boat.containsKey(boatNameList[nbr])) {
                                nbrNotOk = false;
                            } else {
                                print("Bateau deja utilise");
                            }
                        }

                    } else {
                        if (!player2Boat.isEmpty()) {
                            nbrNotOk = false;
                        } else {
                            if (!player2Boat.containsKey(boatNameList[nbr])) {
                                nbrNotOk = false;
                            } else {
                                print("Bateau deja utilise");
                            }
                        }
                    }
                }
            }
            int safeChoiceDone = 0;
            while (safeChoiceDone == 0) {
                print("vous avez choisi " + boatNameList[nbr] + " etes vous sur de votre choix ? (1 pour oui 0 pour non)");
                switch (sc.next()) {
                    case "0":
                        safeChoiceDone = 1;
                        break;
                    case "1":
                        safeChoiceDone = 1;
                        choiceDone = 1;
                        break;
                }
            }
        }
        return nbr;
    }

    private static int positionChoice(String axe, Scanner sc) {
        int choiceDone = 0;
        int pos = min - 1;
        while (choiceDone == 0) {
            pos = min - 1;
            print("choisissez une valeur pour l'axe " + axe + " entre " + min + " et " + max);
            print("cette valeur sera la valeur la plus basse du bateau sur l'axe " + axe);
            pos = sc.nextInt();
            if ((pos <= max) && (pos >= min)) {
                int safeChoiceDone = 0;
                while (safeChoiceDone == 0) {
                    print("vous avez choisi " + pos + " etes vous sur de votre choix ? (1 pour oui 0 pour non)");
                    switch (sc.next()) {
                        case "0":
                            safeChoiceDone = 1;
                            break;
                        case "1":
                            safeChoiceDone = 1;
                            choiceDone = 1;
                            break;
                    }
                }
            } else {
                print("valeur invalide");
            }
        }
        return pos;
    }

    private static int orientationChoice(Scanner sc) {
        int choiceDone = 0;
        int orientation = -1;
        while (choiceDone == 0) {
            orientation = -1;
            print("choisissez si votre bateau est place a l'horizontal ou a la vertical");
            print("0 pour horizontal et 1 pour vertical");
            orientation = sc.nextInt();
            if ((orientation == 0) || (orientation == 1)) {
                int safeChoiceDone = 0;
                while (safeChoiceDone == 0) {
                    print("vous avez choisi " + orientationList[orientation] + " etes vous sur de votre choix ? (1 pour oui 0 pour non)");
                    switch (sc.next()) {
                        case "0":
                            safeChoiceDone = 1;
                            break;
                        case "1":
                            safeChoiceDone = 1;
                            choiceDone = 1;
                            break;
                    }
                }
            } else {
                print("valeur invalide");
            }
        }
        return orientation;
    }

    private static Bateau createBoat(int nbr, int player) {
        switch (nbr) {
            case 0:
                return new ContreTorpilleur(player);
            case 1:
                return new Croiseur(player);
            case 2:
                return new PorteAvion(player);
            case 3:
                return new SousMarin(player);
            case 4:
                return new Torpilleur(player);
            default:
                return null;
        }
    }

    private static boolean testLocation(int x, int y, int orientation, Bateau boat) {
        int length = boat.cases;
        print(length);
        boolean free = true;
        if (orientation == 0) {
            int i ;
            for (i = x; i < x + length; i++) {
                if ((i <= max + abs(min)) && (i >= 0)) {
                    if (gameBoardArray[i][y] != null) {
                        free = false;
                    }
                } else {
                    free = false;
                }
            }
            if (free) {
                for (i = x; i < x + length; i++) {
                    gameBoardArray[i][y] = boat;
                }
            }
        } else {
            int i ;
            for (i = y; i < y + length; i++) {
                if ((i < max + abs(min)) && (i >= 0)) {
                    if (gameBoardArray[x][i] != null) {
                        free = false;
                    }
                } else {
                    free = false;
                }
            }
            if (free) {
                for (i = y; i < y + length; i++) {
                    gameBoardArray[x][i] = boat;
                }
            }
        }
        return free;
    }

    public static void display() {
        String displayString;
        for (int j = max + abs(min) -1; j > -1; j--) {
            displayString = "";
                for (int i = 0; i < max + abs(min); i++) {
                    if (gameBoardArray[i][j/2] == null) {
                        displayString += "| 0 |";
                    } else {
                        if (gameBoardArray[i][j].player == 0) {
                            displayString += "| 1 |";
                        } else {
                            displayString += "| 2 |";
                        }
                    }
                }


            print(displayString);
        }
    }

    // a is equals to the number of players
    public static void init(String a) {
        int nbr = -1;
        Scanner sc = new Scanner(System.in);
        if (a.equals("2")) {
            int player = 0;
            int boatLeft = 10;
            while (boatLeft != 0) {
                print("il reste " + boatLeft + " a placer");
                nbr = boatChoice(player, sc);
                boolean posChoiceNotOk = true;
                while (posChoiceNotOk) {
                    int x = positionChoice("x", sc) + abs(min);
                    int y = positionChoice("y", sc) + abs(min);
                    int orientation = orientationChoice(sc);
                    Bateau boat = createBoat(nbr,player);
                    if (testLocation(x, y, orientation, boat)) {
                        if (player == 0) {
                            player1Boat.put(boatNameList[nbr], boat);
                        } else {
                            player2Boat.put(boatNameList[nbr], boat);
                        }
                        boatLeft--;
                        player = (player + 1) % 2;
                        posChoiceNotOk = false;
                        display();
                    } else {
                        print("position du bateau deja prise ou hors du terrain");
                    }
                }
            }
        }
    }

    //TODO move

    //TODO shoot
    //inside shoot function, verify if the game is over
}
