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
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Perez
 */
public class Ball implements Runnable {
    
    int xPos;
    int yPos;
    int xMove;
    int yMove;
    int whoScored;
    int p1Score;
    int p2Score;
    
    static Paddle p_1up = new Paddle(20, 250, 1);
    static Paddle p_2up = new Paddle(760, 250, 2);
    
    Rectangle ball;
    
    public Ball(int xPos,int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        
        ball = new Rectangle(this.xPos, this.yPos, 15, 15);
        
       xMove = 2;
       yMove = 2;
        
        //p_1up.yMove = 1;
    }
    
    public void collisionCheck(){
        if(ball.intersects(p_1up.paddle))
            xMove = (xMove - 1) * -1;
        else if (ball.intersects(p_2up.paddle))
            xMove = (xMove + 1) * -1;
            
    }
    
    public void givePoint(int id){
        switch(id){
            case 1:
                    System.out.println("Player 1 has scored");
                    if (p1Score != 9)
                        p1Score++;
                    else p1Score = 0;
                    System.out.printf("\nPlayer 1 has %d points\n", p1Score);
                    break;
            case 2:
                    System.out.println("Player 2 has scored");
                    if (p2Score != 9)
                        p2Score++;
                    else p2Score = 0;
                    System.out.printf("\nPlayer 2 has %d points\n", p2Score);
                    break;
            default: break;
        } 
    }
  
    public int getPoint(int id){
        switch(id){
            case 1:
                    return p1Score;
            case 2:
                    return p2Score;
            default: 
                    return 0;
        }
    }
    
    
    public void move(){
        collisionCheck();
        ball.x += xMove;
        ball.y += yMove;
        
        if (ball.x <= -20){
        //System.out.println("Player 2 has scored");
                    whoScored = 2;
                    givePoint(whoScored);
                    respawn(whoScored);
        }
        if (ball.x >= 800){
       // System.out.println("Player 1 has scored");
                    whoScored = 1;
                    givePoint(whoScored);
                    respawn(whoScored);
        }
        if ((ball.y <= 20) || (ball.y >= 580))
        yMove = yMove * -1;
        
        //if(ball.y >= 580)
        //yMove = yMove * -1;
        /*
        switch(ball.x){ //when the ball goes out of bounds, a player scores, and the ball is reset
            case -20:
                    System.out.println("Player 2 has scored");
                    whoScored = 2;
                    respawn(whoScored);
                    break;
                    
            case 800:
                    System.out.println("Player 1 has scored");
                    whoScored = 1;
                    respawn(whoScored);
                    break;
            default:
                    break;
        }
        switch(ball.y){ //when the ball touches upper/lower screen, it bounces, and speed is maintained
            case 20:
                    System.out.println("boing");
                    yMove = yMove * -1;
                    break;
            case 580:
                    System.out.println("boing");
                    yMove = yMove * -1;
                    break;
            default:
                    break;
        }
        */
    }
    
    public void respawn(int serve){
        
        Random randNum = new Random();
        
        try {
            TimeUnit.SECONDS.sleep(1); //delay the serve by a second
        } catch (InterruptedException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ball.y = (randNum.nextInt(559) + 20);
        ball.x = 400;
        
        yMove = randNum.nextInt(1);
        if (yMove == 0)
            yMove = -2;
        
        if (serve == 2)
            xMove = -2;
        else
            xMove = 2;
        
        this.whoScored = 0;
        
        
        
    }
   public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(ball.x, ball.y, ball.width, ball.height);
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
