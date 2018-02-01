public class ContreTorpilleur extends Bateau {
    ContreTorpilleur(int x, int y , int orientation){
        cases = 3;
        shootingRange=2;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }
}
