package com.mycompany.a2;

import com.codename1.util.MathUtil;
import com.mycompany.a2.GameObjects.*;
import com.mycompany.a2.Interfaces.*;
import java.lang.Math;

public class NutStrategy implements IStrategy {
	private NonPlayerSquirrel npc;
	private Nut nut;

	public NutStrategy(GameObjectCollection list, NonPlayerSquirrel npc) {
		this.npc = npc;
		IIterator itr = list.getIterator();
		while(itr.hasNext()) {
			GameObject go = (GameObject) itr.getNext();
			if(go instanceof Nut) {
				if( ((Nut) go).getSequenceNumber() == (npc.getLastNutReached() + 1) ) {
					nut = ((Nut) go);
					break;
				}
				else {
					nut = ((Nut) go);
				}
			}				
		}
	}

	@Override
	public void apply() {
		float temp_x = nut.getLocation().getX() - npc.getLocation().getX();
		float temp_y = nut.getLocation().getY() - npc.getLocation().getY();
		float newAngle = (float)(90 -  Math.toDegrees(MathUtil.atan2((double)temp_x,(double)temp_y)));
		int angle = 90 - (int)newAngle;
		npc.setHeading(npc.getSteeringDirection() + angle);
	}
}
