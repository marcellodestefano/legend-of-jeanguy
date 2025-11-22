package test;

import entities.equipements.Equipements;
import entities.equipements.armes.BouclierBois;
import entities.equipements.armes.EpeeBois;
import entities.equipements.soins.Coeur;
import entities.equipements.soins.CoeurMax;
import main.GamePanel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class EquipementTest {

    @Test
    public void epeeBois() {
        GamePanel gamePanel = new GamePanel();
        EpeeBois epee = new EpeeBois(gamePanel);
        assertEquals(0, epee.getId());
        assertEquals(1, epee.getUnite());
        assertEquals("EpeeBois", epee.getName());
        System.out.println(epee.getSpritePath());
        System.out.println(epee.isRamasser());
        System.out.println(epee.getPosition());
        epee.setRamasser();
        System.out.println(epee.isRamasser());
        System.out.println(epee.getRange());
    }

    @Test
    public void bouclierBois() {
        GamePanel gamePanel = new GamePanel();
        BouclierBois bouclier = new BouclierBois(gamePanel);
        System.out.println(bouclier.getPrix());
        System.out.println(bouclier.getId());
        System.out.println(bouclier.getUnite());
        System.out.println(bouclier.getName());
        System.out.println(bouclier.getSpritePath());
        System.out.println(bouclier.isRamasser());
        System.out.println(bouclier.getPosition());
        bouclier.setRamasser();
        System.out.println(bouclier.isRamasser());
        System.out.println(bouclier.getRange());
    }

    @Test
    public void coeur() {
        GamePanel gamePanel = new GamePanel();
        Coeur coeur = new Coeur(gamePanel);
        System.out.println(coeur.getDropPercentage());
        System.out.println(coeur.getId());
        System.out.println(coeur.getUnite());
        System.out.println(coeur.getName());
        System.out.println(coeur.getSpritePath());
        System.out.println(coeur.isRamasser());
        System.out.println(coeur.getPosition());
        coeur.setRamasser();
        coeur.setPosition(new ArrayList<Integer>(Arrays.asList(100,500,0)));
        System.out.println(coeur.getPosition());
        System.out.println(coeur.isRamasser());
    }

    @Test
    public void coeurMax() {
        GamePanel gamePanel = new GamePanel();
        CoeurMax  coeurMax = new CoeurMax(gamePanel);
        System.out.println(coeurMax.getDropPercentage());
        System.out.println(coeurMax.getId());
        System.out.println(coeurMax.getUnite());
        System.out.println(coeurMax.getName());
        System.out.println(coeurMax.getSpritePath());
        System.out.println(coeurMax.isRamasser());
        System.out.println(coeurMax.getPosition());
        coeurMax.setRamasser();
        coeurMax.setPosition(new ArrayList<Integer>(Arrays.asList(100,500,0)));
        System.out.println(coeurMax.getPosition());
        System.out.println(coeurMax.isRamasser());

    }
}