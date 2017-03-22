/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckTest;

import java.io.File;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author 1553450
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SlickException {
        // TODO code application logic here
        int heigth=250, width=640;
        AppGameContainer app = new AppGameContainer(new WindowGame(heigth, width), width, heigth, false);
        app.start();
    }

}
