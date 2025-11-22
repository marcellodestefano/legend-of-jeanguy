package entities.equipements;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public abstract class Equipements {
    protected static int id;
    protected boolean drop;
    protected String name;
    protected int unite;
    protected List<String> spritePath;
    public BufferedImage equipementImage;
    protected GamePanel gp;
    protected ArrayList<Integer> position;
    protected boolean ramasser = false;

    public Equipements(GamePanel gp, boolean drop, String name, int unite, List<String> spritePath, ArrayList<Integer> position) {
        this.drop = drop;
        this.name = name;
        this.unite = unite;
        this.spritePath = spritePath;
        this.gp = gp;
        this.position =position;
        id = id++;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUnite() {
        return unite;
    }

    public List<String> getSpritePath() {
        return spritePath;
    }

    public boolean isRamasser() {
        return this.ramasser;
    }

    public void setisRamasser(boolean changeChunk) {
        this.ramasser = changeChunk;
    }

    public void getSpriteImage() {
        try {
            this.equipementImage = ImageIO.read(getClass().getResourceAsStream(this.spritePath.get(0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getPosition(){
        return this.position;
    }

    public void setRamasser() {
        this.ramasser = true;
    }

    public void draw(Graphics2D g) {
    }
    public void update() {
    }
}


