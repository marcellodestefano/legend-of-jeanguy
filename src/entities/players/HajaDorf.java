package entities.players;

import main.GamePanel;

import java.util.ArrayList;
import java.util.Arrays;

public class HajaDorf extends NonPlayable{
    public HajaDorf(GamePanel panel) {
        super(panel,"HajaDorf", 20, new ArrayList<Integer>(Arrays.asList(8,8,0)), 15, 100,3,false,false,1,true, Arrays.asList("",""),Arrays.asList("",""));
    }
}

