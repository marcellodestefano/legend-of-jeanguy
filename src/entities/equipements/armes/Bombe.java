package entities.equipements.armes;
import main.GamePanel;

import java.util.*;

public class Bombe extends Armes{
    protected int explosionTime;
    protected int radius;
    protected int nombre;
    protected int prix;


    public Bombe(GamePanel gp){
        super(gp, false, "Bombe", 7, new ArrayList<String>(Arrays.asList("","")), false, 1,new ArrayList<Integer>(Arrays.asList(100,100,0)));
        this.explosionTime = 5;
        this.radius = 2;
        this.nombre = 3;
    }

    public int getExplosionTime(){
        return explosionTime;
    }

    public int getRadius(){
        return radius;
    }

    public int getNombre(){
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public int getPrix(){
        return prix;
    }
}
