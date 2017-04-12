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
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Alex
 */
public class Menu extends BasicGameState {

    private Image background;
    private final Image menu;
    public static final int ID = 1;
    private StateBasedGame game;
    private AppGameContainer app;
    private Graphics g;
    private SoundFx sounds = new SoundFx();


    public Menu(int startMenu, AppGameContainer app) throws SlickException {
        this.app = app;
        menu = new Image("menu.png");
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = game;
        this.background = new Image("backgroundMario.png");
       
        

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        int posX = Mouse.getX();
        int posY = (512-Mouse.getY());
        System.out.println("x:" + posX + "y:" + posY);

        background.draw(0, 0, gc.getWidth(), gc.getHeight());

        if ((posX > 146 && posX < 350) && (posY > 240 && posY < 253)) {
            if (Mouse.isInsideWindow()) {
                g.drawImage(menu, 113, 236);
                
                

            }

            if (Mouse.isButtonDown(0)) {
                sbg.enterState(2);
                app.setDisplayMode(640, 250, false);
                sounds.getItsMe().play();

            }
        }

        if ((posX > 146 && posX < 350) && (posY > 288 && posY < 302)) {
            if (Mouse.isInsideWindow()) {
                g.drawImage(menu, 113, 282);

            }
            if (Mouse.isButtonDown(0)) {

            }
        }

        if ((posX > 238 && posX < 270) && (posY > 336 && posY < 351)) {
            if (Mouse.isInsideWindow()) {
                g.drawImage(menu, 208, 330);

            }
            if (Mouse.isButtonDown(0)) {

            }
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        int posX = Mouse.getX();
        int posY = Mouse.getY();

    }

    

    public int getID() {
        return ID;
    }

}
