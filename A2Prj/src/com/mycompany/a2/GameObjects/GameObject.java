package com.mycompany.a2.GameObjects;
import com.codename1.charts.models.Point;

public abstract class GameObject {
	//ATTRIBUTES
	private int size;
	private int color;
	private Point loc;
	
	//CONSTRUCTOR
	public GameObject(Point newLoc, int newSize, int newColor) {
		size = newSize;
		color = newColor;
		loc = newLoc;
	}
	
	//GETTERS
	public int getSize() {
		return size;
	}
	public int getColor() {
		return color;
	}
	public Point getLocation() {
		return loc;
	}
	
	//SETTERS
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
