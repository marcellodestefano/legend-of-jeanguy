package input;
import main.GamePanel;
import main.UI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, atkPressed, defPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // TITLE STATE

        if(gp.GameState == gp.titleState){
            if(code == KeyEvent.VK_Z && gp.UI.commandNum > 0){
                gp.UI.commandNum--;
            }
            if(code == KeyEvent.VK_S && gp.UI.commandNum < 2){
                gp.UI.commandNum++;
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.UI.commandNum == 0){
                    gp.GameState = gp.playState;
                }
                if(gp.UI.commandNum == 1){
                    // ajouter menu setting
                    // gp.GameState = gp.Settings;
                }
                if(gp.UI.commandNum == 2){
                    System.exit(0);
                }
            }

        }

        // PAUSE STATE

        if(gp.GameState == gp.pauseState){
            if(code == KeyEvent.VK_Z && gp.UI.commandNum > 0){
                gp.UI.commandNum--;
            }
            if(code == KeyEvent.VK_S && gp.UI.commandNum < 1){
                gp.UI.commandNum++;
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.UI.commandNum == 0){
                    gp.GameState = gp.titleState;
                }
                if(gp.UI.commandNum == 1){
                    System.exit(0);
                }
            }
        }

        // COMMAND STATE

        if(gp.GameState == gp.commandState){
            if(code == KeyEvent.VK_Z && gp.UI.commandNum > 0){
                gp.UI.commandNum--;
            }
            if(code == KeyEvent.VK_S && gp.UI.commandNum < 1){
                gp.UI.commandNum++;
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.UI.commandNum == 0){
                    gp.GameState = gp.titleState;
                }
            }
        }


        // PLAY STATE

        if(code == KeyEvent.VK_ESCAPE){
            if(gp.GameState == gp.playState){
                gp.GameState = gp.pauseState;
            } else if (gp.GameState == gp.pauseState){
                gp.GameState = gp.playState;
            }
        }

        if(code == KeyEvent.VK_Q){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_Z){
            upPressed = true;
        }
        if(code == KeyEvent.VK_J){
            atkPressed = true;
        }
        if(code == KeyEvent.VK_K){
            defPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_Q){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_Z){
            upPressed = false;
        }
        if(code == KeyEvent.VK_J){
            atkPressed = false;
        }
        if(code == KeyEvent.VK_K){
            defPressed = false;
        }

    }
}
