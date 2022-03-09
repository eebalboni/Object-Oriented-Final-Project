/*
 * DrawingPanel Class
 * Authors: Emily Balboni and Humna Hanif 
 * Date: May 3rd 2021
 * SER Final Project: Avoid the Carrots 
 * The DrawingPanel class displays the game in the middle of the panel. It extends the JPanel 
 * and implements the action listener. It checks for collision of the enemy and the character 
 * is where the key listeners and mouse listener are located. 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class DrawingPanel extends JPanel implements ActionListener {
	private ArrayList<Enemy> enemyList = new ArrayList<>();
	private int  _x, _y, prevX, prevY;
	private EnemyTimer animationTimer;
	private Character character;
	private CharacterHolder characterHolder;
	private DifficultyHolder difficultyHolder;
	private StartHolder startHolder;
	private ControlPanel controlPanel;
	private EnemyHolder enemyHolder;
	private LifeHolder lives;
	private App app;
	private ScorePanel scorePanel;
	private CountDownClock clock;
	private boolean isDragging;
	private BufferedImage bg;

	/*
	 * The constructor takes in the holders for the character, difficulty level,
	 * start, amount of enemies and lives. It also takes the width and height of the
	 * other panels and takes an instance of the App class. The background of the 
	 * drawing panel is an image. 
	 */
	public DrawingPanel(CharacterHolder avatarHolder, DifficultyHolder difficultyHolder, int dpWidth, int dpHeight,
			StartHolder startHolder, ControlPanel mainPanel, EnemyHolder enemyHolder, int mpHeight, LifeHolder lives,
			App app) {
		super();
		this.characterHolder = avatarHolder;
		this.difficultyHolder = difficultyHolder;
		this.startHolder = startHolder;
		this.controlPanel = mainPanel;
		this.enemyHolder = enemyHolder;
		this.lives = lives;
		this.app = app;
		
		try {
			bg = ImageIO.read(new File("./Images/firstBackground.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		/*
		 * Creating the EnemyTimer object that will animate the enemy objects. 
		 */
		animationTimer = new EnemyTimer(5, this, difficultyHolder);

		/*
		 * Begins the animation if the start button has been pressed. 
		 */
		if (startHolder.getState()) {
			animationTimer.start();
		} else {
			animationTimer.stop();
		}

		/*
		 * Creates the count down clock object and the score panel. 
		 */
		clock = new CountDownClock();
		scorePanel = new ScorePanel(startHolder, animationTimer, clock);

		/*
		 * Sets the size of the drawing panel and places the score board to the 
		 * north of the panel using a border layout. 
		 */
		this.setSize(dpWidth, dpHeight);
		this.add(scorePanel, BorderLayout.NORTH);

		/*
		 * Creating an enemy list using the holder for the size of the arrayList as the amount 
		 * of enemies. Calls the getRandY method to get random Y values for the enemies
		 * to be drawn with. 
		 */
		for (int i = 0; i < enemyHolder.getState(); i++) {
			enemyList.add(new Enemy(0, getRandY(), this));
		}

		/*
		 * Creates the character object using the character holder. 
		 */
		if (avatarHolder.getState().equals("Pig") || avatarHolder.getState().equals("Elephant")
				|| avatarHolder.getState().equals("Cat")) {
			character = new Character(avatarHolder, 700, 100, 100, 100);
		}

		/*
		 * This mouse listener checks to see if the drawing panel has been clicked on, specifically checking 
		 * to see if the character has been clicked on. If it has it gets the previous x and y and sets the dragging 
		 * boolean to true. If the dragging boolean is false nothing happens, if it's true then the character moves up and down by adding the 
		 * previous x to the new x location and same for y and resetting the character location. The repaint method is called after the 
		 * setLocation method has been called. 
		 */
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if ((e.getX() < character.getWidthAvatar() + character.getXLocation() && e.getX() > character.getXLocation())
						&& (e.getY() > character.getYLocation()
								&& e.getY() < character.getYLocation() + character.getHeightAvatar())) {
					prevX = e.getX();
					prevY = e.getY();
					isDragging = true;
				}
			}

			public void mouseReleased(MouseEvent e) {
				isDragging = false;
			}

		});

		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {

				if (isDragging) {
					if (character.getYLocation() < 0) {
						character.setLocation(character.getXLocation(), 0);
					} else if (character.getYLocation() + character.getHeightAvatar() > getHeight()) {
						character.setLocation(character.getXLocation(), getHeight() - character.getHeightAvatar());
					}
					int dx = 0;
					int dy = e.getY() - prevY;
					int newX = character.getXLocation() + dx;
					int newY = character.getYLocation() + dy;
					character.setLocation(newX, newY);
					prevX = e.getX();
					prevY = e.getY();
				}
			}
		});

		/*
		 * The keyListeners are used for the up and down arrow keys. If the up or down arrows are pressed then the 
		 * character will move up or down by 5 pixels. The panel is also repainted. 
		 */
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					character.setLocation(character.getXLocation(), character.getYLocation() - 10);
					if (character.getYLocation() < 0) {
						character.setLocation(character.getXLocation(), 0);
					}
					repaint();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					character.setLocation(character.getXLocation(), character.getYLocation() + 10);
					if (character.getYLocation() + character.getHeightAvatar() > getHeight()) {
						character.setLocation(character.getXLocation(), getHeight() - character.getHeightAvatar());
					}
					repaint();
					System.out.println(character.getXLocation());
					System.out.println(character.getYLocation());
				}
			}

		});

		/*
		 * Requests focus in the main panel and requests focus. 
		 */
		this.setFocusable(true);
		requestFocusInWindow();
	}

	/*
	 * The update method traverses through the Enemy array list and calls the move
	 * method on all of the enemy objects. It also checks to see if any of the
	 * carrot objects have collided with the character. If a carrot object has, it
	 * adds that instance to the deadEnemies arraylist so the array no longer
	 * appears on the screen. It also updates the amount of lives the user has left.
	 * It also checks to see if the user has no lives left. If this is true the
	 * drawing panel visibility is set to false and the defeated display will call.
	 * If the user has lives left and the timer clock runs out, the winner display
	 * panel is shown to the user. The control panel's visibility is also set to
	 * false. The animation timer stops as well.
	 */
	public void update() {
		ArrayList<Enemy> deadEnemies = new ArrayList<Enemy>();
		for (Enemy enemy : enemyList) {
			enemy.moveEnemy(difficultyHolder.getState(), difficultyHolder.getState());

			if (checkCollision(enemy)) {
				scorePanel.updateLabel();
				deadEnemies.add(enemy);
				if (scorePanel.currentLives() == 0) {
					this.setVisible(false);
					app.setDefeated();
					animationTimer.stop();
					controlPanel.setVisible(false);
				}
			}
		}
		/*
		 * Adding dead enemies to the dead enemy array list.
		 */
		for (Enemy enemy : deadEnemies) {
			enemyList.remove(enemy);
		}

		/*
		 * Checking to see if the clock has stopped running to display winner panel.
		 */
		if (clock.wonGame()) {
			this.setVisible(false);
			app.setWinner();
			animationTimer.stop();
			controlPanel.setVisible(false);
		}

		/*
		 * Updating the clock and repainting the panel every time.
		 */
		clock.updateTimer();
		repaint();
	}

	/*
	 * Draws the drawingPanel screen displaying the carrots and the character. The
	 * carrots are drawn using a for loop.
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;
		brush.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
		character.paint(brush);
		for (int i = 0; i < enemyList.size(); i++) {
			enemyList.get(i).paint(brush);
		}
	}

	public void actionPerformed(ActionEvent e) {
	}

	/*
	 * getRandY returns a random value that takes into account the drawingPanel's
	 * height.
	 */
	public int getRandY() {
		int rand = (int) (Math.random() * getHeight());
		return rand;
	}

	/*
	 * Checks to see if the current character has collided with a carrot by checking
	 * all sides. Returns a boolean value.
	 */
	public boolean checkCollision(Enemy enemy) {
		return (enemy.getXLocation() + enemy.getWidth() >= character.getXLocation()
				&& enemy.getXLocation() <= character.getXLocation() + character.getWidthAvatar())
				&& (enemy.getYLocation() + enemy.getHeight() >= character.getYLocation()
						&& enemy.getYLocation() <= character.getYLocation() + character.getHeightAvatar());
	}
}
