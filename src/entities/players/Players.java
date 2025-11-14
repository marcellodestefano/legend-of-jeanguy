package entities.players;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

import input.KeyHandler;
import main.GamePanel;

public abstract class Players implements Actions{

    protected String name;
    protected int damage;
    protected List<Integer> position = new ArrayList<Integer>();
    protected int range;
    protected int hp;
    protected int speed;
    protected boolean Dead;
    protected boolean isMelee;
    protected int attackSpeed;
    protected boolean killable;
    protected List<String> soundPaths = new ArrayList<>();
    protected List<String> spritesPaths = new ArrayList<>();
    protected String direction = "down";
    protected int spriteCounter = 0;
    protected int spriteNum = 1;
    protected GamePanel gamePanel;
    protected int cpAtk;





    public Players(GamePanel panel,String name, int damage, List<Integer> position, int range, int hp, int speed,boolean Dead,boolean isMelee, int attackSpeed, boolean killable, List<String> soundPaths, List<String> spritesPaths ) {
        this.name = name;
        this.damage = damage;
        this.position = position;
        this.range = range;
        this.hp = hp;
        this.speed = speed;
        this.Dead = Dead;
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
    public int getAttackSpeed(){
        return this.attackSpeed;
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
        if (this.hp<= 0){
            this.Dead = true;
        }
        return this.Dead;
    }


    public boolean isKillable() {
        return this.killable;
    }
    public void setKillable(boolean kill){
        this.killable = kill;
    }

    public List<String> getSoundPaths() {
        return this.soundPaths;
    }


    public boolean attackDistance(Players cible){
        double distance;
        distance = Math.pow(Math.pow((cible.getPosition().get(0)-this.getPosition().get(0)),2)+Math.pow((cible.getPosition().get(1)-this.getPosition().get(1)),2),0.5);
        return this.range > distance;
    }


    public void receiveDamage(int damage, String dir){
        this.hp = Math.max(0, this.hp-damage);
    }


    public boolean attack(Players cible){
        if (this.attackDistance(cible) && this.attackDistance(cible)){
            cible.receiveDamage(this.getDamage(), this.direction);
            return true;
        }else{
            return false;
        }
    }

    public void getPlayerImage() {
    }
    public void update(){

    }


    public void draw(Graphics2D g2) {

//        BufferedImage image = null;
//            switch(direction) {
//                case "up":
//                    if (spriteNum == 1) {
//                        image = up1;
//                    }
//                    if (spriteNum == 2) {
//                        image = up2;
//                    }
//                    break;
//                case "down":
//                    if (spriteNum == 1) {
//                        image = down1;
//                    }
//                    if (spriteNum == 2) {
//                        image = down2;
//                    }
//                    break;
//                case "left":
//                    if (spriteNum == 1) {
//                        image = left1;
//                    }
//                    if (spriteNum == 2) {
//                        image = left2;
//                    }
//                    break;
//                case "right":
//                    if (spriteNum == 1) {
//                        image = right1;
//                    }
//                    if (spriteNum == 2) {
//                        image = right2;
//                    }
//                    break;
//            }
//            g2.drawImage(image, position.get(0), position.get(1), gamePanel.tileSize, gamePanel.tileSize, null);
        };


}









