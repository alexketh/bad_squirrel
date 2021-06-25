package com.mycompany.a2.GameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Nut extends Fixed {

	private int sequenceNumber;

	public Nut(Point location, int size, int seq,  int color) {
		super(size, location, color);
		this.sequenceNumber = seq;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}
	
	public String toString() {
		String x = "Nut:" 	+ " loc=" + this.getLocation().getX() + "," + this.getLocation().getY() 
							+ " color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]" 
							+ " size=" + this.getSize() 
							+ " seqNumber=" + sequenceNumber; 
		return x ;
	}
}
