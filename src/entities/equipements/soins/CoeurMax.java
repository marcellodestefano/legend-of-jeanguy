package entities.equipements.soins;

import entities.players.JeanGuy;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.*;

public class CoeurMax extends Soins{


    public CoeurMax(GamePanel gp)
    {
        super(gp,true, 10, "CoeurMax", 1, new ArrayList<String>(Arrays.asList("/assets/equipments/coeurmaxdrop/coeurmax.png")),new ArrayList<Integer>(Arrays.asList(100,250,0)));

        try{
            equipementImage = ImageIO.read(getClass().getResourceAsStream(this.spritePath.get(0)));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(equipementImage, this.position.get(0), this.position.get(1), gp.tileSize, gp.tileSize, null);
    }
}
