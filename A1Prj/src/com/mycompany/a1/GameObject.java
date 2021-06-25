package com.mycompany.a1;

import com.codename1.charts.models.Point;

public abstract class GameObject {
	private int size;
	private int color;
	private Point loc;
	
	public GameObject(int newSize, int newColor, Point newLoc) {
		size = newSize;
		color = newColor;
		loc = newLoc;
	}
	
	public int getSize() {
		return size;
	}
	public int getColor() {
		return color;
	}
	public Point getLocation() {
		return loc;
	}
	
	public void setSize(int newSize) {
		newSize = size;
	}
	public void setColor(int newColor) {
		color = newColor;
	}
	public void setLocation(Point newLocation) {
		loc = newLocation;
	}
}
