package entities.players;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

import input.KeyHandler;
import main.GamePanel;

abstract class Players implements Actions{

    protected String name;
    protected int damage;
    protected List<Integer> position = new ArrayList<Integer>();
    protected int range;
    protected int hp;
    protected int speed;
    protected boolean isDead = false;
    protected boolean isMelee;
    protected int attackSpeed;
    protected boolean killable;
    protected List<String> soundPaths = new ArrayList<>();
    protected List<String> spritesPaths = new ArrayList<>();
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    protected String direction = "down";
    protected int spriteCounter = 0;
    protected int spriteNum = 1;
    protected GamePanel gamePanel;




    public Players(GamePanel panel,String name, int damage, List<Integer> position, int range, int hp, int speed,boolean isMelee, int attackSpeed, boolean killable, List<String> soundPaths, List<String> spritesPaths ) {
        this.name = name;
        this.damage = damage;
        this.position = position;
        this.range = range;
        this.hp = hp;
        this.speed = speed;
        this.isMelee = isMelee;
        this.attackSpeed = attackSpeed;
        this.killable = killable;
        this.soundPaths = soundPaths;
        this.spritesPaths = spritesPaths;
        this.getPlayerImage();
        this.gamePanel = panel;
    }



    public String getName() {
        return this.name;
    }
    public int getDamage() {
        return this.damage;
    }
    public List<Integer> getPosition() {
        return this.position;

    }
    public int getRange() {
        return this.range;
    }

    public int getHp() {
        return this.hp;
    }

    public float getSpeed() {
        return this.speed;
    }

    public boolean isMelee() {
        return this.isMelee;
    }

    public boolean isDead() {
        return this.isDead;
    }

    public void killed(){
        this.isDead = true;
    }

    public boolean isKillable() {
        return this.killable;
    }

    public List<String> getSoundPaths() {
        return this.soundPaths;
    }

    public List<String> getSpritePaths() {
        return this.spritesPaths;
    }

    public boolean moveR(){
        List<Integer> pos = this.getPosition();
        pos.set(0, pos.get(0) + 2);
        return true;
    }

    public boolean moveL(){
        List<Integer> pos = this.getPosition();
        pos.set(0, pos.get(0) - 2);
        return true;

    }
    public boolean moveU(){
        List<Integer> pos = this.getPosition();
        pos.set(1, pos.get(1) + 2);
        return true;
    }
    public boolean moveD(){
        List<Integer> pos = this.getPosition();
        pos.set(1, pos.get(1) - 2);
        return true;

    }



    public boolean attackDistance(Players cible){
        double distance;
        distance = Math.pow(Math.pow((cible.getPosition().get(0)-this.getPosition().get(0)),2)+Math.pow((cible.getPosition().get(1)-this.getPosition().get(1)),2),0.5);
        return this.range > distance;
    }

    public boolean attackKillable(Players cible){
        return cible.isKillable();
    }

    public void receiveDamage(int damage){
        this.hp -= damage;
    }


    public boolean attack(Players cible){
        if (this.attackDistance(cible) && this.attackDistance(cible)){
            cible.receiveDamage(this.getDamage());
            return true;
        }else{
            return false;
        }
    }
    public void getPlayerImage() {
        try{
            this.up1 = ImageIO.read(getClass().getResourceAsStream(this.spritesPaths.get(0)));
            this.up2 = ImageIO.read(getClass().getResourceAsStream(this.spritesPaths.get(1)));
            this.down1 = ImageIO.read(getClass().getResourceAsStream(this.spritesPaths.get(2)));
            this.down2 = ImageIO.read(getClass().getResourceAsStream(this.spritesPaths.get(3)));
            this.left1 = ImageIO.read(getClass().getResourceAsStream(this.spritesPaths.get(4)));
            this.left2 = ImageIO.read(getClass().getResourceAsStream(this.spritesPaths.get(5)));
            this.right1 = ImageIO.read(getClass().getResourceAsStream(this.spritesPaths.get(6)));
            this.right2 = ImageIO.read(getClass().getResourceAsStream(this.spritesPaths.get(7)));
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void update() {
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
            switch(direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
            }
            g2.drawImage(image, position.get(0), position.get(1), gamePanel.tileSize, gamePanel.tileSize, null);
        };


}









