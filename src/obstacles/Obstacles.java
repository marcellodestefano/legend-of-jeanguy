package obstacles;

public abstract class Obstacles{
    private boolean traversable;
    private static int id = 0;
    private String spritePath;
    private boolean isMoovable;

    public Obstacles(boolean traversable, String spritePath, boolean isMovable){

        this.traversable = traversable;
        this.spritePath = spritePath;
        this.isMoovable = isMovable;

        id = id++;
    }

    public boolean getTraversable(){
        return traversable;
    }

    public int getId(){
        return id;
    }

    public String getSpritePath(){
        return spritePath;
    }

    public boolean isMovable(){
        return isMoovable;
    }
}