/*
 * DifficultyHolder Class
 * Authors: Emily Balboni and Humna Hanif 
 * Date: May 3rd 2021
 * SER Final Project: Avoid the Carrots
 * The difficultyHolder is used as a part of the holder pattern. It set's the 
 * state of the difficult as an integer. 1 for easy, 2 for normal and 3 for difficult.  
 */

public class DifficultyHolder {
	private int state;

	/*
	 * The state's default value is set to easy.
	 */
	public DifficultyHolder() {
		state = 1;
	}

	/*
	 * Setting the state to the parameter passed.
	 */
	public void setState(int difficulty) {
		this.state = difficulty;
	}

	/*
	 * Returning the state.
	 */
	public int getState() {
		return state;
	}
}
