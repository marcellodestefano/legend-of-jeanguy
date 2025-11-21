package utils;

import main.GamePanel;

import java.util.ArrayList;
import java.util.Random;

public class CreationMonstres {
    public static ArrayList<Integer> creationMonstres(GamePanel gamePanel) {
        Random r = new Random();
        int x = 0;
        int y = 0;

        x = r.nextInt(gamePanel.screenWidth - gamePanel.tileSize)+1;
        y = r.nextInt(gamePanel.screenHeight - gamePanel.tileSize)+1;

        return new ArrayList<Integer>();
    }
}
