import java.util.Scanner;

public class Battleship {
    private static int  continu =1;
    private static void print(Object a) {
        System.out.println(a);
    }

    private static void game(int a){
        new GameBoard();
        // "2" for test
        GameBoard.init("2");


    }

    private static void fin(Scanner sc){
        String a ="";
        print("partie terminée");
        while (!(a.equals("0"))&&!(a.equals("1"))){
            print("voulez vous continuer ou arreter (répondez 1 ou 0)");
            a = sc.next();
            print(a);
        }
        if (a.equals("0")){
            continu=0;
        }
    }

    public static void main(String[] args) {

        Scanner sc  = new Scanner(System.in);
        while (continu == 1) {
            print("Voulez vous jouez seul ou avec un deuxième personne (répondez 1 ou 2)");
            switch(sc.next()){
                case "1": game(1);
                fin(sc);
                break;

                case "2": game(2);
                fin(sc);
                break;
            }
        }
    }
}
