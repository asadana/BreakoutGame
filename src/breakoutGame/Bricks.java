package breakoutGame;

import java.awt.Color;
import java.awt.Graphics;

public class Bricks {
	public static final int BRICK_WIDTH = 60;
	public static final int BRICK_HEIGHT = 20;
	public static final Color BRICK_COLOR = Color.BLUE;
	private int xPos, yPos; 

	public Bricks(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
	}
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.fillRect(xPos, yPos, BRICK_WIDTH, BRICK_HEIGHT);
		g.setColor(BRICK_COLOR);
		g.fillRect((xPos+2), (yPos+2), BRICK_WIDTH-4, BRICK_HEIGHT-4);
	}
	
	public boolean collision(Ball ball)
	{
		int ballX, ballY;
		ballX = ball.getX();
		ballY = ball.getY();
		
		return true;
	}
	
	public int getX()
	{
		return this.xPos;
	}
	
	public int getY()
	{
		return this.yPos;
	}
	
	
}