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
    private boolean addplayers = false;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public JeanGuy jeanGuy = new JeanGuy(this, keyHandler);
    TileManager tileM = new TileManager(this, jeanGuy);
    public ArrayList<Equipements> equipements = new ArrayList<>();
    public ArrayList<Bullets> bullets = new ArrayList<>();
    protected ArrayList<String> info;



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);
        prepareGame();
    }

    public void prepareGame() {
        personnages.add(jeanGuy);


    }
    public void instateMonsters(){
        String npcs= null;
        int index=-1;
        System.out.println("Inst Mons "+info.size());;
        for (int i =0; i < info.size();i++) {
            if (info.get(i).equals("NPC")){
                index = i;
            }
        }
        if (index!=-1) {
            npcs = info.get(index + 1);
            System.out.println(npcs);
            for(int i=0; i < npcs.length();i++){
                if (npcs.charAt(i) == 'G'){
                    Gumba gumba = new Gumba(this);
                    personnages.add(gumba);
                }
                if (npcs.charAt(i) == 'B'){
                    Bat bat = new Bat(this);
                    personnages.add(bat);
                }
                if (npcs.charAt(i) == 'M'){
                    MaskGuy maskGuy = new MaskGuy(this);
                    personnages.add(maskGuy);
                }
                if (npcs.charAt(i) == 'O'){
                    Octorok octorok = new Octorok(this);
                    personnages.add(octorok);
                }

            }
        }
        for(Players np : personnages){
            if (np instanceof NonPlayable enemy){
                enemy.cible(jeanGuy);
            }
        }

        this.addplayers = false;
        this.info.clear();
        tileM.clearInfo();

    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
        messageCounter = 0;
    }

    public void setInfo(ArrayList<String> info){
        this.info = info;
        System.out.println("set "+info);
    }

    public void setAddplayers(boolean addplayers) {
        this.addplayers = addplayers;
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

        if(addplayers){
            instateMonsters();
        }
//        System.out.println(personnages.size());

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
        }
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





