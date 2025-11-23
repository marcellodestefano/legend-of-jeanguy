package entities.players;
import entities.equipements.Equipements;

import java.awt.*;
import java.util.*;
import java.util.List;

/*
Méthodes communes à tous les joueurs

Cette interface définit les actions de base que tous les joueurs (joueurs jouables et non jouables) doivent implémenter.
Elle inclut des méthodes pour obtenir le nom, les points de vie, la position,
attaquer, et recevoir des dégâts.
On a aussi les méthods nous permettant de acceder aux ressources graphiques et sonores.
Les méthodes de déplacement vont probablement être supprimées ou modifiées plus tard.
 */
interface Actions{

    //Getters
    String getName();
    int getDamage();
    List<Integer> getPosition();
    List<String> getSpritePaths();
    int getAttackSpeed();
    int getRange();
    int getHp();
    float getSpeed();
    boolean isMelee();
    boolean isDead();
    boolean isKillable();
    List<String> getSoundPaths();
    String getDirection();

    // Setters
    void setHp(int hp);
    void setDead(boolean dead);
    void setKillable(boolean kill);
    void setDirection(String direction);

    // Actions
    void receiveDamage(int damage, String dir);
    void receiveDamage(Players sender, int damage, String dir);
    void getPlayerImage();
    void checkDrop(Players players);
    void dropItem(Equipements equipements);
    void update();
    void draw(Graphics2D g2);

}