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

    public List<String> getSpritesPaths() {
        return this.spritesPaths;
    }








}