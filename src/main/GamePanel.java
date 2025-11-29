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

/**
 * The main game panel class that handles the game loop, rendering, and updates.
 * <p>
 * {@code GamePanel} extends {@link JPanel} and implements {@link Runnable} to
 * manage the game thread. It handles:
 * <ul>
 *     <li>Game states: title, play, pause, command, game over, victory</li>
 *     <li>Rendering of tiles, players, bullets, and equipment</li>
 *     <li>Game updates including player, NPC, bullet, and equipment logic</li>
 *     <li>Menu navigation and UI updates via the {@link UI} class</li>
 * </ul>
 */
public class GamePanel extends JPanel implements Runnable {
//    private Panel panel = new Panel();
    /** Original tile size in pixels */
    private final int originalTileSize = 16;
    /** Tile scale factor */
    private final int scale = 3; //for now
    /** Size of each tile after scaling */
    private final int tileSize = originalTileSize * scale;
    /** Number of columns visible on screen */
    private final int maxScreenCol = 16;
    /** Number of rows visible on screen */
    private  final int maxScreenRow = 12;
    /** Screen width in pixels */
    private  final int screenWidth = tileSize * maxScreenCol;
    /** Screen height in pixels */
    private  final int screenHeight = tileSize * maxScreenRow;
    /** List of all players and NPCs in the game */
    private  ArrayList<Players> personnages = new ArrayList<>();
    /** Game frames per second */
    private final int FPS = 60;
    /** Flag to indicate if NPCs should be added */
    private boolean addplayers = false;
    /** Flag indicating if the player has won */
    private boolean victory=false;
    /** Key handler for keyboard input */
    KeyHandler keyHandler = new KeyHandler(this);
    /** The main game thread */
    Thread gameThread;
    /** The main player character */
    private JeanGuy jeanGuy;
    /** Manages tiles and maps */
    private TileManager tileM = new TileManager(this, jeanGuy);
    /** List of all equipment in the game */
    private  ArrayList<Equipements> equipements = new ArrayList<>();
    /** List of bullets in the game */
    private ArrayList<Bullets> bullets = new ArrayList<>();
    /** Temporary information for spawning monsters */
    private ArrayList<String> info;
    /** The game's UI handler */
    private UI UI = new UI(this);

    // GAME STATE

    private int GameState;
    private final int titleState = 0;
    private final int playState = 1;
    private final int pauseState = 2;
    private final int commandState = 3;
    private final int gameOverState = 4;
    private final int victoryState = 5;
    /** Player's X coordinate */
    private int playerX = 200;
    /** Player's Y coordinate */
    private int playerY = 200;


    /**
     * Constructs the game panel and initializes the game.
     * <p>
     * Sets the preferred size, background color, focusable state,
     * and key listener, then prepares the game.
     */
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
    /**
     * Prepares the game by setting the initial game state and title screen.
     */
    public void prepareGame() {
        GameState = titleState;
        UI.setTitleScreenState(0);
    }
    /**
     * Sets the victory state for the game.
     *
     * @param victory True if the player has won, false otherwise
     */
    public void setVictory(boolean victory) {
        this.victory = victory;
    }
    /**
     * Instantiates monsters, NPCs, and equipment based on current map info.
     */
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
    /**
     * Starts the game by initializing the main player and clearing game lists.
     */
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
    /**
     * Resets the game to its initial state, including player, tiles, and UI.
     */
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
    /**
     * Starts the main game thread.
     */
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

    /**
     *  Utility methods to clear entities outside chunk
      */
    public void noMonstersOutChunk(){
        for (Players p : personnages) {
            if(!(p instanceof Playable)){
                p.setDead(true);
            }
        }
    }
    /**
     *  Utility methods to clear entities outside chunk
     */
    public void noBulletsoutChunk(){
        for (Bullets b : bullets) {
            b.setChangeChunk(false);

        }
    }
    /**
     *  Utility methods to clear entities outside chunk
     */
    public void noEquipementsOutChunk(){
        for (Equipements e : equipements) {
            e.setRamasser();

        }
    }
    public boolean getVictory() {
        return this.victory;
    }
    /**
     * Updates all game entities depending on the current game state.
     */
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
    /**
     * Updates all game entities depending on the current game state.
     */
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

    /**
     * Paints all components: tiles, players, bullets, equipment, and UI.
     *
     * @param g Graphics object
     */

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




