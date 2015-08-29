/* GameMain.java : Contains the main function and calls to all sub classes
 * 
 * Part of: BreakoutGame: Assignment #1 for P532
 * 
 * Created By: Ankit Sadana and Harini Rangarajan
 * Created On: 08/27/2015
 * Last Edited By: Ankit Sadana
 * Last Edited On: 08/29/2015
 */

package breakoutGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import java.util.concurrent.TimeUnit;

// Main class
@SuppressWarnings("serial")
public class GameMain extends JPanel {
	
	ArrayList<ArrayList<Bricks>> Brick;
	private int horizontalCount;
	private Paddle myPaddle;
	private Ball ball;
	
	// HEIGHT and WIDTH are used to decide size for Bricks and Paddle 
	public static final int HEIGHT = 600;
	public static final int WIDTH = 720;
	
	// Number of rows of Bricks to be drawn
	public static int numBrickRows = 2;
	
	// timerVar is used to start and stop the timer countdown
	public static Timer timerVar;
	// timeLeft is used to decide the maximum amount of time to be given for the game
	public static int timeLeft = 180;
	
	// GameMain constructor
	public GameMain()
	{
		// Initializing a new Paddle
		myPaddle = new Paddle(400-(Paddle.P_WIDTH/2));;

		// Initializing a new Ball
		ball = new Ball(((myPaddle.getX() + (Paddle.P_WIDTH / 2)) - (Ball.DIAMETER / 2)), 
				(Paddle.yPos - (Ball.DIAMETER)));
		
		// Initializing bricks
		Brick = new ArrayList<ArrayList<Bricks> >();
		horizontalCount = WIDTH / Bricks.BRICK_WIDTH;
		for(int i = 0; i < numBrickRows; ++i){
			ArrayList<Bricks> temp = new ArrayList<Bricks>();
			for(int j = 1; j < horizontalCount; ++j){
				Bricks tempBrickMain = new Bricks((j * Bricks.BRICK_WIDTH), ((i+1) * Bricks.BRICK_HEIGHT));
				temp.add(tempBrickMain);
			}
			Brick.add(temp);
		}
	}
	
	// Drawing all the components
	@Override 
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D graphic = (Graphics2D) g;
		
		// Painting the background Gray
		g.clearRect(0, 0, 800, 800);
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 800, 800);
		
		// Drawing the paddle, ball and bricks
		myPaddle.draw(graphic);
		ball.draw(graphic);
		for(ArrayList<Bricks> row : Brick)
		{
			for(Bricks b : row)
			{
				b.draw(graphic);
			}
		}
	}	


	// Main method of execution
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame();
		GameMain c = new GameMain();
		
		// Defining the Frame
		frame.setTitle("Welcome to the breakout game!");
		frame.pack();
		frame.setSize(800,800);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(c);

		// Confirm on Exit
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we)
			{ 
				// Pause the countdown on confirmation
				timerVar.stop();
				
				String options[] = {"Yes","No"};
		        int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to leave the game?","Breakout Game",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[1]);
		        if(PromptResult==JOptionPane.YES_OPTION)
				{
					System.exit(0);          
				}
				else
				{
					// Resume the countdown if user selects No
					timerVar.start();
				}
			}
		});

		// Centrally aligning the main frame
		frame.setLocationRelativeTo(null);
	
		// Countdown frame
		JFrame clockFrame = new JFrame("Countdown");
		
		// JLabel for clock countdown
		JLabel clockLabel = new JLabel("0", JLabel.CENTER); 
		
		// To-do add score to the game
		
		// Defining the countdown frame
		clockFrame.setSize(300, 200);
		clockFrame.setResizable(false);
		
		//Customizing the clock
		clockLabel.setFont(new Font("Times New Roman", 1, 50));
		clockLabel.setOpaque(true);
		clockLabel.setBackground(Color.black);
		clockLabel.setForeground(Color.red);
	    
		// Setting location of clockFrame on the left side of gameFrame
		clockFrame.setLocation(frame.getX()-300, frame.getY()*2);
		clockFrame.setVisible(true);
		
		// Activating the timerVar every 1000 milliseconds
		timerVar = new Timer(1000, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// Decrease the time left
				timeLeft--;
			
				// Converting seconds into minutes
				long minutes = TimeUnit.SECONDS.toMinutes(timeLeft)
                    - (TimeUnit.SECONDS.toHours(timeLeft) * 60);
				long seconds = TimeUnit.SECONDS.toSeconds(timeLeft)
                    - (TimeUnit.SECONDS.toMinutes(timeLeft) * 60);
				clockLabel.setText(minutes + " : " + seconds);
            
				// Notifying the user that time ran out
				if (timeLeft == 0) {
					JOptionPane.showMessageDialog(null, "Time is up!\n\nSorry, You Lost.", "Game Over", JOptionPane.WARNING_MESSAGE);
                
				}
			}
		});
	
		// Start the timer
		timerVar.start();
	
		clockFrame.add(clockLabel);	    
		clockFrame.setVisible(true);
	

	}

}
