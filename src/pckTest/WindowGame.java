/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckTest;

import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Sami
 */
public class WindowGame extends BasicGame {

    boolean creerRectangles;
    private float xOverLap, yOverLap;
    private boolean rectVisible = false;
    private FakeRectangle rectangle;
    private ArrayList<FakeRectangle> listeRectangles = new ArrayList();
    private ArrayList<FakeRectangle> listeRectanglesGround = new ArrayList();
    private ArrayList<FakeRectangle> listeRectanglesSurprise = new ArrayList();
    private ArrayList<FakeRectangle> listeRectanglesTube = new ArrayList();
    private ArrayList<FakeRectangle> listeRectanglesBloc = new ArrayList();
    private ArrayList<FakeRectangle> listeRectanglesFlag = new ArrayList();

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

    /* private void fillListeRectangles() {
        listeRectangles.add(listeRectanglesBloc);
        listeRectangles.add(listeRectanglesFlag);
        listeRectangles.add(listeRectanglesSurprise);
        listeRectangles.add(listeRectanglesGround);

        listeRectangles.add(listeRectanglesTube);
    }*/
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        map = new Map("map\\map.tmx", this);
        this.mario = new Mario(true, map, this);
        animation = new Animation();
        //fillListeRectangles();

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
    public void keyPressed(int key, char c) {
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

                break;
            case Input.KEY_UP:
                if (mario.isaTerre()) {
                    mario.jump();
                    mario.setState(Mario.State.JUMP);
                }
                break;
            case Input.KEY_V:
                rectVisible = !rectVisible;
                creerRectangles = false;
                mario.setRectVisible(!mario.isRectVisible());
                break;
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {

        this.map.render(map.getRenderX(), map.getRenderY());
        g.drawAnimation(mario.getAnimation(), mario.getX(), mario.getY());

        // if (creerRectangles == false) {
        creerRectangleSurprise(g);
        creerRectangleGround(g);
        drawMarioRect(g);
        creerRectangleTube(g);
        //   creerRectangles = true;
        // }
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        mario.bouger();
        mario.updateAnimation();
        mario.gravity();
        gererCollisionGround();
    }

    public ArrayList<FakeRectangle> getListeRectangles() {
        return listeRectangles;
    }

    private void drawMarioRect(Graphics g) {
        if (mario.isRectVisible()) {
            g.setColor(Color.yellow);
        } else {
            g.setColor(Color.transparent);
        }
        g.draw(mario.getRectangle());
    }

    private void creerRectangleGround(Graphics g) throws SlickException {
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.getTileId(i, j, this.map.getLayerIndex("Ground")) == 49) {
                    FakeRectangle rectangle = new FakeRectangle(i * 16, j * 16, 16, 16);
                    listeRectanglesGround.add(rectangle);
                    listeRectangles.add(rectangle);
                    if (rectVisible) {
                        g.setColor(Color.yellow);
                    } else {
                        g.setColor(Color.transparent);
                    }
                    g.draw(rectangle);
                }
            }
        }
    }

    private void creerRectangleSurprise(Graphics g) {
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.getTileId(i, j, this.map.getLayerIndex("Surprise")) == 253) {
                    FakeRectangle rectangle = new FakeRectangle(i * 16, j * 16, 16, 16);
                    listeRectanglesSurprise.add(rectangle);
                    listeRectangles.add(rectangle);
                    if (rectVisible) {
                        g.setColor(Color.yellow);
                    } else {
                        g.setColor(Color.transparent);
                    }
                    g.draw(rectangle);

                }
            }
        }
    }

    private void creerRectangleTube(Graphics g) {
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                int temp = map.getTileId(i, j, this.map.getLayerIndex("Tuyaux"));
                if (temp == (42) || temp == (43) || temp == (61) || temp == (62)) {
                    FakeRectangle rectangle = new FakeRectangle(i * 16, j * 16, 16, 16);
                    listeRectanglesTube.add(rectangle);
                    listeRectangles.add(rectangle);
                    if (rectVisible) {
                        g.setColor(Color.yellow);
                    } else {
                        g.setColor(Color.transparent);
                    }
                    g.draw(rectangle);

                }
            }
        }
    }

    private void creerRectangleBloc(Graphics g) {
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.getTileId(i, j, this.map.getLayerIndex("Bloc")) == 3) {
                    FakeRectangle rectangle = new FakeRectangle(i * 16, j * 16, 16, 16);
                    listeRectanglesBloc.add(rectangle);
                    listeRectangles.add(rectangle);
                    if (rectVisible) {
                        g.setColor(Color.yellow);
                    } else {
                        g.setColor(Color.transparent);
                    }
                    g.draw(rectangle);

                }
            }
        }
    }

    private void creerRectangleFlag(Graphics g) {
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.getTileId(i, j, this.map.getLayerIndex("Bloc")) == 5) {
                    FakeRectangle rectangle = new FakeRectangle(i * 16, j * 16, 16, 16);
                    listeRectangles.add(rectangle);
                    listeRectanglesFlag.add(rectangle);
                    if (rectVisible) {
                        g.setColor(Color.yellow);
                    } else {
                        g.setColor(Color.transparent);
                    }
                    g.draw(rectangle);

                }
            }
        }
    }

    private float calculateIntersectsX(float xa1, float xa2, float ya1, float ya2, float xb1, float xb2, float yb1, float yb2) {
        xOverLap = Math.max(0, Math.min(xa2, xb2) - Math.max(xa1, xb1));
        return yOverLap;
    }

    private float calculateIntersectsY(float xa1, float xa2, float ya1, float ya2, float xb1, float xb2, float yb1, float yb2) {
        yOverLap = Math.max(0, Math.min(ya2, yb2) - Math.max(ya1, yb1));
        return yOverLap;
    }

    private void gererCollisionGround() throws SlickException {
        /*     float yTile, xTile;
        int x1 = (int) (mario.getX()) / this.map.getTileWidth();
        int y1 = (int) (mario.getY() + 16) / this.map.getTileHeight();
        Image tile1 = this.map.getTileImage(x1, y1, this.map.getLayerIndex("Ground"));
        boolean collision1 = tile1 != null;

        int x2 = (int) (mario.getX() + 16) / this.map.getTileWidth();
        int y2 = (int) (mario.getY() + 16) / this.map.getTileHeight();
        Image tile2 = this.map.getTileImage(x2, y2, this.map.getLayerIndex("Ground"));
        boolean collision2 = tile2 != null;

        int x3 = (int) (mario.getX()) / this.map.getTileWidth();
        int y3 = (int) (mario.getY() + 16) / this.map.getTileHeight();
        Image tile3 = this.map.getTileImage(x3, y3, this.map.getLayerIndex("Ground"));
        boolean collision3 = tile3 != null;

        int x4 = (int) (mario.getX()) / this.map.getTileWidth();
        int y4 = (int) (mario.getY()) / this.map.getTileHeight();
        Image tile4 = this.map.getTileImage(x4, y4, this.map.getLayerIndex("Ground"));
        boolean collision4 = tile4 != null;

        if (collision1 || collision2 || collision3 || collision4) {

            if (collision1) {
                yTile = y1 * 16;
                xTile = x1 * 16;
            } else if (collision2) {
                yTile = y2 * 16;
                xTile = x2 * 16;
            } else if (collision3) {
                yTile = y3 * 16;
                xTile = x3 * 16;
            } else {
                yTile = y4 * 16;
                xTile = x4 * 16;
            }
            if (mario.getY() >= (int) yTile) {
                mario.setaTerre(true);
                if (compteur == 0) {
                    mario.setState(Mario.State.GROUND);
                }
                compteur++;
            } else if (mario.getY() < (int) yTile) {
                mario.setConditionThread(false);
            }

        } else {
            compteur = 0;
            mario.setaTerre(false);
        }
    }*/

        FakeRectangle x = null;
        boolean collision1 = false;

        for (int i = 0; i < listeRectangles.size(); i++) {
            x = listeRectangles.get(i);

            if (mario.getRectangle().getBounds().intersects(x.getX(), x.getY() - 2, x.getWidth(), x.getHeight())) {
                collision1 = true;
                break;
            }
        }

        if (collision1) {
            float xOverLap = calculateIntersectsX(mario.getRectangle().getX(), mario.getRectangle().getX() + 16, mario.getRectangle().getY(), mario.getRectangle().getY() + mario.getRectangle().getHeight(), x.getX(), x.getX() + x.getWidth(), x.getY(), x.getY() + x.getHeight());
            float yOverLap = calculateIntersectsY(mario.getRectangle().getX(), mario.getRectangle().getX() + 16, mario.getRectangle().getY(), mario.getRectangle().getY() + mario.getRectangle().getHeight(), x.getX(), x.getX() + x.getWidth(), x.getY(), x.getY() + x.getHeight());
            if (yOverLap < 6) {
                if (yOverLap == 1) {
                    mario.setY(mario.getY() - 1);
                }
                if (mario.getRectangle().getY() > x.getY()) {
                    mario.setConditionThread(false);
                } else if (yOverLap == 0) {
                    mario.setaTerre(true);
                    if (compteur == 0) {
                        mario.setState(Mario.State.GROUND);
                    }
                    compteur++;
                }
            } else if (xOverLap == 0) {
                mario.setMoving(false);

            }
        } else {
            compteur = 0;
            mario.setaTerre(false);
        }
    }
}
