
package ponggame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Perez
 */
public class Ball implements Runnable {
    
    private double xMove;
    private double yMove;
    private double baseSpeed;
    private int whoScored;
    private int p1Score;
    private int p2Score;
    
    
    static Paddle p_1up = new Paddle(20, 250, 1);
    static Paddle p_2up = new Paddle(765, 250, 2);
    
    Rectangle ball;
    
    public Ball(int xPos,int yPos){
        
        ball = new Rectangle(xPos, yPos, 15, 15);

       baseSpeed = 2.5;
       setXmovement(baseSpeed); //initial movespeed
       setYmovement(baseSpeed);
       
    }
    
    private void setXmovement(double x){
        xMove = x;
    }
    private void setYmovement(double y){
        yMove = y;
    }
    private double getXmovement(){
      return xMove;
    }
    private double getYmovement(){
        return yMove;
    }
    private void setXposition(int x){
        ball.x = x;
    }
    private void setYposition(int y){
        ball.y = y;
    }
    private int getXposition(){
        return ball.x;
    }
    private int getYposition(){
        return ball.y;
    }
    private void setBaseSpeed(double speed){
        baseSpeed = speed;
    }
    private double getBaseSpeed(){
        return baseSpeed;
    }
    
    private void collisionCheck(){ 
        if(ball.intersects(p_1up.paddle)){
            baseSpeed++;
            setXmovement((baseSpeed));
            //System.out.printf("basespeed is %f\n", baseSpeed);
        }
        else if (ball.intersects(p_2up.paddle)){
            baseSpeed++;
            setXmovement((baseSpeed * -1));
            //System.out.printf("basespeed is %f\n", baseSpeed);
        }
    }
    
    private void givePoint(int id){
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
  
    protected int getPoint(int id){
        switch(id){
            case 1:
                    return p1Score;
            case 2:
                    return p2Score;
            default: 
                    return 0;
        }
    }
    private void move(){
        collisionCheck();
        ball.x += getXmovement();
        ball.y += getYmovement();
        
        if (ball.x <= -20){
                    whoScored = 2;
                    givePoint(whoScored);
                    respawn(whoScored);
        }
        if (ball.x >= 800){
                    whoScored = 1;
                    givePoint(whoScored);
                    respawn(whoScored);
        }
         if (ball.y <= 20){
             setYposition(20);
             setYmovement(getYmovement() * -1);
         }
         if (ball.y >= 580){
             setYposition(580);
             setYmovement(getYmovement() * -1);
         }
    }
    
    private void respawn(int serve){
        int upDown;
        setBaseSpeed(2.5);
        Random randNum = new Random();
        
        try {
            TimeUnit.SECONDS.sleep(1); //delay the serve by a second
        } catch (InterruptedException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setYposition(randNum.nextInt(559) + 20);
        setXposition(400);
        
        
        upDown = randNum.nextInt(2); //it is served to the player who got scored on
        if (upDown == 0)
            setYmovement(baseSpeed * -1);
        else    
            setYmovement(baseSpeed);
        
        if (serve == 2)
            setXmovement(baseSpeed * -1);
        else
            setXmovement(baseSpeed);
        
        this.whoScored = 0;
        
    }
   protected void draw(Graphics g) {
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
