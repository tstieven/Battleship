import java.util.Scanner;

public class Battleship {
    private static int  continu =1;
    private static void print(Object a) {
        System.out.println(a);
    }

    private static void game(int a){
        new GameBoard();
        // "2" for test
        //"1" will be available if we make a bot
        GameBoard.init("2");
        int isFinished = 0;
        int actualPlayer = 0;
        while (isFinished == 0 ){

            isFinished = GameBoard.shoot(actualPlayer);
            actualPlayer = (actualPlayer +1)%2;
        }
        //TODO Gameloop


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

           game(2);
           fin(sc);

            }
        }
    }
}

