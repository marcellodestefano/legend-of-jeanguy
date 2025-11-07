package entities.players;
import java.util.*;


interface Actions{
    String getName();
    int getDamage();
    List<Integer> getPosition();
    boolean moveR();
    boolean moveU();
    boolean moveL();
    boolean moveD();
    int getHp();
    boolean attack(Players cible);
    //boolean defend();
    List<String> getSoundPaths();
    List<String> getSpritePaths();
    float getSpeed();
    void receiveDamage(int damage);

}