package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.table.TableLayout;
import com.mycompany.a3.GameWorld;

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

		helpBox.add(new Label("Command | 	Controls"));
		helpBox.add(new Label("Accelerate			|	a"));
		helpBox.add(new Label("Brake				|	b"));
		helpBox.add(new Label("Turn Left			|	l"));
		helpBox.add(new Label("Turn Right			|	r"));
		helpBox.add(new Label("Collide with Tomato	|	e"));
		helpBox.add(new Label("Collide with Bird	|	g"));
		helpBox.add(new Label("Tick				|	t"));
		helpBox.add(new Label("Exit				|	x"));


		Command okCommand = new Command("ok");
		Command c = Dialog.show("", helpBox, okCommand);
		if (c == okCommand) {
			return;
}
	}
}
