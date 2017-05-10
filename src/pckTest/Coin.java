/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckTest;

import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Alex
 */
public class Coin {

    private Animation animation = new Animation();
    private FakeRectangle rectangle;
    private int x, y;

    JFrame f = new JFrame("Animation");

    public Coin(int x, int y) throws SlickException {
        rectangle = new FakeRectangle(x * 16, y * 16, 16, 16);
        x = x * 16;
        y = y * 16;
        initialiserAnimation();
    }

    public int getY() {
        return y;
    }

    private void initialiserAnimation() throws SlickException {
        animation.addFrame(new Image("Images/coin0.gif"), 100);
        animation.addFrame(new Image("Images/coin1.gif"), 100);
        animation.addFrame(new Image("Images/coin2.gif"), 100);
        animation.addFrame(new Image("Images/coin3.gif"), 100);
    }

    public Animation getAnimation() {
        return animation;
    }

    public int getX() {
        return x;
    }

}
