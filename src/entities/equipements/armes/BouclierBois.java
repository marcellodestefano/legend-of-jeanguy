package entities.equipements.armes;

import main.GamePanel;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BouclierBois extends Armes{
    protected int prix=10;

    public BouclierBois(GamePanel gp){
        super(gp,false, "Bouclier_en_bois", 1, new ArrayList<String>(List.of("/assets/equipments/weapons/bouclierbois.png")), true, 1,  new ArrayList<Integer>(Arrays.asList(0,0,0)));


        getSpriteImage();
        startPosition();
    }

    public void getSpriteImage(){
        try{
            this.equipementImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritePath.get(0))));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public int getPrix(){
        return prix;
    }

    public void startPosition(){
        this.position.set(0,220);
        this.position.set(1,285);
    }

    @Override
    public void update(){

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(equipementImage, this.position.get(0), this.position.get(1), gp.tileSize, gp.tileSize, null);
    }
}
