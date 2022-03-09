/*
 * ScorePanel Class
 * Authors: Emily Balboni and Humna Hanif 
 * Date: May 3rd 2021
 * SER Final Project: Avoid the Carrots
 * The ScorePanel class displays the countdown clock, the amount of lives left and the quit
 * button on the screen. It extends the JPanel and uses the startHolder to determine when it displays. 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ScorePanel extends JPanel {
	int _score; 
	private boolean isGameOn;
	private StartHolder startHolder;
	private CountDownClock clock;
	private EnemyTimer timer;
	private int points;
	private JLabel score, countDown;
	
	/*
	 * The constructor of the class takes a startHolder object, the animation timer for the enemies and 
	 * the clock object. 
	 */
	public ScorePanel(StartHolder startHolder, EnemyTimer timer, CountDownClock clock) {
		super();
		this.startHolder = startHolder;
		this.timer = timer;
		this.clock = clock;
		points = 3;
		countDown = new JLabel();
		
		Timer updateTimer = new Timer(10,e -> {
			updateTime();
		});
		updateTimer.start();
		
		
		score = new JLabel();
		
		score.setText("Lives: " + points);
		
		/*
		 * Creating the pause button. The text switches between pause and start depending and stops the count down clock,
		 * the enemies from animating until the user presses the button again to restart everything. 
		 */
		JButton pause = new JButton("Pause");
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (pause.getText().equals("Pause")) {
					pause.setText("Play");
					timer.stop();
					
				} else {
					pause.setText("Pause");
					timer.start();
		
				}
				
			}
		});

		/*
		 * Creating the quit button. If the user presses the quit button the program will exit. 
		 */
		JButton quit = new JButton("Quit");
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			 
		 });
		
		/*
		 * Adding the pause button, score and countdown labels to the panel. 
		 */
		this.add(pause);
		this.add(score);
		this.add(countDown);
		this.add(quit);
		
		/*
		 * Putting the game pause/start and quit button to the screen so both of them cannot be pressed at once. 
		 */
		ButtonGroup gameControlers = new ButtonGroup();
		gameControlers.add(pause);
		gameControlers.add(quit);
		
		/*
		 * This sets the focus back to the drawing panel. 
		 */
		pause.setRequestFocusEnabled(false);
		
	}
	
	/*
	 * The update label method updates the amount of lives a user has left on the panel. 
	 */
	public void updateLabel() {
		points = points - 1;
		score.setText("Lives: " + points);
	}
	/*
	 * Updates the clock label to notify the user how much time they have left to play the game as long as the user has 
	 * not won the game yet. 
	 */
	public void updateTime() {
		Date date = new Date(clock.getElapsed());
		countDown.setVisible(!clock.wonGame());
		countDown.setText("Time Left: " + date.getSeconds());
	}

	public int currentLives() {
		return points;
	}
	/*
	 * Returns the amount of lives left. 
	 */
	public int getScore() {
		return _score;
	}
	
}

