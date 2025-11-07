package entities.players;


import java.util.ArrayList;
import java.util.Arrays;

public class JeanGuy extends Playable {
    public JeanGuy(){
        super("Jean-Guy", 0, new ArrayList<Integer>(Arrays.asList(7,7,0)), 2, 100,5,false,true,2,true, Arrays.asList("",""),Arrays.asList("",""));
    }
}
