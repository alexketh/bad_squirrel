package com.mycompany.a1;

import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject {

	// ATTRIBUTES
    private int speed;
    private int heading;
    
// CONSTRUCTOR
    public Movable(int speed, int heading, int size, Point location, int color) {
        super(size, color, location);
        this.speed = speed;
        this.heading = heading;
        //this.setSize(size);
        //this.setLocation(location);
        //this.setColor(color);
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
		
		double newLocationX = super.getLocation().getX() + deltaX;
		double newLocationY = super.getLocation().getY() + deltaY;
		
        newLocationX = Math.round(newLocationX * 10.0) / 10.0;
        newLocationY = Math.round(newLocationY * 10.0) / 10.0;
        
        Point newLoc = new Point((float)newLocationX, (float)newLocationY);
        
		super.setLocation(newLoc);
	}
}

