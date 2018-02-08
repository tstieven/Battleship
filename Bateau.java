import java.util.ArrayList;

public abstract class Bateau
{
    int cases;
    int shootingRange;
    String shootingDirection;
    int player;
    int[][] pos;
    char orientation;

    boolean alive =true;
    // lowest abscissa
    int lifePoint = 2;

    public int getHurt(){
        lifePoint--;

        System.out.println("You Touched a " + this.getClass());
        if (lifePoint==0){
            alive = false;
            return 0;
        }else return 1;
    }

    public int[][] boatInRange(){

        int[][] posRange;
        if (shootingDirection.equals( "Transverse")) {
            int k = 0;
            posRange = new int[cases * 2 * shootingRange][2];

            for (int i = 0; i < cases; i++) {
                for (int j = 0; j < shootingRange; j++) {
                    if (orientation == 'h') {

                        posRange[k][0] = pos[i][0];
                        posRange[k][1] = pos[i][1] + j + 1;
                        k++;
                        posRange[k][0] = pos[i][0];
                        posRange[k][1] = pos[i][1] - (j + 1);
                        k++;

                    }
                    if (orientation == 'v') {
                        posRange[k][0] = pos[i][0] + j + 1;
                        posRange[k][1] = pos[i][1];
                        k++;
                        posRange[k][0] = pos[i][0] - (j + 1);
                        posRange[k][1] = pos[i][1];
                        k++;

                    }
                }
            }

        } else{

            posRange = new int[2 * shootingRange][2];
            int k = 0;
            System.out.println(posRange.length);
            System.out.println(pos.length);
            for (int i = 0; i < shootingRange; i++) {
                if (orientation == 'h') {

                    posRange[k][0] = pos[pos.length-1][0]+ i + 1;
                    posRange[k][1] = pos[pos.length-1][1] ;
                    k++;
                    posRange[k][0] = pos[0][0]- (i + 1);
                    posRange[k][1] = pos[0][1] ;
                    k++;

                }
                if (orientation == 'v') {

                    if (pos[0][1] > pos[pos.length-1][1]){

                        posRange[k][0] = pos[0][0] ;
                        posRange[k][1] = pos[0][1]+ i + 1;
                        k++;
                        posRange[k][0] = pos[pos.length-1][0];
                        posRange[k][1] = pos[pos.length-1][1] - (i + 1);
                        k++;

                    }
                    else{
                        posRange[k][0] = pos[0][0] ;
                        posRange[k][1] = pos[0][1] - (i + 1);
                        k++;
                        posRange[k][0] = pos[pos.length-1][0];
                        posRange[k][1] = pos[pos.length-1][1] + i + 1;
                        k++;
                    }
                }
            }




        }

        for (int w = 0; w < posRange.length; w++){
            System.out.println(posRange[w][0] + " " + posRange[w][1]);
        }


        return posRange;
    }

    public abstract Bateau copy();

}
