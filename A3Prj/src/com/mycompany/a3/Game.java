package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.Commands.*;


public class Game extends Form implements Runnable{

	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private UITimer timer = new UITimer(this);
	private boolean isGamePaused = false;

	public Game(){
		gw = new GameWorld(); 		// create Observable GameWorld
		mv = new MapView(); 		// create an Observer for the map
		sv = new ScoreView(gw); 	// create an Observer for the game/player-squirrel
		gw.addObserver(mv); 		// register the map observer
		gw.addObserver(sv); 		// register the score observer
		gw.createSounds();
		gw.init(); 					// initialize world

		this.setLayout(new BorderLayout());
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.CENTER, mv);

		timer.schedule(20, true, this);

		//Accelerate Command
		Command accelerateCommand = new CommandAccelerate(gw);
		Button accelerateButton = new Button("Accelerate");
		accelerateButton.setCommand(accelerateCommand);
		addKeyListener('a', accelerateCommand);

		//Brake Command
		Command brakeCommand = new CommandBrake(gw);
		Button brakeButton = new Button("Brake");
		brakeButton.setCommand(brakeCommand);
		addKeyListener('b', brakeCommand);

		//Left Command
		Command leftCommand = new CommandLeft(gw);
		Button leftButton = new Button("Left");
		leftButton.setCommand(leftCommand);
		addKeyListener('l', leftCommand);

		//Right Command
		Command rightCommand = new CommandRight(gw);
		Button rightButton = new Button("Right");
		rightButton.setCommand(rightCommand);
		addKeyListener('r', rightCommand);

		//Tick Command
		Command tickCommand = new CommandTick(gw);
		Button tickButton = new Button("Tick");
		tickButton.setCommand(tickCommand);
		addKeyListener('t', tickCommand);

		//Exit Command
		Command exitCommand = new CommandExit(gw);
		Button exitButton = new Button("Exit");
		exitButton.setCommand(exitCommand);
		addKeyListener('x', exitCommand);

		//Change Strategy Command
		Command strategyCommand = new CommandStrategy(gw);
		Button strategyButton = new Button("Change Strategy");
		strategyButton.setCommand(strategyCommand);

		//Sound Command
		Command soundCommand = new CommandSound(gw);
		CheckBox soundBox = new CheckBox("Sound");
		soundBox.setCommand(soundCommand);


		//Pause Command
		Command pause = new CommandPause(this);
		Button pauseButton = new Button("Pause");
		pauseButton.setCommand(pause);
		
		//Position Command
		Command position = new CommandPosition(gw, mv);
		Button positionButton = new Button("Position");
		positionButton.setCommand(position);

		//About Command
		Command aboutCommand = new CommandAbout(gw);
		Button aboutButton = new Button("About");
		aboutButton.setCommand(aboutCommand);

		//Help Command
		Command helpCommand = new CommandHelp(gw);
		Button helpButton = new Button("Help");
		helpButton.setCommand(helpCommand);

/*======================== v LEFT CONTAINER  v ========================*/
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

		accelerateButton.getAllStyles().setMarginTop(100);

		buttonSettings(accelerateButton, ColorUtil.WHITE, ColorUtil.BLUE);
		accelerateButton.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		accelerateButton.setText("Accelerate");
		padding(accelerateButton, 4, 7);
		leftContainer.add(accelerateButton);

		buttonSettings(leftButton, ColorUtil.WHITE, ColorUtil.BLUE);
		leftButton.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		leftButton.setText("Left");
		padding(leftButton, 4, 7);
		leftContainer.add(leftButton);

		buttonSettings(strategyButton, ColorUtil.WHITE, ColorUtil.BLUE);
		strategyButton.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		strategyButton.setText("Change Strategy");
		padding(strategyButton, 4, 7);
		leftContainer.add(strategyButton);

		leftContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));

		this.add(BorderLayout.WEST, leftContainer);
/*======================== ^ LEFT CONTAINER  ^ ========================*/

/*======================== v RIGHT CONTAINER  v ========================*/
		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

		brakeButton.getAllStyles().setMarginTop(100);
		buttonSettings(brakeButton, ColorUtil.WHITE, ColorUtil.BLUE);
		brakeButton.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		brakeButton.setText("Brake");
		padding(brakeButton, 7, 7);
		rightContainer.add(brakeButton);

		buttonSettings(rightButton, ColorUtil.WHITE, ColorUtil.BLUE);
		rightButton.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		rightButton.setText("Right");
		padding(rightButton, 7, 7);
		rightContainer.add(rightButton);

		rightContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));

		this.add(BorderLayout.EAST, rightContainer);
/*======================== ^ RIGHT CONTAINER  ^ ========================*/

/*======================== v BOTTOM CONTAINER  v ========================*/
		//Had to use FlowLayout here because when using BoxLayout it set the button on the very left, but I wanted it to be center.
		Container bottomContainer = new Container(new FlowLayout(Component.CENTER));

		
		buttonSettings(positionButton, ColorUtil.WHITE, ColorUtil.BLUE);
		positionButton.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(255, 0, 0)));
		positionButton.setText("Position");
		padding(positionButton, 5, 7);
		bottomContainer.add(positionButton);
		
		buttonSettings(pauseButton, ColorUtil.WHITE, ColorUtil.BLUE);
		pauseButton.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		pauseButton.setText("Pause");
		padding(pauseButton, 5, 7);
		bottomContainer.add(pauseButton);


		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));

		this.add(BorderLayout.SOUTH, bottomContainer);
/*======================== ^ BOTTOM CONTAINER  ^ ========================*/

/*======================== v TOOLBAR v ========================*/
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);

		myToolbar.setTitle("Bad Squirrel!");

		myToolbar.addCommandToRightBar(helpCommand);

		myToolbar.addCommandToSideMenu(accelerateCommand);

		myToolbar.addComponentToSideMenu(soundBox);
		soundBox.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(255, 0, 0)));
		buttonSettings(soundBox, ColorUtil.WHITE, ColorUtil.LTGRAY);
		padding(soundBox, 0, 3);

		myToolbar.addCommandToSideMenu(aboutCommand);

		myToolbar.addCommandToSideMenu(exitCommand);
/*======================== ^ TOOLBAR  ^ ========================*/

		this.show();
		//gw.createSounds();
	}

	public void buttonSettings(Button b, int fgColor, int bgColor) {
		b.getUnselectedStyle().setBgTransparency(255);
		b.getAllStyles().setFgColor(fgColor);
		b.getUnselectedStyle().setBgColor(bgColor);
	}

	public void padding(Button b, int x, int y) {
		b.getAllStyles().setPadding(Component.LEFT, x);
		b.getAllStyles().setPadding(Component.RIGHT, x);
		b.getAllStyles().setPadding(Component.TOP, y);
		b.getAllStyles().setPadding(Component.BOTTOM, y);
	}

	public void pauseGame() {
		if(isGamePaused){
			timer.schedule(20, true, this);
			isGamePaused = false;
			gw.setGameStatus(false);
			if(gw.getSound()) {
				gw.getBackgroundSound().play();
			} else {
				gw.getBackgroundSound().pause();
			}
			//System.out.println("false: game is unpaused");
		} else {
			timer.cancel();
			isGamePaused = true;
			gw.setGameStatus(true);
			gw.getBackgroundSound().pause();
			//BGS.pause();
			//System.out.println("true: game is paused");
		}
	}

	//Tick button is not longer needed
	@Override
	public void run() {
		gw.tick();
	}
}
