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
 * @author Sami
 */
public class woomba {

    private int x, y, vitesseX;
    private boolean goingRight;
    private Animation[] animations = new Animation[2];
    private boolean isDead = false;
    private SpriteSheet spriteSheet;

    public woomba(int x, int y) throws SlickException {
        this.x = x;
        this.y = y;
        spriteSheet = new SpriteSheet("sprites/woomba.gif", 16, 16);
        fillAnimations();
    }

    public void fillAnimations() {
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSubImage(0, 0), 100);
        animation.addFrame(spriteSheet.getSubImage(1, 0), 100);
        animations[0] = animation;
        Animation animation2 = new Animation();
        animation2.addFrame(spriteSheet.getSubImage(3, 0), 100);
        animations[1] = animation2;
    }
}
