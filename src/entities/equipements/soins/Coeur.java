package entities.equipements.soins;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Coeur extends Soins{

    static int dropPercentage = 30;

    public Coeur(GamePanel gp)
    {
        super(gp,true, "Coeur", 1, new ArrayList<String>(List.of("/assets/equipments/coeurdrop/coeurdrop.png")), new ArrayList<Integer>(Arrays.asList(200,300,0)));

        try{
            equipementImage = ImageIO.read(getClass().getResourceAsStream(this.spritePath.get(0)));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static int getDropPercentage() {
        return dropPercentage;
    }

    @Override
    public void update(){

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(equipementImage, this.position.get(0), this.position.get(1), gp.tileSize, gp.tileSize, null);
    }
}
