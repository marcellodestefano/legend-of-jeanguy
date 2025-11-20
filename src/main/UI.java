package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font zeldaFont, arial_40, arial_80B;
    BufferedImage titleImage;
    protected String message = "";
    protected boolean messageOn = false;
    private int messageCounter = 0;
    public int commandNum = 0;

    public UI(GamePanel gp){
        this.gp = gp;

        try{
            zeldaFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/font/the-legend-of-zelda-nes.ttf")).deriveFont(17f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            System.out.println("Font not found! We apply Arial !");
            zeldaFont = new Font("Arial", Font.PLAIN, 40);
        }

        try {
            titleImage = ImageIO.read(getClass().getResourceAsStream("/assets/playerblocking/down2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
        messageCounter = 0;
    }

    public void update(){
        if(messageOn){
            messageCounter++;
            if(messageCounter >= 60){
                messageOn = false;
                messageCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        if(gp.GameState == gp.titleState){
            drawTitleScreen();
        }
        if(gp.GameState == gp.playState){
            drawMessage();
        }
        if(gp.GameState == gp.pauseState){
            drawPauseScreen();
        }
    }

    public void drawTitleScreen(){

        g2.setFont(zeldaFont.deriveFont(25f));
        String text = "The Legend of Jean-Guy";
        int x = getXcentered(text);
        int y = gp.tileSize*3;


        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);

        // Image de jean guy

        x = gp.screenWidth/2;
        y = gp.tileSize*2;
        g2.drawImage(titleImage, 330, 200, 125, 125, null);

        // MENU

        g2.setFont(zeldaFont.deriveFont(25f));

        text = "START GAME";
        x = getXcentered(text);
        y = gp.tileSize*9;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "COMMANDS";
        x = getXcentered(text);
        y = gp.tileSize*10;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT";
        x = getXcentered(text);
        y = gp.tileSize*11;
        g2.drawString(text,x,y);
        if(commandNum == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    public void drawCommandScreen(){

        g2.setFont(zeldaFont.deriveFont(25f));
        String text = "The Legend of Jean-Guy";
        int x = getXcentered(text);
        int y = gp.tileSize*3;


        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);

        // Image de jean guy

        x = gp.screenWidth/2;
        y = gp.tileSize*2;
        g2.drawImage(titleImage, 330, 200, 125, 125, null);

        // MENU

        g2.setFont(zeldaFont.deriveFont(25f));

        text = "BACK";
        x = getXcentered(text);
        y = gp.tileSize*11;
        g2.drawString(text,x,y);
        if(commandNum == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

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


    public void drawPauseScreen(){
        g2.setColor(new Color(0, 0, 0, 150)); // RGB(0,0,0) avec alpha=150 (opacité)
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(zeldaFont);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80f));
        String text = "PAUSE";

        int x = getXcentered(text);

        int y = gp.screenHeight/2;

        g2.drawString(text,x,y);

        g2.setFont(zeldaFont.deriveFont(15f));

        text = "RETURN TO MAIN TITLE";
        x = getXcentered(text);
        y = gp.tileSize*9;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT";
        x = getXcentered(text);
        y = gp.tileSize*10;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    public int getXcentered(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }



}
