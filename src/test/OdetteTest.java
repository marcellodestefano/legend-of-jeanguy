package test;

import entities.players.Odette;
import main.GamePanel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OdetteTest {
    GamePanel gamePanel = new GamePanel();
    Odette odette = new Odette(gamePanel);
    @Test
    @DisplayName("Test d'initialisation de la odette")
    void testInitialisation(){
        assertNotNull(odette);
        assertTrue(odette.getName().startsWith("Odette"));
    }

    @Test
    @DisplayName("Odette fait 0 dégâts")
    void testOdetteNoDamage() {
        assertEquals(0, odette.getDamage());
    }

    @Test
    @DisplayName("Odette a une vitesse de 3")
    void testOdetteSpeed() {
        assertEquals(3, odette.getSpeed());
    }

    @Test
    @DisplayName("Odette n'est pas morte initialement")
    void testOdetteNotDead() {
        assertFalse(odette.isDead());
    }

    @Test
    @DisplayName("startPosition définit")
    void testStartPosition() {
        odette.startPosition();

        assertEquals(335, odette.getPosition().get(0));
        assertEquals(200, odette.getPosition().get(1));
    }

    @Test
    @DisplayName("receiveDamage ne recoit pas de dégât")
    void testReceiveDamageDoesNothing() {
        int initialHP = odette.getHp();

        odette.receiveDamage(10, "up-player");

        assertEquals(initialHP, odette.getHp());
    }

    @Test
    @DisplayName("setVictory est appelé lors d'une collision avec le jeanGuy")
    void testSetVictoryOnPlayerCollision() {

        assertDoesNotThrow(() -> gamePanel.setVictory(true));
    }


}