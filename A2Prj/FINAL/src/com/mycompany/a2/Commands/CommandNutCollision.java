package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class CommandNutCollision extends Command {

	private GameWorld gw;

	public CommandNutCollision(GameWorld gw) {
		super("Collide with Nut");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Command enterCommand = new Command("Confirm");
		TextField emptyText = new TextField();
		Command c = Dialog.show("Enter a Nut number between 1-9", emptyText, enterCommand);
		
		int seq = 1;
		if (c == enterCommand) {
			try {
				seq = Integer.parseInt(emptyText.getText());			
			} catch (Exception ex) {
				Dialog.show("Error", "Enter a Number between 1-9", "OK", null);
				return;
			}

		} 
		
		if (seq > 9 || seq < 1 ) {
			Dialog.show("Error", "Enter a Number between 1-9", "OK", null);
			return;
		}
		
		gw.nutReached(seq);
		
	}
}
