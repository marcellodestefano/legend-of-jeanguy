package entities.equipements.armes;
import java.util.ArrayList;
import java.util.List;
import entities.equipements.Equipements;
import main.GamePanel;

/**
 * Abstract class representing a weapon equipment.
 * Extends the generic {@link Equipements} class by adding weapon-specific
 * attributes such as range.
 */
public abstract class Armes extends Equipements{
    /** Effective range of the weapon (melee or distance). */
    protected int range;

    /**
     * Constructs a new weapon.
     *
     * @param gp          Reference to the GamePanel.
     * @param drop        Whether the weapon is dropped on the ground.
     * @param name        Name of the weapon.
     * @param unite       Quantity or damage unit depending on game logic.
     * @param spritePath  Paths to weapon sprites.
     * @param isMelee     Indicates if the weapon is melee (unused logically here).
     * @param range       Effective range of the weapon.
     * @param position    Position of the weapon in the world.
     */
    public Armes(GamePanel gp, boolean drop, String name, int unite, List<String> spritePath, boolean isMelee, int range, ArrayList<Integer> position){
        super(gp, drop, name, unite, spritePath, position);
        this.range = range;

    }

    /**
     * @return The range of the weapon.
     */
    public int getRange(){
        return range;
    }
}
