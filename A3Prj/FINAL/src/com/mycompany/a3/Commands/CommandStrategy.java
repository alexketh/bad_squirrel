package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

//Strategy command
public class CommandStrategy extends Command{
    private GameWorld gw;

    public CommandStrategy(GameWorld gw) {
        super("Change Strategy");
        this.gw = gw;
    }

    // actionPerformed() would call changeStrategy() method in GameWorld
    public void actionPerformed( ActionEvent e ){
        gw.changeStrategy();
    }
}
