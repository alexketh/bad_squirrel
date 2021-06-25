package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

//Right command 
public class CommandRight extends Command {
	private GameWorld gw;

	public CommandRight(GameWorld gw) {
		super ("Right");
		this.gw = gw;
	}

	// actionPerformed() would call Right() method in GameWorld
	public void actionPerformed( ActionEvent e ) {
		gw.right();
	}
}