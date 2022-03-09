/*
 * EnemyHolder Class
 * Authors: Emily Balboni and Humna Hanif 
 * Date: May 3rd 2021
 * SER Final Project: Avoid the Carrots
 * The EnemyHolder class is used to pass along how many enemies the user wants 
 * in their game. The default value is set to 10 enemies. 
 */
public class EnemyHolder {
	private int holder;
	
	public EnemyHolder() {
		holder = 10;
	}
	
	/*
	 * Returns the number of enemies
	 */
	public int getState() {
		return holder;
	}
	
	/*
	 * Sets the number of enemies
	 */
	public void setState(int holder) {
		this.holder = holder;
	}
	
}
