package entities.bullets;

import entities.players.Players;
import main.GamePanel;

import java.util.Arrays;

public class Octorokatk extends Bullets{
    public Octorokatk(GamePanel gamePanel, Players sender, Players receiver) {
        super(gamePanel, sender, receiver, Arrays.asList("/assets/ennemies/shootingmob/the_bullet.png"),2, 3);

    }
}
