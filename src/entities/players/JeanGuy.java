package entities.players;


import entities.equipements.Equipements;
import entities.equipements.armes.BouclierBois;
import entities.equipements.soins.Coeur;
import entities.equipements.soins.CoeurMax;
import entities.equipements.soins.Soins;
import input.KeyHandler;
import main.GamePanel;
import utils.AttackCollisions;
import utils.CollisionDistance;
import utils.CollisionEquipement;
import utils.DropItems;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.desktop.SystemEventListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class JeanGuy extends Playable {

    protected KeyHandler keyHandler;
    protected ArrayList<String> attackSprites = new ArrayList<>(Arrays.asList("/assets/playerattack/attackup.png","/assets/playerattack/attackdown.png",
            "/assets/playerattack/attackleft.png","/assets/playerattack/attackright.png"));
    protected ArrayList<String> weaponsSprites = new ArrayList<>(Arrays.asList("/assets/playerattack/swordup.png","/assets/playerattack/sworddown.png",
            "/assets/playerattack/swordright.png","/assets/playerattack/swordleft.png","/assets/playerattack/slashupleft.png","/assets/playerattack/slashdownleft.png",
            "/assets/playerattack/slashupright.png","/assets/playerattack/slashdownright.png"));
    protected ArrayList<String> damageSprites = new ArrayList<>(Arrays.asList("/assets/playerhit/hithaut1.png","/assets/playerhit/hithaut2.png",
            "/assets/playerhit/hitbas1.png","/assets/playerhit/hitbas2.png","/assets/playerhit/hitgauche1.png","/assets/playerhit/hitgauche2.png",
            "/assets/playerhit/hitdroit1.png","/assets/playerhit/hitdroit2.png"));

    protected ArrayList<String> defenseSprites = new ArrayList<>(Arrays.asList("/assets/playerblocking/up1.png","/assets/playerblocking/up2.png",
            "/assets/playerblocking/down1.png","/assets/playerblocking/down2.png","/assets/playerblocking/left1.png","/assets/playerblocking/left2.png",
            "/assets/playerblocking/right1.png","/assets/playerblocking/right2.png"));

    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, dead;
    protected BufferedImage defup1, defup2, defdown1, defdown2, defleft1, defleft2, defright1, defright2;

    protected BufferedImage atkup, atkleft, atkdown, atkright;
    protected BufferedImage swordup, sworddown, swordright,swordleft,swordupleft,sworddownleft,swordupright,sworddownright;
    protected BufferedImage degatup1, degatup2, degatdown1, degatdown2, degatright1, degatright2, degatleft1, degatleft2;
    protected int cpdmg = 0;
    protected ArrayList<Integer> positionImage2 = new ArrayList<>(Arrays.asList(0,0,0));
    protected String lastdir = "down";
    protected String dmgdir;
    public JeanGuy(GamePanel panel, KeyHandler keyHandler) {
        super(panel,"Jean-Guy", 1, new ArrayList<Integer>(Arrays.asList(200,200,0)), 2, 5,3,
                false,true,2,true, Arrays.asList("",""), Arrays.asList("/assets/player/Haut1.png",
                        "/assets/player/Haut2.png","/assets/player/Bas1.png","/assets/player/Bas2.png", "/assets/player/Gauche1.png",
                        "/assets/player/Gauche2.png","/assets/player/Droite1.png","/assets/player/Droite2.png","/assets/playerdeath/linkdeath.png"));

        this.keyHandler = keyHandler;
        this.getPlayerImage();


    }



    public void rammasserBouclier(){
        this.spritesPaths.set(0, "/assets/playershield/Haut1.png");
        this.spritesPaths.set(1, "/assets/playershield/Haut2.png");
        this.spritesPaths.set(2, "/assets/playershield/Bas1.png");
        this.spritesPaths.set(3, "/assets/playershield/Bas2.png");
        this.spritesPaths.set(4, "/assets/playershield/Gauche1.png");
        this.spritesPaths.set(5, "/assets/playershield/Gauche2.png");
        this.spritesPaths.set(6, "/assets/playershield/Droite1.png");
        this.spritesPaths.set(7, "/assets/playershield/Droite2.png");
        //bouclier
        try {
            this.defup1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.defenseSprites.get(0))));
            this.defup2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.defenseSprites.get(1))));
            this.defdown1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.defenseSprites.get(2))));
            this.defdown2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.defenseSprites.get(3))));
            this.defleft1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.defenseSprites.get(4))));
            this.defleft2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.defenseSprites.get(5))));
            this.defright1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.defenseSprites.get(6))));
            this.defright2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.defenseSprites.get(7))));
        }catch (Exception e){
            e.printStackTrace();
        }
        this.getPlayerImage();
    }





    @Override
    public void getPlayerImage() {
        try{
            //movements
            this.up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(0))));
            this.up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(1))));
            this.down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(2))));
            this.down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(3))));
            this.left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(4))));
            this.left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(5))));
            this.right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(6))));
            this.right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(7))));

            //attack movements
            this.atkup = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.attackSprites.get(0))));
            this.atkdown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.attackSprites.get(1))));
            this.atkleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.attackSprites.get(2))));
            this.atkright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.attackSprites.get(3))));

            //attack sword
            this.swordup = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(0))));
            this.sworddown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(1))));
            this.swordleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(3))));
            this.swordright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(2))));
            this.swordupleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(4))));
            this.sworddownleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(5))));
            this.swordupright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(6))));
            this.sworddownright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.weaponsSprites.get(7))));

            //damage endured
            this.degatup1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(0))));
            this.degatup2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(1))));
            this.degatdown1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(2))));
            this.degatdown2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(3))));
            this.degatleft1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(4))));
            this.degatleft2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(5))));
            this.degatright1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(6))));
            this.degatright2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(7))));
            //dead
            this.dead = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(8))));



        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public boolean isGettingDamage(){
        return cpdmg!=0;
    }

    @Override
    public void receiveDamage(int damage, String dir){
        if(!(defense(dir))){
            this.hp = Math.max(0,this.hp-=damage);
            this.dmgdir = dir;
            this.cpdmg = 12;
            this.setKillable(false);
        }else{
            this.direction = lastdir;
        }
    }

    public boolean defense(String dir){
        if (direction.equals("defdown")&&dir.equals("up-player")){
            return true;
        }else if(direction.equals("defup")&&dir.equals("down-player")){
            return true;
        }else if(direction.equals("defright")&&dir.equals("left-player")){
            return true;
        }else if(direction.equals("defleft")&&dir.equals("right-player")){
            return true;
        }
        return false;
    }

    public void notPassing(String lastdir){
        if (lastdir=="down"){
            this.position.set(1, this.position.get(1)-1);
        }else if (lastdir=="up"){
            this.position.set(1, this.position.get(1)+2);
        }
        else if (lastdir=="left"){
            this.position.set(0, this.position.get(0)+1);
        }else if (lastdir=="right"){
            this.position.set(0, this.position.get(0)-1);
        }
    }

    public String defenseMovement(){
        spriteCounter++;
        if(keyHandler.upPressed) {
            return "defup";
        }
        else if (keyHandler.downPressed) {
            return "defdown";
        }
        else if (keyHandler.leftPressed) {
            return "defleft";
        }
        else if (keyHandler.rightPressed) {
            return "defright";
        }
        return "def"+lastdir;

    }

    public void damageMovement(String dir){
        if (dir.equals("up-player")) {
            position.set(1, Math.max(0,position.get(1) - 5));
        }else if (dir.equals("down-player")) {
            position.set(1, Math.min(gamePanel.getHeight()-gamePanel.tileSize, position.get(1) + 5));
        }else if (dir.equals("left-player")) {
            position.set(0, Math.max(0,position.get(0) - 5));
        }else if (dir.equals("right-player")) {
            position.set(0, Math.min(gamePanel.getWidth()- gamePanel.tileSize,position.get(0) + 5));
        }
        spriteCounter++;
    }

    public String atkMovement() {
        spriteCounter++;
        if (keyHandler.upPressed && keyHandler.leftPressed) {
            return "atkleftup";
        }
        else if (keyHandler.upPressed && keyHandler.rightPressed) {
            return "atkrightup";
        }
        else if (keyHandler.upPressed) {
            return "atkup";
        }
        else if (keyHandler.downPressed && keyHandler.leftPressed) {
            return "atkleftdown";
        }
        else if (keyHandler.downPressed && keyHandler.rightPressed) {
            return "atkrightdown";
        }
        else if (keyHandler.rightPressed ) {
            return "atkright";
        }
        else if (keyHandler.leftPressed ) {
            return "atkleft";
        }
        else if (keyHandler.downPressed ) {
            return "atkdown";
        }
        return "atk"+lastdir;


    }

    public void sendDamage(Players receiver) {
        if (receiver!=null){
            if (receiver.isKillable()){
                receiver.receiveDamage(this.inventaire.get(0).getUnite(), direction);
                if (receiver.isDead() && receiver instanceof NonPlayable npc){
                    this.setArgent(npc.getValue());
                    //mort ennemi
                    String order="";
                    Random r = new Random();
                    int rand = r.nextInt(100);
                    if (rand<Coeur.getDropPercentage()){
                        order = "coeur";
                    }else if (Coeur.getDropPercentage()<rand && rand < Coeur.getDropPercentage() + CoeurMax.getDropPercentage()){
                        order ="coeurmax";
                    }
                    DropItems.dropItems(order, gamePanel, receiver);
                }
            }
        }
    }

    public String normalMovement() {
        spriteCounter++;
        String going = lastdir;
        if (keyHandler.upPressed) {
            position.set(1, position.get(1) - speed);
            going =  "up";
        }
        if (keyHandler.downPressed) {
            position.set(1, position.get(1) + speed);
            going =  "down";
        }
         if (keyHandler.leftPressed) {
            position.set(0, position.get(0) - speed);
            going = "left";
        }
        if (keyHandler.rightPressed) {
            position.set(0, position.get(0) + speed);
            going = "right";
        }
        return going;
    }


    @Override
    public void update() {


        direction = lastdir;
        if (this.isDead()){
            direction = "dead";
        }else{
            if (isGettingDamage()){
                direction = dmgdir;
                cpdmg--;
                damageMovement(direction);
            }else{
                this.setKillable(true);
                boolean pass = CollisionDistance.collisionDistance(gamePanel.personnages, this, gamePanel.tileSize);
                if (!pass){
                    notPassing(lastdir);
                }
                if(keyHandler.defPressed){
                    direction = defenseMovement();
                }
                else if(keyHandler.atkPressed) {
                    direction = atkMovement();
                    Players receiver = AttackCollisions.attackCollisions(gamePanel.personnages, direction, this, gamePanel.tileSize);
                    sendDamage(receiver);
                }else{
                    direction = normalMovement();
                    lastdir = direction;
                }
                if (spriteCounter > 12) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }

            }
        }
    }

    
    @Override
    public void draw(Graphics2D g2) {


        BufferedImage image = null;
        BufferedImage image2 = null;
        switch(direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
            case "atkright":
                if (spriteNum == 1) {
                    image = atkright;
                }
                if (spriteNum == 2) {
                    image = atkright;
                }
                positionImage2.set(0, position.get(0) + gamePanel.tileSize);
                positionImage2.set(1, position.get(1));
                image2 = swordright;
                break;
            case "atkup":
                if (spriteNum == 1) {
                    image = atkup;
                }
                if (spriteNum == 2) {
                    image = atkup;
                }
                positionImage2.set(0, position.get(0));
                positionImage2.set(1, position.get(1)-gamePanel.tileSize);
                image2 = swordup;
                break;
            case "atkleft":
                if (spriteNum == 1) {
                    image = atkleft;
                }
                if (spriteNum == 2) {
                    image = atkleft;
                }
                image2 = swordleft;
                positionImage2.set(0, position.get(0)-gamePanel.tileSize);
                positionImage2.set(1, position.get(1));
                break;
            case "atkdown":
                if (spriteNum == 1) {
                    image = atkdown;
                }
                if (spriteNum == 2) {
                    image = atkdown;
                }
                image2 = sworddown;
                positionImage2.set(0, position.get(0));
                positionImage2.set(1, position.get(1)+gamePanel.tileSize);
                break;
            case "atkleftdown":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                image2 = sworddownleft;
                positionImage2.set(0, position.get(0)-gamePanel.tileSize);
                positionImage2.set(1, position.get(1)+gamePanel.tileSize);
                break;
            case "atkrightdown":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                image2 = sworddownright;
                positionImage2.set(0, position.get(0)+gamePanel.tileSize);
                positionImage2.set(1, position.get(1)+gamePanel.tileSize);
                break;
            case "atkrightup":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                image2 = swordupright;
                positionImage2.set(0, position.get(0)+gamePanel.tileSize);
                positionImage2.set(1, position.get(1)-gamePanel.tileSize);
                break;
            case "atkleftup":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                image2 = swordupleft;
                positionImage2.set(0, position.get(0)-gamePanel.tileSize);
                positionImage2.set(1, position.get(1)-gamePanel.tileSize);
                break;
            case "up-player":
                if (spriteNum == 1) {
                    image = degatup1;
                }
                if (spriteNum == 2) {
                    image = degatup2;
                }
                break;
            case "down-player":
                if (spriteNum == 1) {
                    image = degatdown1;
                }
                if (spriteNum == 2) {
                    image = degatdown2;
                }
                break;
            case "left-player":
                if (spriteNum == 1) {
                    image = degatleft1;
                }
                if (spriteNum == 2) {
                    image = degatleft2;
                }
                break;
            case "right-player":
                if (spriteNum == 1) {
                    image = degatright1;
                }
                if (spriteNum == 2) {
                    image = degatright2;
                }
                break;
            case "dead":
                image = dead;
                break;
            case "defup":
                if (spriteNum == 1) {
                    image = defup1;
                }
                if (spriteNum == 2) {
                    image = defup2;
                }
                break;
            case "defdown":
                if (spriteNum == 1) {
                    image = defdown1;
                }
                if (spriteNum == 2) {
                    image = defdown2;
                }
                break;
            case "defleft":
                if (spriteNum == 1) {
                    image = defleft1;
                }
                if (spriteNum == 2) {
                    image = defleft2;
                }
                break;
            case "defright":
                if (spriteNum == 1) {
                    image = defright1;
                }
                if (spriteNum == 2) {
                    image = defright2;
                }
                break;
        }
        Equipements ramasse= CollisionEquipement.collisionEquipement(gamePanel.equipements, this, gamePanel.tileSize);
        if (ramasse !=null){
            if (ramasse instanceof BouclierBois){
                this.setInventaire(ramasse);
                ramasse.setRamasser();
                this.rammasserBouclier();}
            else if (ramasse instanceof CoeurMax){
                this.ramasserCoeurMax();
                ramasse.setRamasser();

            }else if (ramasse instanceof Coeur){
                this.ramasserCoeur();
                ramasse.setRamasser();
            }


        }
        if (this.cpdmg==0){
            dmgdir=null;

        }
        g2.drawImage(image, position.get(0), position.get(1), gamePanel.tileSize, gamePanel.tileSize, null);
        if (image2 != null) {
            g2.drawImage(image2, positionImage2.get(0), positionImage2.get(1), gamePanel.tileSize, gamePanel.tileSize, null);
        }
    };

}
