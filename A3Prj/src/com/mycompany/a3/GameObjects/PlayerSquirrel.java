package com.mycompany.a3.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.Interfaces.ICollider;
import com.mycompany.a3.Interfaces.IDrawable;

public class PlayerSquirrel extends Squirrel {

	// public Squirrel(Point location, int squirrelSize, int color, int speed, int heading) {
	public PlayerSquirrel(Point location, int squirrelSize, int color, int speed, int heading, GameWorld gw) {
		super(location, squirrelSize, color, speed, heading, gw) ;
	}

	@Override
	public void handleCollision(ICollider otherObject) {
		// TO-DO
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(super.getColor());
		int x =  (int)this.getLocation().getX()+ (int)pCmpRelPrnt.getX();
		int y =   (int)this.getLocation().getY()+(int)pCmpRelPrnt.getY();
		g.fillRect(x, y, this.getSize(), this.getSize());
		/*
		double line = Math.toRadians(90 - super.getHeading());
		double steerX = ((Math.cos(line) * 50) + this.getLocation().getX() + pCmpRelPrnt.getX());
		double steerY = ((Math.sin(line) * 50) + this.getLocation().getY() + pCmpRelPrnt.getY());
		g.setColor(ColorUtil.BLACK);
		g.drawLine((int)this.getLocation().getX() + (int)pCmpRelPrnt.getX(),(int)this.getLocation().getY() + 10 + (int)pCmpRelPrnt.getY(), (int)steerX, (int)steerY);

		 */
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
				+ " damageLevel=" + getDamageLevel()
				+ " lastNutReached=" + this.getLastNutReached();;
		return x ;
	}

}
