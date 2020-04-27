/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Perez
 */
public class Ball implements Runnable {
    
    int xPos;
    int yPos;
    
    Rectangle ball;
    
    public Ball(int xPos,int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        
        ball = new Rectangle(this.xPos, this.yPos, 15, 15);
        
        
    }
   public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(ball.x, ball.y, ball.width, ball.height);
	}

    @Override
    public void run() {
        for(;;){
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Paddle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
