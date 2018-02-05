public class Croiseur extends Bateau {
    Croiseur(int player){
        this.player = player;
        cases = 4;
        pos = new int[cases][2];
        shootingRange=2;
        shootingDirection="Traverse";
    }
}
