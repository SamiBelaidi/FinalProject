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
public class Champignon {

    private int x, y, vitesseX;
    private boolean isGroing;
    private SpriteSheet spriteSheet;
    private Animation[] animations = new Animation[2];
    private int timeSpawn;

    public Champignon(int x, int y, int timeSpawn) {
        this.x = x;
        this.y = y;
        try {
            spriteSheet = new SpriteSheet("sprites/champignon.png", 16, 16);
        } catch (SlickException e) {
        }
        this.timeSpawn = timeSpawn;
    }

    private Animation getAnimation() {
     //   if()
        return animations[0];
    }

    private void fillAnimations() {
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSubImage(0, 1), 500);
        animation.addFrame(spriteSheet.getSubImage(0, 2), 500);
        animations[0] = animation;
        Animation animation2 = new Animation();
        animation2.addFrame(spriteSheet.getSubImage(0, 0), 100);
        animations[1] = animation2;

    }

}
