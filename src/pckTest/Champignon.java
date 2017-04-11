/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckTest;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1553450
 */
public class Champignon implements Bougeable {

    private int x, y, vitesseX;
    private boolean goingRight = true, bougeable, aTerre = false;
    private SpriteSheet spriteSheet;
    private Animation[] animations = new Animation[2];
    private int timeSpawn;
    private WindowGame wg;
    private FakeRectangle rectangle;

    public Champignon(int x, int y, int timeSpawn, WindowGame wg) {
        rectangle = new FakeRectangle(x, y, 16, 16);
        this.x = x;
        this.y = y;
        this.wg = wg;
        try {
            spriteSheet = new SpriteSheet("sprites/champignon.gif", 16, 16);
        } catch (SlickException e) {
        }
        this.timeSpawn = timeSpawn;
        fillAnimations();

    }

    public Animation getAnimation() {
        if ((wg.getTimer() - timeSpawn) <= 90) {
            return animations[0];
        } else {
            bougeable = true;
            return animations[1];
        }
    }

    public boolean isGoingRight() {
        return goingRight;
    }

    public void setATerre(boolean aTerre) {
        this.aTerre = aTerre;
    }

    public void bouger() {

        if (bougeable && aTerre) {
            if (isGoingRight()) {
                vitesseX = 1;
            } else {
                vitesseX = -1;
            }
        }
        x = x + vitesseX;
        rectangle.setX(x);
    }

    public void changerDirection() {
        goingRight = !goingRight;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void gravity() {
        if (!aTerre && bougeable) {
            setY(y + 1);
        }
    }

    public boolean isBougeable() {
        return bougeable;
    }

    public void setY(int y) {
        this.y = y;
        rectangle.setY(y);
    }

    public void setX(int x) {
        this.x = x;
        rectangle.setX(x);
    }

    private void fillAnimations() {
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSubImage(1, 0), 500);
        animation.addFrame(spriteSheet.getSubImage(2, 0), 500);
        animations[0] = animation;
        Animation animation2 = new Animation();
        animation2.addFrame(spriteSheet.getSubImage(0, 0), 100);
        animations[1] = animation2;

    }

    @Override
    public FakeRectangle getRectangle() {
        return rectangle;
    }

}
