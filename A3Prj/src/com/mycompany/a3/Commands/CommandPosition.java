package com.mycompany.a3.Commands;

/*
 * The command for the ant braking.
 *
 * @author Matthew Mendoza
 */

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.MapView;

public class CommandPosition extends Command{

    private GameWorld gw;
    private MapView mv;

    public CommandPosition(GameWorld gw, MapView mv) {
        super("Position");
        this.gw = gw;
        this.mv = mv;
    }

    public void actionPerformed(ActionEvent evt) {
        gw.setPositionClickStatus(true);
        mv.setSelectBoolean(false);
    }
}
