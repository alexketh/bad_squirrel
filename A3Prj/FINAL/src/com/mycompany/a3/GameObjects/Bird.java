package com.mycompany.a3.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.Interfaces.ICollider;
import com.mycompany.a3.Interfaces.IDrawable;
import com.mycompany.a3.Interfaces.IIterator;

public class Bird extends Movable {

	public Bird(int speed, int heading, int size, Point location, int color, GameWorld gw) {
		super(speed, heading, size, location, color, gw);
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(super.getColor());

		int x = (int)this.getLocation().getX() + (int)pCmpRelPrnt.getX();
		int y = (int)this.getLocation().getY() + (int)pCmpRelPrnt.getY();

		int[] xPoints = new int[3];
		int[] yPoints = new int[3];

		xPoints[0] = x;
		yPoints[0] = y +(this.getSize()/2);
		xPoints[1] = x +(this.getSize()/2);
		yPoints[1] = y -(this.getSize()/2);
		xPoints[2] = x -(this.getSize()/2);
		yPoints[2] = y -(this.getSize()/2);

		g.fillPolygon(xPoints, yPoints, 3);
		/*
		if (super.isSelected())
			g.drawPolygon(xPoints, yPoints, nPoints);
		else
			g.fillPolygon(xPoints, yPoints, nPoints);
		 */
		g.setColor(ColorUtil.BLACK);
		g.drawPolygon(xPoints, yPoints, 3);
	}


	@Override
	public void handleCollision(ICollider otherObject) {
		if (otherObject instanceof PlayerSquirrel) {
			if (((PlayerSquirrel) otherObject).isMaxDamageLevel()) {
				super.getGW().setLives( super.getGW().getLives() - 1);
				super.getGW().reset();
				super.getGW().quit(3);
			} else {
				super.getGW().playSound(1);
				((PlayerSquirrel) otherObject).addDamageLevel(1);
				((PlayerSquirrel) otherObject).changeSpeed();
				((PlayerSquirrel) otherObject).setColor(ColorUtil.rgb(
						(int) (25 + ( 2.55 * ((PlayerSquirrel) otherObject).getDamageLevel())),
						(int) (22 + ( 2.21 * ((PlayerSquirrel) otherObject).getDamageLevel())),
						(int) (20 + ( 2.04 * ((PlayerSquirrel) otherObject).getDamageLevel())) ));
			}
		} else if (otherObject instanceof NonPlayerSquirrel) {
			if (((NonPlayerSquirrel) otherObject).isMaxDamageLevel()) {
				((NonPlayerSquirrel) otherObject).setSpeed(0);
			} else {
				((NonPlayerSquirrel) otherObject).addDamageLevel(1);
				((NonPlayerSquirrel) otherObject).changeSpeed();
				((NonPlayerSquirrel) otherObject).setColor(ColorUtil.rgb(255, 255, (int) (0 + ( 2.55 * ((NonPlayerSquirrel) otherObject).getDamageLevel())) ));
			}
		}
	}

	public String toString() {
		String x = "Bird:" 	+ " loc=" + this.getLocation().getX() + "," + this.getLocation().getY()
							+ " color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]"
							+ " heading=" + this.getHeading()
							+ " speed=" + this.getSpeed()
							+ " size=" + this.getSize();
		return x ;
	}
}
