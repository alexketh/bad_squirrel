package com.mycompany.a3;

import com.mycompany.a3.GameObjects.*;
import com.mycompany.a3.Interfaces.*;
import com.codename1.util.MathUtil;

public class AttackStrategy implements IStrategy {
	private PlayerSquirrel s1;
	private NonPlayerSquirrel npc;
	private GameObjectCollection list;

	public AttackStrategy(GameObjectCollection list, NonPlayerSquirrel npc) {
		this.npc = npc;
		this.list = list;
	}

	@Override
	public void apply() {
		IIterator itr = list.getIterator();
		while(itr.hasNext()) {
			GameObject go = (GameObject) itr.getNext();
			if(go instanceof PlayerSquirrel) {
				s1 = ((PlayerSquirrel) go);
				break;
			}
		}

		float temp_x = s1.getLocation().getX() - npc.getLocation().getX();
		float temp_y = s1.getLocation().getY() - npc.getLocation().getY();
		float newAngle = (float)(90 -  Math.toDegrees(MathUtil.atan2((double)temp_x,(double)temp_y)));
		int angle = 90 - (int)newAngle;
		npc.setHeading(npc.getSteeringDirection() + angle);
	}
}
