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
/**
 * Abstract class representing a playable character in the game.
 * <p>
 * Extends the {@link Players} class and adds functionality specific to
 * player-controlled characters, such as inventory, gold, key input handling,
 * health management, movement, attacking, defending, and sprite animation.
 * </p>
 */
public abstract class Playable extends Players{
    /** Key input handler for player actions */
    protected KeyHandler keyHandler;
    /** Maximum health points */
    protected int hpmax =5;
    /** Amount of gold the player has */
    protected int argent;
    /** Player inventory containing equipment and items */
    protected ArrayList<Equipements> inventaire = new ArrayList<>();
    /** Sprites for movement animations */
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, dead;
    /** Sprites for defense animations */
    protected BufferedImage defup1, defup2, defdown1, defdown2, defleft1, defleft2, defright1, defright2;
    /** Sprites for attack animations */
    protected BufferedImage atkup, atkleft, atkdown, atkright;
    /** Sprites for taking damage */
    protected BufferedImage degatup1, degatup2, degatdown1, degatdown2, degatright1, degatright2, degatleft1, degatleft2;
    /** Current damage cooldown */
    protected int cpdmg = 0;
    /** Secondary position for drawing attack or weapon sprites */
    protected ArrayList<Integer> positionImage2 = new ArrayList<>(Arrays.asList(0,0,0));
    /** Last movement direction */
    protected String lastdir = "down";
    /** Last attack direction */
    protected String lastAtk;
    /** Last defense direction */
    protected String  lastDef;
    /** Direction of the last received damage */
    protected String dmgdir;
    /** Attack and defense counters */
    protected int cpAtk, cpDef;
    /** Speed for defense actions */
    protected int defenseSpeed=30;
    /** List of attack animation sprite paths */
    protected ArrayList<String> attackSprites = new ArrayList<>();
    /** List of damage animation sprite paths */
    protected ArrayList<String> damageSprites = new ArrayList<>();
    /** List of defense animation sprite paths */
    protected ArrayList<String> defenseSprites = new ArrayList<>();
    /** List of weapon sprites to draw during attacks */
    protected ArrayList<BufferedImage> weaponsSprites = new ArrayList<>();

    /**
     * Constructs a playable character.
     *
     * @param panel GamePanel reference
     * @param name Character name
     * @param damage Base damage of the character
     * @param position Initial position [x, y]
     * @param range Attack range
     * @param hp Initial health points
     * @param speed Movement speed
     * @param isDead Initial dead state
     * @param isMelee Whether character uses melee attacks
     * @param attackSpeed Speed of attacks
     * @param killable Whether the character can be killed
     * @param soundPaths List of sound file paths
     * @param spritePaths List of sprite image paths
     * @param keyHandler KeyHandler for player input
     */
    public Playable(GamePanel panel, String name, int damage, List<Integer> position, int range, int hp, int speed, boolean isDead, boolean isMelee, int attackSpeed, boolean killable, List<String> soundPaths, List<String> spritePaths,KeyHandler keyHandler){
        super(panel, name, damage, position, range,  hp,  speed, isDead, isMelee,  attackSpeed,  killable,  soundPaths, spritePaths);
        this.argent = 0;
        this.keyHandler = keyHandler;
        Equipements arme  = new EpeeBois(gamePanel);
        inventaire.add(arme);




    }

    /** Increase HP by 1, up to the maximum */
    public void ramasserCoeur(){
        this.hp = Math.min(this.hp+1, this.hpmax);
    }
    /** Increase maximum HP by 1 and restore health to full */
    public void ramasserCoeurMax(){
        this.hpmax += 1;
        this.hp = hpmax;
    }


    /** Load movement and dead sprites */
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
    /** Load attack sprites */
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
    /** Load damage sprites */
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
    /** Add an equipment item to the inventory */
    public void ramasser(Equipements ramasse){

    }

    /** Returns the player's maximum health */
    public int getHpMax() {
        return hpmax;
    }
    /** Loads weapon images if the first inventory item is a sword */
    public void myWeaponImages(){
        if (this.inventaire.get(0) instanceof EpeeBois epeeBois){
            weaponsSprites = (epeeBois.getSwordImages());
        }
    }

    /** Returns the player's inventory */
    public ArrayList<Equipements> getInventaire(){
        return this.inventaire;
    }
    /** Returns the player's gold amount */
    public int getArgent(){
        return this.argent;
    }
    /** Adds gold to the player */
    public void setArgent(int argent){
        this.argent += argent;
    }

    /** Sets or replaces an equipement in the inventory */

    public void setInventaire(Equipements arme){
        if (inventaire.size()<2) {
            this.inventaire.add(arme);
        }else{
            inventaire.remove(1);
            inventaire.add(arme);
        }
    }
    /** Returns whether the player is currently taking damage */
    public boolean isGettingDamage(){
        return cpdmg!=0;
    }
    /** Returns the opposite of a given direction */
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
    /** Checks whether the player successfully defends against an attack */
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
    /** Updates movement direction based on key input */
    public void notPassing(){
        if (keyHandler.isDownPressed()){
            this.direction = "down";
            this.lastdir = "down";
        }if (keyHandler.isUpPressed()){
            this.direction = "up";
            this.lastdir = "up";
        }if (keyHandler.isLeftPressed()){
            this.direction = "left";
            this.lastdir = "left";
        }if (keyHandler.isRightPressed()){
            this.direction = "right";
            this.lastdir = "right";
        }

    }
    /** Returns current movement speed considering damage cooldown */
    public int checkSpeed(){
        if (cpdmg!=0){
            return 5;
        }
        return speed;
    }
    /** Returns the current defense movement direction based on key input */
    public String defenseMovement(){
        spriteCounter++;
        cpDef=10;
        defenseSpeed=30;
        if(keyHandler.isUpPressed()) {
            return "defup";
        }
        else if (keyHandler.isDownPressed()) {
            return "defdown";
        }
        else if (keyHandler.isLeftPressed()) {
            return "defleft";
        }
        else if (keyHandler.isRightPressed()) {
            return "defright";
        }
        return "def"+lastdir;

    }
    /** Moves the player when taking damage */
    public void damageMovement(String dir){
        if (dir.equals("up-player")) {
            position.set(1, Math.max(0,position.get(1) - 5));
        }else if (dir.equals("down-player")) {
            position.set(1, Math.min(gamePanel.getHeight()-gamePanel.getTileSize(), position.get(1) + 5));
        }else if (dir.equals("left-player")) {
            position.set(0, Math.max(0,position.get(0) - 5));
        }else if (dir.equals("right-player")) {
            position.set(0, Math.min(gamePanel.getWidth()- gamePanel.getTileSize(),position.get(0) + 5));
        }
        spriteCounter++;
    }
    /** Returns whether the player is currently attacking */
    public boolean isAttacking(){
        return cpAtk!=0;
    }
    /** Returns whether the player is currently defending */
    public boolean isDefending(){
        return cpDef!=0;
    }
    /** Returns the current attack movement direction based on key input */
    public String atkMovement() {
        spriteCounter++;
        attackSpeed=30;
        cpAtk=5;
        if (keyHandler.isUpPressed() && keyHandler.isLeftPressed()) {
            return "atkleftup";
        }
        else if (keyHandler.isUpPressed() && keyHandler.isRightPressed()) {
            return "atkrightup";
        }
        else if (keyHandler.isUpPressed()) {
            return "atkup";
        }
        else if (keyHandler.isDownPressed() && keyHandler.isLeftPressed()) {
            return "atkleftdown";
        }
        else if (keyHandler.isDownPressed() && keyHandler.isRightPressed()) {
            return "atkrightdown";
        }
        else if (keyHandler.isRightPressed()) {
            return "atkright";
        }
        else if (keyHandler.isLeftPressed()) {
            return "atkleft";
        }
        else if (keyHandler.isDownPressed()) {
            return "atkdown";
        }
        return "atk"+lastdir;


    }


    /** Sends damage to a target player */
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
    /** Returns the direction of normal movement based on key input */
    public String normalMovement() {

        String going = lastdir;
        if (keyHandler.isUpPressed()) {
            spriteCounter++;
            position.set(1, position.get(1) - speed);
            going =  "up";
        }
        if (keyHandler.isDownPressed()) {
            spriteCounter++;
            position.set(1, position.get(1) + speed);
            going =  "down";
        }
        if (keyHandler.isLeftPressed()) {
            spriteCounter++;
            position.set(0, position.get(0) - speed);
            going = "left";
        }
        if (keyHandler.isRightPressed()) {
            spriteCounter++;
            position.set(0, position.get(0) + speed);
            going = "right";
        }
        return going;
    }
    /** Checks if the player has a shield to block attacks */
    public boolean canBlock(){
        for (Equipements eq : inventaire){
            if (eq instanceof BouclierBois){
                return true;
            }
        }
        return false;
    }
    /** Adjusts the player's position when crossing map boundaries */
    public String chooseDirection(){
        if ((gamePanel.getTileSize()<=position.get(0)&&position.get(0)<=gamePanel.getWidth()-gamePanel.getTileSize())){
            if (position.get(1)>gamePanel.getScreenHeight()/2){
                position.set(1, 2*gamePanel.getTileSize());
                return "SOUTH";
            }
            else if(position.get(1)<gamePanel.getScreenHeight()/2){
                position.set(1, gamePanel.getScreenHeight()-3*gamePanel.getTileSize());
                return "NORTH";
            }

        }
        else if(gamePanel.getTileSize()<=position.get(1) && gamePanel.getHeight()-gamePanel.getTileSize()>=position.get(1)){
            if (position.get(0)>gamePanel.getScreenWidth()/2){
                position.set(0, gamePanel.getTileSize());
                return "EAST";
            }
            else if(position.get(0)<gamePanel.getScreenWidth()/2){
                position.set(0, gamePanel.getScreenWidth()-gamePanel.getTileSize());
                return "WEST";
            }
        }
        return null;
    }
    /** Returns the last damage direction */
    public String getDmgdir(){
        return this.dmgdir;
    }
    /** Sets attack speed */
    public void setAttackSpeed(int attackSpeed){
        this.attackSpeed = attackSpeed;
    }
    /** Sets attack counter */
    public void setCpAtk(int cpAtk){
        this.cpAtk = cpAtk;
    }
    /** Returns whether the player can attack */
    public boolean canAttack(){
        return attackSpeed==0;
    }
    /** Returns whether the player can defend */
    public boolean canDefend(){
        return defenseSpeed==0;
    }

    /** Returns the KeyHandler used for player input */

    public KeyHandler getKeyHandler(){
        return this.keyHandler;
    }
    /** Handles receiving damage with defense logic */
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
    /** Updates the player's state, movement, attacks, defense, and collisions */
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
                Equipements ramasse= CollisionEquipement.collisionEquipement(gamePanel.getEquipements(), this, gamePanel.getTileSize());
                boolean pass = CollisionDistance.collisionDistance(gamePanel.getPersonnages(), this, gamePanel.getTileSize());
                if (!pass){
                    notPassing();
                }
                if (ramasse !=null){
                    ramasser(ramasse);
                }
                if(canBlock() && canDefend() && keyHandler.isDefPressed()){
                    direction = defenseMovement();
                    lastDef = direction;
                }
                else if(canAttack() && keyHandler.isAtkPressed()) { // Quand est-ce que jg peut attaker
                    direction = atkMovement();
                    lastAtk = direction;
                    Players receiver = AttackCollisions.attackCollisions(gamePanel.getPersonnages(), direction, this, gamePanel.getTileSize());
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
                        position.set(0, gamePanel.getScreenWidth()/2);
                        position.set(1,gamePanel.getScreenHeight()-3*gamePanel.getTileSize());
                        gamePanel.getTileM().changeMap("MERCHANT");
                    }
                    if (respass.equals("exitmerchant")){
                        position.set(0, 4*gamePanel.getTileSize());
                        position.set(1,gamePanel.getScreenHeight()-3*gamePanel.getTileSize());
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

    /** Draws the player, including movement, attack, defense, and damage animations */
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
                positionImage2.set(0, position.get(0) + gamePanel.getTileSize());
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
                positionImage2.set(1, position.get(1)-gamePanel.getTileSize());
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
                positionImage2.set(0, position.get(0)-gamePanel.getTileSize());
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
                positionImage2.set(1, position.get(1)+gamePanel.getTileSize());
                break;
            case "atkleftdown":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                image2 = weaponsSprites.get(5);
                positionImage2.set(0, position.get(0)-gamePanel.getTileSize());
                positionImage2.set(1, position.get(1)+gamePanel.getTileSize());
                break;
            case "atkrightdown":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                image2 = weaponsSprites.get(7);
                positionImage2.set(0, position.get(0)+gamePanel.getTileSize());
                positionImage2.set(1, position.get(1)+gamePanel.getTileSize());
                break;
            case "atkrightup":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                image2 = weaponsSprites.get(6);
                positionImage2.set(0, position.get(0)+gamePanel.getTileSize());
                positionImage2.set(1, position.get(1)-gamePanel.getTileSize());
                break;
            case "atkleftup":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                image2 = weaponsSprites.get(4);
                positionImage2.set(0, position.get(0)-gamePanel.getTileSize());
                positionImage2.set(1, position.get(1)-gamePanel.getTileSize());
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
        g2.drawImage(image, position.get(0), position.get(1), gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        if (image2 != null) {
            g2.drawImage(image2, positionImage2.get(0), positionImage2.get(1), gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        }
    };





}
