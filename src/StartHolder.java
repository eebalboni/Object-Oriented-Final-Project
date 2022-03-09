/*
 * StartHolder Class
 * Authors: Emily Balboni and Humna Hanif 
 * Date: May 3rd 2021
 * SER Final Project: Avoid the Carrots
 * The StartHolder class uses the holder pattern and is of type boolean. It holds 
 * whether or not the user has clicked the start button. There is no default value. 
 */
public class StartHolder {
	private Boolean state;

	public StartHolder() {
	}

	/*
	 * Sets the state of the holder.
	 */
	public void setState(boolean start) {
		this.state = start;
	}

	/*
	 * Returns the state of the holder.
	 */
	public Boolean getState() {
		return state;
	}
}


