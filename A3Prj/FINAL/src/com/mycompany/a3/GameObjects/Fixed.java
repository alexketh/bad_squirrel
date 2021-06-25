package com.mycompany.a3.GameObjects;

import com.codename1.charts.models.Point;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.Interfaces.ISelectable;

public abstract class Fixed extends GameObject implements ISelectable {
	
	private boolean selected;

	//CONSTRUCTOR
	public Fixed(int size, Point location, int color, GameWorld gw) {
		//public GameObject(int newSize, int newColor, Point newLoc) {
		super(location, size, color, gw);
	}
	
	public void setLocation(float X, float Y) {
		if(selected) 
			super.setLocation(new Point(X,Y));
	}
	@Override
	public void setSelected(boolean y) {
		selected = y;
	}
	@Override
	public boolean isSelected() {
		return selected;
	}
	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int radius = super.getSize() / 2;
		int left = (int)Math.round(super.getLocation().getX() - radius + pCmpRelPrnt.getX());
		int right = left + super.getSize();
		int top = (int)Math.round(super.getLocation().getY() - radius + pCmpRelPrnt.getY());
		int bottom = top + super.getSize();
		boolean checkedLR = pPtrRelPrnt.getX() > left && pPtrRelPrnt.getX() < right;
		boolean checkedTB = pPtrRelPrnt.getY() > top && pPtrRelPrnt.getY() < bottom;
		return checkedLR && checkedTB;
	}

}
