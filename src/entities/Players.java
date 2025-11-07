package main.java.com.LegendOfJG.entities;
import java.util.*;

abstract class Players implements Actions{

    protected String name;
    protected int damage;
    protected List<Integer> position = new ArrayList<Integer>();
    protected int range;
    protected int hp;
    protected float speed;
    protected boolean isDead = false;
    protected boolean isMelee;
    protected int attackSpeed;
    protected boolean killable;
    protected List<String> soundPaths = new ArrayList<>();
    protected List<String> spritesPaths = new ArrayList<>();



    public Players(String name, int damage, List<Integer> position, int range, int hp, float speed,boolean isMelee, int attackSpeed, boolean killable, List<String> soundPaths, List<String> spritesPaths ) {
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






}