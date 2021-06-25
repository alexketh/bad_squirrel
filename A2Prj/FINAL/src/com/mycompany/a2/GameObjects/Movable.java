package com.mycompany.a2.GameObjects;

import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject {

	// ATTRIBUTES
    private int speed;
    private int heading;
    
// CONSTRUCTOR
    public Movable(int speed, int heading, int size, Point location, int color) {
        super(location, size, color);
        this.speed = speed;
        this.heading = heading;
    }
    
// GETTERS
    public int getSpeed(){
        return speed;
    }
    public int getHeading(){
        return heading;
    }
// SETTERS
    public void setSpeed(int newSpeed){
        speed = newSpeed;
    }
    public void setHeading(int newHeading){
        heading = newHeading;
    }
   
//Squirrel moves by setting location's X and Y with different formulas.
    public void move(){
    	double theta = 90 - heading;
		theta = Math.toRadians(theta);
		double deltaX = Math.cos(theta)* speed;
		double deltaY = Math.sin(theta)* speed;
		
		double newX = super.getLocation().getX() + deltaX;
		double newY = super.getLocation().getY() + deltaY;
		
        newX = Math.round(newX * 10.0) / 10.0;
        newY = Math.round(newY * 10.0) / 10.0;
        
        if (	0 + this.getSize() < newX && newX < 1000 - this.getSize() && 
        		0 + this.getSize() < newY && newY < 1000 - this.getSize()	) {
		} else {
			if (newX > 1024) {
				newX = 1024-this.getSize();
			} else if (newX < 0) {
				newX = 0+this.getSize();
			}
			if (newY > 768) {
				newY = 768-this.getSize();
			} else if (newY < 0) {
				newY = 0+this.getSize();
			}
		}
        
        Point newLoc = new Point((float)newX, (float)newY);
        
		super.setLocation(newLoc);
	}
}

