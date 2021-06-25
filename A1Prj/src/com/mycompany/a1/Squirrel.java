package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Squirrel extends Movable implements ISteerable {
	// ATTRIBUTES
	private int steeringDirection;
	private int maxSpeed;
	private int energyLevel;
	private int energyConsumptionRate;
	private int damageLevel;
	private int lastNutReached;
	private int R;
	private int G;
	private int B;

	// CONSTRUCTOR
	public Squirrel(int steeringDirection, int maxSpeed, int energyLevel, int energyConsumptionRate, int damageLevel, 
			int lastNutReached, Point location, int squirrelSize, int R, int G, int B, int initialSpeed, int initialHeading) {
		// public Movable(int speed, int heading, int size, Point location, int color) {
		super(initialSpeed, initialHeading, squirrelSize, location, ColorUtil.rgb(R,G,B)); // might not work
		this.steeringDirection = steeringDirection;
		this.maxSpeed = maxSpeed;
		this.energyLevel = energyLevel;
		this.energyConsumptionRate = energyConsumptionRate;
		this.damageLevel = damageLevel;
		this.lastNutReached = lastNutReached;
		this.R = R;
		this.G = G;
		this.B = B;
	}

	public String toString() {
		String x = "Squirrel: " + "loc=" + this.getLocation().getX() + "," + this.getLocation().getY() 
								+ " color=[" + R + "," + G + "," + B + "] heading=" + this.getHeading()
								+ " speed=" + this.getSpeed() + " size=" + this.getSize() + " maxSpeed=" + maxSpeed 
								+ " steeringDirection=" + steeringDirection + " energyLevel=" + energyLevel
								+ " damageLevel=" + damageLevel; 
		return x ;
	}
	
	// GETTERS
	public int getSteeringDirection() {
		return steeringDirection;
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

	public int getLastNutReached() {
		return this.lastNutReached;
	}

	// SETTERS
	public void setSteeringDirection(int newSteering) {
		this.steeringDirection = newSteering;
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

	public void setLastNutReached() {
		this.lastNutReached += 1;
	}

	public void addDamageLevel(int dmgLevel) {
		this.damageLevel += dmgLevel;
	}

	// METHODS

	public void accelerate() {
		setSpeed(this.getSpeed() + 10);
		if (this.getSpeed() >= this.maxSpeed) {
			setSpeed(this.getSpeed() - 10);
		}
	}

	public void brake() {
		setSpeed(this.getSpeed() - 10);
		if (this.getSpeed() < 0) {
			setSpeed(0);
		}
	}

	public void steerRight() {
		if (steeringDirection < 40) {
			steeringDirection += 5;
			this.setHeading(this.getHeading() + 5);
			System.out.println("Squirrel turning right: " + steeringDirection + " degrees");
		} else
			System.out.println("Can't turn anymore");
	}

	public void steerLeft() {
		if (steeringDirection > this.getHeading()-40) {
			steeringDirection -= 5;
			this.setHeading(this.getHeading() - 5);
			System.out.println("Squirrel turning left "+ steeringDirection + " degrees");
		} else
			System.out.println("Can't turn anymore");
	}

	public void changeColorAndSpeed(int damage) {
		// Case 1-10 Brown to Light Brown
		switch (damage) {
		case 1:
			this.setColor(ColorUtil.rgb(28, 6, 0));
			if (this.getMaxSpeed() != 0) {
				this.setMaxSpeed(this.getMaxSpeed() - 10);
			}
			if (this.getSpeed() != 0) {
				this.setSpeed(this.getSpeed() - 10);
			}
			break;
		case 2:
			this.setColor(ColorUtil.rgb(77, 25, 0));
			if (this.getMaxSpeed() != 0) {
				this.setMaxSpeed(this.getMaxSpeed() - 10);
			}
			if (this.getSpeed() != 0) {
				this.setSpeed(this.getSpeed() - 10);
			}
			break;
		case 3:
			this.setColor(ColorUtil.rgb(128, 42, 0));
			if (this.getMaxSpeed() != 0) {
				this.setMaxSpeed(this.getMaxSpeed() - 10);
			}
			if (this.getSpeed() != 0) {
				this.setSpeed(this.getSpeed() - 10);
			}
			break;
		case 4:
			this.setColor(ColorUtil.rgb(179, 59, 0));
			if (this.getMaxSpeed() != 0) {
				this.setMaxSpeed(this.getMaxSpeed() - 10);
			}
			if (this.getSpeed() != 0) {
				this.setSpeed(this.getSpeed() - 10);
			}
			break;
		case 5:
			this.setColor(ColorUtil.rgb(230, 76, 0));
			if (this.getMaxSpeed() != 0) {
				this.setMaxSpeed(this.getMaxSpeed() - 10);
			}
			if (this.getSpeed() != 0) {
				this.setSpeed(this.getSpeed() - 10);
			}
			break;
		case 6:
			this.setColor(ColorUtil.rgb(255, 85, 0));
			if (this.getMaxSpeed() != 0) {
				this.setMaxSpeed(this.getMaxSpeed() - 10);
			}
			if (this.getSpeed() != 0) {
				this.setSpeed(this.getSpeed() - 10);
			}
			break;
		case 7:
			this.setColor(ColorUtil.rgb(255, 119, 51));
			if (this.getMaxSpeed() != 0) {
				this.setMaxSpeed(this.getMaxSpeed() - 10);
			}
			if (this.getSpeed() != 0) {
				this.setSpeed(this.getSpeed() - 10);
			}
			break;
		case 8:
			this.setColor(ColorUtil.rgb(255, 153, 102));
			if (this.getMaxSpeed() != 0) {
				this.setMaxSpeed(this.getMaxSpeed() - 10);
			}
			if (this.getSpeed() != 0) {
				this.setSpeed(this.getSpeed() - 10);
			}
			break;
		case 9:
			this.setColor(ColorUtil.rgb(255, 187, 153));
			if (this.getMaxSpeed() != 0) {
				this.setMaxSpeed(this.getMaxSpeed() - 10);
			}
			if (this.getSpeed() != 0) {
				this.setSpeed(this.getSpeed() - 10);
			}
			break;

		case 10:
			this.setColor(ColorUtil.rgb(255, 221, 204));
			if (this.getMaxSpeed() != 0)
				this.setMaxSpeed(this.getMaxSpeed() - 10);
			if (this.getSpeed() != 0)
				this.setSpeed(this.getSpeed() - 10);
			break;
		}
	}
}
