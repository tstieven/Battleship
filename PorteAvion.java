public class PorteAvion extends Bateau {
    PorteAvion(int x, int y , int orientation){
        cases = 5;
        shootingRange=2;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }
}
