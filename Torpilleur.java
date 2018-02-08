public class Torpilleur extends Bateau {
    Torpilleur(int player){
        this.player = player;
        cases = 2;
        pos = new int[cases][2];
        shootingRange=5;
        shootingDirection="Traverse";
    }


    public Torpilleur copy(){
        Torpilleur boatcpy = new Torpilleur(this.player);
        for(int i = 0 ; i < this.pos.length ; i++){

            boatcpy.pos[i][0] = this.pos[i][0];
            boatcpy.pos[i][1] = this.pos[i][1];

        }
        boatcpy.orientation = this.orientation;
        boatcpy.lifePoint = this.lifePoint;



        return boatcpy;
    }
}
