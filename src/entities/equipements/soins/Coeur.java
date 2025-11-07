package entities.equipements.soins;

import java.util.ArrayList;
import java.util.Arrays;

public class Coeur extends Soins{

    Coeur(){
        super(true, "Coeur", 1, new ArrayList<String>(Arrays.asList("","")), 0);
    }
}
