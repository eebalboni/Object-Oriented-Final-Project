
/*
 * EnemyTimer Class
 * Authors: Emily Balboni and Humna Hanif 
 * Date: May 3rd 2021
 * SER Final Project: Avoid the Carrots
 * The EnemyTimer class is called to create the enemy object's animation. It calls
 * the update method in the drawing panel class to update the enemy's location. The enemyTimer
 * extends the Timer class and sets the delay along and uses an anonymous action listener class. 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class EnemyTimer extends Timer {
	public EnemyTimer(int delay, DrawingPanel dp, DifficultyHolder holder) {
		super(delay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dp.update();
			}
		});
	}

}



