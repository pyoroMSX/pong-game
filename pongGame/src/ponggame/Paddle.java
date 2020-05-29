
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
public class Paddle implements Runnable {
    
    //int xPos;
    //int yPos;
    double yMove;
    int playerNum;
    
    Rectangle paddle;
    
    public Paddle(int xPos,int yPos,int playerNum){
        //this.xPos = xPos;
        //this.yPos = yPos;
        this.playerNum = playerNum;
       paddle = new Rectangle(xPos, yPos, 15, 100);
    }
    protected void setYmovement(double y){
        yMove = y;
    }
    protected double getYposition(){
        return paddle.y;
    }
    
    protected void draw(Graphics g) {
			g.setColor(Color.WHITE);
			g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
    }
    
    private void move(){ //limit the paddles to the visible screen
        paddle.y += yMove;
        if (paddle.y <= 50)
            paddle.y = 50;
        if (paddle.y >= 470)
            paddle.y = 470;
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
