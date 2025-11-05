package main.java.com.LegendOfJG.entities;
import java.util.*;

interface Actions{
    String getName();
    int getDamage();
    List<Integer> getPosition();
    boolean moveR(Salles);
    boolean moveU(Salles);
    boolean moveL(Salles);
    boolean moveD(Salles);
    int getHp();
    boolean attack(NonPlayable);
    boolean defend();
    String getSoundPath();
    boolean move(Obstacles);
    float getSpeed();

}