package main;
import entities.bullets.Bullets;
import entities.equipements.Equipements;
import entities.equipements.armes.BouclierBois;
import entities.equipements.soins.Coeur;
import entities.equipements.soins.CoeurMax;
import entities.players.JeanGuy;
import input.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import entities.players.*;

import entities.players.*;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3; //for now
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public ArrayList<Players> personnages = new ArrayList<>();
    final int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    TileManager tileM = new TileManager(this);
    public JeanGuy jeanGuy = new JeanGuy(this, keyHandler);
    BouclierBois bbo = new BouclierBois(this);
    Coeur coeur = new Coeur(this);
    CoeurMax coeurmax = new CoeurMax(this);
    public ArrayList<Equipements> equipements = new ArrayList<>();
    MaskGuy maskGuy = new MaskGuy(this);
    MaskGuy maskGuy2 = new MaskGuy(this);
    Octorok octorok = new Octorok(this);
    Bat bat = new Bat(this);
    Gumba gumba = new Gumba(this);
    public ArrayList<Bullets> bullets = new ArrayList<>();
    int playerX = jeanGuy.getPosition().get(0);
    int playerY = jeanGuy.getPosition().get(1);


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
        personnages.add(gumba);
        personnages.add(octorok);
        equipements.add(bbo);

        for(Players np : personnages){
            if (np instanceof NonPlayable enemy){
                enemy.cible(jeanGuy);
            }
        }

    }

    public void checkChunkTransition() {

        int screenWidth = maxScreenCol * tileSize;
        int screenHeight = maxScreenRow * tileSize;

        if(playerX > screenWidth) {
            tileM.changeChunk("EAST");
            playerX = tileSize;
        }

        else if(playerX < 0) {
            tileM.changeChunk("WEST");
            playerX = screenWidth - tileSize * 2;
        }

        else if(playerY < 0) {
            tileM.changeChunk("NORTH");
            playerY = screenHeight - tileSize * 2;
        }

        else if(playerY > screenHeight) {
            tileM.changeChunk("SOUTH");
            playerY = tileSize;
        }
    }

    public void checkZoneTransition() {
        int playerTileX = playerX / tileSize;
        int playerTileY = playerY / tileSize;

        // ici ça servira pour rentrer dans le shop
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
        for (Bullets b: bullets){
            b.update();
        }

        for(Equipements e : equipements){
            e.update();
        }
        bullets.removeIf(b -> !(b.getIsActive()=="ok"));
        personnages.removeIf(p -> p.isDead() &&  !(p instanceof JeanGuy));
        equipements.removeIf(e -> e.isRamasser());

        checkChunkTransition();
        checkZoneTransition();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        tileM.draw(g);

        Graphics2D g2 = (Graphics2D)g;
        for (Players p : personnages) {
            p.draw(g2);
        }
        for (Bullets b: bullets){
            b.draw(g2);
        }
        for (Equipements e : equipements) {
            e.draw(g2);
        }
        g2.dispose();
    }
    }




