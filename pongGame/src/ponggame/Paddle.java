/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Perez
 */
public class Paddle implements Runnable {
    
    int xPos;
    int yPos;
    int yMove;
    int playerNum;
    
    Rectangle paddle;
    
    public Paddle(int xPos,int yPos,int playerNum){
        this.xPos = xPos;
        this.yPos = yPos;
        this.playerNum = playerNum;
       // paddle = new Rectangle(xPos, yPos, 15, 100);
       paddle = new Rectangle(xPos, yPos, 15, 100);
       
       //yMove = 1;
    }
    public void draw(Graphics g) {
			g.setColor(Color.WHITE);
			g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
    }
    
    public void move(){
        paddle.y += yMove;
        if (paddle.y <= 20)
            paddle.y = 20;
        if (paddle.y >= 500)
            paddle.y = 500;
    }
    

    @Override
    public void run() {
        for(;;){
            
            move();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Paddle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
