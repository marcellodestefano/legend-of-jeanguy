package test;

import entities.bullets.Bullets;
import entities.bullets.Octorokatk;
import entities.players.JeanGuy;
import entities.players.Octorok;
import entities.players.Players;
import input.KeyHandler;
import main.GamePanel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BulletsTest {

    GamePanel gamePanel = new GamePanel();
    KeyHandler keyHandler = new KeyHandler(gamePanel);
    Players sender = new Octorok(gamePanel);
    Players receiver = new JeanGuy(gamePanel, keyHandler);
    Bullets bullet = new Octorokatk(gamePanel, sender, receiver);

    @Test
    @DisplayName("Test d'initialistion des bullets")
    void initializationTest(){
        assertNotNull(bullet);
        assertEquals(2, bullet.getDamage());
        assertEquals(3, bullet.getSpeed());
        assertEquals("ok", bullet.getIsActive());
    }

    @Test
    @DisplayName("Position initiale qui est celle du sender")
    void testInitialPosition() {
        assertNotNull(bullet.getPosition().get(0));
        assertNotNull(bullet.getPosition().get(1));
    }

    @Test
    @DisplayName("setPosition change la position X")
    void testSetPositionX() {
        bullet.setPosition(150.0, 100.0);

        assertEquals(150.0, bullet.getPosition().get(0));
    }

    @Test
    @DisplayName("getPosition retourne la liste de positions")
    void testGetPosition() {
        List<Double> pos = bullet.getPosition();

        assertNotNull(pos);
        assertEquals(3, pos.size());
    }

}
