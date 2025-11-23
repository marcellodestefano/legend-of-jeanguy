package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * The {@code UI} class handles all user interface elements in the game.
 * <p>
 * It is responsible for:
 * <ul>
 *     <li>Drawing the title screen, command screen, pause screen, game over screen, and victory screen</li>
 *     <li>Displaying player information such as HP, currency, and messages</li>
 *     <li>Managing menu selections and command navigation</li>
 *     <li>Handling screen transitions and UI updates</li>
 * </ul>
 */
public class UI {
    /** Reference to the main game panel */
    GamePanel gp;
    /** Graphics2D object used for drawing */
    Graphics2D g2;
    /** Custom and standard fonts */
    Font zeldaFont, arial_40, arial_80B;
    /** Images used for UI elements */
    BufferedImage titleImage;
    BufferedImage redJeanGuy;
    BufferedImage arrows;
    BufferedImage attackDefense;
    BufferedImage echapEnter;
    BufferedImage heartFull, heartEmpty, gems;
    /** Message currently displayed on screen */
    protected String message = "";
    /** Flag to indicate if a message is active */
    protected boolean messageOn = false;
    /** Counter for message display duration */
    private int messageCounter = 0;
    /** Current selected menu command */
    private int commandNum = 0;
    /** Current state of the title screen (0 = main title, 1 = skin selection) */
    private int titleScreenState = 0; // 0 = Main title / 1 = Selection of character skin
    /** Alpha transparency for game over screen */
    protected int gameOverAlpha = 0;  // Transparence (0 = invisible, 255 = opaque)
    /** Counter for game over animation */
    protected int gameOverCounter = 0;
    /** Alpha transparency for victory screen */
    protected int victoryAlpha = 0;
    /** Counter for victory animation */
    protected int victoryCounter = 0;
    /**
     * Constructs a UI object associated with the given {@link GamePanel}.
     * Loads fonts and images used in the user interface.
     *
     * @param gp The main game panel
     */
    public UI(GamePanel gp){
        this.gp = gp;

        try{
            zeldaFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/font/the-legend-of-zelda-nes.ttf")).deriveFont(17f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            zeldaFont = new Font("Arial", Font.PLAIN, 40);
        }

        try {
            titleImage = ImageIO.read(getClass().getResourceAsStream("/assets/playerblocking/down2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            arrows = ImageIO.read(getClass().getResourceAsStream("/assets/CommandState/unnamed__2_-removebg-preview.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            attackDefense = ImageIO.read(getClass().getResourceAsStream("/assets/CommandState/attacco_e_difesa_1-removebg-preview.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            echapEnter = ImageIO.read(getClass().getResourceAsStream("/assets/CommandState/unnamed__1_-removebg-preview.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            redJeanGuy = ImageIO.read(getClass().getResourceAsStream("/assets/redplayer/redblocking/down2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            heartFull = ImageIO.read(getClass().getResourceAsStream("/assets/hud/hud36-removebg-preview.png"));
            heartEmpty = ImageIO.read(getClass().getResourceAsStream("/assets/hud/hud38-removebg-preview.png"));
            gems = ImageIO.read(getClass().getResourceAsStream("/assets/hud/hud1-removebg-preview.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }

    public int getCommandNum() {
        return this.commandNum;
    }
    public void plusCommandNum() {
        this.commandNum ++;
    }
    public void minusCommandNum() {
        this.commandNum --;
    }

    public void setCommandNum(int commandNum){
        this.commandNum = commandNum;
    }

    public int getTitleScreenState() {
        return this.titleScreenState;
    }

    public void setTitleScreenState(int titleScreenState) {
        this.titleScreenState = titleScreenState;
    }
    /**
     * Shows a temporary message on screen.
     *
     * @param text The message to display
     */
    public void showMessage(String text){
        message = text;
        messageOn = true;
        messageCounter = 0;
    }
    /**
     * Updates UI elements such as messages, game over, and victory animations.
     * Should be called once per frame.
     */
    public void update(){
        if(messageOn){
            messageCounter++;
            if(messageCounter >= 60){
                messageOn = false;
                messageCounter = 0;
            }
        }
        if (gp.getVictory()){
            gp.setGameState(gp.getVictoryState());
        }

        if(gp.getGameState() == gp.getGameOverState()){
            gameOverCounter++;
            if(gameOverCounter <= 120){  // 2 secondes à 60 FPS
                gameOverAlpha = Math.min(255, gameOverAlpha + 4);  // Augmente progressivement
            }
        } else {
            gameOverAlpha = 0;
            gameOverCounter = 0;
        }

        if(gp.getGameState() == gp.getVictoryState()){
            victoryCounter++;
            if(victoryCounter <= 120){
                victoryAlpha = Math.min(255, victoryAlpha + 4);
            }
        } else {
            victoryAlpha = 0;
            victoryCounter = 0;
        }
    }
    /**
     * Draws all relevant UI elements depending on the current game state.
     *
     * @param g2 Graphics2D object used for drawing
     */
    public void draw(Graphics2D g2){
        this.g2 = g2;

        if(gp.getGameState() == gp.getTitleState()){
            drawTitleScreen();
        }
        if(gp.getGameState() == gp.getPlayState()){
            drawPlayerLife();
            drawMessage();
        }
        if(gp.getGameState() == gp.getPauseState()){
            drawPauseScreen();
        }
        if(gp.getGameState() == gp.getGameOverState()){  // AJOUTE CETTE SECTION
            drawGameOverScreen();
        }
        if(gp.getGameState() == gp.getCommandState()){
            drawCommandScreen();
        }
        if(gp.getGameState() == gp.getVictoryState()){
            drawVictoryScreen();
        }
    }

    /** Draw victory screen with menu */
    public void drawVictoryScreen(){

        g2.setColor(new Color(0, 0, 0, Math.min(200, victoryAlpha)));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());

        g2.setFont(zeldaFont.deriveFont(80f));
        g2.setColor(new Color(255, 215, 0, victoryAlpha)); // Doré avec transparence
        String text = "VICTORY";
        int x = getXcentered(text);
        int y = gp.getScreenHeight() / 2;
        g2.drawString(text, x, y);

        if(victoryAlpha >= 255){
            g2.setFont(zeldaFont.deriveFont(25f));
            g2.setColor(Color.WHITE);

            text = "MAIN MENU";
            x = getXcentered(text);
            y = gp.getTileSize() * 10;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x - gp.getTileSize(), y);
            }

            text = "QUIT";
            x = getXcentered(text);
            y = gp.getTileSize() * 11;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x - gp.getTileSize(), y);
            }}
    }
    /** Draw title and skin selection screens */
    public void drawTitleScreen(){

        if(titleScreenState == 0){

            g2.setFont(zeldaFont.deriveFont(25f));
            String text = "The Legend of Jean-Guy";
            int x = getXcentered(text);
            int y = gp.getTileSize()*3;


            g2.setColor(Color.WHITE);
            g2.drawString(text,x,y);

            // Image de jean guy

            x = gp.getScreenWidth()/2;
            y = gp.getTileSize()*2;
            g2.drawImage(titleImage, 330, 200, 125, 125, null);

            // MENU

            g2.setFont(zeldaFont.deriveFont(25f));

            text = "START GAME";
            x = getXcentered(text);
            y = gp.getTileSize()*9;
            g2.drawString(text,x,y);
            if(commandNum == 0){
                g2.drawString(">", x-gp.getTileSize(), y);
            }

            text = "COMMANDS";
            x = getXcentered(text);
            y = gp.getTileSize()*10;
            g2.drawString(text,x,y);
            if(commandNum == 1){
                g2.drawString(">", x-gp.getTileSize(), y);
            }

            text = "QUIT";
            x = getXcentered(text);
            y = gp.getTileSize()*11;
            g2.drawString(text,x,y);
            if(commandNum == 2){
                g2.drawString(">", x-gp.getTileSize(), y);
            }
        }
        if(titleScreenState == 1){

            // SKIN SELECTION SCREEN
            g2.setFont(zeldaFont.deriveFont(25f));
            String text = "Select your Jean-Guy skin !";
            int x = getXcentered(text);
            int y = gp.getTileSize()*3;
            g2.setColor(Color.WHITE);
            g2.drawString(text,x,y);

            // Dessiner jean guy

            x = gp.getScreenWidth()/2;
            y = gp.getTileSize();
            g2.drawImage(titleImage, 150, 200, 125, 125, null);
            if(commandNum == 0){
                g2.setFont(zeldaFont.deriveFont(50f));
                g2.drawString("^", 140 + 125/2 - 15, 400);
            }

            // Dessiner red jean guy

            x = gp.getScreenWidth()/2;
            y = gp.getTileSize();
            g2.drawImage(redJeanGuy, 500, 200, 125, 125, null);
            if(commandNum == 1){
                g2.setFont(zeldaFont.deriveFont(50f));
                g2.drawString("^", 490 + 125/2 - 15, 400);
            }

            // Dessiner back to the main title

            g2.setFont(zeldaFont.deriveFont(20f));
            text = "Back to the main title";
            x = getXcentered(text);
            y = gp.getTileSize()*9;
            g2.drawString(text,x,y);
            if(commandNum == 2){
                g2.drawString(">", x-gp.getTileSize(), y);
            }
        }
    }
    /** Draw controls screen */
    public void drawCommandScreen(){

        g2.setFont(zeldaFont.deriveFont(25f));
        String text = "COMMANDS";
        int x = getXcentered(text);
        int y = gp.getTileSize()*3;
        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);


        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);

        // Image de flèches

        x = gp.getScreenWidth()/2;
        y = gp.getTileSize()*2;
        g2.drawImage(arrows, 75, 200, 250, 250, null);

        // Image de commandes attaque defense

        x = gp.getScreenWidth()/2;
        y = gp.getTileSize()*2;
        g2.drawImage(attackDefense, 400, 150, 250, 250, null);


        // Image entrée + echap

        x = gp.getScreenWidth()/2;
        y = gp.getTileSize()*2;
        g2.drawImage(echapEnter, 400, 275, 250, 250, null);


        text = "BACK";
        x = getXcentered(text);
        y = gp.getTileSize()*11;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">", x-gp.getTileSize(), y);
        }
    }
    /** Draw game over screen with menu */
    public void drawGameOverScreen(){

        g2.setColor(new Color(0, 0, 0, Math.min(200, gameOverAlpha)));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());


        g2.setFont(zeldaFont.deriveFont(80f));
        g2.setColor(new Color(255, 0, 0, gameOverAlpha)); // Rouge avec transparence
        String text = "GAME OVER";
        int x = getXcentered(text);
        int y = gp.getScreenHeight() / 2;
        g2.drawString(text, x, y);


        if(gameOverAlpha >= 255){
            g2.setFont(zeldaFont.deriveFont(25f));
            g2.setColor(Color.WHITE);

            text = "RETRY";
            x = getXcentered(text);
            y = gp.getTileSize() * 9;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x - gp.getTileSize(), y);
            }

            text = "MAIN MENU";
            x = getXcentered(text);
            y = gp.getTileSize() * 10;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x - gp.getTileSize(), y);
            }

            text = "QUIT";
            x = getXcentered(text);
            y = gp.getTileSize() * 11;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x - gp.getTileSize(), y);
            }
        }
    }
    /**
     * Draws a temporary message in the center of the screen.
     */
    public void drawMessage(){
        if (messageOn) {
            g2.setFont(zeldaFont);
            g2.setColor(Color.RED);


            FontMetrics metrics = g2.getFontMetrics();
            int x = (gp.getWidth() - metrics.stringWidth(message)) / 2;
            int y = gp.getHeight() / 2;

            // Fond semi-transparent
            g2.setColor(new Color(0, 0, 0, 180));
            g2.fillRect(x - 10, y - 25, metrics.stringWidth(message) + 20, 35);

            // Texte
            g2.setColor(Color.WHITE);
            g2.drawString(message, x, y);
        }
    }
    /**
     * Draws the player's current HP and currency.
     */
    public void drawPlayerLife(){

        int x = gp.getTileSize() / 2;
        int y = gp.getTileSize() / 2;

        int heartWidth = 40;
        int heartHeight = 40;


        for(int i = 0; i < gp.getJeanGuy().getHpMax(); i++){


            if(i < gp.getJeanGuy().getHp()){
                g2.drawImage(heartFull, x, y, heartWidth, heartHeight, null);
            }

            else {
                g2.drawImage(heartEmpty, x, y, heartWidth, heartHeight, null);
            }

            x += heartWidth + 5;

            int gemX = gp.getTileSize() / 2;
            int gemY = y + heartHeight + 10;
            int gemSize = 40;


            g2.drawImage(gems, gemX, gemY, gemSize, gemSize, null);


            g2.setFont(zeldaFont.deriveFont(20f));
            g2.setColor(Color.white);
            String argentText = ":" + gp.getJeanGuy().getArgent();
            g2.drawString(argentText, gemX + gemSize + 5, gemY + gemSize - 10);
        }
    }


    /** Draw pause overlay and menu */
    public void drawPauseScreen(){
        g2.setColor(new Color(0, 0, 0, 150)); // RGB(0,0,0) avec alpha=150 (opacité)
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());

        g2.setFont(zeldaFont);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80f));
        String text = "PAUSE";

        int x = getXcentered(text);

        int y = gp.getScreenHeight()/2;

        g2.drawString(text,x,y);

        g2.setFont(zeldaFont.deriveFont(15f));

        text = "RETURN TO MAIN TITLE";
        x = getXcentered(text);
        y = gp.getTileSize()*9;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">", x-gp.getTileSize(), y);
        }

        text = "QUIT";
        x = getXcentered(text);
        y = gp.getTileSize()*10;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawString(">", x-gp.getTileSize(), y);
        }
    }
    /**
     * Returns the X coordinate to center text horizontally on screen.
     *
     * @param text Text to center
     * @return X coordinate
     */
    public int getXcentered(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.getScreenWidth()/2 - length/2;
        return x;
    }



}
