package com.mycompany.a3.GameObjects;

import com.codename1.charts.models.Point;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.Interfaces.ICollider;

public abstract class Movable extends GameObject{

	// ATTRIBUTES
    private int speed;
    private int heading;

// CONSTRUCTOR
    public Movable(int speed, int heading, int size, Point location, int color, GameWorld gw) {
        super(location, size, color, gw);
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
        int width = 1400;
        int height = 1100;

        float temp = 0;
        Point newLoc = new Point(0,0);

    	double theta = 90 - heading;
		theta = Math.toRadians(theta);
		double deltaX = Math.cos(theta)* speed;
		double deltaY = Math.sin(theta)* speed;

		float newX = super.getLocation().getX() + (float)deltaX;
		float newY = super.getLocation().getY() + (float)deltaY;


        // Checks if X is between 1000 and 0
        if (newX - getSize()/3 >= 0 && newX + getSize()/3 <= width) {
            // Checks if Y is between 1000 and 0
            if (newY + getSize()/3 <= height && newY - getSize()/3 >= 0) {
                newLoc = new Point(newX, newY);
            }
        }
        // Check if newX + Size is greater than 1000
        if (newX + getSize()/3 >= width) {
            temp = 0;
            temp = (newX + getSize()/3) - width;
            newLoc = new Point(newX - temp, newY);
        }
        // Checks if newX + Size is less than 0
        if (newX - getSize()/3 <= 0) {
            temp = 0;
            temp = newX - getSize()/3;
            //this.setLocation(newX + Math.abs(temp), newY);
            newLoc = new Point(newX + Math.abs(temp), newY);
        }
        // Check if newY + Size is greater than 1000
        if (newY + getSize()/3 >= height) {
            temp = 0;
            temp = (newY + getSize()/3) - height;
            //this.setLocation(newX, newY - temp);
            newLoc = new Point(newX, newY - temp);
        }
        // Checks if newY + Size is less than 0
        if (newY - getSize()/3 <= 0) {
            temp = 0;
            temp = newY - getSize()/3;
            //this.setLocation(newX, newY + Math.abs(temp));
            newLoc = new Point(newX, newY + Math.abs(temp));
        }

		super.setLocation(newLoc);
	}
}
