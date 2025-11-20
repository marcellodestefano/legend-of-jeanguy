package entities.players;


import entities.equipements.Equipements;
import entities.equipements.armes.BouclierBois;
import entities.equipements.soins.Coeur;
import entities.equipements.soins.CoeurMax;
import input.KeyHandler;
import main.GamePanel;
import utils.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import javax.swing.JOptionPane;

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
    protected String lastdir = "down", lastAtk, lastDef;
    protected String dmgdir;
    protected int cpAtk, cpDef, defenseSpeed=30;
    public JeanGuy(GamePanel panel, KeyHandler keyHandler) {
        super(panel,"Jean-Guy", 1, new ArrayList<Integer>(Arrays.asList(200,200,0)), 2, 5,3,
                false,true,30,true, Arrays.asList("",""), Arrays.asList("/assets/player/Haut1.png",
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

    public String oppositeDirection(String dir){
        if (dir.contains("up")){
            return "down";
        }
        if (dir.contains("down")){
            return "up";
        }
        if (dir.contains("left")){
            return "right";
        }
        if (dir.contains("right")){
            return "left";
        }
        return dir;
    }

    @Override
    public void receiveDamage (NonPlayable sender, int damage, String dir){
        if(!(defense(dir))){
            this.hp = Math.max(0,this.hp-=damage);
            this.dmgdir = dir;
            this.cpdmg = 12;
            this.setKillable(false);
        }
        else{
            String direc = oppositeDirection(dir);
            sender.receiveDamage(0, direc);
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

    public void notPassing(){
        if (keyHandler.downPressed){
            this.direction = "down";
            this.lastdir = "down";
        }if (keyHandler.upPressed){
            this.direction = "up";
            this.lastdir = "up";
        }if (keyHandler.leftPressed){
            this.direction = "left";
            this.lastdir = "left";
        }if (keyHandler.rightPressed){
            this.direction = "right";
            this.lastdir = "right";
        }
    
    }

    public String defenseMovement(){
        spriteCounter++;
        cpDef=10;
        defenseSpeed=30;
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

    public boolean isAttacking(){
        return cpAtk!=0;
    }
    public boolean isDefending(){
        return cpDef!=0;
    }

    public String atkMovement() {
        spriteCounter++;
        attackSpeed=30;
        cpAtk=5;
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

        String going = lastdir;
        if (keyHandler.upPressed) {
            spriteCounter++;
            position.set(1, position.get(1) - speed);
            going =  "up";
        }
        if (keyHandler.downPressed) {
            spriteCounter++;
            position.set(1, position.get(1) + speed);
            going =  "down";
        }
         if (keyHandler.leftPressed) {
             spriteCounter++;
             position.set(0, position.get(0) - speed);
             going = "left";
        }
        if (keyHandler.rightPressed) {
            spriteCounter++;
            position.set(0, position.get(0) + speed);
            going = "right";
        }
        return going;
    }

    public boolean canBlock(){
        for (Equipements eq : inventaire){
            if (eq instanceof BouclierBois){
                return true;
            }
        }
        return false;
    }
    public String chooseDirection(){
        if (((gamePanel.screenWidth/2)-(2*gamePanel.tileSize))<=position.get(0)&&((gamePanel.screenWidth/2)+(2*gamePanel.tileSize))>=position.get(0)){
            if (position.get(1)>gamePanel.screenHeight/2){
                position.set(1, 2*gamePanel.tileSize);
                return "SOUTH";
            }
            else if(position.get(1)<gamePanel.screenHeight/2){
                System.out.println(position.get(1));
                position.set(1, gamePanel.screenHeight-3*gamePanel.tileSize);
                return "NORTH";
            }

        }
        else if(((gamePanel.screenHeight/2)-(2*gamePanel.tileSize))<=position.get(1)&&((gamePanel.screenHeight/2)+(2*gamePanel.tileSize))>=position.get(1)){
            if (position.get(0)>gamePanel.screenWidth/2){
                position.set(0, gamePanel.tileSize);
                return "EAST";
            }
            else if(position.get(0)<gamePanel.screenWidth/2){
                position.set(0, gamePanel.screenWidth-gamePanel.tileSize);
                return "WEST";
            }
        }
        return null;
    }

    public boolean canAttack(){
        return attackSpeed==0;
    }

    public boolean canDefend(){
        return defenseSpeed==0;
    }


    public KeyHandler getKeyHandler(){
        return this.keyHandler;
    }

    @Override
    public void update() {
        direction = lastdir;
        if (this.isDead()){
            direction = "dead";
        }else{
            attackSpeed = Math.max(0, attackSpeed - 1);
            defenseSpeed = Math.max(0, defenseSpeed - 1);
            if (isGettingDamage()){
                direction = dmgdir;
                cpdmg--;
                damageMovement(direction);
            }else if (isAttacking()){
                direction = lastAtk;
                cpAtk--;
            }
            else if (isDefending()){
                direction = lastDef;
                cpDef--;
            }
            else{
                this.setKillable(true);
                boolean pass = CollisionDistance.collisionDistance(gamePanel.personnages, this, gamePanel.tileSize);
                String respass = CollisionsMap.collisionsMap(this, gamePanel.getTileM().getPathTiles(), gamePanel.getTileM().getChunkTiles(), gamePanel.getTileM().getMapTiles(), gamePanel, gamePanel.getTileM().getTiles());
                if (!pass){
                    notPassing();
                }
                if(canBlock() && canDefend() && keyHandler.defPressed){
                    direction = defenseMovement();
                    lastDef = direction;
                }
                else if(canAttack() && keyHandler.atkPressed) {
                    direction = atkMovement();
                    lastAtk = direction;
                    Players receiver = AttackCollisions.attackCollisions(gamePanel.personnages, direction, this, gamePanel.tileSize);
                    sendDamage(receiver);
                }
                else if (!(respass.equals("block"))){
                    direction = normalMovement();
                    lastdir = direction;
                    if (respass.equals("chunk")){
                        String nextCunk = chooseDirection();
                        gamePanel.getTileM().changeMap(nextCunk);
                        System.out.println("hi");
                    }
                    if (respass.equals("merchant")){
                        position.set(0, gamePanel.screenWidth/2);
                        position.set(1,gamePanel.screenHeight-3*gamePanel.tileSize);
                        gamePanel.getTileM().changeMap("MERCHANT");
                    }
                    if (respass.equals("exitmerchant")){
                        position.set(0, 4*gamePanel.tileSize);
                        position.set(1,gamePanel.screenHeight-3*gamePanel.tileSize);
                        gamePanel.getTileM().changeMap("SOUTH");
                    }
                }
                else{
                    notPassing();
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
            if (ramasse instanceof BouclierBois && this.getArgent() >= ((BouclierBois) ramasse).getPrix()){
                this.argent -= ((BouclierBois) ramasse).getPrix();
                this.setInventaire(ramasse);
                ramasse.setRamasser();
                this.rammasserBouclier();}
            else if (ramasse instanceof BouclierBois && this.getArgent() <= ((BouclierBois) ramasse).getPrix()){
                gamePanel.showMessage("Tu n'as pas assez d'argent ! Prix : " + ((BouclierBois) ramasse).getPrix());
            }
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
