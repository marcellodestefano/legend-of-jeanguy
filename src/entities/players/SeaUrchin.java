package entities.players;
import main.GamePanel;

import java.util.ArrayList;
import java.util.Arrays;

public class SeaUrchin extends NonPlayable{
    public SeaUrchin(GamePanel panel) {
        super(panel,"MaskGuy", 4, new ArrayList<Integer>(Arrays.asList(2,2,0)), 1, 1,2,false,true,1,false, Arrays.asList("",""),Arrays.asList("",""));
    }
}

