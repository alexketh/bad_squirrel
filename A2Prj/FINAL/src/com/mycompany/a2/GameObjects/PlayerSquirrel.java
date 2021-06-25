package com.mycompany.a2.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class PlayerSquirrel extends Squirrel{
	
	// public Squirrel(Point location, int squirrelSize, int color, int speed, int heading) {
	public PlayerSquirrel(Point location, int squirrelSize, int color, int speed, int heading) {
		super(location, squirrelSize, color, speed, heading) ;
	}
	
	@Override
	public String toString() {
		//String x = super.toString();
		//return "PlayerSquirrel:\n" + x;
		String x = "PLAYER Squirrel:\n" 	+ " loc=" + this.getLocation().getX() + "," + this.getLocation().getY() 
				+ " color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]" 
				+ " heading=" + this.getHeading()
				+ " speed=" + this.getSpeed() 
				+ " size=" + this.getSize() 
				+ " maxSpeed=" + this.getMaxSpeed()
				+ " steeringDirection=" + this.getSteeringDirection() 
				+ " energyLevel=" + getEnergyLevel()
				+ " damageLevel=" + getDamageLevel(); 
		return x ;
	} 
	
}
