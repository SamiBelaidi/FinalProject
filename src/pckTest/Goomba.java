package pckTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Sami
 */
public class Goomba {

    private int compteur;
    private int x, y, vitesseX;
    private Animation[] animations = new Animation[2];
    private boolean isDead = false;
    private SpriteSheet spriteSheet;
    private int xMax;
    private boolean ecrase;
    private int xMin;
    private FakeRectangle rectangle;
    private WindowGame wg;

    public Goomba(int x, int y, int xMax, WindowGame wg) throws SlickException {
        this.x = x;
        this.y = y;
        xMin = x;
        rectangle = new FakeRectangle(x, y, 16, 16);
        this.xMax = xMax;
        spriteSheet = new SpriteSheet("sprites/woomba.gif", 16, 16);
        fillAnimations();
    }

    public void fillAnimations() {
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSubImage(0, 0), 100);
        animation.addFrame(spriteSheet.getSubImage(1, 0), 100);
        animations[0] = animation;
        Animation animation2 = new Animation();
        animation2.addFrame(spriteSheet.getSubImage(2, 0), 100);
        animations[1] = animation2;
    }

    public void bouger() {
        if (x == xMin) {
            vitesseX = 1;
        } else if (x == xMax) {
            vitesseX = -1;
        }
        x = x + vitesseX;
        rectangle.setX(x);
    }

    public int getX() {
        return x;
    }

    public boolean isEcrase() {
        return ecrase;
    }

    public void setEcrase(boolean ecrase) {
        this.ecrase = ecrase;
    }

    public void setX(int x) {
        this.x = x;
        rectangle.setX(x);
    }

    public void setxMax(int xMax) {
        this.xMax = xMax;
    }

    public void setxMin(int xMin) {
        this.xMin = xMin;
    }

    public int getxMin() {
        return xMin;
    }

    public int getxMax() {
        return xMax;
    }

    public int getY() {
        return y;
    }

    public FakeRectangle getRectangle() {
        return rectangle;
    }

    public Animation getAnimation() {
        if (ecrase) {
            return animations[1];
        } else {
            return animations[0];
        }
    }
}
