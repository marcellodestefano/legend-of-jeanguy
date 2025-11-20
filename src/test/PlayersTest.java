package test;

import entities.equipements.Equipements;
import entities.equipements.armes.EpeeBois;
import entities.players.*;
import input.KeyHandler;
import main.GamePanel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayersTest {

    GamePanel gamePanel = new GamePanel();
    KeyHandler keyHandler = new KeyHandler(gamePanel);

    JeanGuy jeanGuy = new JeanGuy(gamePanel, keyHandler);

    @Test
    void bat(){

        Bat bat = new Bat(gamePanel);

        System.out.println(bat.getName());
        System.out.println(bat.getId());
        System.out.println(bat.getDamage());
        System.out.println(bat.getAttackSpeed());
        bat.startPosition();
        System.out.println(bat.getPosition());
        System.out.println(bat.getRange());

        System.out.println(bat.checkSpeed());
        System.out.println(bat.getSpeed());
        System.out.println(bat.isMelee());


        System.out.println(bat.getSoundPaths());

        System.out.println(bat.getHp());
        bat.receiveDamage(1, "down");
        System.out.println(bat.isKillable());
        System.out.println(bat.getHp());
        System.out.println(bat.isDead());

        System.out.println(bat.getValue());
        System.out.println(bat.getDmgdir());
        System.out.println(bat.getCpdmg());
        bat.setCpdmg();
        System.out.println(bat.getCpdmg());
    }

    @Test
    void gumba(){

        Gumba gumba = new Gumba(gamePanel);

        System.out.println(gumba.getName());
        System.out.println(gumba.getId());
        System.out.println(gumba.getDamage());
        System.out.println(gumba.getAttackSpeed());
        gumba.startPosition();
        System.out.println(gumba.getPosition());
        System.out.println(gumba.getRange());

        System.out.println(gumba.checkSpeed());
        System.out.println(gumba.getSpeed());
        System.out.println(gumba.isMelee());


        System.out.println(gumba.getSoundPaths());

        System.out.println(gumba.getHp());
        gumba.receiveDamage(1, "down");
        System.out.println(gumba.isKillable());
        System.out.println(gumba.getHp());
        System.out.println(gumba.isDead());

        System.out.println(gumba.getValue());
        System.out.println(gumba.getDmgdir());
        System.out.println(gumba.getCpdmg());
        gumba.setCpdmg();
        System.out.println(gumba.getCpdmg());
    }

    @Test
    void maskGuy(){

        MaskGuy maskGuy = new MaskGuy(gamePanel);

        System.out.println(maskGuy.getName());
        System.out.println(maskGuy.getId());
        System.out.println(maskGuy.getDamage());
        System.out.println(maskGuy.getAttackSpeed());
        maskGuy.startPosition();
        System.out.println(maskGuy.getPosition());
        System.out.println(maskGuy.getRange());

        System.out.println(maskGuy.checkSpeed());
        System.out.println(maskGuy.getSpeed());
        System.out.println(maskGuy.isMelee());


        System.out.println(maskGuy.getSoundPaths());

        System.out.println(maskGuy.getHp());
        maskGuy.receiveDamage(1, "down");
        System.out.println(maskGuy.isKillable());
        System.out.println(maskGuy.getHp());
        System.out.println(maskGuy.isDead());

        System.out.println(maskGuy.getValue());
        System.out.println(maskGuy.getDmgdir());
        System.out.println(maskGuy.getCpdmg());
        maskGuy.setCpdmg();
        System.out.println(maskGuy.getCpdmg());
    }

    @Test
    void octorok(){
        Octorok octorok = new Octorok(gamePanel);

        System.out.println(octorok.getName());
        System.out.println(octorok.getId());
        System.out.println(octorok.getDamage());
        System.out.println(octorok.getAttackSpeed());
        octorok.startPosition();
        System.out.println(octorok.getPosition());
        System.out.println(octorok.getRange());

        System.out.println(octorok.checkSpeed());
        System.out.println(octorok.getSpeed());
        System.out.println(octorok.isMelee());


        System.out.println(octorok.getSoundPaths());

        System.out.println(octorok.getHp());
        octorok.receiveDamage(1, "down");
        System.out.println(octorok.isKillable());
        System.out.println(octorok.getHp());
        System.out.println(octorok.isDead());

        System.out.println(octorok.getValue());
        System.out.println(octorok.getDmgdir());
        System.out.println(octorok.getCpdmg());
        octorok.setCpdmg();
        System.out.println(octorok.getCpdmg());
    }

    /* @Test
    void genie(){
        GamePanel gamePanel = new GamePanel();
        Genie genie = new Genie(gamePanel);
        Genie genie2 = new Genie(gamePanel);

        System.out.println(genie.getName());
        System.out.println(genie2.getId());
        System.out.println(genie.getDamage());

        System.out.println(genie.getPosition());
        System.out.println(genie.getRange());
        System.out.println(genie.getHp());
        System.out.println(genie.checkSpeed());
        System.out.println(genie.getSpeed());
        System.out.println(genie.isMelee());
        System.out.println(genie.isDead());
        genie.setKillable(true);
        System.out.println(genie.isKillable());
        System.out.println(genie.getSoundPaths());
        System.out.println(genie.attackDistance(genie2));
        genie.receiveDamage(1, "down");
        System.out.println(genie.attack(genie2));
        System.out.println(genie.getValue());
        System.out.println(genie.getDmgdir());
        System.out.println(genie.getCpdmg());
        genie.setCpdmg();
        System.out.println(genie.getCpdmg());
    } */

    @Test
    void jeanGuy(){

        Equipements arme = new EpeeBois(gamePanel);

        System.out.println(jeanGuy.getName());
        System.out.println(jeanGuy.getDamage());
        System.out.println(jeanGuy.getAttackSpeed());
        System.out.println(jeanGuy.getPosition());
        System.out.println(jeanGuy.getRange());

        System.out.println(jeanGuy.getSpeed());
        System.out.println(jeanGuy.isMelee());


        System.out.println(jeanGuy.getSoundPaths());

        System.out.println(jeanGuy.getHp());
        jeanGuy.receiveDamage(1, "down");
        System.out.println(jeanGuy.getHp());
        System.out.println(jeanGuy.isKillable());
        System.out.println(jeanGuy.isDead());


        jeanGuy.ramasserCoeur();
        System.out.println(jeanGuy.getHp());

        System.out.println(jeanGuy.getHpMax());
        jeanGuy.ramasserCoeurMax();
        System.out.println(jeanGuy.getHpMax());

        jeanGuy.setArgent(10);
        System.out.println(jeanGuy.getArgent());

        jeanGuy.setInventaire(arme);
        System.out.println(jeanGuy.getInventaire());

    }

}