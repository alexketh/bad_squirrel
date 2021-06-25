package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

// Tick Command 
public class CommandTick extends Command {
	private GameWorld gw;
	
	public CommandTick(GameWorld gw) {
		super ("Tick");
		this.gw = gw;
	}
	
	// actionPerformed() would call tick() method in GameWorld
	public void actionPerformed( ActionEvent e ) {
		gw.tick();
	}
}
