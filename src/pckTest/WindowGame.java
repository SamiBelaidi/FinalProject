/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckTest;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.System.gc;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Sami
 */
public class WindowGame extends BasicGameState {

    static int ID = 2;
    private int timer = 0;
    private float xOverLap, yOverLap;
    private boolean rectVisible = false;
    private FakeRectangle rectangle;
    private ArrayList<FakeRectangle> listeRectangles = new ArrayList();
    private ArrayList<FakeRectangle> listeRectanglesGround = new ArrayList();
    private ArrayList<FakeRectangle> listeRectanglesSurprise = new ArrayList();
    private ArrayList<FakeRectangle> listeRectanglesTube = new ArrayList();
    private ArrayList<FakeRectangle> listeRectanglesBloc = new ArrayList();
    private ArrayList<FakeRectangle> listeRectanglesFlag = new ArrayList();
    private ArrayList<Bougeable> listeChampignons = new ArrayList();
    private ArrayList<ArrayList<Bougeable>> listeObjets = new ArrayList();
    private int compteur = 0;
    int i = 0;
    private GameContainer container;
    private Map map;
    private Animation animation;
    private Mario mario;
    private Menu startMenu;
    private static int height, width;

    public WindowGame(int height, int width, int windowGame) throws SlickException {

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

    public int getTimer() {
        return timer;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<ArrayList<Bougeable>> getListeObjets() {
        return listeObjets;
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
                mario.setRectVisible(!mario.isRectVisible());
                break;
        }
    }

    public ArrayList<FakeRectangle> getListeRectangles() {
        return listeRectangles;
    }

    private void creerRectangleGround() throws SlickException {
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.getTileId(i, j, this.map.getLayerIndex("Ground")) == 49) {
                    FakeRectangle rectangle = new FakeRectangle(i * 16, j * 16, 16, 16);
                    listeRectanglesGround.add(rectangle);
                    listeRectangles.add(rectangle);
                }
            }
        }
    }

    private void creerRectangleSurprise() {
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.getTileId(i, j, this.map.getLayerIndex("Surprise")) == 253) {
                    FakeRectangle rectangle = new FakeRectangle(i * 16, j * 16, 16, 16);
                    listeRectanglesSurprise.add(rectangle);
                    listeRectangles.add(rectangle);
                }
            }
        }
    }

    private void creerRectangleTube() {
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                int temp = map.getTileId(i, j, this.map.getLayerIndex("Tuyaux"));
                if (temp == (42) || temp == (43) || temp == (61) || temp == (62)) {
                    FakeRectangle rectangle = new FakeRectangle(i * 16, j * 16, 16, 16);
                    listeRectanglesTube.add(rectangle);
                    listeRectangles.add(rectangle);
                }
            }
        }
    }

    private void creerRectangleBloc() {
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.getTileId(i, j, this.map.getLayerIndex("Bloc")) == 3) {
                    FakeRectangle rectangle = new FakeRectangle(i * 16, j * 16, 16, 16);
                    listeRectanglesBloc.add(rectangle);
                    listeRectangles.add(rectangle);
                }
            }
        }
    }

    private void creerRectangleFlag() {
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.getTileId(i, j, this.map.getLayerIndex("Bloc")) == 5) {
                    FakeRectangle rectangle = new FakeRectangle(i * 16, j * 16, 16, 16);
                    listeRectangles.add(rectangle);
                    listeRectanglesFlag.add(rectangle);
                }
            }
        }
    }

    private void creerRectangle() throws SlickException {
        creerRectangleSurprise();
        creerRectangleGround();
        creerRectangleTube();
    }

    private void dessinerRectangles(Graphics g) {
        if (rectVisible) {
            for (int i = 0; i < listeRectangles.size(); i++) {
                g.draw(listeRectangles.get(i));
            }
            g.draw(mario.getRectangle());
        }
    }

    private void spawnChampignon(int x, int y, int timer) {
        Champignon champignon = new Champignon(x, y - 16, timer, this);
        listeChampignons.add((Bougeable) champignon);
    }

    private void afficherObjets(Graphics g) {
        for (int i = 0; i < listeChampignons.size(); i++) {
            g.drawAnimation(listeChampignons.get(i).getAnimation(), listeChampignons.get(i).getX(), listeChampignons.get(i).getY());
        }
    }

    private void fillListObjets() {
        listeObjets.add(listeChampignons);
    }

    private void bouger(Graphics g) {
        mario.bouger(g);
        for (int i = 0; i < listeObjets.size(); i++) {
            for (int j = 0; j < listeObjets.get(i).size(); j++) {
                listeObjets.get(i).get(j).bouger();
            }
        }
    }

    private void gererCollisionObjets() {
        for (int z = 0; z < listeRectangles.size(); z++) {
            FakeRectangle x = listeRectangles.get(z);
            for (int i = 0; i < listeObjets.size(); i++) {
                for (int j = 0; j < listeObjets.get(i).size(); j++) {
                    if (listeObjets.get(i).get(j).getRectangle().getBounds().intersects(x.getX(), x.getY() - 2, x.getWidth(), x.getHeight()) && listeObjets.get(i).get(j).isBougeable()) {
                        float yOverLap = calculateIntersectsY(listeObjets.get(i).get(j).getRectangle().getX(), listeObjets.get(i).get(j).getRectangle().getX() + 16, listeObjets.get(i).get(j).getRectangle().getY(), listeObjets.get(i).get(j).getRectangle().getY() + listeObjets.get(i).get(j).getRectangle().getHeight(), x.getX(), x.getX() + x.getWidth(), x.getY(), x.getY() + x.getHeight());
                        if (yOverLap == 1) {
                            listeObjets.get(i).get(j).setY(listeObjets.get(i).get(j).getY() - 1);
                        }
                    }
                }
            }
        }
    }

    private void gererGravite() {
        mario.gravity();
        for (int i = 0; i < listeChampignons.size(); i++) {
            listeChampignons.get(i).gravity();
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

    private void gererCollisions(int y) throws SlickException {
        int i = y;
        FakeRectangle x = null;
        boolean collision1 = false;
        //while (j < listeRectangles.size()) {
        boolean collision = false;
        for (i = y; i < listeRectangles.size(); i++) {
            x = listeRectangles.get(i);
            if (mario.getRectangle().getBounds().intersects(x.getX(), x.getY() - 2, x.getWidth(), x.getHeight())) {
                collision1 = true;
                //collision = true;
                // j = i + 1;
                break;
            } else {
                //  collision1 = false;
            }

            //  }
        }
        if (collision1) {
            float xOverLap = calculateIntersectsX(mario.getRectangle().getX(), mario.getRectangle().getX() + 16, mario.getRectangle().getY(), mario.getRectangle().getY() + mario.getRectangle().getHeight(), x.getX(), x.getX() + x.getWidth(), x.getY(), x.getY() + x.getHeight());
            float yOverLap = calculateIntersectsY(mario.getRectangle().getX(), mario.getRectangle().getX() + 16, mario.getRectangle().getY(), mario.getRectangle().getY() + mario.getRectangle().getHeight(), x.getX(), x.getX() + x.getWidth(), x.getY(), x.getY() + x.getHeight());
            if (yOverLap < 6) {
                if (yOverLap == 1) {
                    mario.setY(mario.getY() - 1);
                }
                if (mario.getRectangle().getY() > x.getY()) {
                    if (listeRectanglesSurprise.contains(x) && mario.isConditionThread()) {
                        spawnChampignon((int) x.getX(), (int) x.getY(), timer);
                        listeRectanglesSurprise.remove(x);
                    }
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

    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.container = container;
        map = new Map("map\\map.tmx", this);
        this.mario = new Mario(true, map, this);
        creerRectangle();
        fillListObjets();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        this.map.render(map.getRenderX(), map.getRenderY());
        g.drawAnimation(mario.getAnimation(), mario.getX(), mario.getY());
        dessinerRectangles(g);
        bouger(g);
        timer++;
        afficherObjets(g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        mario.updateAnimation();
        gererGravite();
        gererCollisions(0);
        gererCollisionObjets();
    }
}
