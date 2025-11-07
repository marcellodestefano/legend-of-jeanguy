package com.LegendOfJG.entities;

import java.util.*;

public class Clefs{
    private  ArrayList<Integer> dropSalles = new ArrayList<>(List.of(0, 1, 3));
    private static int count;
    private int id;

    public Clefs(int id){
        this.id = id;

        if(dropSalles.contains(id)){
            count++;
        }
    }

    public ArrayList<Integer> getDropSalles(){
        return dropSalles;
    }

    public int getCount(){
        return count;
    }
}etCount());