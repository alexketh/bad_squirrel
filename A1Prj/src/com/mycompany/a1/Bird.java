package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Bird extends Movable {
	private int R;
	private int G;
	private int B;

	public Bird(int speed, int heading, int size, Point location, int R, int G, int B) {
		super(speed, heading, size, location, ColorUtil.rgb(R, G, B));
		this.R = R;
		this.G = G;
		this.B = B;
	}

	public String toString() {
		String x = "Bird: " + "loc=" + this.getLocation().getX() + "," + this.getLocation().getY() 
								+ " color=[" + R + "," + G + "," + B + "] heading=" + this.getHeading()
								+ " speed=" + this.getSpeed() + " size=" + this.getSize();
		return x ;
	}
}
