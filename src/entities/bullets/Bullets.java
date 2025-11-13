package entities.bullets;

import entities.players.Players;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Objects;

public abstract class Bullets {
    protected List<Double> position;
    protected List<String> spritesPaths;
    protected int damage;
    protected Players sender;
    protected Players receiver;
    protected GamePanel gamePanel;
    protected BufferedImage image;
    protected int speed;
    protected String isActive = "ok";
    protected int diffX, diffY;
    protected double ipten, speedX, speedY;


    public Bullets(GamePanel gamePanel, Players sender, Players receiver, List<String> spritesPaths,int damage, int speed ) {
        this.gamePanel = gamePanel;
        for (int val : sender.getPosition()) {
            this.position.add((double) val);
        }
        this.sender = sender;
        this.receiver = receiver;
        this.damage = damage;
        this.speed = speed;
        this.spritesPaths = spritesPaths;
        this.getPlayerImage();
        this.calcSpeed();
    }
    public void getPlayerImage() {
        try {
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(0))));
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void calcSpeed(){
        this.diffX = receiver.getPosition().get(0) - sender.getPosition().get(0);
        this.diffY = receiver.getPosition().get(1) - sender.getPosition().get(1);
        this.ipten = Math.sqrt(diffX*diffX+diffY*diffY);
        this.speedX = diffX/ipten;
        this.speedY = diffY/ipten;
    }



    public void update(){
        if(isActive.equals("ok")){
            this.position.set(0, position.get(0) + speedX);
            this.position.set(1, position.get(1) + speedY);
        }
    }

    public void draw(Graphics g2){
        g2.drawImage(this.image, (int) Math.round(position.get(0)), (int) Math.round(position.get(1)), gamePanel.tileSize, gamePanel.tileSize,null);

    }
}
