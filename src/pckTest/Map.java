/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckTest;

import java.util.ArrayList;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Sami
 */
public class Map extends TiledMap {

    private int renderX = 0, renderY = 0;
    private WindowGame bg;

    public Map(String ref, WindowGame bg) throws SlickException {
        super(ref);
        this.bg = bg;
    }

    public int getRenderX() {
        return renderX;
    }

    public int getRenderY() {
        return renderY;
    }

    public void avancer() {
        renderX -= 1;
        for (int i = 0; i < bg.getListeObjets().size(); i++) {
            for (int j = 0; j < bg.getListeObjets().get(i).size(); j++) {

            }
        }
    }
}
