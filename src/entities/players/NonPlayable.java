package entities.players;


import entities.equipements.Equipements;
import entities.equipements.soins.Coeur;
import entities.equipements.soins.CoeurMax;
import main.GamePanel;
import utils.AlgorithmMovement;
import utils.Collisions;
import utils.CollisionsNpcMap;

import java.util.*;

public abstract class NonPlayable extends Players{
    protected int id;
    protected static int counter = 0;
    protected Playable cible;
    protected String dmgdir;
    protected int cpdmg = 0;
    protected int oldspeed, maxvalue=10;
    private List<Equipements> possibleDrops;




    public NonPlayable(GamePanel panel , String name, int damage, List<Integer> position, int range, int hp, int speed, boolean isDead, boolean isMelee, int attackSpeed, boolean killable, List<String> soundPaths, List<String> spritePaths) {
        super (panel, name, damage, position, range,  hp,  speed, isDead,  isMelee,  attackSpeed,  killable,  soundPaths,spritePaths);
        id = counter++;
        this.name = name + this.id;
        oldspeed = speed;
        this.startPosition();
    }


    public void startPosition(){
        this.position.set(0, 400);
        this.position.set(1, 100+(this.id%4)*100);


        this.possibleDrops = new ArrayList<Equipements>();

        possibleDrops.add(new Coeur(gamePanel));
        possibleDrops.add(new CoeurMax(gamePanel));
    }


    public void cible(Playable cible){
        this.cible = cible;
    }
    public int getId() {
        return id;
    }


    public int checkSpeed(){
        if (this.cpdmg!=0){
            return this.speed = 5;
        }else{
            return this.speed=oldspeed;
        }
    }

    public int getValue(){
        Random r = new Random();
        return r.nextInt(maxvalue);
    }

    public String getDmgdir(){

        return this.dmgdir;
    }
    public int getCpdmg(){
        return this.cpdmg;
    }
    public void setCpdmg(){
        this.cpdmg--;
    }

    public boolean canPass(String respass){
        return respass=="path";
    }


    @Override
    public void receiveDamage(int damage, String dir) {
        this.hp = Math.max(0, this.hp-damage);
        this.dmgdir = dir;
        this.cpdmg = 8;
        this.setKillable(false);
    }

    @Override
    public void update() {
        String dir = AlgorithmMovement.movements(gamePanel,this, cible);
        String atk = Collisions.collisions(gamePanel.personnages, this.direction, this, gamePanel.tileSize);
        String respass = CollisionsNpcMap.collisionsNpcMap(this,dir ,gamePanel.getTileM().getPathTiles(), gamePanel.getTileM().getMapTiles(), gamePanel, gamePanel.getTileM().getTiles());
        if(!(this.isDead())){
            if(atk=="down-player"||atk=="up-player"||atk=="left-player"||atk=="right-player"){
                if (cible.isKillable()){
                cible.receiveDamage(this, this.damage,atk);
            }}
            if (dir.contains("up")&&canPass(respass)) {
                direction = "up";
                spriteCounter++;
                position.set(1, Math.max(0,position.get(1) - checkSpeed()));
            }
            if (dir.contains("down")&&canPass(respass)) {
                direction = "down";
                spriteCounter++;
                position.set(1, Math.min(gamePanel.getHeight() - gamePanel.tileSize,position.get(1) + checkSpeed()));
            }
            if (dir.contains("left")&&canPass(respass)) {
                direction = "left";
                spriteCounter++;
                position.set(0, Math.max(0,position.get(0) - checkSpeed()));
            }
            if (dir.contains("right")&&canPass(respass)) {
                direction = "right";
                spriteCounter++;
                position.set(0, Math.min(gamePanel.getWidth() - gamePanel.tileSize,position.get(0) + checkSpeed()));
            }

            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }}
    }



}
