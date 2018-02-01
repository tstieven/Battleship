public class Torpilleur extends Bateau {
    Torpilleur(int x, int y , int orientation){
        cases = 2;
        shootingRange=5;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }
}
