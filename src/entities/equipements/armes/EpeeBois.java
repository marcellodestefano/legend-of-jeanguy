package entities.equipements.armes;

import java.util.ArrayList;
import java.util.Arrays;

public class EpeeBois extends Armes{
    protected int prix;

    EpeeBois(int prix){
        super(false, "EpeeBois", 5, new ArrayList<String>(Arrays.asList("","")), true, 1);
        this.prix = prix;
    }

    public int getPrix(){
        return prix;
    }
}
