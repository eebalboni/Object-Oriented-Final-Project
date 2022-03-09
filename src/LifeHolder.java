/*
 * LifeHolder Class
 * Authors: Emily Balboni and Humna Hanif 
 * Date: May 3rd 2021
 * SER Final Project: Avoid the Carrots
 * The LifeHolder class is using the holder pattern to store how many lives the user has left. 
 * The amount of lives the user begins with is 3 and decrements each time there is a collision with the 
 * character and enemy. 
 */
public class LifeHolder {
	private int lives;

	public LifeHolder() {
		lives = 3;
	}
	
	/*
	 * Sets the amount of lives to be decremented by 1
	 */
	public void setLife() {
		lives = lives - 1;
	}
	
	/*
	 * Returns the number of lives left
	 */
	public int getLives() {
		return lives;
	}
}
