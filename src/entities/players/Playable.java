package entities.players;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

import entities.equipements.Equipements;
import entities.equipements.armes.*;
import entities.equipements.soins.Coeur;
import entities.equipements.soins.CoeurMax;
import input.KeyHandler;
import main.GamePanel;
import utils.*;

import javax.imageio.ImageIO;

public abstract class Playable extends Players{

    protected KeyHandler keyHandler;
    protected int hpmax =5;
    protected int argent;
    protected ArrayList<Equipements> inventaire = new ArrayList<>();
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, dead;
    protected BufferedImage defup1, defup2, defdown1, defdown2, defleft1, defleft2, defright1, defright2;
    protected BufferedImage atkup, atkleft, atkdown, atkright;
    protected BufferedImage degatup1, degatup2, degatdown1, degatdown2, degatright1, degatright2, degatleft1, degatleft2;
    protected int cpdmg = 0;
    protected ArrayList<Integer> positionImage2 = new ArrayList<>(Arrays.asList(0,0,0));
    protected String lastdir = "down", lastAtk, lastDef;
    protected String dmgdir;
    protected int cpAtk, cpDef, defenseSpeed=30;
    protected ArrayList<String> attackSprites = new ArrayList<>();
    protected ArrayList<String> damageSprites = new ArrayList<>();
    protected ArrayList<String> defenseSprites = new ArrayList<>();
    protected ArrayList<BufferedImage> weaponsSprites = new ArrayList<>();

    public Playable(GamePanel panel, String name, int damage, List<Integer> position, int range, int hp, int speed, boolean isDead, boolean isMelee, int attackSpeed, boolean killable, List<String> soundPaths, List<String> spritePaths,KeyHandler keyHandler){
        super(panel, name, damage, position, range,  hp,  speed, isDead, isMelee,  attackSpeed,  killable,  soundPaths, spritePaths);
        this.argent = 0;
        this.keyHandler = keyHandler;
        Equipements arme  = new EpeeBois(gamePanel);
        inventaire.add(arme);




    }
    public void ramasserCoeur(){
        this.hp = Math.min(this.hp+1, this.hpmax);
    }

    public void ramasserCoeurMax(){
        this.hpmax += 1;
        this.hp = hpmax;
    }



    @Override
    public void getPlayerImage(){
        try{
            this.up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(0))));
            this.up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(1))));
            this.down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(2))));
            this.down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(3))));
            this.left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(4))));
            this.left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(5))));
            this.right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(6))));
            this.right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(7))));
            this.dead = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.spritesPaths.get(8))));

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void getAttackImage(){
        try{
            this.atkup = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.attackSprites.get(0))));
            this.atkdown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.attackSprites.get(1))));
            this.atkleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.attackSprites.get(2))));
            this.atkright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.attackSprites.get(3))));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getDamageImage(){
        try{
            this.degatup1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(0))));
            this.degatup2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(1))));
            this.degatdown1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(2))));
            this.degatdown2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(3))));
            this.degatleft1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(4))));
            this.degatleft2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(5))));
            this.degatright1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(6))));
            this.degatright2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.damageSprites.get(7))));

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void ramasser(Equipements ramasse){

    }



    public int getHpMax() {
        return hpmax;
    }

    public void myWeaponImages(){
        if (this.inventaire.get(0) instanceof EpeeBois epeeBois){
            weaponsSprites = (epeeBois.getSwordImages());
    }
    }


    public ArrayList<Equipements> getInventaire(){
        return this.inventaire;
    }

    public int getArgent(){
        return this.argent;
    }

    public void setArgent(int argent){
        this.argent += argent;
    }



    public void setInventaire(Equipements arme){
        if (inventaire.size()<2) {
            this.inventaire.add(arme);
        }else{
            inventaire.remove(1);
            inventaire.add(arme);
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
    public int checkSpeed(){
        if (cpdmg!=0){
            return 5;
        }
        return speed;
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
                    if (rand< Coeur.getDropPercentage()){
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
        if ((gamePanel.tileSize<=position.get(0)&&position.get(0)<=gamePanel.getWidth()-gamePanel.tileSize)){
            if (position.get(1)>gamePanel.screenHeight/2){
                position.set(1, 2*gamePanel.tileSize);
                return "SOUTH";
            }
            else if(position.get(1)<gamePanel.screenHeight/2){
                position.set(1, gamePanel.screenHeight-3*gamePanel.tileSize);
                return "NORTH";
            }

        }
        else if(gamePanel.tileSize<=position.get(1) && gamePanel.getHeight()-gamePanel.tileSize>=position.get(1)){
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

    public String getDmgdir(){
        return this.dmgdir;
    }

    public void setAttackSpeed(int attackSpeed){
        this.attackSpeed = attackSpeed;
    }

    public void setCpAtk(int cpAtk){
        this.cpAtk = cpAtk;
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
    public void receiveDamage (Players sender, int damage, String dir){
        if(!(defense(dir))&&killable){
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

    @Override
    public void update() {
        String respass = CollisionsMap.collisionsMap(this, gamePanel.getTileM().getPathTiles(), gamePanel.getTileM().getChunkTiles(), gamePanel.getTileM().getMapTiles(), gamePanel, gamePanel.getTileM().getTiles());
        direction = lastdir;
        if (this.isDead()){
            direction = "dead";
        }else{
            attackSpeed = Math.max(0, attackSpeed - 1);
            defenseSpeed = Math.max(0, defenseSpeed - 1);
            if (isGettingDamage()){
                direction = dmgdir;
                cpdmg--;
                if (respass.equals("path")){
                    damageMovement(direction);
                }
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
                Equipements ramasse= CollisionEquipement.collisionEquipement(gamePanel.equipements, this, gamePanel.tileSize);
                boolean pass = CollisionDistance.collisionDistance(gamePanel.personnages, this, gamePanel.tileSize);
                if (!pass){
                    notPassing();
                }
                if (ramasse !=null){
                    ramasser(ramasse);
                }
                if(canBlock() && canDefend() && keyHandler.defPressed){
                    direction = defenseMovement();
                    lastDef = direction;
                }
                else if(canAttack() && keyHandler.atkPressed) { // Quand est-ce que jg peut attaker
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
                image2 = weaponsSprites.get(3);
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
                image2 = weaponsSprites.get(0);
                break;
            case "atkleft":
                if (spriteNum == 1) {
                    image = atkleft;
                }
                if (spriteNum == 2) {
                    image = atkleft;
                }
                image2 = weaponsSprites.get(2);
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
                image2 = weaponsSprites.get(1);
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
                image2 = weaponsSprites.get(5);
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
                image2 = weaponsSprites.get(7);
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
                image2 = weaponsSprites.get(6);
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
                image2 = weaponsSprites.get(4);
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



        if (this.cpdmg==0){
            dmgdir=null;

        }
        g2.drawImage(image, position.get(0), position.get(1), gamePanel.tileSize, gamePanel.tileSize, null);
        if (image2 != null) {
            g2.drawImage(image2, positionImage2.get(0), positionImage2.get(1), gamePanel.tileSize, gamePanel.tileSize, null);
        }
    };





}
