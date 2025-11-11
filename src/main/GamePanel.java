package main;
import input.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import entities.players.*;

import entities.players.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3; //for now
    public final int tileSize = originalTileSize * scale;

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    public ArrayList<Players> personnages = new ArrayList<>();
    final int FPS = 55;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public JeanGuy jeanGuy = new JeanGuy(this, keyHandler);
    public MaskGuy maskGuy = new MaskGuy(this);
    MaskGuy maskGuy2 = new MaskGuy(this);
    Bat bat = new Bat(this);



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        prepareGame();
    }

    public void prepareGame() {


        personnages.add(maskGuy);
        personnages.add(jeanGuy);
        personnages.add(bat);


        for(Players np : personnages){
            if (np instanceof NonPlayable enemy){
                enemy.cible(jeanGuy);
            }
        }

    }
    public void removeDead(Players player){
        personnages.remove(player);
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
        for (Players p : personnages) {
            p.update();

        }
        personnages.removeIf(p -> p.isDead() && !(p instanceof Bat)&& !(p instanceof JeanGuy));

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        for (Players p : personnages) {
            p.draw(g2);
        }
        g2.dispose();
    }
    }




