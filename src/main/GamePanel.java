package main;
import entities.bullets.Bullets;
import entities.equipements.Equipements;
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
    private boolean addplayers = false;

    KeyHandler keyHandler = new KeyHandler(this);
    Thread gameThread;
    public JeanGuy jeanGuy;
    TileManager tileM = new TileManager(this, jeanGuy);
    public ArrayList<Equipements> equipements = new ArrayList<>();
    public ArrayList<Bullets> bullets = new ArrayList<>();
    protected ArrayList<String> info;
    public UI UI = new UI(this);

    // GAME STATE

    public int GameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int commandState = 3;
    public final int gameOverState = 4;

    int playerX = 200;
    int playerY = 200;



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);
        prepareGame();
    }

    public void prepareGame() {
        GameState = titleState;
        UI.titleScreenState = 0;
    }

    public void instantiateMonsters(){
        String npcs= null;
        int index=-1;
        for (int i =0; i < info.size();i++) {
            if (info.get(i).equals("NPC")){
                index = i;
            }
        }
        if (index!=-1) {
            npcs = info.get(index + 1);
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
        for (Players p : personnages) {
            if ((p instanceof NonPlayable)) {
                ((NonPlayable) p).cible(jeanGuy);
            }
        }
        this.addplayers = false;
        this.info.clear();
        tileM.clearInfo();

    }

    public void startGame() {

        // Recrée Jean-Guy avec le bon skin
        jeanGuy = new JeanGuy(this, keyHandler);
        tileM = new TileManager(this, jeanGuy);

        // Vide et remplit les listes
        personnages.clear();
        equipements.clear();
        bullets.clear();


        personnages.add(jeanGuy);

    }

    public void resetGame() {

        tileM = new TileManager(this, jeanGuy);

        // Reset les positions
        playerX = 200;
        playerY = 200;

        // Recrée tout le jeu
        startGame();

        // Reset l'UI
        UI.gameOverAlpha = 0;
        UI.gameOverCounter = 0;
        UI.commandNum = 0;
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

    public void noMonstersOutChunk(){
        for (Players p : personnages) {
            if(!(p instanceof Playable)){
                p.setDead(true);
            }
        }
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

            if(jeanGuy != null) {
                playerX = jeanGuy.getPosition().get(0);
                playerY = jeanGuy.getPosition().get(1);
            }


            if(jeanGuy != null && jeanGuy.isDead()) {
                GameState = gameOverState;
                UI.commandNum = 0;
            }


        bullets.removeIf(b -> !(b.getIsActive()=="ok"));
        personnages.removeIf(p -> p.isDead() &&  !(p instanceof JeanGuy));
        equipements.removeIf(e -> e.isRamasser());
            for (Players p : personnages) {
                p.update();
            }
            for (Bullets b: bullets){
                b.update();
            }

            for(Equipements e : equipements){
                e.update();
            }

            if(addplayers){
                instantiateMonsters();
            }

            UI.update();
            bullets.removeIf(b -> !(b.getIsActive()=="ok"));
            personnages.removeIf(p -> p.isDead() &&  !(p instanceof JeanGuy));
            equipements.removeIf(e -> e.isRamasser());

        }
        else if(GameState == pauseState){
            UI.update();
        }
        else if(GameState == gameOverState){
            UI.update();
        }
        else if(GameState == titleState){
            UI.update();
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        Graphics2D g2 = (Graphics2D)g;

        // TITLE SCREEN

        if(GameState == titleState){
            UI.draw(g2);
        }
        else{ // Play state
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

        UI.draw(g2);

        g2.dispose();}
    }
}




