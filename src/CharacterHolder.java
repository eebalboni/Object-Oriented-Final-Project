
/*
* CharacterHolder Class
* Authors: Emily Balboni and Humna Hanif 
* Date: May 3rd 2021
* SER Final Project: Avoid the Carrots
* The Character Holder is used by the game in a holder pattern to 
* inform the other classes which character the user wishes to play the 
* game with. It originally sets the state of the character to be null. 
* The state of the character is being stored as a string. 
*/

public class CharacterHolder {
	private String state;

	/*
	 * Constructor of the CharacterHolder set's the default character to be null.
	 */
	public CharacterHolder() {
		setState("Null");
	}

	/*
	 * Setting the state of the character to be of type String.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/*
	 * Returns the state of the character.
	 */
	public String getState() {
		return state;
	}

}
