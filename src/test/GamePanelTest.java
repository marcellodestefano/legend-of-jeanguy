package test;

import entities.bullets.Bullets;
import entities.bullets.Octorokatk;
import entities.equipements.Equipements;
import entities.equipements.armes.BouclierBois;
import entities.players.Gumba;
import entities.players.JeanGuy;
import entities.players.Octorok;
import entities.players.Players;
import input.KeyHandler;
import main.GamePanel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GamePanelTest {

    GamePanel gamePanel =  new GamePanel();
    KeyHandler keyHandler = new KeyHandler(gamePanel);

    @Test
    @DisplayName("Test des constantes de taille")
    void testTileSizeConstants() {
        assertEquals(16, gamePanel.getOriginalTileSize());
        assertEquals(3, gamePanel.getScale());
        assertEquals(48, gamePanel.getTileSize());
    }

    @Test
    @DisplayName("Test des dimensions de l'écran")
    void testScreenDimensions() {
        assertEquals(16, gamePanel.getMaxScreenCol());
        assertEquals(12, gamePanel.getMaxScreenRow());
        assertEquals(768, gamePanel.getScreenWidth());
        assertEquals(576, gamePanel.getScreenHeight());
    }

    @Test
    @DisplayName("Test de l'initialisation du panneau")
    void testPanelInitialization() {
        Dimension expectedSize = new Dimension(768, 576);
        assertEquals(expectedSize, gamePanel.getPreferredSize());
        assertEquals(Color.black, gamePanel.getBackground());
        assertTrue(gamePanel.isFocusable());
    }

    @Test
    @DisplayName("Test du démarrage du thread de jeu")
    void testStartGameThread() {
        gamePanel.startGameThread();

        assertNotNull(gamePanel.getGameThread());
        assertTrue(gamePanel.getGameThread().isAlive());
        gamePanel.setGameThread(null);
    }

    /*@Test
    @DisplayName("Test de update - suppression des balles inactives")
    void testUpdateRemovesInactiveBullets() {


        Players sender = new Octorok(gamePanel);
        Players receiver = new JeanGuy(gamePanel, keyHandler);

        Bullets activeBullet = new Octorokatk(gamePanel, sender, receiver);
        activeBullet.setPosition(200, 200);

        Bullets inactiveBullet = new Octorokatk(gamePanel, sender, receiver);
        inactiveBullet.setPosition(190, 50);

        System.out.println(activeBullet.getPosition());
        System.out.println(inactiveBullet.getPosition());
        gamePanel.bullets.add(activeBullet);
        gamePanel.bullets.add(inactiveBullet);

        //System.out.println(gamePanel.bullets);

        gamePanel.update();

        System.out.println(activeBullet.getIsActive());
        System.out.println(inactiveBullet.getIsActive());

        System.out.println(gamePanel.bullets.size());

        assertEquals(1, gamePanel.bullets.size(),
                "Seule la balle active devrait rester");
    }*/

    @Test
    @DisplayName("suppression des personnages morts")
    void testUpdateRemovesDeadCharacters() {

        Players enemy = new Gumba(gamePanel);

        gamePanel.getPersonnages().add(enemy);

        enemy.setHp(0);
        assertTrue(enemy.isDead());
        gamePanel.update();

        assertEquals(1, gamePanel.getPersonnages().size());
    }

    @Test
    @DisplayName("JeanGuy mort n'est pas supprimé")
    void testUpdateDoesNotRemoveDeadJeanGuy() {

        assertEquals(1, gamePanel.getPersonnages().size());

        gamePanel.getPersonnages().get(0).setHp(0);

        gamePanel.update();

        assertEquals(1, gamePanel.getPersonnages().size());
    }

    @Test
    @DisplayName("Suppression des équipements ramassés")
    void testUpdateRemovesCollectedEquipment() {
        Equipements equip = new BouclierBois(gamePanel);

        gamePanel.getEquipements().add(equip);

        equip.setRamasser();

        gamePanel.update();

        assertFalse(gamePanel.getEquipements().contains(equip),
                "L'équipement ramassé devrait être supprimé");
    }

    @Test
    @DisplayName("Test de la FPS")
    void testFPS() {
        assertEquals(60, gamePanel.getFPS(),
                "Le FPS devrait être de 60");
    }

    @Test
    @DisplayName("Test du GameState qui ne doit pas être null")
    void testGameState() {

        assertNotNull(gamePanel.getGameState());
    }

}
