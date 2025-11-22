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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class JeanGuy extends Playable {


    public JeanGuy(GamePanel panel, KeyHandler keyHandler) {
        super(panel,"Jean-Guy", 1, new ArrayList<Integer>(Arrays.asList(200,200,0)), 2, 5,3,
                false,true,30,true, Arrays.asList("",""), Arrays.asList("/assets/player/Haut1.png",
                        "/assets/player/Haut2.png","/assets/player/Bas1.png","/assets/player/Bas2.png", "/assets/player/Gauche1.png",
                        "/assets/player/Gauche2.png","/assets/player/Droite1.png","/assets/player/Droite2.png","/assets/playerdeath/linkdeath.png"),
                keyHandler);


        this.attackSprites = new ArrayList<>(Arrays.asList("/assets/playerattack/attackup.png","/assets/playerattack/attackdown.png",
                "/assets/playerattack/attackleft.png","/assets/playerattack/attackright.png"));

        this.damageSprites = new ArrayList<>(Arrays.asList("/assets/playerhit/hithaut1.png","/assets/playerhit/hithaut2.png",
                "/assets/playerhit/hitbas1.png","/assets/playerhit/hitbas2.png","/assets/playerhit/hitgauche1.png","/assets/playerhit/hitgauche2.png",
                "/assets/playerhit/hitdroit1.png","/assets/playerhit/hitdroit2.png"));
        this.defenseSprites = new ArrayList<>(Arrays.asList("/assets/playerblocking/up1.png","/assets/playerblocking/up2.png",
                "/assets/playerblocking/down1.png","/assets/playerblocking/down2.png","/assets/playerblocking/left1.png","/assets/playerblocking/left2.png",
                "/assets/playerblocking/right1.png","/assets/playerblocking/right2.png"));

        if (keyHandler.isRedJeanGuy()){
            redJG();
        }
        this.getPlayerImage();
        this.myWeaponImages();
        this.getAttackImage();
        this.getDamageImage();
    }

    public void redJG(){

        this.spritesPaths.set(0, "/assets/redplayer/redsprite/haut1.png");
        this.spritesPaths.set(1, "/assets/redplayer/redsprite/haut2.png");
        this.spritesPaths.set(2, "/assets/redplayer/redsprite/bas1.png");
        this.spritesPaths.set(3, "/assets/redplayer/redsprite/bas2.png");
        this.spritesPaths.set(4, "/assets/redplayer/redsprite/gauche1.png");
        this.spritesPaths.set(5, "/assets/redplayer/redsprite/gauche2.png");
        this.spritesPaths.set(6, "/assets/redplayer/redsprite/droite1.png");
        this.spritesPaths.set(7, "/assets/redplayer/redsprite/droite2.png");
        this.spritesPaths.set(8, "/assets/redplayer/reddeath/reddeath.png");

        this.defenseSprites.set(0, "/assets/redplayer/redshield/up1.png");
        this.defenseSprites.set(1, "/assets/redplayer/redshield/up2.png");
        this.defenseSprites.set(2, "/assets/redplayer/redshield/down1.png");
        this.defenseSprites.set(3, "/assets/redplayer/redshield/down2.png");
        this.defenseSprites.set(4, "/assets/redplayer/redshield/left1.png");
        this.defenseSprites.set(5, "/assets/redplayer/redshield/left2.png");
        this.defenseSprites.set(6, "/assets/redplayer/redshield/right1.png");
        this.defenseSprites.set(7, "/assets/redplayer/redshield/right2.png");

        this.attackSprites.set(0, "/assets/redplayer/redattack/attackup.png");
        this.attackSprites.set(1, "/assets/redplayer/redattack/attackdown.png");
        this.attackSprites.set(2, "/assets/redplayer/redattack/attackleft.png");
        this.attackSprites.set(3, "/assets/redplayer/redattack/attackright.png");
    }



    public void rammasserBouclier(){

        if (keyHandler.isRedJeanGuy() == true){
            this.spritesPaths.set(0, "/assets/redplayer/redblocking/up1.png");
            this.spritesPaths.set(1, "/assets/redplayer/redblocking/up2.png");
            this.spritesPaths.set(2, "/assets/redplayer/redblocking/down1.png");
            this.spritesPaths.set(3, "/assets/redplayer/redblocking/down2.png");
            this.spritesPaths.set(4, "/assets/redplayer/redblocking/left1.png");
            this.spritesPaths.set(5, "/assets/redplayer/redblocking/left2.png");
            this.spritesPaths.set(6, "/assets/redplayer/redblocking/right1.png");
            this.spritesPaths.set(7, "/assets/redplayer/redblocking/right2.png");
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
        else{
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
    }}
    @Override
    public void ramasser(Equipements ramasse){
        if (ramasse instanceof BouclierBois && this.getArgent() >= ((BouclierBois) ramasse).getPrix()){
            this.argent -= ((BouclierBois) ramasse).getPrix();
            this.setInventaire(ramasse);
            ramasse.setRamasser();
            this.rammasserBouclier();
        }
        else if (ramasse instanceof BouclierBois && this.getArgent() <= ((BouclierBois) ramasse).getPrix()){
            gamePanel.UI.showMessage("Tu n'as pas assez d'argent ! Prix : " + ((BouclierBois) ramasse).getPrix());
        }
        else if (ramasse instanceof CoeurMax){
            this.ramasserCoeurMax();
            ramasse.setRamasser();

        }else if (ramasse instanceof Coeur){
            this.ramasserCoeur();
            ramasse.setRamasser();
        }
    }









}
