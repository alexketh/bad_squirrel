package com.mycompany.a3.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.Interfaces.*;

public class Squirrel extends Movable implements ISteerable {
	// ATTRIBUTES
	private int steeringDirection 		= 0;
	private int constantMaxSpeed		= 21;
	private int maxSpeed				= 21;
	private int energyLevel 			= 100;
	private int energyConsumptionRate 	= 1;
	private int damageLevel				= 0;
	private int maxDamageLevel			= 100;
	private int lastNutReached			= 1;

	// CONSTRUCTOR
	public Squirrel(Point location, int squirrelSize, int color, int speed, int heading, GameWorld gw) {
		// public Movable(int speed, int heading, int size, Point location, int color) {
		super(speed, heading, squirrelSize, location, color, gw);
	}

	public boolean isMaxDamageLevel() {
		if (damageLevel == maxDamageLevel)
			return true;
		return false;
	}

	public void decreaseEnergyLevel() {
		if ( super.getGW().getClock() % 20 == 0 )
			setEnergyLevel( getEnergyLevel() - getEnergyConsumptionRate() );
	}

	@Override
	public void handleCollision(ICollider otherObject) {
		// Nothing
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// Will get overridden
	}

	public String toString() {
		String x = "Squirrel:" 	+ " loc=" + this.getLocation().getX() + "," + this.getLocation().getY()
								+ " color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]"
								+ " heading=" + this.getHeading()
								+ " speed=" + this.getSpeed()
								+ " size=" + this.getSize()
								+ " maxSpeed=" + maxSpeed
								+ " steeringDirection=" + steeringDirection
								+ " energyLevel=" + energyLevel
								+ " damageLevel=" + damageLevel;
		return x ;
	}

	// GETTERS
	public int getSteeringDirection() {
		return steeringDirection;
	}
	public int getConstantMaxSpeed() {
		return constantMaxSpeed;
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public int getEnergyLevel() {
		return this.energyLevel;
	}
	public int getEnergyConsumptionRate() {
		return this.energyConsumptionRate;
	}
	public int getDamageLevel() {
		return this.damageLevel;
	}
	public int getMaxDamageLevel() {
		return this.maxDamageLevel;
	}
	public int getLastNutReached() {
		return this.lastNutReached;
	}
//------------------------------------------------------//
	// SETTERS
	public void setSteeringDirection(int newSteering) {
		this.steeringDirection = newSteering;
	}
	public void setConstantMaxSpeed(int newCMaxSpeed) {
		this.constantMaxSpeed = newCMaxSpeed;
	}
	public void setMaxSpeed(int newMaxSpeed) {
		this.maxSpeed = newMaxSpeed;
	}
	public void setEnergyLevel(int newEnergyLevel) {
		this.energyLevel = newEnergyLevel;
	}
	public void setEnergyConsumptionRate(int newEnergyConsumptionRate) {
		this.energyConsumptionRate = newEnergyConsumptionRate;
	}
	public void setDamageLevel(int newDamageLevel) {
		this.damageLevel = newDamageLevel;
	}
	public void setMaxDamageLevel(int newMaxDamageLevel) { this.maxDamageLevel = newMaxDamageLevel; }
	public void setLastNutReached(int x) {
		this.lastNutReached = x;
	}
	public void addDamageLevel(int dmgLevel) {
		this.damageLevel += dmgLevel;
	}

	// METHODS
	public void accelerate() {
		setSpeed(this.getSpeed() + 1);
		if (this.getSpeed() > this.maxSpeed) {
			setSpeed(this.getSpeed() - 1);
		}
	}

	public void brake() {
		setSpeed(this.getSpeed() - 1);
		if (this.getSpeed() < 0) {
			setSpeed(0);
		}
	}

	@Override
	public void steerLeft() {
		if (steeringDirection < 40) {
			steeringDirection += 20;
			this.setHeading(this.getHeading() + 20);
			System.out.println("Squirrel turning left: " + steeringDirection + " degrees");
		} else
			System.out.println("Can't turn anymore");
	}

	@Override
	public void steerRight() {
		if (steeringDirection > -40) {
			steeringDirection -= 20;
			this.setHeading(this.getHeading() - 20);
			System.out.println("Squirrel turning right "+ steeringDirection + " degrees");
		} else
			System.out.println("Can't turn anymore");
	}

	public void changeSpeed() {
		if(!isMaxDamageLevel()) {
			setMaxSpeed( maxSpeed - getDamageLevel()  / 100 * getConstantMaxSpeed() );
		}
		if( getSpeed() > maxSpeed ) {
			setSpeed(maxSpeed);
		}
	}
}
