package main.java.com.LegendOfJG.entities;

import java.util.ArrayList;
import java.util.Arrays;

public class BouclierBois extends Armes{
    protected int prix;

    BouclierBois(int prix){
        super(false, "Bouclier_en_bois", 1, new ArrayList<String>(Arrays.asList("","")), true, 1);
        this.prix = prix;
    }

    public int getPrix(){
        return prix;
    }
}
