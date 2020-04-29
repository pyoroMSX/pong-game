
package ponggame;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import static ponggame.PongGame.playball;

/**
 *
 * @author Rafael Perez
 * 
 */
public class PongGame extends JFrame { //initialize the window, and the ball
    
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
        
        PongGame game = new PongGame(); //begins the game
        
        Thread ball = new Thread(playball); //runs the ball, and the players
	Thread p1 = new Thread(playball.p_1up);
	Thread p2 = new Thread(playball.p_2up);
        ball.start();
	p1.start();
	p2.start();
        
    }
    
    @Override
	public void paint(Graphics g) { //draw graphics
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
                Image p1Count = Toolkit.getDefaultToolkit().getImage("gpx/zero.png"); //load scoreboard numbers
                Image p2Count = Toolkit.getDefaultToolkit().getImage("gpx/zero.png");
                
                Image net = Toolkit.getDefaultToolkit().getImage("gpx/net.png");
                g.drawImage(net, 400, 0, this);
                
                int p1Score = playball.getPoint(1); //check player point values
                int p2Score = playball.getPoint(2);
                
                switch(p1Score){ //updating the scoreboard when a player scores
                    case 0:
                           p1Count = Toolkit.getDefaultToolkit().getImage("gpx/zero.png");
                           break;
                    case 1:
                           p1Count = Toolkit.getDefaultToolkit().getImage("gpx/one.png");
                           break;
                    case 2:
                           p1Count = Toolkit.getDefaultToolkit().getImage("gpx/two.png");
                           break;
                    case 3:
                           p1Count = Toolkit.getDefaultToolkit().getImage("gpx/three.png");
                           break;
                    case 4:
                           p1Count = Toolkit.getDefaultToolkit().getImage("gpx/four.png");
                           break;
                    case 5:
                           p1Count = Toolkit.getDefaultToolkit().getImage("gpx/five.png");
                           break;
                    case 6:
                           p1Count = Toolkit.getDefaultToolkit().getImage("gpx/six.png");
                           break;
                    case 7:
                           p1Count = Toolkit.getDefaultToolkit().getImage("gpx/seven.png");
                           break;
                    case 8:
                           p1Count = Toolkit.getDefaultToolkit().getImage("gpx/eight.png");
                           break;
                    case 9:
                           p1Count = Toolkit.getDefaultToolkit().getImage("gpx/nine.png");
                           break;
                    default: 
                           p1Count = Toolkit.getDefaultToolkit().getImage("gpx/zero.png");
                           break;
                }
                switch(p2Score){ //likewise for player 2
                    case 0:
                           p2Count = Toolkit.getDefaultToolkit().getImage("gpx/zero.png");
                           break;
                    case 1:
                           p2Count = Toolkit.getDefaultToolkit().getImage("gpx/one.png");
                           break;
                    case 2:
                           p2Count = Toolkit.getDefaultToolkit().getImage("gpx/two.png");
                           break;
                    case 3:
                           p2Count = Toolkit.getDefaultToolkit().getImage("gpx/three.png");
                           break;
                    case 4:
                           p2Count = Toolkit.getDefaultToolkit().getImage("gpx/four.png");
                           break;
                    case 5:
                           p2Count = Toolkit.getDefaultToolkit().getImage("gpx/five.png");
                           break;
                    case 6:
                           p2Count = Toolkit.getDefaultToolkit().getImage("gpx/six.png");
                           break;
                    case 7:
                           p2Count = Toolkit.getDefaultToolkit().getImage("gpx/seven.png");
                           break;
                    case 8:
                           p2Count = Toolkit.getDefaultToolkit().getImage("gpx/eight.png");
                           break;
                    case 9:
                           p2Count = Toolkit.getDefaultToolkit().getImage("gpx/nine.png");
                           break;
                    default: 
                           p2Count = Toolkit.getDefaultToolkit().getImage("gpx/zero.png");
                           break;  
                }
		g.drawImage(p1Count, 200, 50, this);
                g.drawImage(p2Count, 550, 50, this);
		repaint();
	}
        
    
}
class Input extends KeyAdapter{ //input control
            @Override
            public void keyPressed(KeyEvent event){
                
                if(event.getKeyCode() == KeyEvent.VK_W)
                    playball.p_1up.yMove = -5;
                if(event.getKeyCode() == KeyEvent.VK_S)
                    playball.p_1up.yMove = 5;
                 if(event.getKeyCode() == KeyEvent.VK_I)
                    playball.p_2up.yMove = -5;
                if(event.getKeyCode() == KeyEvent.VK_K)
                    playball.p_2up.yMove = 5;
            }

            @Override
            public void keyReleased(KeyEvent event){
                if(event.getKeyCode() == event.VK_W) 
			playball.p_1up.yMove = 0; 
                if(event.getKeyCode() == event.VK_S) 
			playball.p_1up.yMove = 0;
                if(event.getKeyCode() == event.VK_I) 
			playball.p_2up.yMove = 0;
                if(event.getKeyCode() == event.VK_K) 
			playball.p_2up.yMove = 0;
		} 
            }
        