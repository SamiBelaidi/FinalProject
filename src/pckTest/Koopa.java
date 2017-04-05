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
public class Koopa {

    /*
    animation 0 > marche droite
    animation1 > marche gauche
    animation 2 > broken immobile
    animation 3> broken mobile
     */
    private int x, y, vitesseX;
    private boolean goingRight;
    private Animation[] animations = new Animation[4];
    private boolean isBroken, isMoving;
    private SpriteSheet spriteSheetRight, spriteSheetLeft;

    public Koopa(int x, int y) throws SlickException {
        this.x = x;
        this.y = y;
        spriteSheetRight = new SpriteSheet("sprites/koopaRight.gif", 16, 16);
        spriteSheetLeft = new SpriteSheet("sprite/koopaLeft.gif", 16, 16);
        fillAnimations();
    }

    private void fillAnimations() {
        Animation animation = new Animation();
        animation.addFrame(spriteSheetRight.getSubImage(0, 5), 100);
        animation.addFrame(spriteSheetRight.getSubImage(0, 4), 100);
        animation.addFrame(spriteSheetRight.getSubImage(0, 3), 100);
        animation.addFrame(spriteSheetRight.getSubImage(0, 2), 100);
        animations[0] = animation;
        Animation animation2 = new Animation();
        animation.addFrame(spriteSheetLeft.getSubImage(0, 0), 100);
        animation.addFrame(spriteSheetLeft.getSubImage(0, 1), 100);
        animation.addFrame(spriteSheetLeft.getSubImage(0, 2), 100);
        animation.addFrame(spriteSheetLeft.getSubImage(0, 3), 100);
        animations[1] = animation2;
        Animation animation3 = new Animation();
        animation3.addFrame(spriteSheetRight.getSubImage(0, 0), 100);
        animations[2] = animation3;
        Animation animation4 = new Animation();
        animation4.addFrame(spriteSheetRight.getSubImage(0, 0), 100);
        animation4.addFrame(spriteSheetRight.getSubImage(0, 1), 100);
        animations[3] = animation4;
    }
}