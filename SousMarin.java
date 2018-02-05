public class SousMarin extends Bateau {
    SousMarin(int player){
        this.player = player;
        cases = 3;
        pos = new int[cases][2];
        shootingRange=4;
        shootingDirection="Transverse";
    }
}
