package main;

import java.awt.*;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;

    public UI(GamePanel gp){
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        if(gp.GameState == gp.playState){
            // Draw playstate
        }
        if(gp.GameState == gp.pauseState){
            drawPauseScreen();
        }
    }

    public void drawPauseScreen(){
        System.out.println("drawPauseScreen called!");


        g2.setColor(new Color(0, 0, 0, 150)); // RGB(0,0,0) avec alpha=150 (opacité)
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80f));
        String text = "PAUSE";



        int x = getXcentered(text);

        int y = gp.screenHeight/2;

        g2.drawString(text,x,y);
    }

    public int getXcentered(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }



}
