/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckTest;

import org.newdawn.slick.Animation;

/**
 *
 * @author Sami
 */
public interface Bougeable {

    public void bouger();
    public Animation getAnimation();
    public int getX();
    public int getY();
}
