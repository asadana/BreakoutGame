package breakoutGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ball {

	private int xPos, yPos;
	public static final int DIAMETER = 20;
	public static final Color BALL_COLOR = Color.black;

	public Ball(int xPos, int yPos){
		this.xPos   = xPos;
		this.yPos   = yPos;
	}


	public void draw(Graphics g){
		g.setColor(BALL_COLOR);
		g.fillOval(xPos, yPos, DIAMETER, DIAMETER);
		g.setColor(Color.gray);
		g.drawOval(xPos, yPos, DIAMETER, DIAMETER);
	}
}

