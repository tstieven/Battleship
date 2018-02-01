public class Croiseur extends Bateau {
    Croiseur(int x, int y , int orientation){
        cases = 4;
        shootingRange=2;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }
}
