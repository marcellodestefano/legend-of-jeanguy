package main.java.com.LegendOfJG.entities;

import java.util.ArrayList;
import java.util.Arrays;

public class EpeeFer extends Armes{
    protected int prix;

    EpeeFer(int prix){
        super(false, "EpeeFer", 10, new ArrayList<String>(Arrays.asList("","")), true, 1);
        this.prix = prix;
    }

    public int getPrix(){
        return prix;
    }
}
