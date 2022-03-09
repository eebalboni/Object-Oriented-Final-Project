/*
 * Enemy Class
 * Authors: Emily Balboni and Humna Hanif 
 * Date: May 3rd 2021
 * SER Final Project: Avoid the Carrots
 * The enemy class creates an enemy object by using an image. It also 
 * has a move enemy method that is called to animate the enemy so it moves
 * within the drawing panel. 
 */


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Enemy {
	private BufferedImage enemyImg;
	private int x, y, width, height, xDirection, yDirection, yMoveSpeed;
	private DrawingPanel dp;

	/*
	 * Constructor of the enemy class takes in an instance of the drawing panel and the x and y location of the enemy.
	 * The enemy objects size is also created. The directions the enemy objects will initially be moving in is also created here. 
	 */
	public Enemy(int x, int y, DrawingPanel dp) {
		this.x = x;
		this.y = y;
		this.dp = dp;
		width = 30;
		height = 15;
		xDirection = 1;
		yDirection = 1;
		yMoveSpeed = (int) (Math.random() * 5);

		/*
		 * Creating the enemy object image by reading in a file. 
		 */
		try {
			enemyImg = ImageIO.read(new File("./images/Carrot.png"));
		} catch (IOException e) {
		}
	}

	/*
	 * Second parameter option for the enemy object but creates an object without the drawingPanel object.
	 */
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		width = 5;
		height = 30;
		try {
			enemyImg = ImageIO.read(new File("./images/Carrot.png"));
		} catch (IOException e) {
		}

	}

	/*
	 * Returns the X location of the enemy. 
	 */
	public int getXLocation() {
		return x;
	}

	/*
	 * Returns the Y location of the enemy.
	 */
	public int getYLocation() {
		return y;
	}

	public void setLocation(int dx, int dy) {
		x = dx;
		y = dy;
	}

	/*
	 * returns the enemy's width
	 */
	public int getWidth() {
		return width;
	}

	/*
	 * returns the enemy's height
	 */
	public int getHeight() {
		return height;
	}

	/*
	 * Paint method is called to draw the image of the enemy. 
	 */
	public void paint(Graphics2D brush) {
		brush.drawImage(enemyImg, x, y, width, height, null);
	}

	/*
	 * The moveEnemy is called to update the location of the enemy object. It takes into 
	 * account the drawingPanel's width and height so the enemy object's stay within the panel.
	 * It also uses the difficultyHolder by calling it so the enemy moves at the appropriate speed. 
	 */
	public void moveEnemy(int dx, int dy) {
		int currentX = getXLocation();
		int currentY = getYLocation();
		setLocation(currentX + (int) (dx * xDirection), currentY + (int) (yMoveSpeed * yDirection));
		if (getXLocation() + getWidth() >= dp.getWidth()) {
			xDirection = (-1);
		} else if (getYLocation() + getHeight() >= dp.getHeight()) {
			yDirection = (-1);
		} else if (getXLocation() <= 0) {
			xDirection = 1;
		} else if (getYLocation() <= 0) {
			yDirection = 1;
		}
	}
}
