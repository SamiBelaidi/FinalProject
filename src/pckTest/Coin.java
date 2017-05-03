/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckTest;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.newdawn.slick.Image;

/**
 *
 * @author Alex
 */
public class Coin {
    
    private int x,y;
    URL url;
        Icon coin = new ImageIcon(url);
        JLabel label = new JLabel(coin);

        JFrame f = new JFrame("Animation");
        
    
    public Coin(int x, int y) throws MalformedURLException{
        this.url = new URL("https://www.google.ca/imgres?imgurl=http%3A%2F%2Frs217.pbsrc.com%2Falbums%2Fcc85%2FShadowmario111160%2FMario%2520Clips%2520And%2520Movies%2Fthcoin.gif~c200&imgrefurl=http%3A%2F%2Fphotobucket.com%2Fgifs%2Fmario%2520coin&docid=10V0ExZz_nFKVM&tbnid=4mDD7nUqzkeb4M%3A&vet=10ahUKEwj9nbW_6tPTAhVm2IMKHTDgACgQMwgpKAQwBA..i&w=200&h=200&bih=676&biw=1366&q=mario%20bros%20coin%20gif&ved=0ahUKEwj9nbW_6tPTAhVm2IMKHTDgACgQMwgpKAQwBA&iact=mrc&uact=8");
        
    }
    
    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
    
    
}
