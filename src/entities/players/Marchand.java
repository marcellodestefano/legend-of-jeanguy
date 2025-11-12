package entities.players;

import main.GamePanel;

import java.util.*;

public class Marchand extends NonPlayable {
    public Marchand(GamePanel panel) {
        super(panel,"Marchand", 0, new ArrayList<Integer>(Arrays.asList(12,12,0)), 0, 1,5,false,false,0,false, Arrays.asList("",""),Arrays.asList("",""));
    }
}
