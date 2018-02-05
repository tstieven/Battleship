public class PorteAvion extends Bateau {
    PorteAvion(int player){
        this.player = player;
        cases = 5;
        pos = new int[cases][2];
        shootingRange=2;
        shootingDirection="Transverse";
    }
}
