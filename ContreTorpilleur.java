public class ContreTorpilleur extends Bateau {
    ContreTorpilleur(int player){
        this.player = player;
        cases = 3;
        pos = new int[cases][2];
        shootingRange=2;
        shootingDirection="Transverse";
    }
}
