/*
 * DefeatDisplay Class
 * Authors: Emily Balboni and Humna Hanif 
 * Date: May 3rd 2021
 * SER Final Project: Avoid the Carrots
 * The defeatDisplay class extends the JPanel and uses an image as a background 
 * that informs the user they have lost. It uses a border layout and uses a quit button 
 * that let's the user exit the game. 
 */

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DefeatDisplay extends JPanel {
	private BufferedImage loser;
	private JButton quit;

	public DefeatDisplay() throws IOException {

		this.setPreferredSize(getPreferredSize());
		loser = ImageIO.read(new File("./Images/Defeated.jpg"));

		/*
		 * Creating a panel for the quit button. Also creating an actionListener so when
		 * the button is clicked the program is exited.
		 */
		JPanel update = new JPanel();
		this.add(update, BorderLayout.SOUTH);
		quit = new JButton("Quit");
		update.add(quit);
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}

	/*
	 * The paintComponent method is used to draw the image on the screen that
	 * displays you lost.
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;
		brush.drawImage(loser, 0, 0, getWidth(), getHeight(), null);
	}
}
