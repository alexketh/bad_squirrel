package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

//Brake command 
public class CommandBrake extends Command {
	private GameWorld gw;

	public CommandBrake(GameWorld gw) {
		super ("Brake");
		this.gw = gw;
	}
	
	// actionPerformed() would call brake() method in GameWorld
	public void actionPerformed( ActionEvent e) { 
		gw.brake();
	}
}
