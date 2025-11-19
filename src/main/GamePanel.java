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
    private String message = "";
    private boolean messageOn = false;
    private int messageCounter = 0;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public JeanGuy jeanGuy = new JeanGuy(this, keyHandler);
    TileManager tileM = new TileManager(this, jeanGuy);
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



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);
        prepareGame();
    }

    public void prepareGame() {
//        personnages.add(maskGuy);
        personnages.add(jeanGuy);
//        personnages.add(bat);
//        personnages.add(gumba);
//        personnages.add(octorok);
//        equipements.add(bbo);

        for(Players np : personnages){
            if (np instanceof NonPlayable enemy){
                enemy.cible(jeanGuy);
            }
        }

    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
        messageCounter = 0;
    }



    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public TileManager getTileM() {
        return tileM;
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

        if(messageOn){
            messageCounter++;
            if(messageCounter >= 60){
                messageOn = false;
                messageCounter = 0;
            }
        }

        bullets.removeIf(b -> !(b.getIsActive()=="ok"));
        personnages.removeIf(p -> p.isDead() &&  !(p instanceof JeanGuy));
        equipements.removeIf(e -> e.isRamasser());

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);



        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        for (Equipements e : equipements) {
            e.draw(g2);
       
        
        for (Bullets b: bullets){
            b.draw(g2);
        }
          for (Players p : personnages) {
            p.draw(g2);
        }

        if (messageOn) {
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            g2.setColor(Color.RED);

            // Centrer le texte
            FontMetrics metrics = g2.getFontMetrics();
            int x = (getWidth() - metrics.stringWidth(message)) / 2;
            int y = getHeight() / 2;

            // Fond semi-transparent
            g2.setColor(new Color(0, 0, 0, 180));
            g2.fillRect(x - 10, y - 25, metrics.stringWidth(message) + 20, 35);

            // Texte
            g2.setColor(Color.WHITE);
            g2.drawString(message, x, y);
        }

        g2.dispose();
    }

}




