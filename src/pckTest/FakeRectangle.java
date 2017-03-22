/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckTest;

import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author 1553450
 */
public class FakeRectangle extends Rectangle {

    public FakeRectangle(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public java.awt.Rectangle getBounds() {
        return new java.awt.Rectangle((int) x, (int) y, (int) width, (int) height);
    }

}
