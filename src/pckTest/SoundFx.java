/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckTest;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

/**
 *
 * @author 1522706
 */
public class SoundFx {
    
    private Music coin;
    private Music oneUp;
    private Music breakBlock;
    private Music jumpSmall;
    private Music jumpSuper;
    private Music die;
    private Music itsMe;
    private Music bye;
    private Music mushroom;
    private Music stomp;
    private Music stageClear;

    public SoundFx() throws SlickException{
        
        coin = new Music("coin.wav");
        oneUp = new Music("1-up.wav");
        breakBlock = new Music("breakblock.wav");
        jumpSmall = new Music("jumpsmall.wav");
        jumpSuper = new Music("jumpsuper.wav");
        die = new Music("die.wav");
        itsMe = new Music("itsme.wav");
        bye = new Music("byebye.wav");
        mushroom = new Music("mushroom.wav");
        stomp = new Music("stomp.wav");
        stageClear = new Music("stageclear.wav");
  
    }

    public Music getBreakBlock() {
        return breakBlock;
    }

    public Music getBye() {
        return bye;
    }

    public Music getCoin() {
        return coin;
    }

    public Music getDie() {
        return die;
    }

    public Music getItsMe() {
        return itsMe;
    }

    public Music getJumpSmall() {
        return jumpSmall;
    }

    public Music getJumpSuper() {
        return jumpSuper;
    }

    public Music getMushroom() {
        return mushroom;
    }

    public Music getOneUp() {
        return oneUp;
    }

    public Music getStageClear() {
        return stageClear;
    }

    public Music getStomp() {
        return stomp;
    }
    
    
    
}
