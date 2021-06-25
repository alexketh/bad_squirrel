package com.mycompany.a2.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.mycompany.a2.*;
import com.mycompany.a2.Interfaces.IStrategy;

public class NonPlayerSquirrel extends Squirrel {
	private IStrategy strat;
	
	// public Squirrel(Point location, int squirrelSize, int color, int speed, int heading) {
	public NonPlayerSquirrel(Point location, int squirrelSize, int color, int speed, int heading) {
		super(location, squirrelSize, color, speed, heading) ;
	}
	
	public void NpcCollisionWithSquirrel() {
		super.setDamageLevel(getDamageLevel()+15) ;		
		//super.setColor(ColorUtil.rgb(255 - (20 *(10 - this.getDamageLevel())) , 0, 0));		
		super.changeColorAndSpeed(getDamageLevel());
	}
	
	public void setStrategy(IStrategy s) {
		strat = s;
	}
	
	public String getStrategy() {
		if (strat instanceof AttackStrategy)
			return "Attack Strategy";
		else
			return "Nut Strategy";
	}
	
	public void invokeStrategy() {
		strat.apply();
	}
	
	@Override
	public String toString() {
		//String x = super.toString();
		//return "PlayerSquirrel:\n" + x;
		String x = "NPC Squirrel:\n" 	+ " loc=" + this.getLocation().getX() + "," + this.getLocation().getY() 
				+ " color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]" 
				+ " heading=" + this.getHeading()
				+ " speed=" + this.getSpeed() 
				+ " size=" + this.getSize() 
				+ " maxSpeed=" + this.getMaxSpeed()
				+ " steeringDirection=" + this.getSteeringDirection() 
				+ " energyLevel=" + getEnergyLevel()
				+ " damageLevel=" + getDamageLevel()
				+ " strategy=" + getStrategy();
		return x ;
	} 
}
