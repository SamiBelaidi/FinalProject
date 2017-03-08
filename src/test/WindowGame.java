/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import test.Mario.State;

/**
 *
 * @author Sami
 */
public class WindowGame extends BasicGame {

            
    private int compteur = 0;
    int i = 0;
    private GameContainer container;
    private Map map;
    private Animation animation;
    private Mario mario;
    private int height, width;

    public WindowGame(int height, int width) throws SlickException {
        super("Maxime Meloche");
        this.height = height;
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        map = new Map("map\\map.tmx");
        this.mario = new Mario(true, map, this);
        animation = new Animation();

    }

    @Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
        switch (key) {
            case Input.KEY_RIGHT:
                if (mario.isGoingRight()) {
                    mario.setMoving(false);

                }
                break;
            case Input.KEY_LEFT:
                if (!mario.isGoingRight()) {

                    mario.setMoving(false);

                }
                break;
            case Input.KEY_UP:
                mario.setConditionThread(false);
        }
        mario.updateAnimation();
    }

    @Override
    public void keyPressed(int key, char c
    ) {
        switch (key) {
            case Input.KEY_LEFT:
                mario.setGoingRight(false);
                mario.setMoving(true);

                break;
            case Input.KEY_RIGHT:
                mario.setGoingRight(true);
                mario.setMoving(true);
                break;
            case Input.KEY_DOWN:
                mario.setY(mario.getY() + 3);
                break;
            case Input.KEY_UP:
                if (mario.isaTerre()) {
                    mario.jump();
                    mario.setState(Mario.State.JUMP);
                }
                break;
        }

    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        this.map.render(map.getRenderX(), map.getRenderY());
        g.drawAnimation(mario.getAnimation(), mario.getX(), mario.getY());
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
        }
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {

        int x = (int) (mario.getX()) / this.map.getTileWidth();
        int y = (int) (mario.getY()) / this.map.getTileHeight();
        Image tile = this.map.getTileImage(x, y, this.map.getLayerIndex("Surprise"));
        boolean collision1 = tile != null;

        x = (int) (mario.getX() + 16) / this.map.getTileWidth();
        y = (int) (mario.getY()) / this.map.getTileHeight();
        tile = this.map.getTileImage(x, y, this.map.getLayerIndex("Surprise"));
        boolean collision2 = tile != null;

        x = (int) (mario.getX() + 16) / this.map.getTileWidth();
        y = (int) (mario.getY() + 16) / this.map.getTileHeight();
        tile = this.map.getTileImage(x, y, this.map.getLayerIndex("Surprise"));
        boolean collision3 = tile != null;

        x = (int) (mario.getX()) / this.map.getTileWidth();
        y = (int) (mario.getY() + 16) / this.map.getTileHeight();
        tile = this.map.getTileImage(x, y, this.map.getLayerIndex("Surprise"));
        boolean collision4 = tile != null;

        if (collision1 || collision2 || collision3 || collision4) {
            mario.setMoving(false);
        } else {
            mario.bouger();
        }
        mario.updateAnimation();
        mario.gravity();
        gererCollisionGround();
    }

    private void gererCollisionGround() {

        int x = (int) (mario.getX()) / this.map.getTileWidth();
        int y = (int) (mario.getY() + 16) / this.map.getTileHeight();
        Image tile = this.map.getTileImage(x, y, this.map.getLayerIndex("Ground"));
        boolean collision1 = tile != null;

        x = (int) (mario.getX() + 16) / this.map.getTileWidth();
        y = (int) (mario.getY() + 16) / this.map.getTileHeight();
        tile = this.map.getTileImage(x, y, this.map.getLayerIndex("Ground"));
        boolean collision2 = tile != null;

        if (collision1 || collision2) {
            mario.setaTerre(true);
            if (compteur == 0) {
                mario.setState(Mario.State.GROUND);
            }
            compteur++;
        } else {
            compteur = 0;
            mario.setaTerre(false);
        }
    }
}
