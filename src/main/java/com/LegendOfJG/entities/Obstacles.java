abstract class Obstacles{
    private boolean traversable;
    private static int id = 0;
    private String spritePath;
    private boolean IsMoovable;

    public Obstacles(boolean traversable, String spritePath, boolean IsMoovable){

        this.traversable = traversable;
        this.spritePath = spritePath;
        this.IsMoovable = IsMoovable;

        this.id = id++;
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

    public boolean isMoovable(){
        return IsMoovable;
    }
}