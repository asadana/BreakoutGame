package breakoutGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Ball {

	private int xPos, yPos;
	public static final int DIAMETER = 20;
	public static final Color BALL_COLOR = Color.black;

	public Ball(int xPos, int yPos){
		this.xPos   = xPos;
		this.yPos   = yPos;
	}


	public void draw(Graphics2D g){
		g.setColor(BALL_COLOR);
		g.fill(new Ellipse2D.Double(xPos, yPos, DIAMETER, DIAMETER));
		g.setColor(Color.gray);
		g.draw(new Ellipse2D.Double(xPos, yPos, DIAMETER, DIAMETER));
	}
	
	public int getX()
	{
		return this.xPos;
	}
	
	public int getY()
	{
		return this.yPos;
	}
	
	public boolean collisionPaddle(Paddle paddle)
	{
		boolean check;
		Area ballArea = new Area(new Ellipse2D.Double(xPos, yPos, DIAMETER, DIAMETER));
		check = ballArea.intersects(new Rectangle2D.Double(paddle.getX(), paddle.getY(), Paddle.P_WIDTH, Paddle.P_HEIGHT));
		
		if(check)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	@SuppressWarnings("null")
	public boolean collisionBrick(Bricks brick)
	{
		boolean check;
		
		Area ballArea = new Area(new Ellipse2D.Double(xPos, yPos, DIAMETER, DIAMETER));
		check = ballArea.intersects(new Rectangle2D.Double(brick.getX(), brick.getY(), Bricks.BRICK_WIDTH, Bricks.BRICK_HEIGHT));
		
		if(check)
		{
			Graphics2D g = null;
			g.setColor(Color.GRAY);
			g.fill(new Rectangle2D.Double(brick.getX(), brick.getY(), Bricks.BRICK_WIDTH, Bricks.BRICK_HEIGHT));
			return true;
		}
		else
		{
			return false;
		}
	}
}

