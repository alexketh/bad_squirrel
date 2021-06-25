package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

//drone collision command 
public class CommandNonPlayerSquirrelCollision extends Command {
	private GameWorld gw;
	
	public CommandNonPlayerSquirrelCollision(GameWorld gw) {
		super ("Collide With NPC");
		this.gw = gw;
	}
	
	// actionPerformed() would call npcCollision() method in GameWorld
	public void actionPerformed( ActionEvent e ) {
		gw.npcCollision();
	}
}