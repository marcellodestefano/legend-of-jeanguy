package entities.players;

import main.GamePanel;

import java.util.*;

public class Genie extends NonPlayable{
    public Genie(GamePanel panel) {
        super(panel,"Genie", 10, new ArrayList<Integer>(Arrays.asList(15,15,0)), 5, 30,2,false,false,1,true, Arrays.asList("",""),Arrays.asList("",""));
    }
}
