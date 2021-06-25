package com.mycompany.a1;

import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject {
	public Fixed(int size, Point location, int color) {
		//public GameObject(int newSize, int newColor, Point newLoc) {
		super(size, color, location);
	}
}
