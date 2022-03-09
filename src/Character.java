/*
 * Character Class
 * Authors: Emily Balboni and Humna Hanif 
 * Date: May 3rd 2021
 * SER Final Project: Avoid the Carrots
 * The Character class creates the character object that the user 
 * chooses to either be Peppa Pig, Candy Cat or Mr. Elephant. This 
 * class uses Images to display the characters. 
 */

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Character {
	private int x, y, width, height;
	private BufferedImage image;
	private String avatarName;

	/*
	 * The constructor of the Character class takes in a holder that informs this
	 * class which character should be drawn on the screen. It also takes in the
	 * images desired width, height and where it should be drawn on the screen.
	 */
	public Character(CharacterHolder holder, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		avatarName = holder.getState();

		/*
		 * Determining which character the class should draw by using a switch case to
		 * determine which file to call to draw the correct image.
		 */
		switch (avatarName) {
		case "Pig":
			try {
				image = ImageIO.read(new File("./Images/peppa.png"));
			} catch (IOException e1) {
			}
			break;

		case "Elephant":
			try {
				image = ImageIO.read(new File("./Images/elephant.png"));
			} catch (IOException e1) {
			}
			break;

		case "Cat":
			try {
				image = ImageIO.read(new File("./Images/cat.png"));
			} catch (IOException e1) {
			}
			break;
		}
	}

	/*
	 * Returning the character's height.
	 */
	public int getHeightAvatar() {
		return height;
	}

	/*
	 * Returning the character's width.
	 */
	public int getWidthAvatar() {
		return width;
	}

	/*
	 * Painting the character on the screen.
	 */
	public void paint(Graphics2D brush) {
		brush.drawImage(image, x, y, width, height, null);
	}

	/*
	 * Setting the location of the character.
	 */
	public void setLocation(int dx, int dy) {
		this.x = dx;
		this.y = dy;
	}

	/*
	 * Returning the X location of the character.
	 */
	public int getXLocation() {
		return this.x;
	}

	/*
	 * Returning the Y location of the character.
	 */
	public int getYLocation() {
		return this.y;
	}

}
