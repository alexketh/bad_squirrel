package com.mycompany.a2.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Bird extends Movable {

	public Bird(int speed, int heading, int size, Point location, int color) {
		super(speed, heading, size, location, color);
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
