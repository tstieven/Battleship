import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Math.abs;

public class GameBoard {

    //min and max values for x and y
    static private int min = 0;
    static private int max = 10;

    static Bateau[][] gameBoardArray = new Bateau[max + abs(min)][max + abs(min)];

    static HashMap<String, Bateau> player1Boat = new HashMap<String, Bateau>();
    static HashMap<String, Bateau> player2Boat = new HashMap<String, Bateau>();

    private static String[] boatNameList = {"ContreTorpilleur", "Croiseur", "Porte Avion", "Sous-Marin", "Torpilleur"};
    private static String[] orientationList = {"Horizontal", "Vertical"};


    private static void print(Object a) {
        System.out.println(a);
    }

    private static void displayBoatList(){
        for (int i =0 ; i< boatNameList.length; i++){
            print( i + " pour " + boatNameList[i]);
        }
    }

    private static int boatChoice(int player, Scanner sc) {
        int choiceDone = 0;
        int nbr = -1;
        while (choiceDone == 0) {
            nbr = -1;
            boolean nbrNotOk = true;
            while (nbrNotOk) {
                print("joueur " + (player + 1) + " choisissez un bateau (0 à 4)");
                displayBoatList();
                // -48 for Ascii conversion, -1 in order to avec nbr between 0 and 4
                nbr = sc.nextInt();
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
            print("choisissez une valeur pour l'axe " + axe + " entre " + min + " et " + (max - 1));
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
                int j = 0;
                for (i = x; i < x + length; i++) {
                    gameBoardArray[i][y] = boat;
                    boat.pos[j][0] = i;
                    boat.pos[j][1] = y;
                    boat.orientation = 'h';
                    j++;
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
                int j = 0;
                for (i = y; i < y + length; i++) {
                    gameBoardArray[x][i] = boat;
                    boat.pos[j][0] = x;
                    boat.pos[j][1] = i;
                    boat.orientation = 'v';
                    j++;
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
                    if (gameBoardArray[i][j] == null) {
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
            int boatLeft = 2;
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
                        boat.boatInRange(); // erreur pour l'instant quand le bateau est vertical
                        display();
                    } else {
                        print("position du bateau deja prise ou hors du terrain");
                    }
                }
            }
        }
    }

    //TODO move
    public static void move(int player){


        Scanner sc = new Scanner(System.in);
        print("Voulez vous bouger un bateau? (Oui 1/ Non 0)");
        int movement = sc.nextInt();
        if(movement == 1) {

            Bateau boat = null;
            String boatName;

            int choiceDone = 0;
            while (choiceDone == 0) {
                print("Quel bateau voulez vous bouger?");
                displayBoatList();
                boatName = boatNameList[sc.nextInt()];
                int safeChoiceDone = 0;

                while (safeChoiceDone == 0) {
                    print("vous avez choisi " + boatName + " etes vous sur de votre choix ? (1 pour oui 0 pour non)");
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

                if (player == 0) {
                    boat = player1Boat.get(boatName);
                } else {
                    if (player == 1) {
                        boat = player2Boat.get(boatName);
                    } else {
                        print("pas un joueur possible");
                        System.exit(1);
                    }
                }
                if (boat.alive == false) {
                    choiceDone = 0;
                    print("Le bateau que vous avez choisi a deja ete coule");
                }
            }
            int mvPossible = 2;
            while(mvPossible>0) {
                Bateau tempBoat = boat.copy();
                for(int k=0; k<boat.pos.length; k++){
                    gameBoardArray[boat.pos[k][0]][ boat.pos[k][1]] = null;
                }
                print("Il vous reste " + mvPossible + "mouvement. Que voules vous faire? (Bouger 0/ Orienter 1/ RIEN)");
                switch(sc.next()){
                    case "0":
                        boatMove(boat, sc);
                        break;
                    case"1":
                        boatOrientation(boat, sc);
                        break;
                    default:
                        mvPossible = 0;
                        break;

                }

                if(!testLocation(boat.pos[0][0], boat.pos[0][1], boat.orientationToInt() , boat)){
                    boat = tempBoat.copy();
                    testLocation(boat.pos[0][0], boat.pos[0][1], boat.orientationToInt() , boat);
                    print("Ce mouvement est obstrué");

                }
                else{
                    display();
                    mvPossible--;
                }

            }
        }

    }

    public static void boatMove(Bateau boat, Scanner sc){

        boolean badDirection = true;
        while(badDirection){

            badDirection = false;
            print("Dans quel direction voulez vous bouger votre bateau? (HAUT / BAS / DROITE / GAUCHE ) ");
            switch (sc.next()){
                case "HAUT":
                    for(int i=0; i<boat.pos.length; i++){
                        boat.pos[i][1]++;
                    }
                   break;
                case "BAS":
                    for(int i=0; i<boat.pos.length; i++){
                        boat.pos[i][1]--;
                    }
                    break;
                case "DROITE":
                    for(int i=0; i<boat.pos.length; i++){
                        boat.pos[i][0]++;
                    }
                    break;
                case "GAUCHE":
                    for(int i=0; i<boat.pos.length; i++){
                        boat.pos[i][0]--;
                    }
                    break;
                default:
                    print("Ceci n'est pas une direction...");
                    badDirection = true;
                    break;

            }

        }

    }

    public static void boatOrientation(Bateau boat, Scanner sc){

        switch(orientationChoice(sc)) {
            case 1:
                boat.orientation = 'v';
                break;
            case 0:
                boat.orientation = 'h';
                break;
        }

    }


    //TODO shoot
    //inside shoot function, verify if the game is over

    public static int shoot(int player){

        String boatName = "";

        Bateau boat = null;
        Scanner sc = new Scanner(System.in);
        int choiceDone = 0;
        while (choiceDone == 0) {
            print("Quel bateau voulez vous utiliser?");
            displayBoatList();
            boatName = boatNameList[sc.nextInt()];
            int safeChoiceDone = 0;

            while (safeChoiceDone == 0) {
                print("vous avez choisi " + boatName + " etes vous sur de votre choix ? (1 pour oui 0 pour non)");
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
            if (player == 0){
                boat = player1Boat.get(boatName);
            }else {
                if (player == 1 ){
                    boat = player2Boat.get(boatName);
                }else{
                    print("pas un joueur possible");
                    System.exit(1);
                }
            }

            if (boat.alive == false){
                choiceDone = 0;
                print("Le bateau que vous avez choisi a deja ete coule");
            }

        }


        int x= 0,y = 0 ;
        int posValid = 0 ;

        while (posValid ==0) {
            x = entryInt("abcisse à viser entre 0-9");
            y = entryInt("ordonnée à viser entre 0-9");
            print("Vous avez entré les positions : " + x + " " + y);
            int[][] posRange = boat.boatInRange();
            for (int k = 0; k < posRange.length; k++){
                System.out.println(posRange[k][0] + " " + posRange[k][1]);
                if (x == posRange[k][0] && y == posRange[k][1]){
                    posValid = 1 ;

                    break;
                }
            }
            print(posValid);
        }

        if (gameBoardArray[x][y] != null) {
            int posPlayer = gameBoardArray[x][y].player;
            if ((posPlayer + player) % 2 == 1) {
                gameBoardArray[x][y].getHurt();
                if (isFinished()) {
                    print("bravo, vous avez gagné");
                    return 1;
                }
            }
        }else {
            int otherPlayer = (player + 1) % 2;
            move(otherPlayer);
        }

        return 0;

    }

    public static boolean isFinished(){

        boolean isPlayer1Alive = false;
        boolean isPlayer2Alive = false;
       /* for (int i = 0; i < boatNameList.length ; i++){
            if (player1Boat.get(boatNameList[i]).alive){
                isPlayer1Alive = true;
            }
        }
        for (int i = 0; i < boatNameList.length ; i++){
            if (player2Boat.get(boatNameList[i]).alive){
                isPlayer2Alive = true;
            }
        }*/

        if (player1Boat.get("ContreTorpilleur").alive){
            isPlayer1Alive = true;
        }
        print(player2Boat.get("Porte Avion").alive);
        if (player2Boat.get("Porte Avion").alive){
            isPlayer2Alive = true;
        }

        if (isPlayer1Alive && isPlayer2Alive) {
            return false;
        } else {
            return true;
        }

    }

    public static int entryInt ( String name){
        int a = 0;
        Scanner sc = new Scanner(System.in);
        int choiceDone = 0;
        while (choiceDone == 0) {
            print("Entrez le " + name +" que vous désirez?");
            a = sc.nextInt();
            int safeChoiceDone = 0;


            while (safeChoiceDone == 0) {
                print("vous avez choisi " + a + " etes vous sur de votre choix ? (1 pour oui 0 pour non)");
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
        return a;
    }

}
