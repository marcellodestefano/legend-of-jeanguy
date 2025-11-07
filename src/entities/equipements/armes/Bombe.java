package entities.equipements.armes;
import java.util.*;

public class Bombe extends Armes{
    protected int explosionTime;
    protected int radius;
    protected int nombre;
    protected int prix;


    Bombe(int explosionTime, int radius, int nombre, List<String> BombSprite, int prix){
        super(false, "Bombe", 7, new ArrayList<String>(Arrays.asList("","")), false, 1);
        this.explosionTime = explosionTime;
        this.radius = radius;
        this.nombre = nombre;
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
