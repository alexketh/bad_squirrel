package com.mycompany.a2;

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
import com.mycompany.a2.Commands.*;

public class Game extends Form {
	
	private GameWorld gw;
	private MapView mv; // new in A2
	private ScoreView sv; // new in A2
	
	public Game(){
		gw = new GameWorld(); 		// create Observable GameWorld
		mv = new MapView(); 		// create an Observer for the map
		sv = new ScoreView(gw); 	// create an Observer for the game/player-squirrel

		gw.addObserver(mv); 		// register the map observer
		gw.addObserver(sv); 		// register the score observer
		gw.init(); 					// initialize world
		
		// Commands
		Command accelerateCommand = new CommandAccelerate(gw);
		Command brakeCommand = new CommandBrake(gw);
		Command leftCommand = new CommandLeft(gw);
		Command rightCommand = new CommandRight(gw);
		Command tomatoCollisionCommand = new CommandTomatoCollision(gw);
		Command birdCollisionCommand = new CommandBirdCollision(gw);
		Command tickCommand = new CommandTick(gw);
		Command exitCommand = new CommandExit(gw);
		Command strategyCommand = new CommandStrategy(gw);
		Command nutCommand = new CommandNutCollision(gw);
		Command npcCommand = new CommandNonPlayerSquirrelCollision(gw);
		Command aboutCommand = new CommandAbout(gw);
		Command soundCommand = new CommandSound(gw);
		Command helpCommand = new CommandHelp(gw);
		
		this.setLayout(new BorderLayout());
		Toolbar myToolbar = new Toolbar();
		this.setToolbar(myToolbar);
		myToolbar.setTitle("Bad Squirrel!");
		myToolbar.setTitleCentered(true);
		
		Button accelerateButton = new Button("Accelerate");
		Button sideAccelerateButton = new Button("Accelerate");
		Button leftButton = new Button("Left");
		Button strategyButton = new Button("Change Strategies");
		Button brakeButton = new Button("Brake");
		Button rightButton = new Button("Right");
		Button npcButton = new Button("Collide with NPC");
		Button nutButton = new Button("Collide with Nut");
		Button tomatoButton = new Button("Collide with Tomato");
		Button birdButton = new Button("Collide with Bird");
		Button tickButton  = new Button(" Tick ");
		Button aboutButton = new Button("About");
		Button helpButton = new Button("Help");
		Button exitButton = new Button("Exit");
		CheckBox soundBox = new CheckBox("Sound");
		
		addKeyListener('a', accelerateCommand);
		addKeyListener('b', brakeCommand);
		addKeyListener('l', leftCommand);
		addKeyListener('r', rightCommand);
		addKeyListener('e', tomatoCollisionCommand);
		addKeyListener('g', birdCollisionCommand);
		addKeyListener('t', tickCommand);
		addKeyListener('x', exitCommand);
		
		Container centerContainer = new Container();
		Container leftContainer = new Container();
		Container rightContainer = new Container();
		Container bottomContainer = new Container(new FlowLayout(Component.CENTER));
		
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.CENTER, mv);
		
		styleCheckBox(soundBox);
		styleButton(accelerateButton);
		styleButton(sideAccelerateButton);
		styleButton(leftButton);
		styleButton(rightButton);
		styleButton(brakeButton);
		styleButton(strategyButton);
		
		styleButton(npcButton);
		styleButton(nutButton);
		styleButton(tomatoButton);
		styleButton(birdButton);
		styleButton(tickButton);
		
		styleButton(aboutButton);
		styleButton(exitButton);
		
		styleContainer(leftContainer);
		styleContainer(rightContainer);
		styleContainer(bottomContainer);
		centerContainer.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.rgb(255, 0, 0)));
		
		// add to toolbar
		myToolbar.addComponentToLeftSideMenu(sideAccelerateButton);
		myToolbar.addComponentToLeftSideMenu(soundBox);
		myToolbar.addComponentToLeftSideMenu(aboutButton);
		myToolbar.addComponentToLeftSideMenu(exitButton);
		myToolbar.addCommandToRightBar(helpCommand);
		
		leftContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.add(accelerateButton);
		leftContainer.add(leftButton);
		leftContainer.add(strategyButton);
		leftContainer.getUnselectedStyle().setPaddingTop(100);
		
		rightContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.add(brakeButton);
		rightContainer.add(rightButton);
		rightContainer.getUnselectedStyle().setPaddingTop(100);
		
		bottomContainer.add(npcButton);
		bottomContainer.add(nutButton);
		bottomContainer.add(tomatoButton);
		bottomContainer.add(birdButton);
		bottomContainer.add(tickButton);
		tickButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		tickButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		tickButton.getAllStyles().setBgTransparency(255);	
		tickButton.getAllStyles().setMarginLeft(5);
		tickButton.getAllStyles().setMarginRight(5);

		this.add(BorderLayout.WEST, leftContainer);
		this.add(BorderLayout.EAST, rightContainer);
		this.add(BorderLayout.SOUTH, bottomContainer);
		this.add(BorderLayout.CENTER, centerContainer);
		
		accelerateButton.setCommand(accelerateCommand);
		leftButton.setCommand(leftCommand);
		strategyButton.setCommand(strategyCommand);

		brakeButton.setCommand(brakeCommand);
		rightButton.setCommand(rightCommand);
		
		npcButton.setCommand(npcCommand);
		nutButton.setCommand(nutCommand);
		tomatoButton.setCommand(tomatoCollisionCommand);
		birdButton.setCommand(birdCollisionCommand);
		tickButton.setCommand(tickCommand);
		exitButton.setCommand(exitCommand);
		soundBox.setCommand(soundCommand);
		sideAccelerateButton.setCommand(accelerateCommand);
		aboutButton.setCommand(aboutCommand);
		helpButton.setCommand(helpCommand);
		
		addKeyListener('a', accelerateCommand);
		addKeyListener('b', brakeCommand);
		addKeyListener('l', leftCommand);
		addKeyListener('r', rightCommand);
		addKeyListener('e', tomatoCollisionCommand);
		addKeyListener('g', birdCollisionCommand);
		addKeyListener('t', tickCommand);
		addKeyListener('x', exitCommand);
		
		this.show();
	}
	
	private void styleContainer(Container c) {
		c.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.GRAY));
	}
	
	private void styleButton(Button b) {
		b.getUnselectedStyle().setBgTransparency(255);
		b.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		b.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		b.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.GRAY));
		b.getUnselectedStyle().setPaddingTop(4);
		b.getUnselectedStyle().setPaddingBottom(4);
		b.getUnselectedStyle().setPaddingLeft(4);
		b.getUnselectedStyle().setPaddingRight(4);
	}
	
	private void styleCheckBox(CheckBox cb) {
		cb.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		cb.getUnselectedStyle().setBgTransparency(255);
		cb.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		cb.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.GRAY));	
	}
}