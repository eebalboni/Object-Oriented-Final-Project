/*
 * WinnerDisplay Class
 * Authors: Emily Balboni and Humna Hanif 
 * Date: May 3rd 2021
 * SER Final Project: Avoid the Carrots
 * The winner display class extends the JPanel and displays an image as the background 
 * and is created to let the player know they have won the game. 
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

public class WinnerDisplay extends JPanel {
	private BufferedImage winner;
	private JButton quit;

	/*
	 * The constructor takes no parameters but creates the image object and adds a
	 * quit option for the panel so the user can exit the game.
	 */
	public WinnerDisplay() throws IOException {
		this.setPreferredSize(getPreferredSize());
		winner = ImageIO.read(new File("./Images/Winner.jpg"));
		JPanel quitPanel = new JPanel();
		this.add(quitPanel, BorderLayout.SOUTH);
		quit = new JButton("Quit");
		quitPanel.add(quit);
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});

	}

	/*
	 * Paint method displays the background image at the desired location.
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;
		brush.drawImage(winner, 0, 0, getWidth(), getHeight(), null);
	}
}
