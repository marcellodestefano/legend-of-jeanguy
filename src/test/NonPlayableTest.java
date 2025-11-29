package test;

import entities.players.Gumba;
import entities.players.JeanGuy;
import entities.players.NonPlayable;
import entities.players.Playable;
import main.GamePanel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class NonPlayableTest {

    GamePanel gamePanel = new GamePanel();
    NonPlayable enemy = new Gumba(gamePanel);

    @Test
    @DisplayName("Test de possition initial")
    void startPositionTest(){

        enemy.startPosition();

        assertNotNull(enemy.getPosition().get(0));
        assertNotNull(enemy.getPosition().get(1));

    }

    /*@Test
    @DisplayName("Test de l'instance de la cible des monstres")
    void testInstanceCible(){

        gamePanel.startGame();
        gamePanel.getPersonnages().add(enemy);
        gamePanel.setAddplayers(true);
        gamePanel.update();
        //gamePanel.instantiateMonsters();

        assertInstanceOf(JeanGuy.class, enemy.getCible());
    }*/

    @Test
    @DisplayName("checkSpeed retourne la vitesse normale quand pas de dégâts")
    void testCheckSpeedNormal() {
        enemy.setCpdmgTest(0);

        assertEquals(enemy.getSpeed(), enemy.checkSpeed());
    }

    @Test
    @DisplayName("checkSpeed retourne 5 quand en train de recevoir des dégâts")
    void testCheckSpeedWhileDamaged() {
        enemy.setCpdmgTest(2);
        assertEquals(5, enemy.checkSpeed());
    }

    @Test
    @DisplayName("checkSpeed restaure la vitesse après les dégâts")
    void testCheckSpeedAfterDamage() {
        enemy.setCpdmgTest(3);
        enemy.checkSpeed();
        assertEquals(5, enemy.checkSpeed());

        enemy.setCpdmgTest(0);
        enemy.checkSpeed();
        assertEquals(enemy.getSpeed(), enemy.checkSpeed());
    }

    @Test
    @DisplayName("getValue retourne une valeur aléatoire comprise entre 0 et maxValue")
    void testGetValue(){

        assertTrue(enemy.getValue()>=0);
        assertTrue(enemy.getValue()<= enemy.getMaxvalue());
    }

    @Test
    @DisplayName("getDmgdir retourne la direction des dégâts")
    void testGetDmgdir() {
        enemy.setDmgdir("up-player");

        assertEquals("up-player", enemy.getDmgdir());
    }

    @Test
    @DisplayName("setCpdmg décrémente le compteur")
    void testSetCpdmg() {
        enemy.setCpdmgTest(8);

        enemy.setCpdmg();

        assertEquals(7, enemy.getCpdmg());
    }

    @Test
    @DisplayName("Test de reception des dégats")
    void TestReceiveDamage(){
        enemy.receiveDamage(1, "up-player");

        assertEquals(2, enemy.getHp());
        assertEquals("up-player", enemy.getDmgdir());
        assertFalse(enemy.isKillable());
        assertEquals(8, enemy.getCpdmg());
    }

    @Test
    @DisplayName("Test de mort des monstres")
    void testDeathAfterExcessiveDamage() {

        enemy.receiveDamage(3, "down-player");

        assertEquals(0, enemy.getHp());
        assertTrue(enemy.isDead());
    }


}
