public class Croiseur extends Bateau {
    Croiseur(int player){
        this.player = player;
        cases = 4;
        pos = new int[cases][2];
        shootingRange=2;
        shootingDirection="Traverse";
    }

    public Croiseur copy(){
        Croiseur boatcpy = new Croiseur(this.player);
        for(int i = 0 ; i < this.pos.length ; i++){

            boatcpy.pos[i][0] = this.pos[i][0];
            boatcpy.pos[i][1] = this.pos[i][1];

        }
        boatcpy.orientation = this.orientation;
        boatcpy.lifePoint = this.lifePoint;



        return boatcpy;
    }
}
