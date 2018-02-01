import java.util.HashMap;
import java.util.Scanner;

public class GameBoard {

    //min and max values for x and y
    static private int min = -100;
    static private int max = 100;

    //static class Triplet{public int x,y; public Bateau boat;};

    static HashMap<String,Bateau> player1Boat= new HashMap<String,Bateau>();
    static HashMap<String,Bateau> player2Boat= new HashMap<String,Bateau>();

    private static String[] boatNameList ={"ContreTorpilleur", "Croiseur", "Porte Avion", "Sous-Marin" , "Torpilleur"};


    private static void print(Object a) {
        System.out.println(a);
    }


    private static int boatChoice(int player,Scanner sc){
        int choiceDone = 0;
        int nbr=-1;
        while (choiceDone == 0 ) {
            nbr=-1;
            boolean nbrNotOk = true;
            while (nbrNotOk) {
                print("joueur " + (player + 1) + " choisissez un bateau (1 à 5)");
                // -48 for Ascii conversion, -1 in order to avec nbr between 0 and 4
                nbr = sc.next().charAt(0) - 49;
                if ((nbr < 5) && (nbr > -1)) {
                    if (player == 0) {
                        if (player1Boat.isEmpty()){
                            nbrNotOk = false;
                        }else{
                            if (!player1Boat.containsKey(boatNameList[nbr])) {
                                nbrNotOk = false;
                            }else{print("Bateau deja utilise");}
                        }

                    } else {
                        if (!player2Boat.isEmpty()) {
                            nbrNotOk = false;
                        }else{
                            if (!player2Boat.containsKey(boatNameList[nbr])) {
                                nbrNotOk = false;
                            }else{print("Bateau deja utilise");}
                        }
                    }
                }
            }
            int safeChoiceDone=0;
            while (safeChoiceDone == 0 ) {
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



    private static int positionChoice(String axe ,Scanner sc) {
        int choiceDone = 0;
        int pos = min - 1;
        while (choiceDone == 0) {
            pos = min - 1;
            boolean nbrNotOk = true;
            while (nbrNotOk) {
            }


        }
        return pos;
    }
    public static Bateau createBoat(int nbr){
        switch (nbr){
            case 0: return new ContreTorpilleur();
            case 1: return new Croiseur();
            case 2: return new PorteAvion();
            case 3: return new SousMarin();
            case 4: return new Torpilleur();
        }
    }

    // a is equals to the number of players
    public static void init(String a){
        int nbr=-1;
        Scanner sc = new Scanner(System.in);
        if (a.equals("2")) {
            int player = 0;
            int boatLeft = 10;
            while (boatLeft != 0) {
                print("il reste "+ boatLeft + " a placer");
                nbr=boatChoice(player,sc);
                if (player==0){
                    player1Boat.put(boatNameList[nbr],createBoat(nbr));
                }else {  player2Boat.put(boatNameList[nbr],createBoat(nbr)); }
                boatLeft--;
                player = (player+1)%2;
            }
        }
    }
}
