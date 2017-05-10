/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckTest;

import java.util.ArrayList;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Sami
 */
public class Map extends TiledMap {

    private int renderX = 0, renderY = 0;
    private WindowGame bg;
    private int codeMap;
    private ArrayList<Coin> listeCoins = new ArrayList();

    public Map(String ref, WindowGame bg, int codeMap) throws SlickException {
        super(ref);
        this.bg = bg;
        this.codeMap = codeMap;
        fillListCoins();
    }

    public ArrayList<Coin> getListeCoins() {
        return listeCoins;
    }

    private void fillListCoins() throws SlickException {
        if (codeMap == 1) {
            Coin coin = new Coin(18, 9);
            Coin coin1 = new Coin(22, 9);
            Coin coin2 = new Coin(78, 5);
            Coin coin3 = new Coin(80, 5);
            Coin coin4 = new Coin(84, 5);
            Coin coin5 = new Coin(91, 9);
            Coin coin6 = new Coin(97, 9);
            Coin coin7 = new Coin(115, 9);
            Coin coin8 = new Coin(119, 5);
            Coin coin9 = new Coin(126, 5);
            Coin coin10 = new Coin(165, 9);

            listeCoins.add(coin);
            listeCoins.add(coin1);
            listeCoins.add(coin2);
            listeCoins.add(coin3);
            listeCoins.add(coin4);
            listeCoins.add(coin5);
            listeCoins.add(coin6);
            listeCoins.add(coin7);
            listeCoins.add(coin8);
            listeCoins.add(coin9);
            listeCoins.add(coin10);
        }
    }

    public int getRenderX() {
        return renderX;
    }

    public int getRenderY() {
        return renderY;
    }

    @Override
    public int getWidth() {
        return super.getWidth(); //To change body of generated methods, choose Tools | Templates.
    }

    public void avancer() {

        renderX -= 1;
        for (int i = 0; i < bg.getListeObjets().size(); i++) {
            for (int j = 0; j < bg.getListeObjets().get(i).size(); j++) {
                bg.getListeObjets().get(i).get(j).setX(bg.getListeObjets().get(i).get(j).getX() - 1);
            }
        }
        for (int i = 0; i < bg.getListeWombas().size(); i++) {
            bg.getListeWombas().get(i).setX(bg.getListeWombas().get(i).getX() - 1);
            bg.getListeWombas().get(i).setxMax(bg.getListeWombas().get(i).getxMax() - 1);
            bg.getListeWombas().get(i).setxMin(bg.getListeWombas().get(i).getxMin() - 1);
        }

    }

}
