package entities.players;


import main.GamePanel;

import java.util.ArrayList;
import java.util.Arrays;

public class Octorok extends NonPlayable{
    public Octorok(GamePanel panel) {
        super(panel,"Octorok", 10, new ArrayList<Integer>(Arrays.asList(5,5,0)), 5, 30,2,false,false,1,true, Arrays.asList("",""),Arrays.asList("",""));
    }
}
