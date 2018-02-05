public class Torpilleur extends Bateau {
    Torpilleur(int player){
        this.player = player;
        cases = 2;
        pos = new int[cases][2];
        shootingRange=5;
        shootingDirection="Traverse";
    }
}
