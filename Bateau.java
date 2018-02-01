public abstract class Bateau
{
    String name;
    int cases;
    int shootingRange;
    // lowest abscissa
    int lifePoint = 2;

    public int getHurt(){
        lifePoint--;
        if (lifePoint==0){
            return 0;
        }else return 1;
    }
}
