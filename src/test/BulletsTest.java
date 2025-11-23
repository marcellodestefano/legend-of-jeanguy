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

}
