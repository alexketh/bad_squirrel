package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.table.TableLayout;
import com.mycompany.a2.GameWorld;

// Help command 
public class CommandHelp extends Command
{
	private GameWorld gw;
	
	public CommandHelp(GameWorld gw)
	{
		super ("Help");
		this.gw = gw;
	}
	
	// actionPerformed() would explain the controls
	public void actionPerformed( ActionEvent e )
	{
		Dialog helpBox = new Dialog("Help", new TableLayout(10, 2));
		
		helpBox.add(new Label("Controls/ Command"));
		helpBox.add(new Label("to Accelerate, press a "));
		helpBox.add(new Label("to Brake, press b "));
		helpBox.add(new Label("to make left turn, press l"));
		helpBox.add(new Label("to make right turn,press r "));
		helpBox.add(new Label("to collide with tomato, press e"));
		helpBox.add(new Label("to collide with bird, press g"));
		helpBox.add(new Label("to tick the clock,press t"));
		helpBox.add(new Label("to exit, press x"));
		
		
		Command okCommand = new Command("ok");
		Command c = Dialog.show("", helpBox, okCommand);
		if (c == okCommand) {
			return;
}
	}
}
