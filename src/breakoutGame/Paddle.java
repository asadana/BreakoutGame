package breakoutGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Paddle extends JPanel implements KeyListener {
	public static final int P_WIDTH = 150;
	public static final int P_HEIGHT = 10;
	public static final Color PADDLE_COLOR = Color.black;
	private int xPos;
	public static int yPos = GameMain.HEIGHT - 30;
	public static final int PADDLE_SPEED = 5;
	
	public Paddle(int xPos)
	{
        this.xPos = xPos;   
    }
	
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
	
	public int getXPos()
	{
		return xPos;
	}
	
	public void draw(Graphics2D g){
        g.setColor(PADDLE_COLOR);
        g.fill(new Rectangle2D.Double(xPos, yPos, P_WIDTH, P_HEIGHT));
        g.setColor(Color.BLUE);
        g.draw(new Rectangle2D.Double(xPos, yPos, P_WIDTH, P_HEIGHT));
    }
	
	public int getX()
	{
		return this.xPos;
	}
	
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