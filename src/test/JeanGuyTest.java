package test;

import entities.players.Gumba;
import entities.players.JeanGuy;
import entities.players.NonPlayable;
import input.KeyHandler;
import main.GamePanel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JeanGuyTest {

    GamePanel gamePanel = new GamePanel();
    KeyHandler keyHandler = new KeyHandler();
    JeanGuy jeanGuy = new JeanGuy(gamePanel, keyHandler);

    @Nested
    @DisplayName("Tests d'initialisation")
    class InitializationTests {
        @Test
        @DisplayName("Test de la position de spawn de JeanGuy")
        void positionDeSpawn() {
            assertEquals(200, jeanGuy.getPosition().get(0));
            assertEquals(200, jeanGuy.getPosition().get(1));
            assertEquals(0, jeanGuy.getPosition().get(2));
        }

        @Test
        @DisplayName("Test de la valeur de vie de départ de JeanGuy")
        void vieDeDepart() {
            assertEquals(5, jeanGuy.getHp());
            assertEquals(5, jeanGuy.getHpMax());
        }

        @Test
        @DisplayName("Test des gemmes de départ de JeanGuy qui doivent être initialement à 0")
        void gemmeDeDepart() {
            assertEquals(0, jeanGuy.getArgent());
        }

        @Test
        @DisplayName("Test de l'équipement de départ de JeanGuy qui doit initialement avoir une éppeé en bois")
        void equipementDeDepart() {
            assertNotNull(jeanGuy.getInventaire());
        }
    }



    //Système d'attack

    @Nested
    @DisplayName("Tests d'attaque")
    class AttackTests {

        @Test
        @DisplayName("atkMovement avec touche up")
        void testAtkMovementUp() {
            keyHandler.upPressed = true;

            String result = jeanGuy.atkMovement();

            assertEquals("atkup", result);
            assertTrue(jeanGuy.isAttacking());
        }

        @Test
        @DisplayName("atkMovement avec touche down")
        void testAtkMovementDown() {
            keyHandler.downPressed = true;

            String result = jeanGuy.atkMovement();

            assertEquals("atkdown", result);
        }

        @Test
        @DisplayName("atkMovement avec touche left")
        void testAtkMovementLeft() {
            keyHandler.leftPressed = true;

            String result = jeanGuy.atkMovement();

            assertEquals("atkleft", result);
        }

        @Test
        @DisplayName("atkMovement avec touche right")
        void testAtkMovementRight() {
            keyHandler.rightPressed = true;

            String result = jeanGuy.atkMovement();

            assertEquals("atkright", result);
        }

        @Test
        @DisplayName("atkMovement diagonale up-left")
        void testAtkMovementUpLeft() {
            keyHandler.upPressed = true;
            keyHandler.leftPressed = true;

            String result = jeanGuy.atkMovement();

            assertEquals("atkleftup", result);
        }

        @Test
        @DisplayName("atkMovement diagonale down-right")
        void testAtkMovementDownRight() {
            keyHandler.downPressed = true;
            keyHandler.rightPressed = true;

            String result = jeanGuy.atkMovement();

            assertEquals("atkrightdown", result);
        }

        @Test
        @DisplayName("atkMovement sans touche utilise lastdir")
        void testAtkMovementNoKey() {
            //jeanGuy.lastdir = "down";

            String result = jeanGuy.atkMovement();

            assertEquals("atkdown", result);
        }

        @Test
        @DisplayName("canAttack retourne true quand attackSpeed est 0")
        void testCanAttackWhenReady() {
            jeanGuy.setAttackSpeed(0);
            assertTrue(jeanGuy.canAttack());
        }

        @Test
        @DisplayName("canAttack retourne false quand attackSpeed > 0")
        void testCannotAttackWhenCooldown() {
            jeanGuy.setAttackSpeed(10);
            assertFalse(jeanGuy.canAttack());
        }

        @Test
        @DisplayName("isAttacking retourne true pendant l'attaque")
        void testIsAttackingDuringAttack() {
            jeanGuy.setCpAtk(5);
            assertTrue(jeanGuy.isAttacking());
        }

        @Test
        @DisplayName("isAttacking retourne false hors attaque")
        void testIsNotAttackingWhenIdle() {
            jeanGuy.setCpAtk(0);
            assertFalse(jeanGuy.isAttacking());
        }
    }

    //Système de protection
    @Nested
    @DisplayName("Système de défense")
    class DefenseTests{
        @Test
        void rammasserBouclier() {
            jeanGuy.rammasserBouclier();
            assertEquals("/assets/playershield/Haut1.png", jeanGuy.getSpritePaths().get(0));
        }

        @Test
        @DisplayName("Défense down")
        void testDefenseDownBlocksUp() {
            jeanGuy.direction = "defdown";
            assertTrue(jeanGuy.defense("up-player"));
        }

        @Test
        @DisplayName("Défense up")
        void testDefenseUpBlocksDown() {
            jeanGuy.direction = "defup";
            assertTrue(jeanGuy.defense("down-player"));
        }

        @Test
        @DisplayName("Défense right")
        void testDefenseRightBlocksLeft() {
            jeanGuy.direction = "defright";
            assertTrue(jeanGuy.defense("left-player"));
        }

        @Test
        @DisplayName("Défense left")
        void testDefenseLeftBlocksRight() {
            jeanGuy.direction = "defleft";
            assertTrue(jeanGuy.defense("right-player"));
        }

        @Test
        @DisplayName("Défense incorrecte ne bloque pas")
        void testIncorrectDefenseDoesNotBlock() {
            jeanGuy.direction = "defdown";
            assertFalse(jeanGuy.defense("down-player"));
            assertFalse(jeanGuy.defense("left-player"));
            assertFalse(jeanGuy.defense("right-player"));
        }

        @Test
        @DisplayName("Sans défense active, aucun blocage")
        void testNoDefenseNoBlock() {
            jeanGuy.direction = "up";
            assertFalse(jeanGuy.defense("up-player"));
        }
    }

    @Nested
    @DisplayName("Test de reception de dégâts")
    class ReceiveDamageTest{

        NonPlayable enemy = new Gumba(gamePanel);

        @Test
        @DisplayName("Recevoir des dégâts réduit les HP")
        void testReceiveDamageReducesHP() {
            int initialHP = jeanGuy.getHp();

            jeanGuy.receiveDamage(enemy, 2, "up-player");

            assertEquals(initialHP - 2, jeanGuy.getHp());
        }

        @Test
        @DisplayName("Recevoir des dégâts active l'état de dégâts")
        void testReceiveDamageActivatesDamageState() {

            jeanGuy.receiveDamage(enemy, 2, "up-player");

            assertTrue(jeanGuy.isGettingDamage());
            assertEquals("up-player", jeanGuy.getDmgdir());
        }

        @Test
        @DisplayName("HP ne peut pas être négatif")
        void testHPCannotBeNegative() {

            jeanGuy.receiveDamage(enemy, 999, "up-player");

            assertTrue(jeanGuy.getHp() >= 0);
        }

        @Test
        @DisplayName("Défense réussie renvoie les dégâts")
        void testSuccessfulDefenseReflectsDamage() {
            int initialHP = jeanGuy.getHp();

            jeanGuy.direction = "defdown";
            jeanGuy.receiveDamage(enemy, 2, "up-player");
            assertEquals(initialHP, jeanGuy.getHp());
        }

        @Test
        @DisplayName("isGettingDamage retourne false quand cpdmg est 0")
        void testIsGettingDamageWhenNotDamaged() {
            assertFalse(jeanGuy.isGettingDamage());
        }
    }

    @Nested
    @DisplayName("Tests de déplacement")
    class DeplacementTests {

        @Test
        @DisplayName("Déplacement vers le haut")
        void testNormalMovementUp() {
            keyHandler.upPressed = true;
            int initialY = jeanGuy.getPosition().get(1);

            String result = jeanGuy.normalMovement();

            assertEquals("up", result);
            assertTrue(jeanGuy.getPosition()
                    .get(1) < initialY);
        }

        @Test
        @DisplayName("Déplacement vers le bas")
        void testNormalMovementDown() {
            keyHandler.downPressed = true;
            int initialY = jeanGuy.getPosition().get(1);

            String result = jeanGuy.normalMovement();

            assertEquals("down", result);
            assertTrue(jeanGuy.getPosition().get(1) > initialY);
        }

        @Test
        @DisplayName("Déplacement vers la gauche")
        void testNormalMovementLeft() {
            keyHandler.leftPressed = true;
            int initialX = jeanGuy.getPosition().get(0);

            String result = jeanGuy.normalMovement();

            assertEquals("left", result);
            assertTrue(jeanGuy.getPosition().get(0) < initialX);
        }

        @Test
        @DisplayName("Déplacement vers la droite")
        void testNormalMovementRight() {
            keyHandler.rightPressed = true;
            int initialX = jeanGuy.getPosition().get(0);

            String result = jeanGuy.normalMovement();

            assertEquals("right", result);
            assertTrue(jeanGuy.getPosition().get(0) > initialX);
        }

        @Test
        @DisplayName("Pas de pression retourne lastdir")
        void testNoMovementReturnsLastDir() {

            String result = jeanGuy.normalMovement();

            assertEquals("down", result);
        }
    }
}