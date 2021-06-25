package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

//Accelerate command 
public class CommandAccelerate extends Command {
	private GameWorld gw;

	public CommandAccelerate(GameWorld gw) {
		super ("Accelerate");
		this.gw = gw;
	}

	// actionPerformed() would call accelerate() method in GameWorld
	public void actionPerformed( ActionEvent e ) {
		gw.accelerate();
	}
}