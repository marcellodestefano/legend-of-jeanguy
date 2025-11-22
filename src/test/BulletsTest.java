package test;

import entities.bullets.Octorokatk;
import entities.players.JeanGuy;
import entities.players.Octorok;
import entities.players.Players;
import input.KeyHandler;
import main.GamePanel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BulletsTest {

    @Test

    void octorokatk(){

        GamePanel gamePanel = new GamePanel();
        KeyHandler keyHandler = new KeyHandler(gamePanel);
        Players sender = new Octorok(gamePanel);
        Players receiver = new JeanGuy(gamePanel, keyHandler);

        Octorokatk octorokatk = new Octorokatk(gamePanel, sender, receiver);

        octorokatk.calcSpeed();
        System.out.println(octorokatk.getIsActive());
        octorokatk.setIsActive();
        System.out.println(octorokatk.getIsActive());
        System.out.println(octorokatk.getPosition());

    }

}