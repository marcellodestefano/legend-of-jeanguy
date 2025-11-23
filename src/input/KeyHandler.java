package input;

import main.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Handles keyboard input for the game.
 * <p>
 * This class implements {@link KeyListener} and is responsible for
 * detecting key presses and releases. It tracks movement keys,
 * attack/defense actions, and menu navigation, including character
 * selection, pause, victory, and game over menus.
 */
public class KeyHandler implements KeyListener {
    /** Reference to the main {@link GamePanel} for state management */
    private final GamePanel gp;
    /** Movement key states */
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    /** Action key states */
    private boolean atkPressed, defPressed;
    /** Whether the player selected the "Red JeanGuy" character */
    private boolean redJeanGuy = false;
    /**
     * Constructs a new KeyHandler for the given {@link GamePanel}.
     *
     * @param gp The main game panel
     */
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }


    public boolean isUpPressed() { return upPressed; }
    public boolean isDownPressed() { return downPressed; }
    public boolean isLeftPressed() { return leftPressed; }
    public boolean isRightPressed() { return rightPressed; }
    public boolean isAtkPressed() { return atkPressed; }
    public boolean isDefPressed() { return defPressed; }
    public boolean isRedJeanGuy() { return redJeanGuy; }

    public void setUpPressed(boolean value) { this.upPressed = value; }
    public void setDownPressed(boolean value) { this.downPressed = value; }
    public void setLeftPressed(boolean value) { this.leftPressed = value; }
    public void setRightPressed(boolean value) { this.rightPressed = value; }
    /**
     * Unused keyTyped method from {@link KeyListener}.
     *
     * @param e KeyEvent object
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }
    /**
     * Handles key press events.
     * <p>
     * Detects the current game state and executes the corresponding
     * actions for title screen, pause, command, victory, or game over.
     * Movement and action keys are also updated.
     *
     * @param e KeyEvent object
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();


        if (gp.getGameState() == gp.getTitleState()) {
            handleTitleState(code);
        } else if (gp.getGameState() == gp.getPauseState()) {
            handlePauseState(code);
        } else if (gp.getGameState() == gp.getCommandState()) {
            handleCommandState(code);
        } else if (gp.getGameState() == gp.getVictoryState()) {
            handleVictoryState(code);
        } else if (gp.getGameState() == gp.getGameOverState()) {
            handleGameOverState(code);
        }


        handleEscapeKey(code);


        handleMovementKeys(code);
    }


    private void handleTitleState(int code) {
        if (gp.getTitleScreenState() == 0) {
            handleMainMenu(code);
        } else if (gp.getTitleScreenState() == 1) {
            handleCharacterSelection(code);
        }
    }

    private void handleMainMenu(int code) {

        if (code == KeyEvent.VK_Z && gp.getMenuCommand() > 0) {
            gp.decrementMenuCommand();
        }
        if (code == KeyEvent.VK_S && gp.getMenuCommand() < 2) {
            gp.incrementMenuCommand();
        }


        if (code == KeyEvent.VK_ENTER) {
            executeMainMenuAction();
        }
    }

    private void executeMainMenuAction() {
        int command = gp.getMenuCommand();

        if (command == 0) {

            gp.setTitleScreenState(1);
            gp.setMenuCommand(0);
        } else if (command == 1) {

            gp.setGameState(gp.getCommandState());
            gp.setMenuCommand(0);
        } else if (command == 2) {

            System.exit(0);
        }
    }

    private void handleCharacterSelection(int code) {

        if (code == KeyEvent.VK_Z && gp.getMenuCommand() > 0) {
            gp.decrementMenuCommand();
        }
        if (code == KeyEvent.VK_S && gp.getMenuCommand() < 2) {
            gp.incrementMenuCommand();
        }


        if (code == KeyEvent.VK_ENTER) {
            executeCharacterSelection();
        }
    }

    private void executeCharacterSelection() {
        int command = gp.getMenuCommand();

        if (command == 0) {
            startGameWithCharacter(false);
        } else if (command == 1) {
            startGameWithCharacter(true);
        } else if (command == 2) {
            gp.setTitleScreenState(0);
            gp.setMenuCommand(0);
        }
    }

    private void startGameWithCharacter(boolean isRed) {
        this.redJeanGuy = isRed;
        gp.startGame();
        gp.setGameState(gp.getPlayState());
    }

    private void handlePauseState(int code) {
        if (code == KeyEvent.VK_Z && gp.getMenuCommand() > 0) {
            gp.decrementMenuCommand();
        }
        if (code == KeyEvent.VK_S && gp.getMenuCommand() < 1) {
            gp.incrementMenuCommand();
        }
        if (code == KeyEvent.VK_ENTER) {
            executePauseMenuAction();
        }
    }

    private void executePauseMenuAction() {
        if (gp.getMenuCommand() == 0) {
            gp.setTitleScreenState(0);
            gp.setGameState(gp.getTitleState());
            gp.setMenuCommand(0);
        } else if (gp.getMenuCommand() == 1) {
            System.exit(0);
        }
    }

    private void handleCommandState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gp.setGameState(gp.getTitleState());
            gp.setMenuCommand(0);
        }
    }

    private void handleVictoryState(int code) {
        if (code == KeyEvent.VK_Z && gp.getMenuCommand() > 0) {
            gp.decrementMenuCommand();
        }
        if (code == KeyEvent.VK_S && gp.getMenuCommand() < 1) {
            gp.incrementMenuCommand();
        }


        if (code == KeyEvent.VK_ENTER) {
            executeVictoryMenuAction();
        }
    }

    private void executeVictoryMenuAction() {
        if (gp.getMenuCommand() == 0) {

            gp.resetGame();
            gp.setTitleScreenState(0);
            gp.setGameState(gp.getTitleState());
        } else if (gp.getMenuCommand() == 1) {

            System.exit(0);
        }
    }

    private void handleGameOverState(int code) {

        if (code == KeyEvent.VK_Z && gp.getMenuCommand() > 0) {
            gp.decrementMenuCommand();
        }
        if (code == KeyEvent.VK_S && gp.getMenuCommand() < 2) {
            gp.incrementMenuCommand();
        }


        if (code == KeyEvent.VK_ENTER) {
            executeGameOverMenuAction();
        }
    }

    private void executeGameOverMenuAction() {
        int command = gp.getMenuCommand();

        if (command == 0 || command == 1) {
            gp.resetGame();
            gp.setGameState(gp.getTitleState());

            if (command == 1) {
                gp.setTitleScreenState(0);
                gp.setMenuCommand(0);
            }
        } else if (command == 2) {
            System.exit(0);
        }
    }

    private void handleEscapeKey(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            int currentState = gp.getGameState();

            if (currentState == gp.getPlayState()) {
                gp.setGameState(gp.getPauseState());
            } else if (currentState == gp.getPauseState()) {
                gp.setGameState(gp.getPlayState());
            }
        }
    }

    private void handleMovementKeys(int code) {
        switch (code) {
            case KeyEvent.VK_Q:
                leftPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
            case KeyEvent.VK_Z:
                upPressed = true;
                break;
            case KeyEvent.VK_J:
                atkPressed = true;
                break;
            case KeyEvent.VK_K:
                defPressed = true;
                break;
        }
    }
    /**
     * Handles key release events to reset movement and action keys.
     *
     * @param e KeyEvent object
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_Q:
                leftPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
            case KeyEvent.VK_Z:
                upPressed = false;
                break;
            case KeyEvent.VK_J:
                atkPressed = false;
                break;
            case KeyEvent.VK_K:
                defPressed = false;
                break;
        }
    }
}
