/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Sami
 */
public class Map extends TiledMap {

    private int renderX = 0, renderY = 0;

    public Map(String ref) throws SlickException {
        super(ref);
    }

    public int getRenderX() {
        return renderX;
    }

    public int getRenderY() {
        return renderY;
    }

    public void avancer() {
        renderX -= 1;
    }
}
