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

public class BouclierBois extends Armes{
    protected int prix=10;

    public BouclierBois(GamePanel gp){
        super(gp,false, "Bouclier_en_bois", 1, new ArrayList<String>(List.of("/assets/equipments/bouclierbois.png")), true, 1,  new ArrayList<Integer>(Arrays.asList(200,500,0)));


        try{
            equipementImage = ImageIO.read(getClass().getResourceAsStream(this.spritePath.get(0)));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public int getPrix(){
        return prix;
    }

    @Override
    public void update(){

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(equipementImage, this.position.get(0), this.position.get(1), gp.tileSize, gp.tileSize, null);
    }
}
