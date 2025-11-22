package main;
import entities.bullets.Bullets;
import entities.equipements.Equipements;
import entities.equipements.armes.BouclierBois;
import entities.players.JeanGuy;
import input.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import entities.players.*;

import entities.players.*;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
//    private Panel panel = new Panel();
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
    private boolean victory=false;

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
    public final int victoryState = 5;
    int playerX = 200;
    int playerY = 200;



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);
        prepareGame();
    }

    public void incrementMenuCommand() {
        UI.commandNum++;
    }

    public void decrementMenuCommand() {
        UI.commandNum--;
    }

    public void setMenuCommand(int value) {
        UI.commandNum = value;
    }

    public int getMenuCommand() {
        return UI.commandNum;
    }

    public void setTitleScreenState(int state) {
        UI.titleScreenState = state;
    }

    public int getTitleScreenState() {
        return UI.titleScreenState;
    }

    public void setGameState(int state) {
        this.GameState = state;
    }

    public int getGameState() {
        return this.GameState;
    }

    public void prepareGame() {
        GameState = titleState;
        UI.titleScreenState = 0;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    public void instantiateMonsters(){
        String npcs= null;
        npcs = info.get(tileM.getIndex() + 1);
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
            if (npcs.charAt(i) == 'P'){
                Odette odette = new Odette(this);
                personnages.add(odette);
            }
            if (npcs.charAt(i) == 'S'){
                Marchand marchand = new Marchand(this);
                personnages.add(marchand);
            }
            if (npcs.charAt(i) == 'A'){
                BouclierBois bouclierBois = new BouclierBois(this);
                equipements.add(bouclierBois);
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

        // Vide les listes
        personnages.clear();
        equipements.clear();
        bullets.clear();
        setVictory(false);


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
    }

    public void setAddplayers(boolean addplayers) {
        this.addplayers = addplayers;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public TileManager getTileM() {
        return tileM;}

    public int getOriginalTileSize(){
        return originalTileSize;
    }

    public int getScale(){
        return scale;
    }

    public Thread getGameThread() {
        return gameThread;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

    public int getFPS() {
        return FPS;
    }

    public void noMonstersOutChunk(){
        for (Players p : personnages) {
            if(!(p instanceof Playable)){
                p.setDead(true);
            }
        }
    }
    public void noBulletsoutChunk(){
        for (Bullets b : bullets) {
            b.setChangeChunk(false);

        }
    }

    public void noEquipementsOutChunk(){
        for (Equipements e : equipements) {
            e.setRamasser();

        }
    }
    public boolean getVictory() {
        return this.victory;
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
        else if (GameState == victoryState){
            UI.update();
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        Graphics2D g2 = (Graphics2D)g;

        // TITLE SCREEN

        if(GameState == titleState || GameState == commandState){
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




