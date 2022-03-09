/*
 * ControlPanel Class
 * Authors: Emily Balboni and Humna Hanif 
 * Date: May 3rd 2021
 * SER Final Project: Avoid the Carrots
 * The ControlPanel class displays the buttons used at the beginning of the game that 
 * gives the user the option to determine the character, number of enemies, difficulty level and the 
 * start button. It uses the holder pattern to set the holders of the number of enemies, difficulty level and 
 * the character that will be used in the drawingPanel class to display the correct objects on the panel. It
 * also creates panels for each of the buttons. Once a user chooses a character they cannot change their choice.
 * Once the user presses start, they also no longer have access to the control panel as it becomes disabled. 
 * This class also uses an anonymous class for the change listener for 
 * the JSlider. 
 * 
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JPanel {
	private JButton pig, elephant, cat, easy, normal, difficult, start;
	private JSlider numEnemies;
	private CharacterHolder avatarHolder;
	private DifficultyHolder difficultyHolder;
	private FirstDisplay fd;
	private App app;
	private StartHolder startHolder;
	private EnemyHolder enemyHolder;
	private JLabel sliderVal;
	private int enemyTotal;
	private boolean avatarSelected, difficultySelected;

	/*
	 * The constructor takes in the holders this class needs to update which
	 * includes the characterHolder, difficultyHolder, the startHolder and the
	 * enemyHolder.
	 */
	public ControlPanel(CharacterHolder characterHolder, DifficultyHolder difficultyHolder, FirstDisplay fd, App app,
			StartHolder startHolder, EnemyHolder enemyHolder) {
		super();
		this.avatarHolder = characterHolder;
		this.fd = fd;
		this.app = app;
		this.difficultyHolder = difficultyHolder;
		this.startHolder = startHolder;
		this.enemyHolder = enemyHolder;

		avatarSelected = false;
		difficultySelected = false;

		/*
		 * Adding a JPanel that will have a GridLayout with all the button rows
		 * (characters, difficulty level, enemy count and the start button. The
		 * background is set to yellow.
		 */
		JPanel controls = new JPanel(new GridLayout(4, 1));
		this.setBackground(Color.yellow);

		/*
		 * Creating character button objects with appropriate labels.
		 */
		pig = new JButton("Peppa Pig");
		cat = new JButton("Candy Cat");
		elephant = new JButton("Mr.Elephant");

		/*
		 * Action listener for pig. If pressed the characterHolder is updated to set the
		 * state to be "Pig". It updates the character boolean selected to be true and
		 * disables all other buttons.
		 */
		pig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avatarHolder.setState("Pig");
				pig.setEnabled(false);
				cat.setEnabled(false);
				elephant.setEnabled(false);

				elephant.setVisible(false);
				pig.setVisible(false);
				cat.setVisible(false);
				avatarSelected = true;
			}
		});

		/*
		 * The action listener for the cat button. If pressed the characterHolder's
		 * state will be set to "Cat". Also disables all other character buttons.
		 */
		cat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avatarHolder.setState("Cat");
				pig.setEnabled(false);
				cat.setEnabled(false);
				elephant.setEnabled(false);
				elephant.setVisible(false);
				pig.setVisible(false);
				cat.setVisible(false);
				avatarSelected = true;
			}
		});

		/*
		 * The action listener for the elephant button. If pressed the characterHolder's
		 * state will be set to "Elephant". Also disables all other character buttons.
		 */
		elephant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avatarHolder.setState("Elephant");
				pig.setEnabled(false);
				cat.setEnabled(false);
				elephant.setEnabled(false);
				// was planning on putting this up above
				elephant.setVisible(false);
				pig.setVisible(false);
				cat.setVisible(false);
				avatarSelected = true;
			}

		});

		/*
		 * Creating a button group for the character options. Adding the characters to
		 * the button group.
		 */
		ButtonGroup avatars = new ButtonGroup();
		avatars.add(pig);
		avatars.add(cat);
		avatars.add(elephant);

		/*
		 * Creating difficulty JButton objects with the appropriate labels.
		 */
		easy = new JButton("Easy");
		normal = new JButton("Normal");
		difficult = new JButton("Difficult");

		/*
		 * The action listener for the easy button. If pressed the difficultyHolder's
		 * state will be set to "Easy". Also disables all other difficulty buttons.
		 */
		easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficultyHolder.setState(1);
				easy.setEnabled(false);
				normal.setEnabled(false);
				difficult.setEnabled(false);
				easy.setVisible(false);
				normal.setVisible(false);
				difficult.setVisible(false);
				difficultySelected = true;
			}

		});

		/*
		 * The action listener for the easy button. If pressed the difficultyHolder's
		 * state will be set to "Normal". Also disables all other difficulty buttons.
		 */
		normal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficultyHolder.setState(2);
				easy.setEnabled(false);
				normal.setEnabled(false);
				difficult.setEnabled(false);

				easy.setVisible(false);
				normal.setVisible(false);
				difficult.setVisible(false);
				difficultySelected = true;
			}

		});

		/*
		 * The action listener for the easy button. If pressed the difficultyHolder's
		 * state will be set to "Difficult". Also disables all other difficulty buttons.
		 */
		difficult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficultyHolder.setState(3);
				easy.setEnabled(false);
				normal.setEnabled(false);
				difficult.setEnabled(false);

				easy.setVisible(false);
				normal.setVisible(false);
				difficult.setVisible(false);
				start.setVisible(true);
				difficultySelected = true;
			}
		});

		/*
		 * Creating button group for the difficulty buttons. Adding the difficulty
		 * buttons to the button group.
		 */
		ButtonGroup difficultyBG = new ButtonGroup();
		difficultyBG.add(easy);
		difficultyBG.add(normal);
		difficultyBG.add(difficult);

		/*
		 * Creating a JSlider to display the user's enemy count options.
		 */
		JLabel sliderLbl = new JLabel("Choose the number of enemies: ");
		numEnemies = new JSlider(5, 15);
		numEnemies.setPaintTrack(true);
		numEnemies.setPaintTicks(true);
		numEnemies.setPaintLabels(true);

		numEnemies.setMajorTickSpacing(5);
		numEnemies.setMinorTickSpacing(1);

		sliderVal = new JLabel();
		sliderVal.setText("Number of Enemies: " + numEnemies.getValue());
		enemyTotal = numEnemies.getValue();

		/*
		 * Using an anonymous class for the changeListener for the JSlider. Get's the
		 * value from the JSlider and updates the JLabel so the user is aware of how
		 * many enemies they selected. Sets the state of the enemyHolder to be the
		 * number of enemies the user chooses.
		 */
		numEnemies.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				enemyTotal = numEnemies.getValue();
				sliderVal.setText("Number of Enemies: " + numEnemies.getValue());
				enemyHolder.setState(enemyTotal);
			}

		});

		/*
		 * Creating the start button and an ActionListener for it through also using an
		 * anonymous class. If both the avatar and the difficulty level is selected than
		 * the game will start. The action listener updates the StartHolder, disables
		 * the first display panel and enables the drawing panel by calling the
		 * setDrawingPanel method in the app class. It set's the start visibility to
		 * false and disables the mainPanel so the user no longer can access it.
		 */
		start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (avatarSelected && difficultySelected) {
					numEnemies.setEnabled(false);
					startHolder.setState(true);
					start.setEnabled(false);
					start.setVisible(false);

					fd.setVisible(false);
					disableControlPanel();
					app.setDrawingPanel();
				}

			}
		});

		/*
		 * Creating a panel for the character buttons and adding them.
		 */
		JPanel avatarRow = new JPanel();
		avatarRow.add(pig);
		avatarRow.add(cat);
		avatarRow.add(elephant);

		/*
		 * Creating a panel for the difficulty buttons and adding them.
		 */
		JPanel difficultyRow = new JPanel();
		difficultyRow.add(easy);
		difficultyRow.add(normal);
		difficultyRow.add(difficult);

		/*
		 * Creating a panel for the number of enemies slider and adding them.
		 */
		JPanel sliderRow = new JPanel();
		sliderRow.add(sliderLbl);
		sliderRow.add(numEnemies);
		sliderRow.add(sliderVal);

		/*
		 * Creating a panel for the start button and adding them.
		 */
		JPanel startRow = new JPanel();
		startRow.add(start);

		/*
		 * Adding each individual panel to the control panel.
		 */
		controls.add(avatarRow);
		controls.add(difficultyRow);
		controls.add(sliderRow);
		controls.add(startRow);

		this.add(controls);

	}

	/*
	 * The disableMainPanel disables every button in the control panel so the user
	 * accidently clicking the panel does not effect the game.
	 */
	public void disableControlPanel() {
		pig.setEnabled(false);
		cat.setEnabled(false);
		elephant.setEnabled(false);
		easy.setEnabled(false);
		normal.setEnabled(false);
		difficult.setEnabled(false);
		numEnemies.setEnabled(false);
	}

}
