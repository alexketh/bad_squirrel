package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

//Exit command 
public class CommandExit extends Command {
	private GameWorld gw;
	
	public CommandExit(GameWorld gw) {
		super ("Exit");
		this.gw = gw;
	}
	
	// actionPerformed() would call exit() method in GameWorld
	public void actionPerformed( ActionEvent e ) {
		Command yes = new Command("Yes");
		Command  no = new Command("No");
		
		Label l1 = new Label("");
		
		Command c = Dialog.show("Are you sure you want to exit", l1, yes, no);
		
		if(c == yes) {
			gw.quit();
		} else if (c == no) {
			return;
		}
	}
}
