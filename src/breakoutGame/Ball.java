/* Ball.java : Controls the drawing/movement of the Ball, and checks for collisions
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
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Ball {

	// Position variables
	private int xPos, yPos;
	// Diameter variable to define the size of the ball
	public static final int DIAMETER = 20;
	
	public static final Color BALL_COLOR = Color.black;

	// Constructor to set the Ball position
	public Ball(int xPos, int yPos)
	{
		this.xPos = xPos;
		this.yPos = yPos;
	}

	// Draw function to draw the ball onto the frame
	public void draw(Graphics2D g)
	{
		g.setColor(BALL_COLOR);
		g.fill(new Ellipse2D.Double(xPos, yPos, DIAMETER, DIAMETER));
		g.setColor(Color.gray);
		g.draw(new Ellipse2D.Double(xPos, yPos, DIAMETER, DIAMETER));
	}
	
	// getX returns the position of the ball on X-axis
	public int getX()
	{
		return this.xPos;
	}
	
	// getY returns the position of the ball on Y-axis
	public int getY()
	{
		return this.yPos;
	}
	
	/* collisionPaddle takes an object of the paddle and compares the area covered by the
	 * paddle to the area covered by the ball and checks if they intersect
	 */
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
	
	/* collisionBall takes an object of the brick and compares the area covered by the
	 * brick and compares it to the area covered by the ball and checks if they intersect.
	 */
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

