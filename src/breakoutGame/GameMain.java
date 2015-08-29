package breakoutGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.Timer;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class GameMain extends JPanel {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ArrayList<Bricks> > Brick;
	private int horizontalCount;
	private Paddle myPaddle;
	private Ball ball;
	public static final int HEIGHT = 600;
	public static final int WIDTH = 720;
	
	public static Timer timerVar;
	public static int timeLeft = 180;
	
	public GameMain(){
		myPaddle = new Paddle(400-(Paddle.P_WIDTH/2));;
		ball = new Ball(((myPaddle.getXPos() + (Paddle.P_WIDTH / 2)) - (Ball.DIAMETER / 2)), 
				(Paddle.Y_POS - (Ball.DIAMETER)));
		
		/*
		 * Drawing bricks in the frame
		 */
		Brick = new ArrayList<ArrayList<Bricks> >();
		horizontalCount = WIDTH / Bricks.BRICK_WIDTH;
		for(int i = 0; i < 2; ++i){
			ArrayList<Bricks> temp = new ArrayList<Bricks>();
			for(int j = 1; j < horizontalCount; ++j){
				Bricks tempBrickMain = new Bricks((j * Bricks.BRICK_WIDTH), ((i+1) * Bricks.BRICK_HEIGHT));
				temp.add(tempBrickMain);
			}
			Brick.add(temp);
		}
	}
		@Override public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.clearRect(0, 0, 800, 800);
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, 800, 800);
			myPaddle.draw(g);
			ball.draw(g);
			for(ArrayList<Bricks> row : Brick){
				for(Bricks b : row){
					b.draw(g);
				}
			}
			}	


public static void main(String[] args) {
	JFrame frame = new JFrame();
	
	GameMain c = new GameMain();
	/* Create the basic Frame */
	frame.setTitle("Welcome to the breakout game!");
	frame.pack();
	frame.setSize(800,800);
	frame.setResizable(false);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(c);
	/* Code for Confirmation On Exit */
	frame.addWindowListener(new WindowAdapter() {
		  @Override
		  public void windowClosing(WindowEvent we)
		  { 
			  timerVar.stop();
			  String ObjButtons[] = {"Yes","No"};
			  int PromptResult = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "Breakout game", 
		        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, 
		        ObjButtons,ObjButtons[1]);
		    if(PromptResult==0)
		    {
		      System.exit(0);          
		    }
		    else
		    {
		    	timerVar.start();
		    }
		  }
		});

	frame.setLocationRelativeTo(null);
	
	JFrame clockFrame = new JFrame("Countdown");
	
	// Creating labels for both frames
	JLabel clockLabel; 
	// To-do add score to the game
		
	clockFrame.setSize(300, 200);
	clockFrame.setResizable(false);
	// Intializing all labels
	clockLabel = new JLabel("0", JLabel.CENTER);
	//Customizing the clock
	clockLabel.setFont(new Font("Times New Roman", 1, 50));
	clockLabel.setOpaque(true);
	clockLabel.setBackground(Color.black);
	clockLabel.setForeground(Color.red);
	    
		
	// Setting location of clockFrame on the left side of gameFrame
	clockFrame.setLocation(frame.getX()-300, frame.getY()*2);
	clockFrame.setVisible(true);
		
	timerVar = new Timer(1000, new ActionListener(){
		
		//Overriding actionPerformed
		public void actionPerformed(ActionEvent e)
		{
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
	
	timerVar.start();
	
	clockFrame.add(clockLabel);	    
	clockFrame.setVisible(true);
	

	}

}
