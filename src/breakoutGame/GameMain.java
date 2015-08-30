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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import java.util.concurrent.TimeUnit;

// Main class
@SuppressWarnings("serial")
public class GameMain extends JPanel {
	
	public static ArrayList<ArrayList<Bricks>> Brick;
	private int horizontalCount;
	private static Paddle paddleObj;
	private static Ball ballObj;
	
	// HEIGHT and WIDTH are used to decide size for Bricks and Paddle 
	public static final int HEIGHT = 600;
	public static final int WIDTH = 720;
	
	// frameSize defines the size of the frame
	public static final int frameSize = 800;
	
	// bgColor stores the background color of the frame
	public static Color bgColor = Color.GRAY;
	
	// Number of rows of Bricks to be drawn
	public static int numBrickRows = 2;
	
	// timerVar is used to start and stop the timer countdown
	public static Timer timerVar;
	// timeLeft is used to decide the maximum amount of time to be given for the game
	public static int timeLeft = 180;
	//
	public static int timerCheck = 0;
	
	// GameMain constructor
	public GameMain()
	{
		// Initializing a new Paddle
		paddleObj = new Paddle(400-(Paddle.P_WIDTH/2));;

		// Initializing a new Ball
		ballObj = new Ball(((paddleObj.getX() + (Paddle.P_WIDTH / 2)) - (Ball.DIAMETER / 2)), 
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
		g.clearRect(0, 0, frameSize, frameSize);
		g.setColor(bgColor);
		g.fillRect(0, 0, frameSize, frameSize);
		
		// Drawing the paddle, ball and bricks
		paddleObj.draw(graphic);
		ballObj.draw(graphic);
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
		frame.setSize(frameSize,frameSize);
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
		timerVar = new Timer(10, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				if(ballObj.collisionPaddle(paddleObj))
				{
					ballObj.moveUp();
					
					if(Ball.ball.getX() + Ball.ball.getWidth() /2 < Paddle.paddle.getX() + Paddle.P_WIDTH /2)
					{
						System.out.println("Go Left");
						ballObj.moveLeft();
					}
					else
					{
						System.out.println("Go right");
						ballObj.moveRight();
					}
				}
				else if (ballObj.ball.getY() > (Paddle.paddle.getY() + Paddle.P_HEIGHT))
				{
					JOptionPane.showMessageDialog(null, "Ball went under the paddle\n Game Over!", "Game Over", JOptionPane.WARNING_MESSAGE);
					System.exit(0);
				}
				
				if(Brick.size()==0)
				{
					JOptionPane.showMessageDialog(null, "YOU WIN!", "WooHoo", JOptionPane.PLAIN_MESSAGE);
					JOptionPane.showMessageDialog(null, "Game will now close", "You Win!", JOptionPane.PLAIN_MESSAGE);
					System.exit(0);
				}
				else
				{
					if(ballObj.collisionBrick())
					{
						
						//frame.paintComponents(null);
					}
					
				}
				
				
				ballObj.move();
				frame.repaint();
				
				
				if(timerCheck%100 == 0)
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
						JOptionPane.showMessageDialog(null, "Game will now exit", "Game Over", JOptionPane.WARNING_MESSAGE);
						System.exit(0);
						
					}
				}
				
				timerCheck++;
			}
		});
	
		// Start the timer
		timerVar.start();
	
		clockFrame.add(clockLabel);	    
		clockFrame.setVisible(true);
		
		frame.addKeyListener(new KeyAdapter()
				{
					@Override
					public void keyPressed(KeyEvent k) 
					{
						if(k.getKeyCode()==KeyEvent.VK_LEFT)
							paddleObj.moveLeft();

						else if(k.getKeyCode()==KeyEvent.VK_RIGHT)
							paddleObj.moveRight();
						
						frame.repaint();
				
					}
				});
	
		frame.addMouseMotionListener (new MouseMotionAdapter() 
		{
			boolean firstTime = true ;
			int oldX ;
			public void mouseMoved(MouseEvent e) {
			
				if (firstTime)
				{
					oldX = e.getX();
					firstTime = false;
				}
				paddleObj.move(e.getX() - oldX);
				oldX = e.getX();
				frame.repaint();
			}
		}) ;
		
		frame.setFocusable(true);
	}

}
