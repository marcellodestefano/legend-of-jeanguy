package entities.players;
import java.util.*;


interface Actions{
    /*
    Méthodes communes à tous les joueurs

    Cette interface définit les actions de base que tous les joueurs (joueurs jouables et non jouables) doivent implémenter.
    Elle inclut des méthodes pour obtenir le nom, les points de vie, la position,
    attaquer, et recevoir des dégâts.
    On a aussi les méthods nous permettant de acceder aux ressources graphiques et sonores.
    Les méthodes de déplacement vont probablement être supprimées ou modifiées plus tard.
     */
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