
/*
 * App Class
 * Authors: Emily Balboni and Humna Hanif 
 * Date: May 3rd 2021
 * SER Final Project: Avoid the Carrots
 * The App class is the main class that runs the program and extends the JFrame. It creates
 * the Frame for the panels to be displayed on and also adds music to the program.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class App extends JFrame {
	private CharacterHolder characterHolder;
	private StartHolder startHolder;
	private DifficultyHolder difficultyHolder;
	private ControlPanel controlPanel;
	private FirstDisplay fd;
	private EnemyHolder enemyHolder;
	private int mainPanelHeight;
	private LifeHolder lifeHolder;
	private DefeatDisplay dd;
	private ScorePanel scorePanel;
	private WinnerDisplay ww;

	/*
	 * The app constructor displays the title "Avoid the Carrots" and sets the size
	 * of the frame. It also creates all of our holders and the winner/loser panels
	 * that will be displayed later on in the game. It uses a border layout to
	 * organize the frame's information.
	 */
	public App() throws IOException, LineUnavailableException {
		super("Avoid the Carrots");
		this.setSize(1000, 800);

		characterHolder = new CharacterHolder();
		startHolder = new StartHolder();
		difficultyHolder = new DifficultyHolder();
		enemyHolder = new EnemyHolder();
		lifeHolder = new LifeHolder();

		dd = new DefeatDisplay();
		ww = new WinnerDisplay();
		fd = new FirstDisplay();

		this.add(fd, BorderLayout.CENTER);

		/*
		 * Adds the control panel to the frame and set's the background color to be
		 * yellow.
		 */
		controlPanel = new ControlPanel(characterHolder, difficultyHolder, fd, this, startHolder, enemyHolder);
		controlPanel.setBackground(Color.yellow);

		this.add(controlPanel, BorderLayout.SOUTH);

		mainPanelHeight = controlPanel.getHeight();

		/*
		 * Adding the music to the game.
		 */
		File musicPath = new File("./sounds/peppaSong.wav");
		try {
			AudioInputStream music = AudioSystem.getAudioInputStream(musicPath);
			Clip clip = AudioSystem.getClip();
			clip.open(music);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setVisible(true);
	}

	/*
	 * The setDrawingPanel method removes the first display from the frame and
	 * creates the DrawingPanel that will display the game. This method adds the
	 * DrawingPanel object to the frame and requests focus so the user is able to
	 * use their mouse and keys during the game.
	 */
	public void setDrawingPanel() {
		this.remove(fd);
		DrawingPanel dp = new DrawingPanel(characterHolder, difficultyHolder, 1000, 500, startHolder, controlPanel,
				enemyHolder, mainPanelHeight, lifeHolder, this);
		this.add(dp, BorderLayout.CENTER);
		dp.requestFocus();
	}

	/*
	 * The setDefeated method adds the defeat display panel that informs the user
	 * that they have lost. It set's this panel to be visible to the user.
	 */
	public void setDefeated() {
		this.add(dd);
		dd.setVisible(true);
	}

	/*
	 * The setScorePanle adds the ScorePanel to the frame when the game is played.
	 * Here is is added to the Frame and it's visibility is set to true.
	 */
	public void setScorePanel(ScorePanel sP) {
		this.scorePanel = sP;
		this.add(scorePanel);
		sP.setVisible(true);
	}

	/*
	 * The setWinner method adds the winner display panel to the frame to inform the
	 * player that they have won. The winner panel object is set to visible here.
	 */
	public void setWinner() {
		this.add(ww);
		dd.setVisible(true);
	}

	/*
	 * The main method starts uses an anonymous class App to begin the game.
	 */
	public static void main(String[] args) throws IOException, LineUnavailableException {
		new App();
	}

}
