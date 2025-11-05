package main.java.com.LegendOfJG.entities;
import java.util.*;

abstract class Players implements Actions{

    protected String name;
    protected int damage;
    protected List<Integer> position = new ArrayList<Integer>();
    protected int range;
    protected int hp;
    protected float speed;
    protected boolean isDead;
    protected boolean isMelee;

}