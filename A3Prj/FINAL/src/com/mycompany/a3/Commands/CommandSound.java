package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

//Sound command
public class CommandSound extends Command {
	private GameWorld gw;

	public CommandSound(GameWorld gw) {
		super ("Sound");
		this.gw = gw;
	}

	// actionPerformed() would call sound() method in GameWorld
	public void actionPerformed( ActionEvent e ) {
		gw.toggleSound();
	}
}
