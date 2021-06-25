package com.mycompany.a2.GameObjects;

import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject {
	
	//CONSTRUCTOR
	public Fixed(int size, Point location, int color) {
		//public GameObject(int newSize, int newColor, Point newLoc) {
		super(location, size, color);
	}
	
}
