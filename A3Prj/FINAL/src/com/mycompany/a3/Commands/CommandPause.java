package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;

public class CommandPause extends Command {

    private Game g;

    public CommandPause(Game g) {
        super("Pause");
        this.g = g;
    }

    public void actionPerformed(ActionEvent evt) {
        g.pauseGame();
    }
}