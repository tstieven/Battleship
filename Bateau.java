public abstract class Bateau
{
    String name;
    int cases;
    int shootingRange;
    int player;
    boolean alive =true;
    // lowest abscissa
    int lifePoint = 2;

    public int getHurt(){
        lifePoint--;
        if (lifePoint==0){
            alive = false;
            return 0;
        }else return 1;
    }
}
