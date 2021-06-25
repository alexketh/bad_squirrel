package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

//left command 
public class CommandLeft extends Command {
	private GameWorld gw;

	public CommandLeft(GameWorld gw) {
		super ("Left");
		this.gw = gw;
	}
	
	// actionPerformed() would call left() method in GameWorld
	public void actionPerformed( ActionEvent e ) {
		gw.left();
	}
}
