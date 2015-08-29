/* Paddle.java : Controls the drawing/movement of the Paddle
 * 
 * Part of: BreakoutGame: Assignment #1 for P532
 * 
 * Created By: Harini Rangarajan and Ankit Sadana
 * Created On: 08/28/2015
 * Last Edited By: Ankit Sadana
 * Last Edited On: 08/29/2015
 */

package breakoutGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Paddle extends JPanel implements KeyListener 
{
	
	// Defining the characteristics of the paddle
	public static final int P_WIDTH = 150;
	public static final int P_HEIGHT = 10;
	public static final Color PADDLE_COLOR = Color.black;
	
	// Defining the position variables for the paddle
	private int xPos;
	public static int yPos = GameMain.HEIGHT - 30;
	
	// Defining the speed of the paddle
	public static final int PADDLE_SPEED = 5;
	
	// Constructor method initially sets the position of the paddle on the x-axis
	public Paddle(int xPos)
	{
        this.xPos = xPos;   
    }
	
	// setXPos is used to set the position of the paddle during the game
	public void setXPos(int xPos)
	{ 
        this.xPos = xPos;
        if(xPos < 0) //make sure its not negative value
        {     
        	this.xPos = 0;
        }
        if(xPos > (GameMain.WIDTH - P_WIDTH)) //to ensure there is enough space for paddle to be drawn without exceeding Game boundary
        {
        	this.xPos = (GameMain.WIDTH - P_WIDTH);
        }
    }
	
	public void draw(Graphics2D g){
        g.setColor(PADDLE_COLOR);
        g.fill(new Rectangle2D.Double(xPos, yPos, P_WIDTH, P_HEIGHT));
        g.setColor(Color.BLUE);
        g.draw(new Rectangle2D.Double(xPos, yPos, P_WIDTH, P_HEIGHT));
    }
	
	// getX function returns the position of the paddle on X-axis
	public int getX()
	{
		return this.xPos;
	}
	
	
	// getY function returns the position of the paddle on Y-axis
	public int getY()
	{
		return Paddle.yPos;
	}

	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode()==KeyEvent.VK_LEFT){
			xPos = (xPos - 3);

		}
		else if(k.getKeyCode()==KeyEvent.VK_RIGHT){
			xPos = (xPos + 3);
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}