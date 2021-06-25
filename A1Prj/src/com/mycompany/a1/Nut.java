package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Nut extends Fixed {

	private int sequenceNumber;
	private int R;
	private int G;
	private int B;

	public Nut(Point location, int size, int seq,  int R, int G, int B) {
		super(size, location, ColorUtil.rgb(R, G, B));
		this.sequenceNumber = seq;
		//this.setColor(color);
		//this.setLocation(new Point(this.getLocation().getX(), this.getLocation().getY()));
		//this.setSize(this.getSize());
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}
	
	public String toString() {
		String x = "Nut: " + "loc=" + this.getLocation().getX() + "," + this.getLocation().getY() 
								+ " color=[" + R + "," + G + "," + B + "] size=" + this.getSize() + " seqNumber=" + sequenceNumber; 
		return x ;
	}
}
