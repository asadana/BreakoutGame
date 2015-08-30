/* Bricks.java : Controls the drawing/movement of the Bricks
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
import java.awt.geom.Rectangle2D;

public class Bricks 
{
	// Defining the characteristics of the bricks
	public static final int BRICK_WIDTH = 60;
	public static final int BRICK_HEIGHT = 20;
	public static final Color BRICK_COLOR = Color.BLUE;
	public static final int BRICK_GAP = 2;
	
	// Defining the position variables of the brick
	private int xPos, yPos; 
	
	public static Rectangle2D.Double brick; 

	// Constructor method to set the position of the brick
	public Bricks(int xPos, int yPos)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		brick = new Rectangle2D.Double(xPos, yPos, BRICK_WIDTH, BRICK_HEIGHT);
	}
	
	// Draw method draws and fills the position of the brick
	public void draw(Graphics2D g)
	{
		g.setColor(GameMain.bgColor);
		g.fill(brick);
		g.setColor(BRICK_COLOR);
		g.fill(new Rectangle2D.Double((xPos+2), (yPos+2), BRICK_WIDTH-4, BRICK_HEIGHT-4));
	}
	
	// getX function returns the position of the brick on X-axis
	public int getX()
	{
		return xPos;
	}
	
	// getY function returns the position of the brick on Y-axis
	public int getY()
	{
		return yPos;
	}
	
}