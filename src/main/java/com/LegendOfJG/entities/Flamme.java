public class Flamme extends Obstacles{

    protected int range;
    protected int degats;

    public Flamme(int range, int degats){
        super(false, "", false);
        this.range = range;
        this.degats = degats;
    }

    public  int getRange(){
        return range;
    }

    public int getDegats(){
        return degats;
    }
}