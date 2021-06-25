package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

// Tomato Collision command 
public class CommandTomatoCollision extends Command {
	private GameWorld gw;
	
	public CommandTomatoCollision(GameWorld gw) {
		super ("Collide With Tomato");
		this.gw = gw;
	}
	
	// actionPerformed() would call collision('e') method in GameWorld
	public void actionPerformed( ActionEvent e ) {
		gw.collision('e');
	}
}