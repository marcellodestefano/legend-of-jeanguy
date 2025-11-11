package entities.players;
import java.util.*;

    /*
    Méthodes communes à tous les joueurs

    Cette interface définit les actions de base que tous les joueurs (joueurs jouables et non jouables) doivent implémenter.
    Elle inclut des méthodes pour obtenir le nom, les points de vie, la position,
    attaquer, et recevoir des dégâts.
    On a aussi les méthods nous permettant de acceder aux ressources graphiques et sonores.
    Les méthodes de déplacement vont probablement être supprimées ou modifiées plus tard.
     */
interface Actions{
    String getName();
    int getDamage();
    List<Integer> getPosition();
    void getPlayerImage();
    int getHp();
    boolean attack(Players cible);
    //boolean defend();
    List<String> getSoundPaths();
    float getSpeed();
    void receiveDamage(int damage, String dir);

}