/* Ball.java : Controls the drawing/movement of the Ball, and checks for collisions
 * 
 * Part of: BreakoutGame: Assignment #1 for P532
 * 
 * Created By: Harini Rangarajan and Ankit Sadana
 * Created On: 08/28/2015
 * Last Edited By: Ankit Sadana
 * Last Edited On: 08/30/2015
 */

package breakoutGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Ball {

	// Position variables
	private double xPos, yPos;
	// Diameter variable to define the size of the ball
	public static final int DIAMETER = 20;
	
	public static final Color BALL_COLOR = Color.black;
	
	public static Ellipse2D.Double ball;
	
	// new position variables for x and y
	private double newX = 1;
	private double newY = -1;

	// Constructor to set the Ball position
	public Ball(double d, double e)
	{
		this.xPos = d;
		this.yPos = e;
		ball = new Ellipse2D.Double(d, e, DIAMETER, DIAMETER);
	}

	// Draw function to draw the ball onto the frame
	public void draw(Graphics2D g)
	{
		g.setColor(BALL_COLOR);
		g.fill(ball);
		g.setColor(Color.gray);
		g.draw(ball);
	}
	
	// getX returns the position of the ball on X-axis
	public double getX()
	{
		return this.xPos;
	}
	
	// getY returns the position of the ball on Y-axis
	public double getY()
	{
		return this.yPos;
	}
	
	// getWidth returns the width of the ball shape
	public int getWidth()
	{
		return (int)ball.getWidth();
	}
	
	// getHeight returns the height of the ball shape
	public int getHeight()
	{
		return (int)ball.getHeight();
	}
	
	/* collisionPaddle takes an object of the paddle and compares the area covered by the
	 * paddle to the area covered by the ball and checks if they intersect
	 */
	public boolean collisionPaddle(Paddle paddleObj)
	{	
		Area ballArea = new Area (ball);
		ballArea.intersect(new Area(new Rectangle2D.Double(paddleObj.paddle.getX(), paddleObj.paddle.getY(), Paddle.P_WIDTH, Paddle.P_HEIGHT)));
		return !ballArea.isEmpty();
	}
	
	/* collisionBall takes an object of the brick and compares the area covered by the
	 * brick and compares it to the area covered by the ball and checks if they intersect.
	 */
	public boolean collisionBrick(Bricks brickObj)
	{
		Area ballArea = new Area(ball);

/*
		for(int i=0; i<GameMain.Brick.size(); i++)
		{
			ArrayList<Bricks> row = GameMain.Brick.get(i);
			for(Bricks brickObj: row)
			{
*/
				ballArea.intersect(new Area(new Rectangle2D.Double(brickObj.brick.getX(), brickObj.brick.getY(), Bricks.BRICK_WIDTH, Bricks.BRICK_HEIGHT)));
		
				if(!ballArea.isEmpty())
				{
					System.out.println("Brick Collision");
					System.out.println("Ball : " + ball.getX() + ", " + ball.getY());
					System.out.println("hitAbove: "  + hitAbove(brickObj));
					System.out.println("hitBelow: "  + hitBelow(brickObj));
					System.out.println("hitLeft: "  + hitLeft(brickObj));
					System.out.println("hitRight: "  + hitRight(brickObj));
				
					if(hitAbove(brickObj))
						moveUp();
					else if(hitBelow(brickObj))
						moveDown();
					else if(hitLeft(brickObj))
						moveLeft();
					else if(hitRight(brickObj))
						moveRight();
					
					
					
					/*
					Graphics2D g = null;
					g.setColor(GameMain.bgColor);
					g.fill(new Rectangle2D.Double(brickObj.brick.getX(), brickObj.brick.getY(), Bricks.BRICK_WIDTH, Bricks.BRICK_HEIGHT));
					*/
					
					//GameMain.Brick.remove(brickObj);
					return true;
				}
			//}
		//}
		return false;
		
	}
	
	// Moves the ball in the frame and keeps it from going outside the frameSize
	public void move()
	{
		if(ball.getX() + newX < 0)
			newX = 1;
		if(ball.getX() + ball.getWidth() + newX >= GameMain.frameSize)
			newX = -1;
		if(ball.getY() + newY < 0)
			newY = 1;
		if(ball.getY() + ball.getHeight() + newY >= GameMain.frameSize)
			newY = -1;
		
		ball.setFrame(newX + ball.getX(), ball.getY() + newY, ball.getWidth(), ball.getHeight());
	}
	
	// Methods to move the ball in each direction by 1
	public void moveUp()
	{
		newY = -1;
	}
	
	public void moveDown()
	{
		newY = 1;
	}
	
	public void moveLeft()
	{
		newX = -1;
	}
	
	public void moveRight()
	{
		newX = 1;
	}
	
	// Methods to check for brick collision
	public boolean hitBelow(Bricks brickObj)
	{
		return xPos >= brickObj.getY() + Bricks.BRICK_HEIGHT ;
	}
	
	public boolean hitAbove(Bricks brickObj) 
	{
		return yPos + ball.getHeight() <= brickObj.brick.getY();
	}
	
	public boolean hitLeft(Bricks brickObj) 
	{
		return xPos + ball.getWidth() <= brickObj.brick.getX();
	}
	
	public boolean hitRight(Bricks brickObj) 
	{
		return xPos >= brickObj.brick.getX() + Bricks.BRICK_WIDTH;
	}

}

