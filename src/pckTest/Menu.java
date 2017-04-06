/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckTest;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Alex
 */
public class Menu extends BasicGameState {

    private Image background;
    public static final int ID = 1;
    private StateBasedGame game;
    private AppGameContainer app;

    public Menu(int startMenu, AppGameContainer app) {
        this.app = app;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = game;
        this.background = new Image("backgroundMario.png");
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        background.draw(0, 0, gc.getWidth(), gc.getHeight());

    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        int posX = Mouse.getX();
        int posY = Mouse.getY();

        System.out.println("X:" + posX + "Y:" + posY);

        if ((posX > 175 && posX < 350) && (posY > 257 && posY < 272)) {
            if (Mouse.isButtonDown(0)) {
                sbg.enterState(2);
                app.setDisplayMode(640, 250, false);
            }
        }

        if ((posX > 175 && posX < 350) && (posY > 209 && posY < 224)) {
            if (Mouse.isButtonDown(0)) {

            }
        }

        if ((posX > 238 && posX < 270) && (posY > 161 && posY < 176)) {
            if (Mouse.isButtonDown(0)) {

            }
        }
    }

    public void keyReleased(int key, char c) {
        game.enterState(WindowGame.ID);
    }

    public int getID() {
        return 0;
    }

}
