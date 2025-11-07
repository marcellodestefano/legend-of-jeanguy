package obstacles;

abstract class Obstacles{
    private boolean traversable;
    private static int id = 0;
    private String spritePath;
    private boolean IsMoovable;

    public Obstacles(boolean traversable, String spritePath, boolean IsMovable){

        this.traversable = traversable;
        this.spritePath = spritePath;
        this.IsMovable = IsMovable;

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
        return IsMovable;
    }
}