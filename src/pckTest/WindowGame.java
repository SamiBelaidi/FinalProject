/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckTest;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
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

    Graphics g;
    private static int ID = 2;
    private int timer = 0;
    private float xOverLap, yOverLap;
    private boolean rectVisible = false, focus, gameFini = false, conditionFin = true, touchesActives = true, AI, doMove = true, doJump = true;
    private ArrayList<FakeRectangle> listeRectangles = new ArrayList();
    private ArrayList<FakeRectangle> listeRectanglesGround = new ArrayList();
    private ArrayList<FakeRectangle> listeRectanglesSurprise = new ArrayList();
    private ArrayList<FakeRectangle> listeRectanglesTube = new ArrayList();
    private ArrayList<FakeRectangle> listeRectanglesBloc = new ArrayList();
    private ArrayList<FakeRectangle> listeRectanglesFlag = new ArrayList();
    private ArrayList<Bougeable> listeChampignons = new ArrayList();
    private ArrayList<ArrayList<Bougeable>> listeObjets = new ArrayList();
    private ArrayList<Goomba> listeGoombas = new ArrayList();
    private ArrayList<FakeRectangle> listeRectanglesObstacles = new ArrayList();
    private Image flag = new Image("Images/flag.png");
    private int compteur = 0;
    private float hauteurObstacle;
    private GameContainer container;
    private Map map;
    private Animation animation;
    private Mario mario;
    private Menu startMenu;
    private static int height, width;
    private SoundFx sounds = new SoundFx();
    private AppGameContainer app;
    private StateBasedGame state;

    public WindowGame(int height, int width, int windowGame, AppGameContainer app, StateBasedGame state) throws SlickException {
        this.app = app;
        this.height = height;
        this.width = width;
        this.state = state;
    }

    public void setAI(boolean AI) {
        this.AI = AI;
    }

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

    public ArrayList<Goomba> getListeWombas() {
        return listeGoombas;
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

    public void setTouchesActives(boolean touchesActives) {
        this.touchesActives = touchesActives;
    }

    @Override
    public void keyPressed(int key, char c) {
        if (!gameFini && touchesActives && !AI) {
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
                        mario.jump(70);
                        mario.setState(Mario.State.JUMP);
                    }
                    break;
                case Input.KEY_V:
                    rectVisible = !rectVisible;
                    mario.setRectVisible(!mario.isRectVisible());
                    break;
            }
        }
    }

    private void fenetreReduite() {

        AWTEventMonitor.addWindowListener(new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent e) {
                super.windowIconified(e);
                sounds.getBye().play();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

            }
        }
        );

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
                if (map.getTileId(i, j, this.map.getLayerIndex("Block")) == 3) {
                    FakeRectangle rectangle = new FakeRectangle(i * 16, j * 16, 16, 16);
                    listeRectanglesSurprise.add(rectangle);
                    listeRectangles.add(rectangle);
                }
            }
        }
    }

    private void creerRectangleFlag() {
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.getTileId(i, j, this.map.getLayerIndex("Flag")) == 5) {
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
        creerRectangleBloc();
        creerRectangleFlag();
    }

    private void dessinerRectangles(Graphics g) {
        if (rectVisible) {
            for (int i = 0; i < listeRectangles.size(); i++) {
                g.draw(listeRectangles.get(i));
            }
            g.draw(mario.getRectangle());
            for (int i = 0; i < listeObjets.size(); i++) {
                for (int j = 0; j < listeObjets.get(i).size(); j++) {
                    g.draw(listeObjets.get(i).get(j).getRectangle());
                }
            }
            for (int i = 0; i < listeGoombas.size(); i++) {
                g.draw(listeGoombas.get(i).getRectangle());
            }
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
        for (int i = 0; i < listeGoombas.size(); i++) {
            g.drawAnimation(listeGoombas.get(i).getAnimation(), listeGoombas.get(i).getX(), listeGoombas.get(i).getY());
        }
        for (int i = 0; i < map.getListeCoins().size(); i++) {
            g.drawAnimation(map.getListeCoins().get(i).getAnimation(), map.getListeCoins().get(i).getX(), map.getListeCoins().get(i).getY());
        }

    }

    private void fillListObjets() {
        listeObjets.add(listeChampignons);
    }

    private void bouger(Graphics g) {
        mario.bouger(g);
        for (int i = 0; i < listeGoombas.size(); i++) {
            listeGoombas.get(i).bouger();
        }
        /*  for (int i = 0; i < listeObjets.size(); i++) {
            for (int j = 0; j < listeObjets.get(i).size(); j++) {
                listeObjets.get(i).get(j).bouger();
            }
        }*/
    }

    private void gererCollisionObjets() {

        for (int i = 0; i < listeObjets.size(); i++) {
            for (int j = 0; j < listeObjets.get(i).size(); j++) {
                if (listeObjets.get(i).get(j).getRectangle().getBounds().intersects(mario.getRectangle().getBounds())) {
                    mario.grandir();
                    listeObjets.get(i).remove(j);
                }
            }
        }
        for (int i = 0; i < listeGoombas.size(); i++) {
            if (listeGoombas.get(i).getRectangle().getBounds().intersects(mario.getRectangle().getBounds()) && !listeGoombas.get(i).isEcrase()) {
                if ((mario.getY() + 14) == listeGoombas.get(i).getY()) {
                    mario.jumpMonstre();
                } else {
                    if (mario.isBig()) {
                        mario.setBig(false);
                    } else {
                        if (conditionFin) {
                            mario.setState(Mario.State.DEAD);
                            mario.jump(50);
                            //JOptionPane.showMessageDialog(null, "FF20");
                            gameFini = true;
                            conditionFin = false;
                            mario.setaTerre(false);
                        }
                    }
                }
                listeGoombas.get(i).setEcrase(true);
            }
        }
      //  for(int i=0;i<listeObjets)
    }

    private void gererGravite() {
        mario.gravity();

    }

    private float calculateIntersectsX(float xa1, float xa2, float ya1, float ya2, float xb1, float xb2, float yb1, float yb2) {
        xOverLap = Math.max(0, Math.min(xa2, xb2) - Math.max(xa1, xb1));
        return yOverLap;
    }

    private void creerWoombas() throws SlickException {
        Goomba Goomba = new Goomba(448, 208, 544, this);
        //      listeGoombas.add(Goomba);

    }

    private void intelligenceArtificielle() {
        boolean continu = false;

        doJump = true;
        if (doMove) {
            mario.setGoingRight(true);
            mario.setMoving(true);
            doMove = false;
        }
        for (int i = 0; i < listeRectangles.size(); i++) {

            if (mario.getX() % 16 == 0 && mario.isaTerre()) {
                if (listeRectangles.get(i).getX() - mario.getX() == 16 && listeRectangles.get(i).getY() - mario.getY() == 16) {
                    // doMove = true;
                    break;
                }
            }

        }
        if (continu) {
            for (int i = 0; i < listeRectangles.size(); i++) {
                if (listeRectangles.get(i).getY() - mario.getY() < mario.getRectangle().getHeight()
                        && listeRectangles.get(i).getY() - mario.getY() >= 0
                        && (listeRectangles.get(i).getX() - mario.getX() == 17 || listeRectangles.get(i).getX() - mario.getX() == 16)
                        && mario.getState() != Mario.State.JUMP
                        && doJump) {
                    hauteurObstacle = listeRectangles.get(i).getY();
                    for (int j = 0; j < listeRectangles.size(); j++) {
                        if (listeRectangles.get(j).getY() == hauteurObstacle - 16
                                && listeRectangles.get(j).getX() == listeRectangles.get(i).getX()) {
                            listeRectanglesObstacles.add(listeRectangles.get(j));
                            hauteurObstacle = listeRectangles.get(j).getY();
                        }
                    }

                    listeRectanglesObstacles.add(listeRectangles.get(i));
                    for (int x = 0; x < listeRectanglesObstacles.size(); x++) {
                        g.setColor(Color.red);
                        g.draw(listeRectanglesObstacles.get(x));
                    }
                    doMove = false;
                    mario.AIjump(hauteurObstacle);
                    mario.setState(Mario.State.JUMP);
                    doJump = false;
                    break;
                }
            }
        }

    }

    private float calculateIntersectsY(float xa1, float xa2, float ya1, float ya2, float xb1, float xb2, float yb1, float yb2) {
        yOverLap = Math.max(0, Math.min(ya2, yb2) - Math.max(ya1, yb1));
        return yOverLap;
    }

    private void gererCollisions(Graphics g) throws SlickException {
        FakeRectangle x = null, y = null;
        int i;
        boolean collision1 = false;
        for (i = 0; i < listeRectangles.size(); i++) {
            x = listeRectangles.get(i);
            if (mario.getRectangle().getBounds().intersects(x.getX() - 1, x.getY() - 2, x.getWidth(), x.getHeight())) {
                collision1 = true;
                break;
            }
        }
        if (collision1) {
            if (listeRectanglesFlag.contains(x)) {
                g.drawImage(flag, mario.getX() + 3, mario.getY());
                gameFini = true;
            }
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
                        compteur++;
                    }
                    for (int j = i; j < listeRectangles.size(); j++) {
                        y = listeRectangles.get(j);
                        if (mario.getRectangle().getBounds().intersects(y.getX() - 1, y.getY() - 2, y.getWidth(), y.getHeight())) {
                            if (y.getY() < x.getY()) {
                                mario.setMoving(false);
                            }
                        }
                    }
                }
            } else if (xOverLap == 0) {
                mario.setMoving(false);

            }
        } else {
            compteur = 0;
            mario.setaTerre(false);
        }
    }

    private void detecterFinGame() {
        if (gameFini) {
            if (mario.getY() == 192 && conditionFin && !mario.isBig()
                    || mario.getY() == 176 && conditionFin && mario.isBig()) {
                JOptionPane.showMessageDialog(null, "easy win");
                conditionFin = false;
            }
        }
    }

    public int getID() {

        return ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        this.container = container;
        map = new Map("map\\map.tmx", this, 1);
        this.mario = new Mario(true, map, this);
        creerRectangle();
        fillListObjets();
        fenetreReduite();
        creerWoombas();
        Thread thread = new Thread(() -> {
            int x = 0;
            while (true) {
                if (app.hasFocus() && !focus) {
                    sounds.getItsMe().play();
                    focus = true;
                } else if (!app.hasFocus() && focus) {
                    sounds.getBye().play();
                    focus = false;
                }
            }
        });
        thread.start();

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        this.g = g;

        this.map.render(map.getRenderX(), map.getRenderY());
        g.drawAnimation(mario.getAnimation(), mario.getX(), mario.getY());
        dessinerRectangles(g);
        bouger(g);
        timer++;
        afficherObjets(g);
        mario.updateAnimation();
        gererGravite();
        gererCollisionObjets();
        if (mario.getState() != Mario.State.DEAD) {
            gererCollisions(g);
        }
        if (AI) {
            intelligenceArtificielle();
        }

        detecterFinGame();
        for (int i = 0; i < listeRectangles.size(); i++) {
            if (mario.getY() - listeRectangles.get(i).getY() <= 16
                    && mario.getY() - listeRectangles.get(i).getY() > 0
                    && listeRectangles.get(i).getX() - mario.getX() == 24) {

            }
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
    }
}
