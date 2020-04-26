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
public class PongGame extends JFrame {
    
    int width = 800;
    int height = 600;
    Dimension resolution = new Dimension(width, height);
    Image dbImage;
    Graphics dbGraphics;
    
    public PongGame(){
        this.setTitle("Pong game");
        this.setSize(resolution);
        this.setResizable(false);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.CYAN);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PongGame game = new PongGame();
        // TODO code application logic here
    }
    
    
    
}
