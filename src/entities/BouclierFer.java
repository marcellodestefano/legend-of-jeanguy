package main.java.com.LegendOfJG.entities;
import java.util.*;

public class BouclierFer extends Armes{
    protected int prix;

    BouclierFer(int prix){
        super(false, "Bouclier_en_fer", 3, new ArrayList<String>(Arrays.asList("","")), true, 1);
        this.prix = prix;
    }

    public int getPrix(){
        return prix;
    }

}
