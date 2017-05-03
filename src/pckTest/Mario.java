/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckTest;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Sami
 */
public class Mario {

    private int x = 5, y = 100, vitesseX = 3, vitesseY = 0;
    private FakeRectangle rectangle;
    private boolean goingRight, big;
    private boolean moving = false;
    private Animation[] animations = new Animation[21];
    private final SpriteSheet[] sprites = new SpriteSheet[4];
    private Animation animation;
    private int positionAnimation;
    private Map map;
    private WindowGame window;
    private boolean conditionThread, aTerre = false;
    private boolean rectVisible;
    private SoundFx sounds = new SoundFx();

    public enum State {
        CROUCH,
        JUMP,
        DEAD,
        GROUND;
    }
    private State state;

    public Mario(boolean goingRight, Map map, WindowGame window) throws SlickException {
        state = State.GROUND;
        this.goingRight = goingRight;
        fillSprites();
        setAnimations();
        setAnimation(animations[1]);
        positionAnimation = 1;
        this.map = map;
        this.window = window;
        if (isBig()) {
            rectangle = new FakeRectangle(x, y, 16, 32);
        } else {
            rectangle = new FakeRectangle(x, y, 16, 16);
        }
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public Animation getAnimation() {
        return animation;
    }

    private void setAnimations() {
        for (int i = 0; i < sprites.length; i++) {
            Animation animation = new Animation();
            animations[i] = creerAnimationDebout(sprites[i]);
            animations[i + 4] = creerAnimationMarche(sprites[i]);
            animations[i + 8] = creerAnimationSaut(sprites[i]);
            animations[i + 12] = creerAnimationChangementDeDirection(sprites[i]);
            animations[i + 16] = creerAnimationCrouchMort(sprites[i]);

        }
        animations[20] = new Animation();
        animations[20].addFrame(sprites[1].getSubImage(6, 0), 100);
    }

    private Animation creerAnimationDebout(SpriteSheet sprite) {
        Animation animation = new Animation();
        animation.addFrame(sprite.getSubImage(0, 0), 100);
        return animation;
    }

    private Animation creerAnimationMarche(SpriteSheet sprite) {
        Animation animation = new Animation();

        animation.addFrame(sprite.getSubImage(1, 0), 100);
        animation.addFrame(sprite.getSubImage(2, 0), 100);
        animation.addFrame(sprite.getSubImage(3, 0), 100);
        return animation;
    }

    private Animation creerAnimationSaut(SpriteSheet sprite) {
        Animation animation = new Animation();
        animation.addFrame(sprite.getSubImage(4, 0), 100);
        return animation;
    }

    private Animation creerAnimationChangementDeDirection(SpriteSheet sprite) {
        Animation animation = new Animation();
        return animation;
    }

    private Animation creerAnimationCrouchMort(SpriteSheet sprite) {
        Animation animation = new Animation();
        animation.addFrame(sprite.getSubImage(6, 0), 100);
        return animation;
    }

    public boolean isGoingRight() {
        return goingRight;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }

    public boolean isBig() {
        return big;
    }

    public void setBig(boolean big) {
        this.big = big;
        if (isBig()) {
            rectangle = new FakeRectangle(x, y - 2, 16, 32);
        } else {
            rectangle = new FakeRectangle(x, y - 2, 16, 16);
        }
    }

    public boolean isRectVisible() {
        return rectVisible;
    }

    public void setRectVisible(boolean rectVisible) {
        this.rectVisible = rectVisible;
    }

    public FakeRectangle getRectangle() {
        return rectangle;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setConditionThread(boolean conditionThread) {
        this.conditionThread = conditionThread;

    }

    public void AIjump(float hauteurObstacle) {
        conditionThread = true;
       
        if (isBig()) {
            sounds.getJumpSuper().play();
        } else {
            sounds.getJumpSmall().play();
        }

        Thread thread = new Thread(() -> {
            int i = 0;
            while (conditionThread) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                }

                setY(y - 2);
                i++;

                if (getY() < (hauteurObstacle - 64)) {
                    //do nothing   
                    setConditionThread(false);
                    setMoving(true);
                    break;
                }

            }
        });
        thread.start();
    }

    public void jump(int hauteurJump) {
        conditionThread = true;

        if (isBig()) {
            sounds.getJumpSuper().play();
        } else {
            sounds.getJumpSmall().play();
        }

        Thread thread = new Thread(() -> {
            int i = 0;
            while (conditionThread && i < hauteurJump) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                }

                setY(y - 2);
                i++;
            }

        });
        thread.start();

    }

    public void jumpMonstre() {
        if (isBig()) {
            sounds.getJumpSuper().play();
        } else {
            sounds.getJumpSmall().play();
        }
        Thread thread = new Thread(() -> {
            boolean movingBMonster = isMoving() == false;
            int i = 0;
            setState(State.JUMP);
            window.setTouchesActives(false);
            while (i < 30) {

                if (goingRight && !movingBMonster) {
                    setX(x + 1);
                } else if (!movingBMonster) {
                    setX(x - 1);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                }
                setY(y - 2);
                i++;
            }
            window.setTouchesActives(true);
        });
        thread.start();
    }

    public int calculerVitesseX() {
        if (isMoving()) {
            if (isGoingRight()) {
                vitesseX = 16;
            } else {
                vitesseX = -16;
            }
        }
        return vitesseX;
    }

    public void setVitesseY(int vitesseY) {
        this.vitesseY = vitesseY;
    }

    public boolean isConditionThread() {
        return conditionThread;
    }

    public void bouger(Graphics g) {
        if (isMoving()) {
            if (isGoingRight()) {
                if (x < (window.getWidth() / 3)) {
                    vitesseX = 1;
                } else if (Math.abs(map.getRenderX()) <= (map.getWidth() * 16 - 720)) {
                    vitesseX = 0;
                    map.avancer();
                    for (int i = 0; i < window.getListeRectangles().size(); i++) {
                        window.getListeRectangles().get(i).setX(window.getListeRectangles().get(i).getX() - 1);
                    }
                } else {
                    vitesseX = 1;
                }
            } else {
                vitesseX = -1;
            }
            if ((x + vitesseX) > 0) {
                x = x + vitesseX;
            }
        }
        rectangle.setX(x);
        rectangle.setY(y);

    }

    public void grandir() {
        big = true;
        setY(y - 16);
        rectangle.setHeight(32);

    }

    public void rapetisser() {
        big = false;
        rectangle.setHeight(16);
    }

    public int getVitesseY() {
        return 16;
    }

    public int getVitesseX() {
        return 16;
    }

    public State getState() {
        return state;
    }

    public void updateAnimation() {
        if (state == State.DEAD) {
            animation = animations[20];
        } else {
            if (isBig()) {
                if (isGoingRight()) {
                    switch (state) {
                        case JUMP:
                            animation = animations[8];
                            break;
                        case CROUCH:
                            animation = animations[16];
                            break;
                        case GROUND:
                            if (isMoving()) {
                                animation = animations[4];
                            } else {
                                animation = animations[0];
                            }
                            break;
                    }
                } else {
                    switch (state) {
                        case JUMP:
                            animation = animations[10];
                            break;
                        case CROUCH:
                            animation = animations[18];
                            break;
                        case GROUND:
                            if (isMoving()) {
                                animation = animations[6];
                            } else {
                                animation = animations[2];
                            }
                            break;
                    }
                }
            } else {
                if (isGoingRight()) {
                    switch (state) {
                        case JUMP:
                            animation = animations[9];
                            break;
                        case CROUCH:
                            animation = animations[1];
                            break;
                        case GROUND:
                            if (isMoving()) {
                                animation = animations[5];
                            } else {
                                animation = animations[1];
                            }
                            break;
                    }
                } else {
                    switch (state) {
                        case JUMP:
                            animation = animations[11];
                            break;
                        case CROUCH:
                            animation = animations[3];
                            break;
                        case GROUND:
                            if (isMoving()) {
                                animation = animations[7];
                            } else {
                                animation = animations[3];
                            }
                            break;
                    }
                }
            }
        }
    }

    private void fillSprites() throws SlickException {
        sprites[0] = new SpriteSheet("sprites/mario_big_right.gif", 16, 32);
        sprites[1] = new SpriteSheet("sprites/mario_mini_right.gif", 16, 16);
        sprites[2] = new SpriteSheet("sprites/mario_big_left.gif", 16, 32);
        sprites[3] = new SpriteSheet("sprites/mario_mini_left.gif", 16, 16);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void gravity() {
        if (!aTerre) {
            setY(y + 1);
        }
    }

    public boolean isaTerre() {
        return aTerre;
    }

    public void setaTerre(boolean aTerre) {
        this.aTerre = aTerre;
    }

}
