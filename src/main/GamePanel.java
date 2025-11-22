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
    private final int originalTileSize = 16;
    private final int scale = 3; //for now
    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 16;
    private  final int maxScreenRow = 12;
    private  final int screenWidth = tileSize * maxScreenCol;
    private  final int screenHeight = tileSize * maxScreenRow;
    private  ArrayList<Players> personnages = new ArrayList<>();
    private final int FPS = 60;
    private boolean addplayers = false;
    private boolean victory=false;

    KeyHandler keyHandler = new KeyHandler(this);
    Thread gameThread;
    private JeanGuy jeanGuy;
    private TileManager tileM = new TileManager(this, jeanGuy);
    private  ArrayList<Equipements> equipements = new ArrayList<>();
    private ArrayList<Bullets> bullets = new ArrayList<>();
    private ArrayList<String> info;
    private UI UI = new UI(this);

    // GAME STATE

    private int GameState;
    private final int titleState = 0;
    private final int playState = 1;
    private final int pauseState = 2;
    private final int commandState = 3;
    private final int gameOverState = 4;
    private final int victoryState = 5;
    private int playerX = 200;
    private int playerY = 200;



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);
        prepareGame();
    }

    public void incrementMenuCommand() {
        UI.plusCommandNum();
    }

    public void decrementMenuCommand() {
        UI.minusCommandNum();
    }

    public void setMenuCommand(int value) {
        UI.setCommandNum(value);
    }

    public int getMenuCommand() {
        return UI.getCommandNum();
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public int getScreenHeight(){
        return this.screenHeight;
    }

    public void setTitleScreenState(int state) {
        UI.setTitleScreenState(state);
    }

    public int getTitleScreenState() {
        return UI.getTitleScreenState();
    }

    public void setGameState(int state) {
        this.GameState = state;
    }

    public int getTileSize(){
        return this.tileSize;
    }

    public int getGameState() {
        return this.GameState;
    }

    public void prepareGame() {
        GameState = titleState;
        UI.setTitleScreenState(0);
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

    public int getTitleState(){
        return this.titleState;
    }
    public int getPauseState(){
        return this.pauseState;
    }
    public int getCommandState(){
        return this.commandState;

    }
    public int getGameOverState(){
        return this.gameOverState;
    }

    public int getVictoryState(){
        return this.victoryState;
    }
    public int getPlayState(){
        return this.playState;
    }
    public ArrayList<Equipements> getEquipements(){
        return this.equipements;
    }
    public ArrayList<Players> getPersonnages(){
        return this.personnages;
    }
    public ArrayList<Bullets> getBullets(){
        return this.bullets;
    }

    public UI getmyUI(){
        return this.UI;
    }
    public int getMaxScreenCol(){
        return this.maxScreenCol;
    }
    public int getMaxScreenRow(){
        return this.maxScreenRow;
    }





    public int getPlayerX(){
        return this.playerX;
    }
    public int getPlayerY(){
        return this.playerY;
    }



    public Playable getJeanGuy(){
        return this.jeanGuy;
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
        UI.setCommandNum(0);
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
                UI.setCommandNum(0);
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




