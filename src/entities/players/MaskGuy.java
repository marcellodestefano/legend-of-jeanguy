package entities.players;


import main.GamePanel;

import java.util.ArrayList;
import java.util.Arrays;

public class MaskGuy extends NonPlayable{
    public MaskGuy(GamePanel panel) {
        super(panel,"MaskGuy", 5, new ArrayList<Integer>(Arrays.asList(3,3,0)), 1, 30,2,false,true,1,true, Arrays.asList("",""),Arrays.asList("",""));
    }
}
