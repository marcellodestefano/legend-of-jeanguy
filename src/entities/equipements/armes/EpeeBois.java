package entities.equipements.armes;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class EpeeBois extends Armes{
    protected int prix = 0;
    protected ArrayList<String> weaponsSprites;
    protected BufferedImage swordup, sworddown, swordright,swordleft,swordupleft,sworddownleft,swordupright,sworddownright;
    protected ArrayList<BufferedImage> swordImages= new ArrayList<>();


    public EpeeBois(GamePanel gp){
        super(gp, false, "EpeeBois", 1, new ArrayList<String>(Arrays.asList("","")), true, 1,new ArrayList<Integer>(Arrays.asList(100,100,0)));
        this.weaponsSprites = new ArrayList<>(Arrays.asList("/assets/playerattack/swordup.png","/assets/playerattack/sworddown.png",
                "/assets/playerattack/swordright.png","/assets/playerattack/swordleft.png","/assets/playerattack/slashupleft.png","/assets/playerattack/slashdownleft.png",
                "/assets/playerattack/slashupright.png","/assets/playerattack/slashdownright.png"));
        this.getSpriteImage();
    }
    @Override
    public void getSpriteImage() {
        try {
            this.swordup = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(0))));
            this.sworddown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(1))));
            this.swordleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(3))));
            this.swordright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(2))));
            this.swordupleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(4))));
            this.sworddownleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(5))));
            this.swordupright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(6))));
            this.sworddownright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(7))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.swordImages.add(this.swordup);
        this.swordImages.add(this.sworddown);
        this.swordImages.add(this.swordleft);
        this.swordImages.add(this.swordright);
        this.swordImages.add(this.swordupleft);
        this.swordImages.add(this.sworddownleft);
        this.swordImages.add(this.swordupright);
        this.swordImages.add(this.sworddownright);
    }

    public ArrayList<BufferedImage> getSwordImages(){
        return this.swordImages;
    }

    public int getPrix(){
        return prix;
    }
}
