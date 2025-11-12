package main;
import entities.equipements.Equipements;
import entities.equipements.armes.BouclierBois;
import entities.players.JeanGuy;
import input.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import entities.players.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3; //for now
    public final int tileSize = originalTileSize * scale;

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    final int FPS = 55;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public JeanGuy jeanGuy = new JeanGuy(this, keyHandler);
    public BouclierBois bbo = new BouclierBois(this);
    ArrayList<Equipements> equipements = new ArrayList<>();

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        long currentTime;
        double drawInterval = 1000000000/FPS;
        long lastTime = System.nanoTime();
        double deltaTime = 0;
        while (gameThread!=null){
            currentTime = System.nanoTime();
            deltaTime += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if(deltaTime >= 1) {
                update();
                repaint();
                deltaTime--;
            }

        }
    }
    public void update() {
        jeanGuy.update();
        bbo.update();

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        bbo.draw(g2);
        jeanGuy.draw(g2);

        g2.dispose();
    }
}

