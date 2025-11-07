package main.java.com.LegendOfJG.entities;

import java.util.ArrayList;
import java.util.Arrays;

public class MaskGuy extends NonPlayable{
    public MaskGuy() {
        super("MaskGuy", 5, new ArrayList<Integer>(Arrays.asList(3,3,0)), 1, 30,2,false,true,1,true, Arrays.asList("",""),Arrays.asList("",""));
    }
}
