package entities.players;


import entities.equipements.soins.CoeurMax;

import java.util.ArrayList;
import java.util.Arrays;

public class JeanGuy extends Playable {
    protected int HpMax = 5;

    public JeanGuy(){
        super("Jean-Guy", 0, new ArrayList<Integer>(Arrays.asList(7,7,0)), 2, 5,5,false,true,2,true, Arrays.asList("",""),Arrays.asList("",""));
    }

    public int getHpMax() {
        return HpMax;
    }

    public int setHpMax(CoeurMax coeurMax) {
        return this.HpMax ++;
    }
}
