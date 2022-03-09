/*
 * CountDownClock Class
 * Authors: Emily Balboni and Humna Hanif 
 * Date: May 3rd 2021
 * SER Final Project: Avoid the Carrots
 * The CountDownClock class uses Date and Timer to create a 
 * count down clock for the game.  
 */

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CountDownClock extends Timer {
	private long timeLimit;
	private boolean lost = false;

	/*
	 * Creating the timeLimit for the game, default time is set to 10 seconds.
	 */
	public CountDownClock() {
		timeLimit = 20 * 1000;
	}

	/*
	 * wonGame determines whether or not the user has won the game. If they have not
	 * been hit by three enemies and this is true they have won the game.
	 */
	public boolean wonGame() {
		return timeLimit < 0;
	}

	/*
	 * Time elapsed returns the amount of time the game has been occuring for.
	 */
	public long getElapsed() {
		return timeLimit;
	}

	/*
	 * This method updates the timer every second.
	 */
	public void updateTimer() {
		timeLimit = timeLimit - 5;
	}

}
