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

    KeyHandler keyHandler = new KeyHandler(this);
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
    public UI UI = new UI(this);

    // GAME STATE

    public int GameState;
    public final int playState = 1;
    public final int pauseState = 2;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        setupGame();
        prepareGame();
    }

    public void setupGame(){
        GameState = playState;
    }

    public void prepareGame() {
        personnages.add(maskGuy);
        personnages.add(jeanGuy);
        personnages.add(bat);
        personnages.add(gumba);
        personnages.add(octorok);
        equipements.add(bbo);
        equipements.add(coeur);
        equipements.add(coeurmax);

        for(Players np : personnages){
            if (np instanceof NonPlayable enemy){
                enemy.cible(jeanGuy);
            }
        }

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

        if(GameState == playState){
            for (Players p : personnages) {
                p.update();

            }
            for (Bullets b: bullets){
                b.update();
            }

            for(Equipements e : equipements){
                e.update();
            }

            UI.update();

            bullets.removeIf(b -> !(b.getIsActive()=="ok"));
            personnages.removeIf(p -> p.isDead() &&  !(p instanceof JeanGuy));
            equipements.removeIf(e -> e.isRamasser());
        }
        if(GameState == pauseState){
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        tileM.draw(g);

        Graphics2D g2 = (Graphics2D)g;
        for (Equipements e : equipements) {
            e.draw(g2);
        }

        for (Players p : personnages) {
            p.draw(g2);
        }
        for (Bullets b: bullets){
            b.draw(g2);
        }

        UI.draw(g2);

        g2.dispose();
    }
    }




