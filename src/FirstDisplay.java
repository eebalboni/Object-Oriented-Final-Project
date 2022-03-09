/*
 * FirstDisplay Class
 * Authors: Emily Balboni and Humna Hanif 
 * Date: May 3rd 2021
 * SER Final Project: Avoid the Carrots
 * The FirstDisplay class extends the JPanel and creates a background 
 * using an image. It displays all three character options that a user can 
 * choose from and displays the game instructions. 
 */


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class FirstDisplay extends JPanel{
	private int x, y, pigWidth, pigHeight, catWidth, catHeight, elWidth, elHeight;
	private BufferedImage pig, cat, el, gameLogo, background;
	private JLabel instructions;
	
	
	public FirstDisplay() throws IOException {
		this.setSize(getPreferredSize());
		
		 pig = ImageIO.read(new File("./Images/peppa.png"));
		 el = ImageIO.read(new File("./Images/elephant.png"));
		 cat = ImageIO.read(new File("./Images/cat.png"));
		 
		 gameLogo = ImageIO.read(new File("./Images/Logo.png"));
		 background = ImageIO.read(new File("./Images/firstBackground.jpg"));
		 
		 x = 20;
		 y = 200;
		 
		 pigWidth = 310;
		 pigHeight = 250;
		 
		 catWidth = 200;
		 catHeight = 275;
		 
		 elWidth = 300;
		 elHeight = 300;
		 
		 instructions = new JLabel("Help Peppa and her friends dodge the carrots");
		 this.add(instructions);
 	}
	
	/*
	 * The paintComponent method is used to paint the backgound on the screen, including the logo and the 
	 * file image. It also displays all three characters. 
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); 
		Graphics2D brush = (Graphics2D) g; 
		brush.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		brush.drawImage(gameLogo, 200, -70, 600, 300, null);
		brush.drawImage(pig, x , y, pigWidth, pigHeight, null);
		brush.drawImage(cat, x + 396 , y - 10, catWidth, catHeight, null);
		brush.drawImage(el, x + 660, y - 15, elWidth, elHeight, null);
	}
}
