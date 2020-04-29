/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.JTextField;
import static ponggame.PongGame.playball;

/**
 *
 * @author Rafael Perez
 */
public class PongGame extends JFrame { //initialize paddles and the ball
    
    int width = 800;
    int height = 600;
    Dimension resolution = new Dimension(width, height);
    Image dbImage;
    Graphics dbGraphics;
  
    static Ball playball = new Ball(400, 300);
    
    
    public PongGame(){ //sets up the window
        this.getContentPane().setBackground(Color.BLACK);
        this.setTitle("Pong game");
        this.setSize(resolution);
        this.setResizable(false);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextField textField = new JTextField();
        textField.addKeyListener(new Input());
        this.add(textField);
        this.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PongGame game = new PongGame();
        
        Thread ball = new Thread(playball); //runs the ball, and the players
	Thread p1 = new Thread(playball.p_1up);
	Thread p2 = new Thread(playball.p_2up);
        ball.start();
	p1.start();
	p2.start();
 
        
        
        
        
    }
    
    @Override
	public void paint(Graphics g) {
		dbImage = createImage(getWidth(), getHeight());
		dbGraphics = dbImage.getGraphics();
		draw(dbGraphics);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public void draw(Graphics g) {
		playball.draw(g);
		playball.p_1up.draw(g);
		playball.p_2up.draw(g);
		
		g.setColor(Color.WHITE);
		
		repaint();
	}
        
        
    
}
class Input extends KeyAdapter{
            @Override
            public void keyPressed(KeyEvent event){
                
                if(event.getKeyCode() == KeyEvent.VK_W) {
                    System.out.println("keypress!!!");
                    playball.p_1up.yMove = -4;
                }
                if(event.getKeyCode() == KeyEvent.VK_S) {
                    System.out.println("keypress!!!");
                    playball.p_1up.yMove = 4;
                }
                 if(event.getKeyCode() == KeyEvent.VK_I) {
                    System.out.println("keypress!!!");
                    playball.p_2up.yMove = -4;
                }
                if(event.getKeyCode() == KeyEvent.VK_K) {
                    System.out.println("keypress!!!");
                    playball.p_2up.yMove = 4;
                }   
		
                
            }
            @Override
            public void keyReleased(KeyEvent event){
                if(event.getKeyCode() == event.VK_W) {
			playball.p_1up.yMove = 0;
                        System.out.println("keyrelease!!");
		}  
                if(event.getKeyCode() == event.VK_S) {
			playball.p_1up.yMove = 0;
                        System.out.println("keyrelease!!");
		}  
                if(event.getKeyCode() == event.VK_I) {
			playball.p_2up.yMove = 0;
                        System.out.println("keyrelease!!");
		}  
                if(event.getKeyCode() == event.VK_K) {
			playball.p_2up.yMove = 0;
                        System.out.println("keyrelease!!");
		}  
            }
        }