package com.mycompany.a3.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.*;
import com.mycompany.a3.Interfaces.ICollider;
import com.mycompany.a3.Interfaces.IDrawable;
import com.mycompany.a3.Interfaces.IStrategy;

public class NonPlayerSquirrel extends Squirrel {
	private IStrategy currStrategy;

	// public Squirrel(Point location, int squirrelSize, int color, int speed, int heading) {
	public NonPlayerSquirrel(Point location, int squirrelSize, int color, int speed, int heading, GameWorld gw) {
		super(location, squirrelSize, color, speed, heading, gw) ;
		super.setEnergyConsumptionRate(0);

	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(super.getColor());
		int x = (int) this.getLocation().getX() + (int) pCmpRelPrnt.getX();
		int y = (int) this.getLocation().getY() + (int) pCmpRelPrnt.getY();
		g.fillRect(x, y, this.getSize(), this.getSize());
		g.setColor(ColorUtil.rgb(139,69,19));
		g.drawRect(x, y, this.getSize(), this.getSize());
		//g.setColor(ColorUtil.rgb(139,69,19));
	}

	public void setStrategy(IStrategy s) {
		currStrategy = s;
	}
	public void invokeStrategy() {
		currStrategy.apply();
	}

	public String getStrategy() {
		if (currStrategy instanceof AttackStrategy)
			return "Attack Strategy";
		else
			return "Nut Strategy";
	}

	public void checkDamageLevel() {
		if (isMaxDamageLevel()) {
			setMaxSpeed(0);
		} else {
			changeSpeed();
		}
	}

	@Override
	public void handleCollision(ICollider otherObject) {
		if (otherObject instanceof PlayerSquirrel) {
			if (((PlayerSquirrel) otherObject).isMaxDamageLevel()) {
				super.getGW().setLives(super.getGW().getLives() - 1);
				super.getGW().reset();
				super.getGW().quit(3);
			} else if (!this.isMaxDamageLevel()){
				super.getGW().playSound(1);
				((PlayerSquirrel) otherObject).addDamageLevel(1);
				this.addDamageLevel(1);
				((PlayerSquirrel) otherObject).changeSpeed();
				this.checkDamageLevel();
				((PlayerSquirrel) otherObject).setColor(ColorUtil.rgb(
						(int) (25 + ( 2.55 * ((PlayerSquirrel) otherObject).getDamageLevel())),
						(int) (22 + ( 2.21 * ((PlayerSquirrel) otherObject).getDamageLevel())),
						(int) (20 + ( 2.04 * ((PlayerSquirrel) otherObject).getDamageLevel())) ));
				this.setColor( ColorUtil.rgb(255, 255, (int) (0 + ( 2.55 * this.getDamageLevel()))) );
			}
		}
		else if (otherObject instanceof NonPlayerSquirrel) {
			if (((NonPlayerSquirrel) otherObject).isMaxDamageLevel() || this.isMaxDamageLevel()) {
				if (((NonPlayerSquirrel) otherObject).isMaxDamageLevel()) {
					((NonPlayerSquirrel) otherObject).setSpeed(0);
					((NonPlayerSquirrel) otherObject).setColor(ColorUtil.rgb(255, 255, (int) (0 + ( 2.55 * ((NonPlayerSquirrel) otherObject).getDamageLevel())) ));
				} else if (this.isMaxDamageLevel()) {
					this.setSpeed(0);
					this.setColor( ColorUtil.rgb(255, 255, (int) (0 + ( 2.55 * this.getDamageLevel()))) );
				}
			} else {
				((NonPlayerSquirrel) otherObject).addDamageLevel(1);
				this.addDamageLevel(1);
				((NonPlayerSquirrel) otherObject).checkDamageLevel();
				this.checkDamageLevel();
				((NonPlayerSquirrel) otherObject).setColor(ColorUtil.rgb(255, 255, (int) (0 + ( 2.55 * ((NonPlayerSquirrel) otherObject).getDamageLevel())) ));
				this.setColor( ColorUtil.rgb(255, 255, (int) (0 + ( 2.55 * this.getDamageLevel()))) );
			}
		}
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
				+ " strategy=" + getStrategy()
				+ " lastNutReached=" + this.getLastNutReached();
		return x ;
	}
}
