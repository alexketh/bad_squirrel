package com.mycompany.a3.GameObjects;
import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.Interfaces.ICollider;
import com.mycompany.a3.Interfaces.IDrawable;

public abstract class GameObject implements IDrawable, ICollider {
	//ATTRIBUTES
	private int size;
	private int color;
	private Point loc;
	private GameWorld gw;
	private boolean flag = false;

	//CONSTRUCTOR
	public GameObject(Point newLoc, int newSize, int newColor, GameWorld newGW) {
		size = newSize;
		color = newColor;
		loc = newLoc;
		this.gw = newGW;
	}

	public void draw(Graphics g, Point pCmpRelPrnt) {
		// Will get overridden
	}

	@Override
	public boolean collidesWith(ICollider otherObject){
		boolean result = false;
		int thisCenterX = (int)this.getLocation().getX() + (getSize()/2);
		int thisCenterY = (int)this.getLocation().getY() + (getSize()/2);
		int otherCenterX = (int) ((GameObject)otherObject).getLocation().getX() + (getSize()/2);
		int otherCenterY = (int) ((GameObject)otherObject).getLocation().getY() + (getSize()/2);

		//find distance between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);

		//find square of sum of radii
		int thisRadius = getSize()/2;
		int otherRadius = getSize()/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius
												+ otherRadius*otherRadius);
		if(distBetweenCentersSqr <= radiiSqr) {
			result = true;
		}
		return result;
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
	public GameWorld getGW() {
		return gw;
	}
	public boolean getFlag() {
		return flag;
	}
	//SETTERS
	public void setSize(int newSize) {
		size = newSize;
	}
	public void setColor(int newColor) {
		color = newColor;
	}
	public void setLocation(Point newLocation) {
		loc = newLocation;
	}
	public void setFlag(boolean newFlag) {
		flag = newFlag;
	}
}
