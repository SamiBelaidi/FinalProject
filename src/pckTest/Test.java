/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckTest;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author 1553450
 */
public class Test extends StateBasedGame {

    public static String gameName = "Smart Mario";
    public static int startMenu = 1;
    public static int windowGame = 2;
    private static AppGameContainer app;
    boolean focus = false;
    SoundFx sounds = new SoundFx();

    public static void main(String[] args) throws SlickException {
        try {
            app = new AppGameContainer(new Test(gameName));
            app.setDisplayMode(512, 512, false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public Test(String gameName) throws SlickException {
        super(gameName);
    }

    public void initStatesList(GameContainer gc) throws SlickException {
        WindowGame wg = new WindowGame(250, 640, windowGame, app, this);
        app.setShowFPS(false);
        this.addState(new Menu(startMenu, app, wg));
        this.addState(wg);
        this.enterState(startMenu);

    }
}
