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
import javax.swing.*;

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
    static Paddle p_1up = new Paddle(20, 250, 1);
    static Paddle p_2up = new Paddle(760, 250, 2);
    static Ball playball = new Ball(400, 300);
    
    
    public PongGame(){ //sets up the window
        this.getContentPane().setBackground(Color.BLACK);
        this.setTitle("Pong game");
        this.setSize(resolution);
        this.setResizable(false);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PongGame game = new PongGame();
        
        Thread ball = new Thread(playball); //runs the ball, and the players
	Thread p1 = new Thread(p_1up);
	Thread p2 = new Thread(p_2up);
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
		p_1up.draw(g);
		p_2up.draw(g);
		
		g.setColor(Color.WHITE);
		
		repaint();
	}
    
    
}
