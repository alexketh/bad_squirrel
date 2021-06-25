package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

// Bird Collision command 
public class CommandBirdCollision extends Command {
	private GameWorld gw;
	
	public CommandBirdCollision(GameWorld gw) {
		super ("Collide With Bird");
		this.gw = gw;
	}
	
	// actionPerformed() would call collision('g') method in GameWorld
	public void actionPerformed( ActionEvent e ) {
		gw.collision('g');
	}
}