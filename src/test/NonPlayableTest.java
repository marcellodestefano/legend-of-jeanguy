package test;

import entities.players.Gumba;
import entities.players.JeanGuy;
import entities.players.NonPlayable;
import entities.players.Playable;
import main.GamePanel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NonPlayableTest {

    GamePanel gamePanel = new GamePanel();
    NonPlayable enemy = new Gumba(gamePanel);

    @Test
    @DisplayName("Test de possition initial")
    void startPositionTest(){


        enemy.startPosition();

        assertEquals(400, enemy.getPosition().get(0));
        assertEquals(50+enemy.getId()*100, enemy.getPosition().get(1));
        assertEquals(2, enemy.getPossibleDrops().size());
    }

    @Test
    @DisplayName("jns")
    void test(){
        System.out.println(enemy.getCible());
    }

}